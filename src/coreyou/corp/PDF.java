package coreyou.corp;

import java.awt.Color;
import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 * PDF生成
 */
public class PDF {

	public static void buidPDF(String pdfFile, String imageFile,
			String waterMarkName, int permission) {
		try {
			File file = File.createTempFile("tempFile", ".pdf");

			// 生成PDF
//			if (createPDFFile(file)) {
				waterMark(file.getPath(), imageFile, pdfFile, waterMarkName, permission); // 添加浮水印
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readPDF() throws Exception {
//		FileInputStream in = new FileInputStream(new File("C:/Users/coreyou/Downloads/test.pdf"));
//		int xx = in.read();
//		while(xx!=-1) { 
//			System.out.print((char)xx);
//			xx = in.read();
//		}
		PdfReader pdfReader = new PdfReader("C:/05102.pdf");
		ByteArrayOutputStream buffOutputStream = new ByteArrayOutputStream();
	    PdfStamper pdfStamper = new PdfStamper(pdfReader, buffOutputStream);
	}

	/**
	 * 建PDF文件
	 * 
	 * @param file 臨時文件
	 * @return 成功/失敗
	 */
	public static boolean createPDFFile(File file) {
		Rectangle rect = new Rectangle(PageSize.A4);
		Document doc = new Document(rect, 50.0F, 50.0F, 50.0F, 50.0F);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(file));
			doc.open();

			BaseFont bf = BaseFont.createFont("C:\\WINDOWS\\Fonts\\KAIU.TTF", BaseFont.IDENTITY_H,  BaseFont.NOT_EMBEDDED);

			Font font = new Font(bf, 14.0F, 0);
//			font.setStyle(37); // 設定樣式
//			font.setFamily("宋体"); // 設定字體

			Paragraph p = new Paragraph("測試段落\r\n", font);
			p.setAlignment(1);
			doc.add(p);
			doc.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 有一些PDF在new PdfReader的時候就失敗，有一些字型會讀不到
	 * @param inputFile
	 * @param imageFile
	 * @param outputFile
	 * @param waterMarkName
	 * @param permission
	 */
	public static void waterMark(String inputFile, String imageFile,
			String outputFile, String waterMarkName, int permission) {
		try {
			// 來源檔案
			PdfReader reader = new PdfReader("C:/Users/coreyou/Downloads/test.pdf");
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));
			Rectangle rectangle = reader.getPageSize(1);
			float docWidth = rectangle.getWidth();
			float height = rectangle.getHeight();

			// 中文字型
//			BaseFont base = BaseFont.createFont("MHei-Medium", "UniCNS-UCS2-H",
//					BaseFont.NOT_EMBEDDED);
			// BaseFont base = BaseFont.createFont("Helvetica", BaseFont.WINANSI,
			// BaseFont.NOT_EMBEDDED);
//			BaseFont base = BaseFont.createFont("MHei-Medium", "UniCNS-UCS2-H", BaseFont.NOT_EMBEDDED); // 預設字型
			BaseFont base = BaseFont.createFont("C:\\WINDOWS\\Fonts\\KAIU.TTF", BaseFont.IDENTITY_H,  BaseFont.NOT_EMBEDDED);

			int total = reader.getNumberOfPages() + 1;
			Image image = Image.getInstance(imageFile);

			// 圖片位置
			image.setAbsolutePosition(400, 480);
			PdfContentByte under;
			int j = waterMarkName.length();
			char c = 0;
			int rise = 0;
			for (int i = 1; i < total; i++) {
				rise = 400;
				under = stamper.getUnderContent(i);
				under.beginText();
				under.setFontAndSize(base, 30);
				under.setColorFill(BaseColor.LIGHT_GRAY);
				// 置中對齊、要印的文字、起始x軸、起始y軸、旋轉角度
//			    under.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 150, 400, 45);
//			    under.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 350, 400, 45);
//			    under.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 550, 400, 45);
//			    under.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 750, 400, 45);
//			    under.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 50, 200, 45);
//			    under.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 250, 200, 45);
//			    under.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 450, 200, 45);
//			    under.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 650, 200, 45);
				under.showTextAligned(Element.ALIGN_CENTER, waterMarkName,
						docWidth / 2, height / 2, 45);
				under.showTextAligned(Element.ALIGN_CENTER, waterMarkName,
						docWidth / 4, 3 * height / 4, 45);
				under.showTextAligned(Element.ALIGN_CENTER, waterMarkName,
						3 * docWidth / 4, height / 4, 45);

				// 浮水印文字
				under.endText();

				// 浮水印圖片
				under.addImage(image);

				// 畫圈
				under.ellipse(250, 450, 350, 550);
				under.setLineWidth(1f);
				under.stroke();
			}
			stamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String imageFilePath = "C:/Users/coreyou/Downloads/bussinessTime.jpg"; // 浮水印圖片路徑
		String pdfFilePath = "C:/Users/coreyou/Downloads/test2.pdf"; // 文件生成路徑
		buidPDF(pdfFilePath, imageFilePath, "浮水印文字內容", 16);
	}
}