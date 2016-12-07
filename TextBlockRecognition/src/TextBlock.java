import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

class TextBlock {
	String fileName = new String();
	public static void main(String args[]) {
		TextBlock obj = new TextBlock();
		System.load("C:\\opencv\\build\\java\\x64\\opencv_java300.dll");
		System.out.println("Welcome to OpenCV " + Core.VERSION + ", lib is: " + Core.NATIVE_LIBRARY_NAME);
		String userDir = System.getProperty("user.home");
		JFileChooser fileChooser = new JFileChooser(userDir +"/Desktop");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(filter);
		int res = fileChooser.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    obj.fileName = selectedFile.getAbsolutePath();
		}
		if(obj.fileName.isEmpty()) {
			System.out.println("File not Chosen");
			System.exit(0);
		}
		System.out.println("Chosen File : " + obj.fileName);
		Mat image = Imgcodecs.imread(obj.fileName);
		Mat gray_image = new Mat(image.height(),image.width(),CvType.CV_8UC1);
		Mat BlackAndWhite = new Mat(image.height(),image.width(),CvType.CV_8UC1);
		Mat SharpBnW = new Mat(image.height(),image.width(),CvType.CV_8UC1);
		Mat Eroded = new Mat(image.height(),image.width(),CvType.CV_8UC1);
		Mat Inverted = new Mat(image.height(),image.width(),CvType.CV_8UC1);
		Imgproc.cvtColor(image, gray_image, Imgproc.COLOR_RGB2GRAY);
		Imgproc.threshold(gray_image,BlackAndWhite,127,255,Imgproc.THRESH_BINARY|Imgproc.THRESH_OTSU);
		Imgproc.bilateralFilter(BlackAndWhite, SharpBnW, 9, 75, 75);
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(20,3));
        Imgproc.erode(SharpBnW, Eroded, element);
        Imgproc.threshold(Eroded, Inverted, 200.0, 255.0, Imgproc.THRESH_BINARY_INV);
        Imgcodecs.imwrite("C:\\Users\\joyte\\Desktop\\output_eroded.jpg", Eroded);
        //Imgproc.threshold(Inverted,Inverted, 254.0, 255.0, Imgproc.THRESH_BINARY);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(Inverted, contours, new Mat(), Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_SIMPLE);
        String[] Headline = new String[contours.size()];
        OCR ob = new OCR();
        int k = 0;
        Rect[] rect = new Rect[contours.size()];
        for(int i=0; i< contours.size();i++){
            Imgproc.contourArea(contours.get(i));
            if (Imgproc.contourArea(contours.get(i)) > 50 ){
                rect[i] = Imgproc.boundingRect(contours.get(i));
                if (rect[i].height > 80 && rect[i].height< 300 && rect[i].height*2 < rect[i].width ){
		            Imgcodecs.imwrite("C:\\Users\\joyte\\Desktop\\output.jpg", new Mat(SharpBnW, rect[i]));
		            Imgproc.rectangle(image, new Point(rect[i].x,rect[i].y), new Point(rect[i].x+rect[i].width,rect[i].y+rect[i].height),new Scalar(255,0,0),3);
		            String result = ob.call_ocr();
		            if(result!=null)
		            	Headline[k++] = result;
                }
            }
        }
        String[]  final_headline = new String[k];
        for (int i = 0; Headline[i]!=null; i++) {
    		final_headline[i] = Headline[k-i-1];
    		System.out.print(final_headline[i]);
        }
		Imgcodecs.imwrite("C:\\Users\\joyte\\Desktop\\output_new.jpg", image);
		System.out.println("Done");
	}
}