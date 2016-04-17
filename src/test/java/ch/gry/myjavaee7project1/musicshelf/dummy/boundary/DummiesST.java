package ch.gry.myjavaee7project1.musicshelf.dummy.boundary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Test;

import ch.gry.myjavaee7project1.musicshelf.MusicShelfST;
import ch.gry.myjavaee7project1.musicshelf.dummy.entity.Dummy;

public class DummiesST extends MusicShelfST {
	
	private WebTarget dummiesRoot = apiRoot.path("dummies");
		
	@Test
	public void testGetAllDummies() {
		Response response = dummiesRoot.request().get();
		assertThat(response.getStatus(), is(200));
		
		JsonArray receivedJsonArr = Json.createReader(new StringReader(response.readEntity(String.class))).readArray();
		
		JsonArray expectedJsonArr = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("value", 1000)
						.add("name", "Dummy A")
						.add("id", 13))
				.add(Json.createObjectBuilder()
						.add("id", 14)
						.add("name", "Dummy B")
						.add("value", 1002))
				.build();
		
		assertThat(receivedJsonArr, is(expectedJsonArr));
	}
	
	@Test
	public void testGetDummy13() {
		Response response = dummiesRoot.path("13").request().get();
		assertThat(response.getStatus(), is(200));

		Dummy receivedDummy = response.readEntity(Dummy.class);
		
		Dummy expectedDummy = new Dummy("Dummy A", 1000l);
		expectedDummy.setId(13l);
		
		assertThat(receivedDummy, is(expectedDummy));
		
	}

	@Test
	public void testGetDummy14() {
		Response response = dummiesRoot.path("14").request().get();
		assertThat(response.getStatus(), is(200));

		Dummy receivedDummy = response.readEntity(Dummy.class);
		
		Dummy expectedDummy = new Dummy("Dummy B", 1002l);
		expectedDummy.setId(14l);
		
		assertThat(receivedDummy, is(expectedDummy));
		
	}

}
