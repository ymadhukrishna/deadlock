package deadlock;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.TextUI;
import javax.swing.plaf.basic.BasicFormattedTextFieldUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.View;
import javax.swing.text.Position.Bias;

public class Swing {

	public static void main(String[] args) {
		JFrame jframe = new JFrame("Test Frame");
		final JPanel jpanel = new JPanel();
		final JTextField	textfield = new JTextField("helo");
		JButton jbutton = new JButton("click");
		jframe.getContentPane().add(jpanel,BorderLayout.CENTER);
		
		jframe.getContentPane().add(jbutton,BorderLayout.SOUTH);
		
		
		
		
		
		jbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("inside action perfomed****");
				
				// TODO Auto-generated method stub
				jpanel.setToolTipText("deepika");
//				textfield.setVisible(true);
			}
		});
		
		
//		jpanel.addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyTyped(KeyEvent arg0) {
//				char text = 'A';
//				arg0.setKeyChar(text);
//				
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			
//			
//			
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
		jframe.setSize(300,300);
		jframe.setVisible(true);

	}

}
