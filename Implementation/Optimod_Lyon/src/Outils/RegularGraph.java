package Outils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Modele.*;

/**
 * @author Christine Solnon + H4103
 *
 */

public class RegularGraph implements Graph {
	private int nbVertices;
	private int maxArcCost;
	private int minArcCost;
	private int[][] cost;
	private ArrayList<ArrayList<Integer>> succ;
	private ArrayList<ArrayList<Chemin>> chemins;
	private Vector<PlageHoraire> plagesHoraires;
	private int nbLivraisonsTotal;

	private Map<Integer, Integer> nodeToChoco;
	private Map<Integer, Integer> chocoToNode;

	/**
	 * Constructeur qui crée le graphe necessaire pour le traitement par Choco
	 * 
	 * @param entrepot
	 *            Le noeud ou se situe l'entrepot
	 * @param plagesHoraires
	 *            L'ensemble des plages horaires (qui contiennent les
	 *            livraisons) de la demande de livraison en cours
	 * @param plan
	 *            Le plan chargé
	 */
	public RegularGraph(Noeud entrepot, Vector<PlageHoraire> plagesHoraires,
			Plan plan) {
		// Verification des parametres
		if (plagesHoraires.isEmpty()) {
			return;
		}

		this.plagesHoraires = plagesHoraires;
		convertNodeIds(plagesHoraires, entrepot);

		calculMatriceSucc(entrepot);

		calculMatriceCost(plan);
	}

	/**
	 * Méthode qui crée 2 structures pour pouvoir facilement convertir les id
	 * des livraisons de choco au plan
	 * 
	 * @param entrepot
	 *            Le noeud ou se situe l'entrepot
	 * @param plagesHoraires
	 *            L'ensemble des plages horaires (qui contiennent les
	 *            livraisons) de la demande de livraison en cours
	 */
	private void convertNodeIds(Vector<PlageHoraire> plagesHoraires,
			Noeud entrepot) {
		nodeToChoco = new HashMap<Integer, Integer>();
		chocoToNode = new HashMap<Integer, Integer>();

		nodeToChoco.put(entrepot.getIdNoeud(), 0);
		chocoToNode.put(0, entrepot.getIdNoeud());

		int sum = 1;
		for (int i = 0; i < plagesHoraires.size(); i++) {
			for (int j = 0; j < plagesHoraires.get(i).getLivraisons().size(); j++) {
				nodeToChoco.put(plagesHoraires.get(i).getLivraisons().get(j)
						.getDestinataire().getNoeudAdresse().getIdNoeud(), sum);
				chocoToNode.put(sum,
						plagesHoraires.get(i).getLivraisons().get(j)
								.getDestinataire().getNoeudAdresse()
								.getIdNoeud());
				sum++;
			}
		}
	}

	/**
	 * Méthode qui crée la matrice de successeurs
	 * 
	 * @param entrepot
	 *            Le noeud ou se situe l'entrepot
	 */
	private void calculMatriceSucc(Noeud entrepot) {
		succ = new ArrayList<ArrayList<Integer>>();

		// Successeurs de l'entrepot
		ArrayList<Integer> succEntrepot = new ArrayList<Integer>();

		for (int i = 0; i < plagesHoraires.get(0).getLivraisons().size(); i++) {
			succEntrepot.add(nodeToChoco.get(plagesHoraires.get(0)
					.getLivraisons().get(i).getDestinataire().getNoeudAdresse()
					.getIdNoeud()));
		}

		succ.add(succEntrepot);

		this.nbLivraisonsTotal = 0;

		for (int i = 0; i < plagesHoraires.size(); i++) {
			for (int j = 0; j < plagesHoraires.get(i).getLivraisons().size(); j++) {
				this.nbLivraisonsTotal += 1;

				ArrayList<Integer> succLivraison = new ArrayList<Integer>();
				int nbLivraisons = plagesHoraires.get(i).getLivraisons().size(); // nb
																					// de
																					// livraisons
																					// de
																					// la
																					// plage
																					// horaire
																					// actuelle

				// Liens entre livraisons d'une meme plage horaire
				for (int k = 0; k < nbLivraisons; k++) {
					if (j == k) {
						continue;
					}

					succLivraison.add(nodeToChoco.get(plagesHoraires.get(i)
							.getLivraisons().get(k).getDestinataire()
							.getNoeudAdresse().getIdNoeud()));
				}

				// Liens entre livraisons d'une plage horaire i et i+1
				if (i < plagesHoraires.size() - 1) {
					for (int k = 0; k < plagesHoraires.get(i + 1)
							.getLivraisons().size(); k++) {
						succLivraison.add(nodeToChoco.get(plagesHoraires
								.get(i + 1).getLivraisons().get(k)
								.getDestinataire().getNoeudAdresse()
								.getIdNoeud()));
					}
				}

				// Successeurs entre livraisons de la derniere plage horaire et
				// l'entrepot
				if (i == plagesHoraires.size() - 1) {
					succLivraison.add(nodeToChoco.get(entrepot.getIdNoeud()));
				}

				succ.add(succLivraison);
			}
		}
	}

	/**
	 * Méthode qui crée la matrice de couts a l'aide de l'algorithme de Dijkstra
	 * 
	 * @param plan
	 *            Le plan de la ville
	 */
	private void calculMatriceCost(Plan plan) {
		// Création de la matrice de couts et calcul des chemins
		nbVertices = nbLivraisonsTotal + 1;

		cost = new int[nbLivraisonsTotal + 1][nbLivraisonsTotal + 1]; // +1 car
																		// l'entrepot
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[i].length; j++) {
				cost[i][j] = Integer.MAX_VALUE;
			}
		}

		chemins = new ArrayList<ArrayList<Chemin>>();

		minArcCost = -1;
		maxArcCost = 0;

		for (int i = 0; i < succ.size(); i++) {
			ArrayList<Chemin> al = new ArrayList<Chemin>();

			for (int j = 0; j < succ.get(i).size(); j++) {
				Chemin chemin = new Chemin();

				// Calcul du chemin avec Dijkstraa
				ArrayList path = Dijkstra
						.getShortPath(plan, chocoToNode.get(i),
								chocoToNode.get(succ.get(i).get(j)));

				for (int k = 0; k < path.size() - 1; k++) {
					Troncon troncon = plan.getTroncon((int) path.get(k),
							(int) path.get(k + 1));
					chemin.addTroncon(troncon);
				}

				al.add(chemin);

				// On rempli la matrice des couts
				int cout = chemin.getTemps();
				cost[i][succ.get(i).get(j)] = cout;

				if (cout > maxArcCost) {
					maxArcCost = cout;
				}
				if (cout < minArcCost || minArcCost == -1) {
					minArcCost = cout;
				}
			}

			chemins.add(al);
		}
	}

	/**
	 * S'occupe de calculer la tournee ideale avec Choco
	 * 
	 * @return Un tableau contenant pour chaque index (correspondant à une plage
	 *         horaire) un tableau de chemins ordonnés
	 */
	public Vector<Vector<Chemin>> calculerChoco() {
		TSP tsp = new TSP(this);

		SolutionState s = tsp.solve(Proprietes.CHOCO_TIMEOUT,
				this.getNbVertices() * this.getMaxArcCost() + 1);

		if (s == SolutionState.OPTIMAL_SOLUTION_FOUND
				|| s == SolutionState.SOLUTION_FOUND) {
			int[] next = tsp.getNext();
			return traitementChoco(next);
		} else {
			System.out.println("Pas de solution trouvée");
		}

		return null;
	}

	/**
	 * Méthode qui traite les resultats de Choco
	 * 
	 * @return Un tableau contenant pour chaque index (correspondant à une plage
	 *         horaire) un tableau de chemins ordonnés
	 */
	private Vector<Vector<Chemin>> traitementChoco(int[] next) {
		int[] livraisonsOrdonnees = new int[next.length + 1];
		livraisonsOrdonnees[0] = chocoToNode.get(0);
		livraisonsOrdonnees[next.length] = chocoToNode.get(0);
		int it = 0;
		int count = 0;
		while (count < next.length - 1) {
			livraisonsOrdonnees[count + 1] = chocoToNode.get(next[it]);
			count++;
			it = next[it];
		}

		Vector<Chemin> allChemins = new Vector<Chemin>();

		// Flatening chemins
		for (int i = 0; i < chemins.size(); i++) {
			for (int j = 0; j < chemins.get(i).size(); j++) {
				allChemins.add(chemins.get(i).get(j));
			}
		}

		Vector<Vector<Chemin>> cheminsClasses = new Vector<Vector<Chemin>>();
		Vector<Chemin> livraisons = new Vector<Chemin>();

		for (int i = 0; i < livraisonsOrdonnees.length - 1; i++) {
			int first = livraisonsOrdonnees[i];
			int second = livraisonsOrdonnees[i + 1];

			for (Chemin a : allChemins) {
				if (a.getListeTroncons().firstElement().getDepart()
						.getIdNoeud() == first
						&& a.getListeTroncons().lastElement().getArrivee()
								.getIdNoeud() == second) {
					livraisons.add(a);
					break;
				}
			}
		}

		int somme = 0;
		for (int j = 0; j < plagesHoraires.size(); j++) {
			PlageHoraire plage = plagesHoraires.get(j);
			int nbParPlage = (j + 1 == plagesHoraires.size()) ? plage
					.getLivraisons().size() + 1 : plage.getLivraisons().size();

			Vector<Chemin> listeCheminPlage = new Vector<Chemin>();
			for (int i = somme; i < somme + nbParPlage; i++) {
				Chemin c = livraisons.get(i);
				listeCheminPlage.add(c);
			}
			somme += nbParPlage;

			cheminsClasses.add(listeCheminPlage);
		}

		return cheminsClasses;
	}

	/**
	 * @return L'arc ayant le plus grand cout
	 */
	public int getMaxArcCost() {
		return maxArcCost;
	}

	/**
	 * @return L'arc ayant le plus petit cout
	 */
	public int getMinArcCost() {
		return minArcCost;
	}

	/**
	 * @return Le nombre de sommets du graphe
	 */
	public int getNbVertices() {
		return nbVertices;
	}

	/**
	 * @return La matrice de couts
	 */
	public int[][] getCost() {
		return cost;
	}

	/**
	 * @return La matrice de successeurs
	 */
	public int[][] getSucc() {
		int[][] succTab = new int[succ.size()][succ.size()];

		for (int i = 0; i < succ.size(); i++) {
			succTab[i] = getSucc(i);
		}

		return succTab;
	}

	public int[] getSucc(int i) throws ArrayIndexOutOfBoundsException {
		if ((i < 0) || (i >= nbVertices))
			throw new ArrayIndexOutOfBoundsException();
		int[] tab = new int[succ.get(i).size()];
		for (int j = 0; j < tab.length; j++) {
			tab[j] = succ.get(i).get(j);
		}
		return tab;
	}

	public int getNbSucc(int i) throws ArrayIndexOutOfBoundsException {
		if ((i < 0) || (i >= nbVertices))
			throw new ArrayIndexOutOfBoundsException();
		return succ.get(i).size();
	}
	
	/**
	 * Creates a graph such that each vertex is connected to the next
	 * <code>d</code> vertices (modulo <code>n</code>) and such that the cost of
	 * each arc is a randomly chosen integer ranging between <code>min</code>
	 * and <code>max</code>
	 * 
	 * @param n
	 *            a number of vertices such that <code>n > 0</code>
	 * @param d
	 *            a degree such that <code>0 < d < n</code>
	 * @param min
	 *            a minimal arc cost such that <code>0 < min</code>
	 * @param max
	 *            a maximal arc cost such that <code>min < max</code>
	 */
	public RegularGraph(int n, int d, int min, int max) {
		nbVertices = n;
		minArcCost = min;
		maxArcCost = max;
		cost = new int[nbVertices][nbVertices];
		succ = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < nbVertices; i++) {
			for (int j = 0; j < nbVertices; j++)
				cost[i][j] = maxArcCost + 1;
			ArrayList<Integer> l = new ArrayList<Integer>();
			for (int j = i + 1; j <= i + d; j++) {
				int k = j % nbVertices;
				cost[i][k] = minArcCost
						+ (int) (Math.random() * (maxArcCost - minArcCost + 1));
				l.add(k);
			}
			succ.add(i, l);
		}
	}
}
