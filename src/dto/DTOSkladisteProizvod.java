/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

public class DTOSkladisteProizvod {
    private int idSkladista;
    private int idProizvoda;
    private int stanje;

    public DTOSkladisteProizvod() {}
    
    public DTOSkladisteProizvod(int idSkladista, int idProizvoda, int stanje) {
        this.idSkladista = idSkladista;
        this.idProizvoda = idProizvoda;
        this.stanje = stanje;
    }

    public int getIdSkladista() {
        return idSkladista;
    }

    public void setIdSkladista(int idSkladista) {
        this.idSkladista = idSkladista;
    }

    public int getIdProizvoda() {
        return idProizvoda;
    }

    public void setIdProizvoda(int idProizvoda) {
        this.idProizvoda = idProizvoda;
    }

    public int getStanje() {
        return stanje;
    }

    public void setStanje(int stanje) {
        this.stanje = stanje;
    }

    
    
    
    
    
}
