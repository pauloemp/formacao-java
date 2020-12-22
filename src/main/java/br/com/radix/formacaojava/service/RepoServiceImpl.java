package br.com.radix.formacaojava.service;

import br.com.radix.formacaojava.model.Repo;
import br.com.radix.formacaojava.model.Technology;
import br.com.radix.formacaojava.repository.RepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class RepoServiceImpl implements RepoService{

    @Autowired
    private RepoRepository repository;

    @Autowired
    private TechnologyService technologyService;

    public List<Repo> list() {
        return repository.findAll();
    }

    public Repo get(Long id) {
        return repository.findByIdOrFail(id);
    }

    @Transactional
    public Repo create(Repo repo) {
        this.repository.checkIfUrlIsAvailableOrFail(repo.getUrl());

        Set<Technology> techs = this.technologyService.findOrCreateMany(repo.getTechnologies());

        repo.setTechnologies(techs);

        return this.repository.save(repo);
    }

    @Transactional
    public Repo update(Long id, Repo repo) {
        Repo foundRepo = this.repository.findByIdOrFail(id);

        this.repository.checkIfUrlIsAvailableOrFail(repo.getUrl(), foundRepo.getUrl());

        Set<Technology> techs = this.technologyService.findOrCreateMany(repo.getTechnologies());

        repo.setTechnologies(techs);

        foundRepo.updateFieldsAs(repo);

        return this.repository.save(foundRepo);

    }

    @Transactional
    public void delete(Long id) {
        Repo repo = this.repository.findByIdOrFail(id);
        this.repository.delete(repo);
    }
}
