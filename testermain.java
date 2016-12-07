import java.util.ArrayList;
import java.util.HashMap;



public class testermain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		
		try {
			DataProducer d = new DataProducer();
			HashMap<String, ArrayList<Review>> h = new HashMap<String, ArrayList<Review>>();
			
			h = d.getData();
			
			for(String s : h.keySet()){
				System.out.println(s);
				for(Review r : h.get(s)){
					System.out.println(r.getCourse().getCourseTitle());
				}
			}

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
