import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;


public class ReviewParserTest {

	ReviewParser r;
	
	private final double EPSILON = 0.0001;
	
	@Before
	public void setUp() throws FileNotFoundException {
		FileReader f = new FileReader("https_penncoursereview.com_course_CIS-401.html");
		r = new ReviewParser(f.getLines());

	}

	@Test
	public void testGetCourse() {
		
		String coursecode = "";
		for(String s : r.getReviews().keySet()){
			coursecode = r.getReviews().get(s).getCourse().getCourseTitle();
			break;
		}
		
		assertEquals("Senior Project", coursecode);
	}

	@Test
	public void testGetReviews(){
		double instructorQuality = r.getReviews().get("Spring 2014").getInstructorQuality();
		
		assertEquals(1.45, instructorQuality, EPSILON);
	}

}
