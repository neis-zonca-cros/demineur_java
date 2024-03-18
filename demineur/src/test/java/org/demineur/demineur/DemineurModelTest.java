package org.demineur.demineur;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemineurModelTest {

    @Test
    void decouvrirCaseSansMine() {
        DemineurModel demineur = new DemineurModel(3, 3,0);

        demineur.decouvrirCase(0, 0);
        System.out.println("La case (0, 0) a été découverte.");

        assertTrue(demineur.estCaseDecouverte(0, 0));
        System.out.println("La case (0, 0) est bien passé en bool true sur estCaseDecouverte");

        assertTrue(demineur.estCaseDecouverte(0, 1));
        assertTrue(demineur.estCaseDecouverte(1, 0));
        assertTrue(demineur.estCaseDecouverte(1, 1));
        assertTrue(demineur.estCaseDecouverte(0, 2));
        assertTrue(demineur.estCaseDecouverte(1, 2));
        assertTrue(demineur.estCaseDecouverte(2, 0));
        assertTrue(demineur.estCaseDecouverte(2, 1));
        assertTrue(demineur.estCaseDecouverte(2, 2));
        System.out.println("Puisqu'il n'y a pas de mines, toutes les cases sont passées en découvertes après le premier découvrirCase");


    }


    @Test
    public void testNombreMines() {
        int largeur = 10;
        int hauteur = 10;
        int nombreMines = 15;

        DemineurModel demineur = new DemineurModel(largeur, hauteur, nombreMines);

        int minesRestantes = demineur.getMinesRestantes();

        assertEquals(nombreMines, minesRestantes);
        System.out.println("Cas sans case mines découverte : Test Nombre de mines : Nombre de mines = mines restantes");


    }

    @Test
    public void testMarquageCase() {
        DemineurModel demineur = new DemineurModel(10, 10, 15);
        demineur.marquerCase(2, 2);
        assertEquals(14, demineur.getMinesRestantes());
        System.out.println("Nombre de mines restantes après avoir découvert une mine : " + demineur.getMinesRestantes());


    }

    @Test
    public void testPartieGameOver() {
        DemineurModel demineur = new DemineurModel(10, 10, 15);

        demineur.marquerCase(3, 6);
        demineur.decouvrirCase(3, 6);
        assertTrue(demineur.estPartieTerminee());
        System.out.println("Partie terminée ok quand on découvre une case mine");
    }


}