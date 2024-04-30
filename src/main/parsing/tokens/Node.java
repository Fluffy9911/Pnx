package main.parsing.tokens;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node implements Serializable {
	Node parent;
	public String raw;
	List<Node> children;
	TokenType ttype;
	TGroup type;
	public int depth = 0, pdepth = 0, bdepth = 0;

	public Node(String raw, TokenType ttype, TGroup type) {
		super();

		this.ttype = ttype;
		this.type = type;
		this.raw = raw;
		children = new ArrayList<>();

	}

	public void addChild(Node n) {
		n.setParent(this);
		this.children.add(n);
	}

	public Node getParent() {
		return parent;
	}

	public ArrayList<Node> getChildren() {
		return (ArrayList<Node>) children;
	}

	public TokenType getTtype() {
		return ttype;
	}

	public TGroup getType() {
		return type;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public void setTtype(TokenType ttype) {
		this.ttype = ttype;
	}

	public void setType(TGroup type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Node [");
		if (parent != null) {
			builder.append("parent=");
			// builder.append(parent);
			builder.append(", ");
		}
		if (raw != null) {
			builder.append("raw=");
			builder.append(raw);
			builder.append(", ");
		}
		if (children != null) {
			builder.append("children=");

			for (Iterator iterator = children.iterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();
				builder.append(node.toString());
			}

			builder.append(", ");
		}
		if (ttype != null) {
			builder.append("ttype=");
			builder.append(ttype.name);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
		}
		builder.append("]");
		return builder.toString();
	}

}
