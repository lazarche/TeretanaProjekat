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

    public Korisnik vratiKorisnika(Korisnik korisnik) {
        int index = ucesnici.indexOf(korisnik);
        if(index == -1)
            return null;
        return ucesnici.get(index);
    }

    public ArrayList<Korisnik> vratiKorisnike() {
        return ucesnici;
    }

    @Override
    public String toString() {
        return "GRUPA: " + vrstaTreninga + " " + vreme;
    }
}
