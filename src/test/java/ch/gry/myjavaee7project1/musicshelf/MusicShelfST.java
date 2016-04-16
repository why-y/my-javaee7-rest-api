package ch.gry.myjavaee7project1.musicshelf;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class MusicShelfST {

	protected Client client = ClientBuilder.newClient();
	protected WebTarget apiRoot = client.target("http://localhost:8080/MusicShelf-JEE7-RestApi/api/");

}
