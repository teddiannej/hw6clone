import java.util.ArrayList;

/**
 * This class creates a Course object, which represents essential data about a Penn CIT or CIS course. 
 * @author mayamudambi
 *
 */
public class Course {

	private String courseCode; //Represents the course code
	private String courseTitle; //Official title of the course
	private String description;
	private String instructor; //Instructor of the course 
	private ArrayList<String> meetingTimes; //Times the course meets in Spring2017
	private String credits; //Number of credits the course contains in Spring2017
	
	/**
	 * Constructor for a Course object. Essential data is passed via arguments to the constructor.
	 * @param code the course code
	 * @param title official title of the course
	 * @param desc the course description
	 */
	public Course(String code, String title, String desc, String professor, String cu, ArrayList<String> times){
		courseCode = code;
		courseTitle = title;
		description = desc;
		instructor = professor;
		credits = cu;
		meetingTimes = times;
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

