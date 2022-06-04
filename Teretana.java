import java.util.ArrayList;

public class Teretana {
    ArrayList<Lokal> lokali;

    public Teretana() {
        lokali = new ArrayList<Lokal>();
    }

    public void dodajLokal(Lokal lokal) {
        lokali.add(lokal);
    }

    public boolean izbrisiLokal(Lokal lokal) {
        return lokali.remove(lokal);
    }

    public Lokal vratiLokal(Lokal lokal) {
        return lokali.get(lokali.indexOf(lokal));
    }
}