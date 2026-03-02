package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Map<Object, Predicate<T>> rules = new LinkedHashMap<>();

    protected final void addRule(Object name, Predicate<T> rule) {
        rules.put(name, rule);
    }

    public final boolean isValid(T value) {
        return rules.values().stream().allMatch(check -> check.test(value));
    }
}
