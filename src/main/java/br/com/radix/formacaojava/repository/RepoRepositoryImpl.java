package br.com.radix.formacaojava.repository;

import br.com.radix.formacaojava.model.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public class RepoRepositoryImpl implements RepoRepository{

    @Autowired
    private JpaRepoRepository jpaRepository;

    public List<Repo> findAll() {
        return this.jpaRepository.findAll();
    };

    public Repo findByIdOrFail(Long id) {
        Optional<Repo> repo = this.jpaRepository.findById(id);
        repo.orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Required repository not found"
        ));
        return repo.get();
    };

    public void checkIfUrlIsAvailableOrFail(String url, String ignoredUrl) {
        List<Repo> repos = this.jpaRepository.findByUrl(url);

        if (repos.isEmpty()) return;

        if (!ignoredUrl.equals(repos.get(0).getUrl())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "A repository with this URL already exists");
        }
    };

    public void checkIfUrlIsAvailableOrFail(String url) {
        this.checkIfUrlIsAvailableOrFail(url, "");
    };

    public Repo save(Repo repo) {
        return this.jpaRepository.save(repo);
    }

    public void delete(Repo repo) {
        this.jpaRepository.delete(repo);
    }
}
