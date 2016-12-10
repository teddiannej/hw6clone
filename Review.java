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
    
    /**
     * Constructs a review object, which corresponds to a single Penn CS course.
     * @param crs Course
     * @param courseQual course quality metric
     * @param instQual instructor quality metric
     * @param diff difficulty metric
     * @param amLrnd amount learned metric
     * @param workReq work required metric
     * @param readVal readings value metric
     * @param comm communication metric
     * @param instAcc instructor access metric
     * @param stimInst stimulates interest metric
     * @param TA TA quality metric
     * @param recMaj recommend for CS majors metric
     * @param recNonMaj recommend for non-CS majors metric
     */
    public Review (Course crs, double courseQual, double instQual, double diff, double amLrnd, double workReq, 
    				double readVal, double comm, double instAcc, double stimInst, double TA, double recMaj, double recNonMaj){
    	
    	course = crs; //CS course associated with the review
    	
    	/* 12 possible review metrics; if no data for a certain metric, initialized to 0.0*/
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
     * Returns the course associated with this review
     * @return course
     */
	public Course getCourse() {
		return course;
	}
	/**
	 * Returns the course quality metric
	 * @return courseQuality
	 */
	public double getCourseQuality() {
		return courseQuality;
	}
	
	/**
	 * Returns instructor quality metric
	 * @return instructorQuality
	 */
	public double getInstructorQuality() {
		return instructorQuality;
	}
	
	/**
	 * Returns difficulty metric
	 * @return difficulty
	 */
	public double getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Returns amount learned metric
	 * @return amountLearned
	 */
	public double getAmountLearned() {
		return amountLearned;
	}
	
	/**
	 * Returns the work required metric
	 * @return workRequired
	 */
	public double getWorkRequired() {
		return workRequired;
	}
	
	/**
	 * Returns the readings value metric
	 * @return readingsValue
	 */
	public double getReadingsValue() {
		return readingsValue;
	}
	
	/**
	 * Returns the communications metric
	 * @return communication
	 */
	public double getCommunication() {
		return communication;
	}
	
	/**
	 * Returns the instructor access metric
	 * @return instructorAccess
	 */
	public double getInstructorAccess() {
		return instructorAccess;
	}
	
	/**
	 * Returns the stimulates interest metric
	 * @return stimulateInterest
	 */
	public double getStimulateInterest() {
		return stimulateInterest;
	}
	
	/**
	 * Returns the metric for recommending to CS majors
	 * @return recommendMajor
	 */
	public double getRecommendMajor() {
		return recommendMajor;
	}
	
	/**
	 * Returns the metric for recommending to non-CS majors
	 * @return recommendNonMajor
	 */
	public double getRecommendNonMajor() {
		return recommendNonMajor;
	}
	
	
	/**
	 * Recommends the metric for TA quality
	 * @return taQuality
	 */
    public double getTaQuality() {
		return taQuality;
	}
}
