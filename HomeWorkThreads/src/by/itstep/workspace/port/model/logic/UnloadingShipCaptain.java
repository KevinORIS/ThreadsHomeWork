package by.itstep.workspace.port.model.logic;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itstep.workspace.port.model.entity.Port;

public class UnloadingShipCaptain extends ShipCaptain {
	private static final Logger LOGGER = LoggerFactory.getLogger(UnloadingShipCaptain.class);
	private int containersCount;
	private int containersForSale;
	private int id;

	public UnloadingShipCaptain(Port port, int id) {
		super(port);
		this.id = id;
		containersCount = 1;
		containersForSale = 1;
		LOGGER.trace("created");
	}

	public UnloadingShipCaptain
	(Port port, int id, int containersCount, int containersForSale) {
		super(port);
		this.id = id;
		this.containersCount = containersCount;
		this.containersForSale = containersForSale;
		LOGGER.trace("created");
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(3);
			LOGGER.info("trying to sail into port");
			port.moor();
			
			TimeUnit.SECONDS.sleep(3);
			LOGGER.info("sailed into port");

			int leftContainersCount = containersCount - containersForSale;
			
			int attempt = 0;
			int maxAttempt = 5;
			
			while (containersCount != leftContainersCount && attempt < maxAttempt) {
				TimeUnit.SECONDS.sleep(3);
				LOGGER.info("comes in trading room");
				containersCount = port.getRoom().putOffer(containersCount);
				
				if(containersCount != leftContainersCount) {
					TimeUnit.SECONDS.sleep(3);
					LOGGER.info("failed to trade, comes in storage");
					containersCount = 
							port.getStorage().addContainersCount(containersForSale, containersCount);
				}
				attempt++;
			}
			port.unmoor();
			LOGGER.info("floats out from port with {} containers", containersCount);

		} catch (InterruptedException e) {
		}
	}

	@Override
	public String toString() {
		return "unloading ship captain " + id;
	}
}
