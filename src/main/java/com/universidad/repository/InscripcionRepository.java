package com.universidad.repository;

import com.universidad.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    // Obtiene todas las inscripciones de un estudiante
    List<Inscripcion> findAllByEstudianteId(Long idEstudiante);

    // Verifica si un estudiante ya está inscrito en una materia
    boolean existsByEstudianteIdAndMateriaId(Long idEstudiante, Long idMateria);

}
