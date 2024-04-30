package main.parsing;

import main.parsing.pattern.PatternBuilder;
import main.parsing.pattern.TypeResolver;
import main.parsing.tokens.EndIfCondition;
import main.parsing.tokens.TGroup;
import main.parsing.tokens.Types;

public class Patterns {

	public static PatternBuilder STATIC_VAR = new PatternBuilder().beginsWith(new TypeResolver(Types.STATIC))
			.then(new TypeResolver(TGroup.TYPE)).then(new TypeResolver(Types.STRING))
			.then(new TypeResolver(Types.OPERATOR_ASSIGN)).until(new TypeResolver(Types.EOL)) // Change to EOL if it
																								// represents the
																								// semicolon
			.then(new TypeResolver(Types.EOL)); // Add pattern for semicolon

	public static final PatternBuilder IF_PATTERN = new PatternBuilder().beginsWith(new TypeResolver(Types.KEYWORD_IF))
			.thenUntil(new TypeResolver(Types.LEFT_PAREN), new TypeResolver(Types.RIGHT_PAREN))
			.then(new TypeResolver(Types.LEFT_BRACE)).endIf(new TypeResolver(Types.RIGHT_BRACE), new EndIfCondition());

	public static final PatternBuilder IF_ELSE_PATTERN = new PatternBuilder()
			.beginsWith(new TypeResolver(Types.KEYWORD_IF))
			.thenUntil(new TypeResolver(Types.LEFT_PAREN), new TypeResolver(Types.RIGHT_PAREN))
			.then(new TypeResolver(Types.LEFT_BRACE)).endIf(new TypeResolver(Types.RIGHT_BRACE), new EndIfCondition())
			.then(new TypeResolver(Types.KEYWORD_ELSE)).then(new TypeResolver(Types.LEFT_BRACE))
			.endIf(new TypeResolver(Types.RIGHT_BRACE), new EndIfCondition());
}
