package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public MapSchema required() {
        addRule(this::isMap);
        return this;
    }

    public MapSchema sizeof(int size) {
        addRule(value -> isMap(value) && ((Map<?, ?>) value).size() == size);
        return this;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        addRule(value -> ((Map<?, ?>) value).entrySet().stream()
                .allMatch(item -> schemas.get(item.getKey()).isValid(item.getValue())));
    }

    private boolean isMap(Object value) {
        return value instanceof Map;
    }
}
