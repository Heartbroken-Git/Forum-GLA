package fr.acceis.forum.classes;

public class Sujet {

    private int idSujet;
    private String titre;
    private Utilisateur auteur;
    private int nbMessages;

    public Sujet(int id, String t, Utilisateur idAut, int n) {
        idSujet = id;
        titre = t;
        auteur = idAut;
        nbMessages = n;
    }

    public int getIdSujet() {
        return idSujet;
    }

    public String getTitre() {
        return titre;
    }

    public Utilisateur getAuteur() {
        return auteur;
    }

    public int getNbMessages() {
        return nbMessages;
    }
}
