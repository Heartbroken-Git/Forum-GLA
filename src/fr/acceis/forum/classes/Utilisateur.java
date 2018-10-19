package fr.acceis.forum.classes;

public class Utilisateur {

    private int id;
    private String login;

    public Utilisateur(int i, String name) {
        id = i;
        login = name;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
