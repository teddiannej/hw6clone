import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class CourseTest {

	private Course c;
	
	@Before
	public void setUp(){
		
		c = new Course("BIO-101", "Introduction to Biology", "Introductory biology course");
	}
	
	@Test
	public void testCourseCode(){
		String code = c.getCourseCode();
		assertEquals("BIO-101", code);
	}
	
	@Test
	public void testSetCourseCode(){
		c.setCourseCode("ANTH-101");
		String code = c.getCourseCode();
		assertEquals("ANTH-101", code);
	}
	
	@Test
	public void testCourseTitle(){
		String title = c.getCourseTitle();
		assertEquals("Introduction to Biology", title);
	}
	
	@Test
	public void testSetCourseTitle(){
		c.setCourseTitle("Art History");
		String title = c.getCourseTitle();
		assertEquals("Art History", title);
	}
	
	@Test
	public void testGetDescription(){
		String description = c.getDescription();
		assertEquals("Introductory biology course", description);
	}
	
	@Test
	public void testSetDescription(){
		c.setDescription("Cell biology");
		String description = c.getDescription();
		assertEquals("Cell biology", description);
	}

}
