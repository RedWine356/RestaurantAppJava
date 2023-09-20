package sample;

import Networks.LoginInfo;
import Networks.MessageHeader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.LoginDTO;

import java.io.IOException;


public class LoginController {

    private Main main;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    ImageView img;

    @FXML
    void init()
    {
        Image img1 = new Image(Main.class.getResourceAsStream("login1.png"));
        img.setImage(img1);
    }
    @FXML
    void loginAction(ActionEvent event) throws IOException {
        String userName = userText.getText();
        String password = passwordText.getText();
        LoginInfo loginInfo = new LoginInfo(MessageHeader.LOGIN, userName, password);
        //loginInfo.setUsername(userName);
        //loginInfo.setPassword(password);
        if(userName == null | password.isBlank())
        {
            showAlert("LogIn");
        }
        else {
            main.loginRes(userName, password);
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }
    private void showAlert(String header) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error");
        a.setHeaderText(header + " not successful");
        a.setContentText("Username or password field cannot be empty.");
        a.showAndWait();
    }

    void setMain(Main main) {
        this.main = main;
    }

}
