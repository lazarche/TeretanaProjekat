import java.util.ArrayList;

public class Main {
    static Teretana teretana;
    static Lokal lokalni;
    static BazaKontroler bk;
    public static void main(String[] args) {
        teretana = new Teretana();
        bk = new BazaKontroler();

        //Ucitavanje
        
        //Ucitaj lokale
        BazaKontroler.UcitajLokale();
        BazaKontroler.UcitajKorisnike(); //Korisnici - clanarina
        for (Lokal lokal : teretana.vratiLokale()) {
            BazaKontroler.UcitajOsoblje(lokal);
            BazaKontroler.UcitajTrenere(lokal);
            BazaKontroler.UcitajGrupe(lokal);
        }
        

        //Setovanje trenutno lokala, u pravoj aplikaciji to bi bio argument za pozivanje args
        lokalni = teretana.vratiLokal(new Lokal(teretana, "adresa", 1, "radnoVreme"));

        System.out.println("~~~~TeretanaProgram~~~~~");
        System.out.println("DOBRODOSLI!");
        Svetovid.in.readLine();
        boolean kraj = false;
        while(!kraj) {
            kraj = Menu();
        }
    }

    public static boolean Menu() {
        clearConsole();
        System.out.println("~~~~~MENU~~~~~");
        System.out.println("1. Dodaj novog korisnika");
        System.out.println("2. Uplati clanarinu korisniku");
        System.out.println("3. Ubaci korisnika u grupu");
        System.out.println("4. Napravi novu grupu");
        System.out.println("5. Prikazi sve korisnike");
        System.out.println("6. Izlaz");
        char unos = 'z';
        try {
            unos = Svetovid.in.readChar(":");
        } catch (Exception e) {
        }
        
        clearConsole();

        switch(unos) {
            case '1':
                DodajNovogClana();
            break;
            case '2':
                UplatiClanarinu();
            break;
            case '3':
                DodajKorisnikaUGrupu();
            break;
            case '4':
                System.out.println("Opcija ce biti dodata u sledecoj iteraciji");
            break;
            case '5':
                PrikaziSveKorisnike();
            break;

            case '6':
                return true;

            default:
                System.out.println("Pogresan unos! Probajte opet");
                Svetovid.in.readLine();
            break;
        }
        return false;
    }

    public static void PrikaziSveKorisnike() {
        System.out.println("Svi korisnici teretane: ");
        System.out.println("------------------------------");
        for (Korisnik korisnik : teretana.vratiKorisnike()) {
            System.out.println(korisnik);
        }
        System.out.println("------------------------------");
        Svetovid.in.readLine();
    }

    public static void DodajKorisnikaUGrupu() {
        ArrayList<Grupa> grupe = GrupaKontroler.VratiSveGrupe(lokalni);
        System.out.println("~~~~~Dodavanje korisnika u grupu~~~~~");
        System.out.println("Izaberi grupu u koju zelite da ubacite korisnika: ");
        int br = 0;
        for (Grupa grupa : grupe) {
            System.out.println(br +". " + grupa.toString());
            br++;
        }

        String unos;
        int i = -1;
        while(true) {
            unos = UnosStringa("Unesi index grupe: ");
            try{
                i = Integer.parseInt(unos);
            } catch(Exception e) {
                System.out.println("Unos nije dobar!");
                continue;
            }

            if (i > -1 && i < grupe.size())
                break;

            System.out.println("Indeks mora biti od 0 do " + (grupe.size()-1));
        }

        System.out.println("ps. ovde treba da ide barkod ili nfc skener :(");
        Korisnik pronadji;
        //da li korisnik postoji
        while(true) {
            String idKorisnika = UnosStringa("Unesi id korisnika(x za izlaz): ");
            if(idKorisnika.equals("x"))
                return;
            try {
                long id = Long.parseLong(idKorisnika);
                pronadji = KorisnikKontroler.pronadjiKorisnika(teretana, id);

                if(pronadji != null)
                    break;
            } catch (Exception e) {
                System.out.println("Unos nije dobar");
            }
            System.out.println("Korisnik nije pronadjen.");
        }

        if(GrupaKontroler.dodajKorisnikaUGrupu(pronadji, grupe.get(i)))
            System.out.println("Korisnik je dodat u grupu");
            else {
            System.out.println("Korisnik je vec u grupi");
            Svetovid.in.readLine();
            }

    }

    public static void DodajNovogClana() {
        System.out.println("~~~~~Unos novog clana~~~~~");
        System.out.println("Novi can uplacuje clanarinu u iznosu od jednog meseca");
        String ime = UnosStringa("Unesi ime korisnika: ");
        String prezime = UnosStringa("Unesi prezime korisnika: ");
        String jmbg = UnosStringa("Unesi jmbg korisnika: ");
        String email = UnosStringa("Unesi email korisnika: ");
        String datumRodjenja = UnosStringa("Unesi datum rodjenja korisnika: ");

        //PROVERA
        Korisnik novi = new Korisnik(teretana, ime, prezime, jmbg, email, datumRodjenja, 0);
        System.out.println("Dodati korisnika(da/ne)?");
        System.out.println(novi.toString() + " ?");

        
        if(Potvrda(":")){
            KorisnikKontroler.dodajKorisnika(teretana, ime, prezime, jmbg, email, datumRodjenja);
        } else {
            System.out.println("Probaj opet(da/ne)?");
            if(Potvrda(""))
                DodajNovogClana();
        }
    }

    public static void UplatiClanarinu() {
        System.out.println("ps. ovde treba da ide barkod ili nfc skener :(");
        System.out.println("~~~~~Uplacivanje clanarine~~~~~");
        Korisnik pronadji;
        //da li korisnik postoji
        while(true) {
            String idKorisnika = UnosStringa("Unesi id korisnika(x za izlaz): ");
            if(idKorisnika.equals("x"))
                return;
            try {
                long id = Long.parseLong(idKorisnika);
                pronadji = KorisnikKontroler.pronadjiKorisnika(teretana, id);

                if(pronadji != null)
                    break;
            } catch (Exception e) {
                System.out.println("Unos nije dobar");
            }
            
            
            System.out.println("Korisnik nije pronadjen.");
        }
        int kol = -1;
        //provera za broj
        while(true) {
            String kolString = UnosStringa("Koliko je uplacano(x za izlaz): ");
            if(kolString.equals("x"))
                return;
                
            kol = Integer.parseInt(kolString);

            if(kol > 0 && kol < 13)
                break;
            
            System.out.println("Iznos je nevazeci.");
        }
        


        KorisnikKontroler.uplatiClanarinu(pronadji, kol);
    }


    //#region Regulacija unosa

    public static String UnosStringa(String poruka) {
        String polje;
        while(true){
            polje = Svetovid.in.readLine(poruka);
            if(polje.equals("") || polje == null)
                System.out.println("Unos nije dobar, probaj opet");
                else
                    break;

        } 
        return polje;
    }

    public static boolean Potvrda(String poruka) {

        String polje;
        while(true){
            polje = Svetovid.in.readLine(poruka);
            if(polje.equals("da") || polje.equals("DA") || polje.equals("ne") || polje.equals("NE"))
                break;
            
            System.out.println("Unos nije dobar, probaj opet"); 
        }
        
        if(polje.equals("da") || polje.equals("DA") )
            return true;
            else
            return false;
    }

    public final static void clearConsole()
    {
        for (int i = 0; i < 50; ++i) System.out.println();
    }
    //#endregion
}
