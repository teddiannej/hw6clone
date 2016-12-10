import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class CourseRecommendationEngine {

	public static void main(String[] args) {	
		
		try {
			
			Scanner scan = new Scanner(System.in);
			
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
		    
		    
			ReviewDataProducer dataProducer = new ReviewDataProducer();

			
			/*System.out.println("Of the following courses offered to CS students, which have you taken?");
			System.out.println("Please indicate the course by entering in its corresponding number. Enter 0 to end entering reviews.");
			
			for(int x = 1; x<=dataProducer.getTotalCourses().size(); x++){
				for(Course c : dataProducer.getTotalCourses().keySet()){
					if(dataProducer.getTotalCourses().get(c) == x){
						System.out.print(dataProducer.getTotalCourses().get(c) + ". ");
						System.out.print(c.getCourseCode() + " ");
						System.out.println(c.getCourseTitle());
						break;
					}
				}
			}
			
			ArrayList<Review> userReviews = new ArrayList<Review>();
			
			int courseChoice = scan.nextInt();
			
			while(courseChoice != 0){
				
				System.out.println("On a scale of 0.0 to 4.0, please indicate the your rating for each attribute of this course.");
				System.out.println("Course quality:");
				courseQuality = scan.nextDouble();
				System.out.println("Instructor quality:");
			    instructorQuality = scan.nextDouble();
				System.out.println("Level of difficulty:");
			    difficulty = scan.nextDouble();
				System.out.println("How much you learned: ");
			    amountLearned = scan.nextDouble();
				System.out.println("Amount of work required: ");
			    workRequired = scan.nextDouble();
				System.out.println("Quality of the course's readings: ");
			    readingsValue = scan.nextDouble();
				System.out.println("Instructor's quality of communication:");
			    communication = scan.nextDouble();
				System.out.println("Instructor's level of availability:");
			    instructorAccess = scan.nextDouble();
				System.out.println("Stimulation of interest: ");
			    stimulateInterest = scan.nextDouble();
				System.out.println("Quality of the TAs:");
				taQuality = scan.nextDouble();
				System.out.println("How likely you would be to recommend the course to Computer Science majors:");
			    recommendMajor = scan.nextDouble();
				System.out.println("How likely you would be to recommend the course to non-Computer Science majors:");
			    recommendNonMajor = scan.nextDouble();
			    
			    for(Course c : dataProducer.getTotalCourses().keySet()){
			    	if(dataProducer.getTotalCourses().get(c) == courseChoice){
			    		Review r = new Review(c,courseQuality, instructorQuality, difficulty, amountLearned, workRequired, 
								readingsValue, communication, instructorAccess, stimulateInterest, taQuality, recommendMajor, recommendNonMajor);
						userReviews.add(r);
						break;
			    	}
			    }
			    
			    courseChoice = scan.nextInt();
			}
			
			dataProducer.addUserPreferences(userReviews);*/
			
			SpringScheduleProducer springScheduler = new SpringScheduleProducer();
			//springScheduler.assignCourseID(dataProducer.getTotalCourses());
			
			(Spring2017Course c : springScheduler.getCourses().keySet()){
				System.out.println(c.getCourseTitle());
			}
			
			
			/*DataModelCreator creator = new DataModelCreator("blank_text.txt", courseQuality, instructorQuality, difficulty, amountLearned, workRequired, 
				readingsValue, communication, instructorAccess, stimulateInterest, taQuality, recommendMajor, recommendNonMajor);
			creator.createDataFile(dataProducer.getTotalCourses(), dataProducer.getData());
			
			Recommender r = new Recommender("blank_text.txt");
			
			System.out.println("How many recommendations would you like? Please enter a number from 1 to 5.");
			int recommendationNumber = scan.nextInt();
			
			r.generateRecommendations(recommendationNumber, springScheduler.getCourses());
			System.out.println(r.getRecommendedCourses().size());
			
			for(Spring2017Course c : r.getRecommendedCourses()){
				System.out.println(c.getCourseCode());
				System.out.println(c.getCourseTitle());
				System.out.println(c.getCredits());
				System.out.println(c.getDescription());
				System.out.println(c.getInstructor());
				for(String m : c.getMeetingTimes()){
					System.out.println(m);
				}
			}
			
			*/

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
