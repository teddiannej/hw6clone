import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creates a text file that serves as a data model for the Mahout recommender engine.
 * @author mayamudambi
 */
public class DataModelCreator {

	String modelFileName; //Name of the text file to write to
	
	/*Appropriate weights to place on each course attribute when calculating overall rating.*/
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
	
    /**
     * Creates a DataModelCreator object, which takes in the appropriate weights the user wishes to place
     * on each course attribute, and has the ability to convert a data structure associating users with reviews 
     * into a data model text file. 
     * @param modelFile filename to write to 
     * @param courseQual
     * @param instQual
     * @param diff
     * @param amLrnd
     * @param workReq
     * @param readVal
     * @param comm
     * @param instAcc
     * @param stimInst
     * @param TA
     * @param recMaj
     * @param recNonMaj
     */
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
	
	/**
	 * Given a data structure that associates users with reviews, writes each review in a text file
	 * in the proper format for a Mahout recommender engine.
	 * @param semesterPreferences
	 * @throws FileNotFoundException
	 */
	public void createDataFile(HashMap<Course, Integer> courseListing, HashMap<String, ArrayList<Review>> semesterPreferences) throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(modelFileName);
		
		for(String semester : semesterPreferences.keySet()){ //For each user
			for(Review review : semesterPreferences.get(semester)){ //Produces the appropriate line of text for each review
				
				/* The data is structured in the format user, course, overall rating (all integers)*/
				
				long courseID = (long)courseListing.get(review.getCourse()); //numerical course ID
				long userID = Long.parseLong(semester); //numerical userID
								
				String dataLine = userID + "," + courseID + "," + (getReviewRating(review)); 
				writer.println(dataLine);
			}
		}
	    writer.close();
	}
	
	/**
	 * Given the appropriate 'weights' to place on each of a course's twelve possible attributes, calculates an 
	 * overall numerical rating for the review.
	 * @param r a given Review
	 * @return the overall rating for the review
	 */
	private double getReviewRating(Review r){
		
		int ratingCategories = generateSignificantCategories(r); //Determines how many categories this review has data for
		
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
		
		if(ratingCategories == 0){
			rating = 0.0;
		}
		
		return rating;
	}
	
	/**
	 * As not all reviews have data for each category,
	 * this method determines how many rating categories this Review has data for.
	 * This aids in the calculation of the overall rating.
	 * A category with no data is indicated by being 0.0
	 * @param r a given Review
	 * @return the number of rating categories this Review has data for
	 */
	private int generateSignificantCategories(Review r){
		int ratingCategories = 0;
		
		/* If a rating category is not 0.0, indicates that there is data for it.*/
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
