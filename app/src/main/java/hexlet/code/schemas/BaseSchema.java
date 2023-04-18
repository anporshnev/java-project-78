package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    protected final List<Predicate<Object>> predicates = new ArrayList<>();

    protected void addPredicate(Predicate<Object> predicate) {
        predicates.add(predicate);
    }

    public final boolean isValid(Object value) {
        return predicates.stream().allMatch(predicate -> predicate.test(value));
    }
}
