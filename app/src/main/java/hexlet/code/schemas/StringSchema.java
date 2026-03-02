package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addRule("required", value -> !isNullOrEmpty(value));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addRule("minLength", value -> isNullOrEmpty(value) || value.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        addRule("contains", value -> isNullOrEmpty(value) || value.contains(substring));
        return this;
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
