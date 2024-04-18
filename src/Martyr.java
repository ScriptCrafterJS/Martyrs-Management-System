

import java.text.SimpleDateFormat;
import java.util.Date;

public class Martyr {
	private String name;
	private byte age;
	private String city;
	private Date dateOfDeath;
	private char gender;
	private char maridStatus;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
	
	public Martyr(String name, byte age, String city, Date dataOfDeath, char gender, char maridStatus) {
		this.name = name;
		this.age = age;
		this.city = city;
		this.dateOfDeath = dataOfDeath;
		this.gender = gender;
		this.maridStatus = maridStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public char getMaridStatus() {
		return maridStatus;
	}

	public Date getDataOfDeath() {
		return dateOfDeath;
	}

	public void setDataOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public char getMaridStatud() {
		return maridStatus;
	}
	
	public void setMaridStatus(char maridStatus) {
		this.maridStatus = maridStatus;
	}

	@Override
	public String toString() {
		String formattedDate = dateFormat.format(dateOfDeath);
		return name + ", " + age + ", " + city + "," + formattedDate + ", " + gender + ", " + maridStatus;
	}
	
	
}
