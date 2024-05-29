package by.itstep.workspace.port.model.entity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TradingRoom {
	private static final Lock lock = new ReentrantLock();
	private static final Condition condition = lock.newCondition();
	
	private volatile boolean hasOffer;

	public TradingRoom() {
		hasOffer = false;
	}

	public int putOffer(int container) throws InterruptedException {
		lock.lock();
        try {
            if (hasOffer == false) {
                hasOffer = true;
                condition.await(3, TimeUnit.SECONDS);
                if (hasOffer == true) {
                    hasOffer = false;
                } else {
                    container--;
                }
            }
        } finally {
            lock.unlock();
        }
        return container;
    }

	public int recieveOffer(int container) {
		 lock.lock();
	        try {
	            if (hasOffer == true) {
	                hasOffer = false;
	                container++;
	                condition.signal();
	            }
	        } finally {
	            lock.unlock();
	        }
	        return container;
	    }
}