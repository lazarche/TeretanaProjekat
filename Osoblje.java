public class Osoblje {
    String ime;
    String prezime;
    String jmbg;
    String email;
    String datumRodjenja;
    String radnoMesto;

    Lokal lokal;

    public Osoblje(String ime, String prezime, String jmbg, String email, String datumRodjenja, String radnoMesto, Lokal l) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.email = email;
        this.datumRodjenja = datumRodjenja;
        this.radnoMesto = radnoMesto;
        this.lokal = l;
    }

    public String getIme() { return ime; }
    public String getPrezime() { return prezime; }
    public String getJmbg() { return jmbg; }
    public String getEmail() { return email; }
    public String getDatumRodjenja() { return datumRodjenja; }
    public String getRadnoMesto() { return radnoMesto; }
    public Lokal getLokal() { return lokal; }

    public String toString() {
        return ime + " " + prezime + " Radno mesto: " + radnoMesto + " JMBG: " + jmbg + " Email: " + email + " Datum Rodjenja: " + datumRodjenja;
    }

    @Override
    public boolean equals(Object o) {
        if( o == this)
            return true;

        if( !(o instanceof Osoblje)) 
            return false;

        Osoblje temp = (Osoblje) o;


        if(!temp.getJmbg().equals(this.getJmbg()))
            return false;

        return true;
    }
    
}