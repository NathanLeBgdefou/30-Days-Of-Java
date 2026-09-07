package fr.cours.demo;

public final class Message {
    private Message() {}

    public static String texte(String prenom) {
        return "Bonjour " + prenom;
    }
}
