import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AdminWindow {
		private Statement stmt;
		private JFrame frame;
		private JTable stockTable;
		private JTable sellTable;
		
		ResultSet rs;
	public AdminWindow(Statement stmt) throws Exception {
		this.stmt = stmt;
		
		String[] menu = {"메뉴 선택","아메리카노 ICE","카페라떼 ICE","돌체라떼","유니콘 프라푸치노","아메리카노 HOT","카페라떼 HOT","카페모카","페퍼민트차"};
		
		frame = new JFrame();
		frame.setTitle("cafeLS [admin]");
		frame.setBounds(100, 100, 447, 331);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocation(330, 429);
		frame.setResizable(false);
		
		JLabel stockLab = new JLabel("재고 현황");
		stockLab.setFont(new Font("굴림", Font.PLAIN, 12));
		stockLab.setBounds(12, 22, 57, 15);
		frame.getContentPane().add(stockLab);
		
		JLabel sellLab = new JLabel("판매 현황");
		sellLab.setFont(new Font("굴림", Font.PLAIN, 12));
		sellLab.setBounds(231, 22, 57, 15);
		frame.getContentPane().add(sellLab);
		
		JScrollPane stockSP = new JScrollPane();
		stockSP.setBounds(12, 47, 190, 151);
		frame.getContentPane().add(stockSP);
		
		String stock[] = {"메뉴","재고"};
		DefaultTableModel dtm  = new DefaultTableModel(stock, 0);
		String sql = "SELECT NAME, QUANTITY FROM ITEMS";

		stockTable = new JTable(dtm);
		
		rs = stmt.executeQuery(sql);
			while(rs.next()){
				dtm.addRow(new Object[] {rs.getString("name"),rs.getString("quantity")});
			}
		
		stockSP.setViewportView(stockTable);
		
		stockTable.setRowSelectionAllowed(false);
		stockTable.setEnabled(false);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		stockTable.getTableHeader().setReorderingAllowed(false);
		stockTable.getColumn("메뉴").setPreferredWidth(100);
		stockTable.getColumn("메뉴").setCellRenderer(celAlignCenter);
		stockTable.getColumn("재고").setPreferredWidth(3);
		stockTable.getColumn("재고").setCellRenderer(celAlignCenter);
		
		JScrollPane sellPane = new JScrollPane();
		sellPane.setBounds(231, 47, 190, 151);
		frame.getContentPane().add(sellPane);
		
		String sell[] = {"메뉴","판매량"};
		DefaultTableModel dtm2 = new DefaultTableModel(sell, 0);
		String sql2 = "select i.name, sum(o.quantity) as sell  from items i, orders o where i.item_code=o.item_code group by i.item_code order by i.item_code;";
		
		sellTable = new JTable(dtm2);
		
		rs = stmt.executeQuery(sql2);
			while(rs.next()) {
				dtm2.addRow(new Object[] {rs.getString("name"), rs.getString("sell")});
			}
			
		sellPane.setViewportView(sellTable);
		
		sellTable.getTableHeader().setReorderingAllowed(false);
		sellTable.getColumn("메뉴").setPreferredWidth(100);
		sellTable.getColumn("메뉴").setCellRenderer(celAlignCenter);
		sellTable.getColumn("판매량").setPreferredWidth(3);
		sellTable.getColumn("판매량").setCellRenderer(celAlignCenter);
		
		JComboBox orderCB = new JComboBox<String>(menu);
		orderCB.setBounds(12, 243, 136, 21);
		orderCB.setFont(new Font("굴림", Font.PLAIN, 12));
		frame.getContentPane().add(orderCB);
		
		JLabel orderLab = new JLabel("상품 발주");
		orderLab.setFont(new Font("굴림", Font.PLAIN, 12));
		orderLab.setBounds(12, 218, 57, 15);
		frame.getContentPane().add(orderLab);
		
		JButton orderBtn = new JButton("발주하기");
		orderBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String orderMenu = (String) orderCB.getSelectedItem();
				if(orderMenu.equals("메뉴 선택")) {				
					JOptionPane.showMessageDialog(null,"발주할 메뉴를 선택해주세요.");
						try {
							new AdminWindow(stmt);
							frame.setVisible(false);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}else {
					String sql3 = "UPDATE ITEMS SET QUANTITY = QUANTITY + 10 WHERE NAME ='"+orderMenu+"'";
					try {
						int i = stmt.executeUpdate(sql3);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
					JOptionPane.showMessageDialog(null, orderMenu+" 10잔 발주 되었습니다.");
					orderCB.setSelectedIndex(0);
					try {
						new AdminWindow(stmt);
						frame.setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		orderBtn.setBounds(160, 242, 88, 23);
		frame.getContentPane().add(orderBtn);
	}
}
