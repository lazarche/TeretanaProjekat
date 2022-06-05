package control;

import java.sql.SQLException;
import java.util.ArrayList;

import control.bazapodataka.BazaKontroler;
import osnova.Grupa;
import osnova.Korisnik;
import osnova.Lokal;

public class GrupaKontroler {
    public static ArrayList<Grupa> VratiSveGrupe(Lokal lokal) {
        return lokal.vratiGrupe();
    }

    public static boolean dodajKorisnikaUGrupu(Korisnik korisnik, Grupa grupa) {
        if(korisnik.vratiGrupu(grupa) != null)
            return false;
        korisnik.DodajGrupu(grupa);
        grupa.DodajUcesnika(korisnik);
        try {
            BazaKontroler.SacuvajGrupuIKorisnika(grupa, korisnik);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
