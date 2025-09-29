package com.system;

import com.system.view.*;

import javax.swing.*;
import java.awt.*;

public class AppTest extends JFrame {

    public App() {
        setTitle("Sistema de Gestión Hospitalaria");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        JButton btnPacientes = new JButton("Gestionar Pacientes");
        btnPacientes.addActionListener(e -> abrirPacienteView());
        panel.add(btnPacientes);

        JButton btnMedicos = new JButton("Gestionar Médicos");
        btnMedicos.addActionListener(e -> abrirMedicoView());
        panel.add(btnMedicos);

        JButton btnCitas = new JButton("Gestionar Citas");
        btnCitas.addActionListener(e -> abrirCitaView());
        panel.add(btnCitas);

        JButton btnMedicamentos = new JButton("Gestionar Medicamentos");
        btnMedicamentos.addActionListener(e -> abrirMedicamentoView());
        panel.add(btnMedicamentos);

        JButton btnFacturas = new JButton("Gestionar Facturas");
        btnFacturas.addActionListener(e -> abrirFacturaView());
        panel.add(btnFacturas);

        JButton btnHospitalizaciones = new JButton("Gestionar Hospitalizaciones");
        btnHospitalizaciones.addActionListener(e -> abrirHospitalizacionView());
        panel.add(btnHospitalizaciones);

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void abrirPacienteView() {
        SwingUtilities.invokeLater(() -> new PacienteView().setVisible(true));
    }

    private void abrirMedicoView() {
        SwingUtilities.invokeLater(() -> new MedicoView().setVisible(true));
    }

    private void abrirCitaView() {
        SwingUtilities.invokeLater(() -> new CitaView().setVisible(true));
    }

    private void abrirMedicamentoView() {
        SwingUtilities.invokeLater(() -> new MedicamentoView().setVisible(true));
    }

    private void abrirFacturaView() {
        SwingUtilities.invokeLater(() -> new FacturaView().setVisible(true));
    }

    private void abrirHospitalizacionView() {
        SwingUtilities.invokeLater(() -> new HospitalizacionView().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
