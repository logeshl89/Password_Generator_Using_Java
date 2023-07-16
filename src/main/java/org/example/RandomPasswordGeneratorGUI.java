package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RandomPasswordGeneratorGUI extends JFrame {
    private JTextField lengthField;
    private JCheckBox lowercaseCheckBox;
    private JCheckBox uppercaseCheckBox;
    private JCheckBox numbersCheckBox;
    private JCheckBox specialCharsCheckBox;
    private JTextArea passwordArea;

    public RandomPasswordGeneratorGUI() {
        setTitle("Random Password Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel passwordPanel = createPasswordPanel();

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(passwordPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lengthLabel = new JLabel("Length:");
        lengthField = new JTextField();
        JLabel lowercaseLabel = new JLabel("Include lowercase letters:");
        lowercaseCheckBox = new JCheckBox();
        JLabel uppercaseLabel = new JLabel("Include uppercase letters:");
        uppercaseCheckBox = new JCheckBox();
        JLabel numbersLabel = new JLabel("Include numbers:");
        numbersCheckBox = new JCheckBox();
        JLabel specialCharsLabel = new JLabel("Include special characters:");
        specialCharsCheckBox = new JCheckBox();

        panel.add(lengthLabel);
        panel.add(lengthField);
        panel.add(lowercaseLabel);
        panel.add(lowercaseCheckBox);
        panel.add(uppercaseLabel);
        panel.add(uppercaseCheckBox);
        panel.add(numbersLabel);
        panel.add(numbersCheckBox);
        panel.add(specialCharsLabel);
        panel.add(specialCharsCheckBox);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(e -> generatePassword());

        panel.add(generateButton);

        return panel;
    }

    private JPanel createPasswordPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel passwordLabel = new JLabel("Generated Password:");
        passwordArea = new JTextArea(5, 20);
        passwordArea.setEditable(false);
        passwordArea.setFont(new Font("Monospaced", Font.BOLD, 16));
        JScrollPane scrollPane = new JScrollPane(passwordArea);

        panel.add(passwordLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void generatePassword() {
        int length = Integer.parseInt(lengthField.getText());
        boolean useLowercase = lowercaseCheckBox.isSelected();
        boolean useUppercase = uppercaseCheckBox.isSelected();
        boolean useNumbers = numbersCheckBox.isSelected();
        boolean useSpecialCharacters = specialCharsCheckBox.isSelected();

        String password = generateRandomPassword(length, useLowercase, useUppercase, useNumbers, useSpecialCharacters);
        passwordArea.setText(password);
    }

    private String generateRandomPassword(int length, boolean useLowercase, boolean useUppercase,
                                          boolean useNumbers, boolean useSpecialCharacters) {
        StringBuilder password = new StringBuilder();
        String characters = "";

        if (useLowercase) {
            characters += "abcdefghijklmnopqrstuvwxyz";
        }
        if (useUppercase) {
            characters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        if (useNumbers) {
            characters += "0123456789";
        }
        if (useSpecialCharacters) {
            characters += "!@#$%^&*()_-+=<>?";
        }

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RandomPasswordGeneratorGUI gui = new RandomPasswordGeneratorGUI();
                gui.setVisible(true);
            }
        });
    }
}


