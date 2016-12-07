import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Mediates the reading in and parsing of the course review files, a data structure of semesters of Penn students correlated with 
 * their course preferences. 
 * @author mayamudambi
 *
 */
public class DataProducer {

	private HashMap<String, ArrayList<Review>> semesterPreferences;
	
	public DataProducer() throws FileNotFoundException{
		semesterPreferences = new HashMap<String, ArrayList<Review>>();
		readInReviews();
	}
	
	private void readInReviews() throws FileNotFoundException{
		FileReader listingReader;
		
		listingReader = new FileReader("ReviewListing.txt");
		
		for(String fileName : listingReader.getLines()){
			FileReader reviewReader = new FileReader(fileName);
			ReviewParser parser = new ReviewParser(reviewReader.getLines());
			
			HashMap<String, Review> courseReview = parser.getReviews();
			
			for(String semester : courseReview.keySet()){
				if(semesterPreferences.containsKey(semester)){
					ArrayList<Review> existingReviews = semesterPreferences.get(semester);
					
					existingReviews.add(courseReview.get(semester));
					
					semesterPreferences.put(semester, existingReviews);
				}
				
				else{
					ArrayList<Review> newReview = new ArrayList<Review>();
					newReview.add(courseReview.get(semester));
					
					semesterPreferences.put(semester, newReview);
				}
			}
		}
	}
	
	public HashMap<String, ArrayList<Review>> getData(){
		return semesterPreferences;
	}

}
