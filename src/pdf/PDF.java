package pdf;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.text.*;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.pdf.BaseFont;
import dao.DAOProizvod;
import dto.DTORacun;
import dto.DTOStavka;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import tabele.TabelaKalkulacija;
import tabele.TabelaNarudzba;
import tabele.TabelaNarudzbenica;
import dao.DAODobavljac;
import dao.DAOMjesto;
import dao.DAORacun;
import dao.DAOSkladiste;
import dao.DAOStavka;
import dao.DAOZaposleni;
import dto.DTODobavljac;
import dto.DTODobavljacIzvjestaj;
import dto.DTOIzvjestajNaruceneRobeDobavljac;
import dto.DTOMjesto;
import dto.DTOProizvod;
import dto.DTOProizvodiUSkladistu;
import dto.DTOZaposleni;
import gui.PrijavaNaSistemController;
import java.awt.Desktop;
import java.io.File;
import java.text.Format;
import java.util.Date;
import java.util.HashMap;

public class PDF {

    public static final String FONT = "./src/FreeSans.ttf";

    public static String kreirajFajlRacun(ArrayList<DTOStavka> stavke, DTORacun racun) {

        String ime = "./racuni/racun" + racun.getIdRacuna() + ".pdf";
        try {
            String vrijeme = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
            String sat = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
            String min = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
            String sek = new SimpleDateFormat("ss").format(Calendar.getInstance().getTime());
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(ime));

            try (Document doc = new Document(pdfDoc, PageSize.A5)) {
                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, "CP1250", BaseFont.EMBEDDED);
                //Font moj = new Font(bf);
                PdfFont moj;
                moj = PdfFontFactory.createFont(FONT, "Cp1250", true);
                doc.add(new Paragraph("Baby shop").setFont(moj));
                doc.add(new Paragraph("Broj računa: " + racun.getIdRacuna()).setFont(moj));
                doc.add(new Paragraph("Datum: " + vrijeme + " " + sat + ":" + min + ":" + sek));
                doc.add(new Paragraph("\n"));
                doc.add(new Paragraph("Maloprodajni račun").setTextAlignment(TextAlignment.CENTER).setFont(moj));
                doc.add(new Paragraph("\n"));
                Border b1 = new SolidBorder(Color.WHITE, Float.MIN_VALUE);
                float[] pointColumnWidthss = {1000F, 1000F, 1000F, 1000F};
                Table tablee = new Table(pointColumnWidthss);
                Cell c11 = new Cell();
                c11.add("Proizvod");
                c11.setBorder(b1);
                c11.setTextAlignment(TextAlignment.LEFT);
                tablee.addCell(c11);
                Cell c22 = new Cell();
                c22.add("Količina").setFont(moj);
                c22.setBorder(b1);
                c22.setTextAlignment(TextAlignment.CENTER);
                tablee.addCell(c22);
                Cell c33 = new Cell();
                c33.add("Cijena");
                c33.setTextAlignment(TextAlignment.CENTER);
                c33.setBorder(b1);
                tablee.addCell(c33);
                Cell c44 = new Cell();
                c44.add("Ukupno");
                c44.setBorder(b1);
                c44.setTextAlignment(TextAlignment.RIGHT);
                tablee.addCell(c44);
                doc.add(tablee);
                doc.add(new Paragraph("---------------------------------------------------------------------------------------"));
                float[] pointColumnWidths = {1000F, 1000F, 1000F, 1000F};
                Table table = new Table(pointColumnWidths);
                for (int i = 0; i < stavke.size(); i++) {
                    Cell c1 = new Cell();
                    c1.add(new DAOProizvod().getProizvodPoId(stavke.get(i).getIdProizvoda()).getNaziv());
                    c1.setBorder(b1);
                    c1.setTextAlignment(TextAlignment.LEFT);
                    c1.setFont(moj);
                    table.addCell(c1);
                    Cell c2 = new Cell();
                    c2.add(String.format("%d", stavke.get(i).getKolicina()));
                    c2.setBorder(b1);
                    c2.setTextAlignment(TextAlignment.CENTER);
                    table.addCell(c2);
                    Cell c3 = new Cell();
                    c3.add(String.format("%.2f", stavke.get(i).getCijena()));
                    c3.setBorder(b1);
                    c3.setTextAlignment(TextAlignment.CENTER);
                    table.addCell(c3);
                    Cell c4 = new Cell();
                    c4.add(String.format("%.2f", stavke.get(i).getCijena() * stavke.get(i).getKolicina()));
                    c4.setBorder(b1);
                    c4.setTextAlignment(TextAlignment.RIGHT);
                    table.addCell(c4);
                    //String novi=String.format("%30s %25d %22.2f %20.2f",new DAOProizvod().getProizvodPoId(stavke.get(i).getIdProizvoda()).getNaziv().toLowerCase(),
                    //        stavke.get(i).getKolicina(),stavke.get(i).getCijena(),(stavke.get(i).getCijena()*stavke.get(i).getKolicina()));

                }
                doc.add(table);
                doc.add(new Paragraph("---------------------------------------------------------------------------------------"));
                double pdv = racun.getUkupnaCijena() * 17 / 100;
                float[] kolone = {1000F, 1000F};
                Table tabela = new Table(kolone);
                Cell ce11 = new Cell();
                ce11.add("Ukupno: ");
                ce11.setBorder(b1);
                tabela.addCell(ce11);
                Cell ce12 = new Cell();
                ce12.add(String.format("%.2f", (racun.getUkupnaCijena() - pdv)));
                ce12.setTextAlignment(TextAlignment.RIGHT);
                ce12.setBorder(b1);
                tabela.addCell(ce12);
                Cell ce21 = new Cell();
                ce21.add("PDV (17.00%) : ");
                ce21.setBorder(b1);
                tabela.addCell(ce21);
                Cell ce22 = new Cell();
                ce22.add(String.format("%.2f", pdv));
                ce22.setTextAlignment(TextAlignment.RIGHT);
                ce22.setBorder(b1);
                tabela.addCell(ce22);
                Cell ce31 = new Cell();
                ce31.add("Ukupno za plaćanje: ");
                ce31.setBorder(b1);
                ce31.setFont(moj);
                tabela.addCell(ce31);
                Cell ce32 = new Cell();
                ce32.add(String.format("%.2f", racun.getUkupnaCijena()));
                ce32.setTextAlignment(TextAlignment.RIGHT);
                ce32.setBorder(b1);
                tabela.addCell(ce32);
                doc.add(tabela);

            } catch (DocumentException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Desktop.isDesktopSupported()) {
                try {
                    File theUMFile = new File(ime);
                    Desktop.getDesktop().open(theUMFile);
                } catch (FileNotFoundException fnf) {
                    // okDialog(msg_fnf);
                    //theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                    //theConcours.GetLogger().info(msg_fnf);
                } catch (IllegalArgumentException fnf) {
                    // okDialog(msg_fnf);
                    // theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                    // theConcours.GetLogger().info(msg_fnf);
                } catch (IOException ex) {
                    //okDialog(msg_cno);
                    // theConcours.GetLogger().log(Level.SEVERE, null, ex);
                    //  theConcours.GetLogger().info(msg_cno);
                }
                return "./racuni/racun" + racun.getIdRacuna() + " " + sat + ":" + min + ":" + sek + ".pdf";
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);

        }

        return "";
    }

    public static String kreirajFajlNarudzbe(ObservableList<TabelaNarudzbenica> stavke, String nazivDobavljaca) {

        try {
            String vrijemeNarudzbe = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
            String satNarudzbe = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
            String minNarudzbe = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
            String sekNarudzbe = new SimpleDateFormat("ss").format(Calendar.getInstance().getTime());
            String ime = "./narudzbenice/narudzba" + vrijemeNarudzbe + " " + satNarudzbe + "h" + minNarudzbe + "min" + sekNarudzbe + "s" + nazivDobavljaca + ".pdf";
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(ime));

            try (Document doc = new Document(pdfDoc)) {
                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, "CP1250", BaseFont.EMBEDDED);
                //Font moj = new Font(bf);
                PdfFont moj;
                moj = PdfFontFactory.createFont(FONT, "Cp1250", true);
                doc.setFontSize(10);
                Border border1 = new SolidBorder(Color.WHITE, Float.MIN_VALUE);
                Paragraph prodavnica = new Paragraph("Poslovna jedinica BABY SHOP");
                prodavnica.setTextAlignment(TextAlignment.LEFT);
                Paragraph vrijeme = new Paragraph("" + vrijemeNarudzbe + " " + satNarudzbe + ":" + minNarudzbe + ":" + sekNarudzbe);
                vrijeme.setTextAlignment(TextAlignment.LEFT);

                float[] pointColumnWidths = {500F, 500F, 500F};
                Table tabela1 = new Table(pointColumnWidths);
                tabela1.setBorder(border1);
                Cell c1 = new Cell();
                c1.setBorder(border1);
                c1.add(prodavnica);
                Cell c2 = new Cell();
                c2.setBorder(border1);
                c2.add(new Paragraph(""));
                Cell c3 = new Cell();
                c3.setBorder(border1);
                c3.add(new Paragraph(""));

                Cell c4 = new Cell();
                c4.setBorder(border1);
                c4.add(vrijeme);

                Cell c5 = new Cell();
                c5.setBorder(border1);
                c5.add(new Paragraph("NARUDŽBA").setTextAlignment(TextAlignment.CENTER)).setFont(moj);
                c5.setFontSize(15);
                c5.setBold();

                Cell c6 = new Cell();
                c6.setBorder(border1);
                c6.add(new Paragraph(""));

                Cell c7 = new Cell();
                c7.setBorder(border1);
                DTOZaposleni k = DAOZaposleni.getZaposleniById(PrijavaNaSistemController.idZaposlenog);
                c7.add(new Paragraph("Poslovođa:" + k.getIme() + " " + k.getPrezime())).setFont(moj);
                c7.setTextAlignment(TextAlignment.LEFT);

                //  Cell c11 = new Cell();
                // c11.setBorder(border1);
                // c11.add(new Paragraph  (""+k.getIme()+" "+k.getPrezime()).setTextAlignment(TextAlignment.LEFT));
                Cell c8 = new Cell();
                c8.setBorder(border1);
                c8.add(new Paragraph(""));

                Cell c = new Cell();
                c.setBorder(border1);
                c.add(new Paragraph(""));

                //     Cell c9 = new Cell();
                //    c9.setBorder(border1);
                //    c9.add(new Paragraph("Vrijeme narudzbe:"));
                //      c9.setTextAlignment(TextAlignment.CENTER);
                //      Cell cc = new Cell();
                //     cc.setBorder(border1);
                //     cc.add(vrijeme);
                //       Cell ccc = new Cell();
                //      ccc.setBorder(border1);
                //      ccc.add(new Paragraph(""));
                Cell c10 = new Cell();
                c10.setBorder(border1);
                c10.add("Dobavljač: " + nazivDobavljaca).setFont(moj);
                c10.setTextAlignment(TextAlignment.LEFT);

                //  Cell c12 = new Cell();
                // c12.setBorder(border1);
                // c12.add(new Paragraph(nazivDobavljaca).setTextAlignment(TextAlignment.LEFT));
                tabela1.addCell(c1);
                tabela1.addCell(c2);
                tabela1.addCell(c3);
                tabela1.addCell(c4);
                tabela1.addCell(c5);
                tabela1.addCell(c6);
                tabela1.addCell(c7);
                tabela1.addCell(c8);
                tabela1.addCell(c);
                // tabela1.addCell(c9);
                // tabela1.addCell(cc);
                // tabela1.addCell(ccc);
                tabela1.addCell(c10);
                // tabela1.addCell(c12);

                doc.add(tabela1);

                // doc.add(new Paragraph("Datum: " + vrijemeNarudzbe + " " + satNarudzbe + ":" + minNarudzbe + ":" + sekNarudzbe));
                Table table = new Table(4);
                Border border = new SolidBorder(Color.WHITE, Float.MIN_VALUE);
                Border borderGray = new SolidBorder(Color.LIGHT_GRAY, Float.MIN_VALUE);
                Cell k1 = new Cell();
                k1.setBorderLeft(borderGray);
                k1.setBorderRight(borderGray);
                k1.setBackgroundColor(Color.LIGHT_GRAY);
                k1.add("Šifra").setFont(moj);
                table.addCell(k1);

                Cell k2 = new Cell();
                k2.setBorderLeft(borderGray);
                k2.setBorderRight(borderGray);
                k2.setBackgroundColor(Color.LIGHT_GRAY);
                k2.add("Barkod");
                table.addCell(k2);

                Cell k3 = new Cell();
                k3.setBorderLeft(borderGray);
                k3.setBorderRight(borderGray);
                k3.setBackgroundColor(Color.LIGHT_GRAY);
                k3.add("Naziv");
                table.addCell(k3);

                Cell k4 = new Cell();
                k4.setBorderLeft(borderGray);
                k4.setBorderRight(borderGray);
                k4.setBackgroundColor(Color.LIGHT_GRAY);
                k4.add("Naručeno").setFont(moj);
                table.addCell(k4);

                for (int i = 0; i < stavke.size(); i++) {

                    Cell p1 = new Cell();
                    p1.setBorderLeft(border);
                    p1.setBorderRight(border);
                    p1.add(stavke.get(i).getSifra());
                    table.addCell(p1);

                    Cell p2 = new Cell();
                    p2.setBorderLeft(border);
                    p2.setBorderRight(border);
                    p2.add(stavke.get(i).getBarKod());
                    table.addCell(p2);

                    Cell p3 = new Cell();
                    p3.setBorderLeft(border);
                    p3.setBorderRight(border);
                    p3.add(stavke.get(i).getNaziv()).setFont(moj);
                    table.addCell(p3);

                    Cell p4 = new Cell();
                    p4.setBorderLeft(border);
                    p4.setBorderRight(border);
                    p4.add(String.valueOf(stavke.get(i).getNaruceno()));
                    table.addCell(p4);

                }

                doc.add(table);

            } catch (DocumentException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Desktop.isDesktopSupported()) {
                try {
                    File theUMFile = new File(ime);
                    Desktop.getDesktop().open(theUMFile);
                } catch (FileNotFoundException fnf) {
                    // okDialog(msg_fnf);
                    //theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                    //theConcours.GetLogger().info(msg_fnf);
                } catch (IllegalArgumentException fnf) {
                    // okDialog(msg_fnf);
                    // theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                    // theConcours.GetLogger().info(msg_fnf);
                } catch (IOException ex) {
                    //okDialog(msg_cno);
                    // theConcours.GetLogger().log(Level.SEVERE, null, ex);
                    //  theConcours.GetLogger().info(msg_cno);
                }
                return "./narudzbenice/narudzba" + vrijemeNarudzbe + " " + satNarudzbe + "h" + minNarudzbe + "min" + sekNarudzbe + "s" + nazivDobavljaca + ".pdf";
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabelaNarudzba.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        return "";
    }

    public static void kreirajFajlKalkulacija(ObservableList<TabelaKalkulacija> stavke, String nazivDobavljaca, int id) {
        DAODobavljac daoDobavljac = new DAODobavljac();
        DTODobavljac dobavljac = daoDobavljac.getDobavljacPoNazivu(nazivDobavljaca);
        DTOMjesto mjesto = DAOMjesto.getMjestoByPostanskiBroj(dobavljac.getPostanskiBroj());
        String ime = "./kalkulacija/kalkulacija_" + id + ".pdf";
        try {
            String vrijemeKalkulacije = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
            String satKalkulacije = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
            String minKalkulacije = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(ime));

            try (Document doc = new Document(pdfDoc, PageSize.A4.rotate())) {
                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, "CP1250", BaseFont.EMBEDDED);
                //Font moj = new Font(bf);
                PdfFont moj;
                moj = PdfFontFactory.createFont(FONT, "Cp1250", true);
                doc.setFontSize(10);
                Border border = new SolidBorder(Color.WHITE, Float.MIN_VALUE);
                Paragraph prodavnica = new Paragraph("Poslovna jedinica BABY SHOP");
                prodavnica.setTextAlignment(TextAlignment.LEFT);
                Paragraph idKalkulacije = new Paragraph("ID: " + id);
                idKalkulacije.setTextAlignment(TextAlignment.CENTER);
                Paragraph vrijeme = new Paragraph("Vrijeme knjiženja: " + vrijemeKalkulacije + " " + satKalkulacije + ":" + minKalkulacije);
                vrijeme.setTextAlignment(TextAlignment.RIGHT);
                vrijeme.setFont(moj);
                float[] pointColumnWidths = {500F, 500F, 500F};
                Table tabela1 = new Table(pointColumnWidths);
                tabela1.setBorder(border);
                Cell c1 = new Cell();
                c1.setBorder(border);
                c1.add(prodavnica);

                Cell c2 = new Cell();
                c2.setBorder(border);
                c2.add(new Paragraph(""));
                Cell c3 = new Cell();
                c3.setBorder(border);
                c3.add(idKalkulacije);

                Cell c4 = new Cell();
                c4.setBorder(border);
                c4.add(new Paragraph(""));
                Cell c5 = new Cell();
                c5.setBorder(border);
                c5.add(new Paragraph("MALOPRODAJNA KALKULACIJA").setTextAlignment(TextAlignment.CENTER));
                c5.setFontSize(15);
                c5.setBold();
                Cell c6 = new Cell();
                c6.setBorder(border);
                DTOZaposleni k = DAOZaposleni.getZaposleniById(PrijavaNaSistemController.idZaposlenog);
                c6.add(new Paragraph("Kalkulant: " + k.getIme() + " " + k.getPrezime()).setTextAlignment(TextAlignment.RIGHT).setFont(moj));

                Cell c7 = new Cell();
                c7.setBorder(border);
                c7.add(new Paragraph("Datum kalkulacije:" + vrijemeKalkulacije));
                Cell c8 = new Cell();
                c8.setBorder(border);
                c8.add(new Paragraph(""));
                Cell c9 = new Cell();
                c9.setBorder(border);
                c9.add(vrijeme);

                Cell c10 = new Cell();
                c10.setBorder(border);
                c10.add("Dobavljač: " + nazivDobavljaca).setFont(moj);
                Cell c11 = new Cell();
                c11.setBorder(border);
                c11.add("Mjesto: " + mjesto.getNaziv()).setFont(moj);
                Cell c12 = new Cell();
                c12.setBorder(border);
                c12.add("");

                tabela1.addCell(c1);
                tabela1.addCell(c2);
                tabela1.addCell(c3);
                tabela1.addCell(c4);
                tabela1.addCell(c5);
                tabela1.addCell(c6);
                tabela1.addCell(c7);
                tabela1.addCell(c8);
                tabela1.addCell(c9);
                tabela1.addCell(c10);
                tabela1.addCell(c11);
                tabela1.addCell(c12);

                doc.add(tabela1);

                //  doc.add(prodavnica);
                //     doc.add(vrijeme);
                // doc.add(new Paragraph("Vrijeme knjiženja: " + vrijemeKalkulacije + " " + satKalkulacije + ":" + minKalkulacije));
                doc.add(new Paragraph(""));
                Table table = new Table(17);
                /* table.setBorderLeft(border);
                table.setBorderRight(border);
                table.setFont("HELVETICA");*/
                Border borderGray = new SolidBorder(Color.LIGHT_GRAY, Float.MIN_VALUE);
                Cell k1 = new Cell();
                k1.setBorderLeft(borderGray);
                k1.setBorderRight(borderGray);
                k1.setBackgroundColor(Color.LIGHT_GRAY);
                k1.add("Rb.");
                table.addCell(k1);

                Cell k2 = new Cell();
                k2.setBorderLeft(borderGray);
                k2.setBorderRight(borderGray);
                k2.setBackgroundColor(Color.LIGHT_GRAY);
                k2.add("Šifra");
                k2.setFont(moj);
                table.addCell(k2);
                //table.addCell("Barkod");

                Cell k3 = new Cell();
                k3.setBorderLeft(borderGray);
                k3.setBorderRight(borderGray);
                k3.setBackgroundColor(Color.LIGHT_GRAY);
                k3.add("Naziv robe");
                table.addCell(k3);

                Cell k4 = new Cell();
                k4.setBorderLeft(borderGray);
                k4.setBorderRight(borderGray);
                k4.setBackgroundColor(Color.LIGHT_GRAY);
                k4.add("Količina").setFont(moj);
                table.addCell(k4);

                Cell k5 = new Cell();
                k5.setBorderLeft(borderGray);
                k5.setBorderRight(borderGray);
                k5.setBackgroundColor(Color.LIGHT_GRAY);
                k5.setTextAlignment(TextAlignment.CENTER);
                k5.add("JM");
                table.addCell(k5);

                Cell k6 = new Cell();
                k6.setBorderLeft(borderGray);
                k6.setBorderRight(borderGray);
                k6.setBackgroundColor(Color.LIGHT_GRAY);
                k6.add("Fakt. cijena");
                table.addCell(k6);

                Cell k7 = new Cell();
                k7.setBorderLeft(borderGray);
                k7.setBorderRight(borderGray);
                k7.setBackgroundColor(Color.LIGHT_GRAY);
                k7.add("Rabat %");
                table.addCell(k7);

                Cell k8 = new Cell();
                k8.setBorderLeft(borderGray);
                k8.setBorderRight(borderGray);
                k8.setBackgroundColor(Color.LIGHT_GRAY);
                k8.add("Fakt. vr. na rabat");
                table.addCell(k8);

                Cell k9 = new Cell();
                k9.setBorderLeft(borderGray);
                k9.setBorderRight(borderGray);
                k9.setBackgroundColor(Color.LIGHT_GRAY);
                k9.add("Nab. cijena");
                table.addCell(k9);

                Cell k10 = new Cell();
                k10.setBorderLeft(borderGray);
                k10.setBorderRight(borderGray);
                k10.setBackgroundColor(Color.LIGHT_GRAY);
                k10.add("Nab. vrijed.");
                table.addCell(k10);

                Cell k11 = new Cell();
                k11.setBorderLeft(borderGray);
                k11.setBorderRight(borderGray);
                k11.setBackgroundColor(Color.LIGHT_GRAY);
                k11.add("Marža %").setFont(moj);
                table.addCell(k11);

                Cell k12 = new Cell();
                k12.setBorderLeft(borderGray);
                k12.setBorderRight(borderGray);
                k12.setBackgroundColor(Color.LIGHT_GRAY);
                k12.add("Marža iznos").setFont(moj);
                table.addCell(k12);

                Cell k13 = new Cell();
                k13.setBorderLeft(borderGray);
                k13.setBorderRight(borderGray);
                k13.setBackgroundColor(Color.LIGHT_GRAY);
                k13.add("Vr. bez PDV-a");
                table.addCell(k13);

                Cell k14 = new Cell();
                k14.setBorderLeft(borderGray);
                k14.setBorderRight(borderGray);
                k14.setBackgroundColor(Color.LIGHT_GRAY);
                k14.setTextAlignment(TextAlignment.CENTER);
                k14.add("Stopa");
                table.addCell(k14);

                Cell k15 = new Cell();
                k15.setBorderLeft(borderGray);
                k15.setBorderRight(borderGray);
                k15.setBackgroundColor(Color.LIGHT_GRAY);
                k15.setTextAlignment(TextAlignment.CENTER);
                k15.add("PDV");
                table.addCell(k15);

                Cell k16 = new Cell();
                k16.setBorderLeft(borderGray);
                k16.setBorderRight(borderGray);
                k16.setBackgroundColor(Color.LIGHT_GRAY);
                k16.add("Prod. vrijed.");
                table.addCell(k16);

                Cell k17 = new Cell();
                k17.setBorderLeft(borderGray);
                k17.setBorderRight(borderGray);
                k17.setBackgroundColor(Color.LIGHT_GRAY);
                k17.add("Prod. cijena");
                table.addCell(k17);

                for (int i = 0; i < stavke.size(); i++) {
                    int j = i + 1;
                    Cell p1 = new Cell();
                    p1.setBorderLeft(border);
                    p1.setBorderRight(border);
                    p1.add("" + j);
                    table.addCell(p1);

                    Cell p2 = new Cell();
                    p2.setBorderLeft(border);
                    p2.setBorderRight(border);
                    p2.add(stavke.get(i).getSifra());
                    table.addCell(p2);
                    //  table.addCell(stavke.get(i).getBarKod());

                    Cell p3 = new Cell();
                    p3.setBorderLeft(border);
                    p3.setBorderRight(border);
                    p3.add(stavke.get(i).getNaziv()).setFont(moj);
                    table.addCell(p3);

                    Cell p4 = new Cell();
                    p4.setBorderLeft(border);
                    p4.setBorderRight(border);
                    p4.setTextAlignment(TextAlignment.CENTER);
                    p4.add(stavke.get(i).getKolicina() + "");
                    table.addCell(p4);

                    Cell p5 = new Cell();
                    p5.setBorderLeft(border);
                    p5.setBorderRight(border);
                    p5.setTextAlignment(TextAlignment.CENTER);
                    p5.add(stavke.get(i).getJedMjere());
                    table.addCell(p5);

                    Cell p6 = new Cell();
                    p6.setBorderLeft(border);
                    p6.setBorderRight(border);
                    p6.add(String.format("%.2f", stavke.get(i).getFakturnaCijena()));
                    table.addCell(p6);

                    Cell p7 = new Cell();
                    p7.setBorderLeft(border);
                    p7.setBorderRight(border);
                    p7.add(String.format("%.2f", stavke.get(i).getRabat()));
                    table.addCell(p7);

                    Cell p8 = new Cell();
                    p8.setBorderLeft(border);
                    p8.setBorderRight(border);
                    p8.add(String.format("%.2f", stavke.get(i).getFakturnaVrijednostNaRabat()));
                    table.addCell(p8);

                    Cell p9 = new Cell();
                    p9.setBorderLeft(border);
                    p9.setBorderRight(border);
                    p9.add(String.format("%.2f", stavke.get(i).getNabavnaCijena()));
                    table.addCell(p9);

                    Cell p10 = new Cell();
                    p10.setBorderLeft(border);
                    p10.setBorderRight(border);
                    p10.add(String.format("%.2f", stavke.get(i).getNabavnaVrijednost()));
                    table.addCell(p10);

                    Cell p11 = new Cell();
                    p11.setBorderLeft(border);
                    p11.setBorderRight(border);
                    p11.add(String.format("%.2f", stavke.get(i).getMarza()));
                    table.addCell(p11);

                    Cell p12 = new Cell();
                    p12.setBorderLeft(border);
                    p12.setBorderRight(border);
                    p12.add(String.format("%.2f", stavke.get(i).getMarzaIznos()));
                    table.addCell(p12);

                    Cell p13 = new Cell();
                    p13.setBorderLeft(border);
                    p13.setBorderRight(border);
                    p13.add(String.format("%.2f", stavke.get(i).getVrijednostBezPdv()));
                    table.addCell(p13);

                    Cell p14 = new Cell();
                    p14.setBorderLeft(border);
                    p14.setBorderRight(border);
                    p14.setTextAlignment(TextAlignment.CENTER);
                    p14.add(stavke.get(i).getStopa() + ".00 %");
                    table.addCell(p14);

                    Cell p15 = new Cell();
                    p15.setBorderLeft(border);
                    p15.setBorderRight(border);
                    p15.add(String.format("%.2f", stavke.get(i).getPdv()));
                    table.addCell(p15);

                    Cell p16 = new Cell();
                    p16.setBorderLeft(border);
                    p16.setBorderRight(border);
                    p16.add(String.format("%.2f", stavke.get(i).getProdajnaVrijednost()));
                    table.addCell(p16);

                    Cell p17 = new Cell();
                    p17.setBorderLeft(border);
                    p17.setBorderRight(border);
                    p17.add(String.format("%.2f", stavke.get(i).getProdajnaCijena()));
                    table.addCell(p17);
                }

                doc.add(table);

            } catch (DocumentException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabelaNarudzba.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        //  return "";
        if (Desktop.isDesktopSupported()) {
            try {
                File theUMFile = new File(ime);
                Desktop.getDesktop().open(theUMFile);
            } catch (FileNotFoundException fnf) {
                // okDialog(msg_fnf);
                //theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                //theConcours.GetLogger().info(msg_fnf);
            } catch (IllegalArgumentException fnf) {
                // okDialog(msg_fnf);
                // theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                // theConcours.GetLogger().info(msg_fnf);
            } catch (IOException ex) {
                //okDialog(msg_cno);
                // theConcours.GetLogger().log(Level.SEVERE, null, ex);
                //  theConcours.GetLogger().info(msg_cno);
            }
        }
    }

    public static void kreirajIzvjestajPoDobavljacuZaMjesec(Date datumOd, Date datumDo, int idDobavljaca) {

        Border border = new SolidBorder(Color.WHITE, Float.MIN_VALUE);
        Border borderGray = new SolidBorder(Color.LIGHT_GRAY, Float.MIN_VALUE);
        ArrayList<DTODobavljacIzvjestaj> proizvodi = new ArrayList<>();
        proizvodi = new DAODobavljac().proizvodiPoDobavljacuZaMjesec(datumOd, datumDo, idDobavljaca);
        SimpleDateFormat datum = new SimpleDateFormat("dd.MM.yyyy");
        String ime = "./izvjestaji/poDobavljacu/izvjestaj_odDatuma_" + datum.format(datumOd) + "_doDatuma_" + datum.format(datumDo) + ".pdf";
        try {
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(ime));

            try (Document doc = new Document(pdfDoc, PageSize.A4.rotate())) {
                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, "CP1250", BaseFont.EMBEDDED);
                //Font moj = new Font(bf);
                PdfFont moj;
                moj = PdfFontFactory.createFont(FONT, "Cp1250", true);
                doc.setFontSize(10);
                Paragraph prodavnica = new Paragraph("Poslovna jedinica BABY SHOP");
                prodavnica.setTextAlignment(TextAlignment.LEFT);
                prodavnica.setFontSize(12);
                doc.add(prodavnica);
                Paragraph prazanRed = new Paragraph("\n");
                doc.add(prazanRed);
                Paragraph izvjestaj = new Paragraph("Izvještaj o naručenim proizvodima po dobavljaču za razdoblje od " + datum.format(datumOd) + " do " + datum.format(datumDo)).setFont(moj);
                izvjestaj.setTextAlignment(TextAlignment.CENTER);
                izvjestaj.setBold();
                izvjestaj.setFontSize(20);
                doc.add(izvjestaj);

                doc.add(prazanRed);
                Paragraph dobavljac = new Paragraph("JIB Dobavljača: " + new DAODobavljac().getJIBPoId(idDobavljaca)).setFont(moj);
                dobavljac.setFontSize(12);
                doc.add(dobavljac);
                Paragraph nazivDobavljaca = new Paragraph("Naziv dobavljača: " + new DAODobavljac().getNazivPoId(idDobavljaca)).setFont(moj);
                nazivDobavljaca.setFontSize(12);
                doc.add(nazivDobavljaca);
                doc.add(prazanRed);
                Table table = new Table(9);

                Cell c1 = new Cell();
                c1.setBorderRight(borderGray);
                c1.setBorderLeft(borderGray);
                c1.setBackgroundColor(Color.LIGHT_GRAY);
                c1.add("Rb. ");
                //table.addCell("Rb. ").setBorderLeft(border).setBorderLeft(border);
                table.addCell(c1);
                //table.addCell("JIB Dobavljaca");
                //table.addCell("Naziv Dobavljaca");
                Cell c2 = new Cell();
                c2.setBorderLeft(borderGray);
                c2.setBorderRight(borderGray);
                c2.setBackgroundColor(Color.LIGHT_GRAY);
                c2.add("Šifra proizvoda").setFont(moj);
                table.addCell(c2);

                Cell c3 = new Cell();
                c3.setBorderLeft(borderGray);
                c3.setBorderRight(borderGray);
                c3.setBackgroundColor(Color.LIGHT_GRAY);
                c3.add("Barkod proizvoda");
                table.addCell(c3);

                Cell c4 = new Cell();
                c4.setBorderLeft(borderGray);
                c4.setBorderRight(borderGray);
                c4.setBackgroundColor(Color.LIGHT_GRAY);
                c4.add("Naziv proizvoda");
                table.addCell(c4);

                Cell c5 = new Cell();
                c5.setBorderLeft(borderGray);
                c5.setBorderRight(borderGray);
                c5.setBackgroundColor(Color.LIGHT_GRAY);
                c5.add("Količina proizvoda").setFont(moj);
                table.addCell(c5);

                Cell c7 = new Cell();
                c7.setBorderLeft(borderGray);
                c7.setBorderRight(borderGray);
                c7.setBackgroundColor(Color.LIGHT_GRAY);
                c7.setTextAlignment(TextAlignment.CENTER);
                c7.add("JM");
                table.addCell(c7);

                Cell c8 = new Cell();
                c8.setBorderLeft(borderGray);
                c8.setBorderRight(borderGray);
                c8.setBackgroundColor(Color.LIGHT_GRAY);
                c8.setTextAlignment(TextAlignment.RIGHT);
                c8.add("Fakturna cijena");
                table.addCell(c8);

                Cell c9 = new Cell();
                c9.setBorderLeft(borderGray);
                c9.setBorderRight(borderGray);
                c9.setBackgroundColor(Color.LIGHT_GRAY);
                c9.setTextAlignment(TextAlignment.CENTER);
                c9.add("PDV");
                table.addCell(c9);

                Cell c6 = new Cell();
                c6.setBorderLeft(borderGray);
                c6.setBorderRight(borderGray);
                c6.setBackgroundColor(Color.LIGHT_GRAY);
                c6.setTextAlignment(TextAlignment.RIGHT);
                c6.add("Cijena proizvoda");
                table.addCell(c6);
                //doc.add(table);
                for (int i = 0; i < proizvodi.size(); i++) {
                    int j = i + 1;
                    Cell p1 = new Cell();
                    p1.setBorderLeft(border);
                    p1.setBorderRight(border);
                    p1.add(" " + j + ".");
                    table.addCell(p1);
                    //table.addCell(proizvodi.get(i).getJIBDobavljaca());
                    //table.addCell(proizvodi.get(i).getNazivDobavaljaca());
                    Cell p2 = new Cell();
                    p2.setBorderLeft(border);
                    p2.setBorderRight(border);
                    p2.add(proizvodi.get(i).getSifraProizvoda());
                    table.addCell(p2);

                    Cell p3 = new Cell();
                    p3.setBorderLeft(border);
                    p3.setBorderRight(border);
                    p3.add(proizvodi.get(i).getBarkodProizvoda());
                    table.addCell(p3);

                    Cell p4 = new Cell();
                    p4.setBorderLeft(border);
                    p4.setBorderRight(border);
                    p4.add(proizvodi.get(i).getNazivProizvoda()).setFont(moj);
                    table.addCell(p4);

                    Cell p5 = new Cell();
                    p5.setBorderLeft(border);
                    p5.setBorderRight(border);
                    p5.setTextAlignment(TextAlignment.RIGHT);
                    p5.add(String.format("%d", proizvodi.get(i).getKolicinaProizvoda()));
                    table.addCell(p5);

                    Cell p7 = new Cell();
                    p7.setBorderLeft(border);
                    p7.setBorderRight(border);
                    p7.add(proizvodi.get(i).getJedinicaMjere());
                    p7.setTextAlignment(TextAlignment.CENTER);
                    table.addCell(p7);

                    Cell p8 = new Cell();
                    p8.setBorderLeft(border);
                    p8.setBorderRight(border);
                    p8.setTextAlignment(TextAlignment.RIGHT);
                    p8.add(String.format("%.2f", proizvodi.get(i).getFakturnaCijena()));
                    table.addCell(p8);

                    Cell p9 = new Cell();
                    p9.setBorderLeft(border);
                    p9.setBorderRight(border);
                    p9.setTextAlignment(TextAlignment.CENTER);
                    p9.add(String.format("%d", proizvodi.get(i).getPdv()) + ".00 %");
                    table.addCell(p9);

                    Cell p6 = new Cell();
                    p6.setBorderLeft(border);
                    p6.setBorderRight(border);
                    p6.setTextAlignment(TextAlignment.RIGHT);
                    p6.add(String.format("%.2f", proizvodi.get(i).getCijenaProizvoda()));
                    table.addCell(p6);
                }
                doc.add(table);
            } catch (DocumentException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabelaNarudzba.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        if (Desktop.isDesktopSupported()) {
            try {
                File theUMFile = new File(ime);
                Desktop.getDesktop().open(theUMFile);
            } catch (FileNotFoundException fnf) {
                // okDialog(msg_fnf);
                //theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                //theConcours.GetLogger().info(msg_fnf);
            } catch (IllegalArgumentException fnf) {
                // okDialog(msg_fnf);
                // theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                // theConcours.GetLogger().info(msg_fnf);
            } catch (IOException ex) {
                //okDialog(msg_cno);
                // theConcours.GetLogger().log(Level.SEVERE, null, ex);
                //  theConcours.GetLogger().info(msg_cno);
            }
        }
    }

    public static String kreirajIzvjestajSvihProdanihProizvoda(Date datumOd, Date datumDo) {

        ObservableList<DTORacun> racuniGodina = DAORacun.getRacunePoMjesecu(datumOd, datumDo);
        HashMap<DTOProizvod, Integer> listaMoja = new HashMap<DTOProizvod, Integer>();

        for (DTORacun racun : racuniGodina) {

            DAOStavka s = new DAOStavka();
            ArrayList<DTOStavka> stavke = s.stavkeNaRacunu(racun.getIdRacuna());
            DAOProizvod p = new DAOProizvod();
            for (DTOStavka st : stavke) {

                if (!listaMoja.containsKey(p.getProizvodPoId(st.getIdProizvoda()))) {

                    listaMoja.put(p.getProizvodPoId(st.getIdProizvoda()), st.getKolicina());

                } else {

                    listaMoja.computeIfPresent(p.getProizvodPoId(st.getIdProizvoda()), (k, v) -> (v + (st.getKolicina())));

                }

            }

        }

        String pattern = "dd.MM.yyyy";

        Format df = new SimpleDateFormat(pattern);

        String Dod = df.format(datumOd);
        String Ddo = df.format(datumDo);

        try {
            String vrijeme = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
            String sat = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
            String min = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
            String sek = new SimpleDateFormat("ss").format(Calendar.getInstance().getTime());
            String ime = "./izvjestaji/izvjestajProdaja" + vrijeme + " " + sat + "h" + min + "min" + sek + "s" + ".pdf";
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(ime));

            try (Document doc = new Document(pdfDoc)) {
                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, "CP1250", BaseFont.EMBEDDED);
                //Font moj = new Font(bf);
                PdfFont moj;
                moj = PdfFontFactory.createFont(FONT, "Cp1250", true);
                doc.setFontSize(10);
                Border border1 = new SolidBorder(Color.WHITE, Float.MIN_VALUE);
                Paragraph prodavnica = new Paragraph("Poslovna jedinica BABY SHOP");
                prodavnica.setTextAlignment(TextAlignment.LEFT);
                Paragraph vrijeme2 = new Paragraph("Vrijeme: " + vrijeme + " " + sat + ":" + min + ":" + sek);
                vrijeme2.setTextAlignment(TextAlignment.RIGHT);

                float[] pointColumnWidths = {500F, 500F, 500F};
                Table tabela1 = new Table(pointColumnWidths);
                tabela1.setBorder(border1);
                Cell c1 = new Cell();
                c1.setBorder(border1);
                c1.add(prodavnica);
                Cell c2 = new Cell();
                c2.setBorder(border1);
                c2.add(new Paragraph(""));

                Cell c3 = new Cell();
                c3.setBorder(border1);
                c3.add(new Paragraph(""));

                Cell c4 = new Cell();
                c4.setBorder(border1);
                c4.add(new Paragraph(""));
                Cell c5 = new Cell();
                c5.setBorder(border1);
                c5.add(new Paragraph("Izvještaj o prodaji proizvoda ").setTextAlignment(TextAlignment.CENTER)).setFont(moj);
                c5.setFontSize(15);
                c5.setBold();
                //   Cell c6 = new Cell();
                //  c6.setBorder(border1);
                // DTOZaposleni k = DAOZaposleni.getZaposleniById(PrijavaNaSistemController.idZaposlenog);
                //c6.add(new Paragraph("Poslovodja: "+k.getIme()+" "+k.getPrezime()).setTextAlignment(TextAlignment.LEFT));

                Cell c6 = new Cell();
                c6.setBorder(border1);
                c6.add(new Paragraph(""));

                Cell c7 = new Cell();
                c7.setBorder(border1);
                c7.add(new Paragraph("Period za koji važi: ")).setFont(moj);

                Cell c8 = new Cell();
                c8.setBorder(border1);
                c8.add(new Paragraph(Dod));

                Cell c9 = new Cell();
                c9.setBorder(border1);
                c9.add(Ddo);

                Cell c10 = new Cell();
                c10.setBorder(border1);
                c10.add("Vrijeme kreiranja izvještaja: " + vrijeme).setFont(moj);

                Cell c12 = new Cell();
                c12.setBorder(border1);
                c12.add("");

                tabela1.addCell(c1);
                tabela1.addCell(c2);
                tabela1.addCell(c3);
                tabela1.addCell(c4);
                tabela1.addCell(c5);
                tabela1.addCell(c6);
                tabela1.addCell(c7);
                tabela1.addCell(c8);
                tabela1.addCell(c9);
                tabela1.addCell(c10);
                tabela1.addCell(c12);

                doc.add(tabela1);

                // doc.add(new Paragraph("Datum: " + vrijemeNarudzbe + " " + satNarudzbe + ":" + minNarudzbe + ":" + sekNarudzbe));
                Table table = new Table(4);
                Border border = new SolidBorder(Color.WHITE, Float.MIN_VALUE);
                Border borderGray = new SolidBorder(Color.LIGHT_GRAY, Float.MIN_VALUE);
                Cell k1 = new Cell();
                k1.setBorderLeft(borderGray);
                k1.setBorderRight(borderGray);
                k1.setBackgroundColor(Color.LIGHT_GRAY);
                k1.add("Barkod");
                table.addCell(k1);

                Cell k2 = new Cell();
                k2.setBorderLeft(borderGray);
                k2.setBorderRight(borderGray);
                k2.setBackgroundColor(Color.LIGHT_GRAY);
                k2.add("Naziv");
                table.addCell(k2);

                Cell k3 = new Cell();
                k3.setBorderLeft(borderGray);
                k3.setBorderRight(borderGray);
                k3.setBackgroundColor(Color.LIGHT_GRAY);
                k3.add("Cijena");
                table.addCell(k3);

                Cell k4 = new Cell();
                k4.setBorderLeft(borderGray);
                k4.setBorderRight(borderGray);
                k4.setBackgroundColor(Color.LIGHT_GRAY);
                k4.add("Prodano");
                table.addCell(k4);

                for (DTOProizvod pr : listaMoja.keySet()) {

                    Cell p1 = new Cell();
                    p1.setBorderLeft(border);
                    p1.setBorderRight(border);
                    p1.add(pr.getBarkod());
                    table.addCell(p1);

                    Cell p2 = new Cell();
                    p2.setBorderLeft(border);
                    p2.setBorderRight(border);
                    p2.add(pr.getNaziv()).setFont(moj);
                    table.addCell(p2);

                    Cell p3 = new Cell();
                    p3.setBorderLeft(border);
                    p3.setBorderRight(border);
                    p3.add(pr.getCijena() + "");
                    table.addCell(p3);

                    Cell p4 = new Cell();
                    p4.setBorderLeft(border);
                    p4.setBorderRight(border);
                    p4.add(String.valueOf(listaMoja.get(pr)));
                    table.addCell(p4);

                }

                doc.add(table);

            } catch (DocumentException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (Desktop.isDesktopSupported()) {
                try {
                    File theUMFile = new File(ime);
                    Desktop.getDesktop().open(theUMFile);
                } catch (FileNotFoundException fnf) {
                    // okDialog(msg_fnf);
                    //theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                    //theConcours.GetLogger().info(msg_fnf);
                } catch (IllegalArgumentException fnf) {
                    // okDialog(msg_fnf);
                    // theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                    // theConcours.GetLogger().info(msg_fnf);
                } catch (IOException ex) {
                    //okDialog(msg_cno);
                    // theConcours.GetLogger().log(Level.SEVERE, null, ex);
                    //  theConcours.GetLogger().info(msg_cno);
                }
                return "./izvjestaji/izvjestajProdaja" + vrijeme + " " + sat + "h" + min + "min" + sek + "s" + ".pdf";
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabelaNarudzba.class
                    .getName()).log(Level.SEVERE, null, ex);

        }

        return "";
    }

    public static void kreirajIzvjestajPoDobavljacuZaProdaneProizvode(Date datumOd, Date datumDo, int idDobavljaca) {

        Border border = new SolidBorder(Color.WHITE, Float.MIN_VALUE);
        Border borderGray = new SolidBorder(Color.LIGHT_GRAY, Float.MIN_VALUE);
        ArrayList<DTOIzvjestajNaruceneRobeDobavljac> proizvodi = new ArrayList<>();
        proizvodi = new DAODobavljac().proizvodiPoDobavljacuProdano(datumOd, datumDo, idDobavljaca);
        SimpleDateFormat datum = new SimpleDateFormat("dd.MM.yyyy");
        String ime = "./izvjestaji/poDobavljacuNaruceno/izvjestaj_odDatuma_" + datum.format(datumOd) + "_doDatuma_" + datum.format(datumDo) + ".pdf";
        try {
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(ime));

            try (Document doc = new Document(pdfDoc, PageSize.A4.rotate())) {
                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, "CP1250", BaseFont.EMBEDDED);
                PdfFont moj;
                moj = PdfFontFactory.createFont(FONT, "Cp1250", true);
                doc.setFontSize(10);
                Paragraph prodavnica = new Paragraph("Poslovna jedinica BABY SHOP");
                prodavnica.setTextAlignment(TextAlignment.LEFT);
                prodavnica.setFontSize(12);
                doc.add(prodavnica);
                Paragraph prazanRed = new Paragraph("\n");
                doc.add(prazanRed);
                Paragraph izvjestaj = new Paragraph("Izvještaj o prodaji naručenih proizvoda po dobavljaču za razdoblje od " + datum.format(datumOd) + " do " + datum.format(datumDo)).setFont(moj);
                izvjestaj.setTextAlignment(TextAlignment.CENTER);
                izvjestaj.setBold();
                izvjestaj.setFontSize(20);
                doc.add(izvjestaj);

                doc.add(prazanRed);
                Paragraph dobavljac = new Paragraph("JIB Dobavljača: " + new DAODobavljac().getJIBPoId(idDobavljaca)).setFont(moj);
                dobavljac.setFontSize(12);
                doc.add(dobavljac);
                Paragraph nazivDobavljaca = new Paragraph("Naziv dobavljača: " + new DAODobavljac().getNazivPoId(idDobavljaca)).setFont(moj);
                nazivDobavljaca.setFontSize(12);
                doc.add(nazivDobavljaca);
                doc.add(prazanRed);
                Table table = new Table(6);

                Cell c1 = new Cell();
                c1.setBorderRight(borderGray);
                c1.setBorderLeft(borderGray);
                c1.setBackgroundColor(Color.LIGHT_GRAY);
                c1.add("Rb. ");
                table.addCell(c1);
                Cell c2 = new Cell();
                c2.setBorderLeft(borderGray);
                c2.setBorderRight(borderGray);
                c2.setBackgroundColor(Color.LIGHT_GRAY);
                c2.add("Šifra proizvoda").setFont(moj);
                table.addCell(c2);

                Cell c3 = new Cell();
                c3.setBorderLeft(borderGray);
                c3.setBorderRight(borderGray);
                c3.setBackgroundColor(Color.LIGHT_GRAY);
                c3.add("Barkod proizvoda");
                table.addCell(c3);

                Cell c4 = new Cell();
                c4.setBorderLeft(borderGray);
                c4.setBorderRight(borderGray);
                c4.setBackgroundColor(Color.LIGHT_GRAY);
                c4.add("Naziv proizvoda");
                table.addCell(c4);

                Cell c5 = new Cell();
                c5.setBorderLeft(borderGray);
                c5.setBorderRight(borderGray);
                c5.setBackgroundColor(Color.LIGHT_GRAY);
                c5.setTextAlignment(TextAlignment.CENTER);
                c5.add("Količina proizvoda").setFont(moj);
                table.addCell(c5);

                Cell c6 = new Cell();
                c6.setBorderLeft(borderGray);
                c6.setBorderRight(borderGray);
                c6.setBackgroundColor(Color.LIGHT_GRAY);
                c6.setTextAlignment(TextAlignment.RIGHT);
                c6.add("Cijena proizvoda");
                table.addCell(c6);
                for (int i = 0; i < proizvodi.size(); i++) {
                    int j = i + 1;
                    Cell p1 = new Cell();
                    p1.setBorderLeft(border);
                    p1.setBorderRight(border);
                    p1.add(" " + j + ".");
                    table.addCell(p1);
                    Cell p2 = new Cell();
                    p2.setBorderLeft(border);
                    p2.setBorderRight(border);
                    p2.add(proizvodi.get(i).getProizvodSifra());
                    table.addCell(p2);

                    Cell p3 = new Cell();
                    p3.setBorderLeft(border);
                    p3.setBorderRight(border);
                    p3.add(proizvodi.get(i).getProizvodBarkod());
                    table.addCell(p3);

                    Cell p4 = new Cell();
                    p4.setBorderLeft(border);
                    p4.setBorderRight(border);
                    p4.add(proizvodi.get(i).getProizvodNaziv()).setFont(moj);
                    table.addCell(p4);

                    Cell p5 = new Cell();
                    p5.setBorderLeft(border);
                    p5.setBorderRight(border);
                    p5.setTextAlignment(TextAlignment.RIGHT);
                    p5.add(String.format("%d", proizvodi.get(i).getKolicina())).setTextAlignment(TextAlignment.CENTER);
                    table.addCell(p5);

                    Cell p6 = new Cell();
                    p6.setBorderLeft(border);
                    p6.setBorderRight(border);
                    p6.setTextAlignment(TextAlignment.RIGHT);
                    p6.add(String.format("%.2f", proizvodi.get(i).getCijenaProizvoda()));
                    table.addCell(p6);
                }
                doc.add(table);
            } catch (DocumentException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabelaNarudzba.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        if (Desktop.isDesktopSupported()) {
            try {
                File theUMFile = new File(ime);
                Desktop.getDesktop().open(theUMFile);
            } catch (FileNotFoundException fnf) {
                // okDialog(msg_fnf);
                //theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                //theConcours.GetLogger().info(msg_fnf);
            } catch (IllegalArgumentException fnf) {
                // okDialog(msg_fnf);
                // theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                // theConcours.GetLogger().info(msg_fnf);
            } catch (IOException ex) {
                //okDialog(msg_cno);
                // theConcours.GetLogger().log(Level.SEVERE, null, ex);
                //  theConcours.GetLogger().info(msg_cno);
            }
        }
    }

    public static void kreirajIzvjestajZaSkladiste() {

        Border border = new SolidBorder(Color.WHITE, Float.MIN_VALUE);
        Border borderGray = new SolidBorder(Color.LIGHT_GRAY, Float.MIN_VALUE);
        ArrayList<DTOProizvodiUSkladistu> proizvodi = new ArrayList<>();
        proizvodi = new DAOSkladiste().pregledProizvodaUSkladistu();
        SimpleDateFormat datum = new SimpleDateFormat("dd.MM.yyyy");
        String ime = "./izvjestaji/skladiste/izvjestaj_zaDatum_" + datum.format(new Date().getTime()) + ".pdf";
        try {
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(ime));

            try (Document doc = new Document(pdfDoc, PageSize.A4.rotate())) {
                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, "CP1250", BaseFont.EMBEDDED);
                //Font moj = new Font(bf);
                PdfFont moj;
                moj = PdfFontFactory.createFont(FONT, "Cp1250", true);
                doc.setFontSize(10);
                Paragraph prodavnica = new Paragraph("Poslovna jedinica BABY SHOP");
                prodavnica.setTextAlignment(TextAlignment.LEFT);
                prodavnica.setFontSize(12);
                doc.add(prodavnica);
                Paragraph prazanRed = new Paragraph("\n");
                doc.add(prazanRed);
                Paragraph izvjestaj = new Paragraph("Stanje zaliha u skladištu za datum " + datum.format(new Date().getTime())).setFont(moj);
                izvjestaj.setTextAlignment(TextAlignment.CENTER);
                izvjestaj.setBold();
                izvjestaj.setFontSize(20);
                doc.add(izvjestaj);

                doc.add(prazanRed);

                Table table = new Table(6);

                Cell c1 = new Cell();
                c1.setBorderRight(borderGray);
                c1.setBorderLeft(borderGray);
                c1.setBackgroundColor(Color.LIGHT_GRAY);
                c1.add("Rb. ");
                //table.addCell("Rb. ").setBorderLeft(border).setBorderLeft(border);
                table.addCell(c1);
                //table.addCell("JIB Dobavljaca");
                //table.addCell("Naziv Dobavljaca");
                Cell c2 = new Cell();
                c2.setBorderLeft(borderGray);
                c2.setBorderRight(borderGray);
                c2.setBackgroundColor(Color.LIGHT_GRAY);
                c2.add("Šifra proizvoda").setFont(moj);
                table.addCell(c2);

                Cell c3 = new Cell();
                c3.setBorderLeft(borderGray);
                c3.setBorderRight(borderGray);
                c3.setBackgroundColor(Color.LIGHT_GRAY);
                c3.add("Barkod proizvoda");
                table.addCell(c3);

                Cell c4 = new Cell();
                c4.setBorderLeft(borderGray);
                c4.setBorderRight(borderGray);
                c4.setBackgroundColor(Color.LIGHT_GRAY);
                c4.add("Naziv proizvoda");
                table.addCell(c4);

                Cell c5 = new Cell();
                c5.setBorderLeft(borderGray);
                c5.setBorderRight(borderGray);
                c5.setBackgroundColor(Color.LIGHT_GRAY);
                c5.setTextAlignment(TextAlignment.CENTER);
                c5.add("Količina proizvoda").setFont(moj);
                table.addCell(c5);

                Cell c6 = new Cell();
                c6.setBorderLeft(borderGray);
                c6.setBorderRight(borderGray);
                c6.setBackgroundColor(Color.LIGHT_GRAY);
                c6.setTextAlignment(TextAlignment.RIGHT);
                c6.add("Cijena proizvoda");
                table.addCell(c6);
                //doc.add(table);
                for (int i = 0; i < proizvodi.size(); i++) {
                    int j = i + 1;
                    Cell p1 = new Cell();
                    p1.setBorderLeft(border);
                    p1.setBorderRight(border);
                    p1.add(" " + j + ".");
                    table.addCell(p1);
                    //table.addCell(proizvodi.get(i).getJIBDobavljaca());
                    //table.addCell(proizvodi.get(i).getNazivDobavaljaca());
                    Cell p2 = new Cell();
                    p2.setBorderLeft(border);
                    p2.setBorderRight(border);
                    p2.add(proizvodi.get(i).getSifra());
                    table.addCell(p2);

                    Cell p3 = new Cell();
                    p3.setBorderLeft(border);
                    p3.setBorderRight(border);
                    p3.add(proizvodi.get(i).getBarkod());
                    table.addCell(p3);

                    Cell p4 = new Cell();
                    p4.setBorderLeft(border);
                    p4.setBorderRight(border);
                    p4.add(proizvodi.get(i).getNaziv()).setFont(moj);
                    table.addCell(p4);

                    Cell p5 = new Cell();
                    p5.setBorderLeft(border);
                    p5.setBorderRight(border);
                    p5.setTextAlignment(TextAlignment.RIGHT);
                    p5.add(String.format("%d", proizvodi.get(i).getKolicina())).setTextAlignment(TextAlignment.CENTER);
                    table.addCell(p5);

                    Cell p6 = new Cell();
                    p6.setBorderLeft(border);
                    p6.setBorderRight(border);
                    p6.setTextAlignment(TextAlignment.RIGHT);
                    p6.add(String.format("%.2f", proizvodi.get(i).getCijena()));
                    table.addCell(p6);
                }
                doc.add(table);
            } catch (DocumentException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabelaNarudzba.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        if (Desktop.isDesktopSupported()) {
            try {
                File theUMFile = new File(ime);
                Desktop.getDesktop().open(theUMFile);
            } catch (FileNotFoundException fnf) {
                // okDialog(msg_fnf);
                //theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                //theConcours.GetLogger().info(msg_fnf);
            } catch (IllegalArgumentException fnf) {
                // okDialog(msg_fnf);
                // theConcours.GetLogger().log(Level.SEVERE, null, fnf);
                // theConcours.GetLogger().info(msg_fnf);
            } catch (IOException ex) {
                //okDialog(msg_cno);
                // theConcours.GetLogger().log(Level.SEVERE, null, ex);
                //  theConcours.GetLogger().info(msg_cno);
            }
        }
    }
}
