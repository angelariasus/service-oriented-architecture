package com.system.dao;

import com.system.model.Factura;
import com.system.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    public void insertar(Factura factura) throws SQLException {
        String sql = "INSERT INTO FACTURA (ID_FACTURA, ID_PACIENTE, MONTO, FECHA, ESTADO) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdPaciente());
            ps.setDouble(3, factura.getMonto());
            ps.setDate(4, new java.sql.Date(factura.getFecha().getTime()));
            ps.setString(5, factura.getEstado());
            ps.executeUpdate();
        }
    }

    public List<Factura> listar() throws SQLException {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM FACTURA";

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Factura f = new Factura(
                    rs.getInt("ID_FACTURA"),
                    rs.getInt("ID_PACIENTE"),
                    rs.getDouble("MONTO"),
                    rs.getDate("FECHA"),
                    rs.getString("ESTADO")
                );
                lista.add(f);
            }
        }
        return lista;
    }

    public void actualizar(Factura factura) throws SQLException {
        String sql = "UPDATE FACTURA SET ID_PACIENTE=?, MONTO=?, FECHA=?, ESTADO=? WHERE ID_FACTURA=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, factura.getIdPaciente());
            ps.setDouble(2, factura.getMonto());
            ps.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            ps.setString(4, factura.getEstado());
            ps.setInt(5, factura.getIdFactura());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idFactura) throws SQLException {
        String sql = "DELETE FROM FACTURA WHERE ID_FACTURA=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idFactura);
            ps.executeUpdate();
        }
    }
}
