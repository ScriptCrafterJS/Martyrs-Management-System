import java.util.Date;

public class DateStack {
	private Date date;
	private Stack martyrsStack;
	
	public DateStack(Date date) {
		this.date = date;
		this.martyrsStack = new Stack();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Stack getMartyrsStack() {
		return martyrsStack;
	}	
}
