package hexlet.code;

public final class StringSchema {
    private boolean required;
    private int minLength;
    private String contains;

    public StringSchema required(boolean required) {
        this.required = required;
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

    public boolean isValid(String s) {
        boolean requiredCheckResult = checkRequired(s);
        boolean minLengthCheckResult = checkMinLen(s);
        boolean containsCheckResult = checkContains(s);

        return requiredCheckResult && minLengthCheckResult && containsCheckResult;
    }

    private boolean checkRequired(String string) {
        return !required || string != null && !string.isEmpty();
    }

    private boolean checkMinLen(String s) {
        return s.length() >= minLength;
    }

    private boolean checkContains(String s) {
        return s.contains(contains);
    }
}
