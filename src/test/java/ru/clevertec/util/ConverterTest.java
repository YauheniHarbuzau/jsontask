package ru.clevertec.util;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.clevertec.util.entity.Chupacabra;
import ru.clevertec.util.entity.Person;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.clevertec.util.builder.ChupacabraTestBuilder.aChupacabra;
import static ru.clevertec.util.builder.PersonTestBuilder.aPerson;
import static ru.clevertec.util.util.Converter.jsonToObj;
import static ru.clevertec.util.util.Converter.objToJson;

class ConverterTest {

    @Test
    void checkObjToJson() {
        Gson gson = new Gson();

        Chupacabra chupacabra = aChupacabra().withId(1L).withName("Chu-Chu").build();
        String chuJson1 = "{\"id\":1,\"name\":\"Chu-Chu\"}";
        String chuJson2 = gson.toJson(chupacabra);

        Person person = aPerson().withId(30L).withName("Ivan").withLastName("Ivanov").withAge(40).withGender('M').build();
        String personJson1 = "{\"id\":30,\"name\":\"Ivan\",\"lastName\":\"Ivanov\",\"age\":40,\"gender\":\"M\"}";
        String personJson2 = gson.toJson(person);

        assertAll(
                () -> assertEquals(chuJson1, objToJson(chupacabra)),
                () -> assertEquals(chuJson2, objToJson(chupacabra)),
                () -> assertEquals(personJson1, objToJson(person)),
                () -> assertEquals(personJson2, objToJson(person))
        );
    }

    @Test
    void checkJsonToObj() {
        Gson gson = new Gson();

        String chuJson = "{\"id\":3,\"name\":\"Chupa-Chupa\"}";
        Chupacabra chupacabra = new Chupacabra();
        jsonToObj(chupacabra, chuJson);
        Chupacabra chupacabraGson = gson.fromJson(chuJson, Chupacabra.class);

        String personJson = "{\"id\":40,\"name\":\"Petr\",\"lastName\":\"Ivanov\",\"age\":20,\"gender\":\"M\"}";
        Person person = new Person();
        jsonToObj(person, personJson);
        Person personGson = gson.fromJson(personJson, Person.class);

        assertAll(
                () -> assertEquals(chupacabraGson.getId(), chupacabra.getId()),
                () -> assertEquals(chupacabraGson.getName(), chupacabra.getName()),
                () -> assertEquals(personGson.getId(), person.getId()),
                () -> assertEquals(personGson.getName(), person.getName()),
                () -> assertEquals(personGson.getLastName(), person.getLastName()),
                () -> assertEquals(personGson.getAge(), person.getAge()),
                () -> assertEquals(personGson.getGender(), person.getGender())
        );
    }
}
