package com.system.dao;

import com.system.model.Medico;
import com.system.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    public void insertar(Medico medico) throws SQLException {
        String sql = "INSERT INTO MEDICO (ID_MEDICO, NOMBRE, APELLIDO, ESPECIALIDAD, TELEFONO) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, medico.getIdMedico());
            ps.setString(2, medico.getNombre());
            ps.setString(3, medico.getApellido());
            ps.setString(4, medico.getEspecialidad());
            ps.setString(5, medico.getTelefono());
            ps.executeUpdate();
        }
    }

    public List<Medico> listar() throws SQLException {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM MEDICO";

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Medico m = new Medico(
                    rs.getInt("ID_MEDICO"),
                    rs.getString("NOMBRE"),
                    rs.getString("APELLIDO"),
                    rs.getString("ESPECIALIDAD"),
                    rs.getString("TELEFONO")
                );
                lista.add(m);
            }
        }
        return lista;
    }

    public void actualizar(Medico medico) throws SQLException {
        String sql = "UPDATE MEDICO SET NOMBRE=?, APELLIDO=?, ESPECIALIDAD=?, TELEFONO=? WHERE ID_MEDICO=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, medico.getNombre());
            ps.setString(2, medico.getApellido());
            ps.setString(3, medico.getEspecialidad());
            ps.setString(4, medico.getTelefono());
            ps.setInt(5, medico.getIdMedico());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idMedico) throws SQLException {
        String sql = "DELETE FROM MEDICO WHERE ID_MEDICO=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMedico);
            ps.executeUpdate();
        }
    }
}
