package fr.acceis.forum.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JeuDeTestJdBc {

	public final static String[] QUERIES = {
			"drop table Utilisateurs if exists",
			"create table Utilisateurs (id bigint not null, login varchar(255), password varchar(255), primary key (id))",
			"INSERT INTO UTILISATEURS VALUES(1,'admin', 'admin')",
			"INSERT INTO UTILISATEURS VALUES(2,'pierre', 'pierre')",
			"INSERT INTO UTILISATEURS VALUES(3,'paul', 'paul')",
			"INSERT INTO UTILISATEURS VALUES(4,'jacques', 'jacques')",
	};

	public static void main(String[] args) throws Exception {
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa",  "");
		Statement stmt = connexion.createStatement();

		for (String query : QUERIES) {
			stmt.executeUpdate(query);
		}

		stmt.close();
		connexion.close();

	}

}
