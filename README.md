# Проект "Валидатор данных"
[![Actions Status](https://github.com/anporshnev/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/anporshnev/java-project-78/actions)
[![Build Status](https://github.com/anporshnev/java-project-78/workflows/Build/badge.svg)](https://github.com/anporshnev/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/9cca25bec71d1e04fc61/maintainability)](https://codeclimate.com/github/anporshnev/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/9cca25bec71d1e04fc61/test_coverage)](https://codeclimate.com/github/anporshnev/java-project-78/test_coverage)

Валидатор данных – небольшая библиотека, с помощью которой можно проверять корректность данных (string, number, map).

### Валидация строк

`required` – любая непустая строка

`minLength` – строка равна или длиннее указанного числа

`contains `– строка содержит определённую подстроку

#### Пример использования:

```
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

Validator v = new Validator();

StringSchema schema = v.string();

schema.isValid(""); // true
// Пока на вызван метод required(), null считается валидным
schema.isValid(null); // true

schema.required();

schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid(5); // false
schema.isValid(""); // false

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
// уже false, так как добавлена ещё одна проверка contains("whatthe")

```

### Валидация чисел

`required` – любое число включая ноль

`positive` – положительное число

`range` – диапазон, в который должны попадать числа включая границы

#### Пример использования:
```
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

Validator v = new Validator();

NumberSchema schema = v.number();

// Пока не вызван метод required(), null считается валидным
schema.isValid(null); // true
schema.positive().isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10) // true
schema.isValid("5"); // false
schema.isValid(-10); // false
//  Ноль - не положительное число
schema.isValid(0); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
```

### Валидация Map

`required` – требуется тип данных Map

`sizeof` – количество пар ключ-значений в объекте Map должно быть равно заданному

`shape` - проверить валидацию данных внутри Map

#### Пример использования:

```
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
```

```
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

MapSchema schema = v.map();

// shape - позволяет описывать валидацию для значений объекта Map по ключам.
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
```


