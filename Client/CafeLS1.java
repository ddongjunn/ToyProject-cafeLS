import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CafeLS1 {
	private JFrame orderH;
	private JTable table;
	private JLabel orderHisLab;
		
	public CafeLS1(BufferedReader br, PrintWriter pw, String id) {
		JFrame Frame;
		Frame= new JFrame();
		Frame.setTitle("cafeLS");
		Frame.setBounds(100, 100, 329, 370);
		Frame.getContentPane().setLayout(null);
		Frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		Frame.setResizable(false);
		Frame.setLocationRelativeTo(null);
		
		Frame.setVisible(true);
		
		ImageIcon logo = new ImageIcon("logo.png");
		
		String [] p = null;
		String [] column = {"번호","주문메뉴","수량","주문날짜"};
		DefaultTableModel dtm = new DefaultTableModel(column,0);
		
		Object[] row = new Object[4];
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 313, 331);
		Frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel welcomeL = new JLabel("WELCOME TO \"cafeLS\"");
		welcomeL.setFont(new Font("Century Gothic", Font.BOLD, 22));
		welcomeL.setBounds(39, 10, 247, 58);
		panel.add(welcomeL);
		
		JLabel logoL = new JLabel("");
		logoL.setIcon(logo);
		logoL.setBounds(85, 54, 187, 161);
		panel.add(logoL);
		
		JButton eatInBtn = new JButton("EAT IN");
		eatInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Order(br,pw,id);
			}
		});
		eatInBtn.setBounds(41, 214, 97, 23);
		panel.add(eatInBtn);
		
		JButton takeOutBtn = new JButton("TAKE OUT");
		takeOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Order(br,pw,id);
			}
		});
		takeOutBtn.setBounds(162, 214, 97, 23);
		panel.add(takeOutBtn);
		
		JButton chatBtn = new JButton("Check my order history");
		chatBtn.setFont(new Font("굴림", Font.BOLD, 12));
		chatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 1;

				pw.println("orderHistory:");

				String result = null;
				try {
					while(!((result = br.readLine()).equals("end"))) {
					String [] p = result.split(";");
					row[0] = i;
					row[1] = p[0]; 
					row[2] = p[1];
					row[3] = p[2];
					
					dtm.addRow(row);
					i++;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

	
				orderH = new JFrame();
				orderH.setTitle("CafeJS");
				orderH.setBounds(100, 100, 410, 343);
				orderH.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				orderH.getContentPane().setLayout(null);
				
				orderH.setResizable(false);
				orderH.setLocation(1150, 200);
				
				JScrollPane sPane = new JScrollPane();
				sPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				sPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				sPane.setBounds(26, 43, 344, 208);
				orderH.getContentPane().add(sPane);
				
				table = new JTable(dtm);
				table.setRowSelectionAllowed(false);
				table.setEnabled(false);
				sPane.setViewportView(table);
				
				//테이블 간격 조절
				DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
				celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
				
				table.getColumn("번호").setPreferredWidth(3);
				table.getColumn("번호").setCellRenderer(celAlignCenter);
				table.getColumn("주문메뉴").setPreferredWidth(100);
				table.getColumn("주문메뉴").setCellRenderer(celAlignCenter);
				table.getColumn("수량").setPreferredWidth(3);
				table.getColumn("수량").setCellRenderer(celAlignCenter);
				table.getColumn("주문날짜").setPreferredWidth(100);
				table.getColumn("주문날짜").setCellRenderer(celAlignCenter);
				
				orderHisLab = new JLabel("<나의 주문내역 확인 >");
				orderHisLab.setFont(new Font("돋움", Font.BOLD, 15));
				orderHisLab.setBounds(121, 10, 162, 23);
				orderH.getContentPane().add(orderHisLab);
				
				
				orderH.setVisible(true);
				
				JButton btnNewButton = new JButton("확인");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						orderH.dispose();
						dtm.setRowCount(0);
					}
				});
				btnNewButton.setBounds(150, 265, 97, 23);
				orderH.getContentPane().add(btnNewButton);
			}
			
		});
		chatBtn.setBounds(41, 259, 218, 40);
		panel.add(chatBtn);
	}
}
