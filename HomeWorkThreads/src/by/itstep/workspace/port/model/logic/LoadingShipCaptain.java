package by.itstep.workspace.port.model.logic;

import java.util.concurrent.TimeUnit;

import by.itstep.workspace.port.model.entity.Port;

public class LoadingShipCaptain extends ShipCaptain {
	private int containerCount = 0;
	private int id;

	public LoadingShipCaptain(Port port, int id) {
		super(port);
		this.id = id;
	}

	@Override
	public void run() {
		try {
			System.out.printf("Загрузочный корабль %d ждет у порта\n", id);
			TimeUnit.SECONDS.sleep(10);
			port.moor();
			System.out.printf("Загрузочный корабль %d заплыл в порт\n", id);
			TimeUnit.SECONDS.sleep(10);
			containerCount = port.getRoom().recieveOffer(containerCount);
			System.out.printf("Загрузочный корабль %d уплывает с %d контейнеров\n"
					, id, containerCount);
			TimeUnit.SECONDS.sleep(10);
			port.unmoor();
			System.out.printf("Загрузочный корабль %d уплыл\n", id);
		} catch (InterruptedException e) {
		}
	}
}
