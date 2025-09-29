package com.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {
    private int idMedicamento;
    private String nombre;
    private int stock;
    private double precio;
}
