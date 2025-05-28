import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrolleybooksUI {
    private final SistemTrolleybooks sistem;
    private JFrame fereastra;
    private JTextArea zonaLoguri;

    public TrolleybooksUI() {
        sistem = new SistemTrolleybooks();
        init();
        sistem.citesteCataloguriDinFisier("Cataloguri.in");
        sistem.citesteCartiDinFisier("Carti.in");
        sistem.citesteCititoriDinFisier("Cititori.in");
        sistem.citesteAngajatiDinFisier("Angajati.in");
        sistem.citesteTrolleybooksDinFisier("Trolleybooks.in");
        sistem.citesteInchirieriDinFisier("Inchirieri.in");
        sistem.citesteCumparariDinFisier("Cumparari.in");
    }

    private void init() {
        fereastra = new JFrame("Sistem Trolleybooks");
        fereastra.setSize(800, 600);
        fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fereastra.setLayout(new BorderLayout());

        // Cream bara de meniu
        JMenuBar baraMeniu = new JMenuBar();

        // Meniu program
        JMenu programMeniu = new JMenu("Program");
        JMenuItem iesireItem = new JMenuItem("Iesire");
        iesireItem.addActionListener(e -> System.exit(0));
        programMeniu.add(iesireItem);
        baraMeniu.add(programMeniu);

        // Meniu Carti
        JMenu cartiMeniu = new JMenu("Carti");

        JMenuItem afiseazaToateCartileItem = new JMenuItem("Afiseaza toate cartile");
        afiseazaToateCartileItem.addActionListener(e -> afiseazaToateCartile());
        cartiMeniu.add(afiseazaToateCartileItem);

        JMenuItem afiseazaCartileDisponibileItem = new JMenuItem("Arata cartile disponibile");
        afiseazaCartileDisponibileItem.addActionListener(e -> afiseazaCartileDisponibile());
        cartiMeniu.add(afiseazaCartileDisponibileItem);

        JMenuItem inchiriazaCarteItem = new JMenuItem("Inchiriaza carte");
        inchiriazaCarteItem.addActionListener(e -> inchiriazaCarte());
        cartiMeniu.add(inchiriazaCarteItem);

        JMenuItem cumparaCarteItem = new JMenuItem("Cumpara carte");
        cumparaCarteItem.addActionListener(e -> cumparaCarte());
        cartiMeniu.add(cumparaCarteItem);

        JMenuItem returneazaCarte = new JMenuItem("Returneaza carte");
        returneazaCarte.addActionListener(e -> returneazaCarte());
        cartiMeniu.add(returneazaCarte);

        baraMeniu.add(cartiMeniu);

        // Reports submenu
        JMenu rapoarteMeniu = new JMenu("Inchirieri");
        JMenuItem venitTotalItem = new JMenuItem("Raport Venit");
        venitTotalItem.addActionListener(e -> afiseazaVenitTotal());
        rapoarteMeniu.add(venitTotalItem);

        JMenuItem afiseazaInchirieri = new JMenuItem("Afiseaza Inchirieri");
        afiseazaInchirieri.addActionListener(e -> afiseazaInchirieri());
        rapoarteMeniu.add(afiseazaInchirieri);

        JMenuItem afiseazaCumparaturi = new JMenuItem("Afiseaza Cumparaturi");
        afiseazaCumparaturi.addActionListener(e -> afiseazaCumparaturi());
        rapoarteMeniu.add(afiseazaCumparaturi);

        baraMeniu.add(rapoarteMeniu);

        // Cititori submeniu
        JMenu cititoriMeniu = new JMenu("Cititori");
        JMenuItem afiseazaReadersItem = new JMenuItem("Afiseaza Cititori");
        afiseazaReadersItem.addActionListener(e -> afiseazaReaders());
        cititoriMeniu.add(afiseazaReadersItem);
        baraMeniu.add(cititoriMeniu);

        // Angajati submeniu
        JMenu angajatiMeniu = new JMenu("Angajati");
        JMenuItem afiseazaEmployeesItem = new JMenuItem("Afiseaza Angajati");
        afiseazaEmployeesItem.addActionListener(e -> afiseazaEmployees());
        angajatiMeniu.add(afiseazaEmployeesItem);
        baraMeniu.add(angajatiMeniu);

        // Trolleybooks meniu
        JMenu trolleyMeniu = new JMenu("Trolleybooks");
        JMenuItem afiseazaTrolleysItem = new JMenuItem("Afiseaza Trolleybooks");
        afiseazaTrolleysItem.addActionListener(e -> afiseazaTrolleys());
        trolleyMeniu.add(afiseazaTrolleysItem);
        baraMeniu.add(trolleyMeniu);

        // Cataloguri submeniu
        JMenu cataloguriMeniu = new JMenu("Cataloguri");
        JMenuItem afiseazaCatalogsItem = new JMenuItem("Afiseaza Catalogurile");
        afiseazaCatalogsItem.addActionListener(e -> afiseazaCatalogs());
        cataloguriMeniu.add(afiseazaCatalogsItem);
        baraMeniu.add(cataloguriMeniu);

        fereastra.setJMenuBar(baraMeniu);

        // Zona loguri
        zonaLoguri = new JTextArea();
        zonaLoguri.setEditable(false);
        JScrollPane panelScroll = new JScrollPane(zonaLoguri);
        fereastra.add(panelScroll, BorderLayout.CENTER);

        // Bara de status
        JLabel baraStatus = new JLabel(" Terminat");
        baraStatus.setBorder(BorderFactory.createEtchedBorder());
        fereastra.add(baraStatus, BorderLayout.SOUTH);

        fereastra.setVisible(true);
    }

    private void afiseazaCartileDisponibile() {
        zonaLoguri.setText("");
        zonaLoguri.append("=== Carti Disponibile ===\n\n");
        for (Carte c : sistem.carti) {
            if (c.getNumarExemplare() > 0) {
                zonaLoguri.append(c.toString() + "\n\n");
            }
        }
    }

    private void inchiriazaCarte() {
        JDialog dialog = new JDialog(fereastra, "Inchiriaza Carte", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(5, 2, 5, 5));

        JLabel carteIdTitlu = new JLabel("Carte ID:");
        JTextField carteIdTextbox = new JTextField();
        JLabel cititorIdTitlu = new JLabel("Cititor ID:");
        JTextField cititorIdTextbox = new JTextField();
        JLabel dataInchirieriiTitlu = new JLabel("Data inchirierii (DD.MM.YYYY):");
        JTextField dataInchirieriiTextbox = new JTextField(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        JLabel dataReturnariiTitlu = new JLabel("Data returnarii (DD.MM.YYYY):");
        JTextField dataReturnariiTextbox = new JTextField(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));

        JButton okButon = new JButton("OK");
        okButon.addActionListener(e -> {
            try {
                String carteIdText = carteIdTextbox.getText();
                String cititorIdText = cititorIdTextbox.getText();
                String dataInchirierii = dataInchirieriiTextbox.getText();
                String dataReturnarii = dataReturnariiTextbox.getText();

                // Validate inputs
                if (carteIdText.isEmpty() || cititorIdText.isEmpty() || dataInchirierii.isEmpty() || dataReturnarii.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Este obligator sa indepliniti toate randurile!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int carteId = -1;
                int cititorId = -1;

                try {
                    carteId = Integer.parseInt(carteIdText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "ID-ul cartii trebuie sa fie numar!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    cititorId = Integer.parseInt(cititorIdText);
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "ID-ul cititorului trebuie sa fie un numar!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }

                Carte carte = null;
                for (Carte c : sistem.carti) {
                    if (c.getId() == carteId){
                        carte = c;
                        break;
                    }
                }

                Cititor cititor = null;
                for (Cititor cit : sistem.cititori) {
                    if (cit.getId() == cititorId) {
                        cititor = cit;
                        break;
                    }
                }

                if (carte == null || cititor == null) {
                    JOptionPane.showMessageDialog(dialog, "ID carte sau cititor invalid!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (carte.getNumarExemplare() <= 0) {
                    JOptionPane.showMessageDialog(dialog, "Nu sunt copii disponibile ale acestei carti!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create inchiriere
                int inchiriereId = (sistem.inchirieri.size() + 1);
                Inchiriere inchiriere = new Inchiriere(inchiriereId, cititorId, carteId, dataInchirierii, dataReturnarii);
                sistem.inchirieri.add(inchiriere);
                carte.actualizeazaStare("inchiriata");
                cititor.adaugaCarteImprumutata(carteId);

                zonaLoguri.setText("");
                zonaLoguri.append("Cartea inchiriata cu succes!\n");
                zonaLoguri.append("Inchiriere ID: " + inchiriereId + "\n");
                zonaLoguri.append("Carte: " + carte.getTitlu() + "\n");
                zonaLoguri.append("Cititor: " + cititor.getNume() + " " + cititor.getPrenume() + "\n");
                zonaLoguri.append("Inchiriere pret: " + carte.calculeazaPretInchiriere() + " lei\n");

                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Eroare: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton anulareButon = new JButton("Anuleaza");
        anulareButon.addActionListener(e -> dialog.dispose());

        dialog.add(carteIdTitlu);
        dialog.add(carteIdTextbox);
        dialog.add(cititorIdTitlu);
        dialog.add(cititorIdTextbox);
        dialog.add(dataInchirieriiTitlu);
        dialog.add(dataInchirieriiTextbox);
        dialog.add(dataReturnariiTitlu);
        dialog.add(dataReturnariiTextbox);
        dialog.add(okButon);
        dialog.add(anulareButon);

        dialog.setVisible(true);
    }

    private void cumparaCarte() {
        JDialog dialog = new JDialog(fereastra, "Cumpara Carte", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(4, 2, 5, 5));

        JLabel carteIdTitlu = new JLabel("Carte ID:");
        JTextField carteIdTextbox = new JTextField();
        JLabel cititorIdTitlu = new JLabel("Citior ID:");
        JTextField cititorIdTextbox = new JTextField();
        JLabel dataCumparariiTitlu = new JLabel("Data cumparare (DD.MM.YYYY):");
        JTextField dataCumparariiTextbox = new JTextField(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));

        JButton okButon = new JButton("OK");
        okButon.addActionListener(e -> {
            try {
                String carteIdText = carteIdTextbox.getText();
                String cititorIdText = cititorIdTextbox.getText();
                String dataCumparariiText = dataCumparariiTextbox.getText();

                if (carteIdText.isEmpty() || cititorIdText.isEmpty() || dataCumparariiText.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Este obligator sa indepliniti toate randurile!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int carteId = -1;
                int cititorId = -1;

                try {
                    carteId = Integer.parseInt(carteIdText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "ID-ul cartii trebuie sa fie numar!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    cititorId = Integer.parseInt(cititorIdText);
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "ID-ul cititorului trebuie sa fie un numar!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }

                Carte carte = null;
                for (Carte c : sistem.carti) {
                    if (c.getId() == carteId) {
                        carte = c;
                        break;
                    }
                }

                Cititor cititor = null;
                for (Cititor cit : sistem.cititori) {
                    if (cit.getId() == cititorId) {
                        cititor = cit;
                        break;
                    }
                }

                if (carte == null || cititor == null) {
                    JOptionPane.showMessageDialog(dialog, "ID-ul cartii sau cititorului invalid!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (carte.getNumarExemplare() <= 0) {
                    JOptionPane.showMessageDialog(dialog, "Nu sunt copii disponibile ale acestei carti!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int cumparareId = sistem.cumparari.size() + 1;
                Cumparare cumparare = new Cumparare(cumparareId, cititorId, carteId, dataCumparariiText, carte.getPret());
                sistem.cumparari.add(cumparare);
                carte.setNumarExemplare(carte.getNumarExemplare() - 1);

                zonaLoguri.setText("");
                zonaLoguri.append("Cartea a fost cumparata cu succes!\n");
                zonaLoguri.append("Cumpararea ID: " + cumparareId + "\n");
                zonaLoguri.append("Cartea: " + carte.getTitlu() + "\n");
                zonaLoguri.append("Cititor: " + cititor.getNume() + " " + cititor.getPrenume() + "\n");
                zonaLoguri.append("Pret: " + carte.getPret() + " lei\n");

                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Eroare: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton anulareButon = new JButton("Anulare");
        anulareButon.addActionListener(e -> dialog.dispose());

        dialog.add(carteIdTitlu);
        dialog.add(carteIdTextbox);
        dialog.add(cititorIdTitlu);
        dialog.add(cititorIdTextbox);
        dialog.add(dataCumparariiTitlu);
        dialog.add(dataCumparariiTextbox);
        dialog.add(okButon);
        dialog.add(anulareButon);

        dialog.setVisible(true);
    }

    private void returneazaCarte() {
        JDialog dialog = new JDialog(fereastra, "Returneaza Carte", true);
        dialog.setSize(400, 200);
        dialog.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel inchiriereIdTitlu = new JLabel("Inchiriere ID:");
        JTextField inchiriereIdTextbox = new JTextField();

        JButton okButon = new JButton("OK");
        okButon.addActionListener(e -> {
            try {
                String inchiriereIdText = inchiriereIdTextbox.getText();

                if (inchiriereIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Este necesar ID-ul inchirierii!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int inchiriereId = -1;

                try {
                    inchiriereId = Integer.parseInt(inchiriereIdText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "ID-ul inchirierii trebuie sa fie un numar!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }

                boolean success = sistem.returneazaCarte(inchiriereId);

                if (success) {
                    zonaLoguri.setText("");
                    zonaLoguri.append("Cartea returnata cu success!\n");
                    zonaLoguri.append("Inchiriere ID: " + inchiriereId + "\n");
                } else {
                    JOptionPane.showMessageDialog(dialog, "Inchirierea nu a fost gasita sau cartea deja a fost returnata!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }

                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Eroare: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton anulareButon = new JButton("Anulare");
        anulareButon.addActionListener(e -> dialog.dispose());

        dialog.add(inchiriereIdTitlu);
        dialog.add(inchiriereIdTextbox);
        dialog.add(new JLabel()); // empty cell
        dialog.add(new JLabel()); // empty cell
        dialog.add(okButon);
        dialog.add(anulareButon);

        dialog.setVisible(true);
    }

    private void afiseazaVenitTotal() {
        zonaLoguri.setText("");
        zonaLoguri.append("=== Venit Total ===\n\n");

        double venitInchirieri = 0;
        for (Inchiriere i : sistem.inchirieri) {
            for (Carte c : sistem.carti) {
                if (c.getId() == i.getIdCarte()) {
                    venitInchirieri += c.calculeazaPretInchiriere();
                    break;
                }
            }
        }

        double venitVanzari = 0;
        for (Cumparare c : sistem.cumparari) {
            venitVanzari += c.getPretPlatit();
        }

        double venitTotal = venitInchirieri + venitVanzari;

        zonaLoguri.append("Venit Total: " + venitTotal + " lei\n");
        zonaLoguri.append("Venit inchirieri: " + venitInchirieri + " lei\n");
        zonaLoguri.append("Venit vanzari: " + venitVanzari + " lei\n");
    }

    private void afiseazaInchirieri() {
        zonaLoguri.setText("");
        zonaLoguri.append("=== Toate Inchirierile ===\n\n");
        for (Inchiriere i : sistem.inchirieri) {
            zonaLoguri.append(i.toString() + "\n\n");
        }
    }

    private void afiseazaCumparaturi() {
        zonaLoguri.setText("");
        zonaLoguri.append("=== Toate Cumpararile ===\n\n");
        for (Cumparare c : sistem.cumparari) {
            zonaLoguri.append(c.toString() + "\n\n");
        }
    }

    private void afiseazaReaders() {
        zonaLoguri.setText("");
        zonaLoguri.append("=== Toti cititorii ===\n\n");
        for (Cititor c : sistem.cititori) {
            zonaLoguri.append(c.toString() + "\n\n");
        }
    }

    private void afiseazaEmployees() {
        zonaLoguri.setText("");
        zonaLoguri.append("=== Toti angajatii ===\n\n");
        for (Angajat a : sistem.angajati) {
            zonaLoguri.append(a.toString() + "\n\n");
        }
    }

    private void afiseazaTrolleys() {
        zonaLoguri.setText("");
        zonaLoguri.append("=== Toate Trolleybooks ===\n\n");
        for (Trolleybooks t : sistem.trolleybooks) {
            zonaLoguri.append(t.toString() + "\n\n");
        }
    }

    private void afiseazaCatalogs() {
        zonaLoguri.setText("");
        zonaLoguri.append("=== Toate catalogurile ===\n\n");
        for (Catalog c : sistem.cataloguri) {
            zonaLoguri.append(c.toString() + "\n\n");
        }
    }

    private void afiseazaToateCartile() {
        zonaLoguri.setText("");
        zonaLoguri.append("=== Toate cartile ===\n\n");
        for (Carte c : sistem.carti) {
            zonaLoguri.append(c.toString() + "\n\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new TrolleybooksUI();
        });
    }
}
