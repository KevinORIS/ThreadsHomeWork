package by.itstep.workspace.port.model.logic;

import java.util.concurrent.TimeUnit;

import by.itstep.workspace.port.model.entity.Port;

public class LoadingShipCaptain extends ShipCaptain {
	private int containersCount;
	private int requestContainersCount;
	private int id;

	public LoadingShipCaptain(Port port, int id) {
		super(port);
		this.id = id;
		containersCount = 0;
		requestContainersCount = 1;
	}

	public LoadingShipCaptain
	(Port port, int id, int containersCount, int requestContainersCount) {
		super(port);
		this.id = id;
		this.containersCount = containersCount;
		this.requestContainersCount = requestContainersCount;
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(3);
			System.out.println(this + " пытается заплыть в порт");
			port.moor();
			TimeUnit.SECONDS.sleep(3);
			System.out.println(this + " заплыл в порт");

			int attempt = 0;
			int maxAttempt = 5;
			
			while (containersCount != requestContainersCount && attempt < maxAttempt) {
				TimeUnit.SECONDS.sleep(3);
				System.out.println(this + " капитан заходит в комнату обмена");
				containersCount = port.getRoom().recieveOffer(containersCount);

				if (containersCount != requestContainersCount) {
					TimeUnit.SECONDS.sleep(3);
					System.out.println(this + " обменяться не удалось, капитан на складе");
					containersCount 
						+= port.getStorage().
							takeContainersCount(requestContainersCount - containersCount);
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
		return "loading ship " + id;
	}
}
