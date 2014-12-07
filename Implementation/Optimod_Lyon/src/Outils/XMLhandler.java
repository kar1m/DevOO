/**
 * 
 */
package Outils;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.xml.sax.SAXException;

/**
 * Classe utilitaire de gestion de fichiers XML
 * @author Yassine Moreno
 *
 */
public class XMLhandler {
	/**
	 * Selection de fichier XML avec JfileChooser
	 * @return path du fichier selectionne
	 */
	public static String selectXML()
	{
    	JFileChooser jFileChooserXML = new JFileChooser();
    	FileFilterApp filter = new FileFilterApp();
    	
        filter.addExtension("xml");
        filter.setDescription("Fichier XML");
        jFileChooserXML.setFileFilter(filter);
        jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal;
        	returnVal = jFileChooserXML.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) 
        		return jFileChooserXML.getSelectedFile().getAbsolutePath();
        return null;
	}
	
		/**
		 * methode de verification syntaxique et semantique des fichiers XML
		 * @param input fichier a verifier
		 * @param modeleXSD modele XSD
		 * @return vrai si fichier conforme, faux sinon
		 * @throws SAXException SaxException
		 * @throws IOException IOException
		 */
		public static boolean  checkXML (String input, String modeleXSD) throws SAXException, IOException {
			 SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		        
		        URL schema = Thread.currentThread().getContextClassLoader().getResource(modeleXSD); 
		        Schema compiledSchema = factory.newSchema(schema);

		        Validator validator = compiledSchema.newValidator();
		        Source source = new StreamSource(input);
	        
	        // Valider la structure du fichier XML
	            try {
					validator.validate(source);
					return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new SAXException();
				}
	            
		}

}
