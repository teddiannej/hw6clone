import java.util.ArrayList;

/**
 * Creates a TimetableParser object that reads in an ArrayList of lines (from a course timetable), parses the essential information,
 * and creates a list of Course objects with the appropriate attributes, which can then be returned.
 * @author mayamudambi
 *
 */
public class TimeTableParser extends Parser {

	private ArrayList<String> lines;
	private ArrayList<Spring2017Course> courses; //Will represent all of the courses in the timetable
	private String nextMeetingStart; //The regular expressions template for a meeting time data point
	
	/**
	 * Constructs a TimetableParser object, which parses in a file and generates appropriate data about courses. 
	 * @param fileLines lines from a course time table file
	 */
	public TimeTableParser(ArrayList<String> fileLines){
		lines = fileLines;
		nextMeetingStart = "\\d{3}\\s(\\w{3}\\s+.*)\\s{3}(\\w+.*)";
		courses = parseCourses(); 
	}
	
	/**
	 * Reads through the file, identifies courses, and creates the appropriate Spring2017Course objects to represent
	 * said courses. 
	 * @param lines 
	 */
	private ArrayList<Spring2017Course> parseCourses(){
		
		ArrayList<Spring2017Course> classes = new ArrayList<Spring2017Course>(); 
		
		String codeTitleCredits = "(CI\\w\\s*-\\d{3})\\s+(.*)\\s{4,}?(\\d.*CU)"; //Template for the first desired line, that contains course code, course title, and credits	
		String meetingTimesInstructor = nextMeetingStart; //Template for second desired line, that contains meeting times and the instructor

		while(!findDataPoint(lines, codeTitleCredits, 1).isEmpty()){ //Keeps looking for courses until the end of the file
							
			/*Record the course code, course title, and number of credits*/
			String code = findDataPoint(lines, codeTitleCredits, 1);
			String title = findDataPoint(lines, codeTitleCredits, 2);
			String credits = findDataPoint(lines, codeTitleCredits, 3);
			
			String line;
			
			/* Additional data for each course read in*/
			String instructor = new String("");
			boolean instructorFound = false;
			ArrayList<String> meetingTimes = new ArrayList<String>();
			
			do {
				line = lines.get(0);
				
				String meetingTime = findDataPoint(lines, meetingTimesInstructor, 1); //Records each meeting time
				meetingTimes.add(meetingTime);
				
				if(!instructorFound){ //Records only the first instructor found
					instructor = findDataPoint(lines, meetingTimesInstructor, 2);
					instructorFound = true;
				}
				
				lines = trimmedList(lines, getLineIndex()+1, lines.size()); //Go down a line
			} while(!endOfSection(line)); //If the at the last meeting time, go to next course
			
			Spring2017Course c = new Spring2017Course(code, title, "", instructor, credits, meetingTimes); //Record course	
			classes.add(c);			
		}
			
		return classes;	
	}
	
	/**
	 * Accessor method for the courses ArrayList.
	 * @return courses
	 */
	public ArrayList<Spring2017Course> getCourses() {
		return courses;
	}

	/**
	 * Identifies the end of a course section
	 * @return true if at the end of a course section
	 */
	protected boolean endOfSection(String line) {			
		
		int counter = 0;
		String blockLine = lines.get(counter);
		while(!blockLine.isEmpty()){ //Counts how many lines until an empty line (end of course block)
			counter++;
			blockLine = lines.get(counter);
		}
		
		findDataPoint(lines, nextMeetingStart, 1);
		int nextMeetingLine = getLineIndex();

		if(nextMeetingLine > counter){ //If the next meeting time is after the end of the course block
			setLineIndex(0); //Reset line index; return true
			return true;
		}
		
		else{
			return false;
		}
	}
}
