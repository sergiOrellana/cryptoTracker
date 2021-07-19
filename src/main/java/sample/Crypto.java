package sample;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceSymbol;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.math.BigDecimal;

public class Crypto extends Pane{
    public Pane img;
    public Label l1;//Nombre Crypto
    public Label l2;//Valor Crypto
    public Label l3;//Cambio Crypyo priceChangePercent

    public Crypto(String name) {
        l1 = new Label();
        l1.setLayoutX(25);l1.setLayoutY(65);
        l2 = new Label();
        l2.setLayoutX(20);l2.setLayoutY(80);
        l2.setFont(Font.font(18));
        l3 = new Label();
        l3.setLayoutX(25);l3.setLayoutY(105);
        img = new Pane();
        img.setLayoutX(25);img.setLayoutY(10);
        img.setStyle("-fx-background-image: url(" + name +".png); "+
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 50px 50px"
        );
        img.setPrefWidth(50);img.setPrefHeight(50);
        l1.setText(name);

        BinanceApi api = new BinanceApi();
        BigDecimal bd = null;

        setLayoutX(0);setLayoutY(0);
        setPrefWidth(100);setPrefHeight(125);
        setStyle("-fx-background-color: lightblue");
        getChildren().addAll(img, l1,l2,l3);

        try {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
            System.out.println(e.toString());
        }

        MiThread mt = new MiThread(l1.getText());
        mt.start();
        System.out.println("Para la moneda: " + l1.getText() + " se ha creado el thread " + mt.getId());
    }

    public class MiThread extends Thread
    {
        public MiThread(String name){super(name);}

        @Override
        public void run()
        {
            while(true)
            {
                BigDecimal bd;
                BinanceApi api = new BinanceApi();
                String precio = "";
                long startw = System.currentTimeMillis();

                try {
                    JsonObject j = api.ticker24hr(BinanceSymbol.valueOf(l1.getText()));
                    JsonElement je = j.get("priceChangePercent");
                    double d = je.getAsDouble();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {l3.setText(String.valueOf(d) + "%");}
                    });

                    if(d > 0.0)
                    {
                        l3.setTextFill(Color.GREEN);
                    }
                    else
                    {
                        l3.setTextFill(Color.RED);
                    }
                    bd = api.pricesMap().get(l1.getText());
                    BigDecimal b2 = new BigDecimal(0);
                    if(l1.getText() != "DOGEUSDT")
                    {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ponTexto(bd.setScale(2, BigDecimal.ROUND_FLOOR).toString());
                            }
                        });
                    }
                    else
                    {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ponTexto(bd.setScale(5, BigDecimal.ROUND_FLOOR).toString());
                            }
                        });
                    }
                }
                catch(BinanceApiException e)
                {
                    System.out.printf(e.toString());
                }
                long rest = System.currentTimeMillis() - startw;
                System.out.println("El precio de " + l1.getText() + " es " + precio + " y la operación ha tardado " + rest + " milisegundos...");
            }
        }
    }

    public void ponTexto(String text)
    {
        l2.setText(text);
    }
}