package com.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    private int idFactura;
    private int idPaciente;
    private double monto;
    private Date fecha;
    private String estado;
}
