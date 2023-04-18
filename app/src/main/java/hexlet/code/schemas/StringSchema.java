package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {
    private boolean requiredState = false;
    private List<Predicate<Object>> predicates = new ArrayList<>();

    public StringSchema required() {
        requiredState = !requiredState;
        return this;
    }

    public StringSchema minLength(int minLength) {
        predicates.add(value -> ((String) value).length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        predicates.add(value -> ((String) value).contains(substring));
        return this;
    }

    public boolean isValid(Object value) {
        return checkRequired(value) && predicates.stream().allMatch(predicate -> predicate.test(value));
    }

    public boolean checkRequired(Object value) {
        if (!requiredState) {
            return true;
        }
        return value instanceof String && !((String) value).isBlank();
    }
}
