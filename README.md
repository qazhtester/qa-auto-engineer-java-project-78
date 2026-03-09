### Hexlet tests and linter status:
[![Actions Status](https://github.com/qazhtester/qa-auto-engineer-java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/qazhtester/qa-auto-engineer-java-project-78/actions)
[![SonarQube](https://github.com/qazhtester/qa-auto-engineer-java-project-78/actions/workflows/sonar.yml/badge.svg)](https://github.com/qazhtester/qa-auto-engineer-java-project-78/actions/workflows/sonar.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=qazhtester_qa-auto-engineer-java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=qazhtester_qa-auto-engineer-java-project-78)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=qazhtester_qa-auto-engineer-java-project-78&metric=bugs)](https://sonarcloud.io/summary/new_code?id=qazhtester_qa-auto-engineer-java-project-78)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=qazhtester_qa-auto-engineer-java-project-78&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=qazhtester_qa-auto-engineer-java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=qazhtester_qa-auto-engineer-java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=qazhtester_qa-auto-engineer-java-project-78)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=qazhtester_qa-auto-engineer-java-project-78&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=qazhtester_qa-auto-engineer-java-project-78)

## О проекте
Java-библиотека для валидации данных с поддержкой строк, чисел и Map-структур. Реализует fluent-интерфейс для построения цепочек проверок.

### Сборка проекта

```bash
cd app && ./gradlew build
``` 
```bash
cd app && make build
``` 

### Пример использования

#### Валидация строк

```java
// Создаем валидатор
Validator v = new Validator();

// Создание схемы для строки
StringSchema schema = v.string();

// Базовая проверка
schema.isValid("");      // true (пустая строка допустима по умолчанию)
schema.isValid(null);    // true (null допустим по умолчанию)

// Добавление правила required
schema.required();
schema.isValid(null);     // false
schema.isValid("");       // false
schema.isValid("hello");  // true

// Проверка минимальной длины
schema.minLength(5);
schema.isValid("hello");  // true (длина 5)
schema.isValid("hi");     // false (длина 2)

// Проверка содержания подстроки
schema.contains("@");
schema.isValid("test@example.com"); // true
schema.isValid("example.com");      // false (нет @)

// Комбинация правил
StringSchema emailSchema = v.string()
        .required()
        .minLength(5)
        .contains("@");

emailSchema.isValid("user@mail.com"); // true
emailSchema.isValid("user@");         // false (меньше 5 символов)
emailSchema.isValid("mail.com");      // false (нет @)
emailSchema.isValid("");              // false
emailSchema.isValid(null);            // false

// Переопределение правил (последний вызов имеет приоритет)
StringSchema usernameSchema = v.string()
        .minLength(10)
        .minLength(3); 

usernameSchema.isValid("jo");     // false (слишком короткое)
usernameSchema.isValid("john");   // true (подходит под новое правило)
```
#### Валидация чисел (int)

```java
// Создаем валидатор
Validator v = new Validator();

// Создание схемы для валидации чисел
NumberSchema schema = v.number();

// Базовая проверка
schema.isValid(5);    // true
schema.isValid(null); // true (null допустим по умолчанию)

// Добавление правила required
schema.required();
schema.isValid(null); // false
schema.isValid(10);   // true

// Проверка на положительное число
schema.positive();
schema.isValid(10);  // true
schema.isValid(-5);  // false
schema.isValid(0);   // false

// Проверка диапазона
schema.range(1, 10);
schema.isValid(1);   // true (граница включена)
schema.isValid(10);  // true (граница включена)
schema.isValid(11);  // false
```
#### Валидация Map

```java
// Создаем валидатор
Validator v = new Validator();

// Создание схемы для Map
MapSchema schema = v.map();

// Базовая проверка
Map<String, String> data = new HashMap<>();
schema.isValid(null);        // true
schema.isValid(data);        // true

// Проверка обязательности
schema.required();
schema.isValid(null);        // false
schema.isValid(data);        // true

// Проверка размера Map
schema.sizeof(2);
data.put("key1", "value1");
schema.isValid(data);        // false (размер 1)

data.put("key2", "value2");
schema.isValid(data);        // true (размер 2)

// Валидация данных (shape)
MapSchema userSchema = v.map();

// Определяем правила для каждого поля
Map<String, BaseSchema<String>> fields = new HashMap<>();
fields.put("firstName", v.string().required().minLength(3));
fields.put("lastName", v.string().required().minLength(3));
fields.put("email", v.string().required().contains("@"));

userSchema.shape(fields);

Map<String, String> invalidUser = new HashMap<>();
invalidUser.put("firstName", "Ян"); //некорректная длина
invalidUser.put("lastName", "Петров");
invalidUser.put("email", "yan@example.com");
userSchema.isValid(invalidUser); // false
```