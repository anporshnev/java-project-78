package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema required() {
        addPredicate(value -> value instanceof String && !((String) value).isBlank());
        return this;
    }

    public StringSchema minLength(int minLength) {
        addPredicate(value -> ((String) value).length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        addPredicate(value -> ((String) value).contains(substring));
        return this;
    }
}
