package com.system.controller;

import com.system.model.Paciente;
import com.system.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> listarPacientes() throws SQLException {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{id}")
    public Paciente obtenerPaciente(@PathVariable int id) throws SQLException {
        return pacienteService.obtenerPacientePorId(id);
    }

    @PostMapping
    public void registrarPaciente(@RequestBody Paciente paciente) throws SQLException {
        pacienteService.registrarPaciente(paciente);
    }

    @PutMapping
    public void actualizarPaciente(@RequestBody Paciente paciente) throws SQLException {
        pacienteService.actualizarPaciente(paciente);
    }

    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable int id) throws SQLException {
        pacienteService.eliminarPaciente(id);
    }
}
