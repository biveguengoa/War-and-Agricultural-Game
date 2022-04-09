package game;
import java.util.ArrayList;
import java.util.List;

import game.TypePlayers.FarmerPlayer;
import game.gameTypes.FarmerGame;
import game.util.io.Input;

/**
 * This class represents Main of the farmer's game
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */
public class FarmerMain {
	private final static int MAX_TOUR = 6;
	private final static int DEFAULT_WIDTH = 10;
	private final static int DEFAULT_LENGTH = 10;

	public static void main(String[] args) {

		List<FarmerPlayer> lst = new ArrayList<>();
		FarmerGame game;

		System.out.println("=========================================");
		System.out.println("|               BIENVENU                 |");
		System.out.println("=========================================");
		System.out.println();

		System.out.println("=========================================");
		System.out.println("|               Jeu agricole             |");
		System.out.println("=========================================");
		System.out.println();
		System.out.println();

		int choice = -1;
		do {

			System.out.println("=========================================");
			System.out.println("|       MENU SELECTION                  |");
			System.out.println("=========================================");
			System.out.println("| Options:                              |");
			System.out.println("|        1.Lancer le jeu!               |");
			System.out.println("|        2.Quitter le Jeu!              |");
			System.out.println("|        Faites Votre Choix             |");
			System.out.println("=========================================");
			System.out.println();
			try {
				choice = Input.readInt();
			} catch (java.io.IOException e) {
				System.out.println("Entrer un entier");
			}
			switch (choice) {
			case 1: {
				// boucle permettant de parcourir le nombre de joueur
				try {
					for (int i = 0; i < args.length; i++) {
						lst.add(new FarmerPlayer(args[i]));
					}
					game = new FarmerGame(FarmerMain.DEFAULT_WIDTH, FarmerMain.DEFAULT_LENGTH, lst);
	
					int nbTour = 0;
					while (!(game.isOver()) && nbTour != FarmerMain.MAX_TOUR) {
						game.play();
						nbTour++;
					}
					if (game.isOver() || nbTour == FarmerMain.MAX_TOUR) {
						System.out.println("----------GAME OVER----------");
						for (int i = 0; i < lst.size(); i++) {
							System.out.println("------------Bilan de " + lst.get(i).getName() + "-------------");
							System.out.println("Score : " + game.getScorePlayer(i) + " point(s)");
							System.out.println("Number of territories : " + lst.get(i).getNumberOfTiles());
	
							System.out.println();
						}
						if (game.getWinner().size() % 2 == 0) {
							System.out.print("Match nul pour les joueurs suvants: ");
							for (int i = 0; i < game.getWinner().size(); i++) {
								System.out.print(game.getWinner().get(i).getName() + ", ");
							}
							System.out.println();
						} else {
							for (int i = 0; i < game.getWinner().size(); i++) {
								System.out.println("-----" + game.getWinner().get(i).getName() + " a gagné-----");
	
							}
						}
					}
				}catch(IndexOutOfBoundsException e) {
					System.out.println("Il faut entrer le nom des joueurs pour pouvoir jouer!");
				}

				break;
			}
			case 2: {
				System.out.println("Au revoir!");
				break;
			}
			default:
				System.out.println("Choix incorrecte!");
			}

		} while (choice != 2 && choice !=1);
	}

}
