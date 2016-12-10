import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creates a TimetableParser object that reads in an ArrayList of lines (from a course timetable), parses the essential information,
 * and creates a list of Course objects with the appropriate attributes, which can then be returned.
 * @author mayamudambi
 *
 */
public class TimetableParser {

	private ArrayList<Course> courses; //Will represent all of the courses in the timetable
	
	/**
	 * Constructs a TimetableParser object. 
	 * @param fileLines lines from a course time table file
	 */
	public TimetableParser(ArrayList<String> fileLines){
		courses = new ArrayList<Course>(); 
		
		parseCourses(fileLines); 
	}
	
	/**
	 * Reads through the file, identifies courses, and creates the appropriate Course objects to represent
	 * said courses. 
	 * @param lines 
	 */
	private void parseCourses(ArrayList<String> lines){
		
		ArrayList<String> courseBlock = new ArrayList<String>(); //Each instance of this list will represent the raw data for one Course
		int lineIndex = 0; //Keeps track of which line is being read
		boolean beginning = false; //Indicates the beginning of raw data for one course

		while(lineIndex < lines.size()){ //Iterates through the entire file's lines
			String line = lines.get(lineIndex); //Each line
			
			if(line.startsWith("CIS") || line.startsWith("CIT")){ //If beginning of a course listing
				beginning = true;
			}
			
			while(beginning && lineIndex < lines.size()){ //Goes through and adds all of the raw data corresponding to that course to courseBlock
				line = lines.get(lineIndex);
				
				if(line.isEmpty()){ //End of course listing
					beginning = false;
					
					parseCourse(courseBlock); //Cleans raw data to create a Course object for this listing
					
					courseBlock.clear(); //Reset courseBlock to find next course listing
				}
				
				else{
					courseBlock.add(line);
					lineIndex++;
				}
			}
			
			lineIndex++;
		}
	}
	
	/**
	 * Given a block of raw data for a single course, parses it to create a course object
	 * with the appropriate attributes. Adds the Course object to the list of courses.
	 * @param lines
	 */
	private void parseCourse(ArrayList<String> lines){
		
		//Essential data for the course
		String code = new String(""); 
		String title = new String("");
		String credits = new String("");
		String instructor = new String("");
		ArrayList<String> meetingTimes = new ArrayList<String>();
		
		boolean instructorFound = false;
		
		String firstTemplate = "(CI\\w\\s*-\\d{3})\\s+(.*)\\s{4,}?(\\d.*CU)"; //Template for the first desired line, that contains course code, course title, and credits
		Pattern firstPattern = Pattern.compile(firstTemplate);  
		
		String secondTemplate = "\\d{3}\\s(\\w{3}\\s+.*)\\s{3}(\\w+.*)"; //Template for second desired line, that contains meeting times and the instructor
		Pattern secondPattern = Pattern.compile(secondTemplate);
		
		for(int i = 0; i < lines.size(); i++){ //Iterate through all of the raw data for this course
			String line = lines.get(i);

			Matcher firstMatcher = firstPattern.matcher(line);  //Tests to see if the line matches either the first or second template
			Matcher secondMatcher = secondPattern.matcher(line);
			
			if(firstMatcher.find()){ //if matches the first template
			    code = firstMatcher.group(1); //Parse out course code, title, and credits
			    title = firstMatcher.group(2);
			    credits = firstMatcher.group(3);
			}
			   	
			if(secondMatcher.find()){ //If matches second template
				meetingTimes.add((secondMatcher.group(1))); //Adds the meeting time to the meeting times arraylist
				
				if(!instructorFound){ //Records only the main instructor
					instructor = secondMatcher.group(2); //Parse out the instructor
					instructorFound = true; //No other instructor will be recorded for thsi course
				}
			}
		}
		
		Course course = new Course(code, title, instructor, credits, meetingTimes, ""); //Initialize new course object with parsed data
		courses.add(course); //Add the course to the list 
	}
	
	/**
	 * Accessor method for the courses ArrayList.
	 * @return courses
	 */
	public ArrayList<Course> getCourses(){
		return courses;
	}
}
