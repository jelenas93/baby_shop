package dto;

public class DTOMaterijal {
    private String naziv;
    private int idMaterijala;

    public DTOMaterijal(int idMaterijala, String naziv) {
        this.naziv = naziv;
        this.idMaterijala = idMaterijala;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getIdMaterijala() {
        return idMaterijala;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setIdMaterijala(int idMaterijala) {
        this.idMaterijala = idMaterijala;
    }
    
    
}
