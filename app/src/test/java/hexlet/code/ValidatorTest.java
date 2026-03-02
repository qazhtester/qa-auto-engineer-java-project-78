package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ValidatorTest {

    @Test
    void stringValidatorTest() {
        String validString = "validString";
        String substring = "valid";
        String invalidString = "testSubstring";
        int minLen = validString.length();

        StringSchema stringSchema = new Validator()
                .string()
                .minLength(minLen)
                .contains(invalidString)
                .contains(substring);
        Assertions.assertTrue(stringSchema.isValid(null));
        Assertions.assertTrue(stringSchema.isValid(""));

        stringSchema.required();
        Assertions.assertFalse(stringSchema.isValid(null));
        Assertions.assertFalse(stringSchema.isValid(""));
        Assertions.assertFalse(stringSchema.isValid(substring));
        Assertions.assertFalse(stringSchema.isValid(invalidString));
        Assertions.assertTrue(stringSchema.isValid(validString));
    }

    @Test
    void validatorTestValidInteger() {
        final int from = 5;
        final int to = 10;
        final int zero = 0;
        final int one = 1;
        final int negative = -1;

        NumberSchema numberSchema = new Validator().number();
        Assertions.assertTrue(numberSchema.isValid(negative));

        numberSchema.positive();
        Assertions.assertTrue(numberSchema.isValid(null));

        numberSchema.required();
        Assertions.assertFalse(numberSchema.isValid(null));
        Assertions.assertFalse(numberSchema.isValid(zero));
        Assertions.assertTrue(numberSchema.isValid(one));

        numberSchema.range(zero, one).range(from, to);
        Assertions.assertFalse(numberSchema.isValid(from - one));
        Assertions.assertFalse(numberSchema.isValid(to + one));
        Assertions.assertTrue(numberSchema.isValid(from));
        Assertions.assertTrue(numberSchema.isValid(to));
    }

    @Test
    void validatorTestValidMapString() {
        final int size = 2;
        Validator validator = new Validator();
        Map<String, BaseSchema<String>> stringSchemas = new HashMap<>();
        stringSchemas.put("firstName", validator.string().required().contains("Ivan"));

        MapSchema mapSchema = validator.map().shape(stringSchemas);
        Assertions.assertTrue(mapSchema.isValid(null));

        mapSchema.required();
        Assertions.assertFalse(mapSchema.isValid(null));

        Map<String, String> humanMap = new HashMap<>();
        Assertions.assertFalse(mapSchema.isValid(humanMap));

        humanMap.put("firstName", "Ivan");
        Assertions.assertTrue(mapSchema.isValid(humanMap));

        mapSchema.sizeof(size);
        Assertions.assertFalse(mapSchema.isValid(humanMap));

        humanMap.put("lastName", null);
        Assertions.assertTrue(mapSchema.isValid(humanMap));

        stringSchemas.put("lastName", validator.string().required());
        mapSchema.shape(stringSchemas);
        Assertions.assertFalse(mapSchema.isValid(humanMap));
    }
}
