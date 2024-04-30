package main.parsing.pattern;

import main.parsing.tokens.Node;

public class ConditionalResolver extends TypeResolver {
	Condition c;
	PatternBuilder builder;

	public ConditionalResolver(TypeResolver tr) {
		super();
		this.flag = tr.flag;
		this.raw = tr.raw;
		this.rawa = tr.rawa;
		this.tg = tr.tg;
		this.tga = tr.tga;
		this.ty = tr.ty;
		this.tya = tr.tya;
		this.cm = tr.cm;

	}

	public ConditionalResolver(Condition c, PatternBuilder builder) {

		this.c = c;
		this.builder = builder;
	}

	public Condition getCondition() {
		return c;
	}

	public void setCondition(Condition c) {
		this.c = c;
	}

	public PatternBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(PatternBuilder builder) {
		this.builder = builder;
	}

	public boolean andCondition(Node n) {
		return c.possible(n, this, builder);
	}

	@Override
	public boolean resolve(Node in) {
		// TODO Auto-generated method stub
		return super.resolve(in) && andCondition(in);
	}

}
