package ch.gry.myjavaee7project1.musicshelf.artists.boundary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import ch.gry.myjavaee7project1.musicshelf.MusicShelfST;
import ch.gry.myjavaee7project1.musicshelf.artists.entity.Artist;

public class ArtistsST extends MusicShelfST {

	private WebTarget artistsRoot = apiRoot.path("artists");

	// Expected JSON Artists:
	private static final JsonObject ELTON = Json.createObjectBuilder()
			.add("id", 9).add("name", "Sir Elton John").add("origin", "UK")
			.add("links", Json.createArrayBuilder()
					.add(Json.createObjectBuilder()
							.add("rel", "self")
							.add("href", "http://localhost:8080/MusicShelf-JEE7-RestApi/api/artists/9")))
			.build();
	private static final JsonObject GAGA = Json.createObjectBuilder()
			.add("id", 10).add("name", "Lady Gaga").add("origin", "US")
			.add("links", Json.createArrayBuilder()
					.add(Json.createObjectBuilder()
							.add("rel", "self")
							.add("href", "http://localhost:8080/MusicShelf-JEE7-RestApi/api/artists/10")))
			.build();
	private static final JsonObject NELLY = Json.createObjectBuilder()
			.add("id", 11).add("name", "Nelly Furtado").add("origin", "CA")
			.add("links", Json.createArrayBuilder()
					.add(Json.createObjectBuilder()
							.add("rel", "self")
							.add("href", "http://localhost:8080/MusicShelf-JEE7-RestApi/api/artists/11")))
			.build();
	private static final JsonObject JOVA = Json.createObjectBuilder()
			.add("id", 12).add("name", "Jovanotti").add("origin", "IT")
			.add("links", Json.createArrayBuilder()
					.add(Json.createObjectBuilder()
							.add("rel", "self")
							.add("href", "http://localhost:8080/MusicShelf-JEE7-RestApi/api/artists/12")))
			.build();


	@Test
	public void testGetAllArtists() {
		Response response = artistsRoot.request().get();
		assertThat(response.getStatus(), is(200));

		JsonArray receivedJsonArr = Json.createReader(new StringReader(response.readEntity(String.class))).readArray();
		JsonArray expectedJsonArr = Json.createArrayBuilder()
				.add(ELTON).add(GAGA).add(NELLY).add(JOVA)
				.build();
		
		assertThat(receivedJsonArr, is(expectedJsonArr));
		
	}
	
	@Test
	public void testCrudArtist() {
		
		
		// CREATE
        final String mediaType = MediaType.APPLICATION_JSON;
        Artist him = new Artist("Him", "FI");
        final Entity<Artist> entity = Entity.entity(him, mediaType);
        Response response = artistsRoot.request().post(entity, Response.class);
        assertThat(response.getStatus(), is(200)); // !! should be 201 !!!
        JsonObject jsonReceived = Json.createReader(new StringReader(response.readEntity(String.class))).readObject();
        assertTrue(jsonReceived.containsKey("id"));
        JsonValue idVal = jsonReceived.get("id");
        assertTrue(idVal.getValueType().equals(ValueType.NUMBER));
        long id = ((JsonNumber)idVal).longValue();
        assertTrue( id > 0);
        
        // READ
        jsonReceived = readArtist(id);
		JsonObject jsonExpected = Json.createObjectBuilder()
				.add("id", id).add("name", "Him").add("origin", "FI")
				.add("links", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("rel", "self")
								.add("href", String.format("http://localhost:8080/MusicShelf-JEE7-RestApi/api/artists/%d", id))))
				.build();
		assertThat(jsonReceived, is(jsonExpected));
		
		// UPDATE
		Artist updatedHim = new Artist("HIM", "FI");
        final Entity<Artist> updatedEntity = Entity.entity(updatedHim, mediaType);
        response = artistsRoot.path(String.valueOf(id)).request().put(updatedEntity, Response.class);
        assertThat(response.getStatus(), is(204)); // OK, but no content
        jsonReceived = readArtist(id);
		jsonExpected = Json.createObjectBuilder()
				.add("id", id).add("name", "HIM").add("origin", "FI")
				.add("links", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("rel", "self")
								.add("href", String.format("http://localhost:8080/MusicShelf-JEE7-RestApi/api/artists/%d", id))))
				.build();
		assertThat(jsonReceived, is(jsonExpected));        
        
        // DELETE
        response = artistsRoot.path(String.valueOf(id)).request().delete();
        assertThat(response.getStatus(), is(204)); // OK, but no content

        // READ non existent
        response = artistsRoot.path(String.valueOf(id)).request().get();
		assertThat(response.getStatus(), is(404));
        
	}
	
	@Test
	public void testArtistsQuantity() {
		Response response = artistsRoot.path("quantity").request().get();
		assertThat(response.getStatus(), is(200));

		JsonObject jsonReceived = Json.createReader(new StringReader(response.readEntity(String.class))).readObject();
		
		JsonObject jsonExpected = Json.createObjectBuilder()
				.add("numOfArtists", 4)
				.build();
		
		assertThat(jsonReceived, is(jsonExpected));
	}

	private JsonObject readArtist(Long id) {
        Response response = artistsRoot.path(String.valueOf(id)).request().get();
		assertThat(response.getStatus(), is(200));
		return Json.createReader(new StringReader(response.readEntity(String.class))).readObject();
	}
}
