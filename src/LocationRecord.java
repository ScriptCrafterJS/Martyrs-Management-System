
public class LocationRecord {
	private String locationName;
	private AVLTreeName namesTree;
	private AVLTreeDate datesTree;
	
	public LocationRecord(String locationName) {
		this.locationName = locationName;
		namesTree = new AVLTreeName();
		datesTree = new AVLTreeDate();
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public AVLTreeName getNamesTree() {
		return namesTree;
	}

	public AVLTreeDate getDatesTree() {
		return datesTree;
	}

}
