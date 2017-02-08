/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Abed
 */
@Entity
public class Stagee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDeb;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;
    private Double budget;
    private Double avancement;
    private int importRest;
    @ManyToOne
    private Departement departement;

    @OneToOne(mappedBy = "stagee")
    private Encadrant encadrant;

    @OneToMany(mappedBy = "stagee")
    private List<Stagiaire> stagiaires;
    
    @OneToMany(mappedBy = "stagee")
    private List<Tache> taches;

    public Encadrant getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(Encadrant encadrant) {
        this.encadrant = encadrant;
    }

    public Double getAvancement() {
        return avancement;
    }

    public void setAvancement(Double avancement) {
        this.avancement = avancement;
    }

    public int getImportRest() {
        return importRest;
    }

    public void setImportRest(int importRest) {
        this.importRest = importRest;
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

    public Stagee(String nom, Date dateDeb, Date dateFin, double budget,  Departement departement, Encadrant encadrant) {
        this.nom = nom;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.budget = budget;
        this.departement = departement;
        this.encadrant = encadrant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Stagee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
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
        if (!(object instanceof Stagee)) {
            return false;
        }
        Stagee other = (Stagee) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "(" + id + ") " + nom;
    }

}
