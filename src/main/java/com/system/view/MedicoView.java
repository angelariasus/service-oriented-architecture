package com.system.view;

import com.system.model.Medico;
import com.system.service.MedicoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MedicoView extends JFrame {

    private MedicoService medicoService = new MedicoService();
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtNombre, txtEspecialidad, txtTelefono;

    public MedicoView() {
        setTitle("Gestión de Médicos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        cargarMedicos();
    }

    private void initComponents() {
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Especialidad:"));
        txtEspecialidad = new JTextField();
        panelForm.add(txtEspecialidad);

        panelForm.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelForm.add(txtTelefono);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrarMedico());
        panelForm.add(btnRegistrar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizarMedico());
        panelForm.add(btnActualizar);

        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Especialidad", "Teléfono"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarMedico());

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(panelForm, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(btnEliminar, BorderLayout.SOUTH);
    }

    private void cargarMedicos() {
        try {
            modelo.setRowCount(0);
            List<Medico> lista = medicoService.listarMedicos();
            for (Medico m : lista) {
                modelo.addRow(new Object[]{m.getIdMedico(), m.getNombre(), m.getEspecialidad(), m.getTelefono()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar médicos: " + e.getMessage());
        }
    }

    private void registrarMedico() {
        try {
            Medico m = new Medico();
            m.setNombre(txtNombre.getText());
            m.setEspecialidad(txtEspecialidad.getText());
            m.setTelefono(txtTelefono.getText());
            medicoService.registrarMedico(m);
            cargarMedicos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar médico: " + e.getMessage());
        }
    }

    private void actualizarMedico() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            try {
                Medico m = new Medico();
                m.setIdMedico((int) modelo.getValueAt(fila, 0));
                m.setNombre(txtNombre.getText());
                m.setEspecialidad(txtEspecialidad.getText());
                m.setTelefono(txtTelefono.getText());
                medicoService.actualizarMedico(m);
                cargarMedicos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar médico: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un médico para actualizar.");
        }
    }

    private void eliminarMedico() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            try {
                int id = (int) modelo.getValueAt(fila, 0);
                medicoService.eliminarMedico(id);
                cargarMedicos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar médico: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un médico para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MedicoView().setVisible(true));
    }
}
