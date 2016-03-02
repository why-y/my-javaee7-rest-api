package ch.gry.myjavaee7project1.musicshelf.ejb;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import ch.gry.myjavaee7project1.musicshelf.model.Dummy;

@Singleton
@Startup
public class DataInitializer {

    private static final Logger logger = Logger.getLogger(DataInitializer.class.getName());

	@Inject
	DummiesService dummiesService;
	
	public DataInitializer() {
		logger.info("DataInitializer constructor");
	}
	
	@PostConstruct
	public void initializeDb() {
		dummiesService.create(new Dummy("Dummy A", 1000L));
		dummiesService.create(new Dummy("Dummy B", 1002L));
	}

}
