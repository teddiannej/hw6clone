import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


public class DataModelCreator {

	String modelFileName;
	
	public DataModelCreator(String modelFile){
		modelFileName = modelFile;
	}
	
	
	public void createDataFile(HashMap<String, ArrayList<Review>> semesterPreferences) throws FileNotFoundException{
		PrintWriter writer = new PrintWriter("review_data_model.txt");

		int userID = 0;
		
		for(String semester : semesterPreferences.keySet()){
			for(Review review : semesterPreferences.get(semester)){
				String dataLine = userID + "," + review.getCourse().getCourseCode() + "," + 
			}
			
		}
	    writer.close();
	}
	
	private void generateRating(Review r){
		double overallRating;
	}
}
