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

import static org.mockito.Mockito.verify;
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
    public void createTest() {
        Repo fakeRepo = new Repo("TestRepo", "https://myrepo.com");
        Technology fakeTech1 = new Technology("Tech1");
        Technology fakeTech2 = new Technology("Tech2");
        Set<Technology> fakeTechs = new HashSet<>(Arrays.asList(fakeTech1, fakeTech2));
        fakeRepo.setTechnologies(fakeTechs);

        when(technologyService.findOrCreateMany(fakeRepo.getTechnologies())).thenReturn(fakeTechs);
        when(repository.save(fakeRepo)).thenReturn(fakeRepo);

        Repo repo = service.create(fakeRepo);

        verify(repository).checkIfUrlIsAvailableOrFail(fakeRepo.getUrl());
        Assertions.assertEquals(fakeRepo, repo);
    }

    @Test
    public void updateTest() {
        Long fakeId = 1L;
        Repo fakeRepo = new Repo("TestRepo", "https://myrepo.com");
        Technology fakeTech1 = new Technology("Tech1");
        Technology fakeTech2 = new Technology("Tech2");
        Set<Technology> fakeTechs = new HashSet<>(Arrays.asList(fakeTech1, fakeTech2));
        fakeRepo.setTechnologies(fakeTechs);

        when(repository.findByIdOrFail(fakeId)).thenReturn(fakeRepo);
        when(technologyService.findOrCreateMany(fakeRepo.getTechnologies())).thenReturn(fakeTechs);
        when(repository.save(fakeRepo)).thenReturn(fakeRepo);

        Repo repo = service.update(fakeId, fakeRepo);

        verify(repository).checkIfUrlIsAvailableOrFail(fakeRepo.getUrl(), fakeRepo.getUrl());
        Assertions.assertEquals(fakeRepo, repo);
    }

    @Test
    public void deleteTest() {
        Long fakeId = 1L;
        Repo fakeRepo = new Repo("TestRepo", "https://myrepo.com");
        when(repository.findByIdOrFail(fakeId)).thenReturn(fakeRepo);

        service.delete(fakeId);

        verify(repository).delete(fakeRepo);
    }
}
