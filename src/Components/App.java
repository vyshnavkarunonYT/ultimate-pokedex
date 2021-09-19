package Components;

import java.awt.Color;
import java.awt.EventQueue;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.border.Border;

import Accessories.Pokemon;
import Accessories.TextToSpeech;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.AWTEvent;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JButton;

public class App {

	// Sub Components
	static RoundedButton closeButton, identifyButton, refreshButton;

	// Design Variables
	Color POKEDEX_RED = new Color(204, 0, 0);
	Color POKEDEX_BLUE = new Color(59, 76, 202);
	Color POKEDEX_YELLOW = new Color(255, 222, 0);
	Color TRANSPARENT = new Color(0, 0, 0, 0);
	Color POKEDEX_DISPLAY_BACKGROUND = new Color(240, 240, 250);

	Border blackBorder = BorderFactory.createLineBorder(Color.black);

	// ELements
	private static TitlePanel titlePanel;
	private static DisplayPanel displayPanel;

	private static JFrame frame;
	private static ImagePanel detailsHolderPanel;
	private JPanel detailsTitlePanel;

	private static JLabel lblPokedexNumber;
	private static JLabel lblPokemonName;
	private static ImagePanel pokemonImagePanel;
	private static ImagePanel typeOneImagePanel;
	private static ImagePanel typeTwoImagePanel;
	private JLabel lblRegionKey;
	private static JLabel lblRegionVal;
	private JLabel lblHeightKey;
	private static JLabel lblHeightVal;
	private JLabel lblWeightKey;
	private static JLabel lblWeightVal;
	private static JPanel miscDetailsCardsHolder;
	private static JLabel lblClassification;
	
	

	
	private JLabel lblAbilities;
	private static JLabel lblAbilityOneKey;
	private static JLabel lblAbilityOneVal;
	private static JLabel lblAbilityTwoVal;
	private static JLabel lblAbilityTwoKey;
	private static JTextField pokemonSearchInput;
	private static PickerButton btnAbilitiesCardPicker;
	private static PickerButton btnEvolutionsCardPicker;
	private static PickerButton btnGrowthsCardPicker;

	// Pokemon
	static Pokemon pokemon;

	// Voice variables
	static TextToSpeech tts;

	// Type Image Variables
	static int typeImageSW = 666;
	static int typeImageSH = 333;
	static String[] typeNames = { "bug", "dark", "dragon", "electric", "fairy", "fighting", "fire", "flying", "ghost",
			"grass", "ground", "ice", "normal", "poison", "psychic", "rock", "steel", "water" };

	static String[] availableCardBackgroundImages = { "grass", "fire", "electric", "water", "ice", "dragon", "flying",
			"ghost", "steel", "psychic", "rock", "bug" };
	private JPanel growthsCard;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public App() throws IOException {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {

		// Initialize Voice

		Runnable r = new Runnable() {
			public void run() {
				tts = new TextToSpeech();
				tts.setVoice("dfki-poppy-hsmm");
			}
		};

		new Thread(r).start();

		/*
		 * Frame Initialization Initializes the a transparent frame on top of which the
		 * title panel, display panel and footer panel sit.
		 */
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);

		// Making the frame background transparent
		frame.setShape(new RoundRectangle2D.Double(0, 0, 300, 500, 30, 30));
		frame.setBackground(TRANSPARENT);

		/*
		 * Initializing the display panel with a transparent background. The display
		 * panel displays the main content of the app and contains the details holder
		 * panel which in turn contains the pokemon image panel and the pokemon data
		 * cards container.
		 */
		displayPanel = new DisplayPanel(frame);
		displayPanel.setBounds(0, 70, 300, 370);
		displayPanel.setBackground(TRANSPARENT);
		frame.getContentPane().add(displayPanel);

		displayPanel.setFocusable(true);
		displayPanel.setFocusTraversalKeysEnabled(false);
		displayPanel.setLayout(null);

		/*
		 * Initializing the footer panel. This contains a search box to search for
		 * pokemon in the pokedex.
		 */
		MotionPanel footerPanel = new FooterPanel(frame);
		footerPanel.setBounds(0, 440, 300, 60);
		frame.getContentPane().add(footerPanel);
		footerPanel.setLayout(null);

		/*
		 * Initializing the search bar in the footer panel.
		 * 
		 */
		pokemonSearchInput = new JTextField();
		pokemonSearchInput.setToolTipText("Enter Pokemon Name");
		pokemonSearchInput.setFont(new Font("Tahoma", Font.BOLD, 16));
		pokemonSearchInput.setForeground(SystemColor.text);
		pokemonSearchInput.setBackground(new Color(35, 35, 35));
		pokemonSearchInput.setBounds(80, 15, 140, 30);
		footerPanel.add(pokemonSearchInput);
		pokemonSearchInput.setColumns(10);
		ImageIcon pokedexIcon = new ImageIcon(new ImageIcon("./resources/buttons/pokedexIcon.jpg").getImage()
				.getScaledInstance(30, 30, Image.SCALE_DEFAULT));

		/*
		 * Initializing the display panel background which will be of the same
		 * texture/appearance as the type of the pokemon
		 */
		Image cardBackgroundImage = ImageIO.read(new File("./resources/cardBackgrounds/grassBackground.jpg"));
		detailsHolderPanel = new ImagePanel(cardBackgroundImage, 0, 70, 300, 300);
		detailsHolderPanel.setBounds(10, 0, 280, 370);
		detailsHolderPanel.setLayout(null);
		detailsHolderPanel.setGlassLayerEnabled(true);

		// displayPanel.add(detailsHolderPanel);

		/*
		 * Panel which holds the pokemon name label and the pokedex number label of the
		 * pokemon
		 */
		detailsTitlePanel = new JPanel();
		detailsTitlePanel.setBackground(TRANSPARENT);
		detailsTitlePanel.setBounds(10, 10, 260, 40);
		detailsHolderPanel.add(detailsTitlePanel);
		detailsTitlePanel.setBorder(blackBorder);
		detailsTitlePanel.setLayout(null);

		/*
		 * Label that displays the pokedex number.
		 */
		lblPokedexNumber = new JLabel("");
		lblPokedexNumber.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPokedexNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblPokedexNumber.setBounds(10, 5, 35, 30);
		detailsTitlePanel.add(lblPokedexNumber);

		/*
		 * Label that displays the pokemon name.
		 */
		lblPokemonName = new JLabel("Bulbasaur");
		lblPokemonName.setFont(new Font("Yu Gothic", Font.BOLD, 20));
		lblPokemonName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPokemonName.setBounds(10, 5, 240, 30);
		detailsTitlePanel.add(lblPokemonName);

		/*
		 * Panel that contains the pokemon image
		 */
		Image pokemonImage = ImageIO.read(new File("./resources/pokemon_jpg/1.jpg"));
		pokemonImagePanel = new ImagePanel(pokemonImage);
		pokemonImagePanel.setBounds(10, 60, 115, 115);

		detailsHolderPanel.add(pokemonImagePanel);

		// Initializing the Image that contains pokemon types.
		Image typeImage = ImageIO.read(new File("./resources/cardBackgrounds/pokemonTypesTransparentBackgrounds.png"));

		/*
		 * Initialzing type one Image Panel which contains image of primary type of the
		 * pokemon
		 */
		typeOneImagePanel = new ImagePanel(typeImage, 80, 1055, 540, 200);
		typeOneImagePanel.setBackground(TRANSPARENT);
		typeOneImagePanel.setBounds(140, 65, 60, 25);
		detailsHolderPanel.add(typeOneImagePanel);

		/*
		 * Initializing type two Image panel which contains the image of the secondary
		 * type of the pokemon
		 */
		typeTwoImagePanel = new ImagePanel(typeImage, 735, 1390, 540, 200);
		typeTwoImagePanel.setBackground(TRANSPARENT);
		typeTwoImagePanel.setBounds(210, 65, 60, 25);
		detailsHolderPanel.add(typeTwoImagePanel);

		/*
		 * Label that contains the literal value "Region"
		 */
		lblRegionKey = new JLabel(" Region");
		lblRegionKey.setBounds(140, 100, 60, 20);
		detailsHolderPanel.add(lblRegionKey);

		/*
		 * Label that contains the value of region of the pokemon
		 */
		lblRegionVal = new JLabel("Kanto");
		lblRegionVal.setBounds(210, 100, 60, 20);
		detailsHolderPanel.add(lblRegionVal);

		/*
		 * Label that contains the literal value of "Height"
		 */
		lblHeightKey = new JLabel(" Height");
		lblHeightKey.setBounds(140, 130, 60, 20);
		detailsHolderPanel.add(lblHeightKey);

		/*
		 * Label that contains the value of the height of the pokemon
		 */
		lblHeightVal = new JLabel("6");
		lblHeightVal.setBounds(210, 130, 60, 20);
		detailsHolderPanel.add(lblHeightVal);

		/*
		 * Label that contains the literal value of "weight"
		 */
		lblWeightKey = new JLabel(" Weight");
		lblWeightKey.setBounds(140, 160, 60, 20);
		detailsHolderPanel.add(lblWeightKey);

		/*
		 * Label that contains the value of weight of the pokemon
		 */
		lblWeightVal = new JLabel("8");
		lblWeightVal.setBounds(210, 160, 60, 20);
		detailsHolderPanel.add(lblWeightVal);

		/*
		 * A panel with card layout that holds all cards with the misc data of the
		 * pokemon
		 */
		miscDetailsCardsHolder = new JPanel();
		miscDetailsCardsHolder.setBorder(blackBorder);
		miscDetailsCardsHolder.setBounds(10, 210, 260, 155);
		miscDetailsCardsHolder.setBackground(TRANSPARENT);
		detailsHolderPanel.add(miscDetailsCardsHolder);
		miscDetailsCardsHolder.setLayout(new CardLayout());

		// The primary card on the miscDetailsCardsHolder panel that displays the
		// abilities of the pokemon
		JPanel abilitiesCard = new JPanel();
		miscDetailsCardsHolder.add(abilitiesCard, "A");
		abilitiesCard.setBackground(TRANSPARENT);
		abilitiesCard.setLayout(null);

		// Label that contains the title of the abilities card with the literal value of
		// "Abilities"
		lblAbilities = new JLabel("<html><u>Abilities</u></html>");
		lblAbilities.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblAbilities.setBounds(10, 5, 100, 20);
		abilitiesCard.add(lblAbilities);

		// Label that contains the title of the primary ability of the pokemon
		lblAbilityOneKey = new JLabel("Chlorophyll");
		lblAbilityOneKey.setBounds(10, 35, 240, 20);
		abilitiesCard.add(lblAbilityOneKey);

		// Label that contains a brief explanation/definition of the primary ability
		lblAbilityOneVal = new JLabel("Boosts grass type moves in harsh sunlight.");
		lblAbilityOneVal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAbilityOneVal.setBounds(10, 55, 240, 30);
		abilitiesCard.add(lblAbilityOneVal);

		// Label that contains a brief explanation/definition of the secondary ability
		// of the pokemon
		lblAbilityTwoVal = new JLabel(
				"<html>Powers up Grass-type moves when the <br> Pok\u00E9mon's HP is low.</html>");
		lblAbilityTwoVal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAbilityTwoVal.setBounds(10, 110, 240, 30);
		abilitiesCard.add(lblAbilityTwoVal);

		// Label that contains the title of the secondary ability of the pokemon
		lblAbilityTwoKey = new JLabel("Overgrowth");
		lblAbilityTwoKey.setBounds(10, 90, 240, 20);
		abilitiesCard.add(lblAbilityTwoKey);
		
		
		
		
		//Creating and adding the growths card
		
		growthsCard = new JPanel();
		miscDetailsCardsHolder.add(growthsCard, "G");
		growthsCard.setBackground(TRANSPARENT);
		growthsCard.setLayout(null);
		
		JLabel growthsLabel = new JLabel("<html><u>Growths</u></html>");
		growthsLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		growthsLabel.setBounds(10, 5, 100, 20);
		growthsCard.add(growthsLabel);
		
		
		//Creating and adding the abilities card
		
		JPanel evolutionsCard = new JPanel();
		miscDetailsCardsHolder.add(evolutionsCard, "E");
		evolutionsCard.setBackground(null);
		evolutionsCard.setLayout(null);
		
		JLabel lblevolutions = new JLabel("<html><u>Evolutions</u></html>");
		lblevolutions.setBounds(10, 5, 100, 20);
		lblevolutions.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		evolutionsCard.add(lblevolutions);
		
		
		
		//Adding all the cards to MiscDetailsCardsHolder panel.
		

		
		
		
		
		
		
	
		
		/*
		 * Label that contains a brief summary of the pokemon
		 */
		lblClassification = new JLabel("Seed Pokemon");
		lblClassification.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblClassification.setHorizontalAlignment(SwingConstants.CENTER);
		lblClassification.setBounds(10, 175, 120, 20);
		detailsHolderPanel.add(lblClassification);

		// A panel that holds round buttons that help user switch between misc data
		// cards
		JPanel miscDataPickerPanel = new JPanel();
		miscDataPickerPanel.setBounds(175, 200, 110, 20);
		detailsHolderPanel.add(miscDataPickerPanel);
		miscDataPickerPanel.setBackground(TRANSPARENT);

		btnAbilitiesCardPicker = new PickerButton("A", pokemon == null ? "null" : pokemon.primary_type,
				true);
		btnAbilitiesCardPicker.setFont(new Font("Tahoma", Font.PLAIN, 5));
		btnAbilitiesCardPicker.setBounds(20, 0, 20, 20);
		btnAbilitiesCardPicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				
				
				((CardLayout) miscDetailsCardsHolder.getLayout()).show(miscDetailsCardsHolder, "A");
				
				btnGrowthsCardPicker.setActivated(false);
				btnAbilitiesCardPicker.setActivated(true);
				btnEvolutionsCardPicker.setActivated(false);
				frame.repaint();
			}
		});
		miscDataPickerPanel.setLayout(null);
		miscDataPickerPanel.add(btnAbilitiesCardPicker);

		btnEvolutionsCardPicker = new PickerButton("E", pokemon == null ? "null" : pokemon.primary_type, false);
		btnEvolutionsCardPicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				((CardLayout) miscDetailsCardsHolder.getLayout()).show(miscDetailsCardsHolder, "E");
				
				
				btnGrowthsCardPicker.setActivated(false);
				btnAbilitiesCardPicker.setActivated(false);
				btnEvolutionsCardPicker.setActivated(true);
				frame.repaint();
			}
		});
		btnEvolutionsCardPicker.setFont(new Font("Tahoma", Font.PLAIN, 5));
		btnEvolutionsCardPicker.setBounds(45, 0, 20, 20);
		miscDataPickerPanel.add(btnEvolutionsCardPicker);

		btnGrowthsCardPicker = new PickerButton("G", pokemon == null ? "null" : pokemon.primary_type, false);
		btnGrowthsCardPicker.setFont(new Font("Tahoma", Font.PLAIN, 5));
		btnGrowthsCardPicker.setBounds(70, 0, 20, 20);
		btnGrowthsCardPicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				((CardLayout) miscDetailsCardsHolder.getLayout()).show(miscDetailsCardsHolder, "G");
				
				btnGrowthsCardPicker.setActivated(true);
				btnAbilitiesCardPicker.setActivated(false);
				btnEvolutionsCardPicker.setActivated(false);
				frame.repaint();
			}
		});
		miscDataPickerPanel.add(btnGrowthsCardPicker);

		/*
		 * The topmost panel that sits on the frame that contains the main control
		 * buttons such as search, refresh and close.
		 */
		titlePanel = new TitlePanel(frame);
		titlePanel.setBackground(TRANSPARENT);
		titlePanel.setBounds(0, 0, 300, 70);
		frame.getContentPane().add(titlePanel);
		titlePanel.setLayout(null);

		/*
		 * Search button - Large blue circular button that performs the function of
		 * either text based search or image based search on the pokedex
		 */
		Image searchButtonImage = ImageIO
				.read(new File("E:\\YouTube\\Eclipse\\UltimateDex\\resources\\buttons\\searchButton.png"));
		identifyButton = new RoundedButton(50, searchButtonImage);
		identifyButton.setBounds(10, 5, 50, 50);
		titlePanel.add(identifyButton);
		identifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchPokemon();
			}
		});

		/*
		 * Close button - button that closes the pokedex.
		 */
		Image closeButtonImage = ImageIO
				.read(new File("E:\\YouTube\\Eclipse\\UltimateDex\\resources\\buttons\\closeButton.png"));
		closeButton = new RoundedButton(25, closeButtonImage);
		closeButton.setBounds(105, 10, 25, 25);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		titlePanel.add(closeButton);

		/*
		 * Refresh button - button that refreshes the screen to a transparent frame.
		 */
		Image refreshButtonImage = ImageIO
				.read(new File("E:\\YouTube\\Eclipse\\UltimateDex\\resources\\buttons\\refreshButton.png"));
		refreshButton = new RoundedButton(25, refreshButtonImage);
		refreshButton.setBounds(70, 10, 25, 25);
		titlePanel.add(refreshButton);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				titlePanel.deactivateDisplay();
				displayPanel.deactivateDisplay();
				displayPanel.removeAll();
				frame.getContentPane().repaint();
			}
		});

		displayPanel.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	
	
	public static void searchPokemon() {


		if (pokemonSearchInput.getText().length() == 0) {
			System.out.println("Image Based Search");
		} else {
			System.out.println("Text Based Search");
			getPokemonWithName(pokemonSearchInput.getText());

		}

		// TODO Auto-generated method stub
		titlePanel.activateDisplay();
		displayPanel.activateDisplay();
		frame.getContentPane().repaint();

		// Open pokemon data display panel
		displayPanel.add(detailsHolderPanel);
		detailsHolderPanel.add(miscDetailsCardsHolder);
		

		// Audio - Reads out basic details about the pokemon
		Runnable r = new Runnable() {
			public void run() {
				String say = pokemon.name + " . " + pokemon.classification + " . " + pokemon.name + " is a "
						+ pokemon.primary_type
						+ (pokemon.secondary_type.length() > 0 ? "and " + pokemon.secondary_type : "")
						+ " type pokemohn. Found" + " in the " + pokemon.region
						+ " region. It is effective against " + getTypeStrength(pokemon.primary_type)
						+ " type pokemohn. It has the " + pokemon.primary_ability + " and "
						+ pokemon.secondary_ability + " abilities.";

				tts.speak(say, 1.0f, false, false);
			}
			
			
			//Used to return the names of types the pokemon's type is effective against.
			private String getTypeStrength(String primary_type) {
				// TODO Auto-generated method stub
				String output = "";
				primary_type = primary_type.trim().toLowerCase();
				if (primary_type.equals("fire")) {
					return "grass ";
				} else if (primary_type.equals("grass")) {
					return "water ";
				} else if (primary_type.equals("water")) {
					return "fire ";
				}

				return null;
			}

		};
		
		
		//Setting the color of the pickerButtons
		btnEvolutionsCardPicker.setColorWithType(pokemon.primary_type);
		btnAbilitiesCardPicker.setColorWithType(pokemon.primary_type);
		btnGrowthsCardPicker.setColorWithType(pokemon.primary_type);
		
		
		
		
		
		new Thread(r).start();
		
		

		((CardLayout) miscDetailsCardsHolder.getLayout()).next(miscDetailsCardsHolder);
		((CardLayout) miscDetailsCardsHolder.getLayout()).next(miscDetailsCardsHolder);
		((CardLayout) miscDetailsCardsHolder.getLayout()).next(miscDetailsCardsHolder);
		
		btnGrowthsCardPicker.setActivated(false);
		btnAbilitiesCardPicker.setActivated(true);
		btnEvolutionsCardPicker.setActivated(false);
		frame.repaint();
	}

	public static void getPokemonWithName(String name) {

		// Getting and reading CSV file
		String path = "./data/PokemonDatabaseCopy.csv";
		String line = "";

		int count = 1;
		int POKEDEX_MAX_COUNT = 1605;

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			while ((line = br.readLine()) != null && count <= POKEDEX_MAX_COUNT) {
				String[] vals = line.split(",");
				// System.out.println(Arrays.toString(vals));
				// System.out.println(count+" "+vals[2]);
				if (count > 1 && checkNameMatch(name, vals[2])) {
					// System.out.println("MATCHED DATA IS " + line);
					updatePokedexDisplayData(vals);
					break;
				}
				count++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updatePokedexDisplayData(String[] data) {
		System.out.println(Arrays.toString(data));
		String pokedexNumber = data[1];
		String name = getSplicedVal(data[2]);
		String classification = getSplicedVal(data[3]);
		String primaryType = getSplicedVal(data[9]);
		String secondaryType = getSplicedVal(data[10]);
		String primaryAbility = getSplicedVal(data[11]);
		String primaryAbilityVal = getSplicedVal(data[12]);
		String height = data[7];
		String weight = data[8];

		String secondaryAbility = "";
		String secondaryAbilityVal = "";

		if (!getSplicedVal(data[13]).equals("")) {
			secondaryAbility = getSplicedVal(data[13]);
			secondaryAbilityVal = getSplicedVal(data[14]);
		} else {
			secondaryAbility = getSplicedVal(data[15]);
			secondaryAbilityVal = getSplicedVal(data[16]);
		}

		String region = getSplicedVal(data[23]);

		pokemon = new Pokemon(pokedexNumber, name, classification, primaryType, secondaryType, height, weight,
				primaryAbility, primaryAbilityVal, secondaryAbility, secondaryAbilityVal, region);

		pokemon.printData();

		// Setting App elements values

//		System.out.println("POKEDEX NUMBER IS " + lblPokedexNumber);

		lblPokemonName.setText(name);
		// lblPokedexNumber.setText(pokedexNumber);
		lblAbilityOneKey.setText(primaryAbility);
		lblAbilityTwoKey.setText(secondaryAbility);
		lblAbilityOneVal.setText(getFormattedDisplayString(primaryAbilityVal));
		lblAbilityTwoVal.setText(getFormattedDisplayString(secondaryAbilityVal));
		lblClassification.setText(getFormattedDisplayString(classification));
		lblRegionVal.setText(region);
		lblHeightVal.setText(height);
		lblWeightVal.setText(weight);

		Image pokemonImage = null;
		Image typeImage = null;
		Image cardBackgroundImage = null;

		try {
			cardBackgroundImage = ImageIO
					.read(new File("./resources/cardBackgrounds/" + getCardBackground(primaryType) + "Background.jpg"));
			pokemonImage = ImageIO.read(new File("./resources/pokemon_jpg/" + pokedexNumber + ".jpg"));
			typeImage = ImageIO.read(new File("./resources/cardBackgrounds/pokemonTypesTransparentBackgrounds.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pokemonImagePanel.setImage(pokemonImage);

		int pTypeIndex = getTypeMappedIndex(primaryType);
		int vertPadding = 50;
		int horPadding = 80;

		if (pTypeIndex == -1) {
			typeOneImagePanel.setImage(null);
		} else {
//			System.out.println("pTypeIndex is " + pTypeIndex);
//			System.out.println("SX is " + (pTypeIndex % 3) * typeImageSW);
//			System.out.println("SY is " + (pTypeIndex / 3) * typeImageSH);

			typeOneImagePanel.setImage(typeImage, (pTypeIndex % 3) * typeImageSW + horPadding,
					(pTypeIndex / 3) * typeImageSH + vertPadding, (int) (typeImageSW - horPadding * 1.9),
					(int) (typeImageSH - vertPadding * 2.25));

			detailsHolderPanel.setImage(cardBackgroundImage, 0, 70, 300, 300);

			titlePanel.setCardBackgroundImage(cardBackgroundImage);
		}

		int sTypeIndex = getTypeMappedIndex(secondaryType);
		if (sTypeIndex == -1) {
			typeTwoImagePanel.setImage(null);
		} else {
//			System.out.println("sTypeIndex is " + sTypeIndex);
//			System.out.println("SX is " + (sTypeIndex % 3) * typeImageSW);
//			System.out.println("SY is " + (sTypeIndex / 3) * typeImageSH);

			typeTwoImagePanel.setImage(typeImage, (sTypeIndex % 3) * typeImageSW + horPadding,
					(sTypeIndex / 3) * typeImageSH + vertPadding, (int) (typeImageSW - horPadding * 1.9),
					(int) (typeImageSH - vertPadding * 2.25));
		}

	}

	private static String getCardBackground(String type) {
		type = type.trim().toLowerCase();

		for (int i = 0; i < availableCardBackgroundImages.length; i++) {
			if (type.equals(availableCardBackgroundImages[i])) {
				return type;
			}
		}

		return "neutral";
	}

	public static int getTypeMappedIndex(String type) {

		type = type.trim().toLowerCase();
		for (int i = 0; i < typeNames.length; i++) {
			if (typeNames[i].equals(type)) {
				return i;
			}
		}

		// Return index -1 if type is not found in selection
		return -1;
	}

	public static String getFormattedDisplayString(String input) {
		String output = "";
		int breakLength = 40;
		if (input.length() > breakLength * 2) {
			input = input.substring(0, breakLength * 2);
		}
		if (input.length() > breakLength) {
			output = input.substring(0, breakLength) + "<br>" + input.substring(breakLength);
		} else {
			output = input;
		}

		output = "<html>" + output + "</html>";
		return output;
	}

	public static String getSplicedVal(String input) {
		if (input.equals("NULL")) {
			return "";
		}

		String output = "";
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (ch != '"') {
				output += ch;
			}
		}
		return output;
	}

	public static boolean checkNameMatch(String input, String val) {

		input = input.toLowerCase().trim();
		String splicedVal = val.substring(3, val.length() - 3).toLowerCase();

		// System.out.println(splicedVal);
		if (input.equals(splicedVal)) {
			// System.out.println("MATCH FOUND");
			return true;
		}

		return false;
	}
}
