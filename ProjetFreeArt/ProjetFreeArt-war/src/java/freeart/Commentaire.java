package freeart;
// Generated 9 janv. 2015 14:45:14 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Commentaire generated by hbm2java
 */
public class Commentaire  implements java.io.Serializable {


     private Integer id;
     private String contenu;
     private int idUser;
     private int idCreation;
     private Date date;

    public Commentaire() {
    }

    public Commentaire(String contenu, int idUser, int idCreation, Date date) {
       this.contenu = contenu;
       this.idUser = idUser;
       this.idCreation = idCreation;
       this.date = date;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getContenu() {
        return this.contenu;
    }
    
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    public int getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdCreation() {
        return this.idCreation;
    }
    
    public void setIdCreation(int idCreation) {
        this.idCreation = idCreation;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }




}


