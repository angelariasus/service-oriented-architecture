package com.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hospitalizacion {
    private int idHospitalizacion;
    private int idPaciente;
    private String habitacion;
    private Date fechaIngreso;
    private Date fechaAlta;
}
