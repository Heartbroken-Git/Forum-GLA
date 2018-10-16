package fr.acceis.forum.classes;

public class Sujet {

    private int idSujet;
    private String titre;
    private int auteur;
    private int nbMessages;

    public Sujet(int id, String t, int idAut, int n) {
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

    public int getAuteur() {
        return auteur;
    }

    public int getNbMessages() {
        return nbMessages;
    }
}
