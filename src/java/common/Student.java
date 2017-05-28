/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author ovidiugiorgi
 */
public class Student {
    private String nume;
    private String prenume;
    private String email;
    private int nr_prezente;
    
    public Student(String nume, String prenume, String email, int nr_prezente) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.nr_prezente = nr_prezente;
    }
    
    public String getNume() {
        return this.nume;
    }
    
    public void setNume(String nume) {
        this.nume = nume;
    }
    
    public String getPrenume() {
        return this.prenume;
    }
    
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getNrPrezente() {
        return this.nr_prezente;
    }
    
    public void setNrPrezente(int nr_prezente) {
        this.nr_prezente = nr_prezente;
    }
}
