package by.itstep.workspace.port.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import by.itstep.workspace.port.model.logic.LoadingShipCaptain;
import by.itstep.workspace.port.model.logic.UnloadingShipCaptain;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

public class TradingRoomTest {
	private Port port; 
	private int brethCount;
	
	
	@BeforeEach
	void setUp() {
		brethCount = 2;
		port = new Port(brethCount);
	}
	
	@Test
	public void testPutOffer() throws InterruptedException {
		int containersToSell = 1;
		int expectedContainersLeft = 1;

		int result = port.getRoom().putOffer(containersToSell);

		assertEquals(expectedContainersLeft, result);
	}

	@Test
	public void testRecieveOffer() {
		int containersInStorage = 0;
		int expectedContainersAfterTrade = 0;

		int result = port.getRoom().recieveOffer(containersInStorage);

		assertEquals(expectedContainersAfterTrade, result);
	}
	
	@Test
	public void testTradeBetweenPutAndReciecve() throws InterruptedException {
		int containersToPut = 1;
		int containersCount = 1;
		
		int containersInStorage = 0;
		int requestContainersInStorage = 1;
		
		LoadingShipCaptain loadingShipCap = new LoadingShipCaptain(port, 1, containersInStorage, requestContainersInStorage);
		UnloadingShipCaptain unloadingShipCap = new UnloadingShipCaptain(port, 2, containersCount, containersToPut);
		
		loadingShipCap.join();
		unloadingShipCap.join();
		
		int expectedContainersCountAfterTrade = 0;
		int expectedContainersInStorageAfterTrade = 1;
		
		assertEquals(expectedContainersInStorageAfterTrade, loadingShipCap.getContainersCount());
		assertEquals(expectedContainersCountAfterTrade, unloadingShipCap.getContainersCount());
		
	}
}
