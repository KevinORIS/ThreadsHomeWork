package by.itstep.workspace.port.model.entity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradingRoom {
	private static final Logger LOGGER = LoggerFactory.getLogger(TradingRoom.class);
	private static final Lock lock = new ReentrantLock();
	private static final Condition condition = lock.newCondition();
	
	private volatile boolean hasOffer;

	public TradingRoom() {
		hasOffer = false;
		LOGGER.trace("created");
	}

	public int putOffer(int container) throws InterruptedException {
		lock.lock();
        try {
            if (hasOffer == false) {
            	LOGGER.debug("someone put offer");
                hasOffer = true;
                condition.await(3, TimeUnit.SECONDS);
                if (hasOffer == true) {
                    hasOffer = false;
                    LOGGER.debug("puted offer failed");
                } else {
                    container--;
                    LOGGER.debug("puted offer succed");
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
	        	LOGGER.debug("someone looking for puted offer");
	            if (hasOffer == true) {
	                hasOffer = false;
	                container++;
	                condition.signal();
	                LOGGER.debug("recieved offer succed");
	            }
	        } finally {
	            lock.unlock();
	        }
	        return container;
	    }
}