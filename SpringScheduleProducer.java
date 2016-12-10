import java.io.FileNotFoundException;
import java.util.HashMap;


public class SpringScheduleProducer{

	private HashMap<Spring2017Course, Integer> courses;
	
	public SpringScheduleProducer() throws FileNotFoundException{
		
		courses = new HashMap<Spring2017Course, Integer>();
		
		generateSchedule("CIS Spring 2017 Schedule.html");
		generateSchedule("CIT Spring 2017 Schedule.html");
	}
	
	private void generateSchedule(String fileName) throws FileNotFoundException{
		FileReader reader = new FileReader(fileName);
		TimeTableParser parser = new TimeTableParser(reader.getLines());
		
		for(Spring2017Course course : parser.getCourses()){
			courses.put(course, 0);
			
		}
	}
	
	public void assignCourseID(HashMap<Course, Integer> totalCourses){
		for(Spring2017Course springCourse : courses.keySet()){
			
			String courseCode = springCourse.getCourseCode();
			int courseID = 0;
			
			for(Course c : totalCourses.keySet()){
				if(c.getCourseCode().equals(courseCode)){
					courseID = totalCourses.get(c);
					break;
				}
			}
			
			courses.put(springCourse, courseID);
		}
	}
	
	public HashMap<Spring2017Course, Integer> getCourses(){
		return courses;
	}
}
