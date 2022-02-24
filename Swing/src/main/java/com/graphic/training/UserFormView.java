package com.graphic.training;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class UserFormView {
    private JFrame frame;

    private JLabel firstnameLabel;
    private JLabel lastnameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;

    private JTextField firstnameTextField;
    private JTextField lastnameTextField;
    private JTextField emailTextField;
    private JPasswordField pwdPasswordField;

    private JTable userTable;

    private JButton cancelButton;
    private JButton addUserButton;

    private UserTableModel userTableModel;

    public UserFormView(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Container contentPane = frame.getContentPane();

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(5, 2));

        // Create UI elements
        firstnameLabel = new JLabel("Firstname :");
        firstnameTextField = new JTextField();
        panel.add(firstnameLabel);
        panel.add(firstnameTextField);

        lastnameLabel = new JLabel("Lastname :");
        lastnameTextField = new JTextField();
        panel.add(lastnameLabel);
        panel.add(lastnameTextField);

        emailLabel = new JLabel("Email :");
        emailTextField = new JTextField();
        panel.add(emailLabel);
        panel.add(emailTextField);

        passwordLabel = new JLabel("Password :");
        pwdPasswordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(pwdPasswordField);

        cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        addUserButton = new JButton("Save");
        panel.add(addUserButton);
        contentPane.add(panel, BorderLayout.SOUTH);

        userTable = new JTable();
        userTableModel = new UserTableModel(new ArrayList<>());
        userTable.setModel(userTableModel);
        userTable.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(userTable);

        frame.getContentPane().add(sp);
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getAddUserButton() {
        return addUserButton;
    }

    public JTable getUserTable() {
        return userTable;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public JTextField getFirstnameTextField() {
        return firstnameTextField;
    }

    public JTextField getLastnameTextField() {
        return lastnameTextField;
    }

    public UserTableModel getUserTableModel() {
        return userTableModel;
    }

    public JPasswordField getPwdPasswordField() {
        return pwdPasswordField;
    }
}
