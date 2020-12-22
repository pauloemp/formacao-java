package br.com.radix.formacaojava.repository;

import br.com.radix.formacaojava.model.Repo;
import br.com.radix.formacaojava.model.Technology;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepoRepositoryTest {

    @InjectMocks
    private RepoRepositoryImpl repository;

    @Mock
    private JpaRepoRepository jpaRepository;

    @Test
    public void findByIdTestOk() {
        Repo fakeRepo = new Repo("TestRepo", "https://myrepo.com");
        Long fakeId = 1L;
        when(jpaRepository.findById(fakeId)).thenReturn(Optional.of(fakeRepo));

        Repo repo = repository.findByIdOrFail(fakeId);

        Assertions.assertEquals(fakeRepo, repo);
    }

    @Test
    public void findByIdTestFail() {
        Long fakeId = 1L;
        when(jpaRepository.findById(fakeId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> repository.findByIdOrFail(fakeId));
    }

    @Test
    public void checkIfUrlIsAvailableTestOk() {
        String fakeUrl = "https://myrepo.com";
        when(jpaRepository.findByUrl(fakeUrl)).thenReturn(Collections.emptyList());

        Assertions.assertDoesNotThrow(() -> repository.checkIfUrlIsAvailableOrFail(fakeUrl));
    }

    @Test
    public void checkIfUrlIsAvailableTestFail() {
//        String fakeUrl = "https://myrepo.com";
//        Repo fakeRepoNew = new Repo("TestRepo", "https://myrepo1.com");
//        Long fakeId = 1L;
//
//        Technology fakeTech1 = new Technology("Tech1");
//        Technology fakeTech2 = new Technology("Tech2");
//        Set<Technology> fakeTechs = new HashSet<>(Arrays.asList(fakeTech1, fakeTech2));
//        fakeRepo.setTechnologies(fakeTechs);
//
//        when(repository.findByIdOrFail(fakeId)).thenReturn(fakeRepo);
//        when(technologyService.findOrCreateMany(fakeRepo.getTechnologies())).thenReturn(fakeTechs);
//
//        Repo repo = service.create(fakeRepo);
//
//        Assertions.assertEquals(fakeRepo, repo);Repo fakeRepo = new Repo("TestRepo", fakeUrl);
//        when(jpaRepository.findByUrl(fakeUrl)).thenReturn(Collections.singletonList(fakeRepo));
//
//        Assertions.assertThrows(ResponseStatusException.class, () -> repository.checkIfUrlIsAvailableOrFail(fakeUrl));
    }

    @Test
    public void checkIfUrlIsAvailableTestOkWithIgnoredUrl() {
        String fakeUrl = "https://myrepo.com";
        Repo fakeRepo = new Repo("TestRepo", fakeUrl);
        when(jpaRepository.findByUrl(fakeUrl)).thenReturn(Collections.singletonList(fakeRepo));

        Assertions.assertDoesNotThrow(() -> repository.checkIfUrlIsAvailableOrFail(fakeUrl, fakeUrl));
    }

    @Test
    public void saveTestOk() {
        Repo fakeRepo = new Repo("TestRepo", "https://myrepo.com");
        when(jpaRepository.save(fakeRepo)).thenReturn(fakeRepo);

        Assertions.assertDoesNotThrow(() -> repository.save(fakeRepo));
    }
}
