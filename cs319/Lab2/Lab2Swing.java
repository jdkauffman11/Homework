import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JTabbedPane;
import javax.swing.JList;


public class Lab2Swing extends JFrame {
	private static DataModel dm;
	private JPanel contentPane;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dm = new DataModel();
					Lab2Swing frame = new Lab2Swing();
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
	@SuppressWarnings("unchecked")
	public Lab2Swing() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500,400));
		setResizable(false);
		getContentPane().setLayout(null);
		 
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 500, 378);
		tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		
		JPanel panel = new JPanel();
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(19, 5, 260, 132);
		list = new JList();
		scroll.setViewportView(list);
        list.setSelectionBackground(Color.yellow);
        list.setSelectionForeground(Color.black);
		
		JButton addButton = new JButton("Add");
		addButton.setBounds(142, 305, 100, 29);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setBounds(237, 305, 100, 29);
		
		scroll.setBounds(5,5,470,300);
		
		panel.setLayout(null);
		panel.add(scroll);
		panel.add(addButton);
		panel.add(removeButton);
		tabbedPane.addTab("List", null, panel, null);
		
		
		JTree tree = new JTree();
		tabbedPane.addTab("Tree", null, tree, null);
		
		tabbedPane.addTab("Table", null);
		
		getContentPane().add(tabbedPane);
		
       
    @SuppressWarnings("rawtypes")
	DefaultListModel listModel = new DefaultListModel();
	for(int i = 0; i < dm.getSize(); i++)
	{
	  listModel.addElement(dm.getElementAt(i));
	}
	list.setModel(listModel);
        
	
	addButton.addActionListener(new ActionListener()
	{
		  public void actionPerformed(ActionEvent e)
		  {
	
			  AddBox();
		  }
	});
	
	removeButton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(!list.isSelectionEmpty())
			{
				dm.RemoveFromList(list.getSelectedIndex());
			
				DefaultListModel listModel = new DefaultListModel();
				for(int i = 0; i < dm.getSize(); i++)
				{
					listModel.addElement(dm.getElementAt(i));
				}
				list.setModel(listModel);
			}
			
		}
	});
        
	}
	//Creates the box to add items to the list
	private void AddBox()
	{
		final JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(400,150));
		frame.setResizable(false);
		frame.setTitle("Enter new company name");
		frame.getContentPane().setLayout(null);
		
		final JLabel label = new JLabel();
		label.setText("What is the new company?");
		label.setBounds(50, 10, 200, 50);
		frame.getContentPane().add(label);
		
		final JTextField textField = new JTextField();
		textField.setBounds(45,45,300,25);
		frame.getContentPane().add(textField);
		
		JButton okButton = new JButton("OK");
		okButton.setBounds(110, 80, 85, 25);
		frame.getContentPane().add(okButton);
		okButton.addActionListener(new ActionListener()
		{
			  @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			  {
				  //Not the most efficient way of repopulating the list but I am not familiar enough with the
				  //libraries to know of a more efficient way.
				 	dm.AddToList(textField.getText());
				 	@SuppressWarnings("rawtypes")
					DefaultListModel listModel = new DefaultListModel();
					for(int i = 0; i < dm.getSize(); i++)
					{
					  listModel.addElement(dm.getElementAt(i));
					}
					list.setModel(listModel);
					frame.dispose();
			  }
			});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(195, 80, 85, 25);
		frame.getContentPane().add(cancelButton);
		cancelButton.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
		
				 	frame.dispose();
			  }
			});
		
//		BufferedImage myPicture = ImageIO.read(new File("question.png"));
//		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//		picLabel.setBounds(20, 20, 45, 45);
//		frame.add(picLabel);
		
		
	}
}
