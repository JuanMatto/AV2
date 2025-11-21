package com.example.academico.service;

import com.example.academico.entity.Aluno;
import com.example.academico.entity.Curso;
import com.example.academico.repository.AlunoRepository;
import com.example.academico.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<Aluno> findAll() { return alunoRepository.findAll(); }

    public Optional<Aluno> findById(Long id) { return alunoRepository.findById(id); }

    public Aluno create(Aluno a) { return alunoRepository.save(a); }

    public Aluno update(Long id, Aluno dto) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setMatricula(dto.getMatricula());
        return alunoRepository.save(aluno);
    }

    public void delete(Long id) { alunoRepository.deleteById(id); }

    public void matricular(Long alunoId, Long cursoId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        aluno.getCursos().add(curso);
        alunoRepository.save(aluno);
    }

    public void desmatricular(Long alunoId, Long cursoId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        aluno.getCursos().removeIf(c -> c.getId().equals(cursoId));
        alunoRepository.save(aluno);
    }
}
