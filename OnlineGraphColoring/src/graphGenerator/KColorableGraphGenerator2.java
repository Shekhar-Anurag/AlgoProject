package graphGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KColorableGraphGenerator2 {

	static final String fileNameSuffix = "-Colorable.txt";
	static final String fileNamePrefix = "Graph";
	static final String filePathPrefix2 = System.getProperty("user.dir")+"/Inputs/2-colorable/";
	static final String filePathPrefix3 = System.getProperty("user.dir")+"/Inputs/3-colorable/";
	static final String filePathPrefix4 = System.getProperty("user.dir")+"/Inputs/4-colorable/";
	static int fileNo = 0;
	static final int N = 100;
	static final double prob =0.50;
	static int[][] adjMatrix;
	static ArrayList<ArrayList<Integer>> groups;

	public static void main(String[] args) throws IOException {

			        for(int k =2;k<=4;k++)
			        	gen(k);
//			        generateGraph(5,2);


	}

	public static void gen(int k) throws IOException {
		List<Integer> vertexCount = Arrays.asList(400);
		//List<Integer> vertexCount = Arrays.asList(50,100,200,400,800,1600);
		

		for(int n : vertexCount ) {
			for(int i = 1; i<=N; i++) {
				System.out.println("File : "+i+" n : "+n+" k : "+k);
				String fileName = fileNamePrefix + (++fileNo) +"-"+n+"-"+k+fileNameSuffix;
				String filePath = "";
				switch(k) {
				case 2 : filePath = filePathPrefix2+String.valueOf(n)+"/"+fileName;
				break;
				case 3 : filePath = filePathPrefix3+String.valueOf(n)+"/"+fileName;
				break;
				case 4 : filePath = filePathPrefix4+String.valueOf(n)+"/"+fileName;
				break;
				}
				generateGraph(n, k,filePath);
			}

		}
		//generateGraph(5,2);
	}

	public static void addEdge(int u, int v) {
		if(u==v)
			return;
		if(adjMatrix[u][v] == 1 && adjMatrix[v][u] == 1)
			return;
		adjMatrix[v][u] = 1;
		adjMatrix[u][v] =1;
	}

	public static void generateGraph(int n, int k, String filePath) throws IOException {
		adjMatrix = new int[n][n];
		groups = new ArrayList<ArrayList<Integer>>();
		Random rand = new Random();
		for(int i =0;i<k;i++)
			groups.add(new ArrayList<Integer>());

		for(int v =0;v<k;v++)
			groups.get(v).add(v);

		for (int vertex = k; vertex <n; vertex++) {
			groups.get(rand.nextInt(k)).add(vertex);
		}
//		printGroup();

		for (int i = 0; i < groups.size(); i++) {
			for (int u : groups.get(i)) {
				for (int m = 0; m < groups.size(); m++) {
					if (groups.get(m).contains(u)) 
						continue;
					for (int v : groups.get(m)) 
						if (Math.random() <= prob) 
							addEdge(u, v);

				}
//				System.out.println("Print2 :");
//				printGraph(adjMatrix);
			}
		}

//				printGraph(adjMatrix);
		
				createFile(adjMatrix,n,k,filePath);
	}
	private static void printGroup()
	{	int gNum = 0;
		for(ArrayList<Integer> al : groups)
		{
			System.out.println("gNum : "+ (++gNum));
			for(int i : al)
				System.out.print(String.valueOf(i)+",");
			System.out.println();
		}
		
	}
	private static int randomIndex(int groupsize) {
		return (int) Math.floor(Math.random() * groupsize);
	}

	public static void printGraph(int[][] adjMatrix) {
		for (int i = 0; i < adjMatrix.length; i++) {
			System.out.print(i + ": ");
			for (int j = 0; j < adjMatrix.length; j++) {
				if (adjMatrix[i][j] == 1) {
					System.out.print(j + " ");
				}
			}
			System.out.println();
		}
	}
	public static void createFile(int[][] adjMatrix,int n, int k, String filePath) throws IOException {

		String result ="";
		for (int i = 0; i < adjMatrix.length; i++) {
			result +=i + ": ";
			for (int j = 0; j < adjMatrix.length; j++) {
				if (adjMatrix[i][j] == 1) {
					result+=j + " ";
				}
			}
			result+="\n";
		}

		System.out.println("Writing file : \t" + filePath);
//		System.out.println(result);
		FileWriter myWriter = new FileWriter(filePath);
		myWriter.write(result);
		myWriter.close();
		System.out.println("File Write complete : \t"+ filePath);
	}
}
