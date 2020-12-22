package br.com.radix.formacaojava.DTO;

import br.com.radix.formacaojava.model.Repo;

public class RepoListResponseDTO {

    private final Long id;

    private final String title;

    private final String url;

    private final Long likes;

    public RepoListResponseDTO(Repo repo) {
        this.id = repo.getId();
        this.title = repo.getTitle();
        this.url = repo.getUrl();
        this.likes = repo.getLikes();
    }

    public static RepoListResponseDTO generateResponse(Repo repo) {
        return new RepoListResponseDTO(repo);
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
}
