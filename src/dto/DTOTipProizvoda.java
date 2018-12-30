package dto;

public class DTOTipProizvoda {
    private int idTipa, idGrupe;
    private String nazivTipaProizvoda;

    public DTOTipProizvoda(int idTipa, String nazivTipaProizvoda, int idGrupe) {
        this.idTipa = idTipa;
        this.nazivTipaProizvoda = nazivTipaProizvoda;
        this.idGrupe=idGrupe;
    }

    public int getIdTipa() {
        return idTipa;
    }

    public void setIdTipa(int idTipa) {
        this.idTipa = idTipa;
    }

    public int getIdGrupe() {
        return idGrupe;
    }

    public void setIdGrupe(int idGrupe) {
        this.idGrupe = idGrupe;
    }

    public String getNazivTipaProizvoda() {
        return nazivTipaProizvoda;
    }

    public void setNazivTipaProizvoda(String nazivTipaProizvoda) {
        this.nazivTipaProizvoda = nazivTipaProizvoda;
    }
    
    
    
}
