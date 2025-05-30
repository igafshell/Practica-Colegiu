import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

interface OperatiiCarte {
    double calculeazaPretInchiriere();

    void actualizeazaStare(String stare);
}

abstract class Persoana {
    protected int id;
    protected String nume;
    protected String prenume;
    protected String telefon;
    protected String dataNasterii;

    public Persoana() {
    }

    public Persoana(int id, String nume, String prenume, String telefon, String dataNasterii) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.telefon = telefon;
        this.dataNasterii = dataNasterii;
    }

    // Metode abstracte
    public abstract void afisareDetalii();

    // Getters si setters
    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getDataNasterii() {
        return dataNasterii;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setDataNasterii(String dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public String toString() {
        return "ID: " + id + ", Nume: " + nume + " " + prenume +
                ", Telefon: " + telefon + ", Data nasterii: " + dataNasterii;
    }
}

class Catalog {
    private int id;
    private String denumire;
    private String domeniu;
    private String descriere;

    public Catalog() {
    }

    public Catalog(int id, String denumire, String domeniu) {
        this.id = id;
        this.denumire = denumire;
        this.domeniu = domeniu;
    }

    public Catalog(int id, String denumire, String domeniu, String descriere) {
        this.id = id;
        this.denumire = denumire;
        this.domeniu = domeniu;
        this.descriere = descriere;
    }

    // Getters si setters
    public int getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public String getDomeniu() {
        return domeniu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setDomeniu(String domeniu) {
        this.domeniu = domeniu;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String toString() {
        return "ID: " + id + ", Denumire: " + denumire +
                ", Domeniu: " + domeniu + ", Descriere: " + descriere;
    }
}

class Carte implements OperatiiCarte {
    private int id;
    private String titlu;
    private String autor;
    private String editura;
    private String localitateEditura;
    private int anEditie;
    private int numarPagini;
    private double pret;
    private int numarExemplare;
    private int numarLecturi;
    private int idCatalog;

    public Carte() {
    }

    public Carte(int id, String titlu, String autor, double pret, int numarExemplare, int idCatalog) {
        this.id = id;
        this.titlu = titlu;
        this.autor = autor;
        this.pret = pret;
        this.numarExemplare = numarExemplare;
        this.idCatalog = idCatalog;
    }

    public Carte(int id, String titlu, String autor, String editura, String localitateEditura,
            int anEditie, int numarPagini, double pret, int numarExemplare, int idCatalog) {
        this.id = id;
        this.titlu = titlu;
        this.autor = autor;
        this.editura = editura;
        this.localitateEditura = localitateEditura;
        this.anEditie = anEditie;
        this.numarPagini = numarPagini;
        this.pret = pret;
        this.numarExemplare = numarExemplare;
        this.numarLecturi = 0;
        this.idCatalog = idCatalog;
    }

    @Override
    public double calculeazaPretInchiriere() {
        // Pretul scade cu 1% pentru fiecare lectura
        double pretRedus = pret * (1 - (0.01 * numarLecturi));
        return Math.max(pretRedus, pret * 0.5); // Minimum 50% din pret
    }

    @Override
    public void actualizeazaStare(String stare) {
        if (stare.equalsIgnoreCase("inchiriata")) {
            numarLecturi++;
            numarExemplare--;
        } else if (stare.equalsIgnoreCase("returnata")) {
            numarExemplare++;
        }
    }

    // Getters si setters
    public int getId() {
        return id;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditura() {
        return editura;
    }

    public String getLocalitateEditura() {
        return localitateEditura;
    }

    public int getAnEditie() {
        return anEditie;
    }

    public int getNumarPagini() {
        return numarPagini;
    }

    public double getPret() {
        return pret;
    }

    public int getNumarExemplare() {
        return numarExemplare;
    }

    public int getNumarLecturi() {
        return numarLecturi;
    }

    public int getIdCatalog() {
        return idCatalog;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public void setLocalitateEditura(String localitateEditura) {
        this.localitateEditura = localitateEditura;
    }

    public void setAnEditie(int anEditie) {
        this.anEditie = anEditie;
    }

    public void setNumarPagini(int numarPagini) {
        this.numarPagini = numarPagini;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public void setNumarExemplare(int numarExemplare) {
        this.numarExemplare = numarExemplare;
    }

    public void setIdCatalog(int idCatalog) {
        this.idCatalog = idCatalog;
    }

    public String toString() {
        return "ID: " + id + ", Titlu: " + titlu + ", Autor: " + autor +
                ", Editura: " + editura + ", Localitate: " + localitateEditura +
                ", An: " + anEditie + ", Pagini: " + numarPagini +
                ", Pret: " + pret + ", Exemplare: " + numarExemplare +
                ", Lecturi: " + numarLecturi + ", ID Catalog: " + idCatalog;
    }
}

class Cititor extends Persoana {
    private String numarCarnet;
    private final ArrayList<Integer> cartiImprumutate;

    public Cititor() {
        cartiImprumutate = new ArrayList<>();
    }

    public Cititor(int id, String nume, String prenume, String telefon, String dataNasterii, String numarCarnet) {
        super(id, nume, prenume, telefon, dataNasterii);
        this.numarCarnet = numarCarnet;
        this.cartiImprumutate = new ArrayList<>();
    }

    @Override
    public void afisareDetalii() {
        System.out.println("Cititor: " + super.toString() + ", Carnet: " + numarCarnet);
    }

    public void adaugaCarteImprumutata(int idCarte) {
        cartiImprumutate.add(Integer.valueOf(idCarte));
    }

    public void stergeCarteImprumutata(int idCarte) {
        for(int i = 0; i < cartiImprumutate.size(); i++) {
            if(cartiImprumutate.get(i) == idCarte) {
                cartiImprumutate.remove(i);
                break;
            }
        }
    }

    // Getters si setters
    public String getNumarCarnet() {
        return numarCarnet;
    }

    public List<Integer> getCartiImprumutate() {
        return cartiImprumutate;
    }

    public void setNumarCarnet(String numarCarnet) {
        this.numarCarnet = numarCarnet;
    }

    public String toString() {
        return super.toString() + ", Numar carnet: " + numarCarnet +
                ", Carti imprumutate: " + cartiImprumutate.size();
    }
}

class Angajat extends Persoana {
    private String pozitie;
    private double salariu;

    public Angajat() {
    }

    public Angajat(int id, String nume, String prenume, String telefon, String dataNasterii, String pozitie,
            double salariu) {
        super(id, nume, prenume, telefon, dataNasterii);
        this.pozitie = pozitie;
        this.salariu = salariu;
    }

    public void afisareDetalii() {
        System.out.println("Angajat: " + super.toString() + ", Pozitie: " + pozitie + ", Salariu: " + salariu);
    }

    // Getters si setters
    public String getPozitie() {
        return pozitie;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setPozitie(String pozitie) {
        this.pozitie = pozitie;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public String toString() {
        return super.toString() + ", Pozitie: " + pozitie + ", Salariu: " + salariu;
    }
}

class Trolleybooks {
    private int id;
    private String denumire;
    private String ruta;
    private int soferId;
    private int bibliotecarId;
    private int capacitateCarti;
    private int numarCartiCurente;

    public Trolleybooks() {
    }

    public Trolleybooks(int id, String denumire, String ruta, int soferId, int bibliotecarId,
            int capacitateCarti) {
        this.id = id;
        this.denumire = denumire;
        this.ruta = ruta;
        this.soferId = soferId;
        this.bibliotecarId = bibliotecarId;
        this.capacitateCarti = capacitateCarti;
        this.numarCartiCurente = 0;
    }

    // Getters si setters
    public int getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public String getRuta() {
        return ruta;
    }

    public int getSoferId() {
        return soferId;
    }

    public int getBibliotecarId() {
        return bibliotecarId;
    }

    public int getCapacitateCarti() {
        return capacitateCarti;
    }

    public int getNumarCartiCurente() {
        return numarCartiCurente;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void setSoferId(int soferId) {
        this.soferId = soferId;
    }

    public void setBibliotecarId(int bibliotecarId) {
        this.bibliotecarId = bibliotecarId;
    }

    public void setCapacitateCarti(int capacitateCarti) {
        this.capacitateCarti = capacitateCarti;
    }

    public void setNumarCartiCurente(int numarCartiCurente) {
        this.numarCartiCurente = numarCartiCurente;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Denumire: " + denumire + ", Ruta: " + ruta +
                ", ID Sofer: " + soferId + ", ID Bibliotecar: " + bibliotecarId +
                ", Capacitate: " + capacitateCarti + "/" + numarCartiCurente;
    }
}

class Inchiriere {
    private int id;
    private int idCititor;
    private int idCarte;
    private String dataInchiriere;
    private String dataReturnare;
    private boolean returnata;

    public Inchiriere() {
    }

    public Inchiriere(int id, int idCititor, int idCarte) {
        this.id = id;
        this.idCititor = idCititor;
        this.idCarte = idCarte;
    }

    public Inchiriere(int id, int idCititor, int idCarte, String dataInchiriere, String dataReturnare) {
        this.id = id;
        this.idCititor = idCititor;
        this.idCarte = idCarte;
        this.dataInchiriere = dataInchiriere;
        this.dataReturnare = dataReturnare;
        this.returnata = false;
    }

    // Getters si setters
    public int getId() {
        return id;
    }

    public int getIdCititor() {
        return idCititor;
    }

    public int getIdCarte() {
        return idCarte;
    }

    public String getDataInchiriere() {
        return dataInchiriere;
    }

    public String getDataReturnare() {
        return dataReturnare;
    }

    public boolean isReturnata() {
        return returnata;
    }

    public void setIdCititor(int idCititor) { this.idCititor = idCititor; }

    public void setIdCarte(int idCarte) { this.idCarte = idCarte; }

    public void setDataInchiriere(String dataInchiriere) { this.dataInchiriere = dataInchiriere; }

    public void setDataReturnare(String dataReturnare) {
        this.dataReturnare = dataReturnare;
    }

    public void setReturnata(boolean returnata) {
        this.returnata = returnata;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", ID Cititor: " + idCititor + ", ID Carte: " + idCarte +
                ", Data inchiriere: " + dataInchiriere + ", Data returnare: " + dataReturnare +
                ", Status: " + (returnata ? "Returnata" : "Inchiriata");
    }
}

class Cumparare {
    private int id;
    private int idCititor;
    private int idCarte;
    private String dataCumparare;
    private double pretPlatit;

    public Cumparare() {
    }

    public Cumparare(int id, int idCititor, int idCarte) {
        this.id = id;
        this.idCititor = idCititor;
        this.idCarte = idCarte;
    }

    public Cumparare(int id, int idCititor, int idCarte, String dataCumparare, double pretPlatit) {
        this.id = id;
        this.idCititor = idCititor;
        this.idCarte = idCarte;
        this.dataCumparare = dataCumparare;
        this.pretPlatit = pretPlatit;
    }

    // Getters si setters
    public int getId() {
        return id;
    }

    public int getIdCititor() {
        return idCititor;
    }

    public int getIdCarte() {
        return idCarte;
    }

    public String getDataCumparare() {
        return dataCumparare;
    }

    public double getPretPlatit() {
        return pretPlatit;
    }

    public void setIdCititor(int idCititor) { this.idCititor = idCititor; }

    public void setIdCarte(int idCarte) { this.idCarte = idCarte; }

    public void setDataCumparare(String dataCumparare) { this.dataCumparare = dataCumparare; }

    public void setPretPlatit(double pretPlatit) {
        this.pretPlatit = pretPlatit;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", ID Cititor: " + idCititor + ", ID Carte: " + idCarte +
                ", Data cumparare: " + dataCumparare + ", Pret platit: " + pretPlatit;
    }
}

class SistemTrolleybooks {
    public List<Catalog> cataloguri;
    public List<Carte> carti;
    public List<Cititor> cititori;
    public List<Angajat> angajati;
    public List<Trolleybooks> trolleybooks;
    public List<Inchiriere> inchirieri;
    public List<Cumparare> cumparari;
    public double venitTotal;

    public SistemTrolleybooks() {
        cataloguri = new ArrayList<>();
        carti = new ArrayList<>();
        cititori = new ArrayList<>();
        angajati = new ArrayList<>();
        trolleybooks = new ArrayList<>();
        inchirieri = new ArrayList<>();
        cumparari = new ArrayList<>();
        venitTotal = 0;
    }

    public void citesteCataloguriDinFisier(String numeFisier) {
        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    cataloguri.add(new Catalog(Integer.parseInt(parts[0].trim()), parts[1].trim(),
                            parts[2].trim(), parts[3].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului cataloguri: " + e.getMessage());
        }
    }

    public void citesteCartiDinFisier(String numeFisier) {
        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 10) {
                    carti.add(new Carte(Integer.parseInt(parts[0].trim()), parts[1].trim(), parts[2].trim(),
                            parts[3].trim(), parts[4].trim(),
                            Integer.parseInt(parts[5].trim()),
                            Integer.parseInt(parts[6].trim()),
                            Double.parseDouble(parts[7].trim()),
                            Integer.parseInt(parts[8].trim()),
                            Integer.parseInt(parts[9].trim())));
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului carti: " + e.getMessage());
        }

    }
    
    public void citesteCititoriDinFisier(String numeFisier) {
        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    cititori.add(new Cititor(Integer.parseInt(parts[0].trim()), parts[1].trim(), parts[2].trim(),
                            parts[3].trim(), parts[4].trim(), parts[5].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului cititori: " + e.getMessage());
        }
    }

    public void citesteAngajatiDinFisier(String numeFisier) {
        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    angajati.add(new Angajat(Integer.parseInt(parts[0].trim()), parts[1].trim(), parts[2].trim(),
                            parts[3].trim(), parts[4].trim(), parts[5].trim(),
                            Double.parseDouble(parts[6].trim())));
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului angajati: " + e.getMessage());
        }
    }

    public void citesteTrolleybooksDinFisier(String numeFisier) {
        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    trolleybooks.add(new Trolleybooks(Integer.parseInt(parts[0].trim()), parts[1].trim(), parts[2].trim(),
                            Integer.parseInt(parts[3].trim()), Integer.parseInt(parts[4].trim()), Integer.parseInt(parts[5].trim())));
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului trolleybooks: " + e.getMessage());
        }
    }

    public void citesteInchirieriDinFisier(String numeFisier) {
        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    inchirieri.add(new Inchiriere(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()),
                            parts[3].trim(), parts[4].trim()));
                    int cititorId = inchirieri.get(i).getIdCititor();
                    for(Cititor cititor : cititori) {
                        if(cititor.getId() == cititorId) {
                            cititor.adaugaCarteImprumutata(inchirieri.get(i).getIdCarte());
                        }
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului inchirieri: " + e.getMessage());
        }
    }

    public void citesteCumparariDinFisier(String numeFisier) {
        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    cumparari.add(new Cumparare(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()),
                            parts[3].trim(), Double.parseDouble(parts[4].trim())));
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului cumparari: " + e.getMessage());
        }
    }

    public static int citesteNumar(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Va rugam introduceti un numar valid!");
            }
        }
    }

    public static double citesteDecimal(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valoare invalida! Introdu un numar real.");
            }
        }
    }

    public static String citesteNume(Scanner scanner) {
        while (true) {
            String nume = scanner.nextLine().trim();
            if (nume.matches("[A-Z][a-z]+") && !nume.matches(".*\\d.*")) {
                return nume;
            }
            System.out.println("Numele trebuie sa inceapa cu majuscula si sa nu contina cifre!");
        }
    }

    public static String citesteData(Scanner scanner) {
        while (true) {
            String data = scanner.nextLine().trim();
            if (data.matches("\\d{2}.\\d{2}.\\d{4}")) {
                return data;
            }
            System.out.println("Format data invalid! Folositi formatul DD.MM.YYYY");
        }
    }

    public void afiseazaCartiDisponibile() {
        System.out.println("\nCarti disponibile:");
        for (Carte c : carti) {
            if (c.getNumarExemplare() > 0) {
                System.out.println(c);
            }
        }
    }

    public void afiseazaVenitTotal() {
        double venitInchirieri = 0;
        for (Inchiriere i : inchirieri) {
            for (Carte c : carti) {
                if (c.getId() == i.getIdCarte()) {
                    venitInchirieri += c.calculeazaPretInchiriere();
                    break;
                }
            }
        }

        double venitVanzari = 0;
        for (Cumparare cumparare : cumparari) {
            venitVanzari += cumparare.getPretPlatit();
        }

        venitTotal = venitInchirieri + venitVanzari;
        System.out.println("\nVenit total: " + venitTotal + " lei");
        System.out.println("Din inchirieri: " + venitInchirieri + " lei");
        System.out.println("Din vanzari: " + venitVanzari + " lei");
    }

    public void inchiriazaCarte(Scanner scanner) {
        System.out.println("\nInchiriere carte:");
        afiseazaCartiDisponibile();

        int idCarte = -1;
        Carte carteSelectata = null;
        while (carteSelectata == null) {
            System.out.print("Introduceti ID-ul cartii: ");
            idCarte = citesteNumar(scanner);

            for (Carte c : carti) {
                if (c.getId() == idCarte && c.getNumarExemplare() > 0) {
                    carteSelectata = c;
                    break;
                }
            }

            if (carteSelectata == null) {
                System.out.println("ID invalid sau carte nu este disponibila!");
            }
        }

        int idCititor = -1;
        Cititor cititorSelectat = null;
        while (cititorSelectat == null) {
            System.out.print("Introduceti ID-ul cititorului: ");
            idCititor = citesteNumar(scanner);

            for (Cititor c : cititori) {
                if (c.getId() == idCititor) {
                    cititorSelectat = c;
                    break;
                }
            }

            if (cititorSelectat == null) {
                System.out.println("ID cititor invalid!");
            }
        }
        System.out.println("Introduceti data inchirierii (DD.MM.YYYY): ");
        String dataInchiriere = citesteData(scanner);
        
        System.out.println("Introduceti data returnarii (DD.MM.YYYY): ");
        String dataReturnare = citesteData(scanner);

        int idInchiriere = inchirieri.size() + 1;
        Inchiriere inchiriere = new Inchiriere(idInchiriere, idCititor, idCarte, dataInchiriere, dataReturnare);
        inchirieri.add(inchiriere);
        carteSelectata.actualizeazaStare("inchiriata");
        cititorSelectat.adaugaCarteImprumutata(idCarte);

        System.out.println("Inchiriere creata cu succes! ID: " + idInchiriere);
        System.out.println("Pret inchiriere: " + carteSelectata.calculeazaPretInchiriere() + " lei");
    }

    public void cumparaCarte(Scanner scanner) {
        System.out.println("\nCumparare carte:");
        afiseazaCartiDisponibile();

        int idCarte = -1;
        Carte carteSelectata = null;
        while (carteSelectata == null) {
            System.out.print("Introduceti ID-ul cartii: ");
            idCarte = citesteNumar(scanner);

            for (Carte c : carti) {
                if (c.getId() == idCarte && c.getNumarExemplare() > 0) {
                    carteSelectata = c;
                    break;
                }
            }

            if (carteSelectata == null) {
                System.out.println("ID invalid sau carte nu este disponibila!");
            }
        }

        int idCititor = -1;
        Cititor cititorSelectat = null;
        while (cititorSelectat == null) {
            System.out.print("Introduceti ID-ul cititorului: ");
            idCititor = citesteNumar(scanner);

            for (Cititor c : cititori) {
                if (c.getId() == idCititor) {
                    cititorSelectat = c;
                    break;
                }
            }

            if (cititorSelectat == null) {
                System.out.println("ID cititor invalid!");
            }
        }

        System.out.println("Introduceti data cumpararii (DD.MM.YYYY): ");
        String dataCumparare = citesteData(scanner);
        double pretPlatit = carteSelectata.getPret();

        int idCumparare = cumparari.size() + 1;
        Cumparare cumparare = new Cumparare(idCumparare, idCititor, idCarte, dataCumparare, pretPlatit);
        cumparari.add(cumparare);
        carteSelectata.setNumarExemplare(carteSelectata.getNumarExemplare() - 1);

        System.out.println("Cumparare inregistrata cu succes! ID: " + idCumparare);
        System.out.println("Pret platit: " + pretPlatit + " lei");
    }

    public boolean returneazaCarte(int idInchiriere) {
        for (Inchiriere inchiriere : inchirieri) {
            if (inchiriere.getId() == idInchiriere && !inchiriere.isReturnata()) {
                Carte carte = null;
                Cititor cititor = null;
                for (Carte c : carti) {
                    if (c.getId() == inchiriere.getIdCarte()) {
                        carte = c;
                        break;
                    }
                }
                for (Cititor cit : cititori) {
                    if (cit.getId() == inchiriere.getIdCititor()) {
                        cititor = cit;
                        break;
                    }
                }
                if (carte != null && cititor != null) {
                    carte.actualizeazaStare("returnata");
                    cititor.stergeCarteImprumutata(carte.getId());
                    inchiriere.setReturnata(true);
                    SimpleDateFormat sdf = new SimpleDateFormat("DD.MM.YYYY");
                    inchiriere.setDataReturnare(sdf.format(new Date()));
                    return true;
                }
            }
        }
        return false;
    }

    public void adaugaEntitate(Scanner scanner) {
        System.out.println("Alege ce vrei sa adaugi:");
        System.out.println("1. Catalog");
        System.out.println("2. Carte");
        System.out.println("3. Cititor");
        System.out.println("4. Angajat");
        System.out.println("5. Trolleybooks");
        System.out.println("6. Inchiriere");
        System.out.println("7. Cumparare");
        System.out.println("Optiune: ");
        int opt = citesteNumar(scanner);

        switch (opt) {
            case 1 -> adaugaCatalog(scanner);
            case 2 -> adaugaCarte(scanner);
            case 3 -> adaugaCititor(scanner);
            case 4 -> adaugaAngajat(scanner);
            case 5 -> adaugaTrolleybooks(scanner);
            case 6 -> adaugaInchiriere(scanner);
            case 7 -> adaugaCumparare(scanner);
            default -> System.out.println("Optiune invalida!");
        }
    }

    private void adaugaCatalog(Scanner scanner) {
        int id = -1;
        for(Catalog c : cataloguri) {
            if(c.getId() > id) id = c.getId();
        }
        id = id + 1;
        System.out.print("Denumire: ");
        String denumire = scanner.nextLine();
        System.out.print("Domeniu: ");
        String domeniu = scanner.nextLine();
        System.out.print("Descriere: ");
        String descriere = scanner.nextLine();

        cataloguri.add(new Catalog(id, denumire, domeniu, descriere));
        System.out.println("Catalog adaugat.");
    }

    private void adaugaCarte(Scanner scanner) {
        int id = -1;
        for(Carte c : carti) {
            if(c.getId() > id) id = c.getId();
        }
        id = id + 1;
        System.out.print("Titlu: ");
        String titlu = citesteNume(scanner);
        System.out.print("Autor: ");
        String autor = citesteNume(scanner);
        System.out.print("Editura: ");
        String editura = citesteNume(scanner);
        System.out.print("Localitate editura: ");
        String localitate = citesteNume(scanner);

        System.out.println("An editie: ");
        int anEditie = citesteNumar(scanner);
        System.out.println("Numar pagini: ");
        int numarPagini = citesteNumar(scanner);
        System.out.println("Pret: ");
        double pret = citesteDecimal(scanner);
        System.out.println("Numar exemplare: ");
        int numarExemplare = citesteNumar(scanner);
        System.out.println("ID catalog: ");
        int idCatalog = citesteNumar(scanner);

        carti.add(new Carte(id, titlu, autor, editura, localitate, anEditie, numarPagini, pret, numarExemplare, idCatalog));
        System.out.println("Carte adaugata.");
    }

    private void adaugaCititor(Scanner scanner) {
        int id = -1;
        for(Cititor c : cititori) {
            if(c.getId() > id) id = c.getId();
        }
        id = id + 1;
        System.out.print("Nume: ");
        String nume = citesteNume(scanner);
        System.out.print("Prenume: ");
        String prenume = citesteNume(scanner);
        System.out.print("Telefon: ");
        String telefon = scanner.nextLine();
        System.out.print("Data nasterii (DD.MM.YYYY): ");
        String dataNasterii = citesteData(scanner);
        System.out.print("Numar carnet: ");
        String numarCarnet = scanner.nextLine();

        cititori.add(new Cititor(id, nume, prenume, telefon, dataNasterii, numarCarnet));
        System.out.println("Cititor adaugat.");
    }

    private void adaugaAngajat(Scanner scanner) {
        int id = -1;
        for(Angajat c : angajati) {
            if(c.getId() > id) id = c.getId();
        }
        id = id + 1;
        System.out.print("Nume: ");
        String nume = citesteNume(scanner);
        System.out.print("Prenume: ");
        String prenume = citesteNume(scanner);
        System.out.print("Telefon: ");
        String telefon = scanner.nextLine();
        System.out.print("Data nasterii (DD.MM.YYYY): ");
        String dataNasterii = citesteData(scanner);
        System.out.print("Pozitie: ");
        String pozitie = citesteNume(scanner);
        System.out.print("Salariu: ");
        double salariu = citesteDecimal(scanner);

        angajati.add(new Angajat(id, nume, prenume, telefon, dataNasterii, pozitie, salariu));
        System.out.println("Angajat adaugat.");
    }

    private void adaugaTrolleybooks(Scanner scanner) {
        int id = -1;
        for(Trolleybooks c : trolleybooks) {
            if(c.getId() > id) id = c.getId();
        }
        id = id + 1;
        System.out.print("Denumire: ");
        String denumire = citesteNume(scanner);
        System.out.print("Ruta: ");
        String ruta = scanner.nextLine();
        System.out.print("ID sofer: ");
        int soferId = citesteNumar(scanner);
        System.out.println("ID bibliotecar: ");
        int bibliotecarId = citesteNumar(scanner);
        System.out.println("Capacitate carti: ");
        int capacitate = citesteNumar(scanner);

        trolleybooks.add(new Trolleybooks(id, denumire, ruta, soferId, bibliotecarId, capacitate));
        System.out.println("Trolleybooks adaugat.");
    }

    private void adaugaInchiriere(Scanner scanner) {
        int id = -1;
        for(Inchiriere c : inchirieri) {
            if(c.getId() > id) id = c.getId();
        }
        id = id + 1;
        System.out.println("ID cititor: ");
        int idCititor = citesteNumar(scanner);
        System.out.println("ID carte: ");
        int idCarte = citesteNumar(scanner);
        System.out.print("Data inchiriere (DD.MM.YYYY): ");
        String dataInchiriere = citesteData(scanner);
        System.out.print("Data returnare (DD.MM.YYYY): ");
        String dataReturnare = citesteData(scanner);

        inchirieri.add(new Inchiriere(id, idCititor, idCarte, dataInchiriere, dataReturnare));
        System.out.println("Inchiriere adaugata.");
    }

    private void adaugaCumparare(Scanner scanner) {
        int id = -1;
        for(Cumparare c : cumparari) {
            if(c.getId() > id) id = c.getId();
        }
        id = id + 1;
        System.out.println("ID cititor: ");
        int idCititor = citesteNumar(scanner);
        System.out.println("ID carte: ");
        int idCarte = citesteNumar(scanner);
        System.out.print("Data cumparare (DD.MM.YYYY): ");
        String dataCumparare = citesteData(scanner);
        System.out.println("Pret platit: ");
        double pretPlatit = citesteDecimal(scanner);

        cumparari.add(new Cumparare(id, idCititor, idCarte, dataCumparare, pretPlatit));
        System.out.println("Cumparare adaugata.");
    }

    public void afiseazaEntitati(Scanner scanner) {
        System.out.println("Alege ce vrei sa afisezi:");
        System.out.println("1. Cataloage");
        System.out.println("2. Carti");
        System.out.println("3. Cititori");
        System.out.println("4. Angajati");
        System.out.println("5. Trolleybooks");
        System.out.println("6. Inchirieri");
        System.out.println("7. Cumparari");
        System.out.print("Optiune: ");
        int opt = citesteNumar(scanner);

        switch (opt) {
            case 1 -> afiseazaLista(cataloguri, "Cataloage");
            case 2 -> afiseazaLista(carti, "Carti");
            case 3 -> afiseazaLista(cititori, "Cititori");
            case 4 -> afiseazaLista(angajati, "Angajati");
            case 5 -> afiseazaLista(trolleybooks, "Trolleybooks");
            case 6 -> afiseazaLista(inchirieri, "Inchirieri");
            case 7 -> afiseazaLista(cumparari, "Cumparari");
            default -> System.out.println("Optiune invalida!");
        }
    }

    public void stergeEntitate(Scanner scanner) {
        System.out.println("Alege ce vrei sa stergi:");
        System.out.println("1. Catalog");
        System.out.println("2. Carte");
        System.out.println("3. Cititor");
        System.out.println("4. Angajat");
        System.out.println("5. Trolleybooks");
        System.out.println("6. Inchiriere");
        System.out.println("7. Cumparare");
        System.out.print("Optiune: ");
        int opt = citesteNumar(scanner);

        switch (opt) {
            case 1 -> stergeCatalog(scanner);
            case 2 -> stergeCarte(scanner);
            case 3 -> stergeCititor(scanner);
            case 4 -> stergeAngajat(scanner);
            case 5 -> stergeTrolleybooks(scanner);
            case 6 -> stergeInchiriere(scanner);
            case 7 -> stergeCumparare(scanner);
            default -> System.out.println("Optiune invalida!");
        }
    }

    private void stergeCatalog(Scanner scanner) {
        afiseazaLista(cataloguri, "Cataloage");
        System.out.print("Introduceti ID-ul catalogului de sters: ");
        int id = citesteNumar(scanner);

        boolean found = false;
        for (int i = 0; i < cataloguri.size(); i++) {
            if (cataloguri.get(i).getId() == id) {
                cataloguri.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Catalog sters cu succes!");
        } else {
            System.out.println("Catalogul nu a fost gasit!");
        }
    }

    private void stergeCarte(Scanner scanner) {
        afiseazaLista(carti, "Carti");
        System.out.print("Introduceti ID-ul cartii de sters: ");
        int id = citesteNumar(scanner);

        boolean found = false;
        for (int i = 0; i < carti.size(); i++) {
            if (carti.get(i).getId() == id) {
                carti.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Carte stearsa cu succes!");
        } else {
            System.out.println("Cartea nu a fost gasita!");
        }
    }

    private void stergeCititor(Scanner scanner) {
        afiseazaLista(cititori, "Cititori");
        System.out.print("Introduceti ID-ul cititorului de sters: ");
        int id = citesteNumar(scanner);

        boolean found = false;
        for (int i = 0; i < cititori.size(); i++) {
            if (cititori.get(i).getId() == id) {
                cititori.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Cititor sters cu succes!");
        } else {
            System.out.println("Cititorul nu a fost gasit!");
        }
    }

    private void stergeAngajat(Scanner scanner) {
        afiseazaLista(angajati, "Angajati");
        System.out.print("Introduceti ID-ul angajatului de sters: ");
        int id = citesteNumar(scanner);

        boolean found = false;
        for (int i = 0; i < angajati.size(); i++) {
            if (angajati.get(i).getId() == id) {
                angajati.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Angajat sters cu succes!");
        } else {
            System.out.println("Angajatul nu a fost gasit!");
        }
    }

    private void stergeTrolleybooks(Scanner scanner) {
        afiseazaLista(trolleybooks, "Trolleybooks");
        System.out.print("Introduceti ID-ul trolleybooks de sters: ");
        int id = citesteNumar(scanner);

        boolean found = false;
        for (int i = 0; i < trolleybooks.size(); i++) {
            if (trolleybooks.get(i).getId() == id) {
                trolleybooks.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Trolleybooks sters cu succes!");
        } else {
            System.out.println("Trolleybooks nu a fost gasit!");
        }
    }

    private void stergeInchiriere(Scanner scanner) {
        afiseazaLista(inchirieri, "Inchirieri");
        System.out.print("Introduceti ID-ul inchirierii de sters: ");
        int id = citesteNumar(scanner);

        boolean found = false;
        for (int i = 0; i < inchirieri.size(); i++) {
            if (inchirieri.get(i).getId() == id) {
                inchirieri.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Inchiriere stearsa cu succes!");
        } else {
            System.out.println("Inchirierea nu a fost gasita!");
        }
    }

    private void stergeCumparare(Scanner scanner) {
        afiseazaLista(cumparari, "Cumparari");
        System.out.print("Introduceti ID-ul cumpararii de sters: ");
        int id = citesteNumar(scanner);

        boolean found = false;
        for (int i = 0; i < cumparari.size(); i++) {
            if (cumparari.get(i).getId() == id) {
                cumparari.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Cumparare stearsa cu succes!");
        } else {
            System.out.println("Cumpararea nu a fost gasita!");
        }
    }

    public void modificaEntitate(Scanner scanner) {
        System.out.println("Alege ce vrei sa modifici:");
        System.out.println("1. Catalog");
        System.out.println("2. Carte");
        System.out.println("3. Cititor");
        System.out.println("4. Angajat");
        System.out.println("5. Trolleybooks");
        System.out.println("6. Inchiriere");
        System.out.println("7. Cumparare");
        System.out.print("Optiune: ");
        int opt = citesteNumar(scanner);

        switch (opt) {
            case 1 -> modificaCatalog(scanner);
            case 2 -> modificaCarte(scanner);
            case 3 -> modificaCititor(scanner);
            case 4 -> modificaAngajat(scanner);
            case 5 -> modificaTrolleybooks(scanner);
            case 6 -> modificaInchiriere(scanner);
            case 7 -> modificaCumparare(scanner);
            default -> System.out.println("Optiune invalida!");
        }
    }

    private void modificaCatalog(Scanner scanner) {
        afiseazaLista(cataloguri, "Cataloage");
        System.out.print("Introduceti ID-ul catalogului de modificat: ");
        int id = citesteNumar(scanner);

        Catalog catalog = null;
        for (Catalog c : cataloguri) {
            if (c.getId() == id) {
                catalog = c;
                break;
            }
        }

        if (catalog == null) {
            System.out.println("Catalogul nu a fost gasit!");
            return;
        }

        System.out.println("Date curente: " + catalog);
        System.out.println("Introduceti noile date (lasati gol pentru a pastra valoarea curenta):");

        System.out.print("Denumire (" + catalog.getDenumire() + "): ");
        String denumire = scanner.nextLine();
        if (!denumire.isEmpty()) {
            catalog.setDenumire(denumire);
        }

        System.out.print("Domeniu (" + catalog.getDomeniu() + "): ");
        String domeniu = scanner.nextLine();
        if (!domeniu.isEmpty()) {
            catalog.setDomeniu(domeniu);
        }

        System.out.print("Descriere (" + catalog.getDescriere() + "): ");
        String descriere = scanner.nextLine();
        if (!descriere.isEmpty()) {
            catalog.setDescriere(descriere);
        }

        System.out.println("Catalog actualizat cu succes!");
    }

    private void modificaCarte(Scanner scanner) {
        afiseazaLista(carti, "Carti");
        System.out.print("Introduceti ID-ul cartii de modificat: ");
        int id = citesteNumar(scanner);

        Carte carte = null;
        for (Carte c : carti) {
            if (c.getId() == id) {
                carte = c;
                break;
            }
        }

        if (carte == null) {
            System.out.println("Cartea nu a fost gasita!");
            return;
        }

        System.out.println("Date curente: " + carte);
        System.out.println("Introduceti noile date (lasati gol pentru a pastra valoarea curenta):");

        System.out.print("Titlu (" + carte.getTitlu() + "): ");
        String titlu = scanner.nextLine();
        if (!titlu.isEmpty()) {
            carte.setTitlu(titlu);
        }

        System.out.print("Autor (" + carte.getAutor() + "): ");
        String autor = scanner.nextLine();
        if (!autor.isEmpty()) {
            carte.setAutor(autor);
        }

        System.out.print("Editura (" + carte.getEditura() + "): ");
        String editura = scanner.nextLine();
        if (!editura.isEmpty()) {
            carte.setEditura(editura);
        }

        System.out.print("Localitate editura (" + carte.getLocalitateEditura() + "): ");
        String localitate = scanner.nextLine();
        if (!localitate.isEmpty()) {
            carte.setLocalitateEditura(localitate);
        }

        System.out.print("An editie (" + carte.getAnEditie() + "): ");
        String anEditieStr = scanner.nextLine();
        if (!anEditieStr.isEmpty()) {
            carte.setAnEditie(Integer.parseInt(anEditieStr));
        }

        System.out.print("Numar pagini (" + carte.getNumarPagini() + "): ");
        String paginiStr = scanner.nextLine();
        if (!paginiStr.isEmpty()) {
            carte.setNumarPagini(Integer.parseInt(paginiStr));
        }

        System.out.print("Pret (" + carte.getPret() + "): ");
        String pretStr = scanner.nextLine();
        if (!pretStr.isEmpty()) {
            carte.setPret(Double.parseDouble(pretStr));
        }

        System.out.print("Numar exemplare (" + carte.getNumarExemplare() + "): ");
        String exemplareStr = scanner.nextLine();
        if (!exemplareStr.isEmpty()) {
            carte.setNumarExemplare(Integer.parseInt(exemplareStr));
        }

        System.out.print("ID catalog (" + carte.getIdCatalog() + "): ");
        String catalogStr = scanner.nextLine();
        if (!catalogStr.isEmpty()) {
            carte.setIdCatalog(Integer.parseInt(catalogStr));
        }

        System.out.println("Carte actualizata cu succes!");
    }

    private void modificaCititor(Scanner scanner) {
        afiseazaLista(cititori, "Cititori");
        System.out.print("Introduceti ID-ul cititorului de modificat: ");
        int id = citesteNumar(scanner);

        Cititor cititor = null;
        for (Cititor c : cititori) {
            if (c.getId() == id) {
                cititor = c;
                break;
            }
        }

        if (cititor == null) {
            System.out.println("Cititorul nu a fost gasit!");
            return;
        }

        System.out.println("Date curente: " + cititor);
        System.out.println("Introduceti noile date (lasati gol pentru a pastra valoarea curenta):");

        System.out.print("Nume (" + cititor.getNume() + "): ");
        String nume = scanner.nextLine();
        if (!nume.isEmpty()) {
            cititor.setNume(nume);
        }

        System.out.print("Prenume (" + cititor.getPrenume() + "): ");
        String prenume = scanner.nextLine();
        if (!prenume.isEmpty()) {
            cititor.setPrenume(prenume);
        }

        System.out.print("Telefon (" + cititor.getTelefon() + "): ");
        String telefon = scanner.nextLine();
        if (!telefon.isEmpty()) {
            cititor.setTelefon(telefon);
        }

        System.out.print("Data nasterii (" + cititor.getDataNasterii() + "): ");
        String dataNasterii = scanner.nextLine();
        if (!dataNasterii.isEmpty()) {
            cititor.setDataNasterii(dataNasterii);
        }

        System.out.print("Numar carnet (" + cititor.getNumarCarnet() + "): ");
        String carnet = scanner.nextLine();
        if (!carnet.isEmpty()) {
            cititor.setNumarCarnet(carnet);
        }

        System.out.println("Cititor actualizat cu succes!");
    }

    private void modificaAngajat(Scanner scanner) {
        afiseazaLista(angajati, "Angajati");
        System.out.print("Introduceti ID-ul angajatului de modificat: ");
        int id = citesteNumar(scanner);

        Angajat angajat = null;
        for (Angajat a : angajati) {
            if (a.getId() == id) {
                angajat = a;
                break;
            }
        }

        if (angajat == null) {
            System.out.println("Angajatul nu a fost gasit!");
            return;
        }

        System.out.println("Date curente: " + angajat);
        System.out.println("Introduceti noile date (lasati gol pentru a pastra valoarea curenta):");

        System.out.print("Nume (" + angajat.getNume() + "): ");
        String nume = scanner.nextLine();
        if (!nume.isEmpty()) {
            angajat.setNume(nume);
        }

        System.out.print("Prenume (" + angajat.getPrenume() + "): ");
        String prenume = scanner.nextLine();
        if (!prenume.isEmpty()) {
            angajat.setPrenume(prenume);
        }

        System.out.print("Telefon (" + angajat.getTelefon() + "): ");
        String telefon = scanner.nextLine();
        if (!telefon.isEmpty()) {
            angajat.setTelefon(telefon);
        }

        System.out.print("Data nasterii (" + angajat.getDataNasterii() + "): ");
        String dataNasterii = scanner.nextLine();
        if (!dataNasterii.isEmpty()) {
            angajat.setDataNasterii(dataNasterii);
        }

        System.out.print("Pozitie (" + angajat.getPozitie() + "): ");
        String pozitie = scanner.nextLine();
        if (!pozitie.isEmpty()) {
            angajat.setPozitie(pozitie);
        }

        System.out.print("Salariu (" + angajat.getSalariu() + "): ");
        String salariuStr = scanner.nextLine();
        if (!salariuStr.isEmpty()) {
            angajat.setSalariu(Double.parseDouble(salariuStr));
        }

        System.out.println("Angajat actualizat cu succes!");
    }

    private void modificaTrolleybooks(Scanner scanner) {
        afiseazaLista(trolleybooks, "Trolleybooks");
        System.out.print("Introduceti ID-ul trolleybooks de modificat: ");
        int id = citesteNumar(scanner);

        Trolleybooks trolley = null;
        for (Trolleybooks t : trolleybooks) {
            if (t.getId() == id) {
                trolley = t;
                break;
            }
        }

        if (trolley == null) {
            System.out.println("Trolleybooks nu a fost gasit!");
            return;
        }

        System.out.println("Date curente: " + trolley);
        System.out.println("Introduceti noile date (lasati gol pentru a pastra valoarea curenta):");

        System.out.print("Denumire (" + trolley.getDenumire() + "): ");
        String denumire = scanner.nextLine();
        if (!denumire.isEmpty()) {
            trolley.setDenumire(denumire);
        }

        System.out.print("Ruta (" + trolley.getRuta() + "): ");
        String ruta = scanner.nextLine();
        if (!ruta.isEmpty()) {
            trolley.setRuta(ruta);
        }

        System.out.print("ID sofer (" + trolley.getSoferId() + "): ");
        String soferStr = scanner.nextLine();
        if (!soferStr.isEmpty()) {
            trolley.setSoferId(Integer.parseInt(soferStr));
        }

        System.out.print("ID bibliotecar (" + trolley.getBibliotecarId() + "): ");
        String biblStr = scanner.nextLine();
        if (!biblStr.isEmpty()) {
            trolley.setBibliotecarId(Integer.parseInt(biblStr));
        }

        System.out.print("Capacitate carti (" + trolley.getCapacitateCarti() + "): ");
        String capStr = scanner.nextLine();
        if (!capStr.isEmpty()) {
            trolley.setCapacitateCarti(Integer.parseInt(capStr));
        }

        System.out.println("Trolleybooks actualizat cu succes!");
    }

    private void modificaInchiriere(Scanner scanner) {
        afiseazaLista(inchirieri, "Inchirieri");
        System.out.print("Introduceti ID-ul inchirierii de modificat: ");
        int id = citesteNumar(scanner);

        Inchiriere inchiriere = null;
        for (Inchiriere i : inchirieri) {
            if (i.getId() == id) {
                inchiriere = i;
                break;
            }
        }

        if (inchiriere == null) {
            System.out.println("Inchirierea nu a fost gasita!");
            return;
        }

        System.out.println("Date curente: " + inchiriere);
        System.out.println("Introduceti noile date (lasati gol pentru a pastra valoarea curenta):");

        System.out.print("ID cititor (" + inchiriere.getIdCititor() + "): ");
        String cititorStr = scanner.nextLine();
        if (!cititorStr.isEmpty()) {
            inchiriere.setIdCititor(Integer.parseInt(cititorStr));
        }

        System.out.print("ID carte (" + inchiriere.getIdCarte() + "): ");
        String carteStr = scanner.nextLine();
        if (!carteStr.isEmpty()) {
            inchiriere.setIdCarte(Integer.parseInt(carteStr));
        }

        System.out.print("Data inchiriere (" + inchiriere.getDataInchiriere() + "): ");
        String dataInchiriere = scanner.nextLine();
        if (!dataInchiriere.isEmpty()) {
            inchiriere.setDataInchiriere(dataInchiriere);
        }

        System.out.print("Data returnare (" + inchiriere.getDataReturnare() + "): ");
        String dataReturnare = scanner.nextLine();
        if (!dataReturnare.isEmpty()) {
            inchiriere.setDataReturnare(dataReturnare);
        }

        System.out.print("Returnata (" + inchiriere.isReturnata() + "): ");
        String returnataStr = scanner.nextLine();
        if (!returnataStr.isEmpty()) {
            inchiriere.setReturnata(Boolean.parseBoolean(returnataStr));
        }

        System.out.println("Inchiriere actualizata cu succes!");
    }

    private void modificaCumparare(Scanner scanner) {
        afiseazaLista(cumparari, "Cumparari");
        System.out.print("Introduceti ID-ul cumpararii de modificat: ");
        int id = citesteNumar(scanner);

        Cumparare cumparare = null;
        for (Cumparare c : cumparari) {
            if (c.getId() == id) {
                cumparare = c;
                break;
            }
        }

        if (cumparare == null) {
            System.out.println("Cumpararea nu a fost gasita!");
            return;
        }

        System.out.println("Date curente: " + cumparare);
        System.out.println("Introduceti noile date (lasati gol pentru a pastra valoarea curenta):");

        System.out.print("ID cititor (" + cumparare.getIdCititor() + "): ");
        String cititorStr = scanner.nextLine();
        if (!cititorStr.isEmpty()) {
            cumparare.setIdCititor(Integer.parseInt(cititorStr));
        }

        System.out.print("ID carte (" + cumparare.getIdCarte() + "): ");
        String carteStr = scanner.nextLine();
        if (!carteStr.isEmpty()) {
            cumparare.setIdCarte(Integer.parseInt(carteStr));
        }

        System.out.print("Data cumparare (" + cumparare.getDataCumparare() + "): ");
        String dataCumparare = scanner.nextLine();
        if (!dataCumparare.isEmpty()) {
            cumparare.setDataCumparare(dataCumparare);
        }

        System.out.print("Pret platit (" + cumparare.getPretPlatit() + "): ");
        String pretStr = scanner.nextLine();
        if (!pretStr.isEmpty()) {
            cumparare.setPretPlatit(Double.parseDouble(pretStr));
        }

        System.out.println("Cumparare actualizata cu succes!");
    }

    public void afiseazaMeniu() {
        System.out.println("=======================================================================================================");
        System.out.println(" ████████╗██████╗  ██████╗ ██╗     ██╗     ███████╗██╗   ██╗██████╗  ██████╗  ██████╗ ██╗  ██╗███████╗");
        System.out.println(" ╚══██╔══╝██╔══██╗██╔═══██╗██║     ██║     ██╔════╝╚██╗ ██╔╝██╔══██╗██╔═══██╗██╔═══██╗██║ ██╔╝██╔════╝");
        System.out.println("    ██║   ██████╔╝██║   ██║██║     ██║     █████╗   ╚████╔╝ ██████╔╝██║   ██║██║   ██║█████╔╝ ███████╗");
        System.out.println("    ██║   ██╔══██╗██║   ██║██║     ██║     ██╔══╝    ╚██╔╝  ██╔══██╗██║   ██║██║   ██║██╔═██╗ ╚════██║");
        System.out.println("    ██║   ██║  ██║╚██████╔╝███████╗███████╗███████╗   ██║   ██████╔╝╚██████╔╝╚██████╔╝██║  ██╗███████║");
        System.out.println("    ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚══════╝╚══════╝   ╚═╝   ╚═════╝  ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚══════╝");
        System.out.println("=======================================================================================================");
        System.out.println("1. Afiseaza entitati");
        System.out.println("2. Adauga entitate");
        System.out.println("3. Modifica entitate");
        System.out.println("4. Sterge entitate");
        System.out.println("5. Afiseaza carti disponibile");
        System.out.println("6. Inchiriaza carte");
        System.out.println("7. Cumpara carte");
        System.out.println("8. Returneaza carte");
        System.out.println("9. Afiseaza venit total");
        System.out.println("0. Iesire");
        System.out.print("Selectati optiunea: ");
    }

    public void ruleaza() {
        Scanner scanner = new Scanner(System.in);

        citesteCataloguriDinFisier("Cataloguri.in");
        citesteCartiDinFisier("Carti.in");
        citesteCititoriDinFisier("Cititori.in");
        citesteAngajatiDinFisier("Angajati.in");
        citesteTrolleybooksDinFisier("Trolleybooks.in");
        citesteInchirieriDinFisier("Inchirieri.in");
        citesteCumparariDinFisier("Cumparari.in");

        boolean running = true;
        while (running) {
            afiseazaMeniu();
            int optiune = citesteNumar(scanner);

            switch (optiune) {
                case 1:
                    afiseazaEntitati(scanner);
                    break;
                case 2:
                    adaugaEntitate(scanner);
                    break;
                case 3:
                    modificaEntitate(scanner);
                    break;
                case 4:
                    stergeEntitate(scanner);
                    break;
                case 5:
                    afiseazaCartiDisponibile();
                    break;
                case 6:
                    inchiriazaCarte(scanner);
                    break;
                case 7:
                    cumparaCarte(scanner);
                    break;
                case 8:
                    System.out.println("Introduceti id-ul cartii care doriti sa o returnati:");
                    int idCarte = citesteNumar(scanner);
                    if (returneazaCarte(idCarte)) {
                        System.out.println("Cartea a fost returnata cu succes!");
                    } else {
                        System.out.println("Cartea nu a fost gasita sau nu poate fi returnata!");
                    }
                    break;
                case 9:
                    afiseazaVenitTotal();
                    break;
                case 0:
                    running = false;
                    System.out.println("La revedere!");
                    break;
                default:
                    System.out.println("Optiune invalida!");
            }
        }
        scanner.close();
    }

    private <T> void afiseazaLista(List<T> lista, String titlu) {
        System.out.println("\n=== " + titlu + " ===");
        for (T item : lista) {
            System.out.println(item);
        }
    }
}

// Clasa principala
public class TrolleybooksConsola {
    public static void main(String[] args) {
        SistemTrolleybooks sistem = new SistemTrolleybooks();
        sistem.ruleaza();
    }
}