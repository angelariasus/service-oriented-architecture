package com.system.controller;

import com.system.model.Factura;
import com.system.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public List<Factura> listarFacturas() throws SQLException {
        return facturaService.listarFacturas();
    }

    @GetMapping("/{id}")
    public Factura obtenerFactura(@PathVariable int id) throws SQLException {
        return facturaService.obtenerFacturaPorId(id);
    }

    @PostMapping
    public void generarFactura(@RequestBody Factura factura) throws SQLException {
        facturaService.generarFactura(factura);
    }

    @PutMapping
    public void actualizarFactura(@RequestBody Factura factura) throws SQLException {
        facturaService.actualizarFactura(factura);
    }

    @DeleteMapping("/{id}")
    public void eliminarFactura(@PathVariable int id) throws SQLException {
        facturaService.eliminarFactura(id);
    }
}
