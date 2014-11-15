package deadlock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JUnitDemo {

	private int testInt;
	private String testString;
	private long testLong;
	
	
	public int getTestInt() {
		return testInt;
	}

	public void setTestInt(int testInt) {
		this.testInt = testInt;
	}

	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}

	public long getTestLong() {
		return testLong;
	}

	public void setTestLong(long testLong) {
		this.testLong = testLong;
	}

	public String DateUtilDemo(Date inputDate){
		
	SimpleDateFormat simpleFormat = new SimpleDateFormat("MM---dd---yyyy");
	
	String outputDate = simpleFormat.format(inputDate);
		
	return 	outputDate;
		
	}
}
