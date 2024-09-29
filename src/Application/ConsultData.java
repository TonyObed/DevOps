package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultData {
    // Déclaration de la connexion à la base de données
    static Connection connexion;

    public static void main(String[] args) {
        // Informations de connexion à la base de données MySQL
        String url = "jdbc:mysql://ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0072408"; // URL de connexion à la base de données
        String user = "user0072408"; // Nom d'utilisateur de la base de données
        String mdp = "Yf3IgyBsOPa34WR"; // Mot de passe de la base de données

        try {
            // Établissement de la connexion à la base de données
            connexion = DriverManager.getConnection(url, user, mdp);

            // Vérification si la connexion est réussie
            if (connexion != null) {
                System.out.println("Connexion à la base de données db0072408 réussie !");
                
                // Création d'un objet Statement pour exécuter des requêtes SQL
                Statement statement = connexion.createStatement();
                
                // Requête SQL pour récupérer les informations de l'étudiant ayant le matricule 0072838
                String requet = ("SELECT * FROM Etudiant WHERE matetud = 0072838 ");
                
                // Exécution de la requête SQL et récupération des résultats dans un ResultSet
                ResultSet resultset = statement.executeQuery(requet);

                // Si un étudiant est trouvé avec ce matricule, récupérer les informations
                if (resultset.next()) {
                    String matetud = resultset.getString("matetud"); // Récupération du matricule
                    String nom = resultset.getString("etudnom"); // Récupération du nom de l'étudiant
                    String pnom = resultset.getString("etudpnom"); // Récupération du prénom de l'étudiant
                    String naissance = resultset.getString("etudnaissance"); // Date de naissance
                    String school = resultset.getString("etudschool"); // Nom de l'école
                    String Bdecision = resultset.getString("etuddecison"); // Décision (succes, échec, etc.)
                    Double moyetud = resultset.getDouble("etudmoy"); // Moyenne obtenue
                    
                    // Affichage des informations de l'étudiant
                    System.out.println(matetud + " " + nom + " " + pnom + " " + naissance + " " + school + " " + Bdecision + " " + moyetud);
                }

            } else {
                // Message affiché en cas d'échec de connexion
                System.out.println("Échec de la connexion.");
            }
        } catch (SQLException e) {
            // Gestion des erreurs SQL et affichage des détails de l'erreur
            e.printStackTrace();
        }
    }
}
