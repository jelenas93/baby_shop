package dto;

import java.util.Date;

public class DTODostava {  // ne znam da li nam ovo treba kao za aplikaciju
    private int idDostave, idDobavljaca, idNarudzbenice;
    private Date datumDostave;

    public DTODostava(int idDostave, int idDobavljaca, int idNarudzbenice, Date datumDostave) {
        this.idDostave = idDostave;
        this.idDobavljaca = idDobavljaca;
        this.idNarudzbenice = idNarudzbenice;
        this.datumDostave = datumDostave;
    }

    public void setIdDostave(int idDostave) {
        this.idDostave = idDostave;
    }

    public void setIdDobavljaca(int idDobavljaca) {
        this.idDobavljaca = idDobavljaca;
    }

    public void setIdNarudzbenice(int idNarudzbenice) {
        this.idNarudzbenice = idNarudzbenice;
    }

    public void setDatumDostave(Date datumDostave) {
        this.datumDostave = datumDostave;
    }

    public int getIdDostave() {
        return idDostave;
    }

    public int getIdDobavljaca() {
        return idDobavljaca;
    }

    public int getIdNarudzbenice() {
        return idNarudzbenice;
    }

    public Date getDatumDostave() {
        return datumDostave;
    }
    
    
}
