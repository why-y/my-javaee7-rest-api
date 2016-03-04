package ch.gry.myjavaee7project1.musicshelf.ejb;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import ch.gry.myjavaee7project1.musicshelf.model.Album;
import ch.gry.myjavaee7project1.musicshelf.model.Artist;
import ch.gry.myjavaee7project1.musicshelf.model.Dummy;
import ch.gry.myjavaee7project1.musicshelf.model.Track;

@Singleton
@Startup
public class DataInitializer {

    private static final Logger logger = Logger.getLogger(DataInitializer.class.getName());

	@Inject
	DummiesService dummiesService;
	
	@Inject
	ArtistsService artistsService;
	
	@Inject
	AlbumsService albumsService;
	
	public DataInitializer() {
		logger.info("DataInitializer constructor");
	}
	
	@PostConstruct
	public void initializeDb() {
		albumsService.create(new Album("Album A-I", "Artist A", LocalDate.of(2000, 4, 23)));
		albumsService.create(new Album("Album A-II", "Artist A", LocalDate.of(2001, 5, 13)));
		albumsService.create(new Album("Album B-I", "Artist B", LocalDate.of(2000, 4, 23)));
		albumsService.create(new Album("Album B-II", "Artist B", LocalDate.of(2001, 5, 13)));
		Album albumA1 = new Album("Album A1", "Artist A", LocalDate.of(2014, 9, 30));
		albumA1.getTracks().addAll(Arrays.asList(
				new Track("Track A1-1", Duration.ofSeconds(200), 1),
				new Track("Track A1-2", Duration.ofSeconds(240), 2),
				new Track("Track A1-3", Duration.ofSeconds(180), 3)
				));
		albumsService.create(albumA1);
		
		artistsService.create(new Artist("Sir Elton John", "UK"));
		artistsService.create(new Artist("Lady Gaga", "US"));
		artistsService.create(new Artist("Nelly Furtado", "CA"));
		artistsService.create(new Artist("Jovanotti", "IT"));
		
		
		dummiesService.create(new Dummy("Dummy A", 1000L));
		dummiesService.create(new Dummy("Dummy B", 1002L));
	}

}
