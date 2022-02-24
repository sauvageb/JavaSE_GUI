package com.graphic.training;

import com.graphic.training.entity.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.List;

public class UserFormController {

    private UserFormView view;

    private int rowEdition;

    public UserFormController(List<User> userList, UserFormView v) {
        this.view = v;
        initView(userList);
    }

    private void initView(List<User> userList) {
        userList.forEach(u -> view.getUserTableModel().add(u));
        clearForm();
    }

    public void initController() {
        view.getCancelButton().addActionListener(e -> clearForm());
        view.getAddUserButton().addActionListener(e -> saveUser());

        JTable table = view.getUserTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(e -> fillEditForm(e));
    }

    private void clearForm() {
        rowEdition = -1;
        view.getLastnameTextField().setText("");
        view.getFirstnameTextField().setText("");
        view.getEmailTextField().setText("");
        view.getPwdPasswordField().setText("");
    }

    private void fillEditForm(ListSelectionEvent e) {
        int rowSelected = view.getUserTable().getSelectedRow();

        User userToEdit = view.getUserTableModel().getUserAt(rowSelected);
        if (userToEdit != null) {
            rowEdition = rowSelected;
            view.getLastnameTextField().setText(userToEdit.getFirstname());
            view.getFirstnameTextField().setText(userToEdit.getLastname());
            view.getEmailTextField().setText(String.valueOf(userToEdit.getEmail()));
            view.getPwdPasswordField().setText(String.valueOf(userToEdit.getPassword()));
        }
    }


    private void saveUser() {
        String firstname = view.getLastnameTextField().getText();
        String lastname = view.getFirstnameTextField().getText();
        String email = view.getEmailTextField().getText();
        String password = view.getPwdPasswordField().getPassword().toString();

        User userModel = new User(firstname, lastname, email, password);

        String popupMsg = "User updated : ";
        if (rowEdition >= 0) {
            view.getUserTableModel().update(rowEdition, userModel);
        } else {
            popupMsg = "User added : ";
            view.getUserTableModel().add(userModel);
        }
        JOptionPane.showMessageDialog(null, popupMsg + userModel.getFirstname(), "Info", JOptionPane.INFORMATION_MESSAGE);
        clearForm();
    }

}
