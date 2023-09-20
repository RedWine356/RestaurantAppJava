package sample;

import data.AllFood;
import data.Food;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ResOrders {
    @FXML
    public Label rsnm;
    @FXML
    public TableView<Food> tabl;
    @FXML
    public TableColumn<Food, String> cat;
    @FXML
    public TableColumn<Food, String> nm;

    @FXML
    public TableColumn<Food, Double> prc;
    @FXML
    public Button homeBt;
    @FXML
    public Button orDone;
    @FXML
    public Button logOut;
    @FXML
    public Label test;
    @FXML
    public TableView<Food> tabl1;
    public TableColumn<Food, String> cat1;
    public TableColumn<Food, String> nm1;
    public TableColumn<Food, Double> prc1;

    private List<Food> All;
    private String naaam;
    private Main main;

    private AllFood n = new AllFood();
    void setMain(Main main){this.main = main; }

    public void init(String name, AllFood o, AllFood c)
    {
        if(!c.getFL().isEmpty())
        {
            this.n = c;
            List <Food> f = n.getFL();
            String m = f.get(0).getFoodName();
            test.setText(m);
            ObservableList<Food> ls = FXCollections.observableArrayList();
            for(Food fd : f)
            {
                ls.add(fd);
            }
            cat1.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodCategory"));
            nm1.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
            prc1.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
            tabl1.setItems(ls);

        }
        this.naaam = name;
        rsnm.setText(name.toUpperCase());
        if(o != null && (!o.getFL().isEmpty())) {
            ObservableList<Food> flist = FXCollections.observableArrayList();
            List<Food> fm = o.getFL();

            for (Food m : fm) {
                flist.add(m);
                n.AddFood(m);
            }

            cat.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodCategory"));
            nm.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
            prc.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
            tabl.setItems(flist);
        }


    }

    @FXML
    void backToHome(ActionEvent event) throws Exception {

        this.main.showHomePageNext(naaam, n);
    }

    @FXML
    void OrderDone(ActionEvent event) throws Exception {
        n.clr();
        AllFood o = new AllFood();
        this.main.showResOrder(naaam,o,n);
    }

}
