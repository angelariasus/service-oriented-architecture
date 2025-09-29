package com.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    private int idCita;
    private int idPaciente;
    private int idMedico;
    private Date fecha;
    private String hora;
    private String estado;
}
