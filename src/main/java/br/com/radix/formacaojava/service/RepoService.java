package br.com.radix.formacaojava.service;

import br.com.radix.formacaojava.model.Repo;

import java.util.List;

public interface RepoService {
    List<Repo> list();

    Repo get(Long id);

    Repo create(Repo repo);

    Repo update(Long id, Repo repo);

    void delete(Long id);
}
