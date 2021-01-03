package br.com.radix.formacaojava.repository;

import br.com.radix.formacaojava.model.Repo;

import java.util.List;

public interface RepoRepository {
    List<Repo> findAll();
    Repo findByIdOrFail(Long id);
    void checkIfUrlIsAvailableOrFail(String url);
    void checkIfUrlIsAvailableOrFail(String url, String ignoredUrl);
    Repo save(Repo repo);
    void delete(Repo repo);
}

