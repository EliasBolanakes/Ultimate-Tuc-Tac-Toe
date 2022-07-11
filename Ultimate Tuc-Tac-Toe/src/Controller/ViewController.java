package Controller;

import View.GameBoardSmall;
import View.LoadScreen;
import View.MainStage;
import View.MenuScreen;
import javafx.scene.layout.Pane;

public class ViewController {

    MainStage mainStage;
    LoadScreen loadScreen;
    MenuScreen menuScreen;
    public GameBoardSmall gameBoardSmall;

    protected Pane loadScreenPane;
    protected Pane menuPane;
    protected Pane gameBoardPane;

    public ViewController(MainStage mainStage){

        this.mainStage = mainStage;

        loadScreenPane = new Pane();
        menuPane = new Pane();
        gameBoardPane = new Pane();

        loadScreen = new LoadScreen(loadScreenPane,mainStage);
        menuScreen = new MenuScreen(menuPane,mainStage);
        gameBoardSmall = new GameBoardSmall(gameBoardPane,mainStage);

        mainStage.stage.setScene(loadScreen);
    }

    public void showMenu(){
        menuScreen.pane.setPrefHeight(loadScreen.pane.getHeight());
        menuScreen.pane.setPrefWidth(loadScreen.pane.getWidth());
        mainStage.stage.setScene(menuScreen);
    }

    public void playButton(){
        gameBoardSmall.pane.setPrefWidth(menuScreen.getWidth());
        gameBoardSmall.pane.setPrefHeight(menuScreen.getHeight());
        mainStage.stage.setScene(gameBoardSmall);
    }
}
