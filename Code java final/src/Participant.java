
public class Participant {
	
	String id;
	String nom;
	String prenom;
	int age;
	String sexe;
	String localite;
	String equipeID;
		
	
	public Participant(){
		id = "Default";
		nom = "Nom par defaut";
		prenom = "Prenom par defaut";
		age = 0;
		sexe = "D";
		localite = "localite par defaut";
		equipeID = null;
	}
	public Participant(String id, String nom, String prenom, int age, String sexe, String localite, String equipeID ){
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.sexe = sexe;
		this.localite = localite;
		this.equipeID = equipeID;
	}
	public Participant(Participant participant){
		this.id = participant.getId();
		this.nom = participant.getNom();
		this.prenom = participant.getPrenom();
		this.age = participant.getAge();
		this.sexe = participant.getSexe();
		this.localite = participant.getLocalite();
		this.equipeID = participant.getEquipeID();
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getLocalite() {
		return localite;
	}
	public void setLocalite(String localite) {
		this.localite = localite;
	}
	public String getEquipeID() {
		return equipeID ;
	}
	public void setEquipeID(String equipeID) {
		this.equipeID = equipeID;
	}
	public void afficher(){
		System.out.println(this);
	}
	
	
}
