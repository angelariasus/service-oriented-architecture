package com.system.dao;

import com.system.model.Cita;
import com.system.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    public void insertar(Cita cita) throws SQLException {
        String sql = "INSERT INTO CITA (ID_CITA, ID_PACIENTE, ID_MEDICO, FECHA, HORA, ESTADO) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cita.getIdCita());
            ps.setInt(2, cita.getIdPaciente());
            ps.setInt(3, cita.getIdMedico());
            ps.setDate(4, new java.sql.Date(cita.getFecha().getTime()));
            ps.setString(5, cita.getHora());
            ps.setString(6, cita.getEstado());
            ps.executeUpdate();
        }
    }

    public List<Cita> listar() throws SQLException {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM CITA";

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cita c = new Cita(
                    rs.getInt("ID_CITA"),
                    rs.getInt("ID_PACIENTE"),
                    rs.getInt("ID_MEDICO"),
                    rs.getDate("FECHA"),
                    rs.getString("HORA"),
                    rs.getString("ESTADO")
                );
                lista.add(c);
            }
        }
        return lista;
    }

    public void actualizar(Cita cita) throws SQLException {
        String sql = "UPDATE CITA SET ID_PACIENTE=?, ID_MEDICO=?, FECHA=?, HORA=?, ESTADO=? WHERE ID_CITA=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cita.getIdPaciente());
            ps.setInt(2, cita.getIdMedico());
            ps.setDate(3, new java.sql.Date(cita.getFecha().getTime()));
            ps.setString(4, cita.getHora());
            ps.setString(5, cita.getEstado());
            ps.setInt(6, cita.getIdCita());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idCita) throws SQLException {
        String sql = "DELETE FROM CITA WHERE ID_CITA=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCita);
            ps.executeUpdate();
        }
    }
}
