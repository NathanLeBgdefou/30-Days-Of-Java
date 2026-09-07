"""Compile et vérifie les exemples, leurs sorties, le projet et les liens internes.

Usage : python scripts/verifier.py depuis la racine du dépôt.
Prérequis : Python 3.10+ et JDK 21 (les sources restent compatibles Java 17).
"""
from concurrent.futures import ThreadPoolExecutor
from pathlib import Path
import json
import os
import re
import shutil
import subprocess
import tempfile
import unicodedata

ROOT = Path(__file__).resolve().parents[1]
JAVA = shutil.which('java')
JAVAC = shutil.which('javac')
if not JAVA:
    raise SystemExit('Java introuvable. Installer un JDK et vérifier le PATH.')
COMPILER = [JAVAC] if JAVAC else [JAVA, '-m', 'jdk.compiler/com.sun.tools.javac.Main']

def run(cmd, cwd, stdin='', expected_code=0):
    result = subprocess.run(cmd, cwd=cwd, input=stdin, capture_output=True,
                            text=True, encoding='utf-8', timeout=35)
    if result.returncode != expected_code:
        raise AssertionError(f"Commande échouée ({result.returncode}) : {' '.join(map(str,cmd))}\n{result.stdout}\n{result.stderr}")
    return result

def slug(s):
    s=re.sub(r'[`*_]','',s).lower()
    return ''.join(c for c in s if c.isalnum() or c in '- _').replace(' ','-')

def check_links():
    checked=0
    for p in ROOT.rglob('*.md'):
        text=p.read_text(encoding='utf-8')
        if text.count('<details>') != text.count('</details>'):
            raise AssertionError(f'Bloc repliable déséquilibré : {p}')
        if len(re.findall(r'^```', text, re.M)) % 2:
            raise AssertionError(f'Bloc de code déséquilibré : {p}')
        links=re.findall(r'\]\(([^)]+)\)',text)+re.findall(r'<img[^>]+src="([^"]+)"',text)
        for link in links:
            if link.startswith(('https:','http:','mailto:')):
                continue
            path,_,fragment=link.partition('#')
            dest=(p.parent/path).resolve() if path else p
            if not dest.exists():
                raise AssertionError(f'Lien absent dans {p.relative_to(ROOT)} : {link}')
            if fragment and dest.suffix.lower()=='.md':
                target=dest.read_text(encoding='utf-8')
                anchors=set(re.findall(r'\bid="([^"]+)"',target))
                anchors.update(slug(h) for h in re.findall(r'^#{1,6}\s+(.+)$',target,re.M))
                if fragment not in anchors:
                    raise AssertionError(f'Ancre absente dans {p.relative_to(ROOT)} : {link}')
            checked+=1
    return checked

def check_fragments(work):
    count=0
    for p in ROOT.glob('*_Day_*/*.md'):
        if p.name=='README.md': continue
        for snippet in re.findall(r'```java fragment\n(.*?)\n```',p.read_text(encoding='utf-8'),re.S):
            count+=1
            name=f'Fragment{count}'
            source=work/f'{name}.java'
            source.write_text(f'public class {name} {{ public static void main(String[] args) {{\n{snippet}\n}} }}',encoding='utf-8')
    sources=sorted(work.glob('Fragment*.java'))
    if sources:
        run(COMPILER+['-encoding','UTF-8','-d',str(work)]+[str(p) for p in sources],ROOT)
    return count

def main():
    cases=json.loads((ROOT/'tests/cases.json').read_text(encoding='utf-8'))
    assert len(cases)==120, '30 exemples et 90 corrigés attendus.'
    chapters=list(ROOT.glob('*_Day_*'))
    assert len(chapters)==30, '30 dossiers de journées attendus.'
    with tempfile.TemporaryDirectory(prefix='verification-java-') as tmp:
        work=Path(tmp)
        compiled=work/'classes'
        compiled.mkdir()
        # Un seul appel de compilation, noms de classes uniques entre les journées.
        source_list=work/'sources.txt'
        source_list.write_text('\n'.join('"'+str(ROOT/c['path']).replace('\\','/')+'"' for c in cases),encoding='utf-8')
        run(COMPILER+['-encoding','UTF-8','-d',str(compiled),'@'+str(source_list)],ROOT)
        print('120 programmes de leçon compilés.',flush=True)

        def check_case(c):
            case_dir=work/c['main'];case_dir.mkdir()
            result=run([JAVA,'-Dfile.encoding=UTF-8','-cp',str(compiled),c['main']],case_dir,c['stdin'])
            if result.stdout.rstrip('\r\n') != c['stdout'].rstrip('\r\n'):
                raise AssertionError(f"Sortie incorrecte : {c['path']}\nAttendu : {c['stdout']!r}\nObtenu : {result.stdout!r}")
            return c['main']
        with ThreadPoolExecutor(max_workers=4) as pool:
            list(pool.map(check_case,cases))
        print('120 sorties conformes aux résultats annoncés.',flush=True)

        # Vérifier aussi le mode source présenté aux débutants, avec deux cas structuraux.
        run([JAVA,str(ROOT/'01_Day_Introduction/exemples/Bonjour.java')],work)
        run([JAVA,str(ROOT/'19_Day_Equality_and_Immutability/exemples/Jour19Exemple.java')],work)
        fragments=check_fragments(work)
        print(f'{fragments} extraits pédagogiques compilés.',flush=True)

        mini=ROOT/'24_Day_Packages_and_Projects/projet'
        mini_out=work/'mini';mini_out.mkdir()
        run(COMPILER+['-encoding','UTF-8','-d',str(mini_out)]+[str(p) for p in mini.rglob('*.java')],work)
        assert run([JAVA,'-cp',str(mini_out),'fr.cours.demo.App'],work).stdout.strip()=='Bonjour Nathan'
        print('Projet du jour 24 vérifié.',flush=True)

        project=ROOT/'29_Day_Final_Project/projet'
        project_out=work/'project';project_out.mkdir()
        run(COMPILER+['-encoding','UTF-8','-d',str(project_out)]+[str(p) for p in project.rglob('*.java')],work)
        tests=run([JAVA,'-Dfile.encoding=UTF-8','-cp',str(project_out),'fr.cours.revisions.TestsProjet'],work)
        print(tests.stdout.strip(),flush=True)
        launch=[JAVA,'-Dfile.encoding=UTF-8','-cp',str(project_out),'fr.cours.revisions.Main']
        data=work/'session'/'taches.tsv'
        first=run(launch+[str(data)],work,'1\nRéviser Java\n2026-10-15\n2\n0\n')
        assert 'Tâche ajoutée et enregistrée (#1).' in first.stdout
        assert '1 | à faire | 2026-10-15 | Réviser Java' in first.stdout
        second=run(launch+[str(data)],work,'3\n1\n2\n0\n')
        assert '1 | terminée | 2026-10-15 | Réviser Java' in second.stdout
        third=run(launch+[str(data)],work,'2\n0\n')
        assert '1 | terminée | 2026-10-15 | Réviser Java' in third.stdout
        before=data.read_bytes()
        errors=run(launch+[str(data)],work,'1\nAutre\n2026-02-30\n3\ninvalide\n3\n999\n0\n')
        assert 'Date invalide' in errors.stdout and "L'identifiant doit être un entier" in errors.stdout
        assert 'Aucune tâche avec' in errors.stdout and data.read_bytes()==before
        data.write_text('Fichier volontairement invalide',encoding='utf-8')
        invalid=run(launch+[str(data)],work,'1\nNe doit pas être ajouté\n2026-10-15\n0\n',expected_code=1)
        assert 'Ouverture impossible' in invalid.stderr
        assert data.read_text(encoding='utf-8')=='Fichier volontairement invalide'
        print('5 scénarios du menu vérifiés : ajout, terminaison, relance, saisies invalides, fichier invalide préservé.',flush=True)

    links=check_links()
    print(f'{links} liens et ancres internes vérifiés.',flush=True)
    print('VÉRIFICATION RÉUSSIE',flush=True)

if __name__=='__main__':
    main()
