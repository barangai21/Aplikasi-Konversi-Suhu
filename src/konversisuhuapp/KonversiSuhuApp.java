package konversisuhuapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KonversiSuhuApp {

    private JFrame frame;
    private JTextField inputField;
    private JLabel resultLabel;
    private JComboBox<String> scaleComboBox;
    private JButton convertButton;

    // Main method to run the application
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                KonversiSuhuApp window = new KonversiSuhuApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor to initialize the GUI
    public KonversiSuhuApp() {
        frame = new JFrame("Aplikasi Konversi Suhu");
        frame.setBounds(100, 100, 400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        // Label input
        JLabel inputLabel = new JLabel("Masukkan nilai suhu:");
        frame.getContentPane().add(inputLabel);

        // JTextField untuk input suhu
        inputField = new JTextField();
        inputField.setColumns(10);
        frame.getContentPane().add(inputField);

        // Menambahkan KeyAdapter untuk membatasi input hanya angka
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == '.')) {
                    e.consume(); // Membatalkan input jika bukan angka
                }
            }
        });

        // ComboBox untuk memilih skala suhu
        String[] scales = {"Celcius", "Fahrenheit", "Reamur", "Kelvin"};
        scaleComboBox = new JComboBox<>(scales);
        frame.getContentPane().add(scaleComboBox);

        // Tombol Konversi
        convertButton = new JButton("Konversi");
        convertButton.addActionListener(e -> convertTemperature());
        frame.getContentPane().add(convertButton);

        // Label untuk hasil konversi
        resultLabel = new JLabel("Hasil: ");
        frame.getContentPane().add(resultLabel);
    }

    // Fungsi untuk konversi suhu
    private void convertTemperature() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Harap masukkan nilai suhu!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double inputTemperature = Double.parseDouble(input);
            String selectedScale = (String) scaleComboBox.getSelectedItem();
            String resultText = "";

            switch (selectedScale) {
                case "Celcius":
                    resultText = "Fahrenheit: " + celciusToFahrenheit(inputTemperature) + "\n"
                            + "Reamur: " + celciusToReamur(inputTemperature) + "\n"
                            + "Kelvin: " + celciusToKelvin(inputTemperature);
                    break;
                case "Fahrenheit":
                    resultText = "Celcius: " + fahrenheitToCelcius(inputTemperature) + "\n"
                            + "Reamur: " + fahrenheitToReamur(inputTemperature) + "\n"
                            + "Kelvin: " + fahrenheitToKelvin(inputTemperature);
                    break;
                case "Reamur":
                    resultText = "Celcius: " + reamurToCelcius(inputTemperature) + "\n"
                            + "Fahrenheit: " + reamurToFahrenheit(inputTemperature) + "\n"
                            + "Kelvin: " + reamurToKelvin(inputTemperature);
                    break;
                case "Kelvin":
                    resultText = "Celcius: " + kelvinToCelcius(inputTemperature) + "\n"
                            + "Fahrenheit: " + kelvinToFahrenheit(inputTemperature) + "\n"
                            + "Reamur: " + kelvinToReamur(inputTemperature);
                    break;
            }

            // Tampilkan hasil konversi
            resultLabel.setText("<html>" + resultText.replace("\n", "<br>") + "</html>");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Input tidak valid! Masukkan angka yang benar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Rumus konversi suhu
    private double celciusToFahrenheit(double c) {
        return c * 9 / 5 + 32;
    }

    private double celciusToReamur(double c) {
        return c * 4 / 5;
    }

    private double celciusToKelvin(double c) {
        return c + 273.15;
    }

    private double fahrenheitToCelcius(double f) {
        return (f - 32) * 5 / 9;
    }

    private double fahrenheitToReamur(double f) {
        return (f - 32) * 4 / 9;
    }

    private double fahrenheitToKelvin(double f) {
        return (f - 32) * 5 / 9 + 273.15;
    }

    private double reamurToCelcius(double r) {
        return r * 5 / 4;
    }

    private double reamurToFahrenheit(double r) {
        return r * 9 / 4 + 32;
    }

    private double reamurToKelvin(double r) {
        return r * 5 / 4 + 273.15;
    }

    private double kelvinToCelcius(double k) {
        return k - 273.15;
    }

    private double kelvinToFahrenheit(double k) {
        return (k - 273.15) * 9 / 5 + 32;
    }

    private double kelvinToReamur(double k) {
        return (k - 273.15) * 4 / 5;
    }
}
