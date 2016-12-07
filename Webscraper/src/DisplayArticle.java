import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;

public class DisplayArticle {
	public void displayOnWindow(String Text,String Newspaper,String link,int Window_number) {
		JLabel headerLabel = new JLabel("",JLabel.CENTER);
		JLabel bodyLabel = new JLabel("",JLabel.CENTER);
		JFrame frame = new JFrame(Newspaper);
		frame.setSize(1000, 700);
		frame.setLocation(Window_number * 50, 100);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		headerLabel.setText("<html><u>"+link+"</u></html>");
		headerLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
		bodyLabel.setText("<html>"+Text+"</html>");
		bodyLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
		frame.add(headerLabel,BorderLayout.NORTH);
		frame.add(bodyLabel);
		frame.setVisible(true);
	}
}
