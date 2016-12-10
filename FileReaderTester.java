import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;


public class FileReaderTester {

	FileReader f;
	
	@Test
	public void testScheduleFile() throws FileNotFoundException{
		f = new FileReader("CIS Spring 2017 Schedule.html");
		
		String line = f.getLines().get(495);
				
		assertEquals("<view-source:http://www.upenn.edu/registrar/timetable/>\">", line);
	}
	
	@Test
	public void testTextFile() throws FileNotFoundException{
		f = new FileReader("ReviewListing.txt");
		
		assertEquals("https_penncoursereview.com_course_BE-537.html", f.getLines().get(0));
	}
	
	@Test
	public void testReviewFile() throws FileNotFoundException{
		f = new FileReader("https_penncoursereview.com_course_BE-537.html");
		
		assertEquals("", f.getLines().get(324));
	}

}
