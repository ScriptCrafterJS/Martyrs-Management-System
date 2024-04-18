
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SummaryTable {

	private SimpleStringProperty cityName;
	private SimpleIntegerProperty numberOfMartyrs;
	private SimpleIntegerProperty heightAVL1;
	private SimpleIntegerProperty heightAVL2;
	private String maxDeathsDate;
	
	
	public String getCityName() {
		return cityName.get();
	}
	public void setCityName(String cityName) {
		this.cityName = new SimpleStringProperty(cityName);
	}
	public int getNumberOfMartyrs() {
		return numberOfMartyrs.get();
	}
	public void setNumberOfMartyrs(int numberOfMartyrs) {
		this.numberOfMartyrs = new SimpleIntegerProperty(numberOfMartyrs);
	}
	public int getHeightAVL1() {
		return heightAVL1.get();
	}
	public void setHeightAVL1(int heightAVL1) {
		this.heightAVL1 = new SimpleIntegerProperty(heightAVL1);
	}
	public int getHeightAVL2() {
		return heightAVL2.get();
	}
	public void setHeightAVL2(int heightAVL2) {
		this.heightAVL2 = new SimpleIntegerProperty(heightAVL2);
	}
	
	public String getMaxDeathsDate() {
		return maxDeathsDate;
	}
	public void setMaxDeathsDate(String maxDeathsDate) {
		this.maxDeathsDate = maxDeathsDate;
	}

	

}
