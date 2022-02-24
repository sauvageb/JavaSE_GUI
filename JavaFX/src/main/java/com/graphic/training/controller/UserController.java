package com.graphic.training.controller;

import com.graphic.training.dao.UserCrudDAO;
import com.graphic.training.dao.UserCrudDAOImpl;
import com.graphic.training.entity.User;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    TableView<User> usersTableView;
    @FXML
    TableColumn<User, Long> idCol;
    @FXML
    TableColumn<User, String> firstnameCol, lastnameCol, emailCol;
    @FXML
    TextField addFirstname, addLastname, addEmail, addPassword;
    @FXML
    ImageView headerImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        headerImageView.setImage(new Image(getClass().getResource("/user.png").toExternalForm()));
        headerImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                usersTableView.getSelectionModel().clearSelection();
            }
        });

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        displayUserList();


        TableView.TableViewSelectionModel<User> selectionModel = usersTableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        selectionModel.getSelectedItems().addListener(new ListChangeListener<User>() {
            @Override
            public void onChanged(Change<? extends User> change) {
                User userSelected = usersTableView.getSelectionModel().getSelectedItem();
                System.out.println(userSelected);

                addFirstname.setText(userSelected.getFirstname());
                addLastname.setText(userSelected.getLastname());
                addEmail.setText(userSelected.getEmail());
                addPassword.setText(userSelected.getPassword());
            }
        });
    }

    private void displayUserList() {
        UserCrudDAO userDao = new UserCrudDAOImpl();
        List<User> userList = userDao.findAll();
        if (userList.isEmpty()) {
            usersTableView.setPlaceholder(new Label("Aucun utilisateur"));
        }

        // Adding users in tableView
        ObservableList<User> userObservableList = usersTableView.getItems();
        userObservableList.addAll(userList);
    }

    @FXML
    public void createUser() throws IOException {
//        HelloApplication.setRoot("hello-view");
        UserCrudDAO userDao = new UserCrudDAOImpl();
        User newUser = new User(addFirstname.getText(), addLastname.getText(), addEmail.getText(), addPassword.getText());
        User insertedUser = userDao.save(newUser);
        // clear field
        addFirstname.clear();
        addLastname.clear();
        addEmail.clear();
        addPassword.clear();
        // Adding insertedUser in tableView
        ObservableList<User> userObservableList = usersTableView.getItems();
        userObservableList.add(insertedUser);
    }
}
