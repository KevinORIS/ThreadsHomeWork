package by.itstep.workspace.port.model.entity;

import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Port {
	private static final Logger LOGGER = LoggerFactory.getLogger(Port.class);
	private Storage storage;
	private TradingRoom room;
	private Semaphore semaphore;

	public Port(int brethCount) {
		semaphore = new Semaphore(brethCount);
		storage = new Storage();
		room = new TradingRoom();
		LOGGER.trace("created");
	}

	public TradingRoom getRoom() {
		return room;
	}
	
	public Storage getStorage() {
		return storage;
	}
	
	public void moor() throws InterruptedException {
		semaphore.acquire();
	}

	public void unmoor() throws InterruptedException {
		semaphore.release();
	}

}
