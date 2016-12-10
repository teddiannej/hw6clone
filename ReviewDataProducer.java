import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Mediates the reading in and parsing of the course review files, as well as takes in human user course reviews,
 * and produces a data structure of units of Penn students correlated with their course preferences. 
 * @author mayamudambi
 *
 */
public class ReviewDataProducer {

	private HashMap<Course, Integer> totalCourses; //Lists all courses reviewed with an associated ID number
	private HashMap<String, ArrayList<Review>> userPreferences; //Stores all of the reviews associated with a given user number
	
	/**
	 * Constructs a DataProducer object, which mediates the reading in and parsing of PennCourseReview data.
	 * @throws FileNotFoundException
	 */
	public ReviewDataProducer() throws FileNotFoundException{
		
		userPreferences = new HashMap<String, ArrayList<Review>>();
		totalCourses = new HashMap<Course, Integer>();
		
		readInReviews(); //Reads in the course review data from PennCourseReview
		formatReviewData(); //Formats the course review data properly to be placed into a DataModel
		generateCourseList();
	}
	
	/**
	 * Reads in the HTML files storing the PennCourseReview data, stores the review data in a format that
	 * associates the semester of Penn students' with their given course preferences
	 * @throws FileNotFoundException
	 */
	private void readInReviews() throws FileNotFoundException{
		FileReader listingReader;
		listingReader = new FileReader("ReviewListing.txt"); //Reads in all of the file names of the review HTML files
		
		for(String fileName : listingReader.getLines()){ //Reads in each review file
			FileReader reviewReader = new FileReader(fileName); 
			readReviewFile(reviewReader.getLines()); //Passes the text data for this review to be appropriately parsed
		}
	}
	
	/**
	 * Given the text data associated with one PennCourseReview HTML file, parses out the appropriate review data, and 
	 * stores each Review associated with the given semester of Penn students in the userPreferences data structure.
	 * @param fileLines
	 */
	private void readReviewFile(ArrayList<String> fileLines){
		
		ReviewParser parser = new ReviewParser(fileLines); //Parses the appropriate data from each course file
		HashMap<String, Review> courseReview = parser.getReviews(); //Stores each semester's review for this given course associated with the semester name
		
		
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
	 * Formats the data structure to have every user assigned an ID number.
	 * This is necessary to create the proper type of data model.
	 */
	private void formatReviewData(){
		
		int userIDNumber = 1;
		
		HashMap<String, ArrayList<Review>> formattedUserPreferences = new HashMap<String, ArrayList<Review>>();
		
		for(String semester : userPreferences.keySet()){
			ArrayList<Review> semestersReviews = userPreferences.get(semester);
			formattedUserPreferences.put("" + userIDNumber, semestersReviews);
			userIDNumber++;
		}
		
		userPreferences.clear();
		userPreferences = formattedUserPreferences;
	}
	
	/**
	 * Given a list of all of the reviews associated with a user, generates a list of all of the courses reviewed, 
	 * each with an associated ID number.
	 */
	private void generateCourseList(){
		
		int courseNumber = 1;
		for(String userID : userPreferences.keySet()){
			
			for(Review r : userPreferences.get(userID)){
				Course c = r.getCourse();
				if(!totalCourses.containsKey(c)){
					totalCourses.put(c, courseNumber);
					courseNumber++;
				}
			}
		}
	}
	
	/**
	 *Given a list of a user's reviews, adds to the overall data structure. 
	 * @param userReviews
	 */
	public void addUserPreferences(ArrayList<Review> userReviews){
		userPreferences.put(0 + "", userReviews); //Add in the human user's course reviews to the data structure
												//0 has not been used yet; course review data started at 1
	}
	
	/**
	 * Returns a list of all of the reviewed courses, with an associated ID number.
	 * @return totalCourses
	 */
	public HashMap<Course, Integer> getTotalCourses(){
		return totalCourses;
	}
	
	/**
	 * Returns the review data structure, which associates each user (named in a String format) 
	 * with their appropriate course reviews.
	 * @return userPreferences
	 */
	public HashMap<String, ArrayList<Review>> getData(){
		return userPreferences;
	}
}
