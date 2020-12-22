package br.com.radix.formacaojava.repository;

import br.com.radix.formacaojava.model.Technology;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TechnologyRepositoryTest {

    @InjectMocks
    private TechnologyRepositoryImpl repository;

    @Mock
    private JpaTechnologyRepository jpaRepository;

    @Test
    public void findByIdTestOk() {
        Technology fakeTech = new Technology("FakeTech");
        Long fakeId = 1L;
        when(jpaRepository.findById(fakeId)).thenReturn(Optional.of(fakeTech));

        Technology technology = repository.findByIdOrFail(fakeId);

        Assertions.assertEquals(fakeTech, technology);
    }

    @Test
    public void findByIdTestFail() {
        Long fakeId = 1L;
        when(jpaRepository.findById(fakeId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> repository.findByIdOrFail(fakeId));
    }
}
