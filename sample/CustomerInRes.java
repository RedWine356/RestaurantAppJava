package sample;

import data.AllFood;
import data.Food;
import data.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerInRes {
    @FXML
    public TableColumn<Food, Integer> rID;
    @FXML
    public TableColumn<Food, String> fcat;
    @FXML
    public TableColumn<Food, String> fname;
    @FXML
    public TableColumn<Food, Double> fprice;
    @FXML
    public Button logout;
    @FXML
    public Button back;
    public ImageView img;
    @FXML
    public TableView<Food> tbl;
    @FXML
    public Label edd;
    @FXML
    public Button ck;
    @FXML
    public Button csk;
    @FXML
    public TableView<Food> tbl2;
    @FXML
    public TableColumn<Food, String> itemName;
    @FXML
    public TableColumn<Food, Double> itemPrice;
    @FXML
    public Button fn;

    private Main main;
    private Restaurant res;

    private AllFood food;
    private String name;
    private List<Food> flist = new ArrayList<>();

    void setMain(Main main){this.main = main; }
    public void initi(String s)
    {
        this.name = s;
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(s + ".png")));
        img.setImage(image);
        loadFoodForRes(s);
        List<Food> f = res.getFoods();
        ObservableList<Food> list = FXCollections.observableArrayList();
        //String m = f.get(0).getFoodName();

        for(Food food: f)
        {
            list.add(food);
        }
        rID.setCellValueFactory(new PropertyValueFactory<Food, Integer>("FoodId"));
        fname.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
        fcat.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodCategory"));
        fprice.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
        tbl.setItems(list);

        /*TableView.TableViewSelectionModel<Food> selectionModel = tbl.getSelectionModel();
        ObservableList<Food> selected = selectionModel.getSelectedItems();
        String mm = selected.get(0).getFoodName();
        edd.setText(mm);*/
        //List<Food> teest = new ArrayList<>();
        /*for(Food fd: selected)
        {
            teest.add(fd);
        }
        String mm = teest.get(0).getFoodName();
        edd.setText(mm);*/
    }
    public void loadFoodForRes(String s)
    {
        this.res = this.main.loadFood(s);
    }
    @FXML
    void logoutAction(ActionEvent event) throws Exception {
        main.showLoginPage();
    }
    @FXML
    void backToHomeAction(ActionEvent event) throws IOException {
        main.showCusHome();
    }
    @FXML
    void retrieveSelectedItem(ActionEvent event) {
        TableView.TableViewSelectionModel<Food> selectionModel = tbl.getSelectionModel();
        ObservableList<Food> selected = selectionModel.getSelectedItems();
        flist.add(selected.get(0));
        /*if (!selected.isEmpty()) {
            String mm = selected.get(0).getFoodName();
            edd.setText(mm);
        } else {
            // Handle the case where no items are selected
            edd.setText("No item selected");
        }*/
    }
    void loadAll()
    {
        this.food = new AllFood(flist);
    }

    @FXML
    void loadList(ActionEvent event)
    {
        ObservableList<Food> list1 = FXCollections.observableArrayList();
        for(Food f : flist)
        {
            list1.add(f);
        }
        itemName.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
        tbl2.setItems(list1);
    }
    @FXML
    void order(ActionEvent event) throws IOException {
        loadAll();
        this.main.sendOrder(food, name);
        flist.clear();
        this.main.showCusInRes(name);
    }
}
