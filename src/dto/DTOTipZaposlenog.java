package dto;

public class DTOTipZaposlenog {
    private int idTipaZaposlenog;
    private String nazivTipa;

    public DTOTipZaposlenog(int idTipaZaposlenog, String nazivTipa) {
        this.idTipaZaposlenog = idTipaZaposlenog;
        this.nazivTipa = nazivTipa;
    }

    public int getIdTipaZaposlenog() {
        return idTipaZaposlenog;
    }

    public String getNazivTipa() {
        return nazivTipa;
    }

    public void setIdTipaZaposlenog(int idTipaZaposlenog) {
        this.idTipaZaposlenog = idTipaZaposlenog;
    }

    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }

    @Override
    public String toString() {
        return "DTOTipZaposlenog{" + "idTipaZaposlenog=" + idTipaZaposlenog + ", nazivTipa=" + nazivTipa + '}';
    }
    
    
    
}
