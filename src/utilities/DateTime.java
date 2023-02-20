package utilities;

import java.text.SimpleDateFormat;

public class DateTime {
	public static String patternDate = "dd MMMM yyyy";
	public static String patternShortDate = "dd/MM/yyyy";
	public static String patternTime = "HH:mm";
	public static SimpleDateFormat sdfDate = new SimpleDateFormat(patternDate);
	public static SimpleDateFormat sdfShortDate = new SimpleDateFormat(patternShortDate);
	public static SimpleDateFormat sdfTime = new SimpleDateFormat(patternTime);
}