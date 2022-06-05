package osnova;
import java.sql.Date;
import java.util.ArrayList;

public class Korisnik {
    Teretana teretana;
    Clanarina clanarina;
    ArrayList<Grupa> grupe;

    String ime;
    String prezime;
    String jmbg;
    String email;
    String datumRodjenja;

    long id;

    public Korisnik(Teretana teretana, String ime, String prezime, String jmbg, String email, String datumRodjenja, long id) {
        this.teretana = teretana;
        grupe = new ArrayList<Grupa>();

        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.email = email;
        this.datumRodjenja = datumRodjenja;
        this.id = id;

        clanarina  = new Clanarina(new Date(1));
        clanarina.UplatiClanarinu(1);
    }

    public String getIme() { return ime; }
    public String getPrezime() { return prezime; }
    public String getJmbg() { return jmbg; }
    public String getEmail() { return email; }
    public String getDatumRodjenja() { return datumRodjenja; }
    public long getId() { return id; }


    public void DodajGrupu(Grupa grupa) {
        this.grupe.add(grupa);
    }

    public boolean IspisGrupe(Grupa grupa) {
        return this.grupe.remove(grupa);
    }

    public Grupa vratiGrupu(Grupa grupa) {
        int index = grupe.indexOf(grupa);
        if(index == -1)
            return null;
        return grupe.get(index);
    }

    public ArrayList<Grupa> vratiGrupe() {
        return grupe;
    }

    public String toString() {
        return ime + " " + prezime + " JMBG: " + jmbg + " Email: " + email + " Datum Rodjenja: " + datumRodjenja + " id:" + id;
    }

    public Clanarina getClanarina() { return clanarina; }

    @Override
    public boolean equals(Object o) {
        if( o == this)
            return true;

        if( !(o instanceof Korisnik)) 
            return false;

        Korisnik temp = (Korisnik) o;

        if(temp.getId() == id)
            return true;

        return false;
    }
}
