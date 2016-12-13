import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class creates an object which takes in text from a Penn course review webpage, and parses out the essential data:
 * the course being reviewed, semesters that reviewed said course, as well as key metrics. 
 * @author mayamudambi
 */
public class ReviewParser extends Parser {

	private ArrayList<String> lines;
	private Course course; //Course being reviewed
	private HashMap<String, Review> reviews; //Holds reviews mapped to each semester section

	/**
	 * Constructs a ReviewParser object, which takes in an ArrayList of Strings, and parses out essential data
	 * for a Penn course review.  
	 * @param l text from a Penn Course Review webpage
	 */
	public ReviewParser(ArrayList<String> fileLines){
		lines = fileLines;
		course = parseCourse();
		reviews = parseSemesters();
	}
	
	/**
	 * Parses out the essential data about the course that is being reviewed at the top of the text
	 * @return c the course that is being reviewed
	 */
	private Course parseCourse(){
		
		String code = findDataPoint(lines, ".*Penn Course Review - (\\w+-\\d+)\\s*", 1);
		lines = trimmedList(lines, getLineIndex(), lines.size()); //Discards the text that has already been iterated over

		String title = findDataPoint(lines, ".*class=\"subtitle\">(.*)</p>\\s*", 1);
		lines = trimmedList(lines, getLineIndex(), lines.size());

		String description = findDataPoint(lines, "<p class=\"desc\">(.*)\\s*", 1);	
		lines = trimmedList(lines, getLineIndex(), lines.size());
		
		
		ArrayList<String> times = new ArrayList<String>();
		times.add("No meeting times in Spring 2017");

		Course c = new Course(code, title, description, "N/A", "N/A", times); //Course associated with the review; will be added to each individual review
		return c;
	}
	
	/**
	 * Goes through the remainder of the text, identifying semester sections of the course, 
	 * and storing the corresponding review.
	 * @return ratings a list of reviews, each corresponding to a semester section of the course
	 */
	private HashMap<String, Review> parseSemesters(){
		
		HashMap<String, Review> ratings = new HashMap<String, Review>(); //Will hold each semester's review
		
		String semesterTemplate = "<td class=\"col_semester\">(.* \\d{4})</td>";
		String ratingTemplate = "<td class=\"col_r(\\w+)\">(.*)</td>"; 

		while(!findDataPoint(lines, semesterTemplate, 1).isEmpty()){ //While semester sections are still being found
			
			String semester = findDataPoint(lines, semesterTemplate, 1); //Identifies a semester section
			lines = trimmedList(lines, getLineIndex(), lines.size()); //Discards used text
			
			/* 12 possible rating categories for each section  */
			double instructorQuality = 0.0; 
			double courseQuality = 0.0;
			double difficulty = 0.0;
			double amountLearned = 0.0;
			double workRequired = 0.0;
			double readingsValue = 0.0;
			double communication = 0.0;
			double instructorAccess = 0.0;
			double taQuality = 0.0;
			double stimulateInterest = 0.0;
			double recommendMajor = 0.0;
			double recommendNonMajor = 0.0;
								
			while(!endOfSection(lines.get(0))){ //Loops until end of semester review section
					
				String ratingCategory = findDataPoint(lines, ratingTemplate, 1); //Identify rating category
				String rating = findDataPoint(lines, ratingTemplate, 2); //Identify numerical rating
						
				if(rating.isEmpty()){ //If no data for this particular category
					rating = "0.0";
				}
				
				/*Identifies rating category and stores appropriate metric*/
				if (ratingCategory.equals("InstructorQuality")){
					instructorQuality = Double.parseDouble(rating);
				}
					
				if (ratingCategory.equals("CourseQuality")){	
					courseQuality = Double.parseDouble(rating);
				}

				if (ratingCategory.equals("Difficulty")){
					difficulty = Double.parseDouble(rating);
				}
				
				if (ratingCategory.equals("AmountLearned")){
					amountLearned = Double.parseDouble(rating);
				}
					
				if (ratingCategory.equals("WorkRequired")){
					workRequired = Double.parseDouble(rating);
				}
					
				if (ratingCategory.equals("ReadingsValue")){
					readingsValue = Double.parseDouble(rating);
				}
						
				if (ratingCategory.equals("CommAbility")){
					communication = Double.parseDouble(rating);
				}
					
				if (ratingCategory.equals("InstructorAccess")){
					instructorAccess = Double.parseDouble(rating);
				}
					
				if (ratingCategory.equals("StimulateInterest")){
					stimulateInterest = Double.parseDouble(rating);
				}
					
				if (ratingCategory.equals("TAQuality")){
					taQuality = Double.parseDouble(rating);
				}
					
				if (ratingCategory.equals("RecommendMajor")){
					recommendMajor = Double.parseDouble(rating);
				}
					
				if (ratingCategory.equals("RecommendNonMajor")){
					recommendNonMajor = Double.parseDouble(rating);
				}
					
				lines = trimmedList(lines, getLineIndex()+1, lines.size()); //Discards analyzed data
			}
				
			Review semesterRating = new Review(course, courseQuality, instructorQuality, difficulty, amountLearned, workRequired, readingsValue, 
					communication, instructorAccess, stimulateInterest, taQuality, recommendMajor, recommendNonMajor); //Create new review associated with this semester section
				
			ratings.put(semester, semesterRating); //Map the semester section to the review	
		}
			
		return ratings;
	}
	
	/**
	 * Returns a mapping of all of the reviews mapped to the corresponding semester
	 * @return
	 */
	public HashMap<String, Review> getReviews() {
		return reviews;
	}

	/**
	 * Identifies the end of a review section
	 * @return true if at the end of a review section
	 */
	public boolean endOfSection(String l) {
		
		String nextLine = lines.get(1);
		if(nextLine.equals("</tr>")){
			return true;
		}
		
		else{
			return false;
		}
	}	
	
	public Course getCourse(){
		return course;
	}
}
