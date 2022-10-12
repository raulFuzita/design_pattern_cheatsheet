package practise.pattern.state.withenum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {

	public static void main(String[] args) {
		Node a = new Node("A");		Node b = new Node("B");
		Node c = new Node("C");		Node d = new Node("D");
		Node e = new Node("E");		Node f = new Node("F");
		Node g = new Node("G");		Node h = new Node("H");
		
		a.addAdjancent(b);		b.addAdjancent(c);
		c.addAdjancent(d);		d.addAdjancent(b);
		a.addAdjancent(e);		e.addAdjancent(f);
		f.addAdjancent(c);		f.addAdjancent(g);
		f.addAdjancent(h);		a.addAdjancent(h);
		
		List<Node> l = new ArrayList<>();
		a.seekDepth(l);
		for (Node node : l) {
			System.out.println(node);
		}
	}
}

class Node {
	private Set<Node> adjancent = new HashSet<>();
	private Color color;
	private String name;
	
	public Node(String name) {
		super();
		this.color = Color.WHITE;
		this.name = name;
	}
	
	public void seekDepth(List<Node> list) {
		this.color.seek(this, list);
	}

	public Set<Node> getAdjancent() {
		return adjancent;
	}

	public void addAdjancent(Node node) {
		this.adjancent.add(node);
	}

	public void setColor(Color color, List<Node> list) {
		this.color = color;
		this.color.takeOn(this, list);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}

enum Color {
	
	WHITE {
		public void seek(Node node, List<Node> list) {
			node.setColor(GRAY, list);
		}
		
	}, GRAY {
		public void takeOn(Node node, List<Node> list) {
			for (Node adj : node.getAdjancent()) {
				adj.seekDepth(list);
			}
			node.setColor(BLACK, list);
		}
		
	}, BLACK {
		public void takeOn(Node node, List<Node> list) {
			list.add(node);
		}
		
	};
	
	
	public void seek(Node node, List<Node> list) {}
	public void takeOn(Node node, List<Node> list) {}
}
