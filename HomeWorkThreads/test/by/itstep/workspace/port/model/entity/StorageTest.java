package by.itstep.workspace.port.model.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StorageTest {
	private Storage storage;
	private int storageSize;
	
	@BeforeEach
	void setUp() {
		storageSize = 2;
		storage = new Storage(storageSize);
	}
	
	@Test
	void hasContainersCountTest() {
		int containersCount = 1;
		
		boolean expected = false;
		
		boolean tested = storage.hasContainers(containersCount);
		
		assertEquals(tested, expected);
	}
	
	@Test
	void addContainersCountTest() {
		int shipStorage = 5;
		int addContainersCount = 2;
		
		int expected = 3;
		
		int tested = storage.addContainersCount(addContainersCount, shipStorage);
		
		assertEquals(tested, expected);
	}
	
	@Test
	void addContainersCountOverflowTest() {
		int shipStorage = 3;
		int addContainersCount = 3;
		
		int expected = 3;
		
		int tested = storage.addContainersCount(addContainersCount, shipStorage);
		
		assertEquals(tested, expected);
	}
	
	@Test
	void takeContainersCountTest() {
		int shipStorage = 2;
		int addContainersCount = 2;
		storage.addContainersCount(addContainersCount, shipStorage);
		
		int expected = 2;
		
		int tested = storage.takeContainersCount(addContainersCount);
		
		assertEquals(tested, expected);
	}
	
	@Test
	void takeContainersCountEmptyTest() {
		int shipStorage = 0;
		int containersRequest = 1;
		
		int expected = 0;
		
		int tested = shipStorage + storage.takeContainersCount(containersRequest);
		
		assertEquals(tested, expected);
	}
}
