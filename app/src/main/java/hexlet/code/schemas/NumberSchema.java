package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        addPredicate(value -> isNumber(value));
        return this;
    }

    public NumberSchema positive() {
        addPredicate(value -> value == null || (isNumber(value) && (int) value > 0));
        return this;
    }

    public NumberSchema range(int min, int max) {
        addPredicate(value -> (int) value >= min && (int) value <= max);
        return this;
    }

    private boolean isNumber(Object value) {
        return value instanceof Number;
    }
}
