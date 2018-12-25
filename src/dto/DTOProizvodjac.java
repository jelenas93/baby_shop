package dto;

public class DTOProizvodjac {
    
    private String JIBProizvodjaca, naziv;
    private int postanskiBroj;

    public DTOProizvodjac(String JIBProizvodjaca, String naziv, int postanskiBroj) {
        this.JIBProizvodjaca = JIBProizvodjaca;
        this.naziv = naziv;
        this.postanskiBroj = postanskiBroj;
    }

    public void setJIBProizvodjaca(String JIBProizvodjaca) {
        this.JIBProizvodjaca = JIBProizvodjaca;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public String getJIBProizvodjaca() {
        return JIBProizvodjaca;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }
    
    
    
}
