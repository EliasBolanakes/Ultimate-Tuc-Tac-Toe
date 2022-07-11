package View;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.Arrays;

public class LoadScreen extends Scene {

    MainStage mainStage;
    Text[] txt;
    Text edition;
    public Pane pane;
    private static final double ANIMATION_DURATION = 4;
    Double TEXT_LENGTH;
    FadeTransition fd;


    public LoadScreen(Parent parent,MainStage mainStage) {
        super(parent);
        this.mainStage = mainStage;
        this.setFill(Color.BLACK);

        // shadows of text

        DropShadow ds = new DropShadow();
        ds.setColor(Color.WHITE);
        ds.setWidth(25);
        ds.setHeight(25);
        ds.setOffsetX(5);
        ds.setOffsetY(2);
        ds.setRadius(20);
        ds.setSpread(0.3);

        txt = new Text[11];
        for (int i=0; i<txt.length; i++) {
            txt[i] = new Text();
            txt[i].setFont(new Font(100));
            txt[i].setFill(Color.TRANSPARENT);
            txt[i].setStroke(Color.TRANSPARENT);
            txt[i].setStrokeWidth(1);
            //txt[i].setEffect(ds);
        }

        txt[0].setText("T");
        txt[1].setText("U");
        txt[2].setText("C");
        txt[3].setText("-");
        txt[4].setText("T");
        txt[5].setText("A");
        txt[6].setText("C");
        txt[7].setText("-");
        txt[8].setText("T");
        txt[9].setText("O");
        txt[10].setText("E");

        edition=new Text("Ultimate Edition");
        edition.setFont(new Font(30));
        edition.setFill(Color.TRANSPARENT);
        edition.setEffect(ds);

        // TEXT_LENGTH is the sum of the width of all the text components

        TEXT_LENGTH = Arrays.stream(txt).
                map(letter -> letter.getLayoutBounds().getWidth()).
                reduce(0.0,Double::sum);

        // Individual animations for each letter of the TUC-TAC-TOE text

        StrokeTransition[] st = new StrokeTransition[6];

        for(int i=0;i<st.length;i++)
            st[i]=new StrokeTransition();

        for(var trans : st){
            trans.setDuration(Duration.seconds(ANIMATION_DURATION));
            trans.setFromValue((Color)txt[1].getStroke());
            trans.setToValue(Color.WHITE);
            trans.setCycleCount(1);
        }

        st[0].setShape(txt[1]);
        st[1].setShape(txt[2]);
        st[2].setShape(txt[4]);
        st[3].setShape(txt[6]);
        st[4].setShape(txt[8]);
        st[5].setShape(txt[9]);


        FillTransition ft = new FillTransition();
        ft.setDuration(Duration.seconds(ANIMATION_DURATION));
        ft.setShape(txt[0]);
        ft.setFromValue((Color)txt[0].getFill());
        ft.setToValue(Color.DARKRED);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();

        FillTransition ft2 = new FillTransition();
        ft2.setDuration(Duration.seconds(ANIMATION_DURATION));
        ft2.setShape(txt[5]);
        ft2.setFromValue((Color)txt[10].getFill());
        ft2.setToValue(Color.DARKGREEN);
        ft2.setCycleCount(1);
        ft2.play();

        FillTransition ft3 = new FillTransition();
        ft3.setDuration(Duration.seconds(ANIMATION_DURATION));
        ft3.setShape(txt[10]);
        ft3.setFromValue((Color)txt[10].getFill());
        ft3.setToValue(Color.MIDNIGHTBLUE);
        ft3.setCycleCount(1);
        ft3.play();

        RotateTransition rt = new RotateTransition();
        rt.setDuration(Duration.seconds(ANIMATION_DURATION/2));
        rt.setNode(txt[3]);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setCycleCount(1);

        RotateTransition rt2 = new RotateTransition();
        rt2.setDuration(Duration.seconds(ANIMATION_DURATION/2));
        rt2.setNode(txt[7]);
        rt2.setFromAngle(360);
        rt2.setToAngle(0);
        rt2.setCycleCount(1);

        // Animation for ULTIMATE EDITION text

        FillTransition ft4 = new FillTransition();
        ft4.setDuration(Duration.seconds(ANIMATION_DURATION));
        ft4.setShape(edition);
        ft4.setFromValue((Color)edition.getFill());
        ft4.setToValue(Color.HONEYDEW);
        ft4.setCycleCount(1);


        // Setting the edition text visible after text animation completion

        ft3.setOnFinished(actionEvent ->{

            txt[3].setStroke(Color.WHITE);
            txt[7].setStroke(Color.WHITE);
            ft4.play();
            rt.play();
            rt2.play();
            for(var trans : st)
                trans.play();
        });
        st[0].setOnFinished(actionEvent -> fadeOut());

        pane = (Pane) parent;
        for (Text text : txt)
            pane.getChildren().add(text);

        pane.getChildren().add(edition);
        pane.setPrefWidth(1200);
        pane.setPrefHeight(700);
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), Insets.EMPTY)));

        // Binding pane position property with text position property for perfect resizing

        txt[0].xProperty().bind(
                pane.widthProperty().
                        subtract(TEXT_LENGTH).divide(14).multiply(2));

        txt[0].yProperty().bind(pane.heightProperty().divide(2));

        for(int i=1;i< txt.length;i++){

            txt[i].xProperty().bind(
                    txt[i-1].xProperty().
                            add(txt[i-1].getLayoutBounds().getWidth()).
                            add(pane.widthProperty().subtract(TEXT_LENGTH).divide(14)));

            txt[i].yProperty().bind(pane.heightProperty().divide(2));
        }

        edition.xProperty().bind(
                pane.widthProperty().divide(2).
                        subtract(edition.getLayoutBounds().getWidth()/2));

        edition.yProperty().bind(txt[3].yProperty().add(50));
    }

    // fade out animations for LoadScreen

    private void fadeOut(){

        fd = new FadeTransition();
        fd.setFromValue(1);
        fd.setToValue(0);
        fd.setDuration(Duration.seconds(2));
        fd.setNode(this.pane);
        fd.play();
        fd.setOnFinished(actionEvent ->  mainStage.VS.showMenu());
    }
}
