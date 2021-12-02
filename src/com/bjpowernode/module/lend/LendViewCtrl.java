package com.bjpowernode.module.lend;

import com.bjpowernode.service.LendService;
import com.bjpowernode.service.UserService;
import com.bjpowernode.service.impl.LendServiceImpl;
import com.bjpowernode.service.impl.UserServiceImpl;
import com.gn.App;
import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.Lend;
import com.bjpowernode.bean.User;
import com.bjpowernode.global.util.Alerts;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * ͼ�����
 *
 * @author admin
 */
public class LendViewCtrl implements Initializable {

    @FXML
    private TableView<Lend> lendTableView;
    @FXML
    private TableColumn<Lend, String> c1;
    @FXML
    private TableColumn<Lend,String> c2;
    @FXML
    private TableColumn<Lend, String> c3;
    @FXML
    private TableColumn<Lend, String> c4;
    @FXML
    private TableColumn<Lend, String> c5;
    @FXML
    private TableColumn<Lend, String> c6;
    @FXML
    private TableColumn<Lend, String> c7;

    @FXML
    private TextField lendNameField;

    @FXML
    private TextField isbnField;

    ObservableList<Lend> lends = FXCollections.observableArrayList();
    private LendService lendService = new LendServiceImpl();
    private UserService userService = new UserServiceImpl();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Lend> lendList = lendService.select(null);
        //�����鼮���ں�����
        lendList.forEach(d-> {
            LocalDate returnDate = d.getReturnDate();
            LocalDate now = LocalDate.now();
            //����ʱ���
            Period period = Period.between(returnDate, now);
            User user = d.getUser();
            BigDecimal money = user.getMoney(); //��ǰ���
            BigDecimal delay = BigDecimal.ZERO; //�ۿ���
            if (period.getDays() >= 1) {
                /*
                    ����
                    ��30��ۿ�30  δ��30��һ���һԪ
                    �û�����Ƿ����0��С���򶳽�
                 */
                if (period.getDays() >= 30) {
                    delay = new BigDecimal(30);
                } else {
                    delay = new BigDecimal(period.getDays());
                }
                //�ۿ�
                user.setMoney(money.subtract(delay));
                //�黹���ڸ�Ϊ����
                d.setReturnDate(now);
                //�ж����  �Ƿ���Ҫ�����û�
                if (BigDecimal.ZERO.compareTo(user.getMoney()) > 0) {
                    user.setStatus(Constant.USER_FROZEN);
                }
                //��Service�� ������
                lendService.update(d);
                userService.update(user);
            }
        });



        lends.addAll(lendList);

        c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        //��ȡͼ������
        c2.setCellValueFactory((TableColumn.CellDataFeatures<Lend, String> p) ->
            new SimpleObjectProperty(p.getValue().getBook().getBookName())
        );
        c3.setCellValueFactory((TableColumn.CellDataFeatures<Lend, String> p) ->
                new SimpleObjectProperty(p.getValue().getBook().getIsbn())
        );
        c4.setCellValueFactory((TableColumn.CellDataFeatures<Lend, String> p) ->
                new SimpleObjectProperty(p.getValue().getUser().getName())
        );
        c5.setCellValueFactory(new PropertyValueFactory<>("lendDate"));
        c6.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        c7.setCellValueFactory(new PropertyValueFactory<>("status"));
        lendTableView.setItems(lends);

    }


    /*
        ��ѯ
     */
    @FXML
    private void lendSelect(){
        String lendName = lendNameField.getText();
        String isbn = isbnField.getText();
        boolean lendFlag = "".equals(lendName);
        boolean isbnFlag = "".equals(isbn);

        Lend lend = new Lend();
        Book book = new Book();
        book.setBookName(lendName);
        book.setIsbn(isbn);

        lend.setBook(book);
        //������ѯ
        List<Lend> lendList = lendService.select(lend);
        lends = new ObservableListWrapper<Lend>(new ArrayList<Lend>(lendList));
        lendTableView.setItems(lends);
    }

    /*
        ����
     */
    @FXML
    private void returnBook(){
        Lend lend = this.lendTableView.getSelectionModel().getSelectedItem();
        if (lend == null){
            Alerts.warning("δѡ��","����ѡ��Ҫ�黹���鼮");
            return;
        }
        List<Lend> lendList = lendService.returnBook(lend);

        ObservableListWrapper<Lend> lends = new ObservableListWrapper<>(new ArrayList<>(lendList));
        lendTableView.setItems(lends);
    }

    /*
        ����
     */
    @FXML
    private void renew(){
        Lend lend = this.lendTableView.getSelectionModel().getSelectedItem();
        if (lend == null){
            Alerts.warning("δѡ��","����ѡ��Ҫ������鼮");
            return;
        }
        lend.setReturnDate(LocalDate.now().plusDays(30));
    }


    /*
        ��ʼ��stage
     */
    private void initStage(Lend lend) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/com/bjpowernode/module/lend/LendHandleView.fxml"));
        StackPane target = (StackPane) loader.load();
        //Scene scene1 = App.getDecorator().getScene();
        Scene scene = new Scene(target);


        Stage stage = new Stage();//������̨��
        LendHandleViewCtrl controller = (LendHandleViewCtrl)loader.getController();
        controller.setStage(stage);
        controller.setLends(lends);
        controller.setLend(lend);
        controller.setLendTableView(lendTableView);
//        stage.setResizable(false);
        stage.setHeight(800);
        stage.setWidth(700);
        //���ô���ͼ��
        stage.getIcons().add(new Image("icon.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene); //������������̨��
        stage.show(); //��ʾ���ڣ�
    }
}
