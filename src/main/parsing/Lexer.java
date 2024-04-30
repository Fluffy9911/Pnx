package main.parsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import main.parsing.tokens.Node;
import main.parsing.tokens.TGroup;
import main.parsing.tokens.TokenType;
import main.parsing.tokens.Types;

public class Lexer {
	ArrayList<String> lines;
	ArrayList<TokenType> types;
	String[] split = new String[] { "(", ")", "[", "]", "{", "}" };

	public Lexer(ArrayList<String> lines) {
		super();
		this.lines = lines;
		types = new ArrayList<TokenType>(Types.asList());
	}

	public ArrayList<Node> read() {
		ArrayList<Node> nodes = new ArrayList<>();

		for (Iterator iterator = lines.iterator(); iterator.hasNext();) {
			String line = (String) iterator.next();

			ArrayList<String> split = (ArrayList<String>) Splitter.DEFAULT.tokenize(line);

			for (String token : split) {
				// Check if the token is a string literal
				if (token.startsWith("\"") && token.endsWith("\"")) {
					// If it's a string literal, create a node for it including the quotes
					Node stringNode = new Node(token, Types.STRING, TGroup.TYPE);
					nodes.add(stringNode);
				} else {
					// Otherwise, process token as usual
					handleRegularToken(token, nodes);
				}
			}
		}

		return nodes;
	}

	public void splitAndAdd(ArrayList<String> split, String string2) {
		for (Iterator iterator2 = split.iterator(); iterator2.hasNext();) {
			String string = (String) iterator2.next();
			add(string.split(Pattern.quote(string2)), split);
		}
	}

	public void add(String[] tokens, ArrayList<String> split) {
		for (int i = 0; i < tokens.length; i++) {
			split.add(tokens[i]);
		}
	}

	private void handleRegularToken(String token, ArrayList<Node> nodes) {

		StringBuilder cbuf = new StringBuilder();

		cbuf.append(token);

		types.forEach((tt) -> {
			if (tt.is(cbuf.toString())) {
				Node n = new Node(cbuf.toString(), tt, tt.getGroup());
				nodes.add(n);
				cbuf.delete(0, cbuf.length());
			}
		});

		Node n = new Node(cbuf.toString(), Types.STRING, TGroup.TYPE);
		nodes.add(n);
		cbuf.delete(0, cbuf.length());

	}

}
