package dto;

import java.util.Date;

public class DTOKalkulacija {
    
    private int idKalkulacije, idDobavljaca, idZaposlenog;
    private Date datumKalkulacije;  //ne znam da li ce ostati date il simpledateformat vidjecemo

    public DTOKalkulacija(int idKalkulacije, int idDobavljaca, int idZaposlenog, Date datumKalkulacije) {
        this.idKalkulacije = idKalkulacije;
        this.idDobavljaca = idDobavljaca;
        this.idZaposlenog = idZaposlenog;
        this.datumKalkulacije = datumKalkulacije;
    }

    public void setIdKalkulacije(int idKalkulacije) {
        this.idKalkulacije = idKalkulacije;
    }

    public void setIdDobavljaca(int idDobavljaca) {
        this.idDobavljaca = idDobavljaca;
    }

    public void setIdZaposlenog(int idZaposlenog) {
        this.idZaposlenog = idZaposlenog;
    }

    public void setDatumKalkulacije(Date datumKalkulacije) {
        this.datumKalkulacije = datumKalkulacije;
    }

    public int getIdKalkulacije() {
        return idKalkulacije;
    }

    public int getIdDobavljaca() {
        return idDobavljaca;
    }

    public int getIdZaposlenog() {
        return idZaposlenog;
    }

    public Date getDatumKalkulacije() {
        return datumKalkulacije;
    }
    
    
    
    
}
