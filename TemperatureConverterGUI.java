import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverterGUI extends JFrame implements ActionListener {

    JLabel titleLabel, inputLabel, resultLabel;
    JTextField tempField;
    JButton cToFButton, fToCButton;

    public TemperatureConverterGUI() {

        setTitle("Temperature Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5, 1, 10, 10));

        titleLabel = new JLabel("TEMPERATURE CONVERTER", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        inputLabel = new JLabel("Enter Temperature:");
        tempField = new JTextField();

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputLabel, BorderLayout.WEST);
        inputPanel.add(tempField, BorderLayout.CENTER);

        cToFButton = new JButton("Celsius → Fahrenheit");
        fToCButton = new JButton("Fahrenheit → Celsius");

        cToFButton.addActionListener(this);
        fToCButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cToFButton);
        buttonPanel.add(fToCButton);

        resultLabel = new JLabel("Result: ", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        add(titleLabel);
        add(inputPanel);
        add(buttonPanel);
        add(resultLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            double temp = Double.parseDouble(tempField.getText());

            if (e.getSource() == cToFButton) {
                double result = (temp * 9 / 5) + 32;
                resultLabel.setText("Result: " + result + " °F");
            }

            if (e.getSource() == fToCButton) {
                double result = (temp - 32) * 5 / 9;
                resultLabel.setText("Result: " + result + " °C");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid number!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TemperatureConverterGUI();
    }
}