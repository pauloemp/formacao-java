package br.com.radix.formacaojava.repository;

import br.com.radix.formacaojava.model.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

interface JpaTechnologyRepository extends JpaRepository<Technology, Long> {
    List<Technology> findByName(String name);
}

@Repository
public class TechnologyRepositoryImpl implements TechnologyRepository {
    @Autowired
    private JpaTechnologyRepository jpaRepository;

    public List<Technology> findAll() {
        return this.jpaRepository.findAll();
    };

    public Technology findByIdOrFail(Long id) {
        Optional<Technology> tech = this.jpaRepository.findById(id);
        tech.orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Required repository not found"
        ));
        return tech.get();
    };

    public Optional<Technology> findByName(String name) {
        List<Technology> tech = this.jpaRepository.findByName(name);
        if (tech.isEmpty()) return Optional.empty();
        return Optional.ofNullable(tech.get(0));
    };

    public Technology save(Technology technology) {
        return this.jpaRepository.save(technology);
    };
}
