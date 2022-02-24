package com.graphic.training;

import com.graphic.training.entity.User;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private static final List<String> HEADERS = Arrays.asList("ID", "Firstname", "Lastname", "Age");

    private List<User> users;


    public UserTableModel(List<User> users) {
        this.users = new ArrayList<>(users);
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return HEADERS.size();
    }

    @Override
    public String getColumnName(int column) {
        return HEADERS.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = user.getId();
                break;
            case 1:
                value = user.getFirstname();
                break;
            case 2:
                value = user.getLastname();
                break;
            case 3:
                value = user.getEmail();
                break;
            case 4:
                value = user.getPassword();
                break;
        }

        return value;

    }

    public User getUserAt(int row) {
        return users.get(row);
    }

    public void update(int rowIndex, User updateUser) {
        users.set(rowIndex, updateUser);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public void add(User userModel) {
        users.add(userModel);
        fireTableRowsInserted(users.size() - 1, users.size() - 1);
    }

    public void removeUser(int rowIndex) {
        users.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

}
