package fr.acceis.forum.classes;

public class Message {

    private int idMessage;
    private String contenu;
    private Utilisateur auteur;
    private Sujet sujet;

    public Message(int idM, String c, Utilisateur u, Sujet s) {
        idMessage = idM;
        contenu = c;
        auteur = u;
        sujet = s;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public String getContenu() {
        return contenu;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public Utilisateur getAuteur() {
        return auteur;
    }
}
