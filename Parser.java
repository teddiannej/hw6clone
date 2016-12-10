import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	private ArrayList<String> lines;
	private int lineIndex;
	private String currentLine;
	private boolean endOfFile;
	
	public Parser(ArrayList<String> l){
		lines = l;
		lineIndex = 0;
		endOfFile = false;
		currentLine = "";
	}
	
	public String findDataPoint(String regExTemplate, int desiredGroup){
		
		String dataPoint = "";
		Pattern p = Pattern.compile(regExTemplate);
			
		boolean dataReached = false;
		 
		 while(!dataReached && lineIndex < lines.size()){
			 Matcher m = p.matcher(getCurrentLine());
			 
			 if(m.find()){
				 dataPoint = m.group(desiredGroup);
					 dataReached = true;
			 }
			 
			 else{
				 lineIndex++;
			 }
		 }
		 
		 return dataPoint;
	}

	
	public String getCurrentLine(){
		currentLine = lines.get(lineIndex);
		return currentLine;
	}
	
	public boolean getEndOfFile(){
		if(lineIndex < lines.size()){
			endOfFile = false;
		}
		
		else{
			endOfFile = true;
		}
		
		return endOfFile;
	}
	
	public int getLineIndex(){
		return lineIndex;
	}
	
	public void setLineIndex(int amount){
		lineIndex = lineIndex + amount;
	}
}
