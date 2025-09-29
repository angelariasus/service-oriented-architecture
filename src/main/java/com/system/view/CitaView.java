package com.system.view;

import com.system.model.Cita;
import com.system.service.CitaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CitaView extends JFrame {

    private CitaService citaService = new CitaService();
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtPaciente, txtMedico, txtFecha, txtHora;

    public CitaView() {
        setTitle("Gestión de Citas");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        cargarCitas();
    }

    private void initComponents() {
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.add(new JLabel("Paciente:"));
        txtPaciente = new JTextField();
        panelForm.add(txtPaciente);

        panelForm.add(new JLabel("Médico:"));
        txtMedico = new JTextField();
        panelForm.add(txtMedico);

        panelForm.add(new JLabel("Fecha (YYYY-MM-DD):"));
        txtFecha = new JTextField();
        panelForm.add(txtFecha);

        panelForm.add(new JLabel("Hora (HH:MM):"));
        txtHora = new JTextField();
        panelForm.add(txtHora);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrarCita());
        panelForm.add(btnRegistrar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizarCita());
        panelForm.add(btnActualizar);

        modelo = new DefaultTableModel(new String[]{"ID", "Paciente", "Médico", "Fecha", "Hora"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JButton btnEliminar = new JButton("Cancelar Cita");
        btnEliminar.addActionListener(e -> cancelarCita());

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(panelForm, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(btnEliminar, BorderLayout.SOUTH);
    }

    private void cargarCitas() {
        try {
            modelo.setRowCount(0);
            List<Cita> lista = citaService.listarCitas();
            for (Cita c : lista) {
                modelo.addRow(new Object[]{c.getIdCita(), c.getPaciente().getNombre(), c.getMedico().getNombre(), c.getFecha(), c.getHora()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar citas: " + e.getMessage());
        }
    }

    private void registrarCita() {
        try {
            Cita c = new Cita();
            c.setPaciente(new com.system.model.Paciente()); // Aquí podrías seleccionar el paciente desde un combo
            c.setMedico(new com.system.model.Medico()); // Aquí podrías seleccionar el médico desde un combo
            c.setFecha(txtFecha.getText());
            c.setHora(txtHora.getText());
            citaService.registrarCita(c);
            cargarCitas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar cita: " + e.getMessage());
        }
    }

    private void actualizarCita() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            try {
                Cita c = new Cita();
                c.setIdCita((int) modelo.getValueAt(fila, 0));
                c.setFecha(txtFecha.getText());
                c.setHora(txtHora.getText());
                citaService.actualizarCita(c);
                cargarCitas();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar cita: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una cita para actualizar.");
        }
    }

    private void cancelarCita() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            try {
                int id = (int) modelo.getValueAt(fila, 0);
                citaService.cancelarCita(id);
                cargarCitas();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cancelar cita: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una cita para cancelar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CitaView().setVisible(true));
    }
}
