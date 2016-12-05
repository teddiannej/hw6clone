/**
 * This class represents a single course review. It represents the relevant metrics and the course reviewed.
 * @author mayamudambi
 *
 */
public class Review {

	private Course course;
	
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
    
    public Review (Course crs, double courseQual, double instQual, double diff, double amLrnd, double workReq, 
    				double readVal, double comm, double instAcc, double stimInst, double TA, double recMaj, double recNonMaj){
    	course = crs;
    	
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
    
	public Course getCourse() {
		return course;
	}
	public double getCourseQuality() {
		return courseQuality;
	}
	public double getInstructorQuality() {
		return instructorQuality;
	}
	public double getDifficulty() {
		return difficulty;
	}
	public double getAmountLearned() {
		return amountLearned;
	}
	public double getWorkRequired() {
		return workRequired;
	}
	public double getReadingsValue() {
		return readingsValue;
	}
	public double getCommunication() {
		return communication;
	}
	public double getInstructorAccess() {
		return instructorAccess;
	}
	public double getStimulateInterest() {
		return stimulateInterest;
	}
	public double getRecommendMajor() {
		return recommendMajor;
	}
	public double getRecommendNonMajor() {
		return recommendNonMajor;
	}
	
    public double getTaQuality() {
		return taQuality;
	}
}
