package com.system.view;

import com.system.model.Hospitalizacion;
import com.system.service.HospitalizacionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class HospitalizacionView extends JFrame {

    private HospitalizacionService hospitalizacionService = new HospitalizacionService();
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtPaciente, txtHabitacion, txtFechaIngreso;

    public HospitalizacionView() {
        setTitle("Gestión de Hospitalizaciones");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        cargarHospitalizaciones();
    }

    private void initComponents() {
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));

        panelForm.add(new JLabel("Paciente:"));
        txtPaciente = new JTextField();
        panelForm.add(txtPaciente);

        panelForm.add(new JLabel("Habitación:"));
        txtHabitacion = new JTextField();
        panelForm.add(txtHabitacion);

        panelForm.add(new JLabel("Fecha Ingreso (YYYY-MM-DD):"));
        txtFechaIngreso = new JTextField();
        panelForm.add(txtFechaIngreso);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrarHospitalizacion());
        panelForm.add(btnRegistrar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizarHospitalizacion());
        panelForm.add(btnActualizar);

        modelo = new DefaultTableModel(new String[]{"ID", "Paciente", "Habitación", "Fecha Ingreso"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JButton btnDarAlta = new JButton("Dar Alta");
        btnDarAlta.addActionListener(e -> darAltaHospitalizacion());

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(panelForm, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(btnDarAlta, BorderLayout.SOUTH);
    }

    private void cargarHospitalizaciones() {
        try {
            modelo.setRowCount(0);
            List<Hospitalizacion> lista = hospitalizacionService.listarHospitalizaciones();
            for (Hospitalizacion h : lista) {
                modelo.addRow(new Object[]{h.getIdHospitalizacion(), h.getPaciente().getNombre(), h.getHabitacion(), h.getFechaIngreso()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar hospitalizaciones: " + e.getMessage());
        }
    }

    private void registrarHospitalizacion() {
        try {
            Hospitalizacion h = new Hospitalizacion();
            h.setPaciente(new com.system.model.Paciente()); // Selección desde combo
            h.setHabitacion(txtHabitacion.getText());
            h.setFechaIngreso(txtFechaIngreso.getText());
            hospitalizacionService.registrarHospitalizacion(h);
            cargarHospitalizaciones();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar hospitalización: " + e.getMessage());
        }
    }

    private void actualizarHospitalizacion() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            try {
                Hospitalizacion h = new Hospitalizacion();
                h.setIdHospitalizacion((int) modelo.getValueAt(fila, 0));
                h.setHabitacion(txtHabitacion.getText());
                h.setFechaIngreso(txtFechaIngreso.getText());
                hospitalizacionService.actualizarHospitalizacion(h);
                cargarHospitalizaciones();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar hospitalización: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una hospitalización para actualizar.");
        }
    }

    private void darAltaHospitalizacion() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            try {
                int id = (int) modelo.getValueAt(fila, 0);
                hospitalizacionService.darAltaHospitalizacion(id);
                cargarHospitalizaciones();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al dar alta: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una hospitalización para dar alta.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HospitalizacionView().setVisible(true));
    }
}
