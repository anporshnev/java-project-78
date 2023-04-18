package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public MapSchema required() {
        addPredicate(this::isMap);
        return this;
    }

    public MapSchema sizeof(int size) {
        addPredicate(value -> isMap(value) && ((Map<?, ?>) value).size() == size);
        return this;
    }

    private boolean isMap(Object value) {
        return value instanceof Map;
    }
}
