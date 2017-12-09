package ohtu.Komento;

import ohtu.IO.IO;
import ohtu.Sovelluslogiikka;

abstract class YksiParametrinenOperaatio implements Komento {

    protected Sovelluslogiikka sovellus;
    protected IO io;
    protected int muutos;
    
    public YksiParametrinenOperaatio(Sovelluslogiikka sovellus, IO io) {
        this.sovellus = sovellus;
        this.io = io;
    }

    @Override
    public void suorita() {
        laske();
        io.naytaTulos(sovellus.tulos());
    }

    @Override
    public void peru() {
        peruLaskenta();
        io.naytaTulos(sovellus.tulos());
    }
    
    protected abstract void laske();

    protected abstract void peruLaskenta();
}
