package ohtu.Komento;

import ohtu.IO.IO;
import ohtu.Sovelluslogiikka;

public class Summa extends YksiParametrinenOperaatio {

    public Summa(Sovelluslogiikka sovellus, IO io) {
        super(sovellus, io);
    }

    @Override
    protected void laske() {
        muutos = io.seuraavaInt();
        sovellus.plus(muutos);
    }

    @Override
    protected void peruLaskenta() {
        sovellus.miinus(muutos);
        muutos = 0;
    }
}
