package com.system.dao;

import com.system.model.Medicamento;
import com.system.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {

    public void insertar(Medicamento medicamento) throws SQLException {
        String sql = "INSERT INTO MEDICAMENTO (ID_MEDICAMENTO, NOMBRE, STOCK, PRECIO) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, medicamento.getIdMedicamento());
            ps.setString(2, medicamento.getNombre());
            ps.setInt(3, medicamento.getStock());
            ps.setDouble(4, medicamento.getPrecio());
            ps.executeUpdate();
        }
    }

    public List<Medicamento> listar() throws SQLException {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM MEDICAMENTO";

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Medicamento m = new Medicamento(
                    rs.getInt("ID_MEDICAMENTO"),
                    rs.getString("NOMBRE"),
                    rs.getInt("STOCK"),
                    rs.getDouble("PRECIO")
                );
                lista.add(m);
            }
        }
        return lista;
    }

    public void actualizar(Medicamento medicamento) throws SQLException {
        String sql = "UPDATE MEDICAMENTO SET NOMBRE=?, STOCK=?, PRECIO=? WHERE ID_MEDICAMENTO=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, medicamento.getNombre());
            ps.setInt(2, medicamento.getStock());
            ps.setDouble(3, medicamento.getPrecio());
            ps.setInt(4, medicamento.getIdMedicamento());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idMedicamento) throws SQLException {
        String sql = "DELETE FROM MEDICAMENTO WHERE ID_MEDICAMENTO=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMedicamento);
            ps.executeUpdate();
        }
    }
}
