package com.universidad.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "docente") // Nombre de la tabla en la base de datos  
public class Docente extends Persona {
    @Column(name = "nro_empleado", nullable = false, unique = true) // Columna no nula y con valor único    
    private String nroEmpleado;

    @Column(name = "departamento", nullable = false) // Columna no nula
    private String departamento;
}    
