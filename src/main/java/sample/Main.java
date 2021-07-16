package sample;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public String[] coins = {"DOGEUSDT", "BTCUSDT", "ETHUSDT", "LTCUSDT"};
    public List<Label> labels;
    public List<Label> labels2;
    public List<Crypto> cryptos;
    public int X = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        cryptos = new ArrayList<>();
        labels = new ArrayList<>();
        labels2 = new ArrayList<>();

        for (int i = 0; i < coins.length; i++) {
            Crypto c = new Crypto(coins[i]);
            c.setLayoutX(X);X += 102;
            root.getChildren().add(c);
            cryptos.add(c);

        }
        for(int j = 0; j < coins.length; j++)
        {
            Crypto mic = cryptos.get(j);

        }
        primaryStage.setTitle("Crypto Tracker By: Sergio Orellana");
        primaryStage.setScene(new Scene(root, 400, 125));
        primaryStage.setResizable(false);
        primaryStage.show();
        System.out.println("done");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
