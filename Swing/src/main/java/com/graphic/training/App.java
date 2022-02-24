package com.graphic.training;


import com.graphic.training.dao.UserCrudDAO;
import com.graphic.training.dao.UserCrudDAOImpl;
import com.graphic.training.entity.User;

import java.util.List;

public class App {
    public static void main(String[] args) {
        UserCrudDAO userDao = new UserCrudDAOImpl();
        List<User> m = userDao.findAll();

        UserFormView v = new UserFormView("MVC with Swing");
        UserFormController c = new UserFormController(m, v);
        c.initController();
    }
}
