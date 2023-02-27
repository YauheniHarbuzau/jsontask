package ru.clevertec.util.builder;

import ru.clevertec.util.entity.Person;

public class PersonTestBuilder implements TestBuilder<Person> {

    private Long id = 0L;
    private String name = "";
    private String lastName = "";
    private Integer age = 0;
    private Character gender = 'M';

    private PersonTestBuilder() {
    }

    public static PersonTestBuilder aPerson() {
        return new PersonTestBuilder();
    }

    public PersonTestBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PersonTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PersonTestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonTestBuilder withAge(Integer age) {
        this.age = age;
        return this;
    }

    public PersonTestBuilder withGender(Character gender) {
        this.gender = gender;
        return this;
    }

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
