package Application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JunitTest {
    @org.junit.jupiter.api.Test
    void testMoySuccès() {
        Double moy = 16.0;
        assertTrue(moy >= 10, "Etudiant Admis");
    }
    @org.junit.jupiter.api.Test
    void testFullNameConca() {
        String nom = "Bouagba";
        String pnom = "Obed";
        assertEquals("Bouagba Obed", nom + " " + pnom);
    }
    @org.junit.jupiter.api.Test
    void testMoyechec() {
        Double moy = 7.64;
        assertFalse(moy < 10, "Etudiant Recalé");
    }
    @org.junit.jupiter.api.Test
    void testMatriculeNonNull() {
        String matEtud = "0072408";
        assertNotNull(matEtud, "Le matricule ne doit pas être nul");
    }
    @org.junit.jupiter.api.Test
    void testIntEtudiant() {
        String nom = "Bouagba";
        String pnom = "Obed";
        int matAttendu = 1;
        int matObtenu = getMatricule(nom, pnom);

        assertEquals(matAttendu, matObtenu, "Le matricule de l'étudiant n'est pas égal à 1");
    }
	private int getMatricule(String nom, String prenom) {
		// TODO Auto-generated method stub
		return 0;
	}}
