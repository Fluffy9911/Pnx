package main.parsing.pattern;

import main.parsing.tokens.Node;

public interface Condition {

	public boolean possible(Node n, TypeResolver tr, PatternBuilder pb);

}
