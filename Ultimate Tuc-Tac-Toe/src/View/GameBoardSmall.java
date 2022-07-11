package View;

import Controller.GameController;
import Model.Game.Board;
import javafx.animation.Animation;
import javafx.animation.StrokeTransition;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameBoardSmall extends Scene {

    MainStage mainStage;
    public Text title;
    public Pane pane;
    Text XSign,OSign;
    public Button[][] btn;
    public StrokeTransition XTrans,OTrans,TitleTrans;
    GameController GC;


    public GameBoardSmall(Parent parent,MainStage mainStage) {

        super(parent);
        pane = (Pane) parent;
        this.mainStage = mainStage;
        GC = new GameController(this);
        btn = new Button[3][3];

        title = new Text("X TURN");
        title.setFont(new Font(100));
        title.setFill(Color.HONEYDEW);
        title.setStroke(Color.TRANSPARENT);
        title.setStrokeWidth(5);

        title.xProperty().bind(pane.widthProperty().divide(2).
                subtract(title.getLayoutBounds().getWidth()/2));

        title.yProperty().bind(pane.heightProperty().divide(6));

        XSign = new Text("X");
        OSign = new Text("O");
        XSign.setFont(new Font(120));
        OSign.setFont(new Font(120));
        XSign.setFill(Color.DARKRED);
        OSign.setFill(Color.MIDNIGHTBLUE);
        XSign.setStroke(Color.DARKRED);
        OSign.setStroke(Color.MIDNIGHTBLUE);
        XSign.setStrokeWidth(5);
        OSign.setStrokeWidth(5);

        XSign.translateXProperty().bind(pane.widthProperty().divide(8).subtract(XSign.getLayoutBounds().getWidth()/2));
        OSign.translateXProperty().bind(pane.widthProperty().multiply(7/8.0).subtract(OSign.getLayoutBounds().getWidth()/2));

        XSign.translateYProperty().bind(pane.heightProperty().divide(6));
        OSign.translateYProperty().bind(pane.heightProperty().divide(6));

        for(int i=0;i<btn.length;i++){
            for(int j=0;j<btn[i].length;j++){

                int finalI = i;
                int finalJ = j;
                btn[i][j] = new Button(" ");
                btn[i][j].setStyle("-fx-border-color: white;");
                btn[i][j].setBackground(new Background(
                        new BackgroundFill(Color.TRANSPARENT, new CornerRadii(0), Insets.EMPTY)));
                pane.getChildren().add(btn[i][j]);
                btn[i][j].prefHeightProperty().bind(pane.heightProperty().divide(4));
                btn[i][j].prefWidthProperty().bind(pane.widthProperty().divide(6));
                btn[i][j].setOnAction(actionEvent -> GC.buttonAction(btn[finalI][finalJ],finalI,finalJ));
            }
        }

        btn[1][1].translateXProperty().bind(pane.widthProperty().divide(2).
                subtract(btn[1][1].widthProperty().divide(2)));

        btn[1][1].translateYProperty().bind(pane.heightProperty().divide(1.65).
                subtract(btn[1][1].heightProperty().divide(2)));

        btn[0][0].translateXProperty().bind(btn[1][1].translateXProperty().subtract(btn[1][1].widthProperty()));
        btn[0][0].translateYProperty().bind(btn[1][1].translateYProperty().subtract(btn[1][1].heightProperty()));

        btn[0][1].translateXProperty().bind(btn[1][1].translateXProperty());
        btn[0][1].translateYProperty().bind(btn[1][1].translateYProperty().subtract(btn[1][1].heightProperty()));

        btn[0][2].translateXProperty().bind(btn[1][1].translateXProperty().add(btn[1][1].widthProperty()));
        btn[0][2].translateYProperty().bind(btn[1][1].translateYProperty().subtract(btn[1][1].heightProperty()));

        btn[1][0].translateXProperty().bind(btn[1][1].translateXProperty().subtract(btn[1][1].widthProperty()));
        btn[1][0].translateYProperty().bind(btn[1][1].translateYProperty());

        btn[1][2].translateXProperty().bind(btn[1][1].translateXProperty().add(btn[1][1].widthProperty()));
        btn[1][2].translateYProperty().bind(btn[1][1].translateYProperty());

        btn[2][0].translateXProperty().bind(btn[1][1].translateXProperty().subtract(btn[1][1].widthProperty()));
        btn[2][0].translateYProperty().bind(btn[1][1].translateYProperty().add(btn[1][1].heightProperty()));

        btn[2][1].translateXProperty().bind(btn[1][1].translateXProperty());
        btn[2][1].translateYProperty().bind(btn[1][1].translateYProperty().add(btn[1][1].heightProperty()));

        btn[2][2].translateXProperty().bind(btn[1][1].translateXProperty().add(btn[1][1].widthProperty()));
        btn[2][2].translateYProperty().bind(btn[1][1].translateYProperty().add(btn[1][1].heightProperty()));

        this.widthProperty().addListener(((observableValue, oldVal, newVal) ->{

            for (Button[] buttons : btn) {
                for (Button button : buttons) {
                    button.setFont(new Font((newVal.doubleValue() + this.getHeight()) / 25));
                }
            }
        }));

        this.heightProperty().addListener(((observableValue, oldVal, newVal) ->{

            for (Button[] buttons : btn) {
                for (Button button : buttons) {
                    button.setFont(new Font((newVal.doubleValue() + this.getWidth()) / 25));
                }
            }
        }));

        pane.getChildren().addAll(title,XSign,OSign);
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(0), Insets.EMPTY)));
        
        XTrans = new StrokeTransition();
        XTrans.setDuration(Duration.seconds(0.5));
        XTrans.setShape(XSign);
        XTrans.setFromValue(Color.DARKRED);
        XTrans.setToValue(Color.WHITESMOKE);
        XTrans.setCycleCount(2);
        XTrans.setAutoReverse(true);

        OTrans = new StrokeTransition();
        OTrans.setDuration(Duration.seconds(0.5));
        OTrans.setShape(OSign);
        OTrans.setFromValue(Color.MIDNIGHTBLUE);
        OTrans.setToValue(Color.WHITESMOKE);
        OTrans.setCycleCount(2);
        OTrans.setAutoReverse(true);

        TitleTrans = new StrokeTransition();
        TitleTrans.setDuration(Duration.seconds(0.5));
        TitleTrans.setShape(title);
        TitleTrans.setFromValue((Color)title.getStroke());
        TitleTrans.setToValue(Color.DARKGREEN);
        TitleTrans.setCycleCount(Animation.INDEFINITE);
        TitleTrans.setAutoReverse(true);
    }

    public boolean detectWin(){

        for (int i = 0; i < Board.ROWS; i++) {
            if (btn[i][0].getText().equals(btn[i][1].getText())
                    && btn[i][1].getText().equals(btn[i][2].getText())
                    && !btn[i][1].getText().equals(" ")) {

                TitleTrans.play();
                title.setText(btn[i][0].getText() + " WON");
                return true;
            }
        }
        for (int i = 0; i < Board.COLS; i++) {
            if (btn[0][i].getText().equals(btn[1][i].getText())
                    && btn[1][i].getText().equals(btn[2][i].getText())
                    && !btn[0][i].getText().equals(" ")) {

                TitleTrans.play();
                title.setText(btn[0][i].getText() + " WON");
                return true;
            }
        }
        if (btn[0][0].getText().equals(btn[1][1].getText())
                && btn[1][1].getText().equals(btn[2][2].getText())
                && !btn[0][0].getText().equals(" ")) {


            TitleTrans.play();
            title.setText(btn[0][0].getText() + " WON");
            return true;

        } if (btn[0][2].getText().equals(btn[1][1].getText())
                && btn[1][1].getText().equals(btn[2][0].getText())
                && !btn[0][2].getText().equals(" ")) {

            TitleTrans.play();
            title.setText(btn[0][2].getText() + " WON");

        } else if(Board.movesLeft(GC.board.getBoard()) == 0){
            TitleTrans.play();
            title.setText("DRAW");
            return true;
        }
        return false;
    }
}
