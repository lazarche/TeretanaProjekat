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

    public Korisnik(Teretana teretana, String ime, String prezime, String jmbg, String email, String datumRodjenja) {
        this.teretana = teretana;
        grupe = new ArrayList<Grupa>();

        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.email = email;
        this.datumRodjenja = datumRodjenja;

    }

    public String getIme() { return ime; }
    public String getPrezime() { return prezime; }
    public String getJmbg() { return jmbg; }
    public String getEmail() { return email; }
    public String getDatumRodjenja() { return datumRodjenja; }


    public void DodajGrupu(Grupa grupa) {
        this.grupe.add(grupa);
    }

    public boolean IspisGrupe(Grupa grupa) {
        return this.grupe.remove(grupa);
    }

    public Grupa vratiGrupu(Grupa grupa) {
        return grupe.get(grupe.indexOf(grupa));
    }

    public ArrayList<Grupa> vratiGrupe() {
        return grupe;
    }

    public String toString() {
        return ime + " " + prezime + " JMBG: " + jmbg + " Email: " + email + " Datum Rodjenja: " + datumRodjenja;
    }


    public Clanarina getClanarina() { return clanarina; }

    @Override
    public boolean equals(Object o) {
        if( o == this)
            return true;

        if( !(o instanceof Osoblje)) 
            return false;

        Korisnik temp = (Korisnik) o;

        if(!temp.getJmbg().equals(this.getJmbg()))
            return false;

        return true;
    }
}
