package dto;

import java.util.Date;

public class DTONarudzbenica {
    private int idNarudzbenice, idZaposlenog;
    private Date darumNarudzbenice;
    private boolean isporucena;

    public DTONarudzbenica(int idNarudzbenice, int idZaposlenog, Date darumNarudzbenice, boolean isporucena) {
        this.idNarudzbenice = idNarudzbenice;
        this.idZaposlenog = idZaposlenog;
        this.darumNarudzbenice = darumNarudzbenice;
        this.isporucena = isporucena;
    }

    public void setIdNarudzbenice(int idNarudzbenice) {
        this.idNarudzbenice = idNarudzbenice;
    }

    public void setIdZaposlenog(int idZaposlenog) {
        this.idZaposlenog = idZaposlenog;
    }

    public void setDarumNarudzbenice(Date darumNarudzbenice) {
        this.darumNarudzbenice = darumNarudzbenice;
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

    public Date getDarumNarudzbenice() {
        return darumNarudzbenice;
    }

    public boolean isIsporucena() {
        return isporucena;
    }
    
}
