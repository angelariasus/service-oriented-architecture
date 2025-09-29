package com.system.controller;

import com.system.model.Medicamento;
import com.system.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping
    public List<Medicamento> listarMedicamentos() throws SQLException {
        return medicamentoService.listarMedicamentos();
    }

    @GetMapping("/{id}")
    public Medicamento obtenerMedicamento(@PathVariable int id) throws SQLException {
        return medicamentoService.obtenerMedicamentoPorId(id);
    }

    @PostMapping
    public void registrarMedicamento(@RequestBody Medicamento medicamento) throws SQLException {
        medicamentoService.registrarMedicamento(medicamento);
    }

    @PutMapping
    public void actualizarMedicamento(@RequestBody Medicamento medicamento) throws SQLException {
        medicamentoService.actualizarMedicamento(medicamento);
    }

    @DeleteMapping("/{id}")
    public void eliminarMedicamento(@PathVariable int id) throws SQLException {
        medicamentoService.eliminarMedicamento(id);
    }
}
