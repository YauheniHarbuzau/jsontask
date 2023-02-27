package ru.clevertec.util.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Person {

    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private Character gender;
}
