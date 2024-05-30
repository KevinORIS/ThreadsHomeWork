package by.itstep.workspace.port.model.entity;

import java.util.concurrent.locks.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Storage {
	private static final Logger LOGGER = LoggerFactory.getLogger(Storage.class);
	final static Lock lock = new ReentrantLock();

	private int containersCount;

	public Storage() {
		LOGGER.trace("created");
	}

	public boolean hasContainers(int requestContainersCount) {
		return containersCount - requestContainersCount >= 0;
	}

	public int addContainersCount(int addContainersCount, int shipStorage) {
		try {
			lock.lock();
			containersCount += addContainersCount;
			LOGGER.info("Storage replenished: {}", containersCount);
			return shipStorage - addContainersCount;
		} finally {
			lock.unlock();
		}
	}

	public int takeContainersCount(int requestContainersCount) {
		try {
			lock.lock();
			if (hasContainers(requestContainersCount)) {
			containersCount -= requestContainersCount;
			LOGGER.info("Storage reduced: {}", containersCount);
			return requestContainersCount;
			}
		} finally {
			lock.unlock();
		}
		return 0;
	}
}
