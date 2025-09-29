package com.system.dao;

import com.system.model.Paciente;
import com.system.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void insertar(Paciente paciente) throws SQLException {
        String sql = "INSERT INTO PACIENTE (ID_PACIENTE, NOMBRE, APELLIDO, DNI, FECHA_NACIMIENTO, DIRECCION, TELEFONO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, paciente.getIdPaciente());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getApellido());
            ps.setString(4, paciente.getDni());
            ps.setDate(5, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            ps.setString(6, paciente.getDireccion());
            ps.setString(7, paciente.getTelefono());
            ps.executeUpdate();
        }
    }

    public List<Paciente> listar() throws SQLException {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM PACIENTE";

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente(
                    rs.getInt("ID_PACIENTE"),
                    rs.getString("NOMBRE"),
                    rs.getString("APELLIDO"),
                    rs.getString("DNI"),
                    rs.getDate("FECHA_NACIMIENTO"),
                    rs.getString("DIRECCION"),
                    rs.getString("TELEFONO")
                );
                lista.add(p);
            }
        }
        return lista;
    }

    public void actualizar(Paciente paciente) throws SQLException {
        String sql = "UPDATE PACIENTE SET NOMBRE=?, APELLIDO=?, DNI=?, FECHA_NACIMIENTO=?, DIRECCION=?, TELEFONO=? WHERE ID_PACIENTE=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDni());
            ps.setDate(4, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            ps.setString(5, paciente.getDireccion());
            ps.setString(6, paciente.getTelefono());
            ps.setInt(7, paciente.getIdPaciente());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idPaciente) throws SQLException {
        String sql = "DELETE FROM PACIENTE WHERE ID_PACIENTE=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPaciente);
            ps.executeUpdate();
        }
    }
}
