import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;


public class ParserTest {

	private Parser parse;
	
	@Test
	public void testFindDataPointSchedule() throws FileNotFoundException{
		FileReader f = new FileReader("CIS Spring 2017 Schedule.html");
		parse = new Parser(f.getLines());
		String codeTitleCredits = "(CI\\w\\s*-\\d{3})\\s+(.*)\\s{4,}?(\\d.*CU)"; 
		String courseCode = parse.findDataPoint(codeTitleCredits, 1);

		assertEquals("CIS -099", courseCode);
		
	}
	
	@Test
	public void testFindDataPointReview() throws FileNotFoundException{
		FileReader f = new FileReader("https_penncoursereview.com_course_CIS-125.html");
		parse = new Parser(f.getLines());
		String courseTitle = ".*Penn Course Review - (\\w+-\\d+)\\s*"; 
		String courseCode = parse.findDataPoint(courseTitle, 1);

		assertEquals("CIS-125", courseCode);
		
	}

	@Test
	public void testGetCurrentLine() throws FileNotFoundException {
		FileReader f = new FileReader("https_penncoursereview.com_course_CIS-125.html");
		parse = new Parser(f.getLines());
		String courseTitle = ".*class=\"subtitle\">(.*)</p>\\s*"; 
		
		parse.findDataPoint(courseTitle, 1);
		assertEquals("<p class=\"subtitle\">Technology And Policy</p>", parse.getCurrentLine());	
	}
	
	public void testGetLineIndex() throws FileNotFoundException{
		FileReader f = new FileReader("https_penncoursereview.com_course_CIS-190.html");
		parse = new Parser(f.getLines());
		String numberOfSemesters = "<p class=\"subtitle\">(\\d+)\\sSection\\w*</p>"; 
		
		parse.findDataPoint(numberOfSemesters, 1);
		assertEquals(160, parse.getCurrentLine());	
	}
	
	
}
