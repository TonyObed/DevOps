package Application; 

// Import des bibliothèques nécessaires pour la connexion à la base de données et l'interface graphique
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Logiciel extends Application {
    // Variables de connexion à la base de données
    static String url = "jdbc:mysql://ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0072408"; // URL de connexion
    static String user = "user0072408"; // Nom d'utilisateur pour la base de données
    static String mdp = "Yf3IgyBsOPa34WR"; // Mot de passe pour la base de données
    static Connection co; // Objet de connexion à la base de données
    static Statement statement; // Objet pour exécuter les requêtes SQL
    static ResultSet resultset; // Stocke les résultats des requêtes SQL
    
    // Éléments de l'interface graphique
    static Label Adecision; // Label pour afficher la décision de réussite ou échec
    static Label instruction; // Instruction à l'utilisateur
    static TextField inputField; // Champ pour entrer le matricule
    static Button val; // Bouton pour valider la saisie
    static Button exit; // Bouton pour quitter l'application
    static Button more; // Bouton pour afficher les détails supplémentaires
    static Label donnee; // Label pour afficher les informations détaillées
    static HBox Hbox; // Conteneur horizontal pour organiser les boutons
    static VBox vetbox; // Conteneur vertical pour organiser l'interface principale
    
    // Méthode principale qui lance l'application JavaFX
    public static void main(String[] args) {
        launch(args); // Démarre l'application
    }

    // Méthode appelée lors du lancement de l'application
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialisation des éléments de l'interface
        instruction = new Label("POUR CONSULTER VOTRE RESULTAT ENTREZ VOTRE MATRICULE:");
        Adecision = new Label(); // Label pour afficher le résultat (succès/échec)
        donnee = new Label(); // Label pour afficher les détails de l'étudiant
        inputField = new TextField(); // Champ de saisie pour le matricule
        val = new Button("ENVOYER"); // Bouton pour soumettre la requête
        more = new Button("AFFICHER LES DETAILS"); // Bouton pour afficher plus de détails
        exit = new Button("EXIT"); // Bouton pour quitter l'application
        Hbox = new HBox(); // Conteneur horizontal pour organiser les boutons
        vetbox = new VBox(); // Conteneur vertical pour organiser l'interface

        // Action à effectuer lors du clic sur le bouton "ENVOYER"
        val.setOnAction(event -> {
            // Appel de la méthode co() pour vérifier le matricule dans la base de données
            co(inputField.getText());
        });
        
        // Action pour le bouton "EXIT" pour fermer l'application
        exit.setOnAction(event -> {
            primaryStage.close(); // Ferme la fenêtre de l'application
        });

        // Mise en page : ajout des éléments dans les conteneurs
        vetbox.getChildren().addAll(instruction, inputField, Hbox, Adecision, donnee); // Organisation des éléments verticaux
        Hbox.getChildren().addAll(val, exit, more); // Ajout des boutons horizontalement
        vetbox.setAlignment(Pos.CENTER); // Centrage des éléments
        vetbox.setSpacing(30); // Espacement entre les éléments verticaux
        Hbox.setSpacing(30); // Espacement entre les boutons

        // Création d'une scène avec les éléments graphiques
        Scene scene = new Scene(vetbox, 400, 600); // Dimension de la scène

        // Paramétrage de la fenêtre (titre et contenu)
        primaryStage.setScene(scene); // Ajout de la scène à la fenêtre principale
        primaryStage.setTitle("Consultation Resultat"); // Titre de la fenêtre
        primaryStage.show(); // Affichage de la fenêtre
    }
    
    // Méthode pour vérifier le matricule dans la base de données
    public static void co(String Verifmat) {
        try {
            // Connexion à la base de données
            co = DriverManager.getConnection(url, user, mdp);
            if (co != null) {
                // Création d'un statement pour exécuter une requête SQL
                statement = co.createStatement(); 
                String requet = "SELECT * FROM Etudiant WHERE matetud = " + Verifmat; // Requête pour rechercher un étudiant avec le matricule
                resultset = statement.executeQuery(requet); // Exécution de la requête SQL
                
                // Si un étudiant avec ce matricule est trouvé
                if (resultset.next()) {
                    // Récupération des données de l'étudiant depuis le résultat de la requête
                    String matetud = resultset.getString("matetud");
                    String nom = resultset.getString("etudnom");
                    String pnom = resultset.getString("etudpnom");
                    String naissance = resultset.getString("etudnaissance");
                    String school = resultset.getString("etudschool");
                    String Bdecision = resultset.getString("etuddecison"); // Décision de réussite ou échec
                    Double moyetud = resultset.getDouble("etudmoy"); // Moyenne obtenue

                    // Affichage de la décision si le matricule saisi correspond à celui récupéré
                    if (Verifmat.equals(matetud)) {
                        Adecision.setText(Bdecision); // Affiche la décision (Succès/Échec)
                    }
                    
                    // Action pour le bouton "AFFICHER LES DETAILS" pour afficher plus d'informations
                    more.setOnAction(event -> {
                        // Affichage des informations détaillées de l'étudiant
                        donnee.setText("Matricule : " + matetud + "\n" + 
                                       "Nom : " + nom + "\n" + 
                                       "Prenoms : " + pnom + "\n" + 
                                       "Date de naissance : " + naissance + "\n" + 
                                       "Nom ecole : " + school + "\n" + 
                                       "Decision : " + Bdecision + "\n" + 
                                       "Moyenne : " + moyetud + "\n");
                    });
                }
            } else {
                System.out.println("Échec de la connexion."); // Message si la connexion échoue
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Affiche les erreurs SQL
        }
    }
}
