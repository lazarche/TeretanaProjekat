package osnova;
import java.util.ArrayList;

public class Trener extends Osoblje{

    ArrayList<Grupa> grupe;

    public Trener(String ime, String prezime, String jmbg, String email, String datumRodjenja, Lokal l) {
        super(ime, prezime, jmbg, email, datumRodjenja, "trener", l);
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

        if( !(o instanceof Trener)) 
            return false;

        Trener temp = (Trener) o;


        if(!temp.getJmbg().equals(this.getJmbg()))
            return false;

        return true;
    }
}
