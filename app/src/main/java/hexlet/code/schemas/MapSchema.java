package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    public MapSchema required() {
        addRule("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        addRule("sizeOf", map -> map == null || map.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        Predicate<Map<String, String>> rule = map -> map == null || validateValues(map, schemas);
        addRule("shape", rule);
        return this;
    }

    private boolean validateValues(Map<String, String> map,
                                   Map<String, BaseSchema<String>> schemas) {
        for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema<String> valueSchema = entry.getValue();
            if (!valueSchema.isValid(map.get(key))) {
                return false;
            }
        }
        return true;
    }
}
