package Vue;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class VuePopup extends JPopupMenu {

	private JMenuItem a; 
	private JMenuItem b; 
	
	public VuePopup(boolean livraison)
	{
		a = new JMenuItem("Inserer Point Livraison"); 
		b = new JMenuItem("Supprimer Point Livraison");
		add(a);
		add(b);
		
		a.setEnabled( !livraison );
		b.setEnabled( livraison );

	}

	public JMenuItem getA() {
		return a;
	}

	public JMenuItem getB() {
		return b;
	}
}
