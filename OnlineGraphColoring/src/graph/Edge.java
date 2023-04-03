package graph;

public class Edge {
	private static int Eid =0;
	
	private int id;
	private Vertex start;
	private Vertex end;
	private String edgeName;
	
	public Edge(Vertex start, Vertex end){
		this.setId(++Eid);
		this.start = start;
		this.end = end;
		this.edgeName = start.getName()+"-"+end.getName();
	}
	
//	@Override
//	public String toString() {
//		return "Edge [start=" + start + ", end=" + end + "]";
//	}

	public Vertex getStart() {
		return start;
	}
	public void setStart(Vertex start) {
		this.start = start;
	}
	public Vertex getEnd() {
		return end;
	}
	public void setEnd(Vertex end) {
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getEdgeName() {
		return this.edgeName;
	}
	
	public Vertex getOtherVertex(String vName) {
		Vertex o = null;
		if(this.getStart().getName().equals(vName))
			return this.getEnd();
		else if(this.getEnd().getName().equals(vName))
			return this.getStart();
		return o;
	}

}
