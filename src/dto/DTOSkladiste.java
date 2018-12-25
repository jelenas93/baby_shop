package dto;

public class DTOSkladiste {
    
    private int idSkladista, stanje, postanskiBroj;
    private String adresa;

    public DTOSkladiste(int idSkladista, int stanje, int postanskiBroj, String adresa) {
        this.idSkladista = idSkladista;
        this.stanje = stanje;
        this.postanskiBroj = postanskiBroj;
        this.adresa = adresa;
    }

    public int getIdSkladista() {
        return idSkladista;
    }

    public int getStanje() {
        return stanje;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setIdSkladista(int idSkladista) {
        this.idSkladista = idSkladista;
    }

    public void setStanje(int stanje) {
        this.stanje = stanje;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    
    
}
