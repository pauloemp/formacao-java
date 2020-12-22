package br.com.radix.formacaojava.repository;

import br.com.radix.formacaojava.model.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoRepository {
    List<Repo> findAll();
    Repo findByIdOrFail(Long id);
    void checkIfUrlIsAvailableOrFail(String url);
    void checkIfUrlIsAvailableOrFail(String url, String ignoredUrl);
    Repo save(Repo repo);
    void delete(Repo repo);
}

interface JpaRepoRepository extends JpaRepository<Repo, Long> {
    List<Repo> findByUrl(String url);
}