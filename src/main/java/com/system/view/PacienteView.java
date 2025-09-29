package com.system.view;

import com.system.model.Paciente;
import com.system.service.PacienteService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PacienteView extends JFrame {

    private PacienteService pacienteService = new PacienteService();
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtNombre, txtApellido, txtEdad;

    public PacienteView() {
        setTitle("Gestión de Pacientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        cargarPacientes();
    }

    private void initComponents() {
        // Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panelForm.add(txtApellido);

        panelForm.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelForm.add(txtEdad);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrarPaciente());
        panelForm.add(btnRegistrar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizarPaciente());
        panelForm.add(btnActualizar);

        // Tabla
        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Edad"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarPaciente());

        // Layout
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(panelForm, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(btnEliminar, BorderLayout.SOUTH);
    }

    private void cargarPacientes() {
        try {
            modelo.setRowCount(0);
            List<Paciente> lista = pacienteService.listarPacientes();
            for (Paciente p : lista) {
                modelo.addRow(new Object[]{p.getIdPaciente(), p.getNombre(), p.getApellido(), p.getEdad()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar pacientes: " + e.getMessage());
        }
    }

    private void registrarPaciente() {
        try {
            Paciente p = new Paciente();
            p.setNombre(txtNombre.getText());
            p.setApellido(txtApellido.getText());
            p.setEdad(Integer.parseInt(txtEdad.getText()));
            pacienteService.registrarPaciente(p);
            cargarPacientes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar paciente: " + e.getMessage());
        }
    }

    private void actualizarPaciente() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            try {
                Paciente p = new Paciente();
                p.setIdPaciente((int) modelo.getValueAt(fila, 0));
                p.setNombre(txtNombre.getText());
                p.setApellido(txtApellido.getText());
                p.setEdad(Integer.parseInt(txtEdad.getText()));
                pacienteService.actualizarPaciente(p);
                cargarPacientes();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar paciente: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un paciente para actualizar.");
        }
    }

    private void eliminarPaciente() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            try {
                int id = (int) modelo.getValueAt(fila, 0);
                pacienteService.eliminarPaciente(id);
                cargarPacientes();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar paciente: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un paciente para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PacienteView().setVisible(true));
    }
}
