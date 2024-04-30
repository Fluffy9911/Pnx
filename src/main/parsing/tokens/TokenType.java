package main.parsing.tokens;

import java.util.Objects;

public class TokenType {
String name;
TGroup group;
Treader reader;
public TokenType(String name, TGroup group, Treader reader) {
	super();
	this.name = name;
	this.group = group;
	this.reader = reader;
}

public boolean is(String in) {
	return reader.is(in);
}

public String getName() {
	return name;
}

public TGroup getGroup() {
	return group;
}

public Treader getReader() {
	return reader;
}

@Override
public String toString() {
	return "TokenType [name=" + name + ", group=" + group + "]";
}

@Override
public int hashCode() {
	return Objects.hash(group, name);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	TokenType other = (TokenType) obj;
	return group == other.group && Objects.equals(name, other.name);
}



}
