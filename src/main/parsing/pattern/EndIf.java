package main.parsing.pattern;

import main.parsing.pattern.TypeResolver.ConditionalMatch;
import main.parsing.tokens.Node;

public class EndIf implements ConditionalMatch {
	private TypeResolver condition;

	public EndIf(TypeResolver condition) {
		this.condition = condition;
	}

	@Override
	public boolean isMatch(Node n, TypeResolver tr) {
		// TODO Auto-generated method stub
		return !condition.resolve(n);
	}
}
