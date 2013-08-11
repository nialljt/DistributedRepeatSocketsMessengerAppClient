package selectClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;



public class SelectClientFrame extends JFrame{
	
	private JPanel panel = new JPanel();
	private JScrollPane scrollPane;
	public static DefaultTableModel model = new DefaultTableModel();
	private JTable table = new JTable(model);	
	private JButton button = new JButton("CHAT"),
			        refreshButton = new JButton("REFRESH");
	
	Vector v = new Vector();
	
	public SelectClientFrame(){
		
		setLayout(new GridBagLayout());
		
		Border lowered = BorderFactory
				.createBevelBorder(BevelBorder.LOWERED);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(lowered);
		model.addColumn("Online Clients");
		scrollPane.setPreferredSize(new Dimension(300,300));
		add(scrollPane, getConstraints(0,0,4,1,GridBagConstraints.CENTER));
		add(button, getConstraints(1,2,2,1,GridBagConstraints.CENTER));
		add(refreshButton, getConstraints(2,2,2,1,GridBagConstraints.EAST));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runnable CreateAndShowProgressBar = new Runnable() {
					public void run(){
						scan();
					}
				};
				SwingUtilities.invokeLater(CreateAndShowProgressBar);
			}
		});
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setBounds((d.width - 400)/2, (d.height - 400)/2, 400, 400);
		setVisible(true);
		
	}
	
	private GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor){
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridheight = gridheight;
		c.gridwidth = gridwidth;
		c.anchor = anchor;
		return c;
	}

	public void scan() {	
		if(v.size() > 0){
			v.removeAllElements();
		}
		for (int i = 0; i < 254; i++) {
			Socket connection = new Socket();
			try {
				System.out.println("test connection "+ i);
				String ip = "192.168.1."+ i;
				connection.connect(new InetSocketAddress(ip,6789), 50);
				v.add(ip);
			} catch (IOException e) {
				System.out.println("top error");
			}
		}
		updateTable();
	}
	
	public void updateTable(){
		if(model.getRowCount() != 0){
			for(int i = 0; i<=model.getRowCount();i++){
				model.removeRow(0);//might have to be set to 0
				
			}
		}
		for(int x=0; x<v.size();x++){
			InetAddress address = null;
		    try {
		      address = InetAddress.getByName(v.get(x).toString());
			model.addRow(new Object[] {address.getHostName()});
			System.out.println(v.get(x).toString());
		    } catch (UnknownHostException e) {
			      System.out.println("Lower error bra");
			    }	
		}
	}
	
}
