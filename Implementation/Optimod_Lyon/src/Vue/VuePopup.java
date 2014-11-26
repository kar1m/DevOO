package Vue;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class VuePopup extends JPopupMenu {

	public VuePopup(boolean livraison)
	{
		JMenuItem a = new JMenuItem("Inserer Point Livraison"); 
		JMenuItem b = new JMenuItem("Supprimer Point Livraison");
		add(a);
		add(b);
		
		a.setEnabled( !livraison );
		b.setEnabled( livraison );

	}
}
