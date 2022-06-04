import java.util.ArrayList;

public class Main {
    static Teretana teretana;
    static Lokal lokalni;
    public static void main(String[] args) {
        teretana = new Teretana();


        //TEST
        teretana.dodajLokal(new Lokal(teretana, "Neka tamo", "1.", "7-23"));

        
        //Trenutna pozicija bi se setovali prilikom namestanja programa/ ili bi se ucitavala
        lokalni = teretana.vratiLokale().get(0);
        Trener t = new Trener("ime", "prezime", "jmbg", "email", "datumRodjenja", "radnoMesto", lokalni);
        lokalni.DodajGrupu(new Grupa(t, lokalni, "vreme", 2, "vrstaTreninga"));
        lokalni.DodajGrupu(new Grupa(t, lokalni, "pravo vreme", 5, "jos bolja grupa"));

        //TEST


        //TO-DO Ucitavanje
        System.out.println("~~~~TeretanaProgram2000~~~~~");
        System.out.println("DOBRODOSLI!");
        boolean kraj = false;
        while(!kraj) {
            kraj = Menu();
        }
    }

    public static boolean Menu() {
        System.out.println("1. Dodaj novog korisnika");
        System.out.println("2. Uplati clanarinu korisniku");
        System.out.println("3. Ubaci korisnika u grupu");
        System.out.println("4. Izlaz");
        char unos = Svetovid.in.readChar(":");

        //clearConsole();
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
                return true;

            default:
                System.out.println("Pogresan unos! Probajte opet");
            break;
        }

        for (Korisnik korisnik : teretana.vratiKorisnike()) {
            System.out.println(korisnik.toString());
            System.out.println(korisnik.getClanarina().getDatumIsteka());
        }

        return false;
    }

    public static void DodajKorisnikaUGrupu() {
        ArrayList<Grupa> grupe = GrupaKontroler.VratiSveGrupe(teretana.vratiLokal(lokalni));
        System.out.println("Izaberi grupu u koju zelite da ubacite korisnika: ");
        for (Grupa grupa : grupe) {
            System.out.println("0. " + grupa.toString());
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

        GrupaKontroler.dodajKorisnikaUGrupu(pronadji, grupe.get(i));

    }

    public static void DodajNovogClana() {
        System.out.println("~~~~Unos novog clana~~~~");
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


    //#region Regalacija unosa

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
