package br.com.radix.formacaojava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "repos")
public class Repo {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String url;

    @Column
    private Long likes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "repos_technologies",
            joinColumns = @JoinColumn(name = "repo_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    @JsonIgnore
    private final Set<Technology> technologies = new HashSet<>();

    public Repo() {
    }

    public Repo(String title, String url) {
        this.title = title;
        this.url = url;
        this.likes = 0L;
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
        return this.technologies;
    }

    public void setTechnologies(Set<Technology> technologies) {
        this.technologies.clear();
        this.technologies.addAll(technologies);
    }

    public void beLiked() {
        this.likes += 1;
    }

    public void updateFieldsAs (Repo repo) {
        this.title = repo.getTitle();
        this.url = repo.getUrl();
        this.setTechnologies(repo.getTechnologies());
    }
}
