package sample;

import data.AllFood;
import data.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CustomerHome {
    @FXML
    public Label customer;
    @FXML
    public ImageView kfc;
    @FXML
    public ImageView ihop;

    @FXML
    public ImageView starbucks;
    @FXML
    public ImageView mcdonalds;
    @FXML
    public Button logout;
    public Label desc;
    public Label test;
    public TableView<Food> table;
    public TableColumn<Food, Integer> id;
    public TableColumn<Food, String> cat;
    public TableColumn<Food, String> name;
    public TableColumn<Food, Double> price;
    @FXML
    public Label namesrc;
    @FXML
    public TextField srchbyname;
    @FXML
    public Button srcName;
    @FXML
    public Label srchbynameinres;
    @FXML
    public Label rname;
    @FXML
    public Label fnameres;
    @FXML
    public TextField rnamein;
    @FXML
    public TextField rfoodin;
    public Button rSrc;
    @FXML
    public Label srchbyCatinres;
    @FXML
    public Label rname1;
    @FXML
    public TextField rnamein1;
    @FXML
    public Label fnameres1;
    @FXML
    public TextField rCat1;
    @FXML
    public Button rSrcCat;
    @FXML
    public Label CatSrc;
    @FXML
    public TextField catText;
    @FXML
    public Button srcCat;
    @FXML
    public Button home;
    @FXML
    public TextField lower;
    @FXML
    public TextField high;
    @FXML
    public Button srcByPrice;
    @FXML
    public Button mm;
    @FXML
    public Button mm1;
    @FXML
    public Button mm11;
    @FXML
    public Button mm111;
    private AllFood all;
    private Main main;
    void setMain(Main main){ this.main = main; }
    public void init() {

        customer.setText("CUSTOMER");
        namesrc.setText("Search By Name");
        rname.setText("Restaurant Name");
        srchbynameinres.setText("Search Food in Restaurant");
        fnameres.setText("Food Name");
        //hit.setText(n);
        ImageView view = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png"))));
        view.setFitHeight(150);
        view.setPreserveRatio(true);
        desc.setText("We have food available of the following restaurants.Pick any restaurant to order food");
        //Image KFC = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("kfc.png")));
        //kfc.setImage(KFC);
        kfc = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("kfc.png"))));
        kfc.setFitHeight(150);
        kfc.setPreserveRatio(true);
        mm.setGraphic(kfc);
        mm.setPadding(Insets.EMPTY);
        //Image IHOP = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png")));
        //ihop.setImage(IHOP);
        ihop = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png"))));
        ihop.setFitHeight(150);
        ihop.setPreserveRatio(true);
        mm1.setGraphic(ihop);
        mm1.setPadding(Insets.EMPTY);
        //Image star = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("starbucks.png")));
        //starbucks.setImage(star);
        starbucks = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("starbucks.png"))));
        starbucks.setFitHeight(150);
        starbucks.setPreserveRatio(true);
        //mm11.setGraphic(true);
        mm11.setGraphic(starbucks);
        mm11.setPadding(Insets.EMPTY);
        //Image mc = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("mcdonalds.png")));
        //mcdonalds.setImage(mc);
        mcdonalds = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("mcdonalds.png"))));
        mcdonalds.setFitHeight(150);
        mcdonalds.setPreserveRatio(true);
        mm111.setGraphic(mcdonalds);
        mm111.setPadding(Insets.EMPTY);
        loadAllFood();
        List<Food> f = all.getFL();
        ObservableList <Food> list = FXCollections.observableArrayList();
        for(Food food : f)
        {
            list.add(food);
        }
        //String m = f.get(0).getFoodName();
        //test.setText(m);
        id.setCellValueFactory(new PropertyValueFactory<Food, Integer>("FoodId"));
        name.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
        cat.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodCategory"));
        price.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
        table.setItems(list);
    }

    public void searchFoodName(String nam)
    {
        customer.setText("CUSTOMER In Search");
        namesrc.setText("Search By Name");
        srchbyname.getText();
        desc.setText("We have food available of the following restaurants.Pick any restaurant to order food");
        kfc = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("kfc.png"))));
        kfc.setFitHeight(150);
        kfc.setPreserveRatio(true);
        mm.setGraphic(kfc);
        mm.setPadding(Insets.EMPTY);
        //Image IHOP = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png")));
        //ihop.setImage(IHOP);
        ihop = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png"))));
        ihop.setFitHeight(150);
        ihop.setPreserveRatio(true);
        mm1.setGraphic(ihop);
        mm1.setPadding(Insets.EMPTY);
        //Image star = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("starbucks.png")));
        //starbucks.setImage(star);
        starbucks = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("starbucks.png"))));
        starbucks.setFitHeight(150);
        starbucks.setPreserveRatio(true);
        //mm11.setGraphic(true);
        mm11.setGraphic(starbucks);
        mm11.setPadding(Insets.EMPTY);
        //Image mc = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("mcdonalds.png")));
        //mcdonalds.setImage(mc);
        mcdonalds = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("mcdonalds.png"))));
        mcdonalds.setFitHeight(150);
        mcdonalds.setPreserveRatio(true);
        mm111.setGraphic(mcdonalds);
        mm111.setPadding(Insets.EMPTY);
        loadByName(nam);
        List<Food> f = all.getFL();
        ObservableList <Food> list = FXCollections.observableArrayList();
        list.addAll(f);
        //String m = f.get(0).getFoodName();
        //test.setText(m);
        id.setCellValueFactory(new PropertyValueFactory<Food, Integer>("FoodId"));
        name.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
        cat.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodCategory"));
        price.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
        table.setItems(list);
    }

    public void searchByNameInRes(String rName, String fName)
    {
        customer.setText("CUSTOMER In Search");
        namesrc.setText("Search By Name");
        srchbyname.getText();
        desc.setText("We have food available of the following restaurants.Pick any restaurant to order food");
        kfc = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("kfc.png"))));
        kfc.setFitHeight(150);
        kfc.setPreserveRatio(true);
        mm.setGraphic(kfc);
        mm.setPadding(Insets.EMPTY);
        //Image IHOP = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png")));
        //ihop.setImage(IHOP);
        ihop = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png"))));
        ihop.setFitHeight(150);
        ihop.setPreserveRatio(true);
        mm1.setGraphic(ihop);
        mm1.setPadding(Insets.EMPTY);
        //Image star = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("starbucks.png")));
        //starbucks.setImage(star);
        starbucks = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("starbucks.png"))));
        starbucks.setFitHeight(150);
        starbucks.setPreserveRatio(true);
        //mm11.setGraphic(true);
        mm11.setGraphic(starbucks);
        mm11.setPadding(Insets.EMPTY);
        //Image mc = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("mcdonalds.png")));
        //mcdonalds.setImage(mc);
        mcdonalds = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("mcdonalds.png"))));
        mcdonalds.setFitHeight(150);
        mcdonalds.setPreserveRatio(true);
        mm111.setGraphic(mcdonalds);
        mm111.setPadding(Insets.EMPTY);
        loadByNameInRes(rName, fName);
        List<Food> f = all.getFL();
        ObservableList <Food> list = FXCollections.observableArrayList();
        list.addAll(f);
        //String m = f.get(0).getFoodName();
        //test.setText(m);
        id.setCellValueFactory(new PropertyValueFactory<Food, Integer>("FoodId"));
        name.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
        cat.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodCategory"));
        price.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
        table.setItems(list);
    }

    public void searchByCatInRes(String rName, String cName)
    {
        customer.setText("CUSTOMER In Search");
        namesrc.setText("Search By Name");
        srchbyname.getText();
        desc.setText("We have food available of the following restaurants.Pick any restaurant to order food");
        kfc = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("kfc.png"))));
        kfc.setFitHeight(150);
        kfc.setPreserveRatio(true);
        mm.setGraphic(kfc);
        mm.setPadding(Insets.EMPTY);
        //Image IHOP = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png")));
        //ihop.setImage(IHOP);
        ihop = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png"))));
        ihop.setFitHeight(150);
        ihop.setPreserveRatio(true);
        mm1.setGraphic(ihop);
        mm1.setPadding(Insets.EMPTY);
        //Image star = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("starbucks.png")));
        //starbucks.setImage(star);
        starbucks = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("starbucks.png"))));
        starbucks.setFitHeight(150);
        starbucks.setPreserveRatio(true);
        //mm11.setGraphic(true);
        mm11.setGraphic(starbucks);
        mm11.setPadding(Insets.EMPTY);
        //Image mc = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("mcdonalds.png")));
        //mcdonalds.setImage(mc);
        mcdonalds = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("mcdonalds.png"))));
        mcdonalds.setFitHeight(150);
        mcdonalds.setPreserveRatio(true);
        mm111.setGraphic(mcdonalds);
        mm111.setPadding(Insets.EMPTY);
        loadByCatInRes(rName, cName);
        List<Food> f = all.getFL();
        ObservableList <Food> list = FXCollections.observableArrayList();
        list.addAll(f);
        //String m = f.get(0).getFoodName();
        //test.setText(m);
        id.setCellValueFactory(new PropertyValueFactory<Food, Integer>("FoodId"));
        name.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
        cat.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodCategory"));
        price.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
        table.setItems(list);
    }

    public void searchByCat(String cName)
    {
        customer.setText("CUSTOMER In Search");
        namesrc.setText("Search By Name");
        srchbyname.getText();
        desc.setText("We have food available of the following restaurants.Pick any restaurant to order food");
        kfc = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("kfc.png"))));
        kfc.setFitHeight(150);
        kfc.setPreserveRatio(true);
        mm.setGraphic(kfc);
        mm.setPadding(Insets.EMPTY);
        //Image IHOP = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png")));
        //ihop.setImage(IHOP);
        ihop = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png"))));
        ihop.setFitHeight(150);
        ihop.setPreserveRatio(true);
        mm1.setGraphic(ihop);
        mm1.setPadding(Insets.EMPTY);
        //Image star = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("starbucks.png")));
        //starbucks.setImage(star);
        starbucks = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("starbucks.png"))));
        starbucks.setFitHeight(150);
        starbucks.setPreserveRatio(true);
        //mm11.setGraphic(true);
        mm11.setGraphic(starbucks);
        mm11.setPadding(Insets.EMPTY);
        //Image mc = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("mcdonalds.png")));
        //mcdonalds.setImage(mc);
        mcdonalds = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("mcdonalds.png"))));
        mcdonalds.setFitHeight(150);
        mcdonalds.setPreserveRatio(true);
        mm111.setGraphic(mcdonalds);
        mm111.setPadding(Insets.EMPTY);
        loadByCat(cName);
        List<Food> f = all.getFL();
        ObservableList <Food> list = FXCollections.observableArrayList();
        list.addAll(f);
        //String m = f.get(0).getFoodName();
        //test.setText(m);
        id.setCellValueFactory(new PropertyValueFactory<Food, Integer>("FoodId"));
        name.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
        cat.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodCategory"));
        price.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
        table.setItems(list);
    }

    public void searchByPrice(String high, String low)
    {
        customer.setText("CUSTOMER In Search");
        namesrc.setText("Search By Name");
        srchbyname.getText();
        desc.setText("We have food available of the following restaurants.Pick any restaurant to order food");
        kfc = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("kfc.png"))));
        kfc.setFitHeight(150);
        kfc.setPreserveRatio(true);
        mm.setGraphic(kfc);
        mm.setPadding(Insets.EMPTY);
        //Image IHOP = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png")));
        //ihop.setImage(IHOP);
        ihop = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ihop.png"))));
        ihop.setFitHeight(150);
        ihop.setPreserveRatio(true);
        mm1.setGraphic(ihop);
        mm1.setPadding(Insets.EMPTY);
        //Image star = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("starbucks.png")));
        //starbucks.setImage(star);
        starbucks = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("starbucks.png"))));
        starbucks.setFitHeight(150);
        starbucks.setPreserveRatio(true);
        //mm11.setGraphic(true);
        mm11.setGraphic(starbucks);
        mm11.setPadding(Insets.EMPTY);
        //Image mc = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("mcdonalds.png")));
        //mcdonalds.setImage(mc);
        mcdonalds = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("mcdonalds.png"))));
        mcdonalds.setFitHeight(150);
        mcdonalds.setPreserveRatio(true);
        mm111.setGraphic(mcdonalds);
        mm111.setPadding(Insets.EMPTY);
        loadByPrice(high, low);
        List<Food> f = all.getFL();
        ObservableList <Food> list = FXCollections.observableArrayList();
        list.addAll(f);
        //String m = f.get(0).getFoodName();
        //test.setText(m);
        id.setCellValueFactory(new PropertyValueFactory<Food, Integer>("FoodId"));
        name.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodName"));
        cat.setCellValueFactory(new PropertyValueFactory<Food, String>("FoodCategory"));
        price.setCellValueFactory(new PropertyValueFactory<Food, Double>("FoodPrice"));
        table.setItems(list);
    }
    public void loadAllFood()
    {
        this.all = this.main.loadAll();
    }

    public void loadByName(String s)
    {
        this.all = this.main.loadFoodByName(s);
    }

    public void loadByNameInRes(String rName, String fName)
    {
        this.all = this.main.loadFoodInRes(rName, fName);
    }
    public void loadByCatInRes(String rName, String cName)
    {
        this.all = this.main.loadFoodByCatInRes(rName, cName);
    }
    public void loadByCat(String cat)
    {
        this.all = this.main.loadFoodByCat(cat);
    }
    public void loadByPrice(String high, String low)
    {
        this.all = this.main.loadFoodByPrice(high, low);
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
    void srcNameAction(ActionEvent event) {
        try{
            //String m = myBox.getValue();
            //hit.setText(m);
            String text = srchbyname.getText();
            main.showCusHomeSrc(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void srcNameinResAction(ActionEvent event) throws IOException {
        String resN = rnamein.getText();
        String fN = rfoodin.getText();
        main.showCusResInFood(resN, fN);
    }

    @FXML
    void srcCatInResAction(ActionEvent event) throws IOException {
        String resN = rnamein1.getText();
        String catN = rCat1.getText();
        main.showCusResInCat(resN, catN);
    }

    @FXML
    void srcCatAction(ActionEvent event) throws IOException {
        String catN = catText.getText();
        main.showCusInCat(catN);
    }
    @FXML
    void backToHomeAction(ActionEvent event) throws IOException {
        main.showCusHome();
    }
    @FXML
    void srcByPriceAction(ActionEvent event) throws IOException {
        String low = lower.getText();
        String hig = high.getText();
        main.showCusInPrice(hig, low);
    }
    @FXML
    void srcKFC(ActionEvent event) throws IOException {
        main.showCusInRes("kfc");
    }
    @FXML
    void srcIHOP(ActionEvent event) throws IOException {
        main.showCusInRes("ihop");
    }
    @FXML
    void srcSTAR(ActionEvent event) throws IOException {
        main.showCusInRes("starbucks");
    }
    @FXML
    void srcMC(ActionEvent event) throws IOException {
        main.showCusInRes("mcdonalds");
    }
    }
