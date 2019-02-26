package tabele;

public class TabelaPazar {
    private String tipRacuna;
    private Double iznos;

    public TabelaPazar(String tipRacuna, Double iznos) {
        this.tipRacuna = tipRacuna;
        this.iznos = Double.parseDouble(String.format("%.2f", iznos));
        
    }

    public String getTipRacuna() {
        return tipRacuna;
    }

    public void setTipRacuna(String tipRacuna) {
        this.tipRacuna = tipRacuna;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = Double.parseDouble(String.format("%.2f", iznos));
    } 
}
