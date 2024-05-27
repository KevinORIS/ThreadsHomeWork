package by.itstep.workspace.port.model.entity;

import java.util.concurrent.locks.*;

public class Storage {
	final static Lock lock = new ReentrantLock();

	private int containersCount;

	public Storage() {
	}

	public int getContainersCount() {
		try {
			lock.lock();
			return containersCount;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			lock.unlock();
		}
	}

	public void addContainersCount() {
		try {
			lock.lock();
			containersCount++;
		} catch (Exception e) {
			System.err.print(e);
		} finally {
			lock.unlock();
		}
	}
	
	public void takeContainersCount() {
		try {
			lock.lock();
			containersCount--;
		} catch (Exception e) {
			System.err.print(e);
		} finally {
			lock.unlock();
		}
	}
}
