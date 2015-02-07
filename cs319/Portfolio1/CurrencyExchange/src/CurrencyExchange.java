import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CurrencyExchange{
	private JFrame frame;
	private JTextArea infoText;
	private JTextField textField;
	private JTextField totalField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrencyExchange window = new CurrencyExchange();
					window.frame.setVisible(true);
					window.frame.setSize(370, 220);
					window.frame.setTitle("Currency Exchange");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public CurrencyExchange() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final String[] images = new String[]
				{
					"us.jpg",
					"aus.jpg",
					"canada.jpg",
					"euro.jpeg",
					"peso.jpeg",
					"real.jpeg",
					"pound.jpeg",
					"yen.jpeg",
				};
		
		
		final Double[] values = new Double[]{
				1.00,
				1.28,
				1.25,
				0.88,
				14.84,
				2.78,
				0.66,
				119.14
		};
		
		final String[] infoArray = new String[]{
				"The U.S. dollar is fiat money. It is the currency most used in international transactions "
				+ "and is the world's most dominant reserve currency. Several countries use it as their "
				+ "official currency, and in many others it is the de facto currency. Besides the United "
				+ "States, it is also used as the sole currency in two British Overseas Territories in the "
				+ "Caribbean: the British Virgin Islands and the Turks and Caicos islands. Some cities around "
				+ "the world use the U.S. Dollar as the de facto currency alongside its own.",
				
				"The currency of the Commonwealth of Australia, including Christmas Island, Cocos (Keeling) "
				+ "Islands, and Norfolk Island, as well as the independent Pacific Island states of Kiribati, "
				+ "Nauru and Tuvalu. Within Australia it is almost always abbreviated with the dollar sign ($), "
				+ "with A$ sometimes used to distinguish it from other dollar-denominated currencies. "
				+ "It is subdivided into 100 cents.",
				
				"The Canadian dollar (symbol: $; code: CAD) is the currency of Canada. "
				+ "It is abbreviated with the dollar sign $, or sometimes C$ to distinguish it from other "
				+ "dollar-denominated currencies. It is divided into 100 cents.",
				
				"The euro (sign: €; code: EUR) is the official currency of the eurozone, which consists of 19 of the 28 member "
				+ "states of the European Union: Austria, Belgium, Cyprus, Estonia, Finland, France, Germany, Greece, Ireland, "
				+ "Italy, Latvia, Lithuania, Luxembourg, Malta, the Netherlands, Portugal, Slovakia, Slovenia, and Spain."
				+ " The currency is also officially used by the institutions of the European Union and four other European "
				+ "countries, as well as unilaterally by two others, and is consequently used daily by some 337 million "
				+ "Europeans as of 2015. Outside of Europe, a number of overseas territories of EU members also use the euro "
				+ "as their currency.",
				
				"The Mexican peso (sign: $; code: MXN) is the currency of Mexico. "
				+ "Modern peso and dollar currencies have a common origin in the 15th–19th century Spanish dollar,"
				+ " most continuing to use its sign, '&'. The Mexican peso is the 8th most traded currency in the world, "
				+ "the third most traded in the Americas (after the United States dollar and Canadian dollar), "
				+ "and the most traded currency in Latin America.",
				
				"The real (/reɪˈɑːl/; Brazilian Portuguese: [ʁeˈaw]; pl. reais) is the present-day currency of Brazil. "
				+ "Its sign is R$ and its ISO code is BRL. It is subdivided into 100 centavos (hundredths). "
				+ "In Portuguese the word real means both 'royal' and 'real'.",
				
				"The pound sterling (symbol: £; ISO code: GBP), commonly known simply as the pound, is the "
				+ "official currency of the United Kingdom, Jersey, Guernsey, the Isle of Man, South Georgia and "
				+ "the South Sandwich Islands, the British Antarctic Territory, and Tristan da Cunha. It is "
				+ "subdivided into 100 pence (singular: penny). A number of nations that do not use sterling "
				+ "also have currencies called the pound. At various times, the pound sterling was commodity "
				+ "money or bank notes backed by silver or gold, but it is currently fiat money, backed only by "
				+ "the economy in the areas where it is accepted. The pound sterling is the world's oldest "
				+ "currency still in use.",
				
				"The Japanese yen (円 or 圓 en?, symbol: ¥; code: JPY) is the official currency of Japan. "
				+ "It is the third most traded currency in the foreign exchange market after the United States "
				+ "dollar and the euro. It is also widely used as a reserve currency after the U.S. dollar, "
				+ "the euro, and the pound sterling."
		};
		
		
		
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(600,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		final JLabel imageLabel = new JLabel();
		imageLabel.setBounds(6, 237, 300, 175);
		imageLabel.setIcon(new javax.swing.ImageIcon(images[0]));
		frame.getContentPane().add(imageLabel);
		
		
		final JComboBox<String> comboBox1 = new JComboBox<String>();
		comboBox1.setModel(new DefaultComboBoxModel<String>(new String[] {"Dollar (USA)", "Dollar (AUS)", "Dollar (Canada)", "Euro (Europe)", "Peso (Mexico)", "Real (Brazil)", "Pound (U.K.)", "Yen (Japan)"}));
		comboBox1.setToolTipText("");
		comboBox1.setBounds(127, 9, 200, 20);
		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				infoText.setText(infoArray[comboBox1.getSelectedIndex()]);
				imageLabel.setIcon(new javax.swing.ImageIcon(images[comboBox1.getSelectedIndex()]));	

			}
		});
		frame.getContentPane().add(comboBox1);
		

		
		final JComboBox<String> comboBox2 = new JComboBox<String>();
		comboBox2.setModel(new DefaultComboBoxModel<String>(new String[] {"Dollar (USA)", "Dollar (AUS)", "Dollar (Canada)", "Euro (Europe)", "Peso (Mexico)", "Real (Brazil)", "Pound (U.K.)", "Yen (Japan)"}));
		comboBox2.setToolTipText("");
		comboBox2.setBounds(127, 96, 200, 20);
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				infoText.setText(infoArray[comboBox2.getSelectedIndex()]);
				imageLabel.setIcon(new javax.swing.ImageIcon(images[comboBox2.getSelectedIndex()]));	

			}
		});
		frame.getContentPane().add(comboBox2);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(275, 449, 89, 23);
		frame.getContentPane().add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				System.exit(0);
			}
		});
		
		JLabel infoLabel = new JLabel("Currency Information:");
		infoLabel.setBounds(329, 13, 247, 16);
		frame.getContentPane().add(infoLabel);
		
		infoText = new JTextArea();
		infoText.setBounds(329, 39, 265, 375);
		frame.getContentPane().add(infoText);
		
		infoText.setText(infoArray[comboBox1.getSelectedIndex()]);
		infoText.setColumns(10);
		infoText.setAutoscrolls(true);
		infoText.setEditable(false);
		infoText.setLineWrap(true);
		infoText.setWrapStyleWord(true);
		
		
		
		textField = new JTextField();
		textField.setBounds(127, 41, 195, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCurrencyType = new JLabel("Currency Type #1:");
		lblCurrencyType.setBounds(6, 10, 114, 16);
		frame.getContentPane().add(lblCurrencyType);
		JLabel label = new JLabel("Amount #1:");
		label.setBounds(6, 45, 99, 20);
		frame.getContentPane().add(label);
		
		JLabel amount1 = new JLabel("Currency Type #2:");
		amount1.setBounds(6, 97, 114, 16);
		frame.getContentPane().add(amount1);
		
		totalField = new JTextField();
		totalField.setBounds(127, 138, 190, 28);
		frame.getContentPane().add(totalField);
		totalField.setEditable(false);
		totalField.setColumns(10);
		
		JLabel totalLabel = new JLabel("Total:");
		totalLabel.setBounds(6, 142, 99, 20);
		frame.getContentPane().add(totalLabel);
		

		JButton exchangeButton = new JButton("Exchange");
		exchangeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText() == "" || textField.getText() == null)
				{
					JOptionPane.showMessageDialog(frame, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);

				}
				else
				{
					try{
						double value1 = Integer.parseInt(textField.getText());
						
						double total = value1 * values[comboBox1.getSelectedIndex()]*values[comboBox2.getSelectedIndex()];
					
						totalField.setText("" + total);
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(frame, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
				

			}
		});
		exchangeButton.setBounds(149, 449, 89, 23);
		frame.getContentPane().add(exchangeButton);
	
		
	}
}
