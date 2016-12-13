import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class ReviewTest {

	private Review r;
	private Course c;
	
	private final double EPSILON = 0.0001;
	
	@Before
	public void setUp() throws Exception {
		
		ArrayList<String >times = new ArrayList<String>();
		times.add("TR 9:00-10:30am");
		c = new Course("BIO-101", "Introduction to Biology", "Introductory biology course", "J Smith", "2 CU", times);		
		r = new Review(c, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0,0, 0.0, 0.0);
	
	}

	@Test
	public void testGetCourse() {
		ArrayList<String >times = new ArrayList<String>();
		times.add("TR 9:00-10:30am");
		Course sCourse = new Course("BIO-101", "Introduction to Biology", "Introductory biology course", "J Smith", "2 CU", times);
		r = new Review(sCourse, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0,0, 0.0, 0.0);
		
		assertEquals(sCourse, r.getCourse());
	}

	@Test
	public void testGetCourseQuality() {
		assertEquals(0.0, r.getCourseQuality(), EPSILON);
	}

	@Test
	public void testGetInstructorQuality() {
		assertEquals(0.0, r.getInstructorQuality(), EPSILON);
	}

	@Test
	public void testGetDifficulty() {
		assertEquals(0.0, r.getDifficulty(), EPSILON);
	}

	@Test
	public void testGetAmountLearned() {
		assertEquals(0.0, r.getAmountLearned(), EPSILON);
	}

	@Test
	public void testGetWorkRequired() {
		assertEquals(0.0, r.getWorkRequired(), EPSILON);
	}

	@Test
	public void testGetReadingsValue() {
		assertEquals(0.0, r.getReadingsValue(), EPSILON);
	}

	@Test
	public void testGetCommunication() {
		assertEquals(0.0, r.getCommunication(), EPSILON);
	}

	@Test
	public void testGetInstructorAccess() {
		assertEquals(0.0, r.getInstructorAccess(), EPSILON);
	}

	@Test
	public void testGetStimulateInterest() {
		assertEquals(0.0, r.getStimulateInterest(), EPSILON);
	}

	@Test
	public void testGetRecommendMajor() {
		assertEquals(0.0, r.getRecommendMajor(), EPSILON);
	}

	@Test
	public void testGetRecommendNonMajor() {
		assertEquals(0.0, r.getRecommendNonMajor(), EPSILON);
	}

	@Test
	public void testGetTaQuality() {
		assertEquals(0.0, r.getTaQuality(), EPSILON);
	}

}
