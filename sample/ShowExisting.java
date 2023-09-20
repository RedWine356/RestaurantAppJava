package sample;

import data.AllFood;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import data.Food;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class ShowExisting {
    @FXML
    public Button bHome;
    public Label Rname;
    public TableView < Food > tbll;
    public TableColumn <Food, String> cat;
    public TableColumn <Food, String> name;
    public TableColumn <Food, Double> prc;
    public Button done;

    private Main main;
    public void setMain(Main main){this.main = main; }

    public AllFood all;

    public String resName;

    void init(String s, AllFood o)
    {
        this.resName = s;
        this.all = o;
        Rname.setText(s);
        List<Food> ls = o.getFL();
        if(!ls.isEmpty()) {
            ObservableList<Food> lst = FXCollections.observableArrayList();
            for(Food f: ls)
            {
                lst.add(f);
            }
            cat.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodCategory"));
            name.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
            prc.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
            tbll.setItems(lst);
        }
    }

    @FXML
    void btoHome(ActionEvent event) throws Exception {
        this.main.showHomePageNext(resName, all);
    }
    @FXML
    void OrderDo(ActionEvent event) throws IOException {
        all.clr();
        AllFood f = new AllFood();
        this.main.showExisting(resName, f);
    }
}
