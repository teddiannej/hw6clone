import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class CourseTest {

	private Course course;
	private String code;
	private String title;
	private String desc;
	private String professor;
	private String credits;
	private ArrayList<String> meetingTimes;
	
	@Before
	public void setUp() {
	
		code = "BIO-101";
		title = "Intro to biology";
		desc = "Course covers basics of cell to conservation biology";
		professor = "Jones M";
		credits = "3 CU"; 
		meetingTimes = new ArrayList<String>();
		
		meetingTimes.add("MWF 8:00-8:50am");
				
		course = new Course(code, title, desc, professor, credits, meetingTimes);
	}

	@Test
	public void testGetInstructor() {
		assertEquals(professor, course.getInstructor());
	}

	@Test
	public void testSetInstructor() {
		course.setInstructor("Kim O");
		assertEquals("Kim O", course.getInstructor());
	}

	@Test
	public void testGetMeetingTimes() {
		
		ArrayList<String> m = course.getMeetingTimes();
		assertEquals(m, course.getMeetingTimes());
	}

	@Test
	public void testSetMeetingTimes() {
		
		ArrayList<String> mT = new ArrayList<String>();
		course.setMeetingTimes(mT);
		assertEquals(mT, course.getMeetingTimes());
	}

	@Test
	public void testGetCredits() {
		assertEquals(credits, course.getCredits());
	}

	@Test
	public void testSetCredits() {
		String cu = "1 to 4 CU";
		course.setCredits(cu);
		assertEquals(cu, course.getCredits());
	}

}
