package main.parsing.pattern;

import java.util.Arrays;

import main.parsing.tokens.Node;
import main.parsing.tokens.TGroup;
import main.parsing.tokens.TokenType;

public class TypeResolver {
	public TGroup tg;
	public TokenType ty;
	public String raw;

	public TGroup[] tga;
	public TokenType[] tya;
	public String[] rawa;

	public int flag = 0;

	public ConditionalMatch cm;

	public void matchExact() {
		flag = 0;
	}

	public TypeResolver() {
		super();
	}

	public void matchNot() {
		flag = 1;
	}

	public TypeResolver(TGroup tg) {
		super();
		this.tg = tg;
	}

	public TypeResolver(TokenType ty) {
		super();
		this.ty = ty;
	}

	public TypeResolver(String raw) {
		super();
		this.raw = raw;
	}

	public TypeResolver(TGroup... tga) {
		super();
		this.tga = tga;
	}

	public TypeResolver(TokenType... tya) {
		super();
		this.tya = tya;
	}

	public TypeResolver(String... rawa) {
		super();
		this.rawa = rawa;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeResolver [");
		if (tg != null) {
			builder.append("tg=");
			builder.append(tg);
			builder.append(", ");
		}
		if (ty != null) {
			builder.append("ty=");
			builder.append(ty);
			builder.append(", ");
		}
		if (raw != null) {
			builder.append("raw=");
			builder.append(raw);
			builder.append(", ");
		}
		if (tga != null) {
			builder.append("tga=");
			builder.append(Arrays.toString(tga));
			builder.append(", ");
		}
		if (tya != null) {
			builder.append("tya=");
			builder.append(Arrays.toString(tya));
			builder.append(", ");
		}
		if (rawa != null) {
			builder.append("rawa=");
			builder.append(Arrays.toString(rawa));
			builder.append(", ");
		}
		builder.append("flag=");
		builder.append(flag);
		builder.append(", ");
		if (cm != null) {
			builder.append("cm=");
			builder.append(cm);
		}
		builder.append("]");
		return builder.toString();
	}

	public TypeResolver(ConditionalMatch cm) {
		super();
		this.cm = cm;
	}

	public boolean resolve(Node in) {
		if (cm != null) {
			switch (flag) {

			case 0:
				return cm.isMatch(in, this);
			case 1:
				return !cm.isMatch(in, this);
			default:
				return cm.isMatch(in, this);
			}

		}
		if (tg != null) {
			switch (flag) {

			case 0:
				return in.getType() == tg;
			case 1:
				return in.getType() != tg;
			default:
				return in.getType() == tg;
			}

		}

		if (ty != null) {
			switch (flag) {

			case 0:
				return in.getTtype().equals(ty);
			case 1:
				return !in.getTtype().equals(ty);
			default:
				return in.getTtype().equals(ty);
			}

		}
		if (raw != null) {
			switch (flag) {

			case 0:
				return in.raw.equals(raw);
			case 1:
				return !in.raw.equals(raw);
			default:
				return in.raw.equals(raw);
			}

		}
		if (tga != null) {
			switch (flag) {

			case 0:
				return isOfGroup(tga, in);
			case 1:
				return !isOfGroup(tga, in);
			default:
				return isOfGroup(tga, in);
			}

		}
		if (tya != null) {
			switch (flag) {

			case 0:
				return isOfType(tya, in);
			case 1:
				return !isOfType(tya, in);
			default:
				return isOfType(tya, in);
			}

		}
		if (raw != null) {
			switch (flag) {

			case 0:
				return isOfStringArr(rawa, in);
			case 1:
				return !isOfStringArr(rawa, in);
			default:
				return isOfStringArr(rawa, in);
			}

		}
		return false;
	}

	public boolean isOfType(TokenType[] n, Node node) {
		if (usableArray(n))
			for (int i = 0; i < n.length; i++) {
				if (node.getTtype().equals(n[i])) {
					return true;
				}
			}
		return false;
	}

	public boolean isOfGroup(TGroup[] n, Node node) {
		if (usableArray(n))
			for (int i = 0; i < n.length; i++) {
				if (node.getType() == n[i]) {
					return true;
				}
			}
		return false;
	}

	public boolean isOfStringArr(String[] n, Node node) {
		if (usableArray(n))
			for (int i = 0; i < n.length; i++) {
				if (node.raw.equals(n[i])) {
					return true;
				}
			}
		return false;
	}

	public boolean usableArray(Object[] arr) {
		if (arr == null)
			return false;

		if (arr.length == 0)
			return false;

		return true;
	}

	public static interface ConditionalMatch {

		public boolean isMatch(Node n, TypeResolver tr);

	}
}
