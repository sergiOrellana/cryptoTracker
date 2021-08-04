package sample;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static String miPar;
    public List<Crypto> cryptos = new ArrayList<>();
    public int X = 0;
    public int Y = 35;
    public Group roots;
    public Pane pp1;
    public Label l2;
    public Stage miStage;
    public Button Anadir;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();

        Anadir = new Button("Añadir");
        ToolBar tb = new ToolBar(Anadir);

        tb.setLayoutX(50);tb.setLayoutY(10);
        root.getChildren().add(tb);

        BorderPane p1 = new BorderPane();

        p1.setPrefHeight(150); p1.setPrefWidth(450);
        p1.setStyle("-fx-background-color: white");
        p1.setTop(tb);



        Label l1 = new Label();
        l1.setText("Clica añadir para añadir un nuevo par");
        l1.setStyle("-fx-text-alignment: center");
        l1.setLayoutX(130); l1.setLayoutY(120);

        root.getChildren().addAll(p1, l1);

        primaryStage.setTitle("Crypto Tracker By: Sergio Orellana");
        primaryStage.setScene(new Scene(root, 450, 250));
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

        Anadir.setOnMouseClicked(event ->
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
            l2 = l1;
            MiThread mi = new MiThread("Hilo");
            mi.start();


        });
    }
    public static void main(String[] args) {
        launch(args);
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
                            roots.getChildren().remove(pp1);
                            BorderPane bp = new BorderPane();
                            bp.setPrefWidth(450);
                            ToolBar tb = new ToolBar(Anadir);
                            tb.setPrefWidth(450);
                            bp.setTop(tb);
                            Crypto c = new Crypto(miPar);
                            c.setLayoutX(X);X+=102;c.setLayoutY(Y);
                            roots.getChildren().addAll(c, bp, tb, Anadir);
                            cryptos.add(c);
                            miPar = null;

                        }
                    });
                    break;
                }
            }
        }
    }
}
