import java.util.ArrayList;
import java.util.HashMap;


public class ReviewParser extends Parser {

	private Course course;
	private HashMap<String, Review> reviews; //Holds reviews mapped to each semester section

	public ReviewParser(ArrayList<String> l){
		super(l);
		
		course = parseCourse();
		reviews = parseSemesters();
	}
	
	private Course parseCourse(){
		
		String code = findDataPoint(".*Penn Course Review - (\\w+-\\d+)\\s*", 1);
		String title = findDataPoint(".*class=\"subtitle\">(.*)</p>\\s*", 1);
		String description = findDataPoint("<p class=\"desc\">(.*)\\s*", 1);	
				
		Course c = new Course(code, title, description);
		
		return c;
	}
	
	private HashMap<String, Review> parseSemesters(){
	HashMap<String, Review> ratings = new HashMap<String, Review>(); //Will hold each semester's review
		
		String numberOfSemestersTemplate = "<p class=\"subtitle\">(\\d+)\\sSection\\w*</p>";
		String semesterTemplate = "<td class=\"col_semester\">(.* \\d{4})</td>";
		String ratingTemplate = "<td class=\"col_r(\\w+)\">(.*)</td>"; //\\d.\\d{2}
			
			int numberOfSemesters = Integer.parseInt(findDataPoint(numberOfSemestersTemplate, 1));
			setLineIndex(1);
			
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
				
					setLineIndex(1); //Move down the text to find the next category
				}
			
				Review semesterRating = new Review(course, courseQuality, instructorQuality, difficulty, amountLearned, workRequired, readingsValue, 
												communication, instructorAccess, stimulateInterest, taQuality, recommendMajor, 
												recommendNonMajor); //Create new review associated with this semester section
				ratings.put(semester, semesterRating); //Map the semester section to the review	
			}
		
		
		return ratings;
	}
	
	public Course getCourse() {
		return course;
	}

	public HashMap<String, Review> getReviews() {
		return reviews;
	}

}
