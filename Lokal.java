import java.util.ArrayList;

public class Lokal {
    Teretana teretana;

    ArrayList<Osoblje> zaposleni;
    ArrayList<Grupa> grupe;
    
    String adresa;
    String naziv;
    String radnoVreme;

    public Lokal(Teretana teretana, String adresa, String naziv, String radnoVreme) {
        this.teretana = teretana;
        this.adresa = adresa;
        this.naziv = naziv;
        this.radnoVreme = radnoVreme;

        zaposleni = new ArrayList<Osoblje>();
    }

    public String getAdresa() { return adresa; }
    public String getNaziv() { return naziv; }
    public String getRadnoVreme() { return radnoVreme; }

    //~~~Zaposleni~~~~~
    public void DodajZaposljenog(Osoblje osoba) {
        this.zaposleni.add(osoba);
    }

    public boolean DajOtkazZaposlenom(Osoblje osoba) {
        return this.zaposleni.remove(osoba);
    }

    public Osoblje vratiZaposlenog(Osoblje zaposlen) {
        int index = zaposleni.indexOf(zaposlen);
        if(index == -1)
            return null;
        return zaposleni.get(index);
    }

    public ArrayList<Osoblje> vratiZaposlene() {
        return zaposleni;
    }


    //~~~~Grupe~~~~~~
    public void DodajGrupu(Grupa grupa) {
        this.grupe.add(grupa);
    }

    public boolean IzbrisiGrupu(Grupa grupa) {
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


    @Override
    public boolean equals(Object o) {
        if( o == this)
            return true;

        if( !(o instanceof Lokal)) 
            return false;

        Lokal temp = (Lokal) o;


        if(!temp.getNaziv().equals(this.getNaziv()))
            return false;
        if(!temp.getAdresa().equals(this.getAdresa()))
            return false;

        return true;
    }
}
