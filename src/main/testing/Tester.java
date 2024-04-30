package main.testing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import main.parsing.Lexer;
import main.parsing.Parser;
import main.parsing.Patterns;
import main.parsing.tokens.Node;

public class Tester {

	public static void main(String[] args) {
		File f = new File("C:\\Users\\James.M\\OneDrive\\Desktop\\langmock\\test.txt");
		try {
			List<String> lines = Files.lines(f.toPath()).toList();
			Lexer l = new Lexer(new ArrayList<String>(lines));

			ArrayList<Node> nodes = l.read();
			Parser p = new Parser(nodes);
//			p.transformDepth();
//			p.transformDepthConnected();
//			p.parentToLeader();
			p.removeWhitespaces();
			nodes = p.nodes;
			System.out.println(nodes.toString());
			System.out.println(Patterns.IF_PATTERN.matches(nodes));

//		Node n = new Node("s",Types.PRG,TGroup.PRG);
//		n.setChildren(nodes);
//		System.out.println(n.toString());
//		Gson g = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Node.class, new NodeSerializer()).create();
//		String o = g.toJson(n);
//		System.out.println(o);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
