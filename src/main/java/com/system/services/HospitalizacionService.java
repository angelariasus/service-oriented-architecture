package com.system.service;

import com.system.dao.HospitalizacionDAO;
import com.system.model.Hospitalizacion;

import java.sql.SQLException;
import java.util.List;

public class HospitalizacionService {

    private HospitalizacionDAO hospitalizacionDAO = new HospitalizacionDAO();

    public void registrarHospitalizacion(Hospitalizacion hosp) throws SQLException {
        hospitalizacionDAO.insertar(hosp);
    }

    public void actualizarHospitalizacion(Hospitalizacion hosp) throws SQLException {
        hospitalizacionDAO.actualizar(hosp);
    }

    public void darAltaHospitalizacion(int idHospitalizacion) throws SQLException {
        hospitalizacionDAO.eliminar(idHospitalizacion);
    }

    public Hospitalizacion obtenerHospitalizacionPorId(int idHospitalizacion) throws SQLException {
        return hospitalizacionDAO.listar().stream()
                .filter(h -> h.getIdHospitalizacion() == idHospitalizacion)
                .findFirst().orElse(null);
    }

    public List<Hospitalizacion> listarHospitalizaciones() throws SQLException {
        return hospitalizacionDAO.listar();
    }
}
