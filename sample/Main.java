package sample;

import Networks.*;
import data.AllFood;
import data.Restaurant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;

    public Stage getStage() {
        return stage;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        //connectToServer();
        showLoginPage();
    }

    private void connectToServer(String s) throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        networkUtil.write(s);
        //new ReadThread(this);
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.init();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 1100, 600));
        stage.show();
    }

    public void showHomePage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this);
        controller.init(userName);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 700, 800));
        stage.show();
    }

    public void showHomePageNext(String userName, AllFood food) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this);
        //controller.init(userName);
        controller.nxtHome(userName, food);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 700, 800));
        stage.show();
    }
    public void showExisting(String resName, AllFood o) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("showExisting.fxml"));
        Parent root = loader.load();

        ShowExisting controller = loader.getController();
        controller.setMain(this);
        controller.init(resName, o);
        stage.setTitle(resName.toUpperCase());
        stage.setScene(new Scene(root, 700, 800));
        stage.show();

    }
    public void showCusHome() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerHome.fxml"));
        Parent root = loader.load();

        CustomerHome controller = loader.getController();
        controller.setMain(this);
        controller.init();

        stage.setTitle("Customer Home");
        stage.setScene(new Scene(root, 1100, 800));
        stage.show();
    }
    public void showCusHomeSrc(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerHome.fxml"));
        Parent root = loader.load();

        CustomerHome controller = loader.getController();
        controller.setMain(this);
        controller.searchFoodName(name);

        stage.setTitle("Customer Home In");
        stage.setScene(new Scene(root, 1100, 800));
        stage.show();
    }
    public void showCusResInFood(String rName, String fName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerHome.fxml"));
        Parent root = loader.load();

        CustomerHome controller = loader.getController();
        controller.setMain(this);
        controller.searchByNameInRes(rName, fName);

        stage.setTitle("Customer Home In");
        stage.setScene(new Scene(root, 1100, 800));
        stage.show();
    }
    public void showCusResInCat(String rName, String cName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerHome.fxml"));
        Parent root = loader.load();

        CustomerHome controller = loader.getController();
        controller.setMain(this);
        controller.searchByCatInRes(rName, cName);

        stage.setTitle("Customer Home In");
        stage.setScene(new Scene(root, 1100, 800));
        stage.show();
    }
    public void showCusInCat(String cName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerHome.fxml"));
        Parent root = loader.load();

        CustomerHome controller = loader.getController();
        controller.setMain(this);
        controller.searchByCat(cName);

        stage.setTitle("Customer Home In");
        stage.setScene(new Scene(root, 1100, 800));
        stage.show();
    }
    public void showCusInPrice(String high, String low) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerHome.fxml"));
        Parent root = loader.load();

        CustomerHome controller = loader.getController();
        controller.setMain(this);
        controller.searchByPrice(high, low);

        stage.setTitle("Customer Home In");
        stage.setScene(new Scene(root, 1100, 800));
        stage.show();
    }
    public void showCusInRes(String resname) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerInRes.fxml"));
        Parent root = loader.load();
        CustomerInRes controller = loader.getController();
        controller.setMain(this);
        controller.initi(resname);

        stage.setTitle("Customer In " + resname.toUpperCase());
        stage.setScene(new Scene(root, 1100, 800));
        stage.show();
    }
    public void showResOrder(String resName, AllFood o, AllFood c) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ResOrders.fxml"));
        Parent root = loader.load();
        ResOrders controller = loader.getController();
        controller.setMain(this);
        controller.init(resName, o, c);

        stage.setTitle(resName.toUpperCase() + " Orders");
        stage.setScene(new Scene(root, 700, 800));
        stage.show();
    }
    public Restaurant loadFood(String resName)
    {
        try{
            networkUtil.write(new Message(MessageHeader.RES_INFO, resName));
            Object obj = networkUtil.read();
            if(obj instanceof Restaurant)
            {
                return (Restaurant) obj;
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AllFood loadFoodByName(String name)
    {
        try{
            networkUtil.write(new Message(MessageHeader.SEARCHBYNAME, name));
            Object obj = networkUtil.read();
            if(obj instanceof AllFood)
            {
                return (AllFood) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public AllFood loadFoodByCatInRes(String rName, String catName)
    {
        try{
            networkUtil.write(new DualMsg(MessageHeader.SEARCHBYCATINRES, rName, catName));
            Object obj = networkUtil.read();
            if(obj instanceof AllFood)
            {
                return (AllFood) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public AllFood loadFoodByPrice(String high, String low)
    {
        try{
            networkUtil.write(new DualMsg(MessageHeader.SEARCHBYPRICE, high, low));
            Object obj = networkUtil.read();
            if(obj instanceof AllFood)
            {
                return (AllFood) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public AllFood getOrder() throws IOException, ClassNotFoundException {
        Object obj = networkUtil.read();
        if(obj instanceof AllFood)
        {
            return (AllFood) obj;
        }
        else if(obj == null)
        {
            return null;
        }
        else {
            return null;
        }
    }
    public void sendOrder(AllFood f, String res) throws IOException {
        OrderList o = new OrderList(res, MessageHeader.ORDER, f);
        networkUtil.write(o);
    }
    public AllFood loadFoodByCat(String catName)
    {
        try{
            networkUtil.write(new Message(MessageHeader.SEARCHBYCAT, catName));
            Object obj = networkUtil.read();
            if(obj instanceof AllFood)
            {
                return (AllFood) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public AllFood loadFoodInRes(String rName, String fName)
    {
        try{
            networkUtil.write((new DualMsg(MessageHeader.SEARCHBYRESINNAME, rName, fName)));
            Object obj = networkUtil.read();
            if(obj instanceof AllFood)
            {
                return (AllFood) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public AllFood loadAll()
    {
        try {
            networkUtil.write(new Message(MessageHeader.ALLFOOD, "get"));
            Object obj = networkUtil.read();
            if(obj instanceof AllFood)
            {
                return (AllFood) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void loginRes(String username, String password) throws IOException {
        connectToServer(username);
        LoginInfo loginInfo = new LoginInfo(MessageHeader.LOGIN, username, password);
        try {
            networkUtil.write(loginInfo);
            Object obj = networkUtil.read();
            if (obj instanceof LoginInfo) {
                LoginInfo l = (LoginInfo) obj;
                System.out.println(l.getUsername());
                System.out.println(l.isStatus());
                //Platform.runLater(new Runnable() {
                //@Override
                //public void run() {
                if (l.isStatus()) {
                    if(l.getCus())
                    {
                        System.out.println("customer logged in");
                        showCusHome();
                    }
                    else {
                        try {
                            showHomePage(username);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    showAlert();
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}
