package br.com.radix.formacaojava.DTO;

import br.com.radix.formacaojava.model.Technology;

public class TechnologyListResponseDTO {

    private final Long id;

    private final String name;

    public TechnologyListResponseDTO(Technology technology) {
        this.id = technology.getId();
        this.name = technology.getName();
    }

    public static TechnologyListResponseDTO generateResponse(Technology technology) {
        return new TechnologyListResponseDTO(technology);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
