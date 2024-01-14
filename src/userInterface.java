import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;


public class userInterface extends JFrame {
	
	//creation of various variables needed
	private String[] flavorArr = {"none", "Pepsi", "Diet Pepsi", "Orange", "Diet Orange", "Root Beer", "Diet Root Beer", "Sierra Mist", "Lemonade"};
	private String[] sizeArr = {"none", "small", "medium", "large"};
	private int flavorCounter = 0;
	private int sizeCounter = 0;
	private JPanel cartPanel = new JPanel(), aboutUsPanel = new JPanel(), menuPanel = new JPanel(), orderingPanel = new JPanel(), ActiveCartPanel = new JPanel(),
			GUIPanel = new JPanel(), ActiveCheckoutPanel = new JPanel(), sidesPanel = new JPanel(), signInPanel = new JPanel(), createAccountPanel = new JPanel(),
			accountSettingsPanel = new JPanel();
	private boolean redirection = false;
	JLabel cheeseCheck = new JLabel(""), pepCheck = new JLabel(""), sausCheck = new JLabel(""), 
			onionCheck = new JLabel(""), mushCheck = new JLabel(""), tomCheck = new JLabel(""), 
			hamCheck = new JLabel(""), pineCheck = new JLabel(""), GrePepCheck = new JLabel(""), viewOrders = new JLabel(),
			totalAmountText = new JLabel("0"), subTotalAmount = new JLabel("0"), taxAmountText = new JLabel("0");
	JCheckBox smallButton = new JCheckBox(), mediumButton = new JCheckBox(), 
			largeButton = new JCheckBox(), xLargeButton = new JCheckBox();
	JCheckBox thinCheck = new JCheckBox("Thin"), regCheck = new JCheckBox("Regular"), panCheck = new JCheckBox("Pan");
	private CardLayout cardLayout = new CardLayout();
	private String crust;
	private String size;
	private LinkedList<String> toppings = new LinkedList<String>();
	//COLORS
	Color backgroundColor = new Color(243,236,212);
	Color headerColor = new Color(102,0,0);
	Color textBoxColor = new Color(152,0,0);
	private JTextField breadstickBiteAmount, cookieAmount, usernameField, passwordField, accountUsername, accountPassword, 
	enterAddressField, enterEmailField, enterPhoneField, emailField, paymentField, addressField, lastNameField, firstNameField, enterPaymentField;
	
	
	static Database liveDatabase = new Database();
	static Customer currentSession;
	static Ticket overrallOrders = new Ticket();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//liveDatabase.retrieveCustomerData();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userInterface frame = new userInterface();
					
					frame.setSize(800,650);
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public userInterface() {

		
		super("Mom and Pop's Pizzeria");
		setResizable(false);

		GUIPanel.setLayout(cardLayout);
		GUIPanel.add(cartPanel, "cartPanel");
		GUIPanel.add(menuPanel, "menuPanel");
		GUIPanel.add(aboutUsPanel, "aboutUsPanel");
		GUIPanel.add(orderingPanel, "orderingPanel");
		GUIPanel.add(signInPanel, "signInPanel");
		GUIPanel.add(sidesPanel, "sidesPanel");
		GUIPanel.add(createAccountPanel, "createAccountPanel");
		GUIPanel.add(accountSettingsPanel, "accountSettingsPanel");
		setContentPane(GUIPanel);
		
		cardLayout.show(GUIPanel, "menuPanel");
		
		createCart();
		createMenu();
		createAboutUs();
		createOrdering();
		createSides();
		createSignIn();
		createAccount();
		createAccountSettings();

		cartPanel.setLayout(null);
		menuPanel.setLayout(null);
		aboutUsPanel.setLayout(null);
		sidesPanel.setLayout(null);
		signInPanel.setLayout(null);
		createAccountPanel.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public void createCart() {

		
//heading
		JButton aboutUsButton, menuButton, orderButton, signInButton;
		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(null);
		panelHeader.setBounds(0, 0, 784, 100);
		panelHeader.setBackground(headerColor);
		
		
		//adds mom and pops logo
		JLabel logoLabel = new JLabel("New label");
		ImageIcon logoPhoto = new ImageIcon(this.getClass().getResource("/logo.png"));
		logoLabel.setIcon(logoPhoto);
		logoLabel.setBounds(0, 0, 100, 100);
		panelHeader.add(logoLabel);
		
		//button creations
		aboutUsButton = new JButton("About Us");
		aboutUsButton.setBounds(492, 60, 108, 29);
		aboutUsButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		menuButton = new JButton("Menu");
		menuButton.setBounds(149, 60, 108, 29);
		menuButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		orderButton = new JButton("Order");
		orderButton.setBounds(323, 60, 108, 29);
		orderButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		signInButton = new JButton("Account");
		signInButton.setBounds(666, 60, 108, 29);
		signInButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		//Actions triggered by button presses
		aboutUsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "aboutUsPanel");
			}
		});
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "menuPanel");
			}
		});
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "orderingPanel");
			}
		});
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "signInPanel");
			}
		});
		
		//adds buttons to the other screens
		panelHeader.add(aboutUsButton);
		panelHeader.add(menuButton);
		panelHeader.add(orderButton);
		panelHeader.add(signInButton);
		cartPanel.add(panelHeader);
		
//body
		cartPanel.setBackground(backgroundColor);
		//cart view
		ActiveCartPanel.setLayout(null);
		
		
		viewOrders.setBounds(0, 0, 448, 387);
		viewOrders.setFont(new Font("Arial", Font.PLAIN, 18));
		ActiveCartPanel.add(viewOrders);
		
		//creates the scrollpane and adds the contentPane to it
				JScrollPane cartscrollPane = new JScrollPane(ActiveCartPanel, 
						ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				
				cartscrollPane.setBounds(20, 120,450,400);
				//scrollPane.setMinimumSize(new Dimension(160,200));
				ActiveCartPanel.setPreferredSize(new Dimension(800,250));
				cartscrollPane.setViewportView(ActiveCartPanel);
				cartscrollPane.getViewport().setPreferredSize(new Dimension(160,250));
				cartPanel.add(cartscrollPane);
			
				
		//total cost box
				
				JLabel subTotalText = new JLabel("Sub Total:      $");
				subTotalText.setVerticalAlignment(SwingConstants.TOP);
				subTotalText.setAlignmentX(SwingConstants.LEFT);
				subTotalText.setFont(new Font("Arial", Font.BOLD, 20));
				subTotalText.setBounds(39, 571, 152, 30);
				subTotalText.setForeground(Color.WHITE);
				cartPanel.add(subTotalText);
				JLabel taxText = new JLabel("Tax 6%:       $");
				taxText.setVerticalAlignment(SwingConstants.TOP);
				taxText.setAlignmentX(SwingConstants.LEFT);
				taxText.setFont(new Font("Arial", Font.BOLD, 20));
				taxText.setBounds(57, 624, 130, 30);
				taxText.setForeground(Color.WHITE);
				cartPanel.add(taxText);
				JLabel totalText = new JLabel("Total Cost:     $");
				totalText.setVerticalAlignment(SwingConstants.TOP);
				totalText.setAlignmentX(SwingConstants.LEFT);
				totalText.setFont(new Font("Arial", Font.BOLD, 20));
				totalText.setBounds(39, 685, 152, 30);
				totalText.setForeground(Color.WHITE);
				cartPanel.add(totalText);
				
				
				subTotalAmount.setVerticalAlignment(SwingConstants.TOP);
				subTotalAmount.setAlignmentX(SwingConstants.LEFT);
				subTotalAmount.setFont(new Font("Arial", Font.BOLD, 20));
				subTotalAmount.setBounds(190, 571, 152, 30);
				subTotalAmount.setForeground(Color.WHITE);
				cartPanel.add(subTotalAmount);
				
				taxAmountText.setVerticalAlignment(SwingConstants.TOP);
				taxAmountText.setAlignmentX(SwingConstants.LEFT);
				taxAmountText.setFont(new Font("Arial", Font.BOLD, 20));
				taxAmountText.setBounds(190, 624, 130, 30);
				taxAmountText.setForeground(Color.WHITE);
				cartPanel.add(taxAmountText);
				
				
				totalAmountText.setVerticalAlignment(SwingConstants.TOP);
				totalAmountText.setAlignmentX(SwingConstants.LEFT);
				totalAmountText.setFont(new Font("Arial", Font.BOLD, 20));
				totalAmountText.setBounds(190, 685, 152, 30);
				totalAmountText.setForeground(Color.WHITE);
				cartPanel.add(totalAmountText);
				
				JLabel costBackground = new JLabel();
				costBackground.setVerticalAlignment(SwingConstants.CENTER);
				costBackground.setAlignmentX(SwingConstants.LEFT);
				costBackground.setBackground(new Color(152, 0, 0));
				costBackground.setForeground(Color.WHITE);
				costBackground.setOpaque(true);
				costBackground.setFont(new Font("Arial", Font.BOLD, 24));
				costBackground.setBounds(20, 541, 450, 200);
				cartPanel.add(costBackground);	
				
		//checkout view
			JButton PizzaButton = new JButton("Add a new Pizza");
			PizzaButton.setHorizontalTextPosition(SwingConstants.CENTER);
			PizzaButton.setVerticalTextPosition(SwingConstants.CENTER);
			PizzaButton.setForeground(Color.WHITE);
			PizzaButton.setOpaque(true);
			PizzaButton.setBackground(textBoxColor);
			PizzaButton.setFont(new Font("Arial", Font.BOLD, 18));
			PizzaButton.setBounds(500, 170, 220, 50);
			cartPanel.add(PizzaButton);
			
			JButton SidesButton = new JButton("Add a side");
			SidesButton.setHorizontalTextPosition(SwingConstants.CENTER);
			SidesButton.setVerticalTextPosition(SwingConstants.CENTER);
			SidesButton.setForeground(Color.WHITE);
			SidesButton.setOpaque(true);
			SidesButton.setBackground(textBoxColor);
			SidesButton.setFont(new Font("Arial", Font.BOLD, 18));
			SidesButton.setBounds(500, 240, 220, 50);
			cartPanel.add(SidesButton);
				
			JButton guestCheckoutButton = new JButton("<html> continue to checkout <br> as guest </html>");
			guestCheckoutButton.setHorizontalTextPosition(SwingConstants.CENTER);
			guestCheckoutButton.setVerticalTextPosition(SwingConstants.CENTER);
			guestCheckoutButton.setForeground(Color.WHITE);
			guestCheckoutButton.setOpaque(true);
			guestCheckoutButton.setBackground(textBoxColor);
			guestCheckoutButton.setFont(new Font("Arial", Font.BOLD, 18));
			guestCheckoutButton.setBounds(500, 400, 220, 50);
			cartPanel.add(guestCheckoutButton);
			
			JButton signInCheckoutButton = new JButton("<html> sign in then <br> continue to checkout </html>");
			signInCheckoutButton.setHorizontalTextPosition(SwingConstants.CENTER);
			signInCheckoutButton.setVerticalTextPosition(SwingConstants.CENTER);
			signInCheckoutButton.setForeground(Color.WHITE);
			signInCheckoutButton.setOpaque(true);
			signInCheckoutButton.setBackground(textBoxColor);
			signInCheckoutButton.setFont(new Font("Arial", Font.BOLD, 18));
			signInCheckoutButton.setBounds(500, 470, 220, 50);
			cartPanel.add(signInCheckoutButton);
			
			guestCheckoutButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					signInCheckoutButton.setVisible(false);
					guestCheckoutButton.setVisible(false);
					SidesButton.setVisible(false);
					PizzaButton.setVisible(false);
					buildCheckoutPane(false);
				}
			});
			signInCheckoutButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					redirection = true;
					signInCheckoutButton.setVisible(false);
					guestCheckoutButton.setVisible(false);
					SidesButton.setVisible(false);
					PizzaButton.setVisible(false);
					cardLayout.show(GUIPanel, "signInPanel");
					
				}
			});
			SidesButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//add sides to the cart
					cardLayout.show(GUIPanel, "sidesPanel");
				}
			});
			
			PizzaButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					refreshOrdering();
					cardLayout.show(GUIPanel, "orderingPanel");
				}
			});
			
	}
	public void createAccount() {
//header	
		JButton menuButton, orderButton, signInButton, aboutUsButton;
		JPanel panelHeader7 = new JPanel();
		panelHeader7.setLayout(null);
		panelHeader7.setBounds(0, 0, 784, 100);
		panelHeader7.setBackground(headerColor);
		
		
		//adds mom and pops logo
		JLabel logoLabel = new JLabel("New label");
		ImageIcon logoPhoto = new ImageIcon(this.getClass().getResource("/logo.png"));
		logoLabel.setIcon(logoPhoto);
		logoLabel.setBounds(0, 0, 100, 100);
		panelHeader7.add(logoLabel);
		
		menuButton = new JButton("Menu");
		menuButton.setBounds(149, 60, 108, 29);
		menuButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		orderButton = new JButton("Order");
		orderButton.setBounds(323, 60, 108, 29);
		orderButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		signInButton = new JButton("Account");
		signInButton.setBounds(666, 60, 108, 29);
		signInButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		aboutUsButton = new JButton("About Us");
		aboutUsButton.setBounds(492, 60, 108, 29);
		aboutUsButton.setFont(new Font("Tahoma", Font.BOLD, 16));

		
		
		//Actions triggered by button presses
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "menuPanel");
			}
		});
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "orderingPanel");
			}
		});
		aboutUsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "aboutUsPanel");
			}
		});
		panelHeader7.add(menuButton);
		panelHeader7.add(orderButton);
		panelHeader7.add(aboutUsButton);
		panelHeader7.add(signInButton);
		createAccountPanel.add(panelHeader7);
		
//body
		createAccountPanel.setBackground(backgroundColor);
		JLabel createAccountLabel = new JLabel();
		createAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createAccountLabel.setText("Create Account");
		createAccountLabel.setFont(new Font("Arial", Font.BOLD, 24));
		createAccountLabel.setBounds(283, 111, 245, 39);
		createAccountPanel.add(createAccountLabel);
		
		JLabel createUsernameLabel = new JLabel();
		createUsernameLabel.setHorizontalAlignment(SwingConstants.LEADING);
		createUsernameLabel.setText("Enter Username");
		createUsernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
		createUsernameLabel.setBounds(90, 227, 245, 39);
		createAccountPanel.add(createUsernameLabel);
		
		
		JTextField enterUsernameField = new JTextField();
		enterUsernameField.setBounds(90, 266, 264, 41);
		createAccountPanel.add(enterUsernameField);
		enterUsernameField.setColumns(10);
		
		JLabel enterPasswordLabel = new JLabel();
		enterPasswordLabel.setHorizontalAlignment(SwingConstants.LEADING);
		enterPasswordLabel.setText("Enter Password");
		enterPasswordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		enterPasswordLabel.setBounds(90, 300, 245, 39);
		createAccountPanel.add(enterPasswordLabel);
		
		JTextField enterPasswordField = new JTextField();
		enterPasswordField.setColumns(10);
		enterPasswordField.setBounds(90, 338, 264, 41);
		createAccountPanel.add(enterPasswordField);
		
		enterAddressField = new JTextField();
		enterAddressField.setColumns(10);
		enterAddressField.setBounds(90, 428, 264, 41);
		createAccountPanel.add(enterAddressField);
		
		JLabel enterAddressLabel = new JLabel();
		enterAddressLabel.setText("Enter Address");
		enterAddressLabel.setHorizontalAlignment(SwingConstants.LEADING);
		enterAddressLabel.setFont(new Font("Arial", Font.BOLD, 16));
		enterAddressLabel.setBounds(90, 390, 245, 39);
		createAccountPanel.add(enterAddressLabel);
		
		enterEmailField = new JTextField();
		enterEmailField.setColumns(10);
		enterEmailField.setBounds(446, 338, 264, 41);
		createAccountPanel.add(enterEmailField);
		
		JLabel enterEmailLabel = new JLabel();
		enterEmailLabel.setText("Enter Email");
		enterEmailLabel.setHorizontalAlignment(SwingConstants.LEADING);
		enterEmailLabel.setFont(new Font("Arial", Font.BOLD, 16));
		enterEmailLabel.setBounds(446, 300, 245, 39);
		createAccountPanel.add(enterEmailLabel);
		
		enterPhoneField = new JTextField();
		enterPhoneField.setColumns(10);
		enterPhoneField.setBounds(446, 266, 264, 41);
		createAccountPanel.add(enterPhoneField);
		
		JLabel createPhoneLabel = new JLabel();
		createPhoneLabel.setText("Enter Phone");
		createPhoneLabel.setHorizontalAlignment(SwingConstants.LEADING);
		createPhoneLabel.setFont(new Font("Arial", Font.BOLD, 16));
		createPhoneLabel.setBounds(446, 227, 245, 39);
		createAccountPanel.add(createPhoneLabel);
		
		
		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(446, 186, 264, 41);
		createAccountPanel.add(lastNameField);
		
		JLabel createLastLabel = new JLabel();
		createLastLabel.setText("Enter Last Name");
		createLastLabel.setHorizontalAlignment(SwingConstants.LEADING);
		createLastLabel.setFont(new Font("Arial", Font.BOLD, 16));
		createLastLabel.setBounds(446, 147, 245, 39);
		createAccountPanel.add(createLastLabel);
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(90, 186, 264, 41);
		createAccountPanel.add(firstNameField);
		
		JLabel createFirstLabel = new JLabel();
		createFirstLabel.setText("Enter First Name");
		createFirstLabel.setHorizontalAlignment(SwingConstants.LEADING);
		createFirstLabel.setFont(new Font("Arial", Font.BOLD, 16));
		createFirstLabel.setBounds(90, 147, 245, 39);
		createAccountPanel.add(createFirstLabel);
		
		enterPaymentField = new JTextField();
		enterPaymentField.setColumns(10);
		enterPaymentField.setBounds(446, 428, 264, 41);
		createAccountPanel.add(enterPaymentField);
		
		JLabel enterPaymentLabel = new JLabel();
		enterPaymentLabel.setText("Enter Payment");
		enterPaymentLabel.setHorizontalAlignment(SwingConstants.LEADING);
		enterPaymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
		enterPaymentLabel.setBounds(446, 390, 245, 39);
		createAccountPanel.add(enterPaymentLabel);
		accountSettingsPanel.setLayout(null);
		
		JButton finalizeAccountButton = new JButton();
		finalizeAccountButton.setText("Create Account");
		finalizeAccountButton.setHorizontalTextPosition(SwingConstants.CENTER);
		finalizeAccountButton.setVerticalTextPosition(SwingConstants.TOP);
		finalizeAccountButton.setForeground(Color.WHITE);
		finalizeAccountButton.setOpaque(true);
		finalizeAccountButton.setBackground(textBoxColor);
		finalizeAccountButton.setFont(new Font("Arial", Font.BOLD, 24));
		finalizeAccountButton.setBounds(294, 480, 221, 60);
		createAccountPanel.add(finalizeAccountButton);
		
		finalizeAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer temp = new Customer(firstNameField.getText(), lastNameField.getText(), enterPhoneField.getText(), enterEmailField.getText(), 
						enterAddressField.getText(), enterPaymentField.getText(), enterUsernameField.getText(), enterPasswordField.getText());
				liveDatabase.customerRecords.add(temp);
				liveDatabase.saveCustomerData();
				currentSession = temp;
				if(redirection == true) {
					buildCheckoutPane(true);
					cardLayout.show(GUIPanel, "cartPanel");
					redirection = false;
				} else {
					cardLayout.show(GUIPanel, "accountSettingsPanel");
				}
				
			}
		});
	}
	public void createSignIn() {
//heading
				JButton menuButton, orderButton, signInButton, aboutUsButton;
				JPanel panelHeader6 = new JPanel();
				panelHeader6.setLayout(null);
				panelHeader6.setBounds(0, 0, 784, 100);
				panelHeader6.setBackground(headerColor);
				
				
				//adds mom and pops logo
				JLabel logoLabel = new JLabel("New label");
				ImageIcon logoPhoto = new ImageIcon(this.getClass().getResource("/logo.png"));
				logoLabel.setIcon(logoPhoto);
				logoLabel.setBounds(0, 0, 100, 100);
				panelHeader6.add(logoLabel);
				
				menuButton = new JButton("Menu");
				menuButton.setBounds(149, 60, 108, 29);
				menuButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				orderButton = new JButton("Order");
				orderButton.setBounds(323, 60, 108, 29);
				orderButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				signInButton = new JButton("Account");
				signInButton.setBounds(666, 60, 108, 29);
				signInButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				
				aboutUsButton = new JButton("About Us");
				aboutUsButton.setBounds(492, 60, 108, 29);
				aboutUsButton.setFont(new Font("Tahoma", Font.BOLD, 16));

				
				
				//Actions triggered by button presses
				menuButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(GUIPanel, "menuPanel");
					}
				});
				orderButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(GUIPanel, "orderingPanel");
					}
				});
				aboutUsButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(GUIPanel, "aboutUsPanel");
					}
				});
				panelHeader6.add(menuButton);
				panelHeader6.add(orderButton);
				panelHeader6.add(aboutUsButton);
				panelHeader6.add(signInButton);
				signInPanel.add(panelHeader6);
//body
				
				JLabel accountLabel = new JLabel();
				accountLabel.setHorizontalAlignment(SwingConstants.CENTER);
				accountLabel.setText("Account Sign In");
				accountLabel.setFont(new Font("Arial", Font.BOLD, 24));
				accountLabel.setBounds(283, 111, 245, 39);
				signInPanel.add(accountLabel);
				
				JLabel usernameLabel = new JLabel();
				usernameLabel.setHorizontalAlignment(SwingConstants.LEADING);
				usernameLabel.setText("Username");
				usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
				usernameLabel.setBounds(207, 171, 245, 39);
				signInPanel.add(usernameLabel);
				
				signInPanel.setBackground(backgroundColor);
				usernameField = new JTextField();
				usernameField.setBounds(207, 210, 386, 41);
				signInPanel.add(usernameField);
				usernameField.setColumns(10);
				
				JLabel passwordLabel = new JLabel();
				passwordLabel.setHorizontalAlignment(SwingConstants.LEADING);
				passwordLabel.setText("Password");
				passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
				passwordLabel.setBounds(207, 270, 245, 39);
				signInPanel.add(passwordLabel);
				
				JLabel incorrectLabel = new JLabel();
				incorrectLabel.setHorizontalAlignment(SwingConstants.LEADING);
				incorrectLabel.setText("Incorrect Username or Password");
				incorrectLabel.setFont(new Font("Arial", Font.ITALIC, 16));
				incorrectLabel.setVisible(false);
				incorrectLabel.setBounds(270, 357, 300, 39);
				signInPanel.add(incorrectLabel);
				
				passwordField = new JTextField();
				passwordField.setColumns(10);
				passwordField.setBounds(207, 308, 386, 41);
				signInPanel.add(passwordField);
				
				JButton verifyButton = new JButton();
				verifyButton.setText("Sign In");
				verifyButton.setHorizontalTextPosition(SwingConstants.CENTER);
				verifyButton.setVerticalTextPosition(SwingConstants.TOP);
				verifyButton.setForeground(Color.WHITE);
				verifyButton.setOpaque(true);
				verifyButton.setBackground(textBoxColor);
				verifyButton.setFont(new Font("Arial", Font.BOLD, 24));
				verifyButton.setBounds(331, 402, 150, 52);
				signInPanel.add(verifyButton);
				
				JButton createAccountButton = new JButton();
				createAccountButton.setText("Create Account");
				createAccountButton.setHorizontalTextPosition(SwingConstants.CENTER);
				createAccountButton.setVerticalTextPosition(SwingConstants.TOP);
				createAccountButton.setForeground(Color.WHITE);
				createAccountButton.setOpaque(true);
				createAccountButton.setBackground(textBoxColor);
				createAccountButton.setFont(new Font("Arial", Font.BOLD, 24));
				createAccountButton.setBounds(550, 464, 221, 60);
				signInPanel.add(createAccountButton);
				
				verifyButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//if the return from the comparison isnt null, the customer object is saved as current customer
						Customer temp = liveDatabase.findCustomer(usernameField.getText(), passwordField.getText());
						//
						if (temp != null) {
							incorrectLabel.setVisible(false);
							currentSession = temp;
							if (redirection == true) {
								buildCheckoutPane(true);
								cardLayout.show(GUIPanel, "cartPanel");
								redirection = false;
							} else {
								cardLayout.show(GUIPanel, "accountSettingsPanel");
							}
						} else {
							incorrectLabel.setVisible(true);
						}
						
						
					}
				});
				createAccountButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(GUIPanel, "createAccountPanel");
					}
				});
	}
	public void createMenu() {
//Header
		JButton aboutUsButton, menuButton, orderButton, signInButton;
		JPanel panelHeader2 = new JPanel();
		panelHeader2.setLayout(null);
		panelHeader2.setBounds(0, 0, 784, 100);
		panelHeader2.setBackground(headerColor);
		
		
		//adds mom and pops logo
		JLabel logoLabel = new JLabel("New label");
		ImageIcon logoPhoto = new ImageIcon(this.getClass().getResource("/logo.png"));
		logoLabel.setIcon(logoPhoto);
		logoLabel.setBounds(0, 0, 100, 100);
		panelHeader2.add(logoLabel);
		
		//button creations
		aboutUsButton = new JButton("About Us");
		aboutUsButton.setBounds(492, 60, 108, 29);
		aboutUsButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		menuButton = new JButton("Menu");
		menuButton.setBounds(149, 60, 108, 29);
		menuButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		orderButton = new JButton("Order");
		orderButton.setBounds(323, 60, 108, 29);
		orderButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		signInButton = new JButton("Account");
		signInButton.setBounds(666, 60, 108, 29);
		signInButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		//Actions triggered by button presses
		aboutUsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "aboutUsPanel");
			}
		});
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "orderingPanel");
			}
		});
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "signInPanel");
			}
		});
		
		
		//adds buttons to the other screens
		panelHeader2.add(aboutUsButton);
		panelHeader2.add(menuButton);
		panelHeader2.add(orderButton);
		panelHeader2.add(signInButton);
		menuPanel.add(panelHeader2);
		
//body
		JPanel menuContentPanel = new JPanel();
		menuContentPanel.setLayout(null);
		menuContentPanel.setBackground(backgroundColor);
		
		JLabel txtMenu = new JLabel();
		txtMenu.setHorizontalAlignment(SwingConstants.CENTER);
		txtMenu.setText("Menu and Prices");
		txtMenu.setFont(new Font("Arial", Font.BOLD, 24));
		txtMenu.setBounds(298, 11, 200, 39);
		menuContentPanel.add(txtMenu);
		
		JLabel sizesLabel = new JLabel("Pizza Sizes");
		sizesLabel.setOpaque(true);
		sizesLabel.setForeground(Color.WHITE);
		sizesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sizesLabel.setFont(new Font("Arial", Font.BOLD, 16));
		sizesLabel.setBackground(new Color(152, 0, 0));
		sizesLabel.setBounds(77, 160, 100, 39);
		menuContentPanel.add(sizesLabel);
		
		JLabel txtSizesLabel = new JLabel();
		txtSizesLabel.setText("<html><pre>Small	$4<br>"
				+ "Medium	$6<br>"
				+ "Large	$8<br>"
				+ "X-Large	$10</pre></html>");
		txtSizesLabel.setFont(new Font("Arial", Font.BOLD, 18));
		txtSizesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		txtSizesLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtSizesLabel.setBounds(41, 210, 167, 116);
		menuContentPanel.add(txtSizesLabel);
		
		JLabel toppingsLabel = new JLabel("Pizza Toppings");
		toppingsLabel.setForeground(Color.WHITE);
		toppingsLabel.setOpaque(true);
		toppingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		toppingsLabel.setBackground(textBoxColor);
		toppingsLabel.setFont(new Font("Arial", Font.BOLD, 16));
		toppingsLabel.setBounds(506, 96, 153, 39);
		menuContentPanel.add(toppingsLabel);
		
		JLabel txtToppingsCostLabel = new JLabel();
		txtToppingsCostLabel.setText("<html><pre>Small	$0.50	Medium	$0.75<br>"
				+ "Large	$1	X-Large	$1.25</pre></html>");
		txtToppingsCostLabel.setFont(new Font("Arial", Font.BOLD, 18));
		txtToppingsCostLabel.setBounds(402, 120, 349, 95);
		menuContentPanel.add(txtToppingsCostLabel);
		
		JLabel txtToppingspanel = new JLabel();
		txtToppingspanel.setText("<html><pre>     First Topping Free! <br>"
				+ " Cheese		Green Pepper <br>"
				+ " Pineapple	Pepperoni<br>"
				+ " Onion		Sausage<br>"
				+ " Tomato				Ham<br>"
				+ " Mushroom </pre></html>"
				);
		txtToppingspanel.setFont(new Font("Arial", Font.BOLD, 18));
		txtToppingspanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtToppingspanel.setBounds(412, 205, 327, 171);
		menuContentPanel.add(txtToppingspanel);
		
		
		JLabel sidesDisplayLabel = new JLabel();
		sidesDisplayLabel.setText("<html><pre>Breadsticks		  $4<br>"
				+ "Breadstick Bites	  $2<br>"
				+ "Big Chocolate-Chip Cookie $4 </pre></html>"
				);
		sidesDisplayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sidesDisplayLabel.setFont(new Font("Arial", Font.BOLD, 18));
		sidesDisplayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sidesDisplayLabel.setBounds(200, 450, 408, 100);
		menuContentPanel.add(sidesDisplayLabel);
		
		JLabel sidesCostLabel = new JLabel("Sides");
		sidesCostLabel.setOpaque(true);
		sidesCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sidesCostLabel.setForeground(Color.WHITE);
		sidesCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
		sidesCostLabel.setBackground(new Color(152, 0, 0));
		sidesCostLabel.setBounds(200, 400, 100, 39);
		menuContentPanel.add(sidesCostLabel);
		
		
		JLabel DrinksFlavorCostLabel = new JLabel("Drinks");
		DrinksFlavorCostLabel.setOpaque(true);
		DrinksFlavorCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DrinksFlavorCostLabel.setForeground(Color.WHITE);
		DrinksFlavorCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
		DrinksFlavorCostLabel.setBackground(new Color(152, 0, 0));
		DrinksFlavorCostLabel.setBounds(200, 600, 100, 39);
		menuContentPanel.add(DrinksFlavorCostLabel);
		
		JLabel txtdrinkFlavorspanel = new JLabel();
		txtdrinkFlavorspanel.setText("<html><pre> 	$1 any size <br><br>"
				+ " Pepsi		Diet Pepsi <br>"
				+ " Orange		Diet Orange<br>"
				+ " Root Beer	Diet Root Beer<br>"
				+ " Sierra Mist	Lemonade</pre></html>"
				);
		txtdrinkFlavorspanel.setFont(new Font("Arial", Font.BOLD, 18));
		txtdrinkFlavorspanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtdrinkFlavorspanel.setBounds(200, 650, 372, 171);
		menuContentPanel.add(txtdrinkFlavorspanel);
		
		//creates the scrollpane and adds the contentPane to it
				JScrollPane scrollMenuPane = new JScrollPane(menuContentPanel, 
						ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				
				scrollMenuPane.setBounds(0, 100,784,550);
				//scrollPane.setMinimumSize(new Dimension(160,200));
				menuContentPanel.setPreferredSize(new Dimension(800,900));
				scrollMenuPane.setViewportView(menuContentPanel);
				
				
				
				
				scrollMenuPane.getViewport().setPreferredSize(new Dimension(160,200));
				menuPanel.add(scrollMenuPane);
	}
	public void createAboutUs() {

//heading
		JButton menuButton, orderButton, signInButton;
		JLabel aboutUsLabel;
		JPanel panelHeader3 = new JPanel();
		panelHeader3.setLayout(null);
		panelHeader3.setBounds(0, 0, 784, 100);
		panelHeader3.setBackground(headerColor);
		
		
		//adds mom and pops logo
		JLabel logoLabel = new JLabel("New label");
		ImageIcon logoPhoto = new ImageIcon(this.getClass().getResource("/logo.png"));
		logoLabel.setIcon(logoPhoto);
		logoLabel.setBounds(0, 0, 100, 100);
		panelHeader3.add(logoLabel);
		
		menuButton = new JButton("Menu");
		menuButton.setBounds(149, 60, 108, 29);
		menuButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		orderButton = new JButton("Order");
		orderButton.setBounds(323, 60, 108, 29);
		orderButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		signInButton = new JButton("Account");
		signInButton.setBounds(666, 60, 108, 29);
		signInButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		aboutUsLabel = new JLabel("About Us");
		aboutUsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aboutUsLabel.setBackground(headerColor);
		aboutUsLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		aboutUsLabel.setForeground(Color.WHITE);
		aboutUsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		aboutUsLabel.setBounds(487, 60, 108, 29);
		
		
		//Actions triggered by button presses
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "menuPanel");
			}
		});
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "orderingPanel");
			}
		});
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "signInPanel");
			}
		});
		panelHeader3.add(menuButton);
		panelHeader3.add(orderButton);
		panelHeader3.add(aboutUsLabel);
		panelHeader3.add(signInButton);
		aboutUsPanel.add(panelHeader3);
//body
		JLabel txtAboutUs, txtAboutuspanel;
		aboutUsPanel.setBackground(backgroundColor);
		
		txtAboutUs = new JLabel();
		txtAboutUs.setHorizontalAlignment(SwingConstants.CENTER);
		txtAboutUs.setText("About Us");
		txtAboutUs.setFont(new Font("Arial", Font.BOLD, 24));
		txtAboutUs.setBounds(292, 111, 167, 39);
		aboutUsPanel.add(txtAboutUs);
		
		txtAboutuspanel = new JLabel();
		txtAboutuspanel.setText("<html><pre>Sunday		11am - 9pm <br>"
				+ "Monday		11am - 9pm<br>"
				+ "Tuesday		11am - 9pm<br>"
				+ "Wednesday	11am - 9pm<br>"
				+ "Thursday	11am - 9pm<br>"
				+ "Friday		11am - 12pm<br>"
				+ "Saturday	11am - 12pm</pre></html>"
				);
		txtAboutuspanel.setFont(new Font("Arial", Font.BOLD, 18));
		txtAboutuspanel.setBounds(361, 143, 327, 171);
		aboutUsPanel.add(txtAboutuspanel);
		
		JLabel lblNewLabel = new JLabel("  Hours of operations");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(textBoxColor);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(118, 197, 185, 39);
		aboutUsPanel.add(lblNewLabel);
		
		JLabel barSeperator = new JLabel("");
		barSeperator.setBounds(62, 325, 663, 14);
		barSeperator.setOpaque(true);
		barSeperator.setBackground(textBoxColor);
		aboutUsPanel.add(barSeperator);
		
		JLabel ContactUs = new JLabel();
		ContactUs.setHorizontalAlignment(SwingConstants.CENTER);
		ContactUs.setText("Contact Us");
		ContactUs.setFont(new Font("Arial", Font.BOLD, 24));
		ContactUs.setBounds(292, 342, 167, 39);
		aboutUsPanel.add(ContactUs);
		
		JLabel numAndEmail = new JLabel();
		numAndEmail.setText("<html><pre>If you are having difficulties please call 770-555-1212 <br>"
				+ "        Or email us at mom@MomAndPopPizzera.com </pre></html>");
		numAndEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		numAndEmail.setBounds(99, 323, 560, 146);
		aboutUsPanel.add(numAndEmail);
		
		JLabel addressLabel = new JLabel();
		addressLabel.setText("<html><pre>680 Arntson Rd, Suite 161 <br>"
				+ "    Marietta, GA 30060 </pre></html>");
		addressLabel.setFont(new Font("Arial", Font.BOLD, 16));
		addressLabel.setBounds(253,412, 270, 71);
		aboutUsPanel.add(addressLabel);
		
	}
	public void createSides() {
		//heading
				
				JButton aboutUsButton, menuButton, orderButton, signInButton;
				JPanel panelHeader5 = new JPanel();
				panelHeader5.setLayout(null);
				panelHeader5.setBounds(0, 0, 784, 100);
				panelHeader5.setBackground(headerColor);
				
				
				//adds mom and pops logo
				JLabel logoLabel = new JLabel("New label");
				ImageIcon logoPhoto = new ImageIcon(this.getClass().getResource("/logo.png"));
				logoLabel.setIcon(logoPhoto);
				logoLabel.setBounds(0, 0, 100, 100);
				panelHeader5.add(logoLabel);
				
				//button creations
				aboutUsButton = new JButton("About Us");
				aboutUsButton.setBounds(492, 60, 108, 29);
				aboutUsButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				menuButton = new JButton("Menu");
				menuButton.setBounds(149, 60, 108, 29);
				menuButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				orderButton = new JButton("Order");
				orderButton.setBounds(323, 60, 108, 29);
				orderButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				signInButton = new JButton("Account");
				signInButton.setBounds(666, 60, 108, 29);
				signInButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				//Actions triggered by button presses
				aboutUsButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(GUIPanel, "aboutUsPanel");
					}
				});
				menuButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(GUIPanel, "menuPanel");
					}
				});
				orderButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(GUIPanel, "orderingPanel");
					}
				});
				signInButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(GUIPanel, "signInPanel");
					}
				});
				
				//adds buttons to the other screens
				panelHeader5.add(aboutUsButton);
				panelHeader5.add(menuButton);
				panelHeader5.add(orderButton);
				panelHeader5.add(signInButton);
				sidesPanel.add(panelHeader5);
				
				
//body
				//breadsticks interaction
				JLabel amountBread = new JLabel("Enter Amount");
				amountBread.setBounds(87, 194, 122, 30);
				amountBread.setForeground(Color.WHITE);
				amountBread.setFont(new Font("Arial", Font.ITALIC, 18));
				sidesPanel.add(amountBread);
				JTextField breadsticksAmount = new JTextField("0");
				breadsticksAmount.setHorizontalAlignment(SwingConstants.CENTER);
				breadsticksAmount.setBounds(125, 230, 29, 30);
				breadsticksAmount.setFont(new Font("Arial", Font.ITALIC, 18));
				sidesPanel.add(breadsticksAmount);
				
				//cookies interaction
				JLabel amountcookie = new JLabel("Enter Amount");
				amountcookie.setForeground(Color.WHITE);
				amountcookie.setFont(new Font("Arial", Font.ITALIC, 18));
				amountcookie.setBounds(568, 194, 122, 30);
				sidesPanel.add(amountcookie);
				cookieAmount = new JTextField("0");
				cookieAmount.setHorizontalAlignment(SwingConstants.CENTER);
				cookieAmount.setFont(new Font("Arial", Font.ITALIC, 18));
				cookieAmount.setBounds(610, 230, 29, 30);
				sidesPanel.add(cookieAmount);
				
				//breadstick bites interaction
				breadstickBiteAmount = new JTextField("0");
				breadstickBiteAmount.setHorizontalAlignment(SwingConstants.CENTER);
				breadstickBiteAmount.setFont(new Font("Arial", Font.ITALIC, 18));
				breadstickBiteAmount.setBounds(365, 235, 29, 30);
				sidesPanel.add(breadstickBiteAmount);
				JLabel amountBreadBite = new JLabel("Enter Amount");
				amountBreadBite.setForeground(Color.WHITE);
				amountBreadBite.setFont(new Font("Arial", Font.ITALIC, 18));
				amountBreadBite.setBounds(322, 207, 122, 30);
				sidesPanel.add(amountBreadBite);
				
				
				//JPanel sidesContentPanel = new JPanel();
				sidesPanel.setBackground(backgroundColor);
				//sidesContentPanel.setLayout(null);
				
				//breadsticks
				JLabel breadstickButton = new JLabel();
				ImageIcon breadstickPhoto = new ImageIcon(this.getClass().getResource("/breadsticks.png"));
				breadstickButton.setIcon(breadstickPhoto);
				breadstickButton.setText("<html>  &nbsp; Breadsticks<br><br><br><br></html>");
				breadstickButton.setHorizontalTextPosition(SwingConstants.CENTER);
				breadstickButton.setVerticalTextPosition(SwingConstants.TOP);
				breadstickButton.setForeground(Color.WHITE);
				breadstickButton.setOpaque(true);
				breadstickButton.setBackground(textBoxColor);
				breadstickButton.setFont(new Font("Arial", Font.BOLD, 24));
				breadstickButton.setBounds(64, 138, 160, 250);
				sidesPanel.add(breadstickButton);
				
				//breadstick bites
				JLabel breadstickBiteButton = new JLabel();
				ImageIcon breadstickBitePhoto = new ImageIcon(this.getClass().getResource("/breadstickBites.png"));
				breadstickBiteButton.setIcon(breadstickBitePhoto);
				breadstickBiteButton.setVerticalTextPosition(SwingConstants.TOP);
				breadstickBiteButton.setText("<html>  &nbsp; Breadsticks<br> &emsp; &nbsp; Bites<br><br><br></html>");
				breadstickBiteButton.setOpaque(true);
				breadstickBiteButton.setHorizontalTextPosition(SwingConstants.CENTER);
				breadstickBiteButton.setForeground(Color.WHITE);
				breadstickBiteButton.setFont(new Font("Arial", Font.BOLD, 24));
				breadstickBiteButton.setBackground(new Color(152, 0, 0));
				breadstickBiteButton.setBounds(299, 138, 160, 250);
				sidesPanel.add(breadstickBiteButton);
				
				//cookies
				JLabel cookieButton = new JLabel();
				ImageIcon cookiePhoto = new ImageIcon(this.getClass().getResource("/cookie.png"));
				cookieButton.setIcon(cookiePhoto);
				cookieButton.setIconTextGap(30);
				cookieButton.setVerticalTextPosition(SwingConstants.TOP);
				cookieButton.setText("<html>  &emsp; Cookies<br><br><br></html>");
				cookieButton.setOpaque(true);
				cookieButton.setHorizontalTextPosition(SwingConstants.CENTER);
				cookieButton.setForeground(Color.WHITE);
				cookieButton.setFont(new Font("Arial", Font.BOLD, 24));
				cookieButton.setBackground(new Color(152, 0, 0));
				cookieButton.setBounds(543, 138, 160, 250);
				sidesPanel.add(cookieButton);
				
				//drinks
				
				JButton leftSize = new JButton("<");
				leftSize.setBounds(284, 428, 41, 30);
				leftSize.setFont(new Font("Arial", Font.BOLD, 12));
				sidesPanel.add(leftSize);
				
				JButton rightSize = new JButton(">");
				rightSize.setFont(new Font("Arial", Font.BOLD, 12));
				rightSize.setBounds(468, 428, 41, 30);
				sidesPanel.add(rightSize);
				
				JLabel sizeSelected = new JLabel(sizeArr[0]);
				sizeSelected.setBounds(330, 428, 133, 30);
				sizeSelected.setBackground(Color.WHITE);
				sizeSelected.setHorizontalAlignment(SwingConstants.CENTER);
				sizeSelected.setOpaque(true);
				sizeSelected.setFont(new Font("Arial", Font.BOLD, 16));
				sidesPanel.add(sizeSelected);
				
				JButton leftFlavor = new JButton("<");
				leftFlavor.setBounds(284, 480, 41, 30);
				leftFlavor.setFont(new Font("Arial", Font.BOLD, 12));
				sidesPanel.add(leftFlavor);
				
				JButton rightFlavor = new JButton(">");
				rightFlavor.setFont(new Font("Arial", Font.BOLD, 12));
				rightFlavor.setBounds(468, 480, 41, 30);
				sidesPanel.add(rightFlavor);
				
				JLabel flavorSelected = new JLabel(flavorArr[0]);
				flavorSelected.setBounds(330, 480, 133, 30);
				flavorSelected.setBackground(Color.WHITE);
				flavorSelected.setHorizontalAlignment(SwingConstants.CENTER);
				flavorSelected.setOpaque(true);
				flavorSelected.setFont(new Font("Arial", Font.BOLD, 16));
				sidesPanel.add(flavorSelected);
				
				JLabel sizeText = new JLabel("Size:");
				sizeText.setVerticalAlignment(SwingConstants.TOP);
				sizeText.setAlignmentX(SwingConstants.LEFT);
				sizeText.setFont(new Font("Arial", Font.BOLD, 20));
				sizeText.setBounds(222, 428, 52, 30);
				sizeText.setForeground(Color.WHITE);
				sidesPanel.add(sizeText);
				JLabel flavorText = new JLabel("Flavor:");
				flavorText.setVerticalAlignment(SwingConstants.TOP);
				flavorText.setAlignmentX(SwingConstants.LEFT);
				flavorText.setFont(new Font("Arial", Font.BOLD, 20));
				flavorText.setBounds(201, 480, 79, 30);
				flavorText.setForeground(Color.WHITE);
				sidesPanel.add(flavorText);
				
				JLabel drinkBackground = new JLabel("Drink");
				drinkBackground.setVerticalAlignment(SwingConstants.CENTER);
				drinkBackground.setAlignmentX(SwingConstants.LEFT);
				drinkBackground.setFont(new Font("Arial", Font.BOLD, 24));
				drinkBackground.setBounds(104, 415, 552, 109);
				drinkBackground.setBackground(new Color(152, 0, 0));
				drinkBackground.setForeground(Color.WHITE);
				drinkBackground.setOpaque(true);
				sidesPanel.add(drinkBackground);
				
				//toggles through the selection options
				leftSize.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sizeCounter -= 1;
						if (sizeCounter < 0) {
							sizeCounter = 3;
						}
						sizeSelected.setText(sizeArr[sizeCounter]);
					}
				});
				rightSize.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sizeCounter += 1;
						if (sizeCounter > 3) {
							sizeCounter = 0;
						}
						sizeSelected.setText(sizeArr[sizeCounter]);
					}
				});
				leftFlavor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						flavorCounter -= 1;
						if (flavorCounter < 0) {
							flavorCounter = 8;
						}
						flavorSelected.setText(flavorArr[flavorCounter]);
					}
				});
				rightFlavor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						flavorCounter += 1;
						if (flavorCounter > 8) {
							flavorCounter = 0;
						}
						flavorSelected.setText(flavorArr[flavorCounter]);
					}
				});
				//returns to the cart
				JButton sidesCartButton = new JButton("Add to Cart");
				sidesCartButton.setForeground(Color.WHITE);
				sidesCartButton.setOpaque(true);
				sidesCartButton.setBackground(textBoxColor);
				sidesCartButton.setFont(new Font("Arial", Font.BOLD, 24));
				sidesCartButton.setBounds(270, 600, 200, 39);
				sidesPanel.add(sidesCartButton);	
				
				sidesCartButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LinkedList<String> nonDrinkSides = new LinkedList<String>();
						if (Integer.parseInt(breadsticksAmount.getText()) > 0) {
							int temp = Integer.parseInt(breadsticksAmount.getText());
							while (temp > 0) {
								nonDrinkSides.add("breadsticks");
								temp--;
							}
						}
						if (Integer.parseInt(breadstickBiteAmount.getText()) > 0) {
							int temp = Integer.parseInt(breadstickBiteAmount.getText());
							while (temp > 0) {
								nonDrinkSides.add("Breadstick Bites");
								temp--;
							}
						}
						if (Integer.parseInt(cookieAmount.getText()) > 0) {
							int temp = Integer.parseInt(cookieAmount.getText());
							while (temp > 0) {
								nonDrinkSides.add("Big Chocolate Chip Cookie");
								temp--;
							}
						}
						Sides currentOrder = new Sides(flavorSelected.getText(), sizeSelected.getText(), nonDrinkSides);
						overrallOrders.activeSides.add(currentOrder);
						viewOrders.setText(overrallOrders.toString());
						subTotalAmount.setText(overrallOrders.calculatesubTotal());
						taxAmountText.setText(overrallOrders.calculatetaxTotal());
						totalAmountText.setText(overrallOrders.calculateTotal());
						cardLayout.show(GUIPanel, "cartPanel");
						}
					});
	}
	public void createOrdering() {

//heading
		JButton aboutUsButton, menuButton, orderButton, signInButton;
		JPanel panelHeader4 = new JPanel();
		panelHeader4.setLayout(null);
		panelHeader4.setBounds(0, 0, 784, 100);
		panelHeader4.setBackground(headerColor);
		
		
		//adds mom and pops logo
		JLabel logoLabel = new JLabel("New label");
		ImageIcon logoPhoto = new ImageIcon(this.getClass().getResource("/logo.png"));
		logoLabel.setIcon(logoPhoto);
		logoLabel.setBounds(0, 0, 100, 100);
		panelHeader4.add(logoLabel);
		
		//button creations
		aboutUsButton = new JButton("About Us");
		aboutUsButton.setBounds(492, 60, 108, 29);
		aboutUsButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		menuButton = new JButton("Menu");
		menuButton.setBounds(149, 60, 108, 29);
		menuButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		orderButton = new JButton("Order");
		orderButton.setBounds(323, 60, 108, 29);
		orderButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		signInButton = new JButton("Account");
		signInButton.setBounds(666, 60, 108, 29);
		signInButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		//Actions triggered by button presses
		aboutUsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "aboutUsPanel");
			}
		});
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "menuPanel");
			}
		});
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(GUIPanel, "signInPanel");
			}
		});
//body
				
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(backgroundColor);
		contentPanel.setLayout(null);
		
		
		//Build a Pizza label
		JLabel pizzaBuilderTxt = new JLabel();
		pizzaBuilderTxt.setHorizontalAlignment(SwingConstants.CENTER);
		pizzaBuilderTxt.setText("Pizza Builder");
		pizzaBuilderTxt.setFont(new Font("Arial", Font.BOLD, 24));
		pizzaBuilderTxt.setBounds(292, 10, 167, 39);
		contentPanel.add(pizzaBuilderTxt);
		
		//crust
			//crust label
			JLabel crustTxt = new JLabel();
			crustTxt.setHorizontalAlignment(SwingConstants.CENTER);
			crustTxt.setText("Type of Crust");
			crustTxt.setFont(new Font("Arial", Font.BOLD, 18));
			crustTxt.setBounds(292, 100, 167, 39);
			contentPanel.add(crustTxt);
					
			//thin crust checkbox
			thinCheck.setSelected(true);
			thinCheck.setForeground(Color.WHITE);
			thinCheck.setOpaque(true);
			thinCheck.setBackground(textBoxColor);
			thinCheck.setFont(new Font("Arial", Font.BOLD, 24));
			thinCheck.setBounds(100, 150, 150, 39);
			contentPanel.add(thinCheck);
			
			//regular crust checkbox
			
			regCheck.setForeground(Color.WHITE);
			regCheck.setOpaque(true);
			regCheck.setBackground(textBoxColor);
			regCheck.setFont(new Font("Arial", Font.BOLD, 24));
			regCheck.setBounds(300, 150, 150, 39);
			contentPanel.add(regCheck);
			
			//pan crust checkbox
			panCheck.setForeground(Color.WHITE);
			panCheck.setOpaque(true);
			panCheck.setBackground(textBoxColor);
			panCheck.setFont(new Font("Arial", Font.BOLD, 24));
			panCheck.setBounds(500, 150, 150, 39);
			contentPanel.add(panCheck);
			
			//deselects the other crust options
			thinCheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panCheck.setSelected(false);
					regCheck.setSelected(false);
				}
			});
			regCheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					thinCheck.setSelected(false);
					panCheck.setSelected(false);
				}
			});
			panCheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					thinCheck.setSelected(false);
					regCheck.setSelected(false);
				}
			});
		
		//Size
			//size label
				JLabel sizeTxt = new JLabel();
				sizeTxt.setHorizontalAlignment(SwingConstants.CENTER);
				sizeTxt.setText("Pick a Size");
				sizeTxt.setFont(new Font("Arial", Font.BOLD, 18));
				sizeTxt.setBounds(292, 200, 167, 39);
				contentPanel.add(sizeTxt);
			//small
				smallButton.setSelected(true);
				smallButton.setText("Small");
				smallButton.setHorizontalTextPosition(SwingConstants.TRAILING);
				//smallButton.setVerticalTextPosition(SwingConstants.TOP);
				smallButton.setForeground(Color.WHITE);
				smallButton.setOpaque(true);
				smallButton.setBackground(textBoxColor);
				smallButton.setFont(new Font("Arial", Font.BOLD, 24));
				smallButton.setBounds(50, 250, 150, 39);
				contentPanel.add(smallButton);
			//medium
				
				mediumButton.setText("Medium");
				mediumButton.setHorizontalTextPosition(SwingConstants.TRAILING);
				//mediumButton.setVerticalTextPosition(SwingConstants.TOP);
				mediumButton.setForeground(Color.WHITE);
				mediumButton.setOpaque(true);
				mediumButton.setBackground(textBoxColor);
				mediumButton.setFont(new Font("Arial", Font.BOLD, 24));
				mediumButton.setBounds(220, 250, 150, 39);
				contentPanel.add(mediumButton);
			//large
				
				largeButton.setText("Large");
				largeButton.setHorizontalTextPosition(SwingConstants.TRAILING);
				//largeButton.setVerticalTextPosition(SwingConstants.TOP);
				largeButton.setForeground(Color.WHITE);
				largeButton.setOpaque(true);
				largeButton.setBackground(textBoxColor);
				largeButton.setFont(new Font("Arial", Font.BOLD, 24));
				largeButton.setBounds(390, 250, 150, 39);
				contentPanel.add(largeButton);
			//xLarge
				
				xLargeButton.setText("X-Large");
				xLargeButton.setHorizontalTextPosition(SwingConstants.TRAILING);
				//xLargeButton.setVerticalTextPosition(SwingConstants.TOP);
				xLargeButton.setForeground(Color.WHITE);
				xLargeButton.setOpaque(true);
				xLargeButton.setBackground(textBoxColor);
				xLargeButton.setFont(new Font("Arial", Font.BOLD, 24));
				xLargeButton.setBounds(560, 250, 150, 39);
				contentPanel.add(xLargeButton);
				

			//deselects the other size options
				smallButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mediumButton.setSelected(false);
						largeButton.setSelected(false);
						xLargeButton.setSelected(false);
					}
				});
				mediumButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						smallButton.setSelected(false);
						largeButton.setSelected(false);
						xLargeButton.setSelected(false);
					}
				});
				largeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						smallButton.setSelected(false);
						mediumButton.setSelected(false);
						xLargeButton.setSelected(false);
					}
				});
				xLargeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						smallButton.setSelected(false);
						largeButton.setSelected(false);
						mediumButton.setSelected(false);
					}
				});
				
			
		//toppings
			//topping label
				JLabel toppingTxt = new JLabel();
				toppingTxt.setHorizontalAlignment(SwingConstants.CENTER);
				toppingTxt.setText("Pick Toppings");
				toppingTxt.setFont(new Font("Arial", Font.BOLD, 18));
				toppingTxt.setBounds(292, 300, 167, 39);
				contentPanel.add(toppingTxt);
			//cheese
				JButton cheeseButton = new JButton();
				ImageIcon cheesePhoto = new ImageIcon(this.getClass().getResource("/cheese.png"));
				cheeseButton.setIcon(cheesePhoto);
				cheeseButton.setText("Cheese");
				cheeseButton.setHorizontalTextPosition(SwingConstants.CENTER);
				cheeseButton.setVerticalTextPosition(SwingConstants.TOP);
				cheeseButton.setForeground(Color.WHITE);
				cheeseButton.setOpaque(true);
				cheeseButton.setBackground(textBoxColor);
				cheeseButton.setFont(new Font("Arial", Font.BOLD, 24));
				cheeseButton.setBounds(100, 350, 150, 200);
				
				
				cheeseCheck.setBounds(100, 550, 150, 50);
				cheeseCheck.setFont(new Font("Arial", Font.PLAIN, 18));
				
				
				//action for adding cheese to pizza
				cheeseButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cheeseCheck.getText().isEmpty()) {
							cheeseCheck.setText("Cheese added!");
						} else {
							cheeseCheck.setText("");
						}
					}
				});
				contentPanel.add(cheeseCheck);
				contentPanel.add(cheeseButton);
			//pepperoni
				JButton pepButton = new JButton();
				ImageIcon pepPhoto = new ImageIcon(this.getClass().getResource("/pepperoni.png"));
				pepButton.setIcon(pepPhoto);
				pepButton.setText("<html>Peppe<br> -roni</html>");
				pepButton.setHorizontalTextPosition(SwingConstants.CENTER);
				pepButton.setVerticalTextPosition(SwingConstants.TOP);
				pepButton.setForeground(Color.WHITE);
				pepButton.setOpaque(true);
				pepButton.setBackground(textBoxColor);
				pepButton.setFont(new Font("Arial", Font.BOLD, 24));
				pepButton.setBounds(300, 350, 150, 200);
				
				
				pepCheck.setBounds(300, 550, 150, 50);
				pepCheck.setFont(new Font("Arial", Font.PLAIN, 18));
				
				
				//action for adding pepperoni to pizza
				pepButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(pepCheck.getText().isEmpty()) {
							pepCheck.setText("Pepperoni added!");
						} else {
							pepCheck.setText("");
						}
					}
				});
				contentPanel.add(pepCheck);
				contentPanel.add(pepButton);
			//Sausage
				JButton sausButton = new JButton();
				ImageIcon sausPhoto = new ImageIcon(this.getClass().getResource("/sausage.png"));
				sausButton.setIcon(sausPhoto);
				sausButton.setText("Sausage");
				sausButton.setHorizontalTextPosition(SwingConstants.CENTER);
				sausButton.setVerticalTextPosition(SwingConstants.TOP);
				sausButton.setForeground(Color.WHITE);
				sausButton.setOpaque(true);
				sausButton.setBackground(textBoxColor);
				sausButton.setFont(new Font("Arial", Font.BOLD, 24));
				sausButton.setBounds(500, 350, 150, 200);
				
				
				sausCheck.setBounds(500, 550, 150, 50);
				sausCheck.setFont(new Font("Arial", Font.PLAIN, 18));
				
				
				//action for adding sausage to pizza
				sausButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(sausCheck.getText().isEmpty()) {
							sausCheck.setText("Sausage added!");
						} else {
							sausCheck.setText("");
						}
					}
				});
				contentPanel.add(sausCheck);
				contentPanel.add(sausButton);
			//onion
				JButton onionButton = new JButton();
				ImageIcon onionPhoto = new ImageIcon(this.getClass().getResource("/onion.png"));
				onionButton.setIcon(onionPhoto);
				onionButton.setText("Onion");
				onionButton.setHorizontalTextPosition(SwingConstants.CENTER);
				onionButton.setVerticalTextPosition(SwingConstants.TOP);
				onionButton.setForeground(Color.WHITE);
				onionButton.setOpaque(true);
				onionButton.setBackground(textBoxColor);
				onionButton.setFont(new Font("Arial", Font.BOLD, 24));
				onionButton.setBounds(100, 620, 150, 200);
				
				
				onionCheck.setBounds(100, 820, 150, 50);
				onionCheck.setFont(new Font("Arial", Font.PLAIN, 18));
				
				
				//action for adding cheese to pizza
				onionButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(onionCheck.getText().isEmpty()) {
							onionCheck.setText("Onion added!");
						} else {
							onionCheck.setText("");
						}
					}
				});
				contentPanel.add(onionCheck);
				contentPanel.add(onionButton);
			//mushroom
				JButton mushButton = new JButton();
				ImageIcon mushPhoto = new ImageIcon(this.getClass().getResource("/mushroom.png"));
				mushButton.setIcon(mushPhoto);
				mushButton.setText("<html>Mush<br>room</html>");
				mushButton.setHorizontalTextPosition(SwingConstants.CENTER);
				mushButton.setVerticalTextPosition(SwingConstants.TOP);
				mushButton.setForeground(Color.WHITE);
				mushButton.setOpaque(true);
				mushButton.setBackground(textBoxColor);
				mushButton.setFont(new Font("Arial", Font.BOLD, 24));
				mushButton.setBounds(300, 620, 150, 200);
				
				
				mushCheck.setBounds(300, 820, 150, 50);
				mushCheck.setFont(new Font("Arial", Font.PLAIN, 18));
				
				
				//action for adding mushroom to pizza
				mushButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(mushCheck.getText().isEmpty()) {
							mushCheck.setText("Mushroom added!");
						} else {
							mushCheck.setText("");
						}
					}
				});
				contentPanel.add(mushCheck);
				contentPanel.add(mushButton);
			//Tomato
				JButton tomButton = new JButton();
				ImageIcon tomPhoto = new ImageIcon(this.getClass().getResource("/tomato.png"));
				tomButton.setIcon(tomPhoto);
				tomButton.setText("Tomato");
				tomButton.setHorizontalTextPosition(SwingConstants.CENTER);
				tomButton.setVerticalTextPosition(SwingConstants.TOP);
				tomButton.setForeground(Color.WHITE);
				tomButton.setOpaque(true);
				tomButton.setBackground(textBoxColor);
				tomButton.setFont(new Font("Arial", Font.BOLD, 24));
				tomButton.setBounds(500, 620, 150, 200);
				
				tomCheck.setBounds(500, 820, 150, 50);
				tomCheck.setFont(new Font("Arial", Font.PLAIN, 18));
				
				
				//action for adding tomato to pizza
				tomButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(tomCheck.getText().isEmpty()) {
							tomCheck.setText("Tomato added!");
						} else {
							tomCheck.setText("");
						}
					}
				});
				contentPanel.add(tomCheck);
				contentPanel.add(tomButton);
			//ham
				JButton hamButton = new JButton();
				ImageIcon hamPhoto = new ImageIcon(this.getClass().getResource("/ham.png"));
				hamButton.setIcon(hamPhoto);
				hamButton.setText("Ham");
				hamButton.setHorizontalTextPosition(SwingConstants.CENTER);
				hamButton.setVerticalTextPosition(SwingConstants.TOP);
				hamButton.setForeground(Color.WHITE);
				hamButton.setOpaque(true);
				hamButton.setBackground(textBoxColor);
				hamButton.setFont(new Font("Arial", Font.BOLD, 24));
				hamButton.setBounds(100, 890, 150, 200);
				
				
				hamCheck.setBounds(100, 1090, 150, 50);
				hamCheck.setFont(new Font("Arial", Font.PLAIN, 18));
				
				
				//action for adding cheese to pizza
				hamButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(hamCheck.getText().isEmpty()) {
							hamCheck.setText("Ham added!");
						} else {
							hamCheck.setText("");
						}
					}
				});
				contentPanel.add(hamCheck);
				contentPanel.add(hamButton);
			//pineapple
				JButton pineButton = new JButton();
				ImageIcon pinePhoto = new ImageIcon(this.getClass().getResource("/pineapple.png"));
				pineButton.setIcon(pinePhoto);
				pineButton.setText("Pineapple");
				pineButton.setHorizontalTextPosition(SwingConstants.CENTER);
				pineButton.setVerticalTextPosition(SwingConstants.TOP);
				pineButton.setForeground(Color.WHITE);
				pineButton.setOpaque(true);
				pineButton.setBackground(textBoxColor);
				pineButton.setFont(new Font("Arial", Font.BOLD, 24));
				pineButton.setBounds(300, 890, 150, 200);
				
				
				pineCheck.setBounds(300, 1090, 150, 50);
				pineCheck.setFont(new Font("Arial", Font.PLAIN, 18));
				
				
				//action for adding mushroom to pizza
				pineButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(pineCheck.getText().isEmpty()) {
							pineCheck.setText("Pineapple added!");
						} else {
							pineCheck.setText("");
						}
					}
				});
				contentPanel.add(pineCheck);
				contentPanel.add(pineButton);
			//Green Peppers
				JButton GrePepButton = new JButton();
				ImageIcon GrePepPhoto = new ImageIcon(this.getClass().getResource("/greenPepper.png"));
				GrePepButton.setIcon(GrePepPhoto);
				GrePepButton.setText("<html>Green<br>Pepper</html>");
				GrePepButton.setHorizontalTextPosition(SwingConstants.CENTER);
				GrePepButton.setVerticalTextPosition(SwingConstants.TOP);
				GrePepButton.setForeground(Color.WHITE);
				GrePepButton.setOpaque(true);
				GrePepButton.setBackground(textBoxColor);
				GrePepButton.setFont(new Font("Arial", Font.BOLD, 24));
				GrePepButton.setBounds(500, 890, 150, 200);
				
				
				GrePepCheck.setBounds(500, 1090, 150, 50);
				GrePepCheck.setFont(new Font("Arial", Font.PLAIN, 18));
				
				
				//action for adding tomato to pizza
				GrePepButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(GrePepCheck.getText().isEmpty()) {
							GrePepCheck.setText("Green Pepper added!");
						} else {
							GrePepCheck.setText("");
						}
					}
				});
				contentPanel.add(GrePepCheck);
				contentPanel.add(GrePepButton);
			
			//add to cart button
			JButton cartButton = new JButton("Add to Cart");
			cartButton.setForeground(Color.WHITE);
			cartButton.setOpaque(true);
			cartButton.setBackground(textBoxColor);
			cartButton.setFont(new Font("Arial", Font.BOLD, 24));
			cartButton.setBounds(270, 1150, 200, 39);
			contentPanel.add(cartButton);	
			cartButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//gathers data for pizza to create an order
					if (thinCheck.isSelected()) {
						crust = "thin";
					} else if (regCheck.isSelected()) {
						crust = "regular";
					} else {
						crust = "pan";
					}
					if (smallButton.isSelected()) {
						size = "small";
					} else if (mediumButton.isSelected()) {
						size = "medium";
					} else if (largeButton.isSelected()) {
						size = "large";
					} else {
						size = "X-Large";
					}
					if (!cheeseCheck.getText().isEmpty()) {
						toppings.add("cheese");
					}
					if (!pepCheck.getText().isEmpty()) {
						toppings.add("pepperoni");
					}
					if (!sausCheck.getText().isEmpty()) {
						toppings.add("sausage");
					}
					if (!onionCheck.getText().isEmpty()) {
						toppings.add("onion");
					}
					if (!mushCheck.getText().isEmpty()) {
						toppings.add("mushroom");
					}
					if (!tomCheck.getText().isEmpty()) {
						toppings.add("tomato");
					}
					if (!hamCheck.getText().isEmpty()) {
						toppings.add("ham");
					}
					if (!pineCheck.getText().isEmpty()) {
						toppings.add("pineapple");
					}
					if (!GrePepCheck.getText().isEmpty()) {
						toppings.add("green pepper");
					}
					Pizza pizzaOrder = new Pizza(crust,size,toppings);
					overrallOrders.activePizzas.add(pizzaOrder);
					viewOrders.setText(overrallOrders.toString());
					subTotalAmount.setText(overrallOrders.calculatesubTotal());
					taxAmountText.setText(overrallOrders.calculatetaxTotal());
					totalAmountText.setText(overrallOrders.calculateTotal());
					cardLayout.show(GUIPanel, "cartPanel");
				}
			});
				
		//creates the scrollpane and adds the contentPane to it
		JScrollPane scrollPane = new JScrollPane(contentPanel, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane.setBounds(0, 100,784,700);
		//scrollPane.setMinimumSize(new Dimension(160,200));
		contentPanel.setPreferredSize(new Dimension(800,1300));
		scrollPane.setViewportView(contentPanel);
		scrollPane.getViewport().setPreferredSize(new Dimension(160,200));
		orderingPanel.add(scrollPane);
		
		//adds buttons to the other screens
		panelHeader4.add(aboutUsButton);
		panelHeader4.add(menuButton);
		panelHeader4.add(orderButton);
		panelHeader4.add(signInButton);
		orderingPanel.add(panelHeader4);
	}
	public void refreshOrdering() {
		crust = "";
		size = "";
		toppings = new LinkedList<String>();
		thinCheck.setSelected(true);
		regCheck.setSelected(false);
		panCheck.setSelected(false);
		smallButton.setSelected(true);
		mediumButton.setSelected(false);
		largeButton.setSelected(false);
		xLargeButton.setSelected(false);
		if (!cheeseCheck.getText().isEmpty()) {
			cheeseCheck.setText("");
		}
		if (!pepCheck.getText().isEmpty()) {
			pepCheck.setText("");
		}
		if (!sausCheck.getText().isEmpty()) {
			sausCheck.setText("");
		}
		if (!onionCheck.getText().isEmpty()) {
			onionCheck.setText("");
		}
		if (!mushCheck.getText().isEmpty()) {
			mushCheck.setText("");
		}
		if (!tomCheck.getText().isEmpty()) {
			tomCheck.setText("");
		}
		if (!hamCheck.getText().isEmpty()) {
			hamCheck.setText("");
		}
		if (!pineCheck.getText().isEmpty()) {
			pineCheck.setText("");
		}
		if (!GrePepCheck.getText().isEmpty()) {
			GrePepCheck.setText("");
		}
	}
	public void buildCheckoutPane(boolean signedIn){
		
		ActiveCheckoutPanel.setLayout(null);
		//checkout title
		JLabel checkoutLabel = new JLabel();
		checkoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		checkoutLabel.setText("CHECKOUT");
		checkoutLabel.setFont(new Font("Arial", Font.BOLD, 24));
		checkoutLabel.setBounds(50, 10, 167, 39);
		ActiveCheckoutPanel.add(checkoutLabel);
		
		JLabel nameLabel = new JLabel();
		nameLabel.setHorizontalAlignment(SwingConstants.LEADING);
		nameLabel.setText("Name:");
		nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
		nameLabel.setBounds(10, 40, 100, 39);
		ActiveCheckoutPanel.add(nameLabel);
		
		JLabel phoneLabel = new JLabel();
		phoneLabel.setHorizontalAlignment(SwingConstants.LEADING);
		phoneLabel.setText("Phone:");
		phoneLabel.setFont(new Font("Arial", Font.BOLD, 16));
		phoneLabel.setBounds(10, 80, 100, 39);
		ActiveCheckoutPanel.add(phoneLabel);
		
		JLabel emailLabel = new JLabel();
		emailLabel.setHorizontalAlignment(SwingConstants.LEADING);
		emailLabel.setText("Email:");
		emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
		emailLabel.setBounds(10, 120, 100, 39);
		ActiveCheckoutPanel.add(emailLabel);
		
		JTextField nameEntry = new JTextField();
		nameEntry.setBounds(65, 45, 150, 29);
		ActiveCheckoutPanel.add(nameEntry);
		
		JTextField phoneEntry = new JTextField();
		phoneEntry.setBounds(65, 85, 150, 29);
		ActiveCheckoutPanel.add(phoneEntry);
		
		JTextField emailEntry = new JTextField();
		emailEntry.setBounds(65, 125, 150, 29);
		ActiveCheckoutPanel.add(emailEntry);
		
		
		JLabel cardNumLabel = new JLabel();
		cardNumLabel.setHorizontalAlignment(SwingConstants.LEADING);
		cardNumLabel.setText("Card Number:");
		cardNumLabel.setFont(new Font("Arial", Font.BOLD, 16));
		cardNumLabel.setBounds(10, 160, 140, 39);
		ActiveCheckoutPanel.add(cardNumLabel);
		
		JLabel addressLabel = new JLabel();
		addressLabel.setHorizontalAlignment(SwingConstants.LEADING);
		addressLabel.setText("Address:");
		addressLabel.setFont(new Font("Arial", Font.BOLD, 16));
		addressLabel.setBounds(10, 240, 100, 39);
		ActiveCheckoutPanel.add(addressLabel);
		
		JTextField paymentEntry = new JTextField();
		paymentEntry.setBounds(10, 200, 200, 29);
		ActiveCheckoutPanel.add(paymentEntry);
		
		JTextField addressEntry = new JTextField();
		addressEntry.setBounds(10, 280, 200, 29);
		ActiveCheckoutPanel.add(addressEntry);

		JCheckBox deliveryCheck = new JCheckBox();
		deliveryCheck.setText("Delivery?");
		deliveryCheck.setHorizontalTextPosition(SwingConstants.TRAILING);
		deliveryCheck.setVerticalTextPosition(SwingConstants.CENTER);
		deliveryCheck.setForeground(Color.WHITE);
		deliveryCheck.setOpaque(true);
		deliveryCheck.setBackground(textBoxColor);
		deliveryCheck.setSelected(true);
		deliveryCheck.setFont(new Font("Arial", Font.BOLD, 18));
		deliveryCheck.setBounds(70, 330, 120, 39);
		ActiveCheckoutPanel.add(deliveryCheck);
		
		JCheckBox pickupCheck = new JCheckBox();
		pickupCheck.setText("Pick Up?");
		pickupCheck.setHorizontalTextPosition(SwingConstants.TRAILING);
		pickupCheck.setVerticalTextPosition(SwingConstants.CENTER);
		pickupCheck.setForeground(Color.WHITE);
		pickupCheck.setOpaque(true);
		pickupCheck.setBackground(textBoxColor);
		pickupCheck.setFont(new Font("Arial", Font.BOLD, 18));
		pickupCheck.setBounds(70, 380, 120, 39);
		ActiveCheckoutPanel.add(pickupCheck);
		
		//deselects the other contact options
		deliveryCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickupCheck.setSelected(false);
			}
		});
		pickupCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deliveryCheck.setSelected(false);
			}
		});
		
		
		JButton checkoutButton = new JButton();
		checkoutButton.setText("CHECKOUT");
		checkoutButton.setHorizontalTextPosition(SwingConstants.CENTER);
		checkoutButton.setVerticalTextPosition(SwingConstants.CENTER);
		checkoutButton.setForeground(Color.WHITE);
		checkoutButton.setOpaque(true);
		checkoutButton.setBackground(textBoxColor);
		checkoutButton.setFont(new Font("Arial", Font.BOLD, 18));
		checkoutButton.setBounds(50, 440, 167, 39);
		ActiveCheckoutPanel.add(checkoutButton);
		
		if (signedIn) {
			nameEntry.setText(currentSession.firstName + " " + currentSession.lastName);
			phoneEntry.setText(currentSession.phone);
			emailEntry.setText(currentSession.email);
			paymentEntry.setText(currentSession.phone);
			addressEntry.setText(currentSession.address);
		}
		
		JLabel successLabel = new JLabel();
		successLabel.setHorizontalAlignment(SwingConstants.CENTER);
		successLabel.setVisible(false);
		successLabel.setText("<html>Checkout <br> Successful!</html>");
		successLabel.setFont(new Font("Arial", Font.BOLD, 24));
		successLabel.setBounds(550, 150, 167, 75);
		cartPanel.add(successLabel);
		
		JLabel receiptLabel = new JLabel();
		receiptLabel.setHorizontalAlignment(SwingConstants.CENTER);
		receiptLabel.setVisible(false);
		receiptLabel.setText("<html>A receipt has been sent to your email.</html>");
		receiptLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		receiptLabel.setBounds(515, 250, 250, 75);
		cartPanel.add(receiptLabel);
		JLabel instructionLabel = new JLabel();
		instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		instructionLabel.setVisible(false);
		instructionLabel.setText("<html>Instructions on delivery or <br>pickup will be texted to your phone number.</html>");
		instructionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		instructionLabel.setBounds(515, 275, 250, 200);
		cartPanel.add(instructionLabel);

		//creates the scrollpane for checkout
			JScrollPane checkoutscrollPane = new JScrollPane(ActiveCheckoutPanel, 
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			checkoutscrollPane.setBounds(490, 120,270,400);
			//scrollPane.setMinimumSize(new Dimension(160,200));
			ActiveCheckoutPanel.setPreferredSize(new Dimension(270,500));
			checkoutscrollPane.setViewportView(ActiveCheckoutPanel);
			checkoutscrollPane.getViewport().setPreferredSize(new Dimension(160,250));
			cartPanel.add(checkoutscrollPane);
			
			
			checkoutButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkoutscrollPane.setVisible(false);
					successLabel.setVisible(true);
					instructionLabel.setVisible(true);
					receiptLabel.setVisible(true);
					if (deliveryCheck.isSelected()) {
						String contact = "delivery";
					} else {
						String contact = "pick up";
					}
					//create ticket
					//create receipt
				}
			});
			
	}
	public void createAccountSettings() {
		//header	
				JButton menuButton, orderButton, signInButton, aboutUsButton;
				JPanel panelHeader8 = new JPanel();
				panelHeader8.setLayout(null);
				panelHeader8.setBounds(0, 0, 784, 100);
				panelHeader8.setBackground(headerColor);
				
				
				//adds mom and pops logo
				JLabel logoLabel = new JLabel("New label");
				ImageIcon logoPhoto = new ImageIcon(this.getClass().getResource("/logo.png"));
				logoLabel.setIcon(logoPhoto);
				logoLabel.setBounds(0, 0, 100, 100);
				panelHeader8.add(logoLabel);
				
				menuButton = new JButton("Menu");
				menuButton.setBounds(149, 60, 108, 29);
				menuButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				orderButton = new JButton("Order");
				orderButton.setBounds(323, 60, 108, 29);
				orderButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				signInButton = new JButton("Account");
				signInButton.setBounds(666, 60, 108, 29);
				signInButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				
				aboutUsButton = new JButton("About Us");
				aboutUsButton.setBounds(492, 60, 108, 29);
				aboutUsButton.setFont(new Font("Tahoma", Font.BOLD, 16));

				
				
				//Actions triggered by button presses
				menuButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(GUIPanel, "menuPanel");
					}
				});
				orderButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(GUIPanel, "orderingPanel");
					}
				});
				aboutUsButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(GUIPanel, "aboutUsPanel");
					}
				});
				signInButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(GUIPanel, "signInPanel");
					}
				});
				panelHeader8.add(menuButton);
				panelHeader8.add(orderButton);
				panelHeader8.add(aboutUsButton);
				panelHeader8.add(signInButton);
				accountSettingsPanel.add(panelHeader8);
				
		//body
				accountSettingsPanel.setBackground(backgroundColor);
				
				JLabel changePhoneLabel = new JLabel();
				changePhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
				changePhoneLabel.setText("Enter new Phone");
				changePhoneLabel.setFont(new Font("Arial", Font.BOLD, 18));
				changePhoneLabel.setBounds(23, 122, 245, 39);
				accountSettingsPanel.add(changePhoneLabel);
				
				
				JTextField phoneField = new JTextField();
				phoneField.setColumns(10);
				phoneField.setBounds(47, 161, 210, 41);
				accountSettingsPanel.add(phoneField);
				
				JButton submitPhoneButton = new JButton();
				submitPhoneButton.setText("<html>Change Phone<br></html>");
				submitPhoneButton.setHorizontalTextPosition(SwingConstants.CENTER);
				submitPhoneButton.setVerticalTextPosition(SwingConstants.TOP);
				submitPhoneButton.setForeground(Color.WHITE);
				submitPhoneButton.setOpaque(true);
				submitPhoneButton.setBackground(textBoxColor);
				submitPhoneButton.setFont(new Font("Arial", Font.BOLD, 18));
				submitPhoneButton.setBounds(93, 213, 104, 63);
				accountSettingsPanel.add(submitPhoneButton);
				
				emailField = new JTextField();
				emailField.setColumns(10);
				emailField.setBounds(47, 360, 210, 41);
				accountSettingsPanel.add(emailField);
				
				JButton submitEmailButton = new JButton();
				submitEmailButton.setVerticalTextPosition(SwingConstants.TOP);
				submitEmailButton.setText("<html>Change Email<br></html>");
				submitEmailButton.setOpaque(true);
				submitEmailButton.setHorizontalTextPosition(SwingConstants.CENTER);
				submitEmailButton.setForeground(Color.WHITE);
				submitEmailButton.setFont(new Font("Arial", Font.BOLD, 18));
				submitEmailButton.setBackground(new Color(152, 0, 0));
				submitEmailButton.setBounds(93, 412, 104, 63);
				accountSettingsPanel.add(submitEmailButton);
				
				JLabel changeEmailLabel = new JLabel();
				changeEmailLabel.setText("Enter new Email");
				changeEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
				changeEmailLabel.setFont(new Font("Arial", Font.BOLD, 18));
				changeEmailLabel.setBounds(23, 321, 245, 39);
				accountSettingsPanel.add(changeEmailLabel);
				
				paymentField = new JTextField();
				paymentField.setColumns(10);
				paymentField.setBounds(463, 360, 210, 41);
				accountSettingsPanel.add(paymentField);
				
				JButton submitPaymentButton = new JButton();
				submitPaymentButton.setVerticalTextPosition(SwingConstants.TOP);
				submitPaymentButton.setText("<html>Change Payment<br></html>");
				submitPaymentButton.setOpaque(true);
				submitPaymentButton.setHorizontalTextPosition(SwingConstants.CENTER);
				submitPaymentButton.setForeground(Color.WHITE);
				submitPaymentButton.setFont(new Font("Arial", Font.BOLD, 18));
				submitPaymentButton.setBackground(new Color(152, 0, 0));
				submitPaymentButton.setBounds(509, 412, 104, 63);
				accountSettingsPanel.add(submitPaymentButton);
				
				JLabel changePaymentLabel = new JLabel();
				changePaymentLabel.setText("Enter new Payment");
				changePaymentLabel.setHorizontalAlignment(SwingConstants.CENTER);
				changePaymentLabel.setFont(new Font("Arial", Font.BOLD, 18));
				changePaymentLabel.setBounds(439, 321, 245, 39);
				accountSettingsPanel.add(changePaymentLabel);
				
				JButton submitAddressButton = new JButton();
				submitAddressButton.setVerticalTextPosition(SwingConstants.TOP);
				submitAddressButton.setText("<html>Change Address<br></html>");
				submitAddressButton.setOpaque(true);
				submitAddressButton.setHorizontalTextPosition(SwingConstants.CENTER);
				submitAddressButton.setForeground(Color.WHITE);
				submitAddressButton.setFont(new Font("Arial", Font.BOLD, 18));
				submitAddressButton.setBackground(new Color(152, 0, 0));
				submitAddressButton.setBounds(509, 213, 104, 63);
				accountSettingsPanel.add(submitAddressButton);
				
				JLabel changeAddressLabel = new JLabel();
				changeAddressLabel.setText("Enter new Address");
				changeAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
				changeAddressLabel.setFont(new Font("Arial", Font.BOLD, 18));
				changeAddressLabel.setBounds(439, 122, 245, 39);
				accountSettingsPanel.add(changeAddressLabel);
				
				addressField = new JTextField();
				addressField.setColumns(10);
				addressField.setBounds(463, 161, 210, 41);
				accountSettingsPanel.add(addressField);
				orderingPanel.setLayout(null);
				
				submitPhoneButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(phoneField.getText() != null) {
							currentSession.phone = phoneField.getText();
							liveDatabase.saveCustomerData();
						}
					}
				});
				submitEmailButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(emailField.getText() != null) {
							currentSession.email = emailField.getText();
							liveDatabase.saveCustomerData();
						}
					}
				});
				submitPaymentButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(paymentField.getText() != null) {
							currentSession.payment = paymentField.getText();
							liveDatabase.saveCustomerData();
						}
					}
				});
				submitAddressButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(addressField.getText() != null) {
							currentSession.address = addressField.getText();
							liveDatabase.saveCustomerData();
						}
					}
				});
				
	}
}
