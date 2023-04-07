package onlineGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import graph.*;

public class OnlineGraph {
	static final String filePathPrefix2 = System.getProperty("user.dir")+"/Inputs/2-colorable/";
	static final String filePathPrefix3 = System.getProperty("user.dir")+"/Inputs/3-colorable/";
	static final String filePathPrefix4 = System.getProperty("user.dir")+"/Inputs/4-colorable/";
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Graph g = readGraph("",2);

	}
	


	static Graph readGraph(String FilePath, int k) throws FileNotFoundException {
		
		Graph g = new Graph(2);
		ArrayList<String> vertexPresent = new ArrayList<String>();
		File myObj = new File(System.getProperty("user.dir")+"/Inputs/file2.txt");
	      Scanner myReader = new Scanner(myObj);
	      Vertex srcVertex = null;
	      while (myReader.hasNextLine()) {
	        String data[] = myReader.nextLine().split(":");
	        String source = data[0].trim();
	        srcVertex = null;
	        if(!g.containsVertex(source)) {
	        	srcVertex = new Vertex(source);
	        	g.addVertex(srcVertex);
	        	vertexPresent.add(source);
	        }
	        else
	        	srcVertex = g.getVertex(source);
	        	
	        String edgesString = data[1].trim();
	        String incidentEdges[] = edgesString.split(" ");
	      
	        for(String destination : incidentEdges) {
	        	Vertex destVertex = null;
	        	if(!vertexPresent.contains(destination))
	        		continue;
	        	if(!g.containsVertex(destination.trim())) {
	        		destVertex = new Vertex(destination.trim());
//	        		System.out.println("Adding Vertex "+ destVertex.getName());
	        		g.addVertex(destVertex);
	        	}
	        	else
	        		destVertex = g.getVertex(destination);
	        		
	        		if(!g.containsEdge(source,destination)) {
	        			Edge edge = new Edge(srcVertex,destVertex);
//	        			System.out.println("Adding Edge "+ edge.getEdgeName());
	        			g.addEdge(edge);
	        		}
	        			
	        	
	        }
	        System.out.println("***************");
	        g.printGraph();
	        g.FirstFitAlgo(srcVertex);
//	        g.CBIP_Algo(srcVertex);
	        g.printVertexColors();

	      }
//	      g.getAdjacencyMatrix();
	      myReader.close();
		
		return null;
		
	}
	
	

}
