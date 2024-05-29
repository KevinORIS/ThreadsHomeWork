package by.itstep.workspace.port.controller;

import by.itstep.workspace.port.model.entity.Port;
import by.itstep.workspace.port.model.logic.LoadingShipCaptain;
import by.itstep.workspace.port.model.logic.UnloadingShipCaptain;

public class Main {
	public static void main(String[] args) {
		int brethCount = 3;
		
		Port port = new Port(brethCount);
		
		LoadingShipCaptain ship1 = new LoadingShipCaptain(port, 1);
		UnloadingShipCaptain ship5 = new UnloadingShipCaptain(port, 1);
		//LoadingShipCaptain ship2 = new LoadingShipCaptain(port, 2);
		UnloadingShipCaptain ship6 = new UnloadingShipCaptain(port, 2);
		LoadingShipCaptain ship3 = new LoadingShipCaptain(port, 3);
		//UnloadingShipCaptain ship7 = new UnloadingShipCaptain(port, 3);
		//LoadingShipCaptain ship4 = new LoadingShipCaptain(port, 4);
		//UnloadingShipCaptain ship8 = new UnloadingShipCaptain(port, 4);	
	}
}
