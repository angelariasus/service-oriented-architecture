package com.system.service;

import com.system.dao.CitaDAO;
import com.system.model.Cita;

import java.sql.SQLException;
import java.util.List;

public class CitaService {

    private CitaDAO citaDAO = new CitaDAO();

    public void registrarCita(Cita cita) throws SQLException {
        citaDAO.insertar(cita);
    }

    public void actualizarCita(Cita cita) throws SQLException {
        citaDAO.actualizar(cita);
    }

    public void cancelarCita(int idCita) throws SQLException {
        citaDAO.eliminar(idCita);
    }

    public Cita obtenerCitaPorId(int idCita) throws SQLException {
        return citaDAO.listar().stream()
                .filter(c -> c.getIdCita() == idCita)
                .findFirst().orElse(null);
    }

    public List<Cita> listarCitas() throws SQLException {
        return citaDAO.listar();
    }
}
