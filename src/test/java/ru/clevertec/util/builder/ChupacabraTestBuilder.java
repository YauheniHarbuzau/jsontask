package ru.clevertec.util.builder;

import ru.clevertec.util.entity.Chupacabra;

public class ChupacabraTestBuilder implements TestBuilder<Chupacabra> {

    private Long id = 0L;
    private String name = "";

    private ChupacabraTestBuilder() {
    }

    public static ChupacabraTestBuilder aChupacabra() {
        return new ChupacabraTestBuilder();
    }

    public ChupacabraTestBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ChupacabraTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Chupacabra build() {
        final var chupacabra = new Chupacabra();
        chupacabra.setId(id);
        chupacabra.setName(name);
        return chupacabra;
    }
}
