/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Temporal;

/**
 *
 * @author Abed
 */
@Entity
public class Stagiaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    private String gender;
    private String etablissement;
    private int niveau;
    private String filiere;
    private String telephone;
    private String adresse;
    private String email;

    @ManyToOne
    private Stagee stagee;
    
    @ManyToOne
    private Departement departement;
    @ManyToOne
    private Encadrant encadrant;
    

    public Encadrant getEncadrant() {
        return encadrant;
    }


    public void setEncadrant(Encadrant encadrant) {
        this.encadrant = encadrant;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Stagee getStagee() {
        return stagee;
    }

    public void setStagee(Stagee stagee) {
        this.stagee = stagee;
    }

    public String getNom() {
        return nom;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Stagiaire(String nom, String prenom, Date dateNaissance, String gender, String etablissement, int niveau, String filiere, String telephone, String adresse, String email, Stagee stage, Departement departement, Encadrant encadrantStage) {
        this.stagee = stage;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.gender = gender;
        this.departement = departement;
        this.encadrant = encadrantStage;
        this.etablissement = etablissement;
        this.niveau = niveau;
        this.filiere = filiere;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
    }

    public Stagiaire() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stagiaire)) {
            return false;
        }
        Stagiaire other = (Stagiaire) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "(" + id + ") " + nom + " " + prenom;
    }

}
