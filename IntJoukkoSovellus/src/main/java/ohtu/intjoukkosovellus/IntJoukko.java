package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alustaTyhjaJoukko(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        alustaTyhjaJoukko(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        alustaTyhjaJoukko(kapasiteetti, kasvatuskoko);
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == ljono.length) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        return etsiLuku(luku) != -1;
    }

    public boolean poista(int luku) {
        int kohta = etsiLuku(luku);
        if (kohta != -1) {
            siirraAlkioitaVasemmalleKohdasta(kohta);
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i] + ", ";
            }
            tuotos += ljono[alkioidenLkm - 1] + "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        kopioiTaulukostaTaulukkoon(ljono, taulu);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i])) {
                y.lisaa(aTaulu[i]);
            }
        }
        return y;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        int[] bTaulukko = b.toIntArray();
        for (int i = 0; i < bTaulukko.length; i++) {
            a.poista(bTaulukko[i]);
        }
        return a;
    }

    private void alustaTyhjaJoukko(int kapasiteetti, int kasvatuskoko) {
        ljono = new int[Math.max(kapasiteetti, 1)];
        alkioidenLkm = 0;
        this.kasvatuskoko = Math.max(kasvatuskoko, 1);
    }

    private int etsiLuku(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return i;
            }
        }
        return -1;
    }

    private void kopioiTaulukostaTaulukkoon(int[] vanha, int[] uusi) {
        for (int i = 0; i < alkioidenLkm; i++) {
            uusi[i] = vanha[i];
        }
    }

    private void kasvataTaulukkoa() {
        int[] uusi = new int[ljono.length + kasvatuskoko];
        kopioiTaulukostaTaulukkoon(ljono, uusi);
        ljono = uusi;
    }

    private void siirraAlkioitaVasemmalleKohdasta(int kohta) {
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            int apu = ljono[j];
            ljono[j] = ljono[j + 1];
            ljono[j + 1] = apu;
        }
    }
}
