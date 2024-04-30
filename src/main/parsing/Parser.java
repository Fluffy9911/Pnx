package main.parsing;

import java.util.ArrayList;
import java.util.Iterator;

import main.parsing.tokens.Node;
import main.parsing.tokens.Types;

public class Parser {
public ArrayList<Node> nodes;

public Parser(ArrayList<Node> nodes) {
	super();
	this.nodes = nodes;
}

public void transformDepth() {
	int depth = 0;
	int bd = 0;
	int pd = 0;
	
	
	
	for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
		Node node = (Node) iterator.next();
		
if(node.getTtype().equals(Types.LEFT_BRACE)) {
			depth++;
			bd++;
		}
if(node.getTtype().equals(Types.RIGHT_BRACE)) {
	depth--;
	bd--;
}

if(node.getTtype().equals(Types.LEFT_PAREN)) {
	depth++;
	pd++;
}
if(node.getTtype().equals(Types.RIGHT_PAREN)) {
depth--;
pd--;
}

node.bdepth=bd;
node.depth=depth;
node.pdepth=pd;

	}
	
	
	
}



public void transformDepthConnected() {
	for (int i = 1; i < getMaxDepth(); i++) {
		transformDepthConnected(i);
	}
}


public void removeWhitespaces() {
	for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
		Node node = (Node) iterator.next();
		if(node.raw.equals("")) {
			iterator.remove();
		}
	}
}

public void transformDepthConnected(int depth) {
	
	
	boolean c = false;
	
	ArrayList<Node> connected = new ArrayList<>();
	Node first = null;
	for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
		Node node = (Node) iterator.next();
		if(node.depth==depth&&!c) {
			c=true;
			first = node;
		}
		if(node.depth>depth&&c) {
			c=false;
			for (Iterator iterator2 = connected.iterator(); iterator2.hasNext();) {
				Node n2 = (Node) iterator2.next();
				first.addChild(n2);
			}
			connected = new ArrayList<>();
		}
		if(c) {
			connected.add(node);
			iterator.remove();
			
		}
	}
	
	
}
public int getMaxDepth() {
	int biggest = 0;
	for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
		Node node = (Node) iterator.next();
		if(node.depth>biggest) {
			biggest = node.depth;
		}
	}
	return biggest;
}

public void parentToLeader() {
	ArrayList<Node> remove = new ArrayList<>();
	for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
		Node node = (Node) iterator.next();
		Node next = node;
		if(iterator.hasNext()) {
			next = (Node) iterator.next();
		}
		
		if(node.depth<next.depth) {
			node.addChild(next);
			remove.add(next);
		}
		
	}
	
	nodes.removeAll(remove);
	
}

}
