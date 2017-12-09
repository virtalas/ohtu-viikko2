package ohtu.Komento;

import ohtu.IO.IO;
import ohtu.Sovelluslogiikka;

public class Nollaa implements Komento {

    private final Sovelluslogiikka sovellus;
    private final IO io;
    private int muutos;

    public Nollaa(Sovelluslogiikka sovellus, IO io) {
        this.sovellus = sovellus;
        this.io = io;
    }

    @Override
    public void suorita() {
        muutos = sovellus.tulos();
        sovellus.nollaa();
        io.naytaTulos(sovellus.tulos());
    }

    @Override
    public void peru() {
        sovellus.plus(muutos);
        io.naytaTulos(sovellus.tulos());
        muutos = 0;
    }
    
}
