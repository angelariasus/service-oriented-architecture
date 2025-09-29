package com.system.controller;

import com.system.model.Cita;
import com.system.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<Cita> listarCitas() throws SQLException {
        return citaService.listarCitas();
    }

    @GetMapping("/{id}")
    public Cita obtenerCita(@PathVariable int id) throws SQLException {
        return citaService.obtenerCitaPorId(id);
    }

    @PostMapping
    public void registrarCita(@RequestBody Cita cita) throws SQLException {
        citaService.registrarCita(cita);
    }

    @PutMapping
    public void actualizarCita(@RequestBody Cita cita) throws SQLException {
        citaService.actualizarCita(cita);
    }

    @DeleteMapping("/{id}")
    public void cancelarCita(@PathVariable int id) throws SQLException {
        citaService.cancelarCita(id);
    }
}
