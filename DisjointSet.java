
import java.util.*;

class Node{
	int data;
	int rank;
	Node parent;
}

public class DisjointSet {
	Map<Integer, Node> map = new HashMap<>();
	
	public void makeSet(int data) {	//self pointing node's parent to itself initially
		Node node = new Node();
		node.data = data;
		node.rank = 0;
		node.parent = node;
		map.put(data, node);
	}
	
	public int findSet(int data) {
		return findSet(map.get(data)).data;
	}
	
	public Node findSet(Node node) {
		Node parent = node.parent;
		if(node == parent) {
			return node;
		}
		node.parent = findSet(node.parent);	//path compression
		return node.parent;
	}
	
	public void union(int d1, int d2) {
		Node node1 = findSet(map.get(d1));
		Node node2 = findSet(map.get(d2));
		if(node1.data == node2.data) {
			return;
		}
		if(node1.rank >= node2.rank) {
			if(node1.rank == node2.rank) {
				node1.rank++;
			}
			node2.parent = node1;
		} else {
			node1.parent = node2;
		}
	}
	
	public static void main(String[] args) {
		DisjointSet set = new DisjointSet();
		set.makeSet(1); set.makeSet(2); set.makeSet(3); set.makeSet(4); set.makeSet(5); set.makeSet(6); set.makeSet(7);
		set.union(3, 4);
		set.union(4, 5);
		set.union(5, 6);
//		set.union(6, 7);
//		set.union(1, 7);
		System.out.println(set.findSet(7));
	}

}
