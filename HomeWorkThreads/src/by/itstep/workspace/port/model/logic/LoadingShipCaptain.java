package by.itstep.workspace.port.model.logic;

import java.util.concurrent.TimeUnit;

import org.slf4j.*;

import by.itstep.workspace.port.model.entity.Port;

public class LoadingShipCaptain extends ShipCaptain {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoadingShipCaptain.class);

	private int containersCount;
	private int requestContainersCount;
	private int id;

	public LoadingShipCaptain(Port port, int id) {
		super(port);
		this.id = id;
		containersCount = 0;
		requestContainersCount = 1;
		LOGGER.trace(toString() + " created");
	}

	public LoadingShipCaptain(Port port, int id, int containersCount, int requestContainersCount) {
		super(port);
		this.id = id;
		if (containersCount >= requestContainersCount) {
			LOGGER.warn(toString() + " already has needed amount of containers");
		}
		this.containersCount = containersCount;
		this.requestContainersCount = requestContainersCount;
		LOGGER.trace(toString() + " created");
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(3);
			LOGGER.info(toString() + " trying to sail into port");
			port.moor();
			TimeUnit.SECONDS.sleep(3);
			LOGGER.info(toString() + " sailed into port");

			int attempt = 0;
			int maxAttempt = 5;

			while (containersCount < requestContainersCount && attempt < maxAttempt) {
				TimeUnit.SECONDS.sleep(3);
				LOGGER.info(toString() + " comes in trading room");
				containersCount = port.getRoom().recieveOffer(containersCount);

				if (containersCount < requestContainersCount) {
					TimeUnit.SECONDS.sleep(3);
					LOGGER.info(toString() + " failed to trade, comes in storage");
					containersCount += port.getStorage().takeContainersCount(requestContainersCount - containersCount);
				}
				attempt++;
			}
			port.unmoor();
			LOGGER.info(toString() + " floats out from port with {} containers", containersCount);

		} catch (InterruptedException e) {
		}
	}
	
	public int getContainersCount() {
		return containersCount;
	}
	
	public int getRequestContainersCount() {
		return requestContainersCount;
	}
	
	@Override
	public String toString() {
		return "loading ship captain " + id;
	}
}
