package com.system.controller;

import com.system.model.Medico;
import com.system.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<Medico> listarMedicos() throws SQLException {
        return medicoService.listarMedicos();
    }

    @GetMapping("/{id}")
    public Medico obtenerMedico(@PathVariable int id) throws SQLException {
        return medicoService.obtenerMedicoPorId(id);
    }

    @PostMapping
    public void registrarMedico(@RequestBody Medico medico) throws SQLException {
        medicoService.registrarMedico(medico);
    }

    @PutMapping
    public void actualizarMedico(@RequestBody Medico medico) throws SQLException {
        medicoService.actualizarMedico(medico);
    }

    @DeleteMapping("/{id}")
    public void eliminarMedico(@PathVariable int id) throws SQLException {
        medicoService.eliminarMedico(id);
    }
}
