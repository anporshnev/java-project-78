package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        addRule(this::isNumber);
        return this;
    }

    public NumberSchema positive() {
        addRule(value -> value == null || (isNumber(value) && (int) value > 0));
        return this;
    }

    public NumberSchema range(int min, int max) {
        addRule(value -> (int) value >= min && (int) value <= max);
        return this;
    }

    private boolean isNumber(Object value) {
        return value instanceof Number;
    }
}
