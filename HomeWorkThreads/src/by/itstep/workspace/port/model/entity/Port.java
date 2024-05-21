package by.itstep.workspace.port.model.entity;

public class Port {
	private int containersNumber;

	public Port() {
	}

	public void put() {
		containersNumber++;
	}

	public int get() {
		containersNumber--;
		return 1;
	}
	
	public int getContainersNumber() {
		return containersNumber;
	}
	
	public void setContainersNumber(int containersNumber) {
		this.containersNumber = containersNumber;
	}
}
