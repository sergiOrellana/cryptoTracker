package sample;

import com.webcerebrium.binance.api.BinanceApi;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class seleccionController{
    @FXML public Pane Pane1;
    @FXML public RadioButton BTC1;
    @FXML public RadioButton ETH1;
    @FXML public RadioButton DOGE1;
    @FXML public RadioButton XRP1;
    @FXML public RadioButton BNB1;
    @FXML public RadioButton ADA1;
    @FXML public RadioButton LTC1;
    @FXML public RadioButton ETC1;
    @FXML public RadioButton BCH1;
    @FXML public RadioButton BUSD1;
    @FXML public RadioButton USDT1;

    @FXML public Pane Pane2;
    @FXML public RadioButton BTC2;
    @FXML public RadioButton ETH2;
    @FXML public RadioButton DOGE2;
    @FXML public RadioButton XRP2;
    @FXML public RadioButton BNB2;
    @FXML public RadioButton ADA2;
    @FXML public RadioButton LTC2;
    @FXML public RadioButton ETC2;
    @FXML public RadioButton BCH2;
    @FXML public RadioButton BUSD2;
    @FXML public RadioButton USDT2;

    @FXML public Button Aceptar;

    public List<RadioButton> l1;
    public List<RadioButton> l2;

    public void list() {
        l1.add(BTC1);
        l1.add(ETH1);
        l1.add(DOGE1);
        l1.add(XRP1);
        l1.add(BNB1);
        l1.add(ADA1);
        l1.add(LTC1);
        l1.add(ETC1);
        l1.add(BCH1);
        l1.add(BUSD1);
        l1.add(USDT1);

        l2.add(BTC2);
        l2.add(ETH2);
        l2.add(DOGE2);
        l2.add(XRP2);
        l2.add(BNB2);
        l2.add(ADA2);
        l2.add(LTC2);
        l2.add(ETC2);
        l2.add(BCH2);
        l2.add(BUSD2);
        l2.add(USDT2);
    }

    public String mouseClicked(MouseEvent mouseEvent)//El botón aceptar ha sido clicado
    {
        RadioButton r1 = null;
        for(RadioButton e: l1)
        {
            if(e.isSelected())
            {
                r1 = e;
            }
        }

        RadioButton r2 = null;
        for(RadioButton t: l2)
        {
            if(t.isSelected())
            {
                r2 = t;
            }
        }
        return r1.getText() + r2.getText();
    }
}
