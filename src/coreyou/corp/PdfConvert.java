package coreyou.corp;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.ghost4j.Ghostscript;
import org.ghost4j.GhostscriptException;

/**
 * 請先參考PdfRenderer.java
 * 這個檔案提供直接下ghostscript指令來達成PDF擷圖的方法
 * 
 * @author coreyou
 *
 */
public class PdfConvert {
	public Logger log = Logger.getLogger(PdfConvert.class);

	public static void main(String[] args) {
		PropertyConfigurator.configure("src/log4jConfigTest.properties");
		
		Ghostscript gs = Ghostscript.getInstance();
		
		//prepare Ghostscript interpreter parameters
        //refer to Ghostscript documentation for parameter usage
        String[] gsArgs = new String[9];
        gsArgs[0] = "-q";
        gsArgs[1] = "-dNOPAUSE";
        gsArgs[2] = "-dBATCH";
        gsArgs[3] = "-dNOPROMPT";
        gsArgs[4] = "-sFONTDIR=c:/windows/fonts";	// fallback font dir
        gsArgs[5] = "-sDEVICE=jpeg";
        gsArgs[6] = "-sOutputFile=D:/page%d.jpg";	// output
        gsArgs[7] = "-r150";
        gsArgs[8] = "D:/test.pdf";	// input
 
        //execute and exit interpreter
        try {
 
            gs.initialize(gsArgs);
            gs.exit();
 
        } catch (GhostscriptException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
	}

}
