package br.com.radix.formacaojava.controller;

import br.com.radix.formacaojava.DTO.TechnologyListResponseDTO;
import br.com.radix.formacaojava.DTO.TechnologyResponseDTO;
import br.com.radix.formacaojava.service.TechnologyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {
    @Autowired
    private TechnologyService service;

    @GetMapping
    public ResponseEntity<List<TechnologyListResponseDTO>> list() {
        return new ResponseEntity<>(
                service.list().stream().map(TechnologyListResponseDTO::generateResponse).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnologyResponseDTO> get(@PathVariable Long id) {
        return new ResponseEntity<>(
                TechnologyResponseDTO.generateResponse(service.get(id)),
                HttpStatus.OK
        );
    }
}
