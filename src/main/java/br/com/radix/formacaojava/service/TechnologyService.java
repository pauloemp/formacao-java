package br.com.radix.formacaojava.service;

import br.com.radix.formacaojava.model.Technology;

import java.util.List;
import java.util.Set;

public interface TechnologyService {
    List<Technology> list();

    Technology get(Long id);

    Set<Technology> findOrCreateMany(Set<Technology> technologies);
}
