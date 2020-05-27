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
import javax.swing.UIManager;

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
	private JScrollPane scrollBar;
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
		this.setBackground(new Color(244, 194, 194));
		this.setTitle("Paint");
		this.setSize(950, 650);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(526,526));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.model = model;

		//Menu bar
		Color white = new Color(255, 255, 255);
		Font f = new Font("sans-serif", Font.PLAIN, 18);
		UIManager.put("Menu.font", f);
		view = new JMenu("View");
		full = new JMenuItem("Full Size");
		full.setBackground(white);
		fit = new JMenuItem("Fit to Window");
		fit.setBackground(white);
		view.add(full);
		view.add(fit);
		file = new JMenu("File");
		newFile = new JMenuItem("New");
		newFile.setBackground(white);
		saveFile = new JMenuItem("Save");
		saveFile.setBackground(white);
		loadFile = new JMenuItem("Load");
		loadFile.setBackground(white);
		fileChooser = new JFileChooser();
		file.add(newFile);
		file.add(loadFile);
		file.add(saveFile);
		bar = new JMenuBar();
		bar.add(file);
		bar.add(view);
		bar.setBackground(white);
		bar.setBounds(0, 0, 1600, 40);
		add(bar);

		//Tools
		ImageIcon moveIcon = new ImageIcon("img/move.png");
		Image moveImage = moveIcon.getImage() ;  
		Image _moveImage = moveImage.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
		move=new JButton(new ImageIcon(_moveImage));
		move.setToolTipText("move");
		move.setBackground(new Color(255,255,255));
		move.setBounds(0, 55,40 ,40);
		
		ImageIcon eraseIcon = new ImageIcon("img/eraser.png");
		Image eraseImage = eraseIcon.getImage() ;  
		Image _eraseImage = eraseImage.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
		eraser=new JButton(new ImageIcon(_eraseImage));
		eraser.setToolTipText("eraser");
		eraser.setBackground(new Color(255,255,255));
		eraser.setBounds(45, 55, 40, 40);
		
		ImageIcon lineIcon = new ImageIcon("img/line.png");
		Image lineImage = lineIcon.getImage() ;  
		Image _lineImage = lineImage.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
		line=new JButton(new ImageIcon(_lineImage));
		line.setBackground(new Color(255,255,255));
		line.setBounds(0, 95, 40, 40);
		
		ImageIcon circleIcon = new ImageIcon("img/circle.png");
		Image circleImage = circleIcon.getImage() ;  
		Image _circleImage = circleImage.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
		circle=new JButton(new ImageIcon(_circleImage));
		circle.setBackground(new Color(255,255,255));
		circle.setBounds(45, 95, 40, 40);
		
		ImageIcon squareIcon = new ImageIcon("img/square.png");
		Image squareImage = squareIcon.getImage() ;  
		Image _squareImage = squareImage.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
		rectangle=new JButton(new ImageIcon(_squareImage));
		rectangle.setBackground(new Color(255,255,255));
		rectangle.setBounds(0, 135, 40, 40);
		
		ImageIcon fillIcon = new ImageIcon("img/fill.png");
		Image fillImage = fillIcon.getImage() ;  
		Image _fillImage = fillImage.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
		filled=new JButton(new ImageIcon(_fillImage));
		filled.setBounds(45, 135, 40, 40);
		filled.setBackground(new Color(255,255,255));
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
		
		blueButton.setBounds(0, 185,40,40);
		//blueButton.setBorderPainted(false);
		redButton.setBounds(45, 185,40,40);
		//redButton.setBorderPainted(false);
		pinkButton.setBounds(0, 225,40,40);
		//pinkButton.setBorderPainted(false);
		yellowButton.setBounds(45, 225,40,40);
		//yellowButton.setBorderPainted(false);
		orangeButton.setBounds(0, 265,40,40);
		//orangeButton.setBorderPainted(false);
		greenButton.setBounds(45, 265,40,40);
		//greenButton.setBorderPainted(false);
		
		ImageIcon chooser = new ImageIcon("img/chooser.png");
		Image img_c = chooser.getImage() ;  
		Image newimg_c = img_c.getScaledInstance( 70, 70,  java.awt.Image.SCALE_SMOOTH ) ;  
		colorChooser=new JButton(new ImageIcon(newimg_c));
		colorChooser.setFont(new Font("Arial", Font.PLAIN, 10));
		colorChooser.setBounds(-5, 315, 95, 80);
		colorChooser.setBackground(new Color(238,238,238));
		colorChooser.setBorderPainted(false);
		colorChooser.setToolTipText("choose color");

		//Line width
		sLine = new JButton(new ImageIcon("img/s.png"));
		mLine = new JButton(new ImageIcon("img/m.png"));
		lLine = new JButton(new ImageIcon("img/l.png"));
		sLine.setBounds(0, 405,85,30);
		sLine.setBackground(new Color(238,238,238));
		sLine.setBorderPainted(false);
		mLine.setBounds(0, 445,85,30);
		mLine.setBackground(new Color(238,238,238));
		mLine.setBorderPainted(false);
		lLine.setBounds(0, 485,85,30);
		lLine.setBackground(new Color(238,238,238));
		lLine.setBorderPainted(false);

		//Extra feature - resize
		ImageIcon icon = new ImageIcon("img/resize.png");
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;  
		resize=new JButton(new ImageIcon(newimg));
		resize.setBackground(new Color(238,238,238));
		resize.setBorderPainted(false);
		resize.setToolTipText("resize");
		resize.setBounds(0, 520, 85, 70);   
		
		components = new JPanel();
		components.setLayout(null);
		components.setBounds(0, 0, 90, 850);
		components.setBackground(new Color(238,238,238));
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
		model.setBounds(100, 45, 1005, 700);
		add(model);

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
					if(!filePath.endsWith(".paint")) {
						file = new File(filePath + ".paint");
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
					if(!ext.equals("paint")){
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
