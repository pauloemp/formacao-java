package br.com.radix.formacaojava.DTO;

import br.com.radix.formacaojava.model.Repo;
import br.com.radix.formacaojava.model.Technology;

import java.util.HashSet;
import java.util.Set;

public class RepoResponseDTO {

    private final Long id;

    private final String title;

    private final String url;

    private final Long likes;

    private final Set<Technology> technologies = new HashSet<>();

    public RepoResponseDTO(Repo repo) {
        this.id = repo.getId();
        this.title = repo.getTitle();
        this.url = repo.getUrl();
        this.likes = repo.getLikes();
        this.technologies.addAll(repo.getTechnologies());
    }

    public static RepoResponseDTO generateResponse(Repo repo) {
        return new RepoResponseDTO(repo);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Long getLikes() {
        return likes;
    }

    public Set<Technology> getTechnologies() {
        return technologies;
    }
}
