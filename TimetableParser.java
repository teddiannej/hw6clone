import java.util.ArrayList;

public class TimeTableParser extends Parser {

	private ArrayList<Spring2017Course> courses; //Will represent all of the courses in the timetable
	
	public TimeTableParser(ArrayList<String> l){
		super(l);
		
		courses = parseCourses(); 
	}
	
	private ArrayList<Spring2017Course> parseCourses(){
		
		ArrayList<Spring2017Course> classes = new ArrayList<Spring2017Course>(); 
		
		String codeTitleCredits = "(CI\\w\\s*-\\d{3})\\s+(.*)\\s{4,}?(\\d.*CU)"; //Template for the first desired line, that contains course code, course title, and credits	
		String meetingTimesInstructor = "\\d{3}\\s(\\w{3}\\s+.*)\\s{3}(\\w+.*)"; //Template for second desired line, that contains meeting times and the instructor

		while(!getEndOfFile()){ //Iterates through the entire file's lines
			
			/* Data for each course read in*/
			String code = new String(""); 
			String title = new String("");
			String credits = new String("");
			String instructor = new String("");
			boolean instructorFound = false;
			ArrayList<String> meetingTimes = new ArrayList<String>();
			
			if(getCurrentLine().contains("CIS ") || getCurrentLine().contains("CIT ")){ //If beginning of a course listing
				
				/*Record the course code, course title, and number of credits*/
				code = findDataPoint(codeTitleCredits, 1);
				title = findDataPoint(codeTitleCredits, 2);
				credits = findDataPoint(codeTitleCredits, 3);
				
				setLineIndex(1); //Go down a line
				
				int counter = getSectionLength(); //Number of file lines associated with this course
				int startLineIndex = getLineIndex(); //Marks place in the file the parser is currently at
												
				while(!getEndOfFile()){ //Loops until the end of the file, or the end of the course block				
					
					if(getLineIndex() < (startLineIndex+counter)){ //If within the course block
						
						String meetingTime = findDataPoint(meetingTimesInstructor, 1); //Find the next meeting time
						
						if(getLineIndex() < (startLineIndex+counter)){ //If the next meeting time is within the course block
							meetingTimes.add(meetingTime); //Add to list
							
							if(!instructorFound){ //Records only the first instructor found
								instructor = findDataPoint(meetingTimesInstructor, 2);
								instructorFound = true;
							}
							
							setLineIndex(1);
						}
						
						else{ //If not within the course block, go to the end of the course block
							setLineIndex(-1*(getLineIndex()-(startLineIndex + counter)));
						}
					}
					
					else{ //End of course listing
						Spring2017Course c = new Spring2017Course(code, title, "", instructor, credits, meetingTimes); //Record course	
						classes.add(c);
						break;
					}
				}
			}
			
			setLineIndex(1);
		}
			
		return classes;	
	}
		
	private int getSectionLength(){
		int counter = 0;
		while(!getCurrentLine().isEmpty()){
			setLineIndex(1);
			counter++;
		}
		
		setLineIndex(-1*counter);
		counter = counter - 1;
		
		return counter;
	}
	
	public ArrayList<Spring2017Course> getCourses() {
		return courses;
	}

}
