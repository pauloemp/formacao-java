package br.com.radix.formacaojava.repository;

import br.com.radix.formacaojava.model.Technology;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TechnologyRepositoryTest {

    @InjectMocks
    private TechnologyRepositoryImpl repository;

    @Mock
    private JpaTechnologyRepository jpaRepository;

    @Test
    public void findAllTestOk() {
        Technology fakeTech1 = new Technology("FakeTech1");
        Technology fakeTech2 = new Technology("FakeTech1");
        List<Technology> fakeTechs = Arrays.asList(fakeTech1,fakeTech2);
        when(jpaRepository.findAll()).thenReturn(fakeTechs);

        List<Technology> techs = repository.findAll();

        Assertions.assertEquals(fakeTechs, techs);
    }

    @Test
    public void findByIdTestOk() {
        Technology fakeTech = new Technology("FakeTech");
        Long fakeId = 1L;
        when(jpaRepository.findById(fakeId)).thenReturn(Optional.of(fakeTech));

        Technology tech = repository.findByIdOrFail(fakeId);

        Assertions.assertEquals(fakeTech, tech);
    }

    @Test
    public void findByIdTestFail() {
        Long fakeId = 1L;
        when(jpaRepository.findById(fakeId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> repository.findByIdOrFail(fakeId));
    }

    @Test
    public void findByNameTestOk() {
        String fakeName = "ExistingTech";
        Technology fakeTech = new Technology(fakeName);
        when(jpaRepository.findByName(fakeName)).thenReturn(Collections.singletonList(fakeTech));

        Optional<Technology> tech = repository.findByName(fakeName);

        Assertions.assertEquals(Optional.of(fakeTech), tech);
    }

    @Test
    public void findByNameTestNotFound() {
        String fakeName = "NotExistingTech";
        when(jpaRepository.findByName(fakeName)).thenReturn(Collections.emptyList());

        Optional<Technology> tech = repository.findByName(fakeName);

        Assertions.assertEquals(Optional.empty(), tech);
    }

    @Test
    public void saveTestOk() {
        Technology fakeTech = new Technology("TestTech");

        repository.save(fakeTech);

        verify(jpaRepository).save(fakeTech);
    }
}
