import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads in the HTML source of a file that is specified and produces a list of all of its lines.
 * @author mayamudambi
 *
 */
public class FileReader {
	
	private String fileName;
	private ArrayList<String> lines;

	/**
	 * Creates a URLReader object which has the ability to read in the HTML source of a webpage.
	 * @param URL the the URL to read in 
	 * @throws IOException 
	 */
	public FileReader(String file) throws FileNotFoundException{
		fileName = file;
		
		lines = new ArrayList<String>();
		
		readFile(fileName);
	}

	/**
	 * Reads in a file
	 * @param file
	 * @throws IOException
	 */
	private void readFile (String file) throws FileNotFoundException{
        
		File f = new File (file);
		
		//Read all of the text
		Scanner scan = new Scanner(f);
		
		String line = new String("");

	    //Read through file one line at a time
	    while (scan.hasNextLine()) 
	    {
	    	line = scan.nextLine();
	    	line = line.trim();
	    	lines.add(line);
	   
	    }
	    
	    scan.close();
	}
	
	/**
	 * The accessor method for the list of lines 
	 * @return lines
	 */
	public ArrayList<String> getLines(){
		return lines;
	}
}
