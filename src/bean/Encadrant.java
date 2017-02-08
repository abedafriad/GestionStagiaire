/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Abed
 */
@Entity
public class Encadrant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

    private String login;
    private String password;
    private int root = 0;
    private int blocked = 1;

    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String gender;
    @OneToMany(mappedBy = "encadrant")
    private List<Stagiaire> stagiaires;
    
    @ManyToOne
    private Departement departement;
    @OneToOne
    private Stagee stagee;

    public String getNom() {
        return nom;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public List<Stagiaire> getStagiaires() {
        return stagiaires;
    }

    public void setStagiaires(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Stagee getStagee() {
        return stagee;
    }

    public void setStagee(Stagee stagee) {
        this.stagee = stagee;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public Encadrant(String nom, String prenom, String adresse, String telephone, String email, String gender, Departement departement, Stagee stagee) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.gender = gender;
        this.departement = departement;
        this.stagee = stagee;
    }

    public Encadrant(String login, String password, String nom, String prenom, String adresse, String telephone, String email, String gender, Departement departement, Stagee stagee) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.gender = gender;
        this.departement = departement;
        this.stagee = stagee;
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }
    
    

    public Encadrant() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the login fields are not set
        if (!(object instanceof Encadrant)) {
            return false;
        }
        Encadrant other = (Encadrant) object;
        return !((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login)));
    }

    @Override
    public String toString() {
        String g="";
        if (gender.equalsIgnoreCase("M")){
            g="Mr.";
        }
        if (gender.equalsIgnoreCase("F")){
            g="Mme.";
        }
        return "(" + login + ") "+g+" " + prenom + " " + nom;
    }

}
