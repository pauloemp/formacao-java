package br.com.radix.formacaojava.service;

import br.com.radix.formacaojava.model.Technology;
import br.com.radix.formacaojava.repository.TechnologyRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TechnologyServiceTest {

    @InjectMocks
    private TechnologyServiceImpl service;

    @Mock
    private TechnologyRepository repository;

    @Test
    public void listTest() {
        service.list();

        verify(repository).findAll();
    }

    @Test
    public void getTest() {
        Long fakeId = 1L;

        service.get(fakeId);

        verify(repository).findByIdOrFail(fakeId);
    }

    @Test
    public void findOrCreateManyTest() {
        Technology fakeTech1 = new Technology("Tech1");
        Technology fakeTech2 = new Technology("Tech2");
        Set<Technology> fakeTechs = new HashSet<>(Arrays.asList(fakeTech1, fakeTech2));

        when(repository.findByName("Tech1")).thenReturn(Optional.of(fakeTech1));
        when(repository.findByName("Tech2")).thenReturn(Optional.empty());
        when(repository.save(fakeTech2)).thenReturn(fakeTech2);

        Set<Technology> techs = service.findOrCreateMany(fakeTechs);

        Assertions.assertEquals(fakeTechs, techs);
    }
}
