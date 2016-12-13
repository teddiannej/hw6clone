import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Generates a user-based recommendation for courses utilizing Mahout
 * @author mayamudambi
 *
 */
public class Recommender {

	private FileDataModel dataModel;	
	private ArrayList<Course> recommendations;
	
	public Recommender(String fileName) throws IOException, TasteException{
		dataModel = new FileDataModel(new File(fileName));

	}
	
	public void generateRecommendations(HashMap<Course, Integer> availableCourses) throws TasteException{
		UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0, similarity, dataModel);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

		List<RecommendedItem> allRecommendations = recommender.recommend(0, 10);
		ArrayList<Course> rec = new ArrayList<Course>();
		for(RecommendedItem r : allRecommendations){
			int itemID = (int)r.getItemID();
			for(Course c : availableCourses.keySet()){
				if(availableCourses.get(c) == itemID){
					rec.add(c);
					break;
				}
			}
		}
		
		recommendations = rec;	
	}
	
	public ArrayList<Course> getRecommendedCourses(){
		
		return recommendations;
	}
}
