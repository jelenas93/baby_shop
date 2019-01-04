package dto;

public class DTOSkladiste {
    
    private int idSkladista, postanskiBroj;
    private String adresa;

    public DTOSkladiste(int idSkladista, int postanskiBroj, String adresa) {
        this.idSkladista = idSkladista;
        this.postanskiBroj = postanskiBroj;
        this.adresa = adresa;
    }

    public int getIdSkladista() {
        return idSkladista;
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

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    
    
}
