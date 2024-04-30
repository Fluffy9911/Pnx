package main.parsing.tokens;

import main.parsing.pattern.Condition;
import main.parsing.pattern.PatternBuilder;
import main.parsing.pattern.TypeResolver;

public class EndIfCondition implements Condition {
	private int nestingLevel = 0;

	@Override
	public boolean possible(Node n, TypeResolver tr, PatternBuilder pb) {
		if (tr.resolve(n)) {
			// Increment the nesting level when an "if" is encountered
			nestingLevel++;
		} else if (tr.resolve(new Node("}", Types.RIGHT_BRACE, TGroup.SYMBOL))) {
			// Decrement the nesting level when a closing brace is encountered
			nestingLevel--;
			if (nestingLevel == 0) {
				// If the nesting level becomes zero, the "endif" condition is met
				return false;
			}
		}
		// Continue pattern matching if the nesting level is not zero
		return nestingLevel != 0;
	}
}
