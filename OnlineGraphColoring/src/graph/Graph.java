package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {
	
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	
	private int actualColorable;
	private int observedColorable;
	private HashMap<String,ArrayList<Edge>> adjList;
	
	public Graph(int actualColorable) {
		super();
		this.actualColorable = actualColorable;
		this.vertices = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
		this.adjList = new HashMap<String,ArrayList<Edge>>();
	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	public int getActualColorable() {
		return actualColorable;
	}

	public void setActualColorable(int actualColorable) {
		this.actualColorable = actualColorable;
	}

	public int getObservedColorable() {
		return observedColorable;
	}

	public void setObservedColorable(int observedColorable) {
		this.observedColorable = observedColorable;
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
		e.getStart().getIncidents().add(e);
		e.getEnd().getIncidents().add(e);
		adjList.put(e.getStart().getName(), e.getStart().getIncidents());
		adjList.put(e.getEnd().getName(), e.getEnd().getIncidents());
//		System.out.println("After");
//		this.printGraph();
	}
	
	public void removeEdge(Edge e) {
		edges.remove(edges.indexOf(e));
	}
	
	public void addVertex(Vertex v) {
		vertices.add(v);
		adjList.put(v.getName(), v.getIncidents());
	}
	
	public void removeVertex(Vertex v) {
		Vertex v1 = null;
		for(Edge e : v.getIncidents()) {
			if(e.getStart() == v)
				v1 = e.getEnd();
			if(e.getEnd() == v)
				v1 = e.getStart();
			v1.getIncidents().remove(v1.getIncidents().indexOf(e));
		}
		vertices.remove(vertices.indexOf(v));
	}
	
	public void printGraph() {
		//adjList.entrySet().forEach(System.out::println);
		for (Map.Entry<String,ArrayList<Edge>> V :adjList.entrySet()){
			String s1 = V.getKey();
			String res = s1+": ";
            for(Edge e : V.getValue()) {
            	String s = e.getEdgeName().replaceAll("-", "").replaceAll(s1, "").trim();
            	res+=s+" ";
            }
            res = res.substring(0,res.lastIndexOf(' '));
            System.out.println(res);
        }
	}
	
	public boolean containsVertex(Vertex v) {
		for(Vertex v1 : vertices)
			if(v1.getName() == v.getName())
				return true;
		return false;
	}
	
	public boolean containsVertex(String v) {
		for(Vertex v1 : vertices)
		{
			//System.out.println(v1.getName());
			if(v1.getName().equals(v))
				return true;
			
		}
		return false;
	}
	
	public boolean containsEdge(Edge e) {
		for(Edge e1 : edges)
			if(e1.getEdgeName().equals(e.getStart().getName()+"-"+e.getEnd().getName()) || e1.getEdgeName().equals(e.getEnd().getName()+"-"+e.getStart().getName()))
				return true;
		return false;
	}
	
	public boolean containsEdge(String s, String e) {
		for(Edge e1 : edges)
			if(e1.getEdgeName().equals(s+"-"+e) || e1.getEdgeName().equals(e+"-"+s))
				return true;
		return false;
	}
	
	public Vertex getVertex(String s) {
		for(Vertex v1 : vertices)
		{
			//System.out.println(v1.getName());
			if(v1.getName().equals(s))
				return v1;
			
		}
		return null;
	}
	
	public void printVertexColors() {
		
		System.out.println("Vertex : Color");
		for(Vertex v : vertices)
		{
			//System.out.println(v1.getName());
			System.out.println(v.getName()+ " : "+v.getColor());
			
		}
	}
	
	public int[][] getAdjacencyMatrix(){
		int n = this.getVertices().size();
		int[][] adjMatrix = new int[n][n];
		for(int i =0;i<n;i++) {
			for(int j = 0; j<n; j++ ) {
				 if(this.containsEdge(String.valueOf(i),String.valueOf(j)))
					adjMatrix[i][j] = 1;
				 else
					 adjMatrix[i][j] = 0;
					
			}
		}
		String res ="";
		for(int i =0;i<n;i++) {
			for(int j = 0; j<n; j++ ) {
				 res+=adjMatrix[i][j]+",";
			}
			res = res.substring(0,res.lastIndexOf(','));
			res+="\n";
		}
		System.out.println(res);
		return adjMatrix;
	}
	
	
	
}
