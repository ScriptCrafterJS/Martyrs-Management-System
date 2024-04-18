import java.util.Date;

public class MaxMartyrsInfo {
    private int maxNumber;
    private Date maxDate;

    public MaxMartyrsInfo(int maxNumber, Date maxDate) {
        this.maxNumber = maxNumber;
        this.maxDate = maxDate;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public Date getMaxDate() {
        return maxDate;
    }
}