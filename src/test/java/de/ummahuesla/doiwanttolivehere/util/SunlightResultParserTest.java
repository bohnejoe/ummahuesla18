package de.ummahuesla.doiwanttolivehere.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.el.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class SunlightResultParserTest {

	@Test
	public void testParseValidResultReturnsMaxSunlightHoursPerYear() throws ParseException {
		// prepare
		int expected = 1749;
		String result = "1700 - 1749 h/Jahr";
		
		//execute
		int actual = SunlightResultParser.returnMaxHoursPerYear(result);
		
		// assert
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test(expected = ParseException.class)
	public void testParseInValidResultThrowsParseException () throws ParseException {
		// prepare
		String result = "Blabla";
		
		//execute and assert
		SunlightResultParser.returnMaxHoursPerYear(result);
	}
	
}
