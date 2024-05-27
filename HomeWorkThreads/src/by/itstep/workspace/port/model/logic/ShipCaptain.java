package by.itstep.workspace.port.model.logic;

import by.itstep.workspace.port.model.entity.Port;

public abstract class ShipCaptain implements Runnable {
	protected Thread thread;
	protected Port port;
	
	public ShipCaptain(Port port) {
		thread = new Thread(this);
		thread.start();
		
		this.port = port;
	}
}
