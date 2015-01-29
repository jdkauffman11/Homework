import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


public class Lab2Swing extends JFrame {
	private static DataModel dm;
	private JPanel contentPane;
	private JList list;
	private JTree tree;

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
	@SuppressWarnings({ "rawtypes" })
	public Lab2Swing() {
		
		//Creates Frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500,400));
		setResizable(false);
		getContentPane().setLayout(null);
		
		//Creates new TabbedPane and sets properties
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 500, 378);
		tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		
		//Creates new JPanel and scroll pane with specific properties
		JPanel listPanel = new JPanel();
		JScrollPane listScroll = new JScrollPane();
		listScroll.setBounds(5,5,470,300);
		
		//initializes list, sets it to the scroll pane
		list = new JList();
        list.setSelectionBackground(Color.yellow);
        list.setSelectionForeground(Color.black);
		listScroll.setViewportView(list);
		
		//Buttons added to add and remove items
		JButton addButton = new JButton("Add");
		addButton.setBounds(142, 305, 100, 29);
		JButton removeButton = new JButton("Remove");
		removeButton.setBounds(237, 305, 100, 29);
		
		//Adding properties to the panel
		listPanel.setLayout(null);
		listPanel.add(listScroll);
		listPanel.add(addButton);
		listPanel.add(removeButton);
		tabbedPane.addTab("List", null, listPanel, null);
		
		//Creating the tree for the tree tab
		JPanel treePanel = new JPanel();
		treePanel.setLayout(null);
		
		JScrollPane treeScroll = new JScrollPane();
		treeScroll.setBounds(5, 5, 470, 275);
		
		tree = new JTree(CreateInitialTree());
		treeScroll.setRowHeaderView(tree);
		
		treeScroll.setViewportView(tree);
		treePanel.add(treeScroll);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tabbedPane.addTab("Tree", null, treePanel, null);
		
		
		//Adds a button to the tree
		JButton treeAddButton = new JButton("Add");
		treeAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If there is no item selected, the AddTreeBox method will not be called, eliminating an error
				if(tree.getLastSelectedPathComponent() != null || tree.getModel().getRoot() == null)
				{
					AddTreeBox();
				}
			}
		});
		
		treeAddButton.setBounds(120, 292, 117, 29);
		treePanel.add(treeAddButton);
		
		JButton treeRemoveButton = new JButton("Remove");
		treeRemoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If there is no item selected, the AddTreeBox method will not be called, eliminating an error
				if(tree.getLastSelectedPathComponent() != null)
				{
					if(tree.getLastSelectedPathComponent() != tree.getModel().getRoot())
					{
						DefaultMutableTreeNode node;
						DefaultTreeModel model = (DefaultTreeModel) (tree.getModel());
						TreePath[] paths = tree.getSelectionPaths();
						for (int i = 0; i < paths.length; i++) 
						{
							node = (DefaultMutableTreeNode) (paths[i].getLastPathComponent());
							model.removeNodeFromParent(node);
						}
					}
					else
					{
						((DefaultTreeModel) tree.getModel()).setRoot(null);
					}
					
				}
			}
		});
		
		treeRemoveButton.setBounds(249, 292, 117, 29);
		treePanel.add(treeRemoveButton);
		
		//Table is added but not implemented because table is not used in this lab.
		tabbedPane.addTab("Table", null);
		
		
		getContentPane().add(tabbedPane);
		
       
    @SuppressWarnings("rawtypes")
    //Populates the list
	DefaultListModel listModel = new DefaultListModel();
	for(int i = 0; i < dm.getSize(); i++)
	{
	  listModel.addElement(dm.getElementAt(i));
	}
	list.setModel(listModel);
        
	//If add button is clicked the AddBox() method is called.
	addButton.addActionListener(new ActionListener()
	{
		  public void actionPerformed(ActionEvent e)
		  {
	
			  AddListBox();
		  }
	});
	
	//If Remove button is clicked, item is removed and list is repopulated
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
	private void AddListBox()
	{
		String company = (String) JOptionPane.showInputDialog(contentPane, 
				"What is the new company?", "Enter the new company name.", JOptionPane.QUESTION_MESSAGE);
		dm.AddToList(company);
	 	@SuppressWarnings("rawtypes")
		DefaultListModel listModel = new DefaultListModel();
		for(int i = 0; i < dm.getSize(); i++)
		{
		  listModel.addElement(dm.getElementAt(i));
		}
		list.setModel(listModel);
		

	}
	
	private void AddTreeBox()
	{// variable named animal because this tree is only to add animals
		String animal = (String) JOptionPane.showInputDialog(contentPane, 
				"What is the new animal?", "Enter new animal name.", JOptionPane.QUESTION_MESSAGE);
		if((DefaultMutableTreeNode) tree.getModel().getRoot() != null)
		{
			DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

			TreePath path = tree.getSelectionPath();
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();

			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(animal);
	    
			model.insertNodeInto(newNode, node, node.getChildCount());
		}
		else
		{
			((DefaultTreeModel) tree.getModel()).setRoot(new DefaultMutableTreeNode(animal));
		}
		
		
	    		
	}
	
	
	private DefaultMutableTreeNode CreateInitialTree()
	{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Animals");
		
		DefaultMutableTreeNode temp = new DefaultMutableTreeNode("Mammals");
		root.add(temp);
		temp.add(new DefaultMutableTreeNode("Human"));
		temp.add(new DefaultMutableTreeNode("Kangaroo"));
		temp.add(new DefaultMutableTreeNode("Elephant"));
		temp.add(new DefaultMutableTreeNode("Goat"));
		
		temp = new DefaultMutableTreeNode("Reptiles");
		root.add(temp);
		temp.add(new DefaultMutableTreeNode("Lizard"));
		temp.add(new DefaultMutableTreeNode("Boa"));
		temp.add(new DefaultMutableTreeNode("Iguana"));
		
		temp = new DefaultMutableTreeNode("Birds");
		root.add(temp);
		temp.add(new DefaultMutableTreeNode("Duck"));
		temp.add(new DefaultMutableTreeNode("Pigeon"));
		temp.add(new DefaultMutableTreeNode("Turkey"));
		temp.add(new DefaultMutableTreeNode("Goose"));
		
		temp = new DefaultMutableTreeNode("Insects");
		root.add(temp);
		temp.add(new DefaultMutableTreeNode("Termite"));
		temp.add(new DefaultMutableTreeNode("Ladybug"));
		temp.add(new DefaultMutableTreeNode("Fly"));
		temp.add(new DefaultMutableTreeNode("Ant"));
		
		temp = new DefaultMutableTreeNode("Aquatics");
		root.add(temp);
		temp.add(new DefaultMutableTreeNode("Swordfish"));
		temp.add(new DefaultMutableTreeNode("Shark"));
		temp.add(new DefaultMutableTreeNode("Whale"));
		
		return root;
		
	}
}
