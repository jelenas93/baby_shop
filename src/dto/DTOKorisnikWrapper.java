/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import dao.DAOKorisnickiNalog;
import dao.DAOZaposleni;

/**
 *
 * @author Tijana Lakic
 */
public class DTOKorisnikWrapper {

    private DTOKorisnickiNalog korisnickiNalog;
    private DTOZaposleni zaposleni;
    
//    private String ime, prezime, JMBG, mejl;
//    private double iznosPlate;
//    private int idZaposlenog, postanskiBroj, idTipZaposlenog;
//    private String korisnickoIme, lozinka, tipKorisnika;
//    private int idNaloga;
//    private boolean aktivan;

    public DTOKorisnikWrapper() {

    }
//        public DTOKorisnikWrapper(String korisnickoIme,String ime,String prezime,String jmbg,String tipKorisnika,boolean aktivan) {
//        this.ime = ime;
//        this.prezime = prezime;
//        this.JMBG = jmbg;
//        this.korisnickoIme = korisnickoIme;
//        this.aktivan = aktivan;
//        this.tipKorisnika = tipKorisnika;
//    }

    public DTOKorisnikWrapper(DTOKorisnickiNalog korisnickiNalog, DTOZaposleni zaposleni) {
        this.korisnickiNalog = korisnickiNalog;
        this.zaposleni = zaposleni;
    }


    public String getIme() {
        return zaposleni.getIme();
    }

    public void setIme(String ime) {
        zaposleni.setIme(ime);
    }

    public String getPrezime() {
      return zaposleni.getPrezime();

    }

    public void setPrezime(String prezime) {
        zaposleni.setPrezime(prezime);
    }

    public String getJMBG() {
        return zaposleni.getJMBG();
    }

    public void setJMBG(String JMBG) {
        zaposleni.setJMBG(JMBG);
    }

    public String getKorisnickoIme() {
        return korisnickiNalog.getKorisnickoIme();
    }

    public void setKorisnickoIme(String korisnickoIme) {
       korisnickiNalog.setKorisnickoIme(korisnickoIme);   
    }

    public String getTipKorisnika() {
        return korisnickiNalog.getTipKorisnika();
    }

    public void setTipKorisnika(String tipKorisnika) {
    korisnickiNalog.setTipKorisnika(tipKorisnika);    }

    public boolean isAktivan() {
        return korisnickiNalog.isAktivan();
    }

    public void setAktivan(boolean aktivan) {
korisnickiNalog.setAktivan(aktivan);    }

    public int getIdZaposlenog() {
        return zaposleni.getIdZaposlenog();
    }

    public void setIdZaposlenog(int idZaposlenog) {
zaposleni.setIdTipZaposlenog(idZaposlenog);    }

    public int getIdNaloga() {
        return korisnickiNalog.getIdNaloga();
    }

    public void setIdNaloga(int idNaloga) {
korisnickiNalog.setIdNaloga(idNaloga);    }
    
/*public DTOKorisnickiNalog getKorisnickiNalog(){

    return DAOKorisnickiNalog.getNalogById(idNaloga);
}
public DTOZaposleni getZaposleni(){

    return DAOZaposleni.getZaposleniById(idZaposlenog);
}*/

}
