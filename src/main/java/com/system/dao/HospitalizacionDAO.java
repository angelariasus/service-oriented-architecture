package com.system.dao;

import com.system.model.Hospitalizacion;
import com.system.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalizacionDAO {

    public void insertar(Hospitalizacion hosp) throws SQLException {
        String sql = "INSERT INTO HOSPITALIZACION (ID_HOSPITALIZACION, ID_PACIENTE, HABITACION, FECHA_INGRESO, FECHA_ALTA) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, hosp.getIdHospitalizacion());
            ps.setInt(2, hosp.getIdPaciente());
            ps.setString(3, hosp.getHabitacion());
            ps.setDate(4, new java.sql.Date(hosp.getFechaIngreso().getTime()));
            if (hosp.getFechaAlta() != null) {
                ps.setDate(5, new java.sql.Date(hosp.getFechaAlta().getTime()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            ps.executeUpdate();
        }
    }

    public List<Hospitalizacion> listar() throws SQLException {
        List<Hospitalizacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM HOSPITALIZACION";

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Hospitalizacion h = new Hospitalizacion(
                    rs.getInt("ID_HOSPITALIZACION"),
                    rs.getInt("ID_PACIENTE"),
                    rs.getString("HABITACION"),
                    rs.getDate("FECHA_INGRESO"),
                    rs.getDate("FECHA_ALTA")
                );
                lista.add(h);
            }
        }
        return lista;
    }

    public void actualizar(Hospitalizacion hosp) throws SQLException {
        String sql = "UPDATE HOSPITALIZACION SET ID_PACIENTE=?, HABITACION=?, FECHA_INGRESO=?, FECHA_ALTA=? WHERE ID_HOSPITALIZACION=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, hosp.getIdPaciente());
            ps.setString(2, hosp.getHabitacion());
            ps.setDate(3, new java.sql.Date(hosp.getFechaIngreso().getTime()));
            if (hosp.getFechaAlta() != null) {
                ps.setDate(4, new java.sql.Date(hosp.getFechaAlta().getTime()));
            } else {
                ps.setNull(4, Types.DATE);
            }
            ps.setInt(5, hosp.getIdHospitalizacion());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idHospitalizacion) throws SQLException {
        String sql = "DELETE FROM HOSPITALIZACION WHERE ID_HOSPITALIZACION=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHospitalizacion);
            ps.executeUpdate();
        }
    }
}
