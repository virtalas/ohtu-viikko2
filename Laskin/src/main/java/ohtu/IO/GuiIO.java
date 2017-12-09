package ohtu.IO;

import javax.swing.JTextField;

public class GuiIO implements IO {

    private JTextField syoteKentta;
    private final JTextField tulosKentta;

    public GuiIO(JTextField syotekentta, JTextField tulosKentta) {
        this.syoteKentta = syotekentta;
        this.tulosKentta = tulosKentta;
    }
    
    @Override
    public int seuraavaInt() {
        int arvo = 0;

        try {
            arvo = Integer.parseInt(syoteKentta.getText());
        } catch (Exception e) {
        }
        
        return arvo;
    }

    @Override
    public void naytaTulos(int luku) {
        tulosKentta.setText(""+luku);
        syoteKentta.setText("");
    }
}
