package ru.clevertec;

import ru.clevertec.entity.Person;

import static ru.clevertec.util.Converter.jsonToObj;
import static ru.clevertec.util.Converter.objToJson;

public class Main {

    public static void main(String[] args) {

        Person person1 = new Person();
        jsonToObj(person1, "{\"id\":30,\"name\":\"Ivan\",\"lastName\":\"Ivanov\",\"age\":40,\"gender\":\"M\"}");
        System.out.println(person1);

        Person person2 = new Person(10L, "Ivan", "Ivanov", 40, 'M');
        String personJson = objToJson(person2);
        System.out.println(personJson);
    }
}
