package dto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import static org.bouncycastle.cms.RecipientId.password;

public class DTOKorisnickiNalog {

    private String korisnickoIme, tipKorisnika;
    private int idZaposlenog, idNaloga;
    private boolean aktivan;
    private byte[] lozinka;

    public DTOKorisnickiNalog(String korisnickoIme, String unesenaLozinka, boolean aktivan, String tipKorisnika, int idZaposlenog) throws NoSuchAlgorithmException {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.tipKorisnika = tipKorisnika;
        this.idZaposlenog = idZaposlenog;
        this.aktivan = aktivan;
         final Random rn = new SecureRandom();
        byte[] salt = new byte[32];
        rn.nextBytes(salt);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
         this.lozinka = digest.digest(unesenaLozinka.getBytes());
        
    }
    
    public DTOKorisnickiNalog(String korisnickoIme, byte[] lozinka, boolean aktivan, String tipKorisnika, int idZaposlenog) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.tipKorisnika = tipKorisnika;
        this.idZaposlenog = idZaposlenog;
        this.aktivan = aktivan;}

    public DTOKorisnickiNalog(int idNaloga, String korisnickoIme, byte[] lozinka, boolean aktivan, String tipKorisnika, int idZaposlenog) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.tipKorisnika = tipKorisnika;
        this.idZaposlenog = idZaposlenog;
        this.aktivan = aktivan;
        this.idNaloga = idNaloga;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public byte[] getLozinka() {
        return lozinka;
    }

    public void setLozinka(String unesenaLozinka) throws IOException, NoSuchAlgorithmException {
        
        final Random rn = new SecureRandom();
        byte[] salt = new byte[32];
        rn.nextBytes(salt);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        byte[] hesiranaLozinka = digest.digest(unesenaLozinka.getBytes());
        System.out.println(hesiranaLozinka);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(hesiranaLozinka);
        outputStream.write(salt);
        byte []hesISalt = outputStream.toByteArray();
        this.lozinka = hesISalt;
    }

    public String getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(String tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public int getIdZaposlenog() {
        return idZaposlenog;
    }

    public void setIdZaposlenog(int idZaposlenog) {
        this.idZaposlenog = idZaposlenog;
    }

    public int getIdNaloga() {
        return idNaloga;
    }

    public void setIdNaloga(int idNaloga) {
        this.idNaloga = idNaloga;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

 @Override
 public String toString(){
 
     return idZaposlenog + "nalog iz DTO";
 
 }
}
