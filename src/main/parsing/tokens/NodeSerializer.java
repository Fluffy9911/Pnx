package main.parsing.tokens;
import com.google.gson.*;
import java.lang.reflect.Type;

public class NodeSerializer implements JsonSerializer<Node> {
    @Override
    public JsonElement serialize(Node node, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("raw", node.raw);
        jsonObject.add("ttype", context.serialize(node.getTtype()));
        jsonObject.add("type", context.serialize(node.getType()));
        // Serialize children recursively
        if (node.getChildren() != null && !node.getChildren().isEmpty()) {
            JsonArray childrenArray = new JsonArray();
            for (Node child : node.getChildren()) {
                childrenArray.add(serialize(child, typeOfSrc, context));
            }
            jsonObject.add("children", childrenArray);
        }
        return jsonObject;
    }
}
