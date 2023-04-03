package graphGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KColorableGraphGenerator {
	 
	static final String fileNameSuffix = "-Colorable.txt";
	static final String fileNamePrefix = "Graph";
	static final String filePathPrefix2 = System.getProperty("user.dir")+"/Inputs/2-colorable/";
	static final String filePathPrefix3 = System.getProperty("user.dir")+"/Inputs/3-colorable/";
	static final String filePathPrefix4 = System.getProperty("user.dir")+"/Inputs/4-colorable/";
	static int fileNo = 0;
	static final int N = 100;
	
	  public static void main(String[] args) throws IOException {
	       
	        for(int k =2;k<=4;k++)
	        	gen(k);
	        
//		  generateGraph(4, 3);
	 
	    }

	public static void gen(int k) throws IOException {
		List<Integer> vertexCount = Arrays.asList(50,100,200,400,800,1600);
		for(int n : vertexCount ) {
			for(int i = 1; i<=N; i++) {
				System.out.println("File : "+i+" n : "+n+" k : "+k);
				generateGraph(n, k);
			}
				
		}
	}
	    
	    public static void generateGraph(int n, int k) throws IOException {
	        int[][] adjMatrix = new int[n][n];
	        Random rand = new Random();
	        
	        for (int i = 0; i < n; i++) {
	            for (int j = i+1; j < n; j++) {
	                if (rand.nextInt(k) == 0) {
	                    adjMatrix[i][j] = 1;
	                    adjMatrix[j][i] = 1;
	                }
	            }
	        }
	        printGraph(adjMatrix);
	       // createFile(adjMatrix,n,k);
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
	    public static void createFile(int[][] adjMatrix,int n, int k) throws IOException {
	    	
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
	        
	        String fileName = fileNamePrefix + (++fileNo) +"-"+n+"-"+k+fileNameSuffix;
	        String filePath = "";
	        switch(k) {
	        case 2 : filePath = filePathPrefix2+fileName;
	        break;
	        case 3 : filePath = filePathPrefix3+fileName;
	        break;
	        case 4 : filePath = filePathPrefix4+fileName;
	        break;
	        }
	      
	        System.out.println("Writing file : \t" + filePath);
	        FileWriter myWriter = new FileWriter(filePath);
	        myWriter.write(result);
	        myWriter.close();
	        System.out.println("File Write complete : \t"+ filePath);
	    }
}
