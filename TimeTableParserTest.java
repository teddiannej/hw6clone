import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;


public class TimeTableParserTest {

	private Parser parse;
	
	
	@Test
	public void testFindDataPointSchedule() throws FileNotFoundException{
		FileReader f = new FileReader("CIS Spring 2017 Schedule.html");
		parse = new TimeTableParser(f.getLines());
		String codeTitleCredits = "(CI\\w\\s*-\\d{3})\\s+(.*)\\s{4,}?(\\d.*CU)"; 
		String courseCode = parse.findDataPoint(f.getLines(), codeTitleCredits, 1);

		assertEquals("CIS -099", courseCode);
		
	}
	
	@Test
	public void testFindDataPointReview() throws FileNotFoundException{
		FileReader f = new FileReader("https_penncoursereview.com_course_CIS-125.html");
		parse = new TimeTableParser(f.getLines());
		String courseTitle = ".*Penn Course Review - (\\w+-\\d+)\\s*"; 
		String courseCode = parse.findDataPoint(f.getLines(), courseTitle, 1);

		assertEquals("CIS-125", courseCode);
		
	}	
}
