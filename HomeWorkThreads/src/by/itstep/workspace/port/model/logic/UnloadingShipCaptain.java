package by.itstep.workspace.port.model.logic;

import java.util.concurrent.TimeUnit;

import by.itstep.workspace.port.model.entity.Port;

public class UnloadingShipCaptain extends ShipCaptain {
	private int containersCount;
	private int containersForSale;
	private int id;

	public UnloadingShipCaptain(Port port, int id) {
		super(port);
		this.id = id;
		containersCount = 1;
		containersForSale = 1;
	}

	public UnloadingShipCaptain
	(Port port, int id, int containersCount, int containersForSale) {
		super(port);
		this.id = id;
		this.containersCount = containersCount;
		this.containersForSale = containersForSale;
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(3);
			System.out.println(this + " пытается заплыть в порт");
			port.moor();
			
			TimeUnit.SECONDS.sleep(3);
			System.out.println(this + " заплыл в порт");

			int leftContainersCount = containersCount - containersForSale;
			
			int attempt = 0;
			int maxAttempt = 5;
			
			while (containersCount != leftContainersCount && attempt < maxAttempt) {
				TimeUnit.SECONDS.sleep(3);
				System.out.println(this + " капитан зашел в комнату обмена");
				containersCount = port.getRoom().putOffer(containersCount);
				
				if(containersCount != leftContainersCount) {
					TimeUnit.SECONDS.sleep(3);
					System.out.println(this + " обменяться не удалось, капитан на складе");
					containersCount = 
							port.getStorage().addContainersCount(containersForSale, containersCount);
				}
				attempt++;
			}
			port.unmoor();
			System.out.printf("%s выплыл из порта с %d контейнеров\n", this, containersCount);

		} catch (InterruptedException e) {
		}
	}

	@Override
	public String toString() {
		return "unloading ship " + id;
	}
}
