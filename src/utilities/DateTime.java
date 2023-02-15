package utilities;

import java.text.SimpleDateFormat;

public class DateTime {
	public static String patternDate = "dd MMMM yyyy";
	public static String patternTime = "HH:mm";
	public static SimpleDateFormat sdfDate = new SimpleDateFormat(patternDate);
	public static SimpleDateFormat sdfTime = new SimpleDateFormat(patternTime);
}