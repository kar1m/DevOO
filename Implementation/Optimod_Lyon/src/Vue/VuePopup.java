package Vue;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
/**
 * Vue correspondant au pop up pour avoir plus d'information sur un noeud
 * @author Mehdi Kitane
 *
 */
public class VuePopup extends JPopupMenu {

	private JMenuItem a; 
	private JMenuItem b; 
	private JMenuItem c;
	public VuePopup(boolean livraison)
	{
		a = new JMenuItem("Inserer Point Livraison"); 
		b = new JMenuItem("Supprimer Point Livraison");
		c = new JMenuItem("Information sur la livraison");
		add(a);
		add(b);
		add(c);
		a.setEnabled( !livraison );
		b.setEnabled( livraison );
		c.setEnabled( livraison );
	}

	public JMenuItem getA() {
		return a;
	}

	public JMenuItem getB() {
		return b;
	}
	public JMenuItem getC() {
		return c;
	}
}
