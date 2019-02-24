package pdf;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;


import com.itextpdf.kernel.font.PdfType1Font;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import static com.itextpdf.kernel.pdf.PdfName.FontFamily;
import com.itextpdf.text.*;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
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
import javax.lang.model.element.Element;
import tabele.TabelaKalkulacija;
import tabele.TabelaNarudzba;
import tabele.TabelaNarudzbenica;
import com.itextpdf.text.*;
import dao.DAODobavljac;
import dao.DAOMjesto;
import dto.DTODobavljac;
import dto.DTOMjesto;

public class PDF {

    public static String kreirajFajlRacun(ArrayList<DTOStavka> stavke, DTORacun racun) {

        try {
            String vrijeme = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
            String sat = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
            String min = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
            String sek = new SimpleDateFormat("ss").format(Calendar.getInstance().getTime());
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter("./racuni/racun" + racun.getIdRacuna() + ".pdf"));
            
            try (Document doc = new Document(pdfDoc,PageSize.A5)) {
                BaseFont bf=BaseFont.createFont(FontFactory.HELVETICA, "CP1250", BaseFont.EMBEDDED);
                Font moj=new Font(bf, 12, Font.BOLD);
                doc.add(new Paragraph("Baby shop"));
                doc.add(new Paragraph("Datum: " + vrijeme + " " + sat + ":" + min + ":" + sek));
                doc.add(new Paragraph("\n"));
                doc.add(new Paragraph("Maloprodajni račun").setTextAlignment(TextAlignment.CENTER));
                Border b1=new SolidBorder(Color.WHITE, Float.MIN_VALUE);
                float [] pointColumnWidthss = {1000F, 1000F, 1000F, 1000F};       
                Table tablee = new Table(pointColumnWidthss); 
                Cell c11 = new Cell();
                c11.add("Proizvod");
                c11.setBorder(b1);
                c11.setTextAlignment(TextAlignment.LEFT);
                tablee.addCell(c11);
                Cell c22 = new Cell();
                c22.add("Količina");
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
                float [] pointColumnWidths = {1000F, 1000F, 1000F, 1000F};       
                Table table = new Table(pointColumnWidths);
                for (int i = 0; i < stavke.size(); i++) {
                    Cell c1 = new Cell();
                    c1.add(new DAOProizvod().getProizvodPoId(stavke.get(i).getIdProizvoda()).getNaziv());
                    c1.setBorder(b1);
                    c1.setTextAlignment(TextAlignment.LEFT);
                    table.addCell(c1);
                    Cell c2=new Cell();
                    c2.add(String.valueOf(stavke.get(i).getKolicina()));
                    c2.setBorder(b1);
                    c2.setTextAlignment(TextAlignment.CENTER);
                    table.addCell(c2);
                    Cell c3=new Cell();
                    c3.add(String.valueOf(stavke.get(i).getCijena()));
                    c3.setBorder(b1);
                    c3.setTextAlignment(TextAlignment.CENTER);
                    table.addCell(c3);
                    Cell c4=new Cell();
                    c4.add(String.valueOf(stavke.get(i).getCijena()*stavke.get(i).getKolicina()));
                    c4.setBorder(b1);
                    c4.setTextAlignment(TextAlignment.RIGHT);
                    table.addCell(c4);
                    //String novi=String.format("%30s %25d %22.2f %20.2f",new DAOProizvod().getProizvodPoId(stavke.get(i).getIdProizvoda()).getNaziv().toLowerCase(),
                    //        stavke.get(i).getKolicina(),stavke.get(i).getCijena(),(stavke.get(i).getCijena()*stavke.get(i).getKolicina()));

                }
                doc.add(table);
                doc.add(new Paragraph("---------------------------------------------------------------------------------------"));
                double pdv=racun.getUkupnaCijena()*17/100;
                float[] kolone={1000F, 1000F};       
                Table tabela = new Table(kolone);
                Cell ce11=new Cell();
                ce11.add("Ukupno: ");
                ce11.setBorder(b1);
                tabela.addCell(ce11);
                Cell ce12=new Cell();
                ce12.add(String.format("%.2f",(racun.getUkupnaCijena()-pdv)));
                ce12.setTextAlignment(TextAlignment.RIGHT);
                ce12.setBorder(b1);
                tabela.addCell(ce12);
                Cell ce21=new Cell();
                ce21.add("PDV (17.00%) : ");
                ce21.setBorder(b1);
                tabela.addCell(ce21);
                Cell ce22=new Cell();
                ce22.add(String.format("%.2f",pdv));
                ce22.setTextAlignment(TextAlignment.RIGHT);
                ce22.setBorder(b1);
                tabela.addCell(ce22);
                Cell ce31=new Cell();
                ce31.add("Ukupno za plaćanje: ");
                ce31.setBorder(b1);
                tabela.addCell(ce31);
                Cell ce32=new Cell();
                ce32.add(String.format("%.2f",racun.getUkupnaCijena()));
                ce32.setTextAlignment(TextAlignment.RIGHT);
                ce32.setBorder(b1);
                tabela.addCell(ce32);
                doc.add(tabela);
                return "./racuni/racun" +racun.getIdRacuna() + " " + sat + ":" + min + ":" + sek + ".pdf";
            } catch (DocumentException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
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
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter("./narudzbenice/narudzba" + vrijemeNarudzbe + " " + satNarudzbe + "h" + minNarudzbe + "min" + sekNarudzbe + "s" + nazivDobavljaca + ".pdf"));

            try (Document doc = new Document(pdfDoc)) {

                doc.add(new Paragraph("Datum: " + vrijemeNarudzbe + " " + satNarudzbe + ":" + minNarudzbe + ":" + sekNarudzbe));

                Table table = new Table(4);

                table.addCell("Sifra");
                table.addCell("Barkod");
                table.addCell("Naziv");
                table.addCell("Naruceno");
                for (int i = 0; i < stavke.size(); i++) {

                    table.addCell(stavke.get(i).getSifra());
                    table.addCell(stavke.get(i).getBarKod());
                    table.addCell(stavke.get(i).getNaziv());
                    table.addCell(String.valueOf(stavke.get(i).getNaruceno()));

                }

                doc.add(table);
                return "./narudzbenice/narudzba" + vrijemeNarudzbe + " " + satNarudzbe + "h" + minNarudzbe + "min" + sekNarudzbe + "s" + nazivDobavljaca + ".pdf";
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabelaNarudzba.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        return "";
    }

    public static void kreirajFajlKalkulacija(ObservableList<TabelaKalkulacija> stavke, String nazivDobavljaca, int id) {

        System.out.println(nazivDobavljaca);
        DAODobavljac daoDobavljac=new DAODobavljac();
        DTODobavljac dobavljac=daoDobavljac.getDobavljacPoNazivu(nazivDobavljaca);
        DTOMjesto mjesto=DAOMjesto.getMjestoByPostanskiBroj(dobavljac.getPostanskiBroj());
        System.out.println(mjesto);
        try {
            String vrijemeKalkulacije = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
            String satKalkulacije = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
            String minKalkulacije = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter("./kalkulacija/kalkulacija_" + id + ".pdf"));
            
            try (Document doc = new Document(pdfDoc,PageSize.A4.rotate())) {
                doc.setFontSize(10);
                Border border = new SolidBorder(Color.WHITE, Float.MIN_VALUE);
                Paragraph prodavnica = new Paragraph("Poslovna jedinica BABY SHOP");
                prodavnica.setTextAlignment(TextAlignment.LEFT);
                Paragraph idKalkulacije = new Paragraph("ID: " + id);
                idKalkulacije.setTextAlignment(TextAlignment.CENTER);
                Paragraph vrijeme = new Paragraph("Vrijeme knjiženja: " + vrijemeKalkulacije + " " + satKalkulacije + ":" + minKalkulacije);
                vrijeme.setTextAlignment(TextAlignment.RIGHT);

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
                Cell c6 = new Cell();
                c6.setBorder(border);
                c6.add(new Paragraph("Kalkulant:").setTextAlignment(TextAlignment.RIGHT));
                
                Cell c7 = new Cell();
                c7.setBorder(border);
                c7.add(new Paragraph("Datum kalkulacije:" + vrijemeKalkulacije));
                Cell c8 = new Cell();
                c8.setBorder(border);
                c8.add(new Paragraph(""));
                Cell c9 = new Cell();
                c9.setBorder(border);
                c9.add(vrijeme);
                
                Cell c10=new Cell();
                c10.setBorder(border);
                c10.add("Dobavljač: "+nazivDobavljaca);
                Cell c11 = new Cell();
                c11.setBorder(border);
                c11.add("Mjesto: "+mjesto.getNaziv());
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
                table.addCell("Rb.");
                table.addCell("Sifra");
                //table.addCell("Barkod");
                table.addCell("Naziv robe");
                table.addCell("Kolicina");
                table.addCell("JM");
                table.addCell("Fakt. cijena");
                table.addCell("Rabat %");
                table.addCell("Fakt. vr. na rabat");
                table.addCell("Nab. cijena");
                table.addCell("Nab. vrijednost");
                table.addCell("Marza %");
                table.addCell("Marza iznos");
                table.addCell("Vr. bez PDV-a");
                table.addCell("stopa");
                table.addCell("PDV");
                table.addCell("Prod. vrijed.");
                table.addCell("Prod. cijena");
                for (int i = 0; i < stavke.size(); i++) {
                    int j=i+1;
                    
                    table.addCell(""+j);
                    table.addCell(stavke.get(i).getSifra());
                  //  table.addCell(stavke.get(i).getBarKod());
                    table.addCell(stavke.get(i).getNaziv());
                    table.addCell(stavke.get(i).getKolicina()+"");
                    table.addCell(stavke.get(i).getJedMjere());
                    table.addCell(String.format("%.2f", stavke.get(i).getFakturnaCijena()));
                    table.addCell(String.format("%.2f",stavke.get(i).getRabat()));
                    table.addCell(String.format("%.2f",stavke.get(i).getFakturnaVrijednostNaRabat()));
                    table.addCell(String.format("%.2f",stavke.get(i).getNabavnaCijena()));
                    table.addCell(String.format("%.2f",stavke.get(i).getNabavnaVrijednost()));
                    table.addCell(String.format("%.2f",stavke.get(i).getMarza()));
                    table.addCell(String.format("%.2f",stavke.get(i).getMarzaIznos()));
                    table.addCell(String.format("%.2f",stavke.get(i).getVrijednostBezPdv()));
                    table.addCell(stavke.get(i).getStopa()+"");
                    table.addCell(String.format("%.2f",stavke.get(i).getPdv()));
                    table.addCell(String.format("%.2f",stavke.get(i).getProdajnaVrijednost()));
                    table.addCell(String.format("%.2f",stavke.get(i).getProdajnaCijena()));
                }

                doc.add(table);
                
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabelaNarudzba.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        //  return "";
    }
    
    public static void kreirajIzvjestajPoDobavljacuZaMjesec(){
        
    }

}
