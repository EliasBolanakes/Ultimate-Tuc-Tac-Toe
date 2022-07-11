package View;

import javafx.animation.*;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class MenuScreen extends Scene {

    MainStage mainStage;
    boolean is3DSupported = Platform.isSupported(ConditionalFeature.SCENE3D);

    Box box_Middle;
    Box box_UpperLeft;
    Box box_UpperLeft2;
    Box box_UpperRight;
    Box box_UpperRight2;
    Box box_LowerLeft;
    Box box_LowerLeft2;
    Box box_LowerRight;
    Box box_LowerRight2;
    Cylinder cylinder;
    Sphere sphere;

    Text title;
    Button[] btn;
    public Pane pane;

    private static final double ANIMATION_DURATION = 2;
    private static final Color BOX_COLOR = Color.DARKRED;
    private static final Color CYLINDER_COLOR = Color.DARKGREEN;
    private static final Color SPHERE_COLOR = Color.MIDNIGHTBLUE;

    public MenuScreen(Parent parent, MainStage mainStage){

        super(parent);
        this.mainStage = mainStage;
        this.setFill(Color.BLACK);
        pane = (Pane) parent;

        if(!is3DSupported)
            System.exit(0);

        // setting the title shadow

        DropShadow ds = new DropShadow();
        ds.setColor(Color.WHITESMOKE);
        ds.setWidth(25);
        ds.setHeight(25);
        ds.setOffsetX(5);
        ds.setOffsetY(2);
        ds.setRadius(20);
        ds.setSpread(0.3);

        title = new Text("Tuc-Tac-Toe");
        title.setFont(new Font(100));
        title.setFill(Color.HONEYDEW);
        title.setStroke(Color.TRANSPARENT);
        title.setStrokeWidth(5);
        title.setEffect(ds);

        // centering the title with property binding

        title.xProperty().bind(pane.widthProperty().divide(2).
                subtract(title.getLayoutBounds().getWidth()/2));

        title.yProperty().bind(pane.heightProperty().divide(5));

        // infinite title stroke transition animation

        StrokeTransition st =new StrokeTransition();
        st.setDuration(Duration.seconds(ANIMATION_DURATION));
        st.setShape(title);
        st.setFromValue((Color)title.getStroke());
        st.setToValue(randomColor());
        st.setCycleCount(2);
        st.setAutoReverse(true);
        st.play();

        st.setOnFinished(e->{
                    st.setToValue(randomColor());
                    st.play();
        });

        // setting-up 3D shapes, their sizes & their positioning on the scene with property binding

        // setting-up the cylinder

        cylinder = new Cylinder();
        cylinder.translateXProperty().bind(pane.widthProperty().divide(2));
        cylinder.translateYProperty().bind(pane.heightProperty().divide(2));
        cylinder.setHeight(100);
        cylinder.setMaterial(new PhongMaterial(CYLINDER_COLOR));
        cylinder.setRotationAxis(Rotate.X_AXIS);
        cylinder.setRotate(45);
        cylinder.radiusProperty().bind((pane.heightProperty().add(pane.widthProperty())).divide(45));

        // setting up the sphere

        sphere = new Sphere();
        sphere.translateXProperty().bind(
                pane.widthProperty().divide(2).add(sphere.radiusProperty()).
                        add(pane.widthProperty().divide(7)));
        sphere.translateYProperty().bind(pane.heightProperty().divide(2));
        sphere.setTranslateZ(100);
        sphere.setMaterial(new PhongMaterial(SPHERE_COLOR));
        sphere.radiusProperty().bind((pane.heightProperty().add(pane.widthProperty())).divide(16));

        // setting-up the X-shape

        box_Middle = new Box();
        box_Middle.translateXProperty().bind(
                pane.widthProperty().divide(2).
                        subtract(box_Middle.widthProperty().multiply(2.7)).
                        subtract(pane.widthProperty().divide(7)));
        box_Middle.translateYProperty().bind(pane.heightProperty().divide(2));
        box_Middle.setMaterial(new PhongMaterial(BOX_COLOR));

        box_Middle.heightProperty().bind((pane.heightProperty().add(pane.widthProperty())).divide(50));
        box_Middle.widthProperty().bind(box_Middle.heightProperty());
        box_Middle.depthProperty().bind(box_Middle.widthProperty());

        box_LowerLeft = new Box();
        box_LowerLeft.translateXProperty().bind(
                box_Middle.translateXProperty().subtract(box_Middle.widthProperty().multiply(1.1)));
        box_LowerLeft.translateYProperty().bind(
                box_Middle.translateYProperty().subtract(box_Middle.widthProperty().multiply(1.1)));
        box_LowerLeft.setMaterial(new PhongMaterial(BOX_COLOR));

        box_LowerLeft.heightProperty().bind(box_Middle.heightProperty());
        box_LowerLeft.widthProperty().bind(box_Middle.widthProperty());
        box_LowerLeft.depthProperty().bind(box_Middle.depthProperty());

        box_LowerLeft2 = new Box();
        box_LowerLeft2.translateXProperty().bind(
                box_LowerLeft.translateXProperty().subtract(box_Middle.widthProperty().multiply(1.1)));
        box_LowerLeft2.translateYProperty().bind(
                box_LowerLeft.translateYProperty().subtract(box_Middle.widthProperty().multiply(1.1)));
        box_LowerLeft2.setMaterial(new PhongMaterial(BOX_COLOR));

        box_LowerLeft2.heightProperty().bind(box_Middle.heightProperty());
        box_LowerLeft2.widthProperty().bind(box_Middle.widthProperty());
        box_LowerLeft2.depthProperty().bind(box_Middle.depthProperty());

        box_LowerRight = new Box();
        box_LowerRight.translateXProperty().bind(
                box_Middle.translateXProperty().add(box_Middle.widthProperty().multiply(1.1)));
        box_LowerRight.translateYProperty().bind(
                box_Middle.translateYProperty().subtract(box_Middle.widthProperty().multiply(1.1)));
        box_LowerRight.setMaterial(new PhongMaterial(BOX_COLOR));

        box_LowerRight.heightProperty().bind(box_Middle.heightProperty());
        box_LowerRight.widthProperty().bind(box_Middle.widthProperty());
        box_LowerRight.depthProperty().bind(box_Middle.depthProperty());

        box_LowerRight2 = new Box();
        box_LowerRight2.translateXProperty().bind(
                box_LowerRight.translateXProperty().add(box_Middle.widthProperty().multiply(1.1)));
        box_LowerRight2.translateYProperty().bind(
                box_LowerRight.translateYProperty().subtract(box_Middle.widthProperty().multiply(1.1)));
        box_LowerRight2.setMaterial(new PhongMaterial(BOX_COLOR));

        box_LowerRight2.heightProperty().bind(box_Middle.heightProperty());
        box_LowerRight2.widthProperty().bind(box_Middle.widthProperty());
        box_LowerRight2.depthProperty().bind(box_Middle.depthProperty());

        box_UpperLeft = new Box();
        box_UpperLeft.translateXProperty().bind(
                box_Middle.translateXProperty().subtract(box_Middle.widthProperty().multiply(1.1)));
        box_UpperLeft.translateYProperty().bind(
                box_Middle.translateYProperty().add(box_Middle.widthProperty().multiply(1.1)));
        box_UpperLeft.setMaterial(new PhongMaterial(BOX_COLOR));

        box_UpperLeft.heightProperty().bind(box_Middle.heightProperty());
        box_UpperLeft.widthProperty().bind(box_Middle.widthProperty());
        box_UpperLeft.depthProperty().bind(box_Middle.depthProperty());

        box_UpperLeft2 = new Box();
        box_UpperLeft2.translateXProperty().bind(
                box_UpperLeft.translateXProperty().subtract(box_Middle.widthProperty().multiply(1.1)));
        box_UpperLeft2.translateYProperty().bind(
                box_UpperLeft.translateYProperty().add(box_Middle.widthProperty().multiply(1.1)));
        box_UpperLeft2.setMaterial(new PhongMaterial(BOX_COLOR));

        box_UpperLeft2.heightProperty().bind(box_Middle.heightProperty());
        box_UpperLeft2.widthProperty().bind(box_Middle.widthProperty());
        box_UpperLeft2.depthProperty().bind(box_Middle.depthProperty());

        box_UpperRight = new Box();
        box_UpperRight.translateXProperty().bind(
                box_Middle.translateXProperty().add(box_Middle.widthProperty().multiply(1.1)));
        box_UpperRight.translateYProperty().bind(
                box_Middle.translateYProperty().add(box_Middle.widthProperty().multiply(1.1)));
        box_UpperRight.setMaterial(new PhongMaterial(BOX_COLOR));

        box_UpperRight.heightProperty().bind(box_Middle.heightProperty());
        box_UpperRight.widthProperty().bind(box_Middle.widthProperty());
        box_UpperRight.depthProperty().bind(box_Middle.depthProperty());

        box_UpperRight2 = new Box();
        box_UpperRight2.translateXProperty().bind(
                box_UpperRight.translateXProperty().add(box_Middle.widthProperty().multiply(1.1)));
        box_UpperRight2.translateYProperty().bind(
                box_UpperRight.translateYProperty().add(box_Middle.widthProperty().multiply(1.1)));
        box_UpperRight2.setMaterial(new PhongMaterial(BOX_COLOR));

        box_UpperRight2.heightProperty().bind(box_Middle.heightProperty());
        box_UpperRight2.widthProperty().bind(box_Middle.widthProperty());
        box_UpperRight2.depthProperty().bind(box_Middle.depthProperty());

        // creating the buttons and their respective position

        btn = new Button[4];

        btn[0] = new Button("Play");
        btn[1] = new Button("Login");
        btn[2] = new Button("Sign-up");
        btn[3] = new Button("Settings");

        for (var button : btn){
            button.setPrefSize(100, 50);
            button.setFont(new Font(20));
            button.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 15px; -fx-text-fill: #000000");
        }

        double BTN_LENGTH = Arrays.stream(btn).
                map(Region::getPrefWidth).
                reduce(0.0,Double::sum);


        btn[0].translateXProperty().bind(
                pane.widthProperty().
                        subtract(BTN_LENGTH).divide(9).multiply(3));

        for(int i=1;i<btn.length;i++)
            btn[i].translateXProperty().bind(
                    btn[i-1].translateXProperty().
                            add(btn[i-1].widthProperty()).add(pane.widthProperty()
                                    .subtract(BTN_LENGTH).divide(9)));

        for(var button : btn)
            button.translateYProperty().bind(
                    pane.heightProperty().divide(2).add(pane.heightProperty().divide(3)));

        btn[0].setOnAction(actionEvent -> switchToGame());


        // creating the camera

        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        this.setCamera(camera);

        // creating separate group for each shape (easier parameterization) and the combining them all in one group

        Group XGroup = new Group(box_Middle,box_LowerLeft,box_LowerLeft2,box_LowerRight,box_LowerRight2,
                box_UpperLeft,box_UpperLeft2,box_UpperRight,box_UpperRight2);
        XGroup.setRotationAxis(Rotate.Y_AXIS);

        Group cylinderGroup = new Group(cylinder);

        Group sphereGroup = new Group(sphere);

        // creating a PointLight that illuminates the 3D shapes form the center of the pane
        // & at depth of 1000 (outwards of the screen)

        PointLight pointLight = new PointLight();
        pointLight.translateXProperty().bind(pane.widthProperty().divide(2));
        pointLight.translateYProperty().bind(pane.widthProperty().divide(2));
        pointLight.setTranslateZ(-1000);

        Group shapes3D = new Group(XGroup,cylinderGroup,sphereGroup);
        shapes3D.getChildren().add(pointLight);

        pane.getChildren().addAll(shapes3D,title,btn[0],btn[1],btn[2],btn[3]);
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), Insets.EMPTY)));

        // creating the KeyFrames

        KeyFrame[] frames = new KeyFrame[360];

        for (int i=0; i<frames.length; i++) {
            frames[i] = new KeyFrame(Duration.millis(50*i),
                    new KeyValue(cylinder.heightProperty(), cylinder.getHeight()+i*0.75),
                    new KeyValue(XGroup.rotateProperty(),i),
                    new KeyValue(sphere.translateZProperty(),sphere.getTranslateZ()+3/2.0*i)
            );
        }

        // setting up the TimeLine animation

        Timeline timeline = new Timeline();
        for (KeyFrame frame : frames) timeline.getKeyFrames().add(frame);
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    // random color generator

    private Color randomColor(){

        Random r = new SecureRandom();
        return Color.color(r.nextDouble(1),r.nextDouble(1),r.nextDouble(1));
    }

    public void switchToGame(){

        FadeTransition fd = new FadeTransition();
        fd.setFromValue(1);
        fd.setToValue(0);
        fd.setDuration(Duration.seconds(2));
        fd.setNode(this.pane);
        fd.play();
        fd.setOnFinished(actionEvent ->  mainStage.VS.playButton());
    }
}
