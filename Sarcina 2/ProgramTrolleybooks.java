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

    @Override
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

    @Override
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

    @Override
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
        cartiImprumutate.add(idCarte);
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

    @Override
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

    @Override
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

    @Override
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
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    inchiriere.setDataReturnare(sdf.format(new Date()));
                    return true;
                }
            }
        }
        return false;
    }

    public void afiseazaMeniu() {
        System.out.println("\n===== MENIU PRINCIPAL =====");
        System.out.println("1. Afiseaza carti disponibile");
        System.out.println("2. Inchiriaza carte");
        System.out.println("3. Cumpara carte");
        System.out.println("4. Returneaza carte");
        System.out.println("5. Afiseaza toate inchirierile");
        System.out.println("6. Afiseaza toate cumpararile");
        System.out.println("7. Afiseaza toti cititorii");
        System.out.println("8. Afiseaza toti angajatii");
        System.out.println("9. Afiseaza toate trolleybooks");
        System.out.println("10. Afiseaza toate catalogurile");
        System.out.println("11. Afiseaza toate cartile");
        System.out.println("12. Afiseaza venit total");
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
                    afiseazaCartiDisponibile();
                    break;
                case 2:
                    inchiriazaCarte(scanner);
                    break;
                case 3:
                    cumparaCarte(scanner);
                    break;
                case 4:
                    System.out.println("Introduceti id-ul cartii care doriti sa o inchiriati:");
                    int idCarte = citesteNumar(scanner);
                    if (returneazaCarte(idCarte)) {
                        System.out.println("Cartea a fost returnata cu succes!");
                    } else {
                        System.out.println("Cartea nu a fost gasita sau nu poate fi returnata!");
                    }
                    break;
                case 5:
                    afiseazaLista(inchirieri, "Inchirieri");
                    break;
                case 6:
                    afiseazaLista(cumparari, "Cumparari");
                    break;
                case 7:
                    afiseazaLista(cititori, "Cititori");
                    break;
                case 8:
                    afiseazaLista(angajati, "Angajati");
                    break;
                case 9:
                    afiseazaLista(trolleybooks, "Trolleybooks");
                    break;
                case 10:
                    afiseazaLista(cataloguri, "Cataloguri");
                    break;
                case 11:
                    afiseazaLista(carti, "Carti");
                    break;
                case 12:
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
public class ProgramTrolleybooks {
    public static void main(String[] args) {
        SistemTrolleybooks sistem = new SistemTrolleybooks();
        sistem.ruleaza();
    }
}