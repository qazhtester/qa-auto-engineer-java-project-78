package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"Zhanna", "Zhe", "anZh"})
    void validatorTestValidString(String s) {
        final int minLen = 3;

        Validator validator = new Validator();
        StringSchema stringSchema = validator
                .string()
                .contains("Zh")
                .required(true)
                .minLength(minLen);

        boolean result = stringSchema.isValid(s);
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Zh", "zhzh"})
    void validatorTestInvalidString(String s) {
        final int minLen = 3;

        Validator validator = new Validator();
        StringSchema stringSchema = validator
                .string()
                .contains("Zh")
                .required(true)
                .minLength(minLen);

        boolean result = stringSchema.isValid(s);
        Assertions.assertFalse(result);
    }

    @Test
    void validatorTestValidInteger() {
        final int from = 5;
        final int to = 10;

        Validator validator = new Validator();
        NumberSchema stringSchema = validator
                .number()
                .positive(true)
                .range(from, to);

        boolean result1 = stringSchema.isValid(from);
        boolean result2 = stringSchema.isValid(to);

        Assertions.assertTrue(result1);
        Assertions.assertTrue(result2);
    }

    @Test
    void validatorTestInvalidInteger() {
        final int from = -5;
        final int to = 0;

        Validator validator = new Validator();
        NumberSchema numberSchema = validator
                .number()
                .required(true)
                .positive(true)
                .range(from, to);

        boolean result1 = numberSchema.isValid(from);
        boolean result2 = numberSchema.isValid(null);
        boolean result3 = numberSchema.isValid(to + 1);

        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
        Assertions.assertFalse(result3);
    }
}
