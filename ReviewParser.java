import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class creates an object which takes in text from a Penn course review webpage, and parses out the essential data:
 * the course being reviewed, semesters that reviewed said course, as well as key metrics. 
 * @author mayamudambi
 *
 */
public class ReviewParser {

	private ArrayList<String> lines; //Text to be parsed
	private int lineIndex; 
	private Course course; //Course being reviewed
	private HashMap<String, Review> reviews; //Holds reviews mapped to each semester section
	
	/**
	 * Constructs a ReviewParser object, which takes in an ArrayList of Strings, and parses out essential data
	 * for a Penn course review. 
	 * This object goes through the text from start to finish in one pass, finding essential data points along the way. 
	 * @param l text from a Penn Course Review webpage
	 */
	public ReviewParser(ArrayList<String> l){
		
		lines = l;
		lineIndex = 0; //Keeps track of where the ReviewParser is in the text
				
		course = parseCourse(); //Parses the course's information from the webpage
		reviews = parseSemesters(); //Parses the past semesters of the course and the associated ratings data
	}
	
	/**
	 * Parses out the essential data about the course that is being reviewed at the top of the text
	 * @return c the course that is being reviewed
	 */
	private Course parseCourse(){
				
		String code = findDataPoint(".*Penn Course Review - (\\w+-\\d+)\\s*", 1);
		String title = findDataPoint(".*class=\"subtitle\">(.*)</p>\\s*", 1);
		String description = findDataPoint("<p class=\"desc\">(.*)\\s*", 1);	
		
		ArrayList<String> times = new ArrayList<String>();
		
		Course c = new Course(code, title, "", "", times, description);
		
		return c;
	}
	
	/**
	 * Goes through the remainder of the text, identifying semester sections of the course, 
	 * and storing the corresponding review..
	 * @return ratings a list of reviews, each corresponding to a semester section of the course
	 */
	private HashMap<String, Review> parseSemesters(){ 
	
		HashMap<String, Review> ratings = new HashMap<String, Review>(); //Will hold each semester's review
		
		String numberOfSemestersTemplate = "<p class=\"subtitle\">(\\d+)\\sSection\\w*</p>";
		String semesterTemplate = "<td class=\"col_semester\">(.* \\d{4})</td>";
		String ratingTemplate = "<td class=\"col_r(\\w+)\">(.*)</td>"; //\\d.\\d{2}
			
			int numberOfSemesters = Integer.parseInt(findDataPoint(numberOfSemestersTemplate, 1));
			lineIndex++;
			
			for(int i = 0; i < numberOfSemesters; i++){
			
				String semester = findDataPoint(semesterTemplate, 1); //Identifies a semester section
				
				double instructorQuality = 0.0; //12 possible rating categories for each section 
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
							
				for(int x = 0; x < 12; x++){ //12 possible categories; some semester sections do not select all
					String ratingCategory = findDataPoint(ratingTemplate, 1); //Identify rating category
					String rating = findDataPoint(ratingTemplate, 2);
					
					if(rating.isEmpty()){
						rating = "0.0";
					}
					//Identifies rating category and stores appropriate metric
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
						x = 12; //End of rating categories; looping should end
					}
				
					lineIndex++; //Move down the text to find the next category
				}
			
			Review semesterRating = new Review(course, courseQuality, instructorQuality, difficulty, amountLearned, workRequired, readingsValue, 
											communication, instructorAccess, stimulateInterest, taQuality, recommendMajor, 
											recommendNonMajor); //Create new review associated with this semester section
			ratings.put(semester, semesterRating); //Map the semester section to the review		}
			}
		
		
		return ratings;
	}
	
	/**
	 * This private method takes in a regular expressions template, and the desired "group", in said template, and returns
	 * the appropriate data point in a String format.
	 * It advances the lineIndex further down the text. 
	 * @param template
	 * @param desiredGroup
	 * @return dataPoint
	 */
	private String findDataPoint(String template, int desiredGroup){
		
		String dataPoint = "";
		Pattern p = Pattern.compile(template);
			
		boolean dataReached = false;
		 
		 while(!dataReached && lineIndex < lines.size()){
			 String line = lines.get(lineIndex);
			 Matcher m = p.matcher(line);
			 
			 if(m.find()){
				 dataPoint = m.group(desiredGroup);
					 dataReached = true;
				 
			 }
			 
			 else{
				 lineIndex++;
			 }
		 }
		 
		 return dataPoint;
	}
	
	/**
	 * Returns the list of reviews associated with this webpage
	 * @return reviews
	 */
	public HashMap<String, Review> getReviews(){
		return reviews;
	}
	

	/**
	 * Returns the course associated with this webpage.
	 * @return course
	 */
	public Course getCourse(){
		return course;
	}
}
