import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract class that provides the functionality to iterate through a given ArrayList of lines, and 
 * identify and parse out specific blocks of data in a String format.
 * The line index of the ArrayList as it is being parsed is kept track of.
 * Other helpful methods to facilitate the parsing of text data are described. 
 * @author mayamudambi
 *
 */
public abstract class Parser {

	private int lineIndex; //Keeps track of the Parser's place in the ArrayLists
	
	/**
	 * Given a String template utilizing the appropriate regular expressions and the desired group, 
	 * returns the appropriate data point in a String format. 
	 * Begins looking at the beginning of the ArrayList parameter and keeps track of the index at which
	 * the data is found.
	 * @param lines ArrayList of text
	 * @param regExTemplate appropriate template of regular expressions
	 * @param desiredGroup group within the regular expressions template that is desired
	 * @return data point in String format
	 */
	protected String findDataPoint(ArrayList<String> lines, String regExTemplate, int desiredGroup){
		
		lineIndex = 0; //Begins at the beginning of the list
		
		String dataPoint = "";
		Pattern p = Pattern.compile(regExTemplate);
			
		boolean dataReached = false;
		 
		 while(!dataReached && lineIndex < lines.size()){ //Iterates until either data is found, or at the end of the list
			 String line = lines.get(lineIndex);
			 Matcher m = p.matcher(line);
			 
			 if(m.find()){ //If data point is found
				 dataPoint = m.group(desiredGroup);
			     dataReached = true; //Stop looping
			 }
			 
			 else{
				 lineIndex++;
			 }
		 }
		 		 		 
		 return dataPoint; //Returns data point; will be an empty string if data point not in list.
	}
	
	
	/**
	 * This allows the subclasses of this class to properly define what constitutes a "section" of raw data,
	 * as this is highly variable between different types of documents. 
	 * @param line the current line being analyzed
	 * @return true if at the end of the defined section; false if not
	 */
	protected abstract boolean endOfSection(String line);
	
	/**
	 * This method takes in a larger parent ArrayList and produces a smaller, trimmed list
	 * between the appropriate start and end indices.
	 * @param l larger parent ArrayList
	 * @param startIndex index to go into trimmed ArrayList (inclusive)
	 * @param endIndex last index to go into trimmed ArrayList (exclusive)
	 * @return trimmedList
	 */
	protected ArrayList<String> trimmedList(ArrayList<String> l, int startIndex, int endIndex){
		ArrayList<String> trimmedList = new ArrayList<String>();
		
		for(int index = startIndex; index < endIndex; index++){
			trimmedList.add(l.get(index));
		}
		
		return trimmedList;
	}	
	
	/**
	 * Returns the current line index of the ArrayList that the parser is on.
	 * @return lineIndex
	 */
	protected int getLineIndex(){
		return lineIndex;
	}
	
	/**
	 * Allows for the line index to be reset or changed after a data point is found.
	 * @param amount
	 */
	protected void setLineIndex(int amount){
		lineIndex = amount;
	}
	
}
