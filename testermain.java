import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class testermain {

	/**
	 * @param argsd
	 */
	public static void main(String[] args) {	
		
		try {
			Scanner scan = new Scanner(System.in);
			
			DataProducer d = new DataProducer();
			HashMap<String, ArrayList<Review>> h = new HashMap<String, ArrayList<Review>>();
			
			h = d.getData();
			
			System.out.println("For the following questions, please respond with any number between 0.0 and 5.0 to indicate how important this course attribute is to you.");
			System.out.println("How important is the overall course quality?");
			double courseQuality = scan.nextDouble();
			System.out.println("How important is the overall instructor quality?");
		    double instructorQuality = scan.nextDouble();
			System.out.println("How important is it that the course is challenging?");
		    double difficulty = scan.nextDouble();
			System.out.println("How important is it that you learn a lot from this course?");
		    double amountLearned = scan.nextDouble();
			System.out.println("How important is it that you do a lot of work for this course?");
		    double workRequired = scan.nextDouble();
			System.out.println("How important is it that the course's readings are of a good quality?");
		    double readingsValue = scan.nextDouble();
			System.out.println("How important is the instructor's quality of communication?");
		    double communication = scan.nextDouble();
			System.out.println("How important is the instructor's level of availability?");
		    double instructorAccess = scan.nextDouble();
			System.out.println("How important is it that the course stimluates your interest?");
		    double stimulateInterest = scan.nextDouble();
			System.out.println("How important is the quality of the TAs?");
			double taQuality = scan.nextDouble();
			System.out.println("How important is it that others would recommend the course for Computer Science majors?");
		    double recommendMajor = scan.nextDouble();
			System.out.println("How important is it that others would recommend the course for non-Computer Science majors?");
		    double recommendNonMajor = scan.nextDouble();
			
			DataModelCreator creator = new DataModelCreator("blank_text.txt", courseQuality, instructorQuality, difficulty, amountLearned, workRequired, 
					readingsValue, communication, instructorAccess, stimulateInterest, taQuality, recommendMajor, recommendNonMajor);
			creator.createDataFile(h);

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
