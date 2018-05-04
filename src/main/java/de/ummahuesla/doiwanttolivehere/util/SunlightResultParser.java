package de.ummahuesla.doiwanttolivehere.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.el.parser.ParseException;

public class SunlightResultParser {

	private static final String patternString = "^\\s*(\\d*)\\s*-\\s*(\\d*).*$";
	private static Pattern pattern = Pattern.compile(patternString);
	
	
	public static int returnMaxHoursPerYear(String result) throws ParseException {
        Matcher matcher = pattern.matcher(result);
        
        if(!matcher.matches()) {
        	throw new ParseException();
        }
        
        String max = matcher.group(2);
		return Integer.parseInt(max);
	}
	
}
