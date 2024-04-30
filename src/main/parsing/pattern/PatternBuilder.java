package main.parsing.pattern;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import main.parsing.tokens.Node;

public class PatternBuilder {
	ArrayList<Node> nodes = new ArrayList<>(), matched = new ArrayList<>();
	LinkedHashMap<TypeResolver, Integer> patterns = new LinkedHashMap<>();

	public PatternBuilder() {
		super();
	}

	public PatternBuilder(ArrayList<Node> nodes) {
		super();
		this.nodes = nodes;
	}

	public boolean matches() {
		matched = new ArrayList<>();
		int currentNodeIndex = 0;
		int currentPatternIndex = 0;

		while (currentNodeIndex < nodes.size() && currentPatternIndex < patterns.size()) {
			Node currentNode = nodes.get(currentNodeIndex);
			TypeResolver currentPattern = (TypeResolver) patterns.keySet().toArray()[currentPatternIndex];
			int currentPatternCount = patterns.get(currentPattern);

			if (currentPattern.resolve(currentNode)) {
				// If the current node matches the current pattern, increment its count
				patterns.put(currentPattern, currentPatternCount + 1);
				matched.add(currentNode);
				// Move to the next node
				currentNodeIndex++;
			} else {
				// If the current node does not match the current pattern, check if it's an
				// endIf condition
				if (currentPattern instanceof ConditionalResolver) {
					ConditionalResolver conditionalResolver = (ConditionalResolver) currentPattern;
					if (!conditionalResolver.resolve(currentNode)) {
						// If the endIf condition is met, move to the next pattern
						currentPatternIndex++;
						matched.add(currentNode);
					} else {
						// If the endIf condition is not met, continue with the current pattern
						currentNodeIndex++;
					}
				} else {
					// If the current node does not match the current pattern and it's not an endIf
					// condition,
					// move to the next pattern
					currentPatternIndex++;
				}
			}
		}

		// Check if all patterns have been matched
		for (Integer count : patterns.values()) {
			if (count == 0) {
				return false; // Pattern not matched
			}
		}

		// Check if all nodes have been processed
		return currentNodeIndex == nodes.size();
	}

	public boolean matches(ArrayList<Node> nodes) {
		ArrayList<Node> remainingNodes = new ArrayList<>(nodes);

		for (TypeResolver pattern : patterns.keySet()) {
			System.out.println("Checking pattern: " + pattern);
			boolean patternMatched = false;

			for (int nodeIndex = 0; nodeIndex < remainingNodes.size(); nodeIndex++) {
				Node currentNode = remainingNodes.get(nodeIndex);
				if (pattern.resolve(currentNode)) {
					patternMatched = true;
					matched.add(currentNode);
					remainingNodes.remove(nodeIndex);
					System.out.println("Matched pattern: " + pattern + " with node: " + currentNode);
					break;
				}
			}

			if (!patternMatched) {
				System.out.println("Pattern not matched: " + pattern);
				return false;
			}
		}

		if (!remainingNodes.isEmpty()) {
			System.out.println("All patterns matched but there are remaining nodes:");
			for (Node node : remainingNodes) {
				System.out.println(node);
			}
			return false;
		}

		System.out.println("All patterns matched and all nodes processed.");
		return true;
	}

	public PatternBuilder beginsWith(TypeResolver type) {
		patterns.put(type, 0);
		return this;
	}

	public PatternBuilder then(TypeResolver type) {
		patterns.put(type, 0);
		return this;
	}

	public PatternBuilder thenUntil(TypeResolver type, TypeResolver tr) {
		patterns.put(ThenUntil.create(type, tr), 0);
		return this;
	}

	public PatternBuilder until(TypeResolver type) {
		patterns.put(Until.create(type), 0);
		return this;
	}

	public PatternBuilder endIf(TypeResolver type, Condition c) {
		ConditionalResolver cr = new ConditionalResolver(type);
		cr.setBuilder(this);
		cr.setCondition(c);
		patterns.put(cr, 0);
		return this;
	}

}
