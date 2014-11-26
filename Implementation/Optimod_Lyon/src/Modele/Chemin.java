package Modele;

import java.util.Vector;

import solver.ResolutionPolicy;
import solver.Solver;
import solver.constraints.IntConstraintFactory;
import solver.search.loop.monitors.SearchMonitorFactory;
import solver.search.strategy.IntStrategyFactory;
import solver.variables.IntVar;
import solver.variables.VariableFactory;

/**
 * 
 */
public class Chemin {

	private Livraison origine;
    private Livraison destination;
    private Vector<Troncon> listeTroncons;
    private Graph graph;
    /**
     * 
     */
    public Chemin() {
    }


}