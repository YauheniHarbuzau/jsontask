package ru.clevertec.util;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.clevertec.entity.Person;
import ru.clevertec.util.builder.PersonTestBuilder;
import ru.clevertec.util.builder.TestEntityBuilder;
import ru.clevertec.util.entity.TestEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.clevertec.util.Converter.jsonToObj;
import static ru.clevertec.util.Converter.objToJson;

class ConverterTest {

    @Test
    void checkObjToJson() {
        Gson gson = new Gson();

        Person person = PersonTestBuilder.aPerson()
                .withId(30L)
                .withName("Ivan")
                .withLastName("Ivanov")
                .withAge(40)
                .withGender('M')
                .build();
        String personJson1 = "{\"id\":30,\"name\":\"Ivan\",\"lastName\":\"Ivanov\",\"age\":40,\"gender\":\"M\"}";
        String personJson2 = gson.toJson(person);

        List<Integer> intList = new ArrayList<>();
        intList.add(4);
        intList.add(2);
        Map<Long, Integer> longIntMap = new HashMap<>();
        longIntMap.put(1L, 40);
        longIntMap.put(2L, 30);
        TestEntity entity = TestEntityBuilder.aTestEntity()
                .withIntArray(new int[]{34, 20})
                .withStrArray(new String[]{"Hello", "World"})
                .withIntList(intList)
                .withLongIntMap(longIntMap)
                .build();
        String entityJson = gson.toJson(entity);

        assertAll(
                () -> assertEquals(personJson1, objToJson(person)),
                () -> assertEquals(personJson2, objToJson(person)),
                () -> assertEquals(entityJson, objToJson(entity))
        );
    }

    @Test
    void checkJsonToObj() {
        Gson gson = new Gson();

        String personJson = "{\"id\":40,\"name\":\"Petr\",\"lastName\":\"Ivanov\",\"age\":20,\"gender\":\"M\"}";
        Person person = new Person();
        jsonToObj(person, personJson);
        Person personGson = gson.fromJson(personJson, Person.class);

        assertAll(
                () -> assertEquals(personGson.getId(), person.getId()),
                () -> assertEquals(personGson.getName(), person.getName()),
                () -> assertEquals(personGson.getLastName(), person.getLastName()),
                () -> assertEquals(personGson.getAge(), person.getAge()),
                () -> assertEquals(personGson.getGender(), person.getGender())
        );
    }
}
