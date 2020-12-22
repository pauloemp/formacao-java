package br.com.radix.formacaojava.service;

import br.com.radix.formacaojava.model.Technology;
import br.com.radix.formacaojava.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class TechnologyServiceImpl implements TechnologyService{

    @Autowired
    private TechnologyRepository repository;

    public List<Technology> list() {
        return repository.findAll();
    };

    public Technology get(Long id) {
        return repository.findByIdOrFail(id);
    };

    @Transactional
    public Set<Technology> findOrCreateMany(Set<Technology> technologies) {
        Set<Technology> savedTechs = new HashSet<>();

        for (Technology technology : technologies) {
            Optional<Technology> technologyAlreadyExists = this.repository.findByName(technology.getName());

            technologyAlreadyExists.ifPresentOrElse(savedTechs::add, () -> {
                Technology savedTech = this.repository.save(technology);
                savedTechs.add(savedTech);
            });
        }

        return savedTechs;
    }
}
