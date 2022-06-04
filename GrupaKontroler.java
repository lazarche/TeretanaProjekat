import java.util.ArrayList;

public class GrupaKontroler {
    public static ArrayList<Grupa> VratiSveGrupe(Lokal lokal) {
        return lokal.vratiGrupe();
    }

    public static void dodajKorisnikaUGrupu(Korisnik korisnik, Grupa grupa) {
        korisnik.DodajGrupu(grupa);
        grupa.DodajUcesnika(korisnik);
    }
}
