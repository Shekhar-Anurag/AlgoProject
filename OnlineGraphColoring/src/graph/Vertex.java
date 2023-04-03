package graph;

import java.util.ArrayList;

public class Vertex {
	private static int Vid =0;
	
	private int id;
	private String name;
	private int color;
	private ArrayList<Edge> incidents;
	//private ArrayList<Vertex> adjacents;
	
	public Vertex(String name){
		this.id = ++Vid;
		this.color = -1;
		this.name = name;
		this.setIncidents(new ArrayList<Edge>());
		//this.setAdjacents(new ArrayList<Vertex>());
	}
	
	
//	@Override
//	public String toString() {
//		return "Vertex id=" + id + ", \nname=" + name + ", \ncolor=" + color + ", \nincidents=" + incidents ;// "\nadjacents="
//				//+ adjacents ;
//	}


//	public ArrayList<Vertex> getAdjacents() {
//		return adjacents;
//	}
//
//	public void setAdjacents(ArrayList<Vertex> adjacents) {
//		this.adjacents = adjacents;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public void setIncidents(ArrayList<Edge> incidents) {
		this.incidents = incidents;
	}

	
	public ArrayList<Edge> getIncidents() {
		return incidents;
	}
	
	
	
	
}
