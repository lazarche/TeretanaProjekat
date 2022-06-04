import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;




public class Clanarina {

    Date datumIsteka;

    public Clanarina(Date datumIsteka) {
        this.datumIsteka = datumIsteka;
    }
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

        ld = ld.plusMonths(brojUplacenihMeseci);

        datumIsteka = Date.valueOf(ld);
    }
}
