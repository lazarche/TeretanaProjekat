import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

enum VrstaClanarine{
    obicnaClanarina, //bez mogucnosti grupnih treninga
    grupnaClanarina //sa grupnim treninzima
}


public class Clanarina {
    VrstaClanarine vrstaClanarine;
    Date datumIsteka;

    public Clanarina(VrstaClanarine vrstaClanarine, Date datumIsteka) {
        this.vrstaClanarine = vrstaClanarine;
        this.datumIsteka = datumIsteka;
    }

    public VrstaClanarine getVrstaClanarine() { return vrstaClanarine; }
    public Date getDatumIsteka() { return datumIsteka; }

    public boolean DaLiClanarinaTraje() {
        java.sql.Date cur = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        return datumIsteka.after(cur);
    }
    
    public void UplatiClanarinu(int brojUplacenihMeseci) {
        java.sql.Date cur = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        if(datumIsteka.before(cur))
            datumIsteka = cur;

        LocalDate ld = datumIsteka.toLocalDate();

        ld.plusMonths(3);

        datumIsteka = Date.valueOf(ld);
    }
}
