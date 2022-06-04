import java.util.ArrayList;

public class Grupa {
    Trener trener;
    Lokal lokal;
    ArrayList<Korisnik> ucesnici;

    String vreme;
    int maxBrojKorisnika;
    String vrstaTreninga;

    public Grupa(Trener trener, Lokal lokal, String vreme, int maxBrojKorisnika, String vrstaTreninga) {
        this.trener = trener;
        this.lokal = lokal;
        this.vreme = vreme;
        this.maxBrojKorisnika = maxBrojKorisnika;
        this.vrstaTreninga = vrstaTreninga;
    }

    public Trener getTrener() { return trener; }
    public Lokal getLokal() { return lokal; }
    public String getVreme() { return vreme; }
    public int MaxBrojKorisnika() { return maxBrojKorisnika; }

    public void DodajUcesnika(Korisnik korisnik) {
        ucesnici.add(korisnik);
    }

    public boolean IspisGrupe(Korisnik korisnik) {
        return this.ucesnici.remove(korisnik);
    }

    public Korisnik vratiGrupu(Korisnik korisnik) {
        return ucesnici.get(ucesnici.indexOf(korisnik));
    }

    public ArrayList<Korisnik> vratiGrupe() {
        return ucesnici;
    }
}
