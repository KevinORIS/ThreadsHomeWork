package by.itstep.workspace.port.controller;

import by.itstep.workspace.port.model.entity.Port;
import by.itstep.workspace.port.model.logic.LoadingShipCaptain;
import by.itstep.workspace.port.model.logic.UnloadingShipCaptain;

public class Main {
	public static void main(String[] args) {
		int brethCount = 3;
		int storageSize = 2;
		Port port = new Port(brethCount, storageSize);
		
		UnloadingShipCaptain uship1 = new UnloadingShipCaptain(port, 1, 1, 4);
		UnloadingShipCaptain uship2 = new UnloadingShipCaptain(port, 2);
		UnloadingShipCaptain uship3 = new UnloadingShipCaptain(port, 3);
		UnloadingShipCaptain uship4 = new UnloadingShipCaptain(port, 4);
		
		LoadingShipCaptain ship1 = new LoadingShipCaptain(port, 1, 2, 2);
		LoadingShipCaptain ship2 = new LoadingShipCaptain(port, 2);
		LoadingShipCaptain ship3 = new LoadingShipCaptain(port, 3);
		LoadingShipCaptain ship4 = new LoadingShipCaptain(port, 4);
	
		
		
		
	}
}
