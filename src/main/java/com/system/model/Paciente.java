package com.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    private int idPaciente;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaNacimiento;
    private String direccion;
    private String telefono;
}
