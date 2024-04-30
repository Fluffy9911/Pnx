package main.parsing.pattern;

import main.parsing.pattern.TypeResolver.ConditionalMatch;
import main.parsing.tokens.Node;

public class Until implements ConditionalMatch {
	private boolean found = false;
	private final TypeResolver find;

	public Until(TypeResolver find) {
		this.find = find;
	}

	public static TypeResolver create(TypeResolver until) {
		return new TypeResolver(new Until(until));
	}

	@Override
	public boolean isMatch(Node n, TypeResolver tr) {
		if (!found) {
			if (find.resolve(n)) {
				found = true;
				return false; // Stop matching after finding the condition
			}
			return true; // Continue matching until the condition is found
		}
		return false; // Stop matching after the condition is found
	}
}
