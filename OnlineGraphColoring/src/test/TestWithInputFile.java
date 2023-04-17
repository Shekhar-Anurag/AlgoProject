package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import graph.Edge;
import graph.Graph;
import graph.Vertex;


public class TestWithInputFile {



	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Please place the file TestFile.txt in the path " + System.getProperty("user.dir")+"/Inputs/");
		System.out.println("Refer file "+System.getProperty("user.dir")+"/Inputs/RandomGraph.txt" + " for sample input.");
		System.out.println("Enter k : ");
		int k = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter 1 for First Fit  or 2 for CBIP ");
		int choice = scan.nextInt();


		System.out.println("Generating graph :");
		String filePath = System.getProperty("user.dir")+"/Inputs/TestFile.txt";
		System.out.println("Graph generated");


		Graph g = new Graph(k);
		ArrayList<String> vertexPresent = new ArrayList<String>();
		File myObj = new File(filePath);
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
			if(data.length==1)
				continue;
			String edgesString = data[1].trim();
			String incidentEdges[] = edgesString.split(" ");

			for(String destination : incidentEdges) {
				Vertex destVertex = null;
				if(!vertexPresent.contains(destination))
					continue;
				if(!g.containsVertex(destination.trim())) {
					destVertex = new Vertex(destination.trim());
					g.addVertex(destVertex);
				}
				else
					destVertex = g.getVertex(destination);

				if(!g.containsEdge(source,destination)) {
					Edge edge = new Edge(srcVertex,destVertex);
					g.addEdge(edge);
				}

			}
//			g.printGraph();
//			
//						System.out.println("***************");

			if(choice == 1)
				g.FirstFitAlgo(srcVertex);
			if(choice == 2)
				g.CBIP_Algo(srcVertex);
			g.setObservedColorable();
//			g.printVertexColors();


		}

		//		g.getAdjacencyMatrix();
	//	g.printGraph();
		System.out.println("***************");
		g.printVertexColors();
		g.calculateCompRatio();
		myReader.close();

		return ;

	}

}
