package pdf;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import dao.DAOProizvod;
import dto.DTORacun;
import dto.DTOStavka;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

public class PDF {

    public static String kreirajFajlRacun(ArrayList<DTOStavka> stavke, DTORacun racun) {

        try {
            String vrijeme = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
            String sat = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
            String min = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
            String sek = new SimpleDateFormat("ss").format(Calendar.getInstance().getTime());
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter("./racuni/racun" + racun.getIdRacuna() + ".pdf"));

            try (Document doc = new Document(pdfDoc,PageSize.A5)) {

                doc.add(new Paragraph("Baby shop"));
                doc.add(new Paragraph("Datum: " + vrijeme + " " + sat + ":" + min + ":" + sek));
                doc.add(new Paragraph("\n"));
                doc.add(new Paragraph("                               Maloprodajni racun"));
                doc.add(new Paragraph("\n"));
                doc.add(new Paragraph("Proizvod              -      kolicina       -        cijena      -        ukupno      "));
                doc.add(new Paragraph("--------------------------------------------------------------------------------------"));
                float [] pointColumnWidths = {1000F, 1000F, 1000F, 1000F};       
                Table table = new Table(pointColumnWidths); 
                for (int i = 0; i < stavke.size(); i++) {
                    Cell c1 = new Cell();
                    c1.add(new DAOProizvod().getProizvodPoId(stavke.get(i).getIdProizvoda()).getNaziv());
                    Border b1=new SolidBorder(Color.WHITE, Float.MIN_VALUE);
                    c1.setBorder(b1);
                    table.addCell(c1);
                    Cell c2=new Cell();
                    c2.add(String.valueOf(stavke.get(i).getKolicina()));
                    c2.setBorder(b1);
                    table.addCell(c2);
                    Cell c3=new Cell();
                    c3.add(String.valueOf(stavke.get(i).getCijena()));
                    c3.setBorder(b1);
                    table.addCell(c3);
                    Cell c4=new Cell();
                    c4.add(String.valueOf(stavke.get(i).getCijena()*stavke.get(i).getKolicina()));
                    c4.setBorder(b1);
                    table.addCell(c4);
                    //String novi=String.format("%30s %25d %22.2f %20.2f",new DAOProizvod().getProizvodPoId(stavke.get(i).getIdProizvoda()).getNaziv().toLowerCase(),
                    //        stavke.get(i).getKolicina(),stavke.get(i).getCijena(),(stavke.get(i).getCijena()*stavke.get(i).getKolicina()));
                    
                }
                doc.add(table);
                String dodaj="";
                /*for (int i = 0; i < stavke.size(); i++) {
                    String novi=String.format("%30s %25d %22.2f %20.2f",new DAOProizvod().getProizvodPoId(stavke.get(i).getIdProizvoda()).getNaziv().toLowerCase(),
                            stavke.get(i).getKolicina(),stavke.get(i).getCijena(),(stavke.get(i).getCijena()*stavke.get(i).getKolicina()));
                    System.out.println(novi);
                    dodaj+=novi;
                }
                doc.add(new Paragraph(dodaj));*/
                doc.add(new Paragraph("-------------------------------------------------------------------------------------"));
                double pdv=racun.getUkupnaCijena()*17/100;
                doc.add(new Paragraph("Ukupno: "+(racun.getUkupnaCijena()-pdv)));
                doc.add(new Paragraph("PDV: "+pdv));
                doc.add(new Paragraph("Ukupno za plaćanje: "+racun.getUkupnaCijena()));
                return "./racuni/racun" +racun.getIdRacuna() + " " + sat + ":" + min + ":" + sek + ".pdf";
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);

        }
        return "";
    }

}
