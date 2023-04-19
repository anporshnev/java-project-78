package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        addRule(value -> value instanceof String && !((String) value).isBlank());
        return this;
    }

    public StringSchema minLength(int minLength) {
        addRule(value -> ((String) value).length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        addRule(value -> ((String) value).contains(substring));
        return this;
    }
}
