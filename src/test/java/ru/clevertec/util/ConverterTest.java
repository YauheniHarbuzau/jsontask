package ru.clevertec.util;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.clevertec.entity.Person;
import ru.clevertec.util.builder.PersonTestBuilder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.clevertec.util.Converter.jsonToObj;
import static ru.clevertec.util.Converter.objToJson;

class ConverterTest {

    @Test
    void checkObjToJson() {
        Gson gson = new Gson();

        Person person = PersonTestBuilder.aPerson().withId(30L).withName("Ivan").withLastName("Ivanov").withAge(40).withGender('M').build();
        String personJson1 = "{\"id\":30,\"name\":\"Ivan\",\"lastName\":\"Ivanov\",\"age\":40,\"gender\":\"M\"}";
        String personJson2 = gson.toJson(person);

        assertAll(
                () -> assertEquals(personJson1, objToJson(person)),
                () -> assertEquals(personJson2, objToJson(person))
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
