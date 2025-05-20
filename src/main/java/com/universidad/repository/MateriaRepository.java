package com.universidad.repository;

import com.universidad.model.Materia;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Lock;
import jakarta.persistence.LockModeType;

import java.util.List;
import java.util.Optional;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    Materia findByCodigoUnico(String codigoUnico);

    //@Lock(LockModeType.PESSIMISTIC_WRITE) // Bloqueo pesimista para evitar condiciones de carrera
    Optional<Materia> findById(Long id);

    @EntityGraph(attributePaths = {"docente"})
    List<Materia> findAll();

    @EntityGraph(attributePaths = {"docente"})
    Materia findWithDocentesById(Long id);

    // Método para obtener todas las materias ordenadas por id (ascendente)
    @EntityGraph(attributePaths = {"docente"})
    List<Materia> findAllByOrderByIdAsc();

    // Si prefieres ordenar de forma descendente:
    @EntityGraph(attributePaths = {"docente"})
    List<Materia> findAllByOrderByIdDesc();



}
