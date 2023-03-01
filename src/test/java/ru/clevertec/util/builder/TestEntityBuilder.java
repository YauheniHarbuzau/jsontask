package ru.clevertec.util.builder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import ru.clevertec.util.entity.TestEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aTestEntity")
@With
public class TestEntityBuilder implements TestBuilder<TestEntity> {

    private int[] intArray = new int[]{0};
    private String[] strArray = new String[]{""};
    private List<Integer> intList = new ArrayList<>();
    private Map<Long, Integer> longIntMap = new HashMap<>();

    @Override
    public TestEntity build() {
        final var testEntity = new TestEntity();
        testEntity.setIntArray(intArray);
        testEntity.setStrArray(strArray);
        testEntity.setIntList(intList);
        testEntity.setLongIntMap(longIntMap);
        return testEntity;
    }
}
