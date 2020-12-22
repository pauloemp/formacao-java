package br.com.radix.formacaojava.DTO;

import br.com.radix.formacaojava.model.Repo;
import br.com.radix.formacaojava.model.Technology;

import java.util.HashSet;
import java.util.Set;

public class RepoCreateRequestDTO {

    private String title;

    private String url;

    private final Set<Technology> technologies = new HashSet<>();

    public Repo generateRepo() {
        Repo repo = new Repo(this.title, this.url);
        repo.setTechnologies(this.technologies);
        return repo;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Set<Technology> getTechnologies() {
        return technologies;
    }
}
