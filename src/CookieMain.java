import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class CookieMain {
	
	JLabel counterLabel, perSecLabel;
	JButton button1, button2, button3, button4;
	int cookieCounter, timerSpeed, cursorNumber, cursorPrice, grandpaNumber, grandpaPrice, grandmaNumber, grandmaPrice, oompaloompaNumber, oompaloompaPrice;
	double perSecond;
	boolean timerOn, grandpaUnlocked, grandmaUnlocked, oompaloompaUnlocked;
	Font font1, font2;
	CookieHandler cHandler = new CookieHandler();
	Timer timer;
	JTextArea messageText;
	MouseHandler mHandler = new MouseHandler();

	public static void main(String[] args) {
		
		new CookieMain();
	}
	public CookieMain() {
		
		timerOn = false;
		grandpaUnlocked = false;
		grandmaUnlocked = false;
		oompaloompaUnlocked = false;
		perSecond = 0;
		cookieCounter = 0;
		cursorNumber = 0;
		cursorPrice = 10;
		grandpaNumber = 0;
		grandpaPrice = 100;
		grandmaNumber = 0;
		grandmaPrice = 500;
		oompaloompaNumber = 0;
		oompaloompaPrice = 1000;
		
		createFont();
		createUI();
	}
	public void createFont() {
		
		// new Font("font name", font style, font size)
		font1 = new Font("Comic Sans MS", Font.PLAIN, 26);
		font2 = new Font("Comic Sans MS", Font.PLAIN, 15);
	}
	public void createUI() {
		
		// Creates entire window, sizes it, and sets default close behaviour
		JFrame window = new JFrame();
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		
		// Creates JPanel for the cookie to be placed
		JPanel cookiePanel = new JPanel();
		cookiePanel.setBounds(100, 220, 200, 200);
		cookiePanel.setBackground(Color.black);
		window.add(cookiePanel);
		
		// Creates icon using an image in CookeClicker/res
		ImageIcon cookie = new ImageIcon(getClass().getClassLoader().getResource("cookie.png"));
		
		// Create button which uses cookie image. Add ActionListener to listen for a click event (cHandler)
		JButton cookieButton = new JButton();
		cookieButton.setBackground(Color.black);
		cookieButton.setFocusPainted(false);
		cookieButton.setBorder(null);
		cookieButton.setIcon(cookie);
		cookieButton.addActionListener(cHandler);
		cookieButton.setActionCommand("cookie");
		cookiePanel.add(cookieButton);
		
		// Creates JPanel for the counter
		JPanel counterPanel = new JPanel();
		counterPanel.setBounds(100,100,200,100);
		counterPanel.setBackground(Color.black);
		counterPanel.setLayout(new GridLayout(2,1));
		window.add(counterPanel);
		
		// Sets label for the counter
		counterLabel = new JLabel(cookieCounter + " cookies");
		counterLabel.setForeground(Color.white);
		counterLabel.setFont(font1);
		counterPanel.add(counterLabel);
		
		// Sets label for cookies per second count
		perSecLabel = new JLabel();
		perSecLabel.setForeground(Color.white);
		perSecLabel.setFont(font2);
		counterPanel.add(perSecLabel);
		
		// Creates JPanel for the items
		JPanel itemPanel = new JPanel();
		itemPanel.setBounds(500, 170, 250, 250);
		itemPanel.setBackground(Color.black);
		itemPanel.setLayout(new GridLayout(4,1));
		window.add(itemPanel);
		
		button1 = new JButton("Cursor");
		button1.setFont(font1);
		button1.setFocusPainted(false);
		button1.addActionListener(cHandler);
		button1.setActionCommand("Cursor");
		button1.addMouseListener(mHandler);
		itemPanel.add(button1);
		button2 = new JButton("?");
		button2.setFont(font1);
		button2.setFocusPainted(false);
		button2.addActionListener(cHandler);
		button2.setActionCommand("Grandpa");
		button2.addMouseListener(mHandler);
		itemPanel.add(button2);
		button3 = new JButton("?");
		button3.setFont(font1);
		button3.setFocusPainted(false);
		button3.addActionListener(cHandler);
		button3.setActionCommand("Grandma");
		button3.addMouseListener(mHandler);
		itemPanel.add(button3);
		button4 = new JButton("?");
		button4.setFont(font1);
		button4.setFocusPainted(false);
		button4.addActionListener(cHandler);
		button4.setActionCommand("Oompa Loompa");
		button4.addMouseListener(mHandler);
		itemPanel.add(button4);
		
		JPanel messagePanel = new JPanel();
		messagePanel.setBounds(500,70,250,150);
		messagePanel.setBackground(Color.black);
		window.add(messagePanel);
		
		messageText = new JTextArea();
		messageText.setBounds(500,70,250,150);
		messageText.setForeground(Color.white);
		messageText.setBackground(Color.black);
		messageText.setFont(font2);
		messageText.setLineWrap(true);
		messageText.setWrapStyleWord(true);
		messageText.setEditable(false);
		messagePanel.add(messageText);
		
		window.setVisible(true);
	}
	
	public void setTimer() {
		timer = new Timer(timerSpeed, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cookieCounter++;
				counterLabel.setText(cookieCounter + " cookies");
				
				if(grandpaUnlocked==false) {
					if(cookieCounter>=100) {
						grandpaUnlocked = true;
						button2.setText("Grandpa" + "(" + grandpaNumber + ")");
					}
				}
				if(grandmaUnlocked==false) {
					if(cookieCounter>=500) {
						grandmaUnlocked = true;
						button3.setText("Grandma" + "(" + grandmaNumber + ")");
					}
				}
				if(oompaloompaUnlocked==false) {
					if(cookieCounter>=1000) {
						oompaloompaUnlocked = true;
						button4.setText("Oompa Loompa" + "(" + oompaloompaNumber + ")");
					}
				}
			}
		});
	}
	
	public void timerUpdate() {
		if(timerOn == false) {
			timerOn = true;
		}
		else if(timerOn == true) {
			timer.stop();
		}
		
		double speed = 1/perSecond*1000;
		timerSpeed = (int)Math.round(speed);

		String s = String.format("%.1f", perSecond);
		perSecLabel.setText("per second: " + s);
		
		setTimer();
		timer.start();
	}
	
	public class CookieHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			
			String action = event.getActionCommand(); // setActionCommand() returns the command name of the action event fired by this button
			
			switch(action) {
				case "cookie":
					// Increment cookieCounter & updates text each time the cookie is clicked.
					cookieCounter++;
					counterLabel.setText(cookieCounter + " cookies");
					break;
				case "Cursor":
					if(cookieCounter >= cursorPrice) {
						cookieCounter = cookieCounter - cursorPrice;
						cursorPrice = cursorPrice + 5;
						counterLabel.setText(cookieCounter + " cookies");

						cursorNumber++;
						button1.setText("Cursor" + "(" + cursorNumber + ")");
						messageText.setText("Cursor\n[price: " + cursorPrice + "]\nAutoclicks once every 10 seconds.");
						perSecond = perSecond + 0.1;
						timerUpdate();
					}
					else {
						messageText.setText("You need more cookies!");
					}
					break;
				case "Grandpa":
					if(cookieCounter >= grandpaPrice) {
						cookieCounter = cookieCounter - grandpaPrice;
						grandpaPrice = grandpaPrice + 50;
						counterLabel.setText(cookieCounter + " cookies");

						grandpaNumber++;
						button2.setText("Grandpa" + "(" + grandpaNumber + ")");
						messageText.setText("Grandpa\n[price: " + grandpaPrice + "]\nEach Grandpa produces 1 cookie per second");
						perSecond = perSecond + 1;
						timerUpdate();
					}
					else {
						messageText.setText("You need more cookies!");
					}
					break;
				case "Grandma":
					if(cookieCounter >= grandmaPrice) {
						cookieCounter = cookieCounter - grandmaPrice;
						grandmaPrice = grandmaPrice + 150;
						counterLabel.setText(cookieCounter + " cookies");

						grandmaNumber++;
						button3.setText("Grandma" + "(" + grandmaNumber + ")");
						messageText.setText("Grandma\n[price: " + grandmaPrice + "]\nEach Grandma produces 5 cookie per second");
						perSecond = perSecond + 5;
						timerUpdate();
					}
					else {
						messageText.setText("You need more cookies!");
					}
					break;
				case "Oompa Loompa":
					if(cookieCounter >= oompaloompaPrice) {
						cookieCounter = cookieCounter - oompaloompaPrice;
						oompaloompaPrice = oompaloompaPrice + 300;
						counterLabel.setText(cookieCounter + " cookies");

						oompaloompaNumber++;
						button4.setText("Oompa Loopa" + "(" + oompaloompaNumber + ")");
						messageText.setText("Oompa Loompa\n[price: " + oompaloompaPrice + "]\nEach Oompa-Loompa produces 10 cookies per second");
						perSecond = perSecond + 10;
						timerUpdate();
					}
					else {
						messageText.setText("You need more cookies!");
					}
					break;
			}

		}
	}
	public class MouseHandler implements MouseListener{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			
			JButton button = (JButton)e.getSource();
			
			if(button == button1) {
				messageText.setText("Cursor\n[price: " + cursorPrice + "]\nAutoclicks once every 10 seconds.");
			}
			else if(button == button2) {
				
				if(grandpaUnlocked==false) {
					messageText.setText("This item is currently locked!");
				}
				else {
					messageText.setText("Grandpa\n[price: " + grandpaPrice + "]\nEach Grandpa produces 1 cookie per second");
				}
			}
			else if(button == button3) {

				if(grandmaUnlocked==false) {
					messageText.setText("This item is currently locked!");
				}
				else {
					messageText.setText("Grandma\n[price: " + grandmaPrice + "]\nEach Grandma produces 5 cookies per second");
				}
			}
			else if(button == button4) {
				if(oompaloompaUnlocked==false) {
					messageText.setText("This item is currently locked!");
				}
				else {
					messageText.setText("Oompa Loopa\n[price: " + oompaloompaPrice + "]\nEach Oompa Loopa produces 10 cookies per second");
				}
			}
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
			JButton button = (JButton)e.getSource();
			
			if(button == button1) {
				messageText.setText(null);
			}
			else if(button == button2) {
				messageText.setText(null);
			}
			else if(button == button3) {
				messageText.setText(null);
			}
			else if(button == button4) {
				messageText.setText(null);
			}
		}
	}

}
