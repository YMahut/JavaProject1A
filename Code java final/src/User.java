
public class User {
	
	String id;
	String pseudo;
	String mdp;
	
	public User( String id, String pseudo, String mdp){
		this.pseudo = pseudo;
		this.id = id;
		this.mdp = mdp;
	}

	public User(User nouvUser){
		this.pseudo = nouvUser.getPseudo();
		this.id = nouvUser.getId();
		this.mdp = nouvUser.getMdp();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public void afficher(){
		System.out.println(this);
	}

}
