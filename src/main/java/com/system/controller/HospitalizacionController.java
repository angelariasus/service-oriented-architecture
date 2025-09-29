package com.system.controller;

import com.system.model.Hospitalizacion;
import com.system.service.HospitalizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/hospitalizaciones")
public class HospitalizacionController {

    @Autowired
    private HospitalizacionService hospitalizacionService;

    @GetMapping
    public List<Hospitalizacion> listarHospitalizaciones() throws SQLException {
        return hospitalizacionService.listarHospitalizaciones();
    }

    @GetMapping("/{id}")
    public Hospitalizacion obtenerHospitalizacion(@PathVariable int id) throws SQLException {
        return hospitalizacionService.obtenerHospitalizacionPorId(id);
    }

    @PostMapping
    public void registrarHospitalizacion(@RequestBody Hospitalizacion hosp) throws SQLException {
        hospitalizacionService.registrarHospitalizacion(hosp);
    }

    @PutMapping
    public void actualizarHospitalizacion(@RequestBody Hospitalizacion hosp) throws SQLException {
        hospitalizacionService.actualizarHospitalizacion(hosp);
    }

    @DeleteMapping("/{id}")
    public void darAltaHospitalizacion(@PathVariable int id) throws SQLException {
        hospitalizacionService.darAltaHospitalizacion(id);
    }
}
