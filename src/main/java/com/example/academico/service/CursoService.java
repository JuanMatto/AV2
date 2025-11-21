package com.example.academico.service;

import com.example.academico.entity.Curso;
import com.example.academico.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CursoService {
    private final CursoRepository cursoRepository;
    public CursoService(CursoRepository cursoRepository) { this.cursoRepository = cursoRepository; }
    public List<Curso> findAll() { return cursoRepository.findAll(); }
    public Optional<Curso> findById(Long id) { return cursoRepository.findById(id); }
    public Curso create(Curso c) { return cursoRepository.save(c); }
    public Curso update(Long id, Curso dto) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        curso.setNome(dto.getNome());
        curso.setCargaHoraria(dto.getCargaHoraria());
        return cursoRepository.save(curso);
    }
    public void delete(Long id) { cursoRepository.deleteById(id); }
}
