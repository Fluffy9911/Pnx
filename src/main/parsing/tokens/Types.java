package main.parsing.tokens;

import java.util.List;

public class Types {

	// Define token types
	public static TokenType PRG = new TokenType("prg", TGroup.PRG, single(""));
	public static TokenType EOL = new TokenType("eol", TGroup.EOL, single(";"));
	public static TokenType COMMA = new TokenType("comma", TGroup.COMMA, single(","));
	public static TokenType INTEGER = new TokenType("integer", TGroup.TYPE, regex("\\d+"));
	public static TokenType FLOAT = new TokenType("float", TGroup.TYPE, regex("\\d+\\.\\d+"));
	public static TokenType STRING_LITERAL = new TokenType("string_literal", TGroup.TYPE, single("string"));
	public static TokenType IDENTIFIER = new TokenType("identifier", TGroup.IDENTIFIER, regex("[a-zA-Z][a-zA-Z0-9_]*"));
	public static TokenType KEYWORD_IF = new TokenType("if", TGroup.KEYWORD, single("if"));
	public static TokenType KEYWORD_ELSE = new TokenType("else", TGroup.KEYWORD, single("else"));
	public static TokenType KEYWORD_WHILE = new TokenType("while", TGroup.KEYWORD, single("while"));
	public static TokenType KEYWORD_FOR = new TokenType("for", TGroup.KEYWORD, single("for"));
	public static TokenType KEYWORD_FUNCTION = new TokenType("function", TGroup.KEYWORD, single("function"));
	public static TokenType OPERATOR_PLUS = new TokenType("plus", TGroup.OPERATOR, single("+"));
	public static TokenType OPERATOR_MINUS = new TokenType("minus", TGroup.OPERATOR, single("-"));
	public static TokenType OPERATOR_MULTIPLY = new TokenType("multiply", TGroup.OPERATOR, single("*"));
	public static TokenType OPERATOR_DIVIDE = new TokenType("divide", TGroup.OPERATOR, single("/"));
	public static TokenType OPERATOR_ASSIGN = new TokenType("assign", TGroup.OPERATOR, single("="));
	public static TokenType LEFT_PAREN = new TokenType("left_paren", TGroup.SYMBOL, single("("));
	public static TokenType RIGHT_PAREN = new TokenType("right_paren", TGroup.SYMBOL, single(")"));
	public static TokenType LEFT_BRACE = new TokenType("left_brace", TGroup.SYMBOL, single("{"));
	public static TokenType RIGHT_BRACE = new TokenType("right_brace", TGroup.SYMBOL, single("}"));
	public static TokenType LEFT_BRACKET = new TokenType("left_bracket", TGroup.SYMBOL, single("["));
	public static TokenType RIGHT_BRACKET = new TokenType("right_bracket", TGroup.SYMBOL, single("]"));
	public static TokenType KEYWORD_RETURN = new TokenType("return", TGroup.KEYWORD, single("return"));
	public static TokenType BOOLEAN = new TokenType("boolean", TGroup.TYPE, regex("true|false"));
	public static TokenType PUBLIC = new TokenType("public_am", TGroup.AM, single("public"));
	public static TokenType STATIC = new TokenType("static_am", TGroup.AM, single("static"));
	public static TokenType NULL = new TokenType("null", TGroup.AM, single("null"));
	public static TokenType STRING = new TokenType("str", TGroup.AM, single("null"));

	public static List<TokenType> asList() {
		return List.of(EOL, COMMA, INTEGER, FLOAT, STRING_LITERAL, KEYWORD_IF, KEYWORD_ELSE, KEYWORD_WHILE, KEYWORD_FOR,
				KEYWORD_FUNCTION, OPERATOR_PLUS, OPERATOR_MINUS, OPERATOR_MULTIPLY, OPERATOR_DIVIDE, OPERATOR_ASSIGN,
				LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE, LEFT_BRACKET, RIGHT_BRACKET, KEYWORD_RETURN, BOOLEAN,
				PUBLIC, STATIC, NULL);
	}

	// Utility method to create a single token reader
	public static Treader single(String rf) {
		return new Treader() {
			@Override
			public boolean is(String in) {
				return in.equals(rf);
			}
		};
	}

	// Utility method to create a regex token reader
	public static Treader regex(String regex) {
		return new Treader() {
			@Override
			public boolean is(String in) {
				return in.matches(regex);
			}
		};
	}
}
