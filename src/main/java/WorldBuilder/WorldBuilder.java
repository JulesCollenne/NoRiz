package WorldBuilder;

/**
 * Cette classe permet de créer les niveaux
 * Les niveaux sont des matrices d'entiers dont les valeurs signifie la présence de vide (0), murs (1) ou consommables (2)
 *
 * NOTE TODO : Tu pourras rajouter un argument, ou d'autres fonctions pour créer des niveaux différents. Soit tu fais création de niveau
 * aléatoire, soit des niveau sur mesure. Comme tu le sens
 */
public class WorldBuilder {

    public int[][] build(int l, int h){
        int[][] world = new int[l][h];

        //TODO créer le niveau

        return world;
    }

    public int[][] build(){
        return build(16,16);
    }
}
