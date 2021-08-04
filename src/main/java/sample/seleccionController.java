package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class seleccionController{
    @FXML public RadioButton BTC1 = new RadioButton();
    @FXML public RadioButton ETH1  = new RadioButton();
    @FXML public RadioButton DOGE1 = new RadioButton();
    @FXML public RadioButton XRP1  = new RadioButton();
    @FXML public RadioButton BNB1  = new RadioButton();
    @FXML public RadioButton ADA1  = new RadioButton();
    @FXML public RadioButton LTC1  = new RadioButton();
    @FXML public RadioButton ETC1  = new RadioButton();
    @FXML public RadioButton BCH1  = new RadioButton();
    @FXML public RadioButton BUSD1 = new RadioButton();
    @FXML public RadioButton USDT1 = new RadioButton();

    @FXML public RadioButton BTC2  = new RadioButton();
    @FXML public RadioButton ETH2 = new RadioButton();
    @FXML public RadioButton USDT2 = new RadioButton();


    @FXML public Button Aceptar;

    public List<RadioButton> l1 = new ArrayList<RadioButton>();
    public List<RadioButton> l2 = new ArrayList<RadioButton>();

    public String par;

    public void initialize()
    {
        if(l1 == null)
        {
            System.out.println("l1 es nulls");
        }

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
        l2.add(USDT2);


    }

   public String mouseClicked(MouseEvent mouseEvent)//El botón aceptar ha sido clicado
    {
        RadioButton r1 = null;
        System.out.println(l1.size());
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
        if(r1!=null && r2 != null)
        {
            String r =r1.getText() + r2.getText();
            System.out.println(r);
            par = r;
            if(r==null)
            {
                par = "";
            }

            Main.done(par);
            return r;

        }

        return null;

    }
}
