package by.itstep.workspace.port.model.entity;

import java.util.concurrent.locks.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Storage {
	private static final Logger LOGGER = LoggerFactory.getLogger(Storage.class);
	final static Lock lock = new ReentrantLock();

	private int containersCount;
	private int storageSize;

	public Storage() {
		storageSize = 10;
		LOGGER.trace("created");
	}

	public Storage(int storageSize) {
		this.storageSize = storageSize;
		LOGGER.trace("created");
	}

	public boolean hasContainers(int requestContainersCount) {
		return containersCount - requestContainersCount >= 0;
	}

	public int addContainersCount(int addContainersCount, int shipStorage) {
		try {
			lock.lock();
			if (containersCount + addContainersCount <= storageSize) {
				containersCount += addContainersCount;
				LOGGER.debug("Storage replenished: {}", containersCount);
				return shipStorage - addContainersCount;
			}
			LOGGER.debug("Cant add {} in storage that has {} out of {} ",
					addContainersCount, containersCount, storageSize);
			return shipStorage;
		} finally {
			lock.unlock();
		}
	}

	public int takeContainersCount(int requestContainersCount) {
		try {
			lock.lock();
			if (hasContainers(requestContainersCount)) {
				containersCount -= requestContainersCount;
				LOGGER.debug("Storage reduced: {}", containersCount);
				return requestContainersCount;
			}
		} finally {
			lock.unlock();
		}
		return 0;
	}
}
