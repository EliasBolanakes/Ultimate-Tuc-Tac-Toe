package View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TicTacToePane extends Pane {

    public Button[][] btn;
    Font font;
    Pane pane;

    TicTacToePane(Pane pane){

        this.pane = pane;

        font = new Font(10);

        btn = new Button[3][3];
        for(int i=0;i<btn.length;i++){
            for(int j=0;j<btn[i].length;j++){
                btn[i][j] = new Button(" ");
                btn[i][j].setPrefSize(150,150);
                btn[i][j].setStyle("-fx-border-color: white;");
                btn[i][j].setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(0), Insets.EMPTY)));
                this.getChildren().add(btn[i][j]);
            }
        }

        for(int i=0;i<btn.length;i++){
            for(int j=0;j<btn[i].length;j++){
                if(i != 1 && j != 1) {

                    btn[i][j].prefWidthProperty().bind(btn[1][1].prefWidthProperty());
                    btn[i][j].prefHeightProperty().bind(btn[1][1].prefHeightProperty());
                }
            }
        }

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
    }
}
