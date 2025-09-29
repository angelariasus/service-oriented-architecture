package com.system.service;

import com.system.dao.MedicoDAO;
import com.system.model.Medico;

import java.sql.SQLException;
import java.util.List;

public class MedicoService {

    private MedicoDAO medicoDAO = new MedicoDAO();

    public void registrarMedico(Medico medico) throws SQLException {
        medicoDAO.insertar(medico);
    }

    public void actualizarMedico(Medico medico) throws SQLException {
        medicoDAO.actualizar(medico);
    }

    public void eliminarMedico(int idMedico) throws SQLException {
        medicoDAO.eliminar(idMedico);
    }

    public Medico obtenerMedicoPorId(int idMedico) throws SQLException {
        return medicoDAO.listar().stream()
                .filter(m -> m.getIdMedico() == idMedico)
                .findFirst().orElse(null);
    }

    public List<Medico> listarMedicos() throws SQLException {
        return medicoDAO.listar();
    }
}
