package dto;

import java.util.Date;

public class DTOStorniranRacun {
     private int idRacuna, idZaposlenog, idStorniranogRacuna;
    private Date datumRacuna;
    private double ukupnaCijena;

    public DTOStorniranRacun(int idStorniranogRacuna, Date datumRacuna, double ukupnaCijena, int idZaposlenog,int idRacuna) {
        this.idRacuna = idRacuna;
        this.idZaposlenog = idZaposlenog;
        this.datumRacuna = datumRacuna;
        this.ukupnaCijena = ukupnaCijena;
        this.idStorniranogRacuna=idStorniranogRacuna;
    }

    public int getIdStorniranogRacuna() {
        return idStorniranogRacuna;
    }

    public void setIdStorniranogRacuna(int idStorniranogRacuna) {
        this.idStorniranogRacuna = idStorniranogRacuna;
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
