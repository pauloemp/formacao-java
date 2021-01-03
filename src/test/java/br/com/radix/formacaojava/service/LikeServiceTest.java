package br.com.radix.formacaojava.service;

import br.com.radix.formacaojava.model.Repo;
import br.com.radix.formacaojava.repository.RepoRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LikeServiceTest {

    @InjectMocks
    private LikeServiceImpl service;

    @Mock
    private RepoRepository repository;

    @Test
    public void likeTest() {
        Long fakeId = 1L;
        Repo fakeRepo = Mockito.mock(Repo.class);
        when(repository.findByIdOrFail(fakeId)).thenReturn(fakeRepo);
        when(repository.save(fakeRepo)).thenReturn(fakeRepo);

        Repo repo = service.like(fakeId);

        verify(fakeRepo).beLiked();
        Assertions.assertEquals(fakeRepo, repo);
    }

}
