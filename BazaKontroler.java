import java.sql.*;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

class DBConnection {		
	public static Connection getConnection() {
		return conn;
	}	
	public static void closeConnection() {
		try{
		 conn.close();
		}
		catch(SQLException e){
		 System.out.println("Neuspesno zatvaranje konekcije");
		 e.printStackTrace();
		} 
	}	
	private static Connection conn = null;	
	static{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/teretanadb", "root", "root");
		}
		catch(Exception e){
		 System.out.println("Database down");
		 e.printStackTrace();
		}
	}
}

class BazaKontroler {
    
    static Connection con;

    public BazaKontroler() {
        con = DBConnection.getConnection();
        
    }

    // Vraca rezultate posle izvrsavanja sql komande
    public static CachedRowSet returnSet(String sql) {
        try{  
            Statement stmt = con.createStatement();  
            ResultSet rs=stmt.executeQuery(sql);  

            // while(rs.next())  
            //     System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  

            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowset = factory.createCachedRowSet();
            rowset.populate(rs);
             
            return rowset;
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
        return null;
    }

    //Izvrsava Sql i vraca da li je uspeo ili ne
    public static boolean executeSql(String sql) {
        try{  
            Statement stmt = con.createStatement(); 

            stmt.executeUpdate(sql);  
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
        return false;
    }
    
    //#region Korisnik i clanarina

    public static boolean SacuvajKorisnika(Korisnik korisnik) throws SQLException {
        boolean temp = false;
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("INSERT INTO `teretanadb`.`korisnik` (`jmbg`, `ime`, `prezime`, `email`, `datumRodjenja`, `id`) VALUES (?,?,?,?,?,?)");
        ps.setString(1, korisnik.getJmbg());
        ps.setString(2, korisnik.getIme());
        ps.setString(3, korisnik.getPrezime());
        ps.setString(4, korisnik.getEmail());
        ps.setString(5, korisnik.getDatumRodjenja());
        ps.setLong(6, korisnik.getId());

        ps.executeUpdate();

        PreparedStatement pss = DBConnection.getConnection().prepareStatement("INSERT INTO `teretanadb`.`clanarina` (`idKorisnika`, `datumIsteka`) VALUES (?,?); ");
    
        pss.setLong(1, korisnik.getId());
        pss.setDate(2, korisnik.getClanarina().getDatumIsteka());

        pss.executeUpdate();
        
        // tek kasnije
        // for (Grupa grupa : korisnik.vratiGrupe()) {
        //     temp = executeSql("INSERT INTO `teretanadb`.`korisnikugrupi` (`idKorisnika`, `idGrupe`) VALUES ("+korisnik.getId()+", "+ grupa.getId() +"); ");
        // }
        
        return temp;
    }

    public static boolean UcitajKorisnike() {
        CachedRowSet _temp = returnSet("SELECT * FROM korisnik");
        try {
            while(_temp.next()) {
                Korisnik novi = new Korisnik(Main.teretana, _temp.getString(2), _temp.getString(3), _temp.getString(1), _temp.getString(4), _temp.getString(5), _temp.getLong(6));
                
                //Ucitaj clanarinu
                CachedRowSet _clanarina = returnSet("SELECT datumIsteka FROM clanarina WHERE idKorisnika = " + novi.getId() +";");
                while(_clanarina.next()) {
                    novi.getClanarina().datumIsteka = _clanarina.getDate(1);
                }



                Main.teretana.dodajKorisnika(novi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean SacuvajUplatu(Korisnik k) {
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement("UPDATE `teretanadb`.`clanarina` SET  `datumIsteka` = ? WHERE `idKorisnika` = ?; ");
            ps.setDate(1, k.getClanarina().getDatumIsteka());
            ps.setLong(2,k.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean SacuvajGrupu(Korisnik k) {
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement("UPDATE `teretanadb`.`clanarina` SET  `datumIsteka` = ? WHERE `idKorisnika` = ?; ");
            ps.setDate(1, k.getClanarina().getDatumIsteka());
            ps.setLong(2,k.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    //#endregion

    //#region Osoblje i treneri povezivanje sa lokalom
    public static boolean UcitajOsoblje (Lokal l) {
        CachedRowSet _temp = returnSet("SELECT * FROM osoblje WHERE idLokal = " + l.getId());
        try {
            while(_temp.next()) {
                Osoblje novi = new Osoblje(_temp.getString(2), _temp.getString(3), _temp.getString(1), _temp.getString(4), _temp.getString(5), _temp.getString(6), l);
                l.DodajZaposljenog(novi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean UcitajTrenere (Lokal l) {
        CachedRowSet _temp = returnSet("SELECT * FROM trener WHERE idLokal = " + l.getId());
        try {
            while(_temp.next()) {
                Trener novi = new Trener(_temp.getString(2), _temp.getString(3), _temp.getString(1), _temp.getString(4), _temp.getString(5), l);
                l.DodajZaposljenog(novi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //#endregion

    //#region Lokali i povezivanje
    public static boolean UcitajLokale () {
        CachedRowSet _temp = returnSet("SELECT * FROM lokal");
        try {
            while(_temp.next()) {
                Lokal novi = new Lokal(Main.teretana, _temp.getString(2), _temp.getInt(1), _temp.getString(3));
                Main.teretana.dodajLokal(novi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //#endregion

    //#region Grupe i povezivanje sa korisnicima
    public static boolean UcitajGrupe(Lokal l) {
        CachedRowSet _temp = returnSet("SELECT * FROM grupa WHERE idLokala = " + l.getId());
        try {
            while(_temp.next()) {
                Grupa nova = new Grupa((Trener)l.vratiZaposlenog(new Trener("","",_temp.getString(5),"","",l)), l, _temp.getString(2), _temp.getInt(1), _temp.getString(3));
                PoveziGrupuIKorisnike(nova);
                l.DodajGrupu(nova);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean PoveziGrupuIKorisnike(Grupa g) {
        CachedRowSet _temp = returnSet("SELECT * FROM korisnikugrupi");
        try {
            while(_temp.next()) {
                Korisnik zaNaci = new Korisnik(Main.teretana, "ime", "prezime", "jmbg", "email", "datumRodjenja", _temp.getLong(1));
                Main.teretana.vratiKorisnika(zaNaci).DodajGrupu(g);
                g.DodajUcesnika(Main.teretana.vratiKorisnika(zaNaci));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void SacuvajGrupuIKorisnika(Grupa g, Korisnik k) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("INSERT INTO `teretanadb`.`korisnikugrupi` (`idKorisnika`, `idGrupe`) VALUES (?,?); ");
        ps.setLong(1, k.getId());
        ps.setInt(2, g.getId());


        ps.executeUpdate();
    }
    //#endregion
}