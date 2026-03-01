package hexlet.code;

public final class StringSchema extends BaseSchema<String> {
    private int minLength;
    private String contains;

    public StringSchema required(boolean isRequired) {
        setRequired(isRequired);
        return this;
    }

    public StringSchema minLength(int len) {
        this.minLength = len;
        return this;
    }

    public StringSchema contains(String substring) {
        this.contains = substring;
        return this;
    }

    @Override
    public boolean isValid(String value) {
        boolean requiredCheckResult = checkRequired(value);

        if (value == null) {
            return requiredCheckResult;
        }

        boolean minLengthCheckResult = checkMinLen(value);
        boolean containsCheckResult = checkContains(value);

        return requiredCheckResult && minLengthCheckResult && containsCheckResult;
    }

    private boolean checkRequired(String string) {
        return !isRequired() || string != null && !string.isEmpty();
    }

    private boolean checkMinLen(String s) {
        return s.length() >= minLength;
    }

    private boolean checkContains(String s) {
        return s.contains(contains);
    }
}
