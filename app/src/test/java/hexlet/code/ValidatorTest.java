package hexlet.code;

import org.junit.jupiter.api.Assertions;
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
}
