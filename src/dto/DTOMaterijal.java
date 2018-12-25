package dto;

public class DTOMaterijal {
    private String naziv;
    private int idMaterijala;

    public DTOMaterijal(String naziv, int idMaterijala) {
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
