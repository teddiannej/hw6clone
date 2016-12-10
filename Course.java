import java.util.ArrayList;

/**
 * This class creates a Course object, which represents essential data about a Penn CIT or CIS course. 
 * @author mayamudambi
 *
 */
public class Course {

	private String courseCode; //Represents the course code
	private String credits; //Number of credits the course contains
	private String courseTitle; //Official title of the course
	private String instructor; //Instructor of the course 
	private ArrayList<String> meetingTimes; 
	private String description;
	
	/**
	 * Constructor for a Course object. Essential data is passed via arguments to the constructor.
	 * @param code the course code
	 * @param title official title of the course
	 * @param professor
	 * @param cu number of credits
	 * @param times available course times
	 */
	public Course(String code, String title, String professor, String cu, ArrayList<String> times, String desc){

		courseCode = code;
		courseTitle = title;
		credits = cu;
		instructor = professor;
		meetingTimes = times;
		description = desc;
	}
	
	
	/**
	 * Returns the course code
	 * @return courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}
	
	/**
	 * Mutates the course code
	 * @param courseCode
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Returns the full title of the course
	 * @return courseTitle
	 */
	public String getCourseTitle() {
		return courseTitle;
	}
	
	/**
	 * Mutates the course title
	 * @param courseTitle
	 */
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	
	/**
	 * Returns the course's instructor
	 * @return instructor
	 */
	public String getInstructor() {
		return instructor;
	}
	
	/**
	 * Mutates the course's instructor
	 * @param instructor
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	/**
	 * Returns the number of credits 
	 * @return credits
	 */
	public String getCredits() {
		return credits;
	}
	
	/**
	 * Mutates the number of credits
	 * @param credits
	 */
	public void setCredits(String credits) {
		this.credits = credits;
	}
	
	/**
	 * Returns all of the meeting times
	 * @return meetingTimes
	 */
	public ArrayList<String> getMeetingTimes() {
		return meetingTimes;
	}
	
	/**
	 * Mutates the meeting times 
	 * @param meetingTimes
	 */
	public void setMeetingTimes(ArrayList<String> meetingTimes) {
		this.meetingTimes = meetingTimes;
	}	
	
	/**
	 * Returns the course's description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Mutates the course's description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
