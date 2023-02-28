package ru.clevertec.util.builder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import ru.clevertec.entity.Person;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aPerson")
@With
public class PersonTestBuilder implements TestBuilder<Person> {

    private Long id = 0L;
    private String name = "";
    private String lastName = "";
    private Integer age = 0;
    private Character gender = 'M';

    @Override
    public Person build() {
        final var person = new Person();
        person.setId(id);
        person.setName(name);
        person.setLastName(lastName);
        person.setAge(age);
        person.setGender(gender);
        return person;
    }
}
