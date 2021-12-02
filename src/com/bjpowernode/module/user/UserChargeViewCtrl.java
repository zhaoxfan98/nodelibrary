package com.bjpowernode.module.user;

import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.User;
import com.bjpowernode.global.util.Alerts;
import com.bjpowernode.service.UserService;
import com.bjpowernode.service.impl.UserServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class UserChargeViewCtrl {

    @FXML
    private TextField moneyField;

    private Stage stage;

    private User user;

    private TableView<User> userTableView;
    private UserService userService = new UserServiceImpl();

    /*
        ��ֵ
     */
    @FXML
    private void charge() {
        try {
            //���γ�ֵ�Ľ��
            BigDecimal money = new BigDecimal(moneyField.getText());
            User chargeUser = userService.charge(user, money);
            user = chargeUser;

            userTableView.refresh();
            stage.close();
            Alerts.success("�ɹ�", "��ֵ�ɹ�");
        } catch (Exception e) {
            e.printStackTrace();
            Alerts.error("ʧ��","����ʧ��");
        }

    }

    @FXML
    private void closeView() {
        stage.close();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public TableView<User> getUserTableView() {
        return userTableView;
    }

    public void setUserTableView(TableView<User> userTableView) {
        this.userTableView = userTableView;
    }
}
