package by.itstep.workspace.port.model.entity;

import java.util.concurrent.locks.*;

public class Storage {
	final static Lock lock = new ReentrantLock();

	private int containersCount;

	public Storage() {
	}

	public boolean hasContainers(int requestContainersCount) {

		return containersCount - requestContainersCount >= 0;
	}

	public int addContainersCount(int addContainersCount, int shipStorage) {
		try {
			lock.lock();
			containersCount += addContainersCount;
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
			return requestContainersCount;
			}
		} finally {
			lock.unlock();
		}
		return 0;
	}
}
