package main.parsing.pattern;

import main.parsing.pattern.TypeResolver.ConditionalMatch;
import main.parsing.tokens.Node;

public class ThenUntil implements ConditionalMatch {
	boolean found = false;
	TypeResolver find, until;

	public ThenUntil(TypeResolver find, TypeResolver until) {
		super();
		this.find = find;
		this.until = until;
	}

	public static TypeResolver create(TypeResolver then, TypeResolver until) {
		TypeResolver tr = new TypeResolver(new ThenUntil(then, until));
		return tr;
	}

	@Override
	public boolean isMatch(Node n, TypeResolver tr) {
		if (found) {
			return !found;
		}
		if (find.resolve(n) || until.resolve(n)) {

			return true;
		}
		if (!(find.resolve(n) && until.resolve(n))) {
			found = true;
			return false;
		}

		return false;
	}
}
