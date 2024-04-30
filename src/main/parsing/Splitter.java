package main.parsing;
import java.util.ArrayList;
import java.util.List;

public class Splitter {
	public static Splitter DEFAULT = new Splitter(new String[] { " ", "\t", "\n" },
			new String[] { ",", "=", "-", "+", ";", "(", ")", "{", "}", "\\", "//", "!","#","$","." });
	public static Splitter DEFAULT_NOP = new Splitter(new String[] { " ", "\t", "\n" },
			new String[] { ",", "=", "-", "+", ";", "(", ")", "{", "}", "\"", "\\", "//", "!","#","$" });
	// Arrays to hold removals and breaks
	String[] removals;
	String[] breaks;

	public Splitter(String[] removals, String[] breaks) {
		this.removals = removals;
		this.breaks = breaks;

	}

	public List<String> tokenize(String input) {

		List<String> tokens = new ArrayList<>();
		StringBuilder currentToken = new StringBuilder(); // To build each token

		for (int i = 0; i < input.length(); i++) {
			char currentChar = input.charAt(i);

			// Check if the current character is a removal
			boolean isRemoval = false;
			for (String removal : removals) {
				if (removal.indexOf(currentChar) != -1) {
					isRemoval = true;
					break;
				}
			}

			if (isRemoval) {
				// If current token has content, add it as a token
				if (currentToken.length() > 0) {
					tokens.add(currentToken.toString());
					currentToken.setLength(0); // Clear the StringBuilder
				}
				continue;
			}
			// Check if the current character is a break
			boolean isBreak = false;
			for (String breakChar : breaks) {
				if (breakChar.indexOf(currentChar) != -1) {
					isBreak = true;
					break;
				}
			}

			// If it's a break, add the current token to the list and start fresh
			if (isBreak) {
				if (currentToken.length() > 0) {
					tokens.add(currentToken.toString());
					currentToken.setLength(0); // Clear the StringBuilder
				}
				tokens.add(String.valueOf(currentChar)); // Add the break character
			} else {
				currentToken.append(currentChar); // Add the character to the current token
			}
		}

		// Add the last token (if any)
		if (currentToken.length() > 0) {
			tokens.add(currentToken.toString());
		}

		return tokens;
	}

}