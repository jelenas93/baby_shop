package pdf;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import static com.itextpdf.kernel.pdf.PdfName.FontFamily;
import com.itextpdf.text.*;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.TextAlignment;
import dao.DAOProizvod;
import dto.DTORacun;
import dto.DTOStavka;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDF {

    public static String kreirajFajlRacun(ArrayList<DTOStavka> stavke, DTORacun racun) {

        try {
            String vrijeme = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
            String sat = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
            String min = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
            String sek = new SimpleDateFormat("ss").format(Calendar.getInstance().getTime());
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter("./racuni/racun" + racun.getIdRacuna() + ".pdf"));
            
            try (Document doc = new Document(pdfDoc,PageSize.A5)) {
                Font smallItalic;
                smallItalic = new Font(Font.FontFamily.HELVETICA, 12,Font.BOLD);
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
                c22.setFont("ARIAL");
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
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);

        }
        return "";
    }

}
