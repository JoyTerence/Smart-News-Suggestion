import java.io.File;
import net.sourceforge.tess4j.*;

public class OCR {
	String result = new String();
    public String call_ocr() {
        // System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");

    	File imageFile = new File("C:\\Users\\joyte\\Desktop\\output.jpg");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        //File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Maven build bundles English data
        instance.setDatapath("C:\\Users\\joyte\\Downloads\\Tess4J-3.2.1-src\\Tess4J");

        try {
             result = instance.doOCR(imageFile);
            
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
}