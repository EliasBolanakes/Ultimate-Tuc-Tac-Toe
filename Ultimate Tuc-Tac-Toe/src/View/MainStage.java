package View;

import Controller.ViewController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainStage extends Application {


    public Stage stage;
    protected ViewController VS;

    @Override
    public void start(Stage stage) {

        this.stage = stage;
        VS = new ViewController(this);
        stage.setTitle("Ultimate Tuc-Tac-Toe");
        stage.show();
        stage.setMinHeight(600);
        stage.setMinWidth(900);
        stage.setFullScreen(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
