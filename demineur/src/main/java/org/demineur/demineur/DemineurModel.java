package org.demineur.demineur;
import java.util.Random;

public class DemineurModel {
    private int[][] grille;
    private boolean[][] decouverte;
    private boolean[][] mines;
    private int minesRestantes;

    public DemineurModel(int largeur, int hauteur, int nombreMines) {
        grille = new int[largeur][hauteur];
        decouverte = new boolean[largeur][hauteur];
        mines = new boolean[largeur][hauteur];
        minesRestantes = nombreMines;
        initialiserGrille();
        placerMines(nombreMines);
        mettreAJourGrille();
    }

    private void initialiserGrille() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                grille[i][j] = 0;
            }
        }
    }

    private void placerMines(int nombreMines) {
        Random random = new Random();
        int minesPlacees = 0;

        while (minesPlacees < nombreMines) {
            int x = random.nextInt(grille.length);
            int y = random.nextInt(grille[0].length);
            if (!mines[x][y]) {
                mines[x][y] = true;
                minesPlacees++;
            }
        }
    }

    private void mettreAJourGrille() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                if (!mines[i][j]) {
                    grille[i][j] = compterMinesVoisines(i, j);
                }
            }
        }
    }

    private int compterMinesVoisines(int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < grille.length && j >= 0 && j < grille[0].length && mines[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public void decouvrirCase(int x, int y) {
        if (x < 0 || x >= grille.length || y < 0 || y >= grille[0].length || decouverte[x][y]) {
            return;
        }

        decouverte[x][y] = true;
        System.out.println("Découvert: (" + x + ", " + y + ")");

        if (grille[x][y] > 0) {
            return;
        }

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (!(i == x && j == y)
                        && i >= 0
                        && i < grille.length
                        && j >= 0
                        && j < grille[0].length
                        && !decouverte[i][j]) {
                    decouvrirCase(i, j);

                }
            }
        }
    }

    public void marquerCase(int x, int y) {
        if (x < 0 || x >= grille.length || y < 0 || y >= grille[0].length || decouverte[x][y]) {
            return;
        }

        mines[x][y] = !mines[x][y];

        // MAJ de nb de mines restantes
        if (mines[x][y]) {
            minesRestantes--;
        } else {
            minesRestantes++;
        }
    }

    public boolean estPartieTerminee() {
        boolean partieTerminee = false;

        boolean toutesDecouvertes = true;
        for (int i = 0; i < grille.length && toutesDecouvertes; i++) {
            for (int j = 0; j < grille[0].length && toutesDecouvertes; j++) {
                if (!mines[i][j] && !decouverte[i][j]) {
                    toutesDecouvertes = false;
                }
            }
        }

        boolean mineDecouverte = false;
        for (int i = 0; i < grille.length && !mineDecouverte; i++) {
            for (int j = 0; j < grille[0].length && !mineDecouverte; j++) {
                if (mines[i][j] && decouverte[i][j]) {
                    mineDecouverte = true;
                }
            }
        }

        if (toutesDecouvertes || mineDecouverte) {
            partieTerminee = true;
        }

        return partieTerminee;
    }

    public int getValeurCase(int x, int y) {
        return grille[x][y];
    }

    public boolean estCaseDecouverte(int x, int y) {
        return decouverte[x][y];
    }

    public boolean estCaseMarquee(int x, int y) {
        return mines[x][y];
    }

    public int getMinesRestantes() {
        return minesRestantes;
    }


}

