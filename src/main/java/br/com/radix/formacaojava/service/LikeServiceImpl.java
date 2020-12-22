package br.com.radix.formacaojava.service;

import br.com.radix.formacaojava.model.Repo;
import br.com.radix.formacaojava.repository.RepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private RepoRepository repository;

    @Transactional
    public Repo like(Long id) {
        Repo repo = this.repository.findByIdOrFail(id);

        repo.beLiked();

        return this.repository.save(repo);
    }
}
