package ohtu.Komento;

import ohtu.IO.IO;
import ohtu.Sovelluslogiikka;

public class Erotus extends YksiParametrinenOperaatio {

    public Erotus(Sovelluslogiikka sovellus, IO io) {
        super(sovellus, io);
    }

    @Override
    protected void laske() {
        muutos = io.seuraavaInt();
        sovellus.miinus(muutos);
    }

    @Override
    protected void peruLaskenta() {
        sovellus.plus(muutos);
        muutos = 0;
    }
}
