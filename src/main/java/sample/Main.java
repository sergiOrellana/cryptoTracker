package sample;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceSymbol;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.sound.midi.MidiSystem;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static String miPar;
    public List<Crypto> cryptos = new ArrayList<>();
    public int X = 0;
    public Group roots;
    public Pane pp1;
    public Pane pp2;
    public Label l2;
    public Stage miStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();

        Pane p1 = new Pane();
        p1.setPrefHeight(150); p1.setPrefWidth(400);
        p1.setStyle("-fx-background-color: white");

        Pane p2 = new Pane();
        p2.setPrefWidth(100); p2.setPrefHeight(150);
        p2.setStyle(("-fx-background-image: url(Anadir.png); "+
                "-fx-background-position: center 20px; " +
                "-fx-background-color: lightblue; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 50px 50px"));

        Label l1 = new Label();
        l1.setText("Haz clic para\n añadir un nuevo\n par");
        l1.setStyle("-fx-text-alignment: center");
        l1.setLayoutX(5); l1.setLayoutY(85);

        root.getChildren().addAll(p1,p2, l1);




        primaryStage.setTitle("Crypto Tracker By: Sergio Orellana");
        primaryStage.setScene(new Scene(root, 450, 250));
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

        p2.setOnMouseClicked(event ->
        {
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/seleccion.fxml"));
                Parent root2 = loader.load();
                seleccionController cont = new seleccionController();cont.initialize();
                Scene s = new Scene(root2);
                Stage newS = new Stage();
                newS.setScene(s);
                newS.setResizable(false);
                newS.setAlwaysOnTop(true);
                newS.show();
                miStage = newS;
            }
            catch(IOException e)
            {
                System.out.println(e.toString());
            }
            roots = root;
            pp1 = p1;
            pp2 = p2;
            l2 = l1;
            MiThread mi = new MiThread("Hilo");
            mi.start();

        });
        /*
        primaryStage.setTitle("Crypto Tracker By: Sergio Orellana");

        primaryStage.setScene(s);
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();*/
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void FXML()
    {

    }

    public static void done(String par)
    {
        miPar = par;
    }

    public class MiThread extends Thread
    {
        public MiThread(String name){super(name);}

        @Override
        public void run()
        {
            while(true)
            {
                System.out.println(miPar);
                if(miPar != null) //Ya se ha pulsado el botón aceptar
                {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            miStage.hide();
                            roots.getChildren().remove(l2);
                            roots.getChildren().remove(pp2);
                            roots.getChildren().remove(pp1);
                            ToolBar tb = new ToolBar();
                            Button prb = new Button("Click me");
                            tb.getItems().add(prb);
                            roots.getChildren().addAll(tb, prb);
                            Crypto c = new Crypto(miPar);
                            c.setLayoutX(X);X+=102;
                            roots.getChildren().add(c);
                            cryptos.add(c);

                        }
                    });
                    break;
                }
            }
        }
    }

    public void cerrar()
    {


    }
}
