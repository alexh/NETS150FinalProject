import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {
	
	Graph g = new Graph();

	public FileParser(String filename){
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			g = new Graph();
		    String line;
		    while ((line = br.readLine()) != null) {
		       String[] parts =line.split(" ");
		       if (parts.length == 3){
		    	   String deck1 = parts[0];
			       String deck2 = parts[1];
			       double winRate = Double.parseDouble(parts[2]);
			       Vertex v1 = new Vertex(deck1, deck1.substring(deck1.indexOf('_') + 1));
			       Vertex v2 = new Vertex(deck2,  deck2.substring(deck2.indexOf('_') + 1));
			       g.addEdge(v1, v2);
		       }
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
