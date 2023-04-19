package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    protected final List<Predicate<Object>> rules = new ArrayList<>();

    protected final void addRule(Predicate<Object> predicate) {
        rules.add(predicate);
    }

    public final boolean isValid(Object value) {
        return rules.stream().allMatch(rule -> rule.test(value));
    }
}
