package br.com.radix.formacaojava.repository;

import br.com.radix.formacaojava.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TechnologyRepository {
    List<Technology> findAll();
    Technology findByIdOrFail(Long id);
    Optional<Technology> findByName(String name);
    Technology save(Technology technology);
}

interface JpaTechnologyRepository extends JpaRepository<Technology, Long> {
    List<Technology> findByName(String name);
}