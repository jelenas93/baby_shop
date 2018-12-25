package dto;

import java.util.Date;

public class DTORacun {
    private int idRacuna, idZaposlenog;
    private Date datumRacuna;
    private double ukupnaCijena;

    public DTORacun(int idRacuna, int idZaposlenog, Date datumRacuna, double ukupnaCijena) {
        this.idRacuna = idRacuna;
        this.idZaposlenog = idZaposlenog;
        this.datumRacuna = datumRacuna;
        this.ukupnaCijena = ukupnaCijena;
    }

    public void setIdRacuna(int idRacuna) {
        this.idRacuna = idRacuna;
    }

    public void setIdZaposlenog(int idZaposlenog) {
        this.idZaposlenog = idZaposlenog;
    }

    public void setDatumRacuna(Date datumRacuna) {
        this.datumRacuna = datumRacuna;
    }

    public void setUkupnaCijena(double ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public int getIdRacuna() {
        return idRacuna;
    }

    public int getIdZaposlenog() {
        return idZaposlenog;
    }

    public Date getDatumRacuna() {
        return datumRacuna;
    }

    public double getUkupnaCijena() {
        return ukupnaCijena;
    }
    
    
}
