package dto;

import java.util.Date;

public class DTONarudzbenica {
    private int idNarudzbenice, idZaposlenog;
    private Date datumNarudzbenice;
    private boolean isporucena, kalkulisana;
    private double ukupnaCijena;

    public DTONarudzbenica(int idNarudzbenice, Date datumNarudzbenice, boolean isporucena, int idZaposlenog,  double ukupnaCijena, boolean kalkulisana) {
        this.idNarudzbenice = idNarudzbenice;
        this.idZaposlenog = idZaposlenog;
        this.datumNarudzbenice = datumNarudzbenice;
        this.isporucena = isporucena;
        this.ukupnaCijena=ukupnaCijena;
        this.kalkulisana=kalkulisana;
    }

    public boolean isKalkulisana() {
        return kalkulisana;
    }

    public void setKalkulisana(boolean kalkulisana) {
        this.kalkulisana = kalkulisana;
    }

    public double getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setUkupnaCijena(double ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public void setIdNarudzbenice(int idNarudzbenice) {
        this.idNarudzbenice = idNarudzbenice;
    }

    public void setIdZaposlenog(int idZaposlenog) {
        this.idZaposlenog = idZaposlenog;
    }

    public void setDatumNarudzbenice(Date datumNarudzbenice) {
        this.datumNarudzbenice = datumNarudzbenice;
    }

    public void setIsporucena(boolean isporucena) {
        this.isporucena = isporucena;
    }

    public int getIdNarudzbenice() {
        return idNarudzbenice;
    }

    public int getIdZaposlenog() {
        return idZaposlenog;
    }

    public Date getDatumNarudzbenice() {
        return datumNarudzbenice;
    }

    public boolean isIsporucena() {
        return isporucena;
    }

    @Override
    public String toString() {
        return "DTONarudzbenica{" + "idNarudzbenice=" + idNarudzbenice 
                + ", idZaposlenog=" + idZaposlenog 
                + ", datumNarudzbenice=" + datumNarudzbenice 
                + ", isporucena=" + isporucena + '}';
    }
    
}
