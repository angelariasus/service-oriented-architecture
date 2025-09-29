package com.system.service;

import com.system.dao.PacienteDAO;
import com.system.model.Paciente;

import java.sql.SQLException;
import java.util.List;

public class PacienteService {

    private PacienteDAO pacienteDAO = new PacienteDAO();

    public void registrarPaciente(Paciente paciente) throws SQLException {
        pacienteDAO.insertar(paciente);
    }

    public void actualizarPaciente(Paciente paciente) throws SQLException {
        pacienteDAO.actualizar(paciente);
    }

    public void eliminarPaciente(int idPaciente) throws SQLException {
        pacienteDAO.eliminar(idPaciente);
    }

    public Paciente obtenerPacientePorId(int idPaciente) throws SQLException {
        return pacienteDAO.listar().stream()
                .filter(p -> p.getIdPaciente() == idPaciente)
                .findFirst().orElse(null);
    }

    public List<Paciente> listarPacientes() throws SQLException {
        return pacienteDAO.listar();
    }
}
