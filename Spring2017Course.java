import java.util.ArrayList;

/**
 * This class creates a course object, that corresponds to a Penn Computer Science course held in Spring 2017.
 * @author mayamudambi
 *
 */
public class Spring2017Course extends Course {

	private String instructor; //Instructor of the course 
	private ArrayList<String> meetingTimes; //Times the course meets
	private String credits; //Number of credits the course contains
	
	/**
	 * Constructs a Spring2017Course object
	 * @param code course code
	 * @param title course title
	 * @param desc description
	 * @param professor 
	 * @param cu credits provided
	 * @param times times the class meets
	 */
	public Spring2017Course(String code, String title, String desc, String professor, String cu, ArrayList<String> times){
		super(code, title, desc); //Calls Course constructor
		
		instructor = professor;
		credits = cu;
		meetingTimes = times;
	}
	
	/**
	 * Returns the instructor of the course
	 * @return instructor
	 */
	public String getInstructor() {
		return instructor;
	}

	/**
	 * Mutator method for instructor
	 * @param instructor
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	/**
	 * Returns all of the times that the class meets
	 * @return meetingTImes
	 */
	public ArrayList<String> getMeetingTimes() {
		return meetingTimes;
	}

	/**
	 * Mutator method for meeting times
	 * @param meetingTimes
	 */
	public void setMeetingTimes(ArrayList<String> meetingTimes) {
		this.meetingTimes = meetingTimes;
	}

	/**
	 * Returns the amount of credits that correspond to this course
	 * @return credits
	 */
	public String getCredits() {
		return credits;
	}
	
	/**
	 * Changes the amount of credits 
	 * @param credits
	 */
	public void setCredits(String credits) {
		this.credits = credits;
	}
}
