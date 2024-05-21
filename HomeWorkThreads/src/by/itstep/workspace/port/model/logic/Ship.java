package by.itstep.workspace.port.model.logic;

import by.itstep.workspace.port.model.entity.Port;

public class Ship implements Runnable {
	private boolean running;
	private Port port;
	private Thread thread;
	
	public Ship(Port port) {
		this.port = port;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	
	@Override
	public void run() {
		while(running) {
			port.put();
		}
	}
	
	public void stop() {
		running = false;
	}
}
