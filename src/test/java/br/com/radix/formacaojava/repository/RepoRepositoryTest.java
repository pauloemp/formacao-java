package br.com.radix.formacaojava.repository;

import br.com.radix.formacaojava.model.Repo;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RepoRepositoryTest {

    @InjectMocks
    private RepoRepositoryImpl repository;

    @Mock
    private JpaRepoRepository jpaRepository;

    @Test
    public void findAllTestOk() {
        Repo fakeRepo1 = new Repo("TestRepo", "https://myrepo.com");
        Repo fakeRepo2 = new Repo("TestRepo", "https://myrepo2.com");
        List<Repo> fakeRepos = Arrays.asList(fakeRepo1,fakeRepo2);
        when(jpaRepository.findAll()).thenReturn(fakeRepos);

        List<Repo> repos = repository.findAll();

        Assertions.assertEquals(fakeRepos, repos);
    }

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
        String fakeUrl = "https://myrepo.com";
        Repo fakeRepo = new Repo("TestRepo", fakeUrl);
        when(jpaRepository.findByUrl(fakeUrl)).thenReturn(Collections.singletonList(fakeRepo));

        Assertions.assertThrows(ResponseStatusException.class, () -> repository.checkIfUrlIsAvailableOrFail(fakeUrl));
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

        repository.save(fakeRepo);

        verify(jpaRepository).save(fakeRepo);
    }

    @Test
    public void deleteTestOk() {
        Repo fakeRepo = new Repo("TestRepo", "https://myrepo.com");

        repository.delete(fakeRepo);

        verify(jpaRepository).delete(fakeRepo);
    }
}
