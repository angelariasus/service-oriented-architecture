package com.system.service;

import com.system.dao.FacturaDAO;
import com.system.model.Factura;

import java.sql.SQLException;
import java.util.List;

public class FacturaService {

    private FacturaDAO facturaDAO = new FacturaDAO();

    public void generarFactura(Factura factura) throws SQLException {
        facturaDAO.insertar(factura);
    }

    public void actualizarFactura(Factura factura) throws SQLException {
        facturaDAO.actualizar(factura);
    }

    public void eliminarFactura(int idFactura) throws SQLException {
        facturaDAO.eliminar(idFactura);
    }

    public Factura obtenerFacturaPorId(int idFactura) throws SQLException {
        return facturaDAO.listar().stream()
                .filter(f -> f.getIdFactura() == idFactura)
                .findFirst().orElse(null);
    }

    public List<Factura> listarFacturas() throws SQLException {
        return facturaDAO.listar();
    }
}
