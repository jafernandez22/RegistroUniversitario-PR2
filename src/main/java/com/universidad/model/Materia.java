package com.universidad.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter // Genera un getter para todos los campos de la clase
@Setter // Genera un setter para todos los campos de la clase
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "materia") // Nombre de la tabla en la base de datos
// Esta clase representa una materia en el sistema de gestión de estudiantes
public class Materia implements Serializable {
    
    private static final long serialVersionUID = 1L;

    // Constructor to match the required signature
    public Materia(Long id, String nombreMateria, String codigoUnico) {
        this.id = id;
        this.nombreMateria = nombreMateria;
        this.codigoUnico = codigoUnico;
    }


    @Id // Anotación que indica que este campo es la clave primaria
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) // Generación automática del ID
    @Column(name = "id_materia") // Nombre de la columna en la base de datos
    // El ID de la materia es generado automáticamente por la base de datos
    private Long id;

    @Column(name = "nombre_materia", nullable = false, length = 100) // Columna no nula con longitud máxima de 100 caracteres
    // El nombre de la materia no puede ser nulo y tiene una longitud máxima de 100 caracteres
    private String nombreMateria;

    @Column(name = "codigo_unico", nullable = false, unique = true) // Columna no nula y con valor único
    // El código único de la materia no puede ser nulo y debe ser único en la base de datos
    private String codigoUnico;

    @Column(name = "creditos", nullable = false) // Columna no nula
    // El número de créditos de la materia no puede ser nulo
    private Integer creditos;

    @Version
    @Column(nullable = false, columnDefinition = "bigint default 1")
    private Long version = 1L; // Inicialización explícita

    @PrePersist
    public void initializeVersion() {
        if (this.version == null) {
            this.version = 1L;
        }
    }
    /**
     * Lista de materias que son prerequisitos para esta materia.
     */
    @ManyToMany
    @JoinTable(
        name = "materia_prerequisito",
        joinColumns = @JoinColumn(name = "id_materia"),
        inverseJoinColumns = @JoinColumn(name = "id_prerequisito") // Nombre de la columna en la tabla inversa
    )
    private List<Materia> prerequisitos;

    /**
     * Lista de materias para las que esta materia es prerequisito.
     */
    @ManyToMany(mappedBy = "prerequisitos")
    private List<Materia> esPrerequisitoDe;

    /**
     * Verifica si agregar la materia con el ID dado como prerequisito formaría un ciclo.
     * @param prerequisitoId ID de la materia candidata a prerequisito
     * @return true si se formaría un ciclo, false en caso contrario
     */
    public boolean formariaCirculo(Long prerequisitoId) {
        return formariaCirculoRecursivo(this.getId(), prerequisitoId, new java.util.HashSet<>());
    }

    // Método auxiliar recursivo para detectar ciclos
    private boolean formariaCirculoRecursivo(Long objetivoId, Long actualId, java.util.Set<Long> visitados) {
        if (objetivoId == null || actualId == null) return false;
        if (objetivoId.equals(actualId)) return true;
        if (!visitados.add(actualId)) return false;
        if (this.getPrerequisitos() == null) return false;
        for (Materia prereq : this.getPrerequisitos()) { // Itera sobre los prerequisitos de la materia
            if (prereq != null && prereq.getId() != null && prereq.getId().equals(actualId)) { // Verifica si el prerequisito actual es el objetivo
                if (prereq.getPrerequisitos() != null) { // Verifica si tiene prerequisitos
                    // Si el prerequisito tiene prerequisitos, verifica recursivamente si alguno de ellos forma un ciclo
                    for (Materia subPrereq : prereq.getPrerequisitos()) { // Itera sobre los prerequisitos del prerequisito actual
                        if (formariaCirculoRecursivo(objetivoId, subPrereq.getId(), visitados)) { // Llama recursivamente al método
                            // Si se encuentra un ciclo, retorna true
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @ManyToMany
    @JoinTable(
            name = "docente_materia",
            joinColumns = @JoinColumn(name = "id_materia"),
            inverseJoinColumns = @JoinColumn(name = "id_docente")
    )
    @Builder.Default
    private Set<Docente> docente = new HashSet<>();

    // Métodos auxiliares
    public void agregarDocente(Docente docente) {
        this.docente.add(docente);
        docente.getMaterias().add(this);
    }

    public void removerDocente(Docente docente) {
        this.docente.remove(docente);
        docente.getMaterias().remove(this);
    }

}