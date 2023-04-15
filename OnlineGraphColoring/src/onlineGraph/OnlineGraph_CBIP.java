package onlineGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import graph.*;

public class OnlineGraph_CBIP {
	static final String filePathPrefix2 = System.getProperty("user.dir")+"/Inputs/2-colorable/";
	static final String filePathPrefix3 = System.getProperty("user.dir")+"/Inputs/3-colorable/";
	static final String filePathPrefix4 = System.getProperty("user.dir")+"/Inputs/4-colorable/";

	static int k =2;
	static List<String> vertices = Arrays.asList("50");
//	static List<String> vertices = Arrays.asList("50","100","200","400","800","1600");

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String inDir ="";
		switch(k) {
		case 2 : inDir = filePathPrefix2;
		break;
		case 3 : inDir = filePathPrefix3;
		break;
		case 4 : inDir = filePathPrefix4;
		break;
		}
		for(String folder : vertices) {
			String inFilePath = "";
			Double compRatioSum = 0.0;
			File directoryPath = new File(inDir+folder+"/");
			String contents[] = directoryPath.list();
			for(int i=0; i<contents.length; i++) {
				inFilePath = inDir+folder+"/"+contents[i];
				System.out.println("Reading graph from file : "+ inFilePath);
				if(inFilePath.contains(".DS"))
					continue;
				Graph g = readGraph(inFilePath,2);
				compRatioSum +=g.getCompRatio();
			}
			Double avgCompRatio = compRatioSum/100;
			System.out.println("Average Competitive Ratio for "+folder+" vertices using CBIP is :"+ avgCompRatio);
			
		}

	}



	static Graph readGraph(String FilePath, int k) throws FileNotFoundException {

		Graph g = new Graph(k);
		ArrayList<String> vertexPresent = new ArrayList<String>(k);
		File myObj = new File(FilePath);
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
				destVertex = g.getVertex(destination);

				Edge edge = new Edge(srcVertex,destVertex);
				g.addEdge(edge);

			}

			g.CBIP_Algo(srcVertex);

			g.setObservedColorable();

			//				        g.printVertexColors();

		}
		g.calculateCompRatio();
//		g.getAdjacencyMatrix();
		myReader.close();

		return g;

	}



}
