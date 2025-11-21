package com.example.academico.controller;

import com.example.academico.entity.Aluno;
import com.example.academico.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService service;
    public AlunoController(AlunoService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) { return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno dto) {
        Aluno created = service.create(dto);
        return ResponseEntity.created(URI.create("/alunos/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno dto) { return ResponseEntity.ok(service.update(id, dto)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }

    @PostMapping("/{alunoId}/cursos/{cursoId}")
    public ResponseEntity<Void> matricular(@PathVariable Long alunoId, @PathVariable Long cursoId) { service.matricular(alunoId, cursoId); return ResponseEntity.ok().build(); }

    @DeleteMapping("/{alunoId}/cursos/{cursoId}")
    public ResponseEntity<Void> desmatricular(@PathVariable Long alunoId, @PathVariable Long cursoId) { service.desmatricular(alunoId, cursoId); return ResponseEntity.ok().build(); }
}
