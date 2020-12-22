package br.com.radix.formacaojava.service;

import br.com.radix.formacaojava.model.Repo;
import br.com.radix.formacaojava.model.Technology;
import br.com.radix.formacaojava.repository.RepoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepoServiceTest {

    @InjectMocks
    private RepoServiceImpl service;

    @Mock
    private RepoRepository repository;

    @Mock
    private TechnologyService technologyService;

    @Test
    public void listTest() {
        Repo fakeRepo1 = new Repo("TestRepo1", "https://myrepo1.com");
        Repo fakeRepo2 = new Repo("TestRepo2", "https://myrepo2.com");
        Repo fakeRepo3 = new Repo("TestRepo3", "https://myrepo3.com");
        List<Repo> allFakeRepos = new ArrayList<>(Arrays.asList(fakeRepo1, fakeRepo2, fakeRepo3));
        when(repository.findAll()).thenReturn(allFakeRepos);

        List<Repo> allRepos = service.list();

        Assertions.assertEquals(allFakeRepos, allRepos);
    }

    @Test
    public void getTest() {
        Repo fakeRepo = new Repo("TestRepo", "https://myrepo1.com");
        Long fakeId = 1L;
        when(repository.findByIdOrFail(fakeId)).thenReturn(fakeRepo);

        Repo repo = repository.findByIdOrFail(fakeId);

        Assertions.assertEquals(fakeRepo, repo);
    }

    @Test
    public void createTest() {
        Repo fakeRepo = new Repo("TestRepo", "https://myrepo1.com");
        Technology fakeTech1 = new Technology("Tech1");
        Technology fakeTech2 = new Technology("Tech2");
        Set<Technology> fakeTechs = new HashSet<>(Arrays.asList(fakeTech1, fakeTech2));
        fakeRepo.setTechnologies(fakeTechs);
        when(repository.save(fakeRepo)).thenReturn(fakeRepo);
        when(technologyService.findOrCreateMany(fakeRepo.getTechnologies())).thenReturn(fakeTechs);

        Repo repo = service.create(fakeRepo);

        Assertions.assertEquals(fakeRepo, repo);
    }

    @Test
    public void updateTest() {

    }
}
