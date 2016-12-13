import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Mediates the reading in and parsing of the course review files, as well as takes in human user course reviews,
 * and produces a data structure of units of Penn students correlated with their course preferences. 
 * @author mayamudambi
 *
 */
public class DataProducer {

	private ArrayList<Course> springCourseList;
	private ArrayList<Course> reviewedCourseList;
	private HashMap<Course, Integer> masterCourseList;
	private HashMap<String, ArrayList<Review>> userPreferences; //Stores all of the reviews associated with a given user number
	
	/**
	 * Constructs a DataProducer object, which mediates the reading in and parsing of PennCourseReview data.
	 * @throws FileNotFoundException
	 */
	public DataProducer() throws FileNotFoundException{
		
		reviewedCourseList = new ArrayList<Course>();
		springCourseList = new ArrayList<Course>();
		userPreferences = new HashMap<String, ArrayList<Review>>();
		masterCourseList = new HashMap<Course, Integer> ();
		
		synthesizeData(); //Reads in the course review data from PennCourseReview, as well as the Spring 2017 schedule data
	}
	
	/**
	 * Reads in the HTML files storing the PennCourseReview data, stores the review data in a format that
	 * associates the semester of Penn students' with their given course preferences
	 * Reads in the HTML files storing the Spring 2017 schedule data; creates a master list of courses with all the appropriate 
	 * information
	 * @throws FileNotFoundException
	 */
	private void synthesizeData() throws FileNotFoundException{
		
		FileReader listingReader;
		listingReader = new FileReader("FileListing.txt"); //Reads in all of the file names of the appropriate HTML files
		
		for(String fileName : listingReader.getLines()){ //Reads in each review file
			
			FileReader HTMLReader = new FileReader(fileName); 

			if(fileName.startsWith("https_penncoursereview.com")){
				readReviewFile(HTMLReader.getLines()); //Passes the text data for this review to be appropriately parsed
			
			}
			
			if(fileName.startsWith("CI")){
				readScheduleFile(HTMLReader.getLines());
			}
		}
		
		generateMasterCourseList(reviewedCourseList, springCourseList);
		updateReviewData();
	}
	
	/**
	 * Given the text data associated with one PennCourseReview HTML file, parses out the appropriate review data, and 
	 * stores each Review associated with the given semester of Penn students in the userPreferences data structure.
	 * @param fileLines
	 */
	private void readReviewFile(ArrayList<String> fileLines){
		
		ReviewParser parser = new ReviewParser(fileLines); //Parses the appropriate data from each course file
		HashMap<String, Review> courseReview = parser.getReviews(); //Stores each semester's review for this given course associated with the semester name
		reviewedCourseList.add(parser.getCourse());
		
		for(String semester : courseReview.keySet()){ //Iterates through the course's reviews
									
			if(userPreferences.containsKey(semester)){ //If this semester has previously been recorded
				
				ArrayList<Review> existingReviews = userPreferences.get(semester); //Add in this semester's review for this course
				existingReviews.add(courseReview.get(semester));
				userPreferences.put(semester, existingReviews);
			}
			
			else{ //If a new semester
				ArrayList<Review> newReview = new ArrayList<Review>(); 
				newReview.add(courseReview.get(semester));
				userPreferences.put(semester, newReview); //Make a new listing in the data structure for this semester
			}
		}
	}
	
	/**
	 * Given the text data associated with one schedule file, parses out the appropriate review data, and 
	 * stores each course to the list of courses offered in the spring.
	 * @param fileLines
	 */
	private void readScheduleFile(ArrayList<String> fileLines){
		
		TimeTableParser parser = new TimeTableParser(fileLines); //Parses the file for course data
		
		for(Course c: parser.getCourses()){ //Passes the text data for this schedule to be appropriately parsed
			springCourseList.add(c);
		}
	}
	
	/**
	 * Compares the list of courses gleaned from the reviews, and the list of courses offered in the Spring, and 
	 * merges the parsed data
	 * @param reviewedCourseList
	 * @param springCourseList
	 */
	private void generateMasterCourseList(ArrayList<Course> reviewedCourseList, ArrayList<Course> springCourseList){
		
		int courseID = 1;
		
		for(Course reviewedCourse : reviewedCourseList){
			for(Course springCourse : springCourseList){
				if(reviewedCourse.getCourseCode().equals(springCourse.getCourseCode())){
					reviewedCourse.setMeetingTimes(springCourse.getMeetingTimes());
					reviewedCourse.setCredits(springCourse.getCredits());
					reviewedCourse.setInstructor(springCourse.getInstructor());
					break;
				}
			}
			
			masterCourseList.put(reviewedCourse, courseID);
			courseID++;
		}
		
		reviewedCourseList.clear();
		springCourseList.clear();
	}
	
	/**
	 * Adds in the updated courses to the reviews 
	 */
	private void updateReviewData(){
		
		for(String semester : userPreferences.keySet()){
			ArrayList<Review> semestersReviews = userPreferences.get(semester);
			
			for(Review r : semestersReviews){
				Course reviewedCourse = r.getCourse();
				for(Course c : masterCourseList.keySet()){
					if(reviewedCourse.getCourseCode().equals(c.getCourseCode())){
						r.setCourse(c);
						break;
					}
				}
			}
		}
	}
	
	/**
	 *Given a list of a user's reviews, adds to the overall data structure. 
	 * @param userReviews
	 */
	public void addUserPreferences(ArrayList<Review> userReviews){
		userPreferences.put("Human User", userReviews); //Add in the human user's course reviews to the data structure
												//0 has not been used yet; course review data started at 1
	}
	
	/**
	 * Returns the review data structure, which associates each user (named in a String format) 
	 * with their appropriate course reviews.
	 * @return userPreferences
	 */
	public HashMap<String, ArrayList<Review>> getData(){
		return userPreferences;
	}
	
	/**
	 * Returns the list of all courses
	 * @return masterCourseList
	 */
	public HashMap<Course, Integer> getMasterCourseList(){
		return masterCourseList;
	}
}
