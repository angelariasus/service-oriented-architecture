package com.system.view;

import com.system.model.Medicamento;
import com.system.service.MedicamentoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MedicamentoView extends JFrame {

    private MedicamentoService medicamentoService = new MedicamentoService();
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtNombre, txtDescripcion, txtStock;

    public MedicamentoView() {
        setTitle("Gestión de Medicamentos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        cargarMedicamentos();
    }

    private void initComponents() {
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));

        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelForm.add(txtDescripcion);

        panelForm.add(new JLabel("Stock:"));
        txtStock = new JTextField();
        panelForm.add(txtStock);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrarMedicamento());
        panelForm.add(btnRegistrar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizarMedicamento());
        panelForm.add(btnActualizar);

        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripción", "Stock"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarMedicamento());

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(panelForm, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(btnEliminar, BorderLayout.SOUTH);
    }

    private void cargarMedicamentos() {
        try {
            modelo.setRowCount(0);
            List<Medicamento> lista = medicamentoService.listarMedicamentos();
            for (Medicamento m : lista) {
                modelo.addRow(new Object[]{m.getIdMedicamento(), m.getNombre(), m.getDescripcion(), m.getStock()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar medicamentos: " + e.getMessage());
        }
    }

    private void registrarMedicamento() {
        try {
            Medicamento m = new Medicamento();
            m.setNombre(txtNombre.getText());
            m.setDescripcion(txtDescripcion.getText());
            m.setStock(Integer.parseInt(txtStock.getText()));
            medicamentoService.registrarMedicamento(m);
            cargarMedicamentos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar medicamento: " + e.getMessage());
        }
    }

    private void actualizarMedicamento() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            try {
                Medicamento m = new Medicamento();
                m.setIdMedicamento((int) modelo.getValueAt(fila, 0));
                m.setNombre(txtNombre.getText());
                m.setDescripcion(txtDescripcion.getText());
                m.setStock(Integer.parseInt(txtStock.getText()));
                medicamentoService.actualizarMedicamento(m);
                cargarMedicamentos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar medicamento: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un medicamento para actualizar.");
        }
    }

    private void eliminarMedicamento() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            try {
                int id = (int) modelo.getValueAt(fila, 0);
                medicamentoService.eliminarMedicamento(id);
                cargarMedicamentos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar medicamento: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un medicamento para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MedicamentoView().setVisible(true));
    }
}
