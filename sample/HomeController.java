package sample;

import data.AllFood;
import data.Food;
import data.Restaurant;
import data.RestaurantDatabase;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.*;

public class HomeController {

    @FXML
    public Label test;
    public TableView <Food> table;
    public TableColumn <Food, Integer> id;
    public TableColumn <Food, String> name;
    public TableColumn <Food, String> category;
    public TableColumn <Food, Double> price;
    public ImageView logo;
    @FXML
    public Button check;
    @FXML
    public Label rem;

    //public Label tst;
    @FXML
    public Button ffm;

    private Main main;
    private Restaurant res;
    private String resName;
    private List<Food> foodOnRes;

    @FXML
    private Label message;
    private AllFood food = new AllFood();

    //private AllFood fn = new AllFood();

    @FXML
    private Button button;
    void setMain(Main main) {
        this.main = main;
    }
    public void init(String msg) {
        message.setText(msg.toUpperCase());
        message.setTextAlignment(TextAlignment.JUSTIFY);
        this.resName = msg;
        //Image img = new Image(Main.class.getResourceAsStream("2.png"));
        //image.setImage(img);
        //List<Food> flist = main.loadFood(msg);
        //String m = flist.get(0).getFoodName();
        //Restaurant r = main.loadFood(msg);
        Image img = new Image(Objects.requireNonNull(Main.class.getResourceAsStream(msg + ".png")));
        logo.setImage(img);
        loadResData();
        List<Food> flist = res.getFoods();
        ObservableList<Food> list = FXCollections.observableArrayList();
        String m = flist.get(0).getFoodName();

        for(Food f: flist)
        {
            list.add(f);
        }
        id.setCellValueFactory(new PropertyValueFactory<Food, Integer>("FoodId"));
        name.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
        category.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodCategory"));
        price.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
        table.setItems(list);
        //test.setText(flist.get(0).getFoodName());
    }
    public void nxtHome(String msg, AllFood o) {
        message.setText(msg.toUpperCase());
        message.setTextAlignment(TextAlignment.JUSTIFY);
        this.resName = msg;
        //Image img = new Image(Main.class.getResourceAsStream("2.png"));
        //image.setImage(img);
        //List<Food> flist = main.loadFood(msg);
        //String m = flist.get(0).getFoodName();
        //Restaurant r = main.loadFood(msg);
        Image img = new Image(Objects.requireNonNull(Main.class.getResourceAsStream(msg + ".png")));
        logo.setImage(img);
        loadResData();
        List<Food> flist = res.getFoods();
        ObservableList<Food> list = FXCollections.observableArrayList();
        String m = flist.get(0).getFoodName();

        for(Food f: flist)
        {
            list.add(f);
        }
        id.setCellValueFactory(new PropertyValueFactory<Food, Integer>("FoodId"));
        name.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
        category.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodCategory"));
        price.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
        table.setItems(list);
        //test.setText(flist.get(0).getFoodName());
        if(!o.getFL().isEmpty()) {
            List<Food> fnew = o.getFL();
            for (Food f : fnew) {
                food.AddFood(f);
            }
            //tst.setText(fnew.get(0).getFoodName());
        }
    }
    private void loadResData()
    {
        this.res = main.loadFood(resName);
    }
    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void viewOrder(ActionEvent event) throws Exception {
        AllFood fn = this.main.getOrder();
            //List<Food> f = food.getFL();
            //String na = f.get(0).getFoodName();
            //rem.setText(na);
        if(fn != null) {
            this.main.showResOrder(resName, fn, food);
        }
        else
        {
            this.main.showHomePage(resName);
        }
    }
    @FXML
    void viewExisting(ActionEvent event) throws IOException {
        this.main.showExisting(resName, food);
    }


}
