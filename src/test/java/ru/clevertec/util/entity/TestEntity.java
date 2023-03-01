package ru.clevertec.util.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TestEntity {

    private int[] intArray;
    private String[] strArray;
    private List<Integer> intList;
    private Map<Long, Integer> longIntMap;
}
