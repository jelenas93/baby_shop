package dto;

public class DTODobavljacProizvod {
    
    private int idDobavljaca, idProizvoda;

    public DTODobavljacProizvod(int idDobavljaca, int idProizvoda) {
        this.idDobavljaca = idDobavljaca;
        this.idProizvoda = idProizvoda;
    }

    public int getIdDobavljaca() {
        return idDobavljaca;
    }

    public void setIdDobavljaca(int idDobavljaca) {
        this.idDobavljaca = idDobavljaca;
    }

    public int getIdProizvoda() {
        return idProizvoda;
    }

    public void setIdProizvoda(int idProizvoda) {
        this.idProizvoda = idProizvoda;
    }
    
    
    
    
    
}
