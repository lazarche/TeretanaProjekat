import java.util.Calendar;

public class KorisnikKontroler {
    public static long dodajKorisnika(Teretana teretana,String ime, String prezime, String jmbg, String email, String datumRodjenja) {
        //vraca trenutno vreme u ovoj milisukundi, sto je okej za id jer mozemo predpostaviti da se nece dva korisnika napraviti u 1/1000 sekunde na istom sistemu
        java.sql.Date cur = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Korisnik novi = new Korisnik(teretana, ime, prezime, jmbg, email, datumRodjenja, cur.getTime());

        teretana.dodajKorisnika(novi);
        
        return cur.getTime();
    }

    public static Korisnik pronadjiKorisnika(Teretana teretana, long id) {
        Korisnik temp = new Korisnik(teretana, "temp", "temp", "temp", "temp", "temp", id);
        return teretana.vratiKorisnika(temp);
    }

    public static void uplatiClanarinu(Korisnik korisnik, int meseci) {
        korisnik.getClanarina().UplatiClanarinu(meseci);
    }
    
}
