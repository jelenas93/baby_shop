package dto;

public class DTOTipProizvoda {
    private int idTipa;
    private String nazivTipaProizvoda;

    public DTOTipProizvoda(int idTipa, String nazivTipaProizvoda) {
        this.idTipa = idTipa;
        this.nazivTipaProizvoda = nazivTipaProizvoda;
    }

    public int getIdTipa() {
        return idTipa;
    }

    public void setIdTipa(int idTipa) {
        this.idTipa = idTipa;
    }

    public String getNazivTipaProizvoda() {
        return nazivTipaProizvoda;
    }

    public void setNazivTipaProizvoda(String nazivTipaProizvoda) {
        this.nazivTipaProizvoda = nazivTipaProizvoda;
    }
    
    
    
}
