package src;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//This View file used the concept of MVC from the started code that is provided.
@SuppressWarnings("serial")
public class View extends JFrame  {
	Model model;
	private JMenuBar bar;
	private JMenu view;
	private JMenuItem full;
	private JMenuItem fit;
	private JMenu file;
	private JMenuItem newFile;
	private JMenuItem saveFile;
	private JMenuItem loadFile;
	private JFileChooser fileChooser;
	private JPanel components;
	private JScrollPane scrollBar;//OPPA
	public JButton circle, rectangle, line, eraser, filled, move, resize, colorChooser;

	//Color buttons
	private JButton blueButton;
	private JButton redButton;
	private JButton pinkButton;
	private JButton yellowButton;
	private JButton greenButton;
	private JButton orangeButton;

	//Line width buttons
	private JButton sLine;
	private JButton mLine;
	private JButton lLine;

	public View(Model model) {
		this.setTitle("A MVC JSketch program");
		this.setSize(950, 650);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(526,526));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.model = model;

		//Menu bar
		view = new JMenu("View");
		full = new JMenuItem("Full Size");
		fit = new JMenuItem("Fit to Window");
		view.add(full);
		view.add(fit);
		file = new JMenu("File");
		newFile = new JMenuItem("New");
		saveFile = new JMenuItem("Save");
		loadFile = new JMenuItem("Load");
		fileChooser = new JFileChooser();
		file.add(newFile);
		file.add(loadFile);
		file.add(saveFile);
		bar = new JMenuBar();
		bar.add(file);
		bar.add(view);
		bar.setBounds(0, 0, 1600, 20);
		add(bar);

		//Tools
		move=new JButton(new ImageIcon("img/move.png"));
		move.setToolTipText("move");
		move.setBounds(0, 25,40 ,40);
		eraser=new JButton(new ImageIcon("img/eraser.png"));
		eraser.setToolTipText("eraser");
		eraser.setBounds(45, 25, 40, 40);
		line=new JButton(new ImageIcon("img/line.png"));
		line.setBounds(0, 65, 40, 40);
		circle=new JButton(new ImageIcon("img/circle.png"));
		circle.setBounds(45, 65, 40, 40);
		rectangle=new JButton(new ImageIcon("img/rectangle.png"));
		rectangle.setBounds(0, 105, 40, 40);
		filled=new JButton(new ImageIcon("img/fill.png"));
		filled.setBounds(45, 105, 40, 40);
		filled.setToolTipText("fill color");

		//Colors
		blueButton = new JButton(new ImageIcon("img/blue.png"));
		blueButton.setBackground(Color.BLUE);
		redButton = new JButton(new ImageIcon("img/red.png"));
		redButton.setBackground(Color.RED);
		pinkButton = new JButton(new ImageIcon("img/pink.png"));
		pinkButton.setBackground(Color.PINK);
		yellowButton = new JButton(new ImageIcon("img/yellow.png"));
		yellowButton.setBackground(Color.YELLOW);
		orangeButton = new JButton(new ImageIcon("img/orange.png"));
		orangeButton.setBackground(Color.ORANGE);
		greenButton = new JButton(new ImageIcon("img/green.jpg"));
		
		greenButton.setBackground(Color.GREEN);
		blueButton.setBounds(0, 155,40,40);
		redButton.setBounds(45, 155,40,40);
		pinkButton.setBounds(0, 195,40,40);
		yellowButton.setBounds(45, 195,40,40);
		orangeButton.setBounds(0, 235,40,40);
		greenButton.setBounds(45, 235,40,40);
		colorChooser=new JButton("Chooser");
		colorChooser.setFont(new Font("Arial", Font.PLAIN, 10));
		colorChooser.setBounds(0, 285, 85, 40);
		colorChooser.setToolTipText("choose color");

		//Line width
		sLine = new JButton(new ImageIcon("img/s.png"));
		mLine = new JButton(new ImageIcon("img/m.png"));
		lLine = new JButton(new ImageIcon("img/l.png"));
		sLine.setBounds(0, 335,85,40);
		mLine.setBounds(0, 375,85,40);
		lLine.setBounds(0, 415,85,40);

		//Extra feature - resize
		resize=new JButton(new ImageIcon("img/resize.png"));
		resize.setToolTipText("resize");
		resize.setBounds(0, 465, 85, 40);     

		components = new JPanel();
		components.setLayout(null);
		components.setBounds(0, 0, 90, 850);
		components.add(line);
		components.add(rectangle);
		components.add(circle);
		components.add(blueButton);
		components.add(redButton);
		components.add(pinkButton);
		components.add(yellowButton);
		components.add(orangeButton);
		components.add(greenButton);
		components.add(sLine);
		components.add(mLine);
		components.add(lLine);
		components.add(move);
		components.add(eraser);
		components.add(resize);
		components.add(colorChooser);
		components.add(filled);
		add(components);
		model.setBounds(105, 25, 1005, 700);
		add(model);

		//Scrollbar
		/*
		scrollBar = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setPreferredSize(new Dimension(450, 110));
		add(scrollBar, BorderLayout.CENTER);
		getContentPane().add(scrollBar);
		*/
		newFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.clear();
			}
		});

		saveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rv = fileChooser.showSaveDialog(null);
				if (rv == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String filePath = file.getAbsolutePath();
					if(!filePath.endsWith(".png")) {
						file = new File(filePath + ".png");
					}
					try {
						FileOutputStream fileStream = new FileOutputStream(file);
						ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
						objectStream.writeObject(model.getImageList());
						objectStream.close();
					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		loadFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rv = fileChooser.showOpenDialog(null);
				if (rv == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
					if(!ext.equals("png")){
						JOptionPane.showMessageDialog(null, "This is not a png file, please choose a .png file");
					}
					else {
						try {
							FileInputStream fileStream = new FileInputStream(file);
							ObjectInputStream objectStream = new ObjectInputStream(fileStream);
							@SuppressWarnings("unchecked")
							ArrayList<Shape> result = (ArrayList<Shape>) objectStream.readObject();
							model.loadImages(result);
							objectStream.close();
						} catch (FileNotFoundException ex) {
							ex.printStackTrace();
						} catch (IOException ex) {
							ex.printStackTrace();
						} catch (ClassNotFoundException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});

		move.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				model.flag = 1;	
				move.setBorder(
						BorderFactory.createLineBorder(Color.BLACK, 3));
				resize.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				filled.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				eraser.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				circle.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				rectangle.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				line.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));

			}
		});

		filled.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.flag = 2;
				model.isFilled = true;
				filled.setBorder(
						BorderFactory.createLineBorder(Color.BLACK, 3));
				resize.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				move.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				eraser.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				circle.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				rectangle.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				line.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
			}
		});

		eraser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				model.flag = 3;
				eraser.setBorder(
						BorderFactory.createLineBorder(Color.BLACK, 3));
				resize.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				move.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				filled.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				circle.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				rectangle.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				line.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
			}
		});

		circle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.flag = 4;
				model.isFilled = false;
				circle.setBorder(
						BorderFactory.createLineBorder(Color.BLACK, 3));
				resize.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				move.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				filled.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				eraser.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				rectangle.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				line.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
			}
		});

		rectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.flag = 5;
				model.isFilled = false;
				rectangle.setBorder(
						BorderFactory.createLineBorder(Color.BLACK, 3));
				resize.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				move.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				filled.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				circle.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				eraser.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				line.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));	
			}
		});

		line.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.flag = 6;
				line.setBorder(
						BorderFactory.createLineBorder(Color.BLACK, 3));
				resize.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				move.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				filled.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				circle.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				rectangle.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				eraser.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
			}
		});

		colorChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.flag = 7;
				model.color = JColorChooser.showDialog(model, "Choose a color",
						model.color);
			}
		});
		
		String[] buttons = {"Purple", "White", "Cyan"};
		
		blueButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) { // if right click
					model.flag = 7;
					int colorChoice = JOptionPane.showOptionDialog(null, "Which color would you like to add? [Choose 'Chooser' button for more colors.]", "Color Palette - Editor's pick",
					        JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[2]);
					if (colorChoice == 0){
						blueButton.setIcon(new ImageIcon("img/purple.png"));
						blueButton.setBackground(Color.MAGENTA);
						model.color = Color.MAGENTA;
					} else if (colorChoice == 1) {
						blueButton.setIcon(new ImageIcon("img/white.jpg"));
						blueButton.setBackground(Color.WHITE);
						model.color = Color.WHITE;	
					} else if (colorChoice == 2) {
						blueButton.setIcon(new ImageIcon("img/cyan.jpg"));
						blueButton.setBackground(Color.CYAN);
						model.color = Color.CYAN;
					}
					filled.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					
				} else {
					model.color = blueButton.getBackground();
					yellowButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					blueButton.setBorder(
							BorderFactory.createLineBorder(Color.BLACK, 3));
					redButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					pinkButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					orangeButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					greenButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
				}
			}
		});

		redButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) { // if right click
					model.flag = 7;
					int colorChoice = JOptionPane.showOptionDialog(null, "Which color would you like to add? [Choose 'Chooser' button for more colors.]", "Color Palette - Editor's pick",
					        JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[2]);
					if (colorChoice == 0){
						redButton.setIcon(new ImageIcon("img/purple.png"));
						redButton.setBackground(Color.MAGENTA);
						model.color = Color.MAGENTA;
					} else if (colorChoice == 1) {
						redButton.setIcon(new ImageIcon("img/white.jpg"));
						redButton.setBackground(Color.WHITE);
						model.color = Color.WHITE;	
					} else if (colorChoice == 2) {
						redButton.setIcon(new ImageIcon("img/cyan.jpg"));
						redButton.setBackground(Color.CYAN);
						model.color = Color.CYAN;
					}
					filled.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					
				} else {
					model.color = redButton.getBackground();
					yellowButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					blueButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					redButton.setBorder(
							BorderFactory.createLineBorder(Color.BLACK, 3));
					pinkButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					orangeButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					greenButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
				}
			}
		});

		pinkButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) { // if right click
					model.flag = 7;
					int colorChoice = JOptionPane.showOptionDialog(null, "Which color would you like to add? [Choose 'Chooser' button for more colors.]", "Color Palette - Editor's pick",
					        JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[2]);
					if (colorChoice == 0){
						pinkButton.setIcon(new ImageIcon("img/purple.png"));
						pinkButton.setBackground(Color.MAGENTA);
						model.color = Color.MAGENTA;
					} else if (colorChoice == 1) {
						pinkButton.setIcon(new ImageIcon("img/white.jpg"));
						pinkButton.setBackground(Color.WHITE);
						model.color = Color.WHITE;	
					} else if (colorChoice == 2) {
						pinkButton.setIcon(new ImageIcon("img/cyan.jpg"));
						pinkButton.setBackground(Color.CYAN);
						model.color = Color.CYAN;
					}
					filled.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					
				} else {
					model.color = pinkButton.getBackground();
					yellowButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					blueButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					redButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					pinkButton.setBorder(
							BorderFactory.createLineBorder(Color.BLACK, 3));
					orangeButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					greenButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));


				}
			}
		});

		yellowButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) { // if right click
					model.flag = 7;
					int colorChoice = JOptionPane.showOptionDialog(null, "Which color would you like to add? [Choose 'Chooser' button for more colors.]", "Color Palette - Editor's pick",
					        JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[2]);
					if (colorChoice == 0){
						yellowButton.setIcon(new ImageIcon("img/purple.png"));
						yellowButton.setBackground(Color.MAGENTA);
						model.color = Color.MAGENTA;
					} else if (colorChoice == 1) {
						yellowButton.setIcon(new ImageIcon("img/white.jpg"));
						yellowButton.setBackground(Color.WHITE);
						model.color = Color.WHITE;	
					} else if (colorChoice == 2) {
						yellowButton.setIcon(new ImageIcon("img/cyan.jpg"));
						yellowButton.setBackground(Color.CYAN);
						model.color = Color.CYAN;
					}
					filled.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					
				} else {
					model.color = yellowButton.getBackground();
					yellowButton.setBorder(
							BorderFactory.createLineBorder(Color.BLACK, 3));
					blueButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					redButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					pinkButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					orangeButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					greenButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
				}
			}
		});

		orangeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) { // if right click
					model.flag = 7;
					int colorChoice = JOptionPane.showOptionDialog(null, "Which color would you like to add? [Choose 'Chooser' button for more colors.]", "Color Palette - Editor's pick",
					        JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[2]);
					if (colorChoice == 0){
						orangeButton.setIcon(new ImageIcon("img/purple.png"));
						orangeButton.setBackground(Color.MAGENTA);
						model.color = Color.MAGENTA;
					} else if (colorChoice == 1) {
						orangeButton.setIcon(new ImageIcon("img/white.jpg"));
						orangeButton.setBackground(Color.WHITE);
						model.color = Color.WHITE;	
					} else if (colorChoice == 2) {
						orangeButton.setIcon(new ImageIcon("img/cyan.jpg"));
						orangeButton.setBackground(Color.CYAN);
						model.color = Color.CYAN;
					}
					filled.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					
				} else {
					model.color = orangeButton.getBackground();
					yellowButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					blueButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					redButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					pinkButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					orangeButton.setBorder(
							BorderFactory.createLineBorder(Color.BLACK, 3));
					greenButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
				}
			}
		});

		greenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) { // if right click
					model.flag = 7;
					int colorChoice = JOptionPane.showOptionDialog(null, "Which color would you like to add? [Choose 'Chooser' button for more colors.]", "Color Palette - Editor's pick",
					        JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[2]);
					if (colorChoice == 0){
						greenButton.setIcon(new ImageIcon("img/purple.png"));
						greenButton.setBackground(Color.MAGENTA);
						model.color = Color.MAGENTA;
					} else if (colorChoice == 1) {
						greenButton.setIcon(new ImageIcon("img/white.jpg"));
						greenButton.setBackground(Color.WHITE);
						model.color = Color.WHITE;	
					} else if (colorChoice == 2) {
						greenButton.setIcon(new ImageIcon("img/cyan.jpg"));
						greenButton.setBackground(Color.CYAN);
						model.color = Color.CYAN;
					}
					filled.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					
				} else {
					model.color = greenButton.getBackground();
					yellowButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					blueButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					redButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					pinkButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					orangeButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					greenButton.setBorder(
							BorderFactory.createLineBorder(Color.BLACK, 3));
				}
			}
		});

		sLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.lineWidthChoice = 0;	
				sLine.setBorder(
						BorderFactory.createLineBorder(Color.BLACK, 3));
				mLine.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				lLine.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
			}
		});

		mLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.lineWidthChoice = 1;
				mLine.setBorder(
						BorderFactory.createLineBorder(Color.BLACK, 3));
				sLine.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				lLine.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
			}
		});

		lLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.lineWidthChoice = 2;
				lLine.setBorder(
						BorderFactory.createLineBorder(Color.BLACK, 3));
				mLine.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				sLine.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
			}
		});
		
		resize.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				model.flag = 8;
				resize.setBorder(
						BorderFactory.createLineBorder(Color.BLACK, 3));
				rectangle.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				move.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				filled.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				circle.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				eraser.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
				line.setBorder(
						BorderFactory.createLineBorder(Color.GRAY, 1));
			}
		});


		// ESC key to deselect shape
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE && e.getID() == KeyEvent.KEY_PRESSED) {
					resize.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					rectangle.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					move.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					filled.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					circle.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					eraser.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					line.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					sLine.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					mLine.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					lLine.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					yellowButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					blueButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					redButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					pinkButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					orangeButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
					greenButton.setBorder(
							BorderFactory.createLineBorder(Color.GRAY, 1));
				}
				return false;
			}
		});
	}

}
