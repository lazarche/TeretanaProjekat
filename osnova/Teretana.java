package osnova;
import java.util.ArrayList;

public class Teretana {
    ArrayList<Lokal> lokali;
    ArrayList<Korisnik> korisnici;

    public Teretana() {
        lokali = new ArrayList<Lokal>();
        korisnici = new ArrayList<Korisnik>();
    }


    //#region Za lokal
    public void dodajLokal(Lokal lokal) {
        lokali.add(lokal);
    }

    public boolean izbrisiLokal(Lokal lokal) {
        return lokali.remove(lokal);
    }

    public Lokal vratiLokal(Lokal lokal) {
        int index = lokali.indexOf(lokal);
        if(index == -1)
            return null;
        return lokali.get(index);
    }
    //#endregion
    public ArrayList<Lokal> vratiLokale() {
        return lokali;
    }

    //#region Za korisnike
    public void dodajKorisnika(Korisnik korisnik) {
        korisnici.add(korisnik);
    }

    public boolean IspisGrupe(Korisnik korisnik) {
        return this.korisnici.remove(korisnik);
    }

    public Korisnik vratiKorisnika(Korisnik korisnik) {
        int index = korisnici.indexOf(korisnik);
        if(index == -1)
            return null;
        return korisnici.get(index);
    }

    public ArrayList<Korisnik> vratiKorisnike() {
        return korisnici;
    }

    //#endregion
}