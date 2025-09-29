package com.system.view;

import com.system.model.Factura;
import com.system.service.FacturaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class FacturaView extends JFrame {

    private FacturaService facturaService = new FacturaService();
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtPaciente, txtTotal;

    public FacturaView() {
        setTitle("Gestión de Facturas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        cargarFacturas();
    }

    private void initComponents() {
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));

        panelForm.add(new JLabel("Paciente:"));
        txtPaciente = new JTextField();
        panelForm.add(txtPaciente);

        panelForm.add(new JLabel("Total:"));
        txtTotal = new JTextField();
        panelForm.add(txtTotal);

        JButton btnGenerar = new JButton("Generar");
        btnGenerar.addActionListener(e -> generarFactura());
        panelForm.add(btnGenerar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizarFactura());
        panelForm.add(btnActualizar);

        modelo = new DefaultTableModel(new String[]{"ID", "Paciente", "Total"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarFactura());

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(panelForm, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(btnEliminar, BorderLayout.SOUTH);
    }

    private void cargarFacturas() {
        try {
            modelo.setRowCount(0);
            List<Factura> lista = facturaService.listarFacturas();
            for (Factura f : lista) {
                modelo.addRow(new Object[]{f.getIdFactura(), f.getPaciente().getNombre(), f.getTotal()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar facturas: " + e.getMessage());
        }
    }

    private void generarFactura() {
        try {
            Factura f = new Factura();
            f.setPaciente(new com.system.model.Paciente()); // Podrías seleccionar desde un combo
            f.setTotal(Double.parseDouble(txtTotal.getText()));
            facturaService.generarFactura(f);
            cargarFacturas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al generar factura: " + e.getMessage());
        }
    }

    private void actualizarFactura() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            try {
                Factura f = new Factura();
                f.setIdFactura((int) modelo.getValueAt(fila, 0));
                f.setTotal(Double.parseDouble(txtTotal.getText()));
                facturaService.actualizarFactura(f);
                cargarFacturas();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar factura: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una factura para actualizar.");
        }
    }

    private void eliminarFactura() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            try {
                int id = (int) modelo.getValueAt(fila, 0);
                facturaService.eliminarFactura(id);
                cargarFacturas();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar factura: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una factura para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FacturaView().setVisible(true));
    }
}
