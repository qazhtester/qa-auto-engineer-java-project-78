package hexlet.code;

public abstract class BaseSchema<T> {
    private boolean isRequired;

    public abstract boolean isValid(T value);

    /**Геттер.
     * @return признак обязательности проверяемого значения **/
    public boolean isRequired() {
        return isRequired;
    }

    /**
     * Сеттер.
     * @param required признак обязательности проверяемого значения
     **/
    public void setRequired(boolean required) {
        isRequired = required;
    }
}
