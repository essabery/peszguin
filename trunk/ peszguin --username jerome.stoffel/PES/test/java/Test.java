import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;


public class Test {
	
	public static void main(String[] args) {
		Date date = new Date();
		
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		System.out.println(format.format(date));
	}
}
