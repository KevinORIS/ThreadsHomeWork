package by.itstep.workspace.port.model.entity;

import java.util.concurrent.Semaphore;

import by.itstep.workspace.port.model.logic.ShipCaptain;

public class Port {
	private Storage storage;
	private TradingRoom room;
	private Semaphore semaphore;

	public Port(int brethCount) {
		semaphore = new Semaphore(brethCount);
		storage = new Storage();
		room = new TradingRoom();
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
