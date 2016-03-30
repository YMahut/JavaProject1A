import java.awt.*;

import javax.swing.*;

import java.util.*;

public class Equipe {
	String id;
	String nom;
	String localite;
	int nbMembre;
	ArrayList<Participant> listParticipant;

	public Equipe(){
		id = "Defaut";
		nom = "nom par defaut";
		localite= "localite par defaut";
		nbMembre= 0;
		this.listParticipant = new ArrayList();
	}
	public Equipe(String id, String nom, String localite,  int nbMembre){
		this.id = id;
		this.nom = nom;
		this.localite= localite;
		this.nbMembre= nbMembre;
		this.listParticipant = new ArrayList();
	}	
	public Equipe(String id, String nom, String localite,  int nbMembre, Participant participant){
		this.id = id;
		this.nom = nom;
		this.localite= localite;
		this.nbMembre= nbMembre;
		this.listParticipant = new ArrayList();
		this.listParticipant.add(participant);
	}
	public Equipe(Equipe nouvEquipe){
		this.id = nouvEquipe.getId();
		this.nom = nouvEquipe.getNom();
		this.localite= nouvEquipe.getLocalite();
		this.nbMembre= nouvEquipe.getNbMembre();
		this.listParticipant = new ArrayList();
		this.listParticipant = nouvEquipe.getListParticipant();
	}
	public ArrayList<Participant> getListParticipant() {
		return listParticipant;
	}
	public void setListParticipant(ArrayList<Participant> listParticipant) {
		this.listParticipant = listParticipant;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLocalite() {
		return localite;
	}
	public void setLocalite(String localite) {
		this.localite = localite;
	}
	public int getNbMembre() {
		return nbMembre;
	}
	public void setNbMembre(int nbMembre) {
		this.nbMembre = nbMembre;
	}
	public void afficher(){
		System.out.println(this.getId() + this.getNom() + this.getLocalite() + this.getNbMembre());
	}
}
