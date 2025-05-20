package com.universidad.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscripcionDTO {
    private Long id; // ID de inscripción
    private Long idEstudiante; // ID del estudiante asociado
    private Long idMateria;    // ID de la materia asociada
    private LocalDate fechaInscripcion; // Fecha de inscripción
    private String estado; // Estado de la inscripción
}