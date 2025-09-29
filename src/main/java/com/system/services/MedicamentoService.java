package com.system.service;

import com.system.dao.MedicamentoDAO;
import com.system.model.Medicamento;

import java.sql.SQLException;
import java.util.List;

public class MedicamentoService {

    private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    public void registrarMedicamento(Medicamento medicamento) throws SQLException {
        medicamentoDAO.insertar(medicamento);
    }

    public void actualizarMedicamento(Medicamento medicamento) throws SQLException {
        medicamentoDAO.actualizar(medicamento);
    }

    public void eliminarMedicamento(int idMedicamento) throws SQLException {
        medicamentoDAO.eliminar(idMedicamento);
    }

    public Medicamento obtenerMedicamentoPorId(int idMedicamento) throws SQLException {
        return medicamentoDAO.listar().stream()
                .filter(m -> m.getIdMedicamento() == idMedicamento)
                .findFirst().orElse(null);
    }

    public List<Medicamento> listarMedicamentos() throws SQLException {
        return medicamentoDAO.listar();
    }
}
