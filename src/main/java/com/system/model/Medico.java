package com.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
    private int idMedico;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
}
