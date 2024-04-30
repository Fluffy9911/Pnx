package main.parsing;

import java.util.ArrayList;
import java.util.Stack;

import main.parsing.tokens.Node;
import main.parsing.tokens.TGroup;
import main.parsing.tokens.TokenType;
import main.parsing.tokens.Types;

public class Ast {
	public static Node buildAST(ArrayList<Node> tokens) {
	    Node root = new Node("prg", Types.PRG, TGroup.PRG);
	    Node currentParent = root;
	    Stack<Node> controlStack = new Stack<>();
	    
	    for (Node token : tokens) {
	        TokenType type = token.getTtype();
	        
	        if (type.equals(Types.KEYWORD_IF) || type.equals(Types.KEYWORD_WHILE) || type.equals(Types.KEYWORD_FOR)) {
	            controlStack.push(currentParent);
	            currentParent.addChild(token);
	            currentParent = token;
	        } else if (type.equals(Types.LEFT_BRACE)) {
	            controlStack.push(currentParent);
	            currentParent = token;
	        } else if (type.equals(Types.RIGHT_BRACE)) {
	            if (!controlStack.isEmpty()) {
	                currentParent = controlStack.pop();
	            }
	        } else if (type.equals(Types.LEFT_PAREN)) {
	            controlStack.push(currentParent);
	            currentParent = token;
	        } else if (type.equals(Types.RIGHT_PAREN)) {
	            if (!controlStack.isEmpty()) {
	                currentParent = controlStack.pop();
	            }
	        } else {
	            currentParent.addChild(token);
	        }
	    }

	    return root;
	}


}
