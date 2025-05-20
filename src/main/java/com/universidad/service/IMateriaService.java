package com.universidad.service;

import com.universidad.dto.MateriaDTO;
import com.universidad.model.Materia;
import jakarta.transaction.Transactional;

import java.util.List;

public interface IMateriaService {
    List<MateriaDTO> obtenerTodasLasMaterias();
    MateriaDTO obtenerMateriaPorId(Long id);
    MateriaDTO obtenerMateriaPorCodigoUnico(String codigoUnico);
    MateriaDTO crearMateria(MateriaDTO materia);
    MateriaDTO actualizarMateria(Long id, MateriaDTO materia);
    void eliminarMateria(Long id);

    @Transactional
    void asignarDocente(Long idMateria, Long idDocente);

    @Transactional
    void removerDocente(Long idMateria, Long idDocente);
}
