package br.com.radix.formacaojava.DTO;

import br.com.radix.formacaojava.model.Repo;
import br.com.radix.formacaojava.model.Technology;

import java.util.HashSet;
import java.util.Set;

public class TechnologyResponseDTO {

    private final Long id;

    private final String name;

    private final Set<Repo> repos = new HashSet<>();

    public TechnologyResponseDTO(Technology technology) {
        this.id = technology.getId();
        this.name = technology.getName();
        this.repos.addAll(technology.getRepos());
    }

    public static TechnologyResponseDTO generateResponse(Technology technology) {
        return new TechnologyResponseDTO(technology);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Repo> getRepos() {
        return repos;
    }
}
