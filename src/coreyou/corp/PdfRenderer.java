package coreyou.corp;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.SimpleRenderer;

/**
 * Example showing how to render pages of a PDF document using the high level
 * API.
 * 
 * 1.下載ghostscript 64bit，並安裝。
 * 2.設定環境變數PATH，新增ghostscript安裝資料夾下的bin資料夾
 * 3.下載ghost4j。
 * 4.Window >> Preferences >> Java >> Build Path >> User Libraries >> new 一個ghost4j-0.5.1的library，
 *   將ghost4j.jar和ghost4j/bin/下面的所有jar檔放到這一個Library中。
 * 5.將Library ghost4j-0.5.1加入build path。
 * 
 * @author Gilles Grousset (gi.grousset@gmail.com)
 */
public class PdfRenderer {
	public Logger log = Logger.getLogger(PdfRenderer.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("src/log4jConfigTest.properties");
		try {

			String rootPath = "C:" + File.separator + "Users" + File.separator + "coreyou" + File.separator + "Downloads" + File.separator;
			String pdfName = "test.pdf";
			String outputPath = rootPath + "pdfRender" + File.separator; 
			
			// load PDF document
			PDFDocument document = new PDFDocument();
			document.load(new File(rootPath + pdfName));

			// create renderer
			SimpleRenderer renderer = new SimpleRenderer();

			// set resolution (in DPI)
			renderer.setResolution(300);

			// render
			List<Image> images = renderer.render(document);
			
			File outputFolder = new File(outputPath);
			if (!outputFolder.exists()) {
				outputFolder.mkdirs();
			}

			// write images to files to disk as PNG
			try {
				for (int i = 0; i < images.size(); i++) {
					ImageIO.write((RenderedImage) images.get(i), "png",
							new File(outputPath + (i + 1) + ".png"));
				}
			} catch (IOException e) {
				System.out.println("ERROR: " + e.getMessage());
			}

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

	}
}