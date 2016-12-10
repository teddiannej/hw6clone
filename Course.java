/**
 * This class creates a Course object, which represents essential data about a Penn CIT or CIS course. 
 * @author mayamudambi
 *
 */
public class Course {

	private String courseCode; //Represents the course code
	private String courseTitle; //Official title of the course
	private String description;
	
	/**
	 * Constructor for a Course object. Essential data is passed via arguments to the constructor.
	 * @param code the course code
	 * @param title official title of the course
	 * @param desc the course description
	 */
	public Course(String code, String title, String desc){

		courseCode = code;
		courseTitle = title;
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
