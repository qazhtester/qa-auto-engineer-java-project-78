package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    public MapSchema required() {
        addRule("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeOf(int size) {
        addRule("sizeOf", map -> map == null || map.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema<String> valueSchema = entry.getValue();
            Predicate<Map<String, String>> mapPredicate = map -> map == null || valueSchema.isValid(map.get(key));
            addRule(key, mapPredicate);
        }

        return this;
    }
}
