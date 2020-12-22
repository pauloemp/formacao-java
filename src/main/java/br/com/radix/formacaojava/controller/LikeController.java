package br.com.radix.formacaojava.controller;

import br.com.radix.formacaojava.DTO.RepoResponseDTO;
import br.com.radix.formacaojava.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repositories/{id}/like")
public class LikeController {

    @Autowired
    private LikeService service;

    @PostMapping
    public ResponseEntity<RepoResponseDTO> create(@PathVariable Long id) {
        return new ResponseEntity<>(
                RepoResponseDTO.generateResponse(service.like(id)),
                HttpStatus.CREATED
        );
    }
}
