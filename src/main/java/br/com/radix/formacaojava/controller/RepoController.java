package br.com.radix.formacaojava.controller;

import br.com.radix.formacaojava.DTO.RepoCreateRequestDTO;
import br.com.radix.formacaojava.DTO.RepoListResponseDTO;
import br.com.radix.formacaojava.DTO.RepoResponseDTO;
import br.com.radix.formacaojava.DTO.RepoUpdateRequestDTO;
import br.com.radix.formacaojava.model.Repo;
import br.com.radix.formacaojava.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/repositories")
public class RepoController {

    @Autowired
    private RepoService service;

    @GetMapping
    public ResponseEntity<List<RepoListResponseDTO>> list() {
        return new ResponseEntity<>(
                service.list().stream().map(RepoListResponseDTO::generateResponse).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepoResponseDTO> get(@PathVariable Long id) {
        return new ResponseEntity<>(
                RepoResponseDTO.generateResponse(service.get(id)),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<RepoResponseDTO> create(@RequestBody RepoCreateRequestDTO request) {
        Repo repo = request.generateRepo();
        return new ResponseEntity<>(
                RepoResponseDTO.generateResponse(service.create(repo)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepoResponseDTO> update(@PathVariable Long id, @RequestBody RepoUpdateRequestDTO request) {
        Repo repo = request.generateRepo();
        return new ResponseEntity<>(
                RepoResponseDTO.generateResponse(service.update(id, repo)),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
