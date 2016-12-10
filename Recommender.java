import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Recommender {

	private FileDataModel dataModel;	
	private ArrayList<Spring2017Course> recommendations;
	
	public Recommender(String fileName) throws IOException, TasteException{
		dataModel = new FileDataModel(new File(fileName));
		recommendations = new ArrayList<Spring2017Course>();
	}
	
	public void generateRecommendations(int numberOfRecommendations, HashMap<Spring2017Course, Integer> availableCourses) throws TasteException{
		UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.02, similarity, dataModel);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
		
		int index = 0;
		int numberRecommended = 0;
		List<RecommendedItem> allRecommendations = recommender.recommend(0, availableCourses.size());

		while(numberRecommended < numberOfRecommendations && index < allRecommendations.size()){
			
			RecommendedItem recommendation = allRecommendations.get(index);
			long itemID = recommendation.getItemID();
			
			if(availableCourses.containsValue(itemID)){
				
				for(Spring2017Course c : availableCourses.keySet()){
					if(availableCourses.get(c) == itemID){
						recommendations.add(c);
						numberRecommended++;
						break;
					}
				}

			}
			index++;
		}
	}
	
	public ArrayList<Spring2017Course> getRecommendedCourses(){
		return recommendations;
	}
}
