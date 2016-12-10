import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creates a text file that serves as a data model for the Mahout recommender engine.
 * @author mayamudambi
 */

public class DataModelCreator {

	String modelFileName;
	
	private double courseQuality;
    private double instructorQuality;
    private double difficulty;
    private double amountLearned;
    private double workRequired;
    private double readingsValue;
    private double communication;
    private double instructorAccess;
    private double stimulateInterest;
	private double taQuality;
    private double recommendMajor;
    private double recommendNonMajor;
	
	public DataModelCreator(String modelFile, double courseQual, double instQual, double diff, double amLrnd, double workReq, 
			double readVal, double comm, double instAcc, double stimInst, double TA, double recMaj, double recNonMaj){
		
		modelFileName = modelFile;
    	courseQuality = courseQual;
    	instructorQuality = instQual;
    	difficulty = diff;
    	amountLearned = amLrnd;
    	workRequired = workReq;
    	readingsValue = readVal;
    	communication = comm;
    	instructorAccess = instAcc;
    	stimulateInterest = stimInst;
    	taQuality = TA;
    	recommendMajor = recMaj;
    	recommendNonMajor = recNonMaj;
	}
	
	
	public void createDataFile(HashMap<String, ArrayList<Review>> semesterPreferences) throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(modelFileName);
		
		for(String semester : semesterPreferences.keySet()){
			for(Review review : semesterPreferences.get(semester)){
				String dataLine = semester + "," + review.getCourse().getCourseCode() + "," + getReviewRating(review); 
				writer.println(dataLine);
			}
		}
	    writer.close();
	}
	
	private double getReviewRating(Review r){
		
		int ratingCategories = generateSignificantCategories(r);
		
		double courseQualityWeight = (double)(courseQuality/5.0)*(r.getCourseQuality());
	    double instructorQualityWeight = (double)(instructorQuality/5.0)*(r.getInstructorQuality());
	    double difficultyWeight = (double)(difficulty/5.0)*(r.getDifficulty());
	    double amountLearnedWeight = (double)(amountLearned/5.0)*(r.getAmountLearned()); 
	    double workRequiredWeight = (double)(workRequired/5.0)*(r.getWorkRequired());
	    double readingsValueWeight = (double)(readingsValue/5.0)*(r.getReadingsValue());
	    double communicationWeight = (double)(communication/5.0)*(r.getCommunication());
	    double instructorAccessWeight = (double)(instructorAccess/5.0)*(r.getInstructorAccess());
	    double stimulateInterestWeight = (double)(stimulateInterest/5.0)*(r.getStimulateInterest());
		double taQualityWeight = (double)(taQuality/5.0)*(r.getTaQuality());
	    double recommendMajorWeight = (double)(recommendMajor/5.0)*(r.getRecommendMajor());
	    double nonRecommendMajorWeight = (double)(recommendNonMajor/5.0)*(r.getRecommendNonMajor());
		
		double rating = (double)(courseQualityWeight + instructorQualityWeight + difficultyWeight + amountLearnedWeight + workRequiredWeight +
				readingsValueWeight + communicationWeight + instructorAccessWeight + stimulateInterestWeight + taQualityWeight +
				recommendMajorWeight + nonRecommendMajorWeight)/ratingCategories;
		
		return rating;
	}
	
	private int generateSignificantCategories(Review r){
		int ratingCategories = 0;
		
		if(r.getCourseQuality() != 0.0){
			ratingCategories++;
		}
		
		if(r.getInstructorQuality() != 0.0){
			ratingCategories++;
		}
		
		if(r.getDifficulty() != 0.0){
			ratingCategories++;
		}
		
		if(r.getAmountLearned() != 0.0){
			ratingCategories++;
		}
		
		if(r.getWorkRequired() != 0.0){
			ratingCategories++;
		}
		
		if(r.getReadingsValue() != 0.0){
			ratingCategories++;
		}
		
		if(r.getCommunication() != 0.0){
			ratingCategories++;
		}
		
		if(r.getInstructorAccess() != 0.0){
			ratingCategories++;
		}
		
		if(r.getStimulateInterest() != 0.0){
			ratingCategories++;
		}
		
		if(r.getTaQuality() != 0.0){
			ratingCategories++;
		}
		
		if(r.getRecommendMajor() != 0.0){
			ratingCategories++;
		}
		
		if(r.getRecommendNonMajor() != 0.0){
			ratingCategories++;
		}	
		
		return ratingCategories;
	}
}
