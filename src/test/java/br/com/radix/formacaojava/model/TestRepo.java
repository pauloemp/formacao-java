package br.com.radix.formacaojava.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRepo {

    @Test
    public void beLiked() {  // Should be able to increment Repo likes by one
        Repo repo = new Repo("TestRepo", "https://myrepo.com");

        Long likes = repo.getLikes();

        repo.beLiked();

        Assertions.assertEquals(likes + 1, repo.getLikes());
    }
}
