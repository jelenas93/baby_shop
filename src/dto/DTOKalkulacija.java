package dto;

import java.util.Date;

public class DTOKalkulacija {
    
    private int idKalkulacije, idDobavljaca, idZaposlenog;
    private Date datumKalkulacije; 
    private double ukupnaCIjena;

    public DTOKalkulacija(int idKalkulacije, Date datumKalkulacije, int idDobavljaca, int idZaposlenog, double ukupnaCijena) {
        this.idKalkulacije = idKalkulacije;
        this.idDobavljaca = idDobavljaca;
        this.idZaposlenog = idZaposlenog;
        this.datumKalkulacije = datumKalkulacije;
        this.ukupnaCIjena=ukupnaCijena;
    }

    public double getUkupnaCIjena() {
        return ukupnaCIjena;
    }

    public void setUkupnaCIjena(double ukupnaCIjena) {
        this.ukupnaCIjena = ukupnaCIjena;
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
