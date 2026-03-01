package hexlet.code;

public final class NumberSchema extends BaseSchema<Integer> {
    private boolean isPositive;
    private int[] range;

    public NumberSchema required(boolean isRequired) {
        setRequired(isRequired);
        return this;
    }

    public NumberSchema positive(boolean isPositive) {
        this.isPositive = isPositive;
        return this;
    }

    public NumberSchema range(int... range) {
        this.range = range;
        return this;
    }

    @Override
    public boolean isValid(Integer value) {
        boolean requiredCheckResult = checkRequired(value);

        if (value == null) {
            return requiredCheckResult;
        }

        boolean minLengthCheckResult = checkPositive(value);
        boolean containsCheckResult = checkRange(value);

        return requiredCheckResult && minLengthCheckResult && containsCheckResult;
    }

    private boolean checkRequired(Integer number) {
        return !isRequired() || number != null;
    }

    private boolean checkPositive(Integer number) {
        return !isPositive || number >= 0;
    }

    private boolean checkRange(Integer number) {
        return number >= range[0] && number <= range[1];
    }
}
