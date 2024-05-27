package by.itstep.workspace.port.model.logic;

import java.util.concurrent.TimeUnit;

import by.itstep.workspace.port.model.entity.Port;

public class UnloadingShipCaptain extends ShipCaptain {
	private int containerCount = 1;
	private int id;

	public UnloadingShipCaptain(Port port, int id) {
		super(port);
		this.id = id;
	}

	@Override
	public void run() {
		try {
			System.out.printf("Разгрузочный корабль %d ждет у порта\n", id);
			TimeUnit.SECONDS.sleep(5);
			port.moor();
			System.out.printf("Разгрузочный корабль %d заплыл в порт\n", id);
			TimeUnit.SECONDS.sleep(10);
			containerCount = port.getRoom().putOffer(containerCount);
			System.out.printf("Разгрузочный корабль %d уплывает с %d контейнеров\n"
					, id, containerCount);
			TimeUnit.SECONDS.sleep(10);
			port.unmoor();
			System.out.printf("Разгрузочный корабль %d уплыл\n", id);
		} catch (InterruptedException e) {
		}
	}
}
