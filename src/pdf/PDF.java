
package pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import dto.DTORacun;
import dto.DTOStavka;
import javafx.collections.ObservableList;

public class PDF {
    
  /*   public static String kreirajFajlNarudzbe( ObservableList<DTOStavka>  stavke,DTORacun racun) {
        
        try { 
            // String vrijeme=new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime()); 
            // String satNarudzbe  = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
            // String minNarudzbe = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
           //  String sekNarudzbe = new SimpleDateFormat("ss").format(Calendar.getInstance().getTime());
      /*      PdfDocument pdfDoc = new PdfDocument(new PdfWriter("./racuni/racun"+racun.getIdRacuna()+".pdf"));
            
            try (Document doc = new Document(pdfDoc)) {
                 
                
                doc.add(new Paragraph("Datum: " + vrijemeNarudzbe+" "+satNarudzbe+":"+minNarudzbe+":"+sekNarudzbe));
                
                Table table = new Table(4);

                table.addCell("Sifra");
                table.addCell("Barkod");
                table.addCell("Naziv");
                table.addCell("Naruceno");
                for ( int i = 0 ; i < stavke.size() ; i++) {

                    table.addCell(stavke.get(i).getSifra());
                    table.addCell(stavke.get(i).getBarKod());
                    table.addCell(stavke.get(i).getNaziv()); 
                    table.addCell(String.valueOf(stavke.get(i).getNaruceno())); 
                    
                    
                    
                }

                doc.add(table);
                 return "./narudzbenice/narudzba"+vrijemeNarudzbe+" "+satNarudzbe+"h"+minNarudzbe+"min"+sekNarudzbe+"s"+nazivDobavljaca+".pdf";
            }
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabelaNarudzba.class
                    .getName()).log(Level.SEVERE, null, ex);
            
        }
        return "";
    }
*/
    
}
