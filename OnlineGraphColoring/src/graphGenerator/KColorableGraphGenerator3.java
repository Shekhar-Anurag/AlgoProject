package graphGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class KColorableGraphGenerator3 {

	static final String fileNameSuffix = "-Colorable.txt";
	static final String fileNamePrefix = "Graph";
	static final String filePathPrefix2 = System.getProperty("user.dir")+"/Inputs/2-colorable/";
	static final String filePathPrefix3 = System.getProperty("user.dir")+"/Inputs/3-colorable/";
	static final String filePathPrefix4 = System.getProperty("user.dir")+"/Inputs/4-colorable/";
	static int fileNo = 0;
	static final int N = 100;
	static final double prob =0.50;
	static int[][] adjMatrix;
	static ArrayList<List<Integer>> graph;

	public static void main(String[] args) throws IOException {

			        for(int k =2;k<=4;k++)
			        	gen(k);
//			        generateGraph(5,2);
//		gen(4);


	}

	public static void gen(int k) throws IOException {
//		List<Integer> vertexCount = Arrays.asList(800);
		List<Integer> vertexCount = Arrays.asList(50,100,200,400,800);
		
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
		graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = i;
        }
        shuffle(v);
        List<Set<Integer>> setlist = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            Set<Integer> newset = new HashSet<>();
            int val = i;
            while (val < n) {
                newset.add(v[val]);
                val += k;
            }
            setlist.add(newset);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (differentSet(i, j, setlist) && Math.random() < prob) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        graph = generate(graph);
        createFile (graph,filePath);
    }

    private static void createFile(List<List<Integer>> result, String filePath) throws IOException {
    	String res ="";
    	for (int i = 0; i< result.size();i++) {
            res+=i+": ";
            for(int j = 0; j< result.get(i).size();j++)
            	res+=(result.get(i).get(j)+ " ");
            res+="\n";
        }

		System.out.println("Writing file : \t" + filePath);
//		System.out.println(result);
		FileWriter myWriter = new FileWriter(filePath);
		myWriter.write(res);
		myWriter.close();
		System.out.println("File Write complete : \t"+ filePath);
	
		
	}

	private static void shuffle(int[] arr) {
		
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    private static boolean differentSet(int i, int j, List<Set<Integer>> setlist) {
        for (Set<Integer> s : setlist) {
            if (s.contains(i) && s.contains(j)) {
                return false;
            }
        }
        return true;
    }
    
    private static ArrayList<List<Integer>> generate(ArrayList<List<Integer>> graph2) {
        List<List<Integer>> newgraph = new ArrayList<>();
        for (List<Integer> s : graph2) {
            newgraph.add(new ArrayList<>(s));
        }
        return (ArrayList<List<Integer>>) newgraph;
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
	public static void createFile(int[][] adjMatrix,String filePath) throws IOException {

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
		FileWriter myWriter = new FileWriter(filePath);
		myWriter.write(result);
		myWriter.close();
		System.out.println("File Write complete : \t"+ filePath);
	}
}
