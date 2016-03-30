import java.awt.*;
import java.awt.List;

import javax.imageio.*;
import javax.swing.*;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Fenetre extends JFrame {
	
	JFrame content;
	GridBagConstraints gbc;
	String log;
	public Fenetre(){		
		content = new JFrame();		
	    this.setTitle("Gestion d'équipe");
	    this.setSize(1200, 800);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
   	}
		
	public void validation(){
		JButton oui;
		JButton non;
		 Font font = new Font("Courier", Font.BOLD,32);
		 
		Graphique validation= new Graphique();
		 validation.setLayout(null);
		
		
		
		oui = new JButton("Continuer");
		non = new JButton("Se déconnecter");	
		oui.setBounds(400, 400, 100, 50);
		non.setBounds(800,400,100,50);
		validation.add(oui);
		validation.add(non);
		JLabel TitreCentral =new JLabel("Veuillez confirmer cette action : ");
		
		TitreCentral.setFont(font);
		TitreCentral.setForeground(Color.orange);
		TitreCentral.setBounds(350, 100, 1000, 50);
		validation.add(TitreCentral);
		this.setContentPane(validation);
	    this.setVisible(true);
	    
	    
	    
	     oui.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {					
						accueilGestionTournoi();
					}});	     
	     non.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent arg0) {
						accueil();
					}});
	}

	public JFrame getFenetre() {
		return content;
	}

	public void setFenetre(JFrame fenetre) {
		this.content = fenetre;
	}
   
	public JPanel accueil(){
        //Initialisation
	     JButton Valider = new JButton("Valider");
	     JButton Annuler =new JButton("Effacer");
	     Graphique panneau = new Graphique();	     
	     panneau.setLayout(null);
	     Font font = new Font("Courier", Font.BOLD,32);
	     Font font1 = new Font("Courier", Font.BOLD,16);
	     
	     //TitreCentral
	     JLabel TitreCentral =new JLabel("Bienvenue dans le système de gestion d'un tournoi");
	     JLabel TitreCentral1=new JLabel("sportif ! ! !");
	     TitreCentral.setForeground(Color.orange);
	     TitreCentral1.setForeground(Color.orange);
	     TitreCentral.setFont(font);
	     TitreCentral1.setFont(font);
	     TitreCentral.setBounds(100, 100, 1000, 50);
	     TitreCentral1.setBounds(500, 150, 1000, 50);
	     panneau.add(TitreCentral);
	     panneau.add(TitreCentral1);
	
	     //Bouton valider
	   
	     Valider.setBounds(450, 600, 100, 50);
	     panneau.add(Valider);
	     TitreCentral.setOpaque(true);
	     
	     //Bouton Annuler
	     Annuler.setBounds(650, 600, 100, 50);
	     panneau.add(Annuler);
	     
	     //Saisie du mot de passe 
	     JPasswordField  passwordField1 = new JPasswordField ("");
	     passwordField1.setBounds(500, 400, 300, 50);
		 panneau.add(passwordField1);
	
	     //Creation du texte mot de passe 
	     JLabel Nom =new JLabel("Mot de passe : ");
	     Nom.setBounds(300,400,100,50);
	     Nom.setForeground(Color.black);
	     Nom.setDisplayedMnemonic('n');
	     panneau.add(Nom);
	     Nom.setOpaque(true);    
	     setContentPane(panneau);
	     setSize(1200,800);
	     
	   //Saisie du mot de passe 
	     JTextField  textField1 = new JTextField ("");
	     textField1.setBounds(500, 300, 300, 50);
		 panneau.add(textField1);
	     
	     //Creation du texte login
	     JLabel login =new JLabel("Login : ");
	     login.setBounds(300,300,100,50);
	     login.setForeground(Color.black);
	     login.setDisplayedMnemonic('n');
	     panneau.add(login);
	     login.setOpaque(true);    
	     setContentPane(panneau);
	     setSize(1200,800);

	     //centrer la fram par rapport à l'écran 
	     setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
	     
			   
	     Valider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					log = textField1.getText();
				 	String correctPass=UserDAO.getUserByName(log).getMdp();
					char [] verificationmdp=correctPass.toCharArray();
					char[] password = passwordField1.getPassword();
				
					JPasswordField  Vraimotdepasse = new JPasswordField ("tournoi");
					System.out.println("texte saisie = " + String.copyValueOf(passwordField1.getPassword()));
					System.out.println(verificationmdp);
					System.out.println(Vraimotdepasse);
					
					   if(Arrays.equals(password, verificationmdp)){
						   validation();
						   JOptionPane jop;
						   jop = new JOptionPane();
						   System.out.println("mot de passe correcte");
					   }else{							   
						   JOptionPane jop;
						   
						   //Boîte du message d'erreur
						   jop = new JOptionPane();
						   jop.showMessageDialog(null, "Erreur mot de passe", "Erreur", JOptionPane.ERROR_MESSAGE);
						   System.out.println("entrer1");
						   passwordField1.setText(null);
					   }
				}
	     });	     
	     Annuler.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent arg0) {
		    		 System.out.println("entrer2");
		    		 passwordField1.setText(null);			   
					 System.out.println("texte saisie = " + String.copyValueOf(passwordField1.getPassword()));
			 }
		 });
	     setVisible(true);  
	     return panneau;    
 }
    
    public JPanel accueilGestionTournoi(){
    
    	//Initialisation
	    JButton VoirLesEquipes = new JButton("Voir les Equipes");
	    JButton ModifierMotDePasse =new JButton("Modifier le mot de passe");
	    Graphique panneau2 = new Graphique();
	    panneau2.setLayout(null);
	    Font font = new Font("Courier", Font.BOLD,32);
	    JLabel TitreCentralAccueil =new JLabel("Bienvenue dans le système de gestion d'un tournoi");
	    JLabel TitreCentralAccueil1=new JLabel("sportif ! ! !");
	    TitreCentralAccueil.setForeground(Color.orange);
	    TitreCentralAccueil1.setForeground(Color.orange);
	    TitreCentralAccueil.setFont(font);  
	    TitreCentralAccueil1.setFont(font);
	    TitreCentralAccueil.setBounds(100, 100, 1000, 50);
	    TitreCentralAccueil1.setBounds(500, 150, 1000, 50);
	    panneau2.add(TitreCentralAccueil);
	    panneau2.add(TitreCentralAccueil1);
	
	    //Bouton voir equipe
	    VoirLesEquipes.setBounds(700, 400, 150, 50);
	    panneau2.add(VoirLesEquipes);
	    
	    //Bouton modifier mot de passe
	    ModifierMotDePasse.setBounds(300, 400, 200, 50);
	    panneau2.add(ModifierMotDePasse);
	    
	    
	    //centrer la fenetre
	    setSize(1200,800);
	    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
	    TitreCentralAccueil.setOpaque(true);
	    setContentPane(panneau2);
	    ModifierMotDePasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modifiermotdepasse();
			}
		});
	    VoirLesEquipes.addActionListener(new ActionListener() {
	   		public void actionPerformed(ActionEvent arg0) {
	   			equipeInscrite();
	   		}
	   	});
	    setVisible(true);  
	    return panneau2; 

    }
     
	public JPanel creerEquipe(){
		 //Initialisation
	   JButton Enregistrer = new JButton("Enregistrer");
	   JButton Annuler =new JButton("Retour");
	   Graphique panneau3 = new Graphique();
	
	   panneau3.setLayout(null);
	
	   Font font = new Font("Courier", Font.BOLD,32);
	
	   
	   JLabel TitreModifierEquipe =new JLabel("Creer une équipe");
	   TitreModifierEquipe.setBounds(450, 100, 400, 50);
	   TitreModifierEquipe.setForeground(Color.orange);
	   TitreModifierEquipe.setBackground(Color.cyan);
	   TitreModifierEquipe.setFont(font);  
	   panneau3.add(TitreModifierEquipe);
	
	   //Bouton voir equipe
		   
	   Enregistrer.setBounds(700, 600, 150, 50);
	   panneau3.add(Enregistrer);
	   
	   //Bouton annuler 
	   
	   Annuler.setBounds(300, 600, 200, 50);
	   panneau3.add(Annuler);
	   
	   Annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				equipeInscrite();
			}
		});
	   
	   //Saisie nom de l'équipe 
	   JTextField SaisieNom = new JTextField("Nom de l'équipe");
	   SaisieNom.setBounds(500, 200, 300, 50);
	   panneau3.add(SaisieNom);
		   
	   //Creation du texte Nom de l'équipe
	   JLabel Nom =new JLabel("Nom de l'équipe : ");
	   Nom.setBounds(300,200,100,50);
	   Nom.setForeground(Color.black);
	   Nom.setDisplayedMnemonic('n');
	   Nom.setLabelFor(SaisieNom);
	   panneau3.add(Nom);
	   
	 //Saisie nombre de membre 
	   JTextField SaisieNom1 = new JTextField("Nombre de membre");
	   SaisieNom1.setBounds(500, 300, 300, 50);
	   panneau3.add(SaisieNom1);
		   
	   //Creation du texte Nom de l'équipe
	   JLabel Nom1 =new JLabel("Nombre de membre : ");
	   Nom1.setBounds(300,300,200,50);
	   Nom1.setForeground(Color.black);
	   Nom1.setDisplayedMnemonic('n');
	   Nom1.setLabelFor(SaisieNom1);
	   panneau3.add(Nom1);
	   
	   
	//Saisie localité 
	   JTextField SaisieNom2 = new JTextField("Localité (Ville,Pays..");
	   SaisieNom2.setBounds(500, 400, 300, 50);
	   panneau3.add(SaisieNom2);
	   //Creation du texte Localité (Ville,Pays..
	   JLabel Nom2 =new JLabel("Localité (Ville,Pays.. : ");
	   Nom2.setBounds(300,400,200,50);
	   Nom2.setForeground(Color.black);
	   Nom2.setDisplayedMnemonic('n');
	   Nom2.setLabelFor(SaisieNom2);
	   panneau3.add(Nom2);
	   
	   //centrer la fenetre
	   setSize(1200,800);
	   setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	   Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	   setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
	   TitreModifierEquipe.setOpaque(true);
	   setContentPane(panneau3);
	   
	   Enregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Equipe tmp =new Equipe(Integer.toString(EquipeDAO.getEquipeAll().size() +1), SaisieNom.getText(), SaisieNom2.getText(), Integer.parseInt(SaisieNom1.getText()));
					int retour = EquipeDAO.add(tmp);
					System.out.println(retour+ " lignes ajoutées");	
					JOptionPane jop;
					jop = new JOptionPane();
					jop.showMessageDialog(null, "Equipe crée !", "Information", JOptionPane.INFORMATION_MESSAGE);
					
				
			}
		});
	   
	 /*  
	   SaisieNom.addActionListener(new ActionListener() {
		   
	   public void actionPerformed(ActionEvent arg0) {
		   MouseListener l;
		   SaisieNom.addKeyListener(l.mousedown);
		   SaisieNom.setText("");
		   
		}
		});
	*/
	
	   setVisible(true);  
	   return panneau3; 
	}   
	
	public JPanel ajoutMembre(String nom){
		//Initialisation
		JButton Enregistrer = new JButton("Enregistrer");
		JButton Annuler =new JButton("Annuler");
		JPanel panneau3 = new JPanel();
		panneau3.setLayout(null);
		Font font = new Font("Courier", Font.BOLD,32);
		JLabel TitreModifierEquipe =new JLabel("Ajouter un membre");
		TitreModifierEquipe.setForeground(Color.orange);
		TitreModifierEquipe.setFont(font);  
		TitreModifierEquipe.setBounds(450, 10, 1000, 50);
		panneau3.add(TitreModifierEquipe);
		
		//Bouton voir equipe
		Enregistrer.setBounds(700, 600, 150, 50);
		panneau3.add(Enregistrer);
		
		//Bouton modifier mot de passe
		Annuler.setBounds(300, 600, 200, 50);
		panneau3.add(Annuler);
		
		//Saisie nom de l'équipe 
		JTextField SaisieNom = new JTextField("Nom");
		SaisieNom.setBounds(500, 100, 300, 50);
		panneau3.add(SaisieNom);
			   
		//Creation du texte Nom 
		JLabel Nom =new JLabel("Nom : ");
		Nom.setBounds(300,100,100,50);
		Nom.setForeground(Color.black);
		Nom.setDisplayedMnemonic('n');
		Nom.setLabelFor(SaisieNom);
		panneau3.add(Nom);
		
		//Saisie prenom
		JTextField SaisieNom1 = new JTextField("Prenom");
		SaisieNom1.setBounds(500, 200, 300, 50);
		panneau3.add(SaisieNom1);
			   
		//Creation du texte prenom
		JLabel Nom1 =new JLabel("Prenom : ");
		Nom1.setBounds(300,200,200,50);
		Nom1.setForeground(Color.black);
		Nom1.setDisplayedMnemonic('n');
		Nom1.setLabelFor(SaisieNom1);
		panneau3.add(Nom1);
		
		//Creation du texte Localité (Ville,Pays..
		JLabel Nom2 =new JLabel("Sexe : ");
		Nom2.setBounds(300,300,200,50);
		Nom2.setForeground(Color.black);
		Nom2.setDisplayedMnemonic('n');
		JRadioButton radio1 = new JRadioButton("Masculin");
		radio1.setMnemonic(KeyEvent.VK_1);
		radio1.setActionCommand("Masculin");
		radio1.setSelected(true);
		JRadioButton radio2 = new JRadioButton("Féminin");
		radio2.setMnemonic(KeyEvent.VK_2);
		radio2.setActionCommand("Féminin");
		radio1.setBounds(500,300,200,50);
		radio2.setBounds(700,300,200,50);
		panneau3.add(radio1);
		panneau3.add(radio2);
		panneau3.add(Nom2);
		
		//Saisie age
		JTextField Age = new JTextField("Age");
		Age.setBounds(500, 400, 50, 50);
		panneau3.add(Age);
			   
		//Creation du texte Nom de l'équipe
		JLabel Age1 =new JLabel("Age : ");
		Age1.setBounds(300,400,200,50);
		Age1.setForeground(Color.black);
		Age1.setDisplayedMnemonic('n');
		Age1.setLabelFor(Age1);
		panneau3.add(Age1);
		
		//Saisie localité 
		JTextField SaisieNom2 = new JTextField("Localité (Ville,Pays..");
		SaisieNom2.setBounds(500, 500, 300, 50);
		panneau3.add(SaisieNom2);
		  
		//Creation du texte Localité (Ville,Pays..
		JLabel Nom3 =new JLabel("Localité (Ville,Pays.. : ");
		Nom3.setBounds(300,500,200,50);
		Nom3.setForeground(Color.black);
		Nom3.setDisplayedMnemonic('n');
		Nom3.setLabelFor(SaisieNom2);
		panneau3.add(Nom3);
		
		//centrer la fenetre
		setSize(1200,800);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		Enregistrer.addActionListener(new ActionListener() {
			
				public void actionPerformed(ActionEvent arg0) { 
					String sexe;
					if (radio1.isSelected()){sexe = "m";}else{sexe = "f";}
					Participant tmp =new Participant(Integer.toString(ParticipantDAO.getParticipantAll().size() +1), SaisieNom.getText(), SaisieNom1.getText(), Integer.parseInt(Age.getText()), sexe, SaisieNom2.getText(), EquipeDAO.getEquipeByName(nom).getId());
					int retour = ParticipantDAO.add(tmp);
					System.out.println(retour+ " lignes ajoutées");	
					JOptionPane jop;
					jop = new JOptionPane();
					jop.showMessageDialog(null, "Membre créé et ajouté à l'équipe : " + nom , "Information", JOptionPane.INFORMATION_MESSAGE);
					detailEquipe(nom);
				}
		});
		Annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			detailEquipe(nom);
			}
		});
		TitreModifierEquipe.setOpaque(true);
		setContentPane(panneau3);
		setVisible(true);  
		return panneau3; 
	}   
	
	public JPanel modifiermotdepasse() {
	Graphique panneau = new Graphique();
	//Saisie ancien mot de passe
    JTextField ancienmdp = new JTextField("Ancien mot de passe");
    ancienmdp.setBounds(500, 200, 300, 50);
    panneau.add(ancienmdp);
    
		   //Creation du texte ancien mot de passe
		   JLabel Nom =new JLabel("Ancien mot de passe : ");
		   Nom.setBounds(200,200,200,50);
		   Nom.setForeground(Color.black);
		   Nom.setDisplayedMnemonic('n');
		   Nom.setLabelFor(ancienmdp);
		   panneau.add(Nom);
		   
		   //Saisie du nouveau mdp
		   JTextField newmdp = new JTextField("Nouveau mot de passe");
		   newmdp.setBounds(500, 300, 300, 50);
		   panneau.add(newmdp);
		   
		   //Creation du texte Nnoueau mot de passe
		   JLabel Nom1 =new JLabel("Nouveau mot de passe : ");
		   Nom1.setBounds(200,300,200,50);
		   Nom1.setForeground(Color.black);
		   Nom1.setDisplayedMnemonic('n');
		   Nom1.setLabelFor(newmdp);
		   panneau.add(Nom1);
		   
		   //Saisir confirmation du nouveau mot de passe
		   JTextField newmdpconfirm = new JTextField("Confirmé nouveau mot de passe");
		   newmdpconfirm.setBounds(500, 400, 300, 50);
		   panneau.add(newmdpconfirm);
			   
		   //Creation du texte confirmé mdp
		   JLabel Nom2 =new JLabel("Confirmé nouveau mot de passe: ");
		   Nom2.setBounds(200,400,300,50);
		   Nom2.setForeground(Color.black);
		   Nom2.setDisplayedMnemonic('n');
		   Nom2.setLabelFor(newmdpconfirm);
		   panneau.add(Nom2);
		   JButton Retour = new JButton("Retour");
		   Retour.setBounds(800, 500, 150, 50);
		   panneau.add(Retour);
		   JButton Enregistrer = new JButton("Enregistrer");
		   Enregistrer.setBounds(400, 500, 150, 50);
		   panneau.add(Enregistrer);
		   panneau.setLayout(null);
		   Retour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					accueilGestionTournoi();
				}
			});
		   Enregistrer.addActionListener(new ActionListener() {
				@SuppressWarnings("null")
				public void actionPerformed(ActionEvent arg0) {
					   //fonction de recuperation des Jtext
					   String Ancienmotdepasse1;
					   String nouveaumotdepasse;
					   String Confirmenouveaumotdepasse;
					   Ancienmotdepasse1=ancienmdp.getText();
					   nouveaumotdepasse= newmdp.getText();
					   Confirmenouveaumotdepasse= newmdpconfirm.getText();
					   
					   // System.out.println(mott);
					   @SuppressWarnings("static-access")
					   String correctPass=UserDAO.getUserByName(log).getMdp();
					   char [] Ancienmotdepasse=Ancienmotdepasse1.toCharArray();
					   char [] correctPasschar=correctPass.toCharArray();
					   char [] nouveaumotdepassechar=nouveaumotdepasse.toCharArray();
					   char [] confirmenouveaumotdepassechar=Confirmenouveaumotdepasse.toCharArray();
					   
					   if(Arrays.equals(Ancienmotdepasse, correctPasschar) &&(Arrays.equals(nouveaumotdepassechar, confirmenouveaumotdepassechar))){
						   try{
							   UserDAO.updateUser(UserDAO.getUserByName(log).getPseudo(), nouveaumotdepasse, UserDAO.getUserByName(log).getId());
							   System.out.println("ancien mot de passe vérifié");
							   JOptionPane jop;
							   jop = new JOptionPane();
							   jop.showMessageDialog(null, "Nouveau mot de passe enregistré !", "Information", JOptionPane.INFORMATION_MESSAGE);
							   System.out.println(UserDAO.getUserByName(log).getMdp()); 
						   }catch (Exception e) {
							   e.printStackTrace();
						   }
					   }else{
						   System.out.println("Erreur dans la verification des mots de passe");
						   JOptionPane jop;
						   //Boîte du message d'erreur
						   jop = new JOptionPane();
						   jop.showMessageDialog(null, "Erreur lors de la selection des champs des mots de passe", "Erreur", JOptionPane.ERROR_MESSAGE);
					    }
				}
			});
		setContentPane(panneau);
		setVisible(true);
		return panneau;
	}
	
	public JPanel equipeInscrite(){
		
		Graphique panneau = new Graphique();
		Font font = new Font("Courier", Font.BOLD,32);
		JLabel TitreCentralAccueil =new JLabel("Equipe(s)Inscrite(s)");
		TitreCentralAccueil.setForeground(Color.orange);
	    TitreCentralAccueil.setFont(font);  
	    TitreCentralAccueil.setBounds(450, 0, 1000, 50);
	    panneau.add(TitreCentralAccueil);
	    panneau.setLayout(null);
	    JButton creerEquipe = new JButton("creer Equipe");
	    creerEquipe.setBounds(800, 100, 150, 50);
	    panneau.add(creerEquipe);
	    JButton modifierEquipe = new JButton("Modifier Equipe");
	    modifierEquipe.setBounds(800, 300, 150, 50);
	    panneau.add(modifierEquipe);
	    JButton Retour = new JButton("Retour");
	    Retour.setBounds(800, 500, 150, 50);
	    panneau.add(Retour);
	    JButton detailequipe = new JButton("Détails équipe");
	    detailequipe.setBounds(200, 500, 150, 50);
	    panneau.add(detailequipe);
	    
	    //Les données du tableau
	    ArrayList <Equipe> listEquipe=new ArrayList();
		listEquipe.addAll(EquipeDAO.getEquipeAll());
		List listePanel = new List();
		int i = 0;
		while (i < listEquipe.size()){
			listePanel.add(listEquipe.get(i).getNom());
			i=i+1;
		}
		listePanel.setBounds(100, 200, 500, 250);
		panneau.add(listePanel);
		
		
	    creerEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				creerEquipe();
			}
		});
	    modifierEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modifierEquipe();
			}
		});
	    Retour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					accueilGestionTournoi();
				}
		});
	    detailequipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					JOptionPane jop = new JOptionPane();
				    String nom = jop.showInputDialog(null, "Veuillez indiquer l'équipe à modifier", "Détail Equipe", JOptionPane.QUESTION_MESSAGE);
				    detailEquipe(nom);
				    Equipe tmp = new Equipe(EquipeDAO.getEquipeByName(nom));
			    }catch(Exception e){
					JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
					jop.showMessageDialog(null, "Erreur", "Information", JOptionPane.ERROR_MESSAGE);
					equipeInscrite();
			    }
			}
		});
		setContentPane(panneau);
		setVisible(true);
		return panneau;
		
	}
	
	public JPanel modifierEquipe() {
		
		//Initialisation
		   JButton Enregistrer = new JButton("Enregistrer");
		   JButton Annuler =new JButton("Retour");
		   Graphique panneau3 = new Graphique();
		
		   panneau3.setLayout(null);
		
		   Font font = new Font("Courier", Font.BOLD,14);
		
		   
		   JLabel TitreModifierEquipe =new JLabel("Modifier equipe (Rentrer le nom d'une équipe à modifier ainsi que les modifications à apporter.)");
		   TitreModifierEquipe.setBounds(250, 100, 800, 50);
		   TitreModifierEquipe.setForeground(Color.orange);
		   TitreModifierEquipe.setBackground(Color.cyan);
		   TitreModifierEquipe.setFont(font);  
		   panneau3.add(TitreModifierEquipe);
		
		   //Bouton voir equipe
			   
		   Enregistrer.setBounds(700, 600, 150, 50);
		   panneau3.add(Enregistrer);
		   
		   //Bouton annuler 
		   
		   Annuler.setBounds(300, 600, 200, 50);
		   panneau3.add(Annuler);
		   
		   Annuler.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					equipeInscrite();
				}
			});
		   
		   //Saisie nom de l'équipe 
		   JTextField SaisieNom = new JTextField("Nom de l'équipe à modifier");
		   SaisieNom.setBounds(500, 200, 300, 50);
		   panneau3.add(SaisieNom);
			   
		   //Creation du texte Nom de l'équipe
		   JLabel Nom =new JLabel("Nom de l'équipe à modifier : ");
		   Nom.setBounds(300,200,175,50);
		   Nom.setForeground(Color.black);
		   Nom.setDisplayedMnemonic('n');
		   Nom.setLabelFor(SaisieNom);
		   panneau3.add(Nom);
		   
		   
		   //Saisie nom de l'équipe 
		   JTextField nouvNom = new JTextField("Nouveau nom de l'équipe");
		   nouvNom.setBounds(500, 300, 300, 50);
		   panneau3.add(nouvNom);
			   
		   //Creation du texte Nom de l'équipe
		   JLabel nouvNomLabel =new JLabel("Nouveau nom : ");
		   nouvNomLabel.setBounds(300,300,100,50);
		   nouvNomLabel.setForeground(Color.black);
		   nouvNomLabel.setDisplayedMnemonic('n');
		   nouvNomLabel.setLabelFor(nouvNom);
		   panneau3.add(nouvNomLabel);
		   
		 //Saisie nombre de membre 
		   JTextField SaisieNom1 = new JTextField("Nombre de membre");
		   SaisieNom1.setBounds(500, 400, 300, 50);
		   panneau3.add(SaisieNom1);
			   
		   //Creation du texte Nom de l'équipe
		   JLabel Nom1 =new JLabel("Nombre de membre : ");
		   Nom1.setBounds(300,400,200,50);
		   Nom1.setForeground(Color.black);
		   Nom1.setDisplayedMnemonic('n');
		   Nom1.setLabelFor(SaisieNom1);
		   panneau3.add(Nom1);
		   
		   
		//Saisie localité 
		   JTextField SaisieNom2 = new JTextField("Localité (Ville,Pays..");
		   SaisieNom2.setBounds(500, 500, 300, 50);
		   panneau3.add(SaisieNom2);
		   //Creation du texte Localité (Ville,Pays..
		   JLabel Nom2 =new JLabel("Localité (Ville,Pays.. : ");
		   Nom2.setBounds(300,500,200,50);
		   Nom2.setForeground(Color.black);
		   Nom2.setDisplayedMnemonic('n');
		   Nom2.setLabelFor(SaisieNom2);
		   panneau3.add(Nom2);
		   
		   //centrer la fenetre
		   setSize(1200,800);
		   setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		   Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		   setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		   TitreModifierEquipe.setOpaque(true);
		   setContentPane(panneau3);
		   
		   Enregistrer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
						Equipe tmp =new Equipe(EquipeDAO.getEquipeByName(SaisieNom.getText()));
						EquipeDAO.updateEquipe(nouvNom.getText(), SaisieNom2.getText(), Integer.parseInt(SaisieNom1.getText()), tmp.getId());
						JOptionPane jop;
						jop = new JOptionPane();
						jop.showMessageDialog(null, "Equipe crée !", "Information", JOptionPane.INFORMATION_MESSAGE);					
				}
			});
		   
		 /*  
		   SaisieNom.addActionListener(new ActionListener() {
			   
		   public void actionPerformed(ActionEvent arg0) {
			   MouseListener l;
			   SaisieNom.addKeyListener(l.mousedown);
			   SaisieNom.setText("");
			   
			}
			});
		*/
		
		   setVisible(true);  
		   return panneau3; 
		
	}
	
	public JPanel detailEquipe(String nom) {
		
		Graphique panneau = new Graphique();
		
		   JButton Retour = new JButton("Retour");
		    Retour.setBounds(800, 500, 150, 50);
		    panneau.add(Retour);
		    panneau.setLayout(null);
		    JButton Ajoutmembre = new JButton("Ajouter un membre");
		    Ajoutmembre.setBounds(200, 500, 150, 50);
		    panneau.add(Ajoutmembre);
		    panneau.setLayout(null);
		    JButton detailmembre = new JButton("Detail membre");
		    detailmembre.setBounds(200, 100, 150, 50);
		    panneau.add(detailmembre);
		    panneau.setLayout(null);
		    
		    
		    ArrayList <Participant> listParticipant = new ArrayList();
			listParticipant.addAll(ParticipantDAO.getParticipantByTeam(Integer.parseInt(EquipeDAO.getEquipeByName(nom).getId())));
			List listePanel = new List();
			int i = 0;
			while (i < listParticipant.size()){
				listePanel.add(listParticipant.get(i).getNom());
				i=i+1;
			}
			listePanel.setBounds(100, 200, 500, 250);
			panneau.add(listePanel);
		    
		    
		    
		   Retour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					equipeInscrite();
				}
		   });
				Ajoutmembre.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							ajoutMembre(nom);
						}
			});
				detailmembre.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						detailMembre();
					}
		});
		
		setContentPane(panneau);
		setVisible(true);
		return panneau;		
	}
	
	public JPanel detailMembre() {
		Graphique panneau = new Graphique();
		    JButton modifier = new JButton("Modifier");
		    modifier.setBounds(400, 500, 150, 50);
		    panneau.add(modifier);
		    panneau.setLayout(null);
		    
		    JButton supprimer = new JButton("Supprimer");
		    supprimer.setBounds(600, 500, 150, 50);
		    panneau.add(supprimer);
		    panneau.setLayout(null);
		    
		    JButton Retour = new JButton("Retour");
		    Retour.setBounds(800, 500, 150, 50);
		    panneau.add(Retour);
		    panneau.setLayout(null);
	
		   Retour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					equipeInscrite();
				}
			});
		   
		   modifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try{
						JOptionPane jop = new JOptionPane();
					    String nom = jop.showInputDialog(null, "Veuillez indiquer le nom du membre à modifier", "Détail membre", JOptionPane.QUESTION_MESSAGE);
						modifierMembre(ParticipantDAO.getParticipantByName(nom).getId());
				    }catch(Exception e){
						JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
						jop.showMessageDialog(null, "Erreur", "Information", JOptionPane.ERROR_MESSAGE);
						equipeInscrite();
				    }
				}
			});
		   
		   supprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try{
						JOptionPane jop = new JOptionPane();
					    String nom = jop.showInputDialog(null, "Veuillez indiquer le nom du membre à supprimer de la Base de données", "Détail membre", JOptionPane.QUESTION_MESSAGE);
						ParticipantDAO.deleteParticipant(ParticipantDAO.getParticipantByName(nom).getId());
						jop.showMessageDialog(null, "Membre supprimé", "Information", JOptionPane.INFORMATION_MESSAGE);
				    }catch(Exception e){
						JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
						jop.showMessageDialog(null, "Erreur", "Information", JOptionPane.ERROR_MESSAGE);
						equipeInscrite();
				    }
				}
			});
		
		
		setContentPane(panneau);
		setVisible(true);
		return panneau;		
	}
		
	public JPanel modifierMembre(String id) {
		//Initialisation
		   JButton Enregistrer = new JButton("Enregistrer");
		   JButton Annuler =new JButton("Retour");
		   Graphique panneau3 = new Graphique();
		   panneau3.setLayout(null);
		   Font font = new Font("Courier", Font.BOLD,14);
		   JLabel TitreModifierEquipe =new JLabel("Modifier membre");
		   TitreModifierEquipe.setBounds(250, 100, 800, 50);
		   TitreModifierEquipe.setForeground(Color.orange);
		   TitreModifierEquipe.setBackground(Color.cyan);
		   TitreModifierEquipe.setFont(font);  
		   panneau3.add(TitreModifierEquipe);
		   
	
		   //Bouton voir membre    
			   
		   Enregistrer.setBounds(700, 700, 150, 50);
		   panneau3.add(Enregistrer);
		   
		   //Bouton annuler 
		   
		   Annuler.setBounds(300, 700, 200, 50);
		   panneau3.add(Annuler);
		   
		   Annuler.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					equipeInscrite();
				}
			});
		   
		   //Saisie nom de l'équipe 
		   JTextField nouvNom = new JTextField("Nouveau nom du membre");
		   nouvNom.setBounds(500, 200, 300, 50);
		   panneau3.add(nouvNom);
			   
		   //Creation du texte Nom de l'équipe
		   JLabel nouvNomLabel =new JLabel("Nouveau nom du membre: ");
		   nouvNomLabel.setBounds(300,200,100,50);
		   nouvNomLabel.setForeground(Color.black);
		   nouvNomLabel.setDisplayedMnemonic('n');
		   nouvNomLabel.setLabelFor(nouvNom);
		   panneau3.add(nouvNomLabel);
		   
		   //Saisie nom de l'équipe 
		   JTextField nouvPrenom = new JTextField("Nouveau prenom du membre");
		   nouvPrenom.setBounds(500, 300, 300, 50);
		   panneau3.add(nouvPrenom);
			   
		   //Creation du texte Nom de l'équipe
		   JLabel nouvNomLabel1 =new JLabel("Nouveau prenom du membre: ");
		   nouvNomLabel1.setBounds(300,300,100,50);
		   nouvNomLabel1.setForeground(Color.black);
		   nouvNomLabel1.setDisplayedMnemonic('n');
		   nouvNomLabel1.setLabelFor(nouvNom);
		   panneau3.add(nouvNomLabel1);
		   
		 //Saisie nombre de membre 
		   JTextField SaisieNom1 = new JTextField("Nouvel age");
		   SaisieNom1.setBounds(500, 400, 300, 50);
		   panneau3.add(SaisieNom1);
			   
		   //Creation du texte Nom de l'équipe
		   JLabel agenew =new JLabel("Nouvel age : ");
		   agenew.setBounds(300,400,200,50);
		   agenew.setForeground(Color.black);
		   agenew.setDisplayedMnemonic('n');
		   agenew.setLabelFor(SaisieNom1);
		   panneau3.add(agenew);
		   
		   
		//Saisie localité 
		   JTextField localite = new JTextField("Localité (Ville,Pays..");
		   localite.setBounds(500, 500, 300, 50);
		   panneau3.add(localite);
		   //Creation du texte Localité (Ville,Pays..
		   JLabel localite1 =new JLabel("Nouvelle Localité (Ville,Pays.. : ");
		   localite1.setBounds(300,500,200,50);
		   localite1.setForeground(Color.black);
		   localite1.setDisplayedMnemonic('n');
		   localite1.setLabelFor(localite);
		   panneau3.add(localite1);
		   
		   //Sexe
				JLabel Sexe1 =new JLabel("Sexe : ");
				Sexe1.setBounds(300,600,200,50);
				Sexe1.setForeground(Color.black);
				Sexe1.setDisplayedMnemonic('n');
				JRadioButton sexemasculin = new JRadioButton("Masculin");
				sexemasculin.setMnemonic(KeyEvent.VK_1);
				sexemasculin.setActionCommand("Masculin");
				sexemasculin.setSelected(true);
				JRadioButton sexefeminin = new JRadioButton("Féminin");
				sexefeminin.setMnemonic(KeyEvent.VK_2);
				sexefeminin.setActionCommand("Féminin");
				sexemasculin.setBounds(500,600,200,50);
				sexefeminin.setBounds(700,600,200,50);
				panneau3.add(sexemasculin);
				panneau3.add(sexefeminin);
				panneau3.add(Sexe1);
			
		   
		   //centrer la fenetre
		   setSize(1200,800);
		   setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		   Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		   setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		   TitreModifierEquipe.setOpaque(true);
		   setContentPane(panneau3);
		   
		   //sexefeminin,sexemasculin,agenew,nouvNom,nouvPrenom,localite
		   
		   Enregistrer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try{
						String sexe;
						if(sexemasculin.isSelected()==true){
							sexe="m";
						}
						else{
							sexe="f";
						}

						ParticipantDAO.updateParticipant(nouvNom.getText(), nouvPrenom.getText(), Integer.parseInt(agenew.getText()), sexe,localite.getText(),ParticipantDAO.getParticipantByID(id).getEquipeID(),ParticipantDAO.getParticipantByID(id).getId());
						JOptionPane jop;
						jop = new JOptionPane();
						jop.showMessageDialog(null, "Membre modifié !", "Information", JOptionPane.INFORMATION_MESSAGE);	
						
					}
					catch(Exception e ){
						e.printStackTrace();
						
						JOptionPane jop;
						jop = new JOptionPane();
						jop.showMessageDialog(null, "Erreur !", "Information", JOptionPane.ERROR_MESSAGE);
					}
					}
				
			});
		
		
		setContentPane(panneau3);
		setVisible(true);
		return panneau3;		
	}
		

		
	public JPanel supprimerEquipe(String id) {
		Graphique panneau = new Graphique();
		    JButton modifier = new JButton("Modifier");
		    modifier.setBounds(400, 500, 150, 50);
		    panneau.add(modifier);
		    panneau.setLayout(null);
		    
		    JButton Retour = new JButton("Retour");
		    Retour.setBounds(800, 500, 150, 50);
		    panneau.add(Retour);
		    panneau.setLayout(null);
	
		   Retour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					detailMembre();
				}
			});
		
		setContentPane(panneau);
		setVisible(true);
		return panneau;		
	}
		
}
