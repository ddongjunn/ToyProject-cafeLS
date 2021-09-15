import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

public class Order {	
	private JFrame CafeLS2;
	JLayeredPane layeredPane;
	TextArea orderTa;
	private JTextField ameTF, latteTF, dolceTF, uniTF, hameTF, hlatteTF, hmochaTF, hpeppTF;
	int ameCount, latteCount, dolceCount, uniCount, hameCount, hlatteCount, hmochaCount, hpeppCount  = 0; 
	int price; //각 메뉴 가격 설정
	int ameTp, latteTp, dolceTp, uniTp, hameTp, hlatteTp, hmochaTp, hpeppTp; //각 메뉴 총 가격
	int totalPrice; //결제시 총 가격
	
	JButton ameMinusBtn, latteMinusBtn, dolceMinusBtn, uniMinusBtn, hameMinusBtn, hlatteMinusBtn, 
			hmochaMinusBtn, hpeppMinusBtn;
	
	//ImageIcon americanoHot, americanoIce, dolce, LatteHot, latteIce, mochaHot, peppeTea, unicorn;

	private JFrame CafeLS3;
	private JTable table;
	private JLabel orderHisLab;
	private JLabel totalPlab;
	JScrollPane sPane;
	
	JButton reOrder;
	
	Vector<Items> itemList = new Vector<Items>(); //메뉴 리스트	
	
	public Order(BufferedReader br, PrintWriter pw,String id) {
		pw.println("orderStart:");
		String result = null;
		try {
			while(!((result = br.readLine()).equals("end"))) {
			String [] p = result.split(":");
			itemList.add(new Items(Integer.parseInt(p[0]),p[1],Integer.parseInt(p[2]),Integer.parseInt(p[3])));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
				
		
		CafeLS2 = new JFrame();
		CafeLS2.setTitle("CafeLS");
		CafeLS2.setBounds(100, 100, 362, 583);
		CafeLS2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		CafeLS2.getContentPane().setLayout(null);
		
		CafeLS2.setResizable(false);
		CafeLS2.setLocationRelativeTo(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(12, 43, 332, 352);
		CafeLS2.getContentPane().add(layeredPane);
		
		CafeLS2.setVisible(true);
		
		String [] column = {"메뉴","수량","총 가격"};
		DefaultTableModel dtm = new DefaultTableModel(column,0);
		
		Object[] row = new Object[3];
		
		JPanel icePanel = new JPanel();
		icePanel.setLayout(null);
		icePanel.setBounds(0, 0, 332, 352);
		layeredPane.add(icePanel);
		
		JPanel amePan = new JPanel();
		amePan.setLayout(null);
		amePan.setBounds(12, 10, 145, 164);
		icePanel.add(amePan);
		
		JLabel ameIconLab = new JLabel("아메리카노 3000원");
		ameIconLab.setFont(new Font("굴림", Font.PLAIN, 12));
		ameIconLab.setBounds(3, 94, 152, 23);
		amePan.add(ameIconLab);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("americanoIce.png"));
		label_1.setBounds(12, 0, 107, 97);
		amePan.add(label_1);
		
		JButton ameAddBtn = new JButton("추가");
		ameAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = itemList.get(0).name;
				price = itemList.get(0).price;
				ameTp = price * ameCount;
				orderTa.append(name+"        \t "+price+"원"+"          "+ameCount+"\t   "+ameTp+"원\n");
				ameAddBtn.setEnabled(false);
				
				String str1 = ameCount+"잔";
				String str2 = ameTp+"원";
				row[0] = name;
				row[1] = str1;
				row[2] = str2;
				
				itemList.get(0).setOrderFlag(true);
				dtm.addRow(row);
				
			}
		});
		ameAddBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		ameAddBtn.setBounds(3, 139, 125, 23);
		amePan.add(ameAddBtn);
		
		JButton amePlusBtn = new JButton("+");
		amePlusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ameCount = ameCount +1;
				ameTF.setText(ameCount+"");
				ameAddBtn.setEnabled(true);
				if(itemList.get(0).quantity < ameCount) {
					JOptionPane.showMessageDialog(null, "현재 "+itemList.get(0).name+ " 재고가 부족합니다.\n 주문 가능 수량 : "+itemList.get(0).quantity+"잔");
					ameTF.setText(Integer.toString(itemList.get(0).quantity));
				}else if(ameCount>0){
					ameMinusBtn.setEnabled(true);
				}
			}
		});
		amePlusBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		amePlusBtn.setBounds(88, 115, 40, 23);
		amePan.add(amePlusBtn);
		
		ameMinusBtn = new JButton("-");
		ameMinusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
					if(ameCount>0) {
						ameCount = ameCount - 1;
						ameTF.setText(ameCount+"");
						ameAddBtn.setEnabled(true);
					}else {
						ameMinusBtn.setEnabled(false);
					}
			}
		});
		ameMinusBtn.setFont(new Font("굴림", Font.PLAIN, 8));
		ameMinusBtn.setBounds(3, 115, 39, 23);
		amePan.add(ameMinusBtn);
		
		ameTF = new JTextField();
		ameTF.setText("0");
		ameTF.setHorizontalAlignment(SwingConstants.CENTER);
		ameTF.setColumns(10);
		ameTF.setBounds(45, 115, 41, 23);
		amePan.add(ameTF);
		
		JPanel lattePan = new JPanel();
		lattePan.setLayout(null);
		lattePan.setBounds(169, 10, 145, 164);
		icePanel.add(lattePan);
		
		JLabel latteLab = new JLabel("카페라떼 4000원");
		latteLab.setFont(new Font("굴림", Font.PLAIN, 12));
		latteLab.setBounds(3, 94, 152, 23);
		lattePan.add(latteLab);
		
		JLabel latteIconLab = new JLabel("");
		latteIconLab.setIcon(new ImageIcon("LatteHot.png"));
		latteIconLab.setBounds(40, 0, 107, 97);
		lattePan.add(latteIconLab);
		
		JButton latteAddBtn = new JButton("추가");
		latteAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = itemList.get(1).name;
				price = itemList.get(1).price;
				latteTp = price * latteCount;
				orderTa.append(name+"           \t "+price+"원"+"          "+latteCount+"\t   "+latteTp+"원\n");
				latteAddBtn.setEnabled(false);
				
				String str1 = latteCount+"잔";
				String str2 = latteTp+"원";
				row[0] = name;
				row[1] = str1;
				row[2] = str2;
				
				itemList.get(1).setOrderFlag(true);
				dtm.addRow(row);
				
			}
		});
		latteAddBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		latteAddBtn.setBounds(3, 139, 125, 23);
		lattePan.add(latteAddBtn);
		
		JButton lattePlusBtn = new JButton("+");
		lattePlusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				latteCount = latteCount +1;
				latteTF.setText(latteCount+"");
				latteAddBtn.setEnabled(true);
				if(itemList.get(1).quantity < latteCount) {
					JOptionPane.showMessageDialog(null, "현재 "+itemList.get(1).name+ " 재고가 부족합니다.\n 주문 가능 수량 : "+itemList.get(1).quantity+"잔");
					latteTF.setText(Integer.toString(itemList.get(1).quantity));
				}else if(latteCount>0){
					latteMinusBtn.setEnabled(true);
				}
			}
		});
		lattePlusBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		lattePlusBtn.setBounds(88, 115, 40, 23);
		lattePan.add(lattePlusBtn);
		
		latteMinusBtn = new JButton("-");
		latteMinusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(latteCount>0) {
					latteCount = latteCount - 1;
					latteTF.setText(latteCount+"");
					latteAddBtn.setEnabled(true);
				}else {
					latteMinusBtn.setEnabled(false);
				}
			}
		});
		latteMinusBtn.setFont(new Font("굴림", Font.PLAIN, 8));
		latteMinusBtn.setBounds(3, 115, 39, 23);
		lattePan.add(latteMinusBtn);
		
		latteTF = new JTextField();
		latteTF.setText("0");
		latteTF.setHorizontalAlignment(SwingConstants.CENTER);
		latteTF.setColumns(10);
		latteTF.setBounds(45, 115, 41, 23);
		lattePan.add(latteTF);
		
		JPanel dolcePan = new JPanel();
		dolcePan.setLayout(null);
		dolcePan.setBounds(12, 184, 145, 164);
		icePanel.add(dolcePan);
		
		JLabel dolceLab = new JLabel("돌체라떼 4000원");
		dolceLab.setFont(new Font("굴림", Font.PLAIN, 12));
		dolceLab.setBounds(3, 94, 152, 23);
		dolcePan.add(dolceLab);
		
		JLabel dolceIconLab = new JLabel("");
		dolceIconLab.setIcon(new ImageIcon("dolce.png"));
		dolceIconLab.setBounds(12, 0, 107, 97);
		dolcePan.add(dolceIconLab);
		
		JButton dolceAddBtn = new JButton("추가");
		dolceAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = "돌체라떼";
				price = itemList.get(2).price;
				dolceTp = price * dolceCount;
				orderTa.append(name+"                 \t "+price+"원"+"          "+dolceCount+"\t   "+dolceTp+"원\n");
				dolceAddBtn.setEnabled(false);
				
				String str1 = dolceCount+"잔";
				String str2 = dolceTp+"원";
				row[0] = name;
				row[1] = str1;
				row[2] = str2;
				
				itemList.get(2).setOrderFlag(true);
				dtm.addRow(row);
				
			}
		});
		dolceAddBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		dolceAddBtn.setBounds(3, 139, 125, 23);
		dolcePan.add(dolceAddBtn);
		
		JButton dolcePlusBtn = new JButton("+");
		dolcePlusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dolceCount = dolceCount +1;
				dolceTF.setText(dolceCount+"");
				dolceAddBtn.setEnabled(true);
				if(itemList.get(2).quantity < dolceCount) {
					JOptionPane.showMessageDialog(null, "현재 "+itemList.get(2).name+ " 재고가 부족합니다.\n 주문 가능 수량 : "+itemList.get(2).quantity+"잔");
					dolceTF.setText(Integer.toString(itemList.get(2).quantity));
				}else if(dolceCount>0){
					dolceMinusBtn.setEnabled(true);
				}
			}
		});
		dolcePlusBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		dolcePlusBtn.setBounds(88, 115, 40, 23);
		dolcePan.add(dolcePlusBtn);
		
		dolceMinusBtn = new JButton("-");
		dolceMinusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dolceCount>0) {
					dolceCount = dolceCount - 1;
					dolceTF.setText(dolceCount+"");
					dolceAddBtn.setEnabled(true);
				}else {
					dolceMinusBtn.setEnabled(false);
				}
			}
		});
		dolceMinusBtn.setFont(new Font("굴림", Font.PLAIN, 8));
		dolceMinusBtn.setBounds(3, 115, 39, 23);
		dolcePan.add(dolceMinusBtn);
		
		dolceTF = new JTextField();
		dolceTF.setText("0");
		dolceTF.setHorizontalAlignment(SwingConstants.CENTER);
		dolceTF.setColumns(10);
		dolceTF.setBounds(45, 115, 41, 23);
		dolcePan.add(dolceTF);
		
		JPanel unicornPan = new JPanel();
		unicornPan.setLayout(null);
		unicornPan.setBounds(169, 184, 145, 164);
		icePanel.add(unicornPan);
		
		JLabel uniLab = new JLabel("유니콘 프라푸치노 5000원");
		uniLab.setFont(new Font("굴림", Font.PLAIN, 12));
		uniLab.setBounds(3, 94, 152, 23);
		unicornPan.add(uniLab);
		
		JLabel uniIconLab = new JLabel("");
		uniIconLab.setIcon(new ImageIcon("unicorn.png"));
		uniIconLab.setBounds(12, 0, 107, 97);
		unicornPan.add(uniIconLab);
		
		JButton uniAddBtn = new JButton("추가");
		uniAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = itemList.get(3).name;
				price = itemList.get(3).price;
				uniTp = price * uniCount;
				orderTa.append(name+"    \t "+price+"원"+"          "+uniCount+"\t   "+uniTp+"원\n");
				uniAddBtn.setEnabled(false);
				
				String str1 = uniCount+"잔";
				String str2 = uniTp+"원";
				row[0] = name;
				row[1] = str1;
				row[2] = str2;
				
				itemList.get(3).setOrderFlag(true);
				dtm.addRow(row);
				
			}
		});
		uniAddBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		uniAddBtn.setBounds(3, 139, 125, 23);
		unicornPan.add(uniAddBtn);
		
		JButton uniPlusBtn = new JButton("+");
		uniPlusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uniCount = uniCount +1;
				uniTF.setText(uniCount+"");
				uniAddBtn.setEnabled(true);
				if(itemList.get(3).quantity < uniCount) {
					JOptionPane.showMessageDialog(null, "현재 "+itemList.get(3).name+ " 재고가 부족합니다.\n 주문 가능 수량 : "+itemList.get(3).quantity+"잔");
					uniTF.setText(Integer.toString(itemList.get(3).quantity));
				}else if(uniCount>0){
					uniMinusBtn.setEnabled(true);
				}
			}
		});
		uniPlusBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		uniPlusBtn.setBounds(88, 115, 40, 23);
		unicornPan.add(uniPlusBtn);
		
		uniMinusBtn = new JButton("-");
		uniMinusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(uniCount>0) {
					uniCount = uniCount - 1;
					uniTF.setText(uniCount+"");
					uniAddBtn.setEnabled(true);
				}else {
					uniMinusBtn.setEnabled(false);
				}
			}
		});
		uniMinusBtn.setFont(new Font("굴림", Font.PLAIN, 8));
		uniMinusBtn.setBounds(3, 115, 39, 23);
		unicornPan.add(uniMinusBtn);
		
		uniTF = new JTextField();
		uniTF.setText("0");
		uniTF.setHorizontalAlignment(SwingConstants.CENTER);
		uniTF.setColumns(10);
		uniTF.setBounds(45, 115, 41, 23);
		unicornPan.add(uniTF);
		
		JPanel hotPan = new JPanel();
		hotPan.setLayout(null);
		hotPan.setBounds(0, 0, 332, 352);
		layeredPane.add(hotPan);
		
		JPanel hamePan = new JPanel();
		hamePan.setLayout(null);
		hamePan.setBounds(12, 10, 145, 164);
		hotPan.add(hamePan);
		
		JLabel hameLab = new JLabel("아메리카노 3000원");
		hameLab.setFont(new Font("굴림", Font.PLAIN, 12));
		hameLab.setBounds(3, 94, 130, 23);
		hamePan.add(hameLab);
		
		JLabel hameIconLab = new JLabel("");
		hameIconLab.setIcon(new ImageIcon("americanoHot.png"));
		hameIconLab.setBounds(23, 0, 96, 97);
		hamePan.add(hameIconLab);
		
		JButton hameAddBtn = new JButton("추가");
		hameAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = itemList.get(4).name;
				price = itemList.get(4).price;
				hameTp = price * hameCount;
				orderTa.append(name+"        \t "+price+"원"+"          "+hameCount+"\t   "+hameTp+"원\n");
				hameAddBtn.setEnabled(false);
				
				String str1 = hameCount+"잔";
				String str2 = hameTp+"원";
				row[0] = name;
				row[1] = str1;
				row[2] = str2;
				
				itemList.get(4).setOrderFlag(true);
				dtm.addRow(row);

			}
		});
		hameAddBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		hameAddBtn.setBounds(3, 139, 125, 23);
		hamePan.add(hameAddBtn);
		
		JButton hamePlusBtn = new JButton("+");
		hamePlusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hameCount = hameCount +1;
				hameTF.setText(hameCount+"");
				hameAddBtn.setEnabled(true);
				if(itemList.get(4).quantity < hameCount) {
					JOptionPane.showMessageDialog(null, "현재 "+itemList.get(4).name+ " 재고가 부족합니다.\n 주문 가능 수량 : "+itemList.get(4).quantity+"잔");
					hameTF.setText(Integer.toString(itemList.get(4).quantity));
				}else if(hameCount>0){
					hameMinusBtn.setEnabled(true);
				}
			}
		});
		hamePlusBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		hamePlusBtn.setBounds(88, 115, 40, 23);
		hamePan.add(hamePlusBtn);
		
		hameMinusBtn = new JButton("-");
		hameMinusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hameCount>0) {
					hameCount = hameCount - 1;
					hameTF.setText(hameCount+"");
					hameAddBtn.setEnabled(true);
				}else {
					hameMinusBtn.setEnabled(false);
				}
			}
		});
		
		hameMinusBtn.setFont(new Font("굴림", Font.PLAIN, 8));
		hameMinusBtn.setBounds(3, 115, 39, 23);
		hamePan.add(hameMinusBtn);
		
		hameTF = new JTextField();
		hameTF.setText("0");
		hameTF.setHorizontalAlignment(SwingConstants.CENTER);
		hameTF.setColumns(10);
		hameTF.setBounds(45, 115, 41, 23);
		hamePan.add(hameTF);
		
		JPanel hlattePan = new JPanel();
		hlattePan.setLayout(null);
		hlattePan.setBounds(169, 10, 145, 164);
		hotPan.add(hlattePan);
		
		JLabel hlatteLab = new JLabel("카페라떼 4000원");
		hlatteLab.setFont(new Font("굴림", Font.PLAIN, 12));
		hlatteLab.setBounds(3, 94, 152, 23);
		hlattePan.add(hlatteLab);
		
		JLabel hlatteIconLab = new JLabel("");
		hlatteIconLab.setIcon(new ImageIcon("LatteHot.png"));
		hlatteIconLab.setBounds(28, 0, 107, 97);
		hlattePan.add(hlatteIconLab);
		
		JButton hlatteAddBtn = new JButton("추가");
		hlatteAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = itemList.get(5).name;
				price = itemList.get(5).price;
				hlatteTp = price * hlatteCount;
				orderTa.append(name+"            \t "+price+"원"+"          "+hlatteCount+"\t   "+hlatteTp+"원\n");
				hlatteAddBtn.setEnabled(false);
				
				String str1 = hlatteCount+"잔";
				String str2 = hlatteTp+"원";
				row[0] = name;
				row[1] = str1;
				row[2] = str2;
				
				itemList.get(5).setOrderFlag(true);
				dtm.addRow(row);
				
			}
		});
		hlatteAddBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		hlatteAddBtn.setBounds(3, 139, 125, 23);
		hlattePan.add(hlatteAddBtn);
		
		JButton hlattePlusBtn = new JButton("+");
		hlattePlusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hlatteCount = hlatteCount +1;
				hlatteTF.setText(hlatteCount+"");
				hlatteAddBtn.setEnabled(true);
				if(itemList.get(5).quantity < hlatteCount) {
					JOptionPane.showMessageDialog(null, "현재 "+itemList.get(5).name+ " 재고가 부족합니다.\n 주문 가능 수량 : "+itemList.get(5).quantity+"잔");
					hlatteTF.setText(Integer.toString(itemList.get(5).quantity));
				}else if(hlatteCount>0){
					hlatteMinusBtn.setEnabled(true);
				}
			}
		});
		hlattePlusBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		hlattePlusBtn.setBounds(88, 115, 40, 23);
		hlattePan.add(hlattePlusBtn);
		
		hlatteMinusBtn = new JButton("-");
		hlatteMinusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hlatteCount>0) {
					hlatteCount = hlatteCount - 1;
					hlatteTF.setText(hlatteCount+"");
					hlatteAddBtn.setEnabled(true);
				}else {
					hlatteMinusBtn.setEnabled(false);
				}
			}
		});
		hlatteMinusBtn.setFont(new Font("굴림", Font.PLAIN, 8));
		hlatteMinusBtn.setBounds(3, 115, 39, 23);
		hlattePan.add(hlatteMinusBtn);
		
		hlatteTF = new JTextField();
		hlatteTF.setText("0");
		hlatteTF.setHorizontalAlignment(SwingConstants.CENTER);
		hlatteTF.setColumns(10);
		hlatteTF.setBounds(45, 115, 41, 23);
		hlattePan.add(hlatteTF);
		
		JPanel hmochaPan = new JPanel();
		hmochaPan.setLayout(null);
		hmochaPan.setBounds(12, 184, 145, 164);
		hotPan.add(hmochaPan);
		
		JLabel hmochaLab = new JLabel("카페모카 4000원");
		hmochaLab.setFont(new Font("굴림", Font.PLAIN, 12));
		hmochaLab.setBounds(3, 94, 152, 23);
		hmochaPan.add(hmochaLab);
		
		JLabel hmochaIconLab = new JLabel("");
		hmochaIconLab.setIcon(new ImageIcon("mochaHot.png"));
		hmochaIconLab.setBounds(25, 0, 107, 97);
		hmochaPan.add(hmochaIconLab);
		
		JButton hmochaAddBtn = new JButton("추가");
		hmochaAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = itemList.get(6).name;
				price = itemList.get(6).price;
				hmochaTp = price * hmochaCount;
				orderTa.append(name+"                \t "+price+"원"+"          "+hmochaCount+"\t   "+hmochaTp+"원\n");
				hmochaAddBtn.setEnabled(false);
				
				String str1 = hmochaCount+"잔";
				String str2 = hmochaTp+"원";
				row[0] = name;
				row[1] = str1;
				row[2] = str2;
				
				itemList.get(6).setOrderFlag(true);
				dtm.addRow(row);

			}
		});
		hmochaAddBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		hmochaAddBtn.setBounds(3, 139, 125, 23);
		hmochaPan.add(hmochaAddBtn);
		
		JButton hmochaPlusBtn = new JButton("+");
		hmochaPlusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hmochaCount = hmochaCount +1;
				hmochaTF.setText(hmochaCount+"");
				hmochaAddBtn.setEnabled(true);
				if(itemList.get(6).quantity < hmochaCount) {
					JOptionPane.showMessageDialog(null, "현재 "+itemList.get(6).name+ " 재고가 부족합니다.\n 주문 가능 수량 : "+itemList.get(6).quantity+"잔");
					hmochaTF.setText(Integer.toString(itemList.get(6).quantity));
				}else if(hmochaCount>0){
					hmochaMinusBtn.setEnabled(true);
				}
			}
		});
		hmochaPlusBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		hmochaPlusBtn.setBounds(88, 115, 40, 23);
		hmochaPan.add(hmochaPlusBtn);
		
		hmochaMinusBtn = new JButton("-");
		hmochaMinusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hmochaCount>0) {
					hmochaCount = hmochaCount - 1;
					hmochaTF.setText(hmochaCount+"");
					hmochaAddBtn.setEnabled(true);
				}else {
					hmochaMinusBtn.setEnabled(false);
				}
			}
		});
		hmochaMinusBtn.setFont(new Font("굴림", Font.PLAIN, 8));
		hmochaMinusBtn.setBounds(3, 115, 39, 23);
		hmochaPan.add(hmochaMinusBtn);
		
		hmochaTF = new JTextField();
		hmochaTF.setText("0");
		hmochaTF.setHorizontalAlignment(SwingConstants.CENTER);
		hmochaTF.setColumns(10);
		hmochaTF.setBounds(45, 115, 41, 23);
		hmochaPan.add(hmochaTF);
		
		JPanel hpeppPan = new JPanel();
		hpeppPan.setLayout(null);
		hpeppPan.setBounds(169, 184, 145, 164);
		hotPan.add(hpeppPan);
		
		JLabel hpeppLab = new JLabel("페퍼민트차 3500원");
		hpeppLab.setFont(new Font("굴림", Font.PLAIN, 12));
		hpeppLab.setBounds(3, 94, 152, 23);
		hpeppPan.add(hpeppLab);
		
		JLabel hpeppIconLab = new JLabel("");
		hpeppIconLab.setIcon(new ImageIcon("peppeTea.png"));
		hpeppIconLab.setBounds(12, 0, 107, 97);
		hpeppPan.add(hpeppIconLab);
		
		JButton hpeppAddBtn = new JButton("추가");
		hpeppAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = itemList.get(7).name;
				price = itemList.get(7).price;
				hpeppTp = price * hpeppCount;
				orderTa.append(name+"            \t "+price+"원"+"          "+hpeppCount+"\t   "+hpeppTp+"원\n");
				hpeppAddBtn.setEnabled(false);
				
				String str1 = hpeppCount+"잔";
				String str2 = hpeppTp+"원";
				row[0] = name;
				row[1] = str1;
				row[2] = str2;
				
				itemList.get(7).setOrderFlag(true);
				dtm.addRow(row);
				
			}
		});
		hpeppAddBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		hpeppAddBtn.setBounds(3, 139, 125, 23);
		hpeppPan.add(hpeppAddBtn);
		
		JButton hpeppPlusBtn = new JButton("+");
		hpeppPlusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hpeppCount = hpeppCount +1;
				hpeppTF.setText(hpeppCount+"");
				hpeppAddBtn.setEnabled(true);
				if(itemList.get(7).quantity < hpeppCount) {
					JOptionPane.showMessageDialog(null, "현재 "+itemList.get(7).name+ " 재고가 부족합니다.\n 주문 가능 수량 : "+itemList.get(7).quantity+"잔");
					hpeppTF.setText(Integer.toString(itemList.get(7).quantity));
				}else if(hpeppCount>0){
					hpeppMinusBtn.setEnabled(true);
				}
			}
		});
		hpeppPlusBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		hpeppPlusBtn.setBounds(88, 115, 40, 23);
		hpeppPan.add(hpeppPlusBtn);
		
		hpeppMinusBtn = new JButton("-");
		hpeppMinusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hpeppCount>0) {
					hpeppCount = hpeppCount - 1;
					hpeppTF.setText(hpeppCount+"");
					hpeppAddBtn.setEnabled(true);
				}else {
					hpeppMinusBtn.setEnabled(false);
				}
			}
		});
		hpeppMinusBtn.setFont(new Font("굴림", Font.PLAIN, 8));
		hpeppMinusBtn.setBounds(3, 115, 39, 23);
		hpeppPan.add(hpeppMinusBtn);
		
		hpeppTF = new JTextField();
		hpeppTF.setText("0");
		hpeppTF.setHorizontalAlignment(SwingConstants.CENTER);
		hpeppTF.setColumns(10);
		hpeppTF.setBounds(45, 115, 41, 23);
		hpeppPan.add(hpeppTF);
		
		JButton ICEbtn = new JButton("ICE");
		ICEbtn.setFont(new Font("굴림", Font.BOLD, 12));
		ICEbtn.setForeground(Color.BLUE);
		ICEbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(icePanel);
			}
		});
		ICEbtn.setBounds(26, 10, 97, 23);
		CafeLS2.getContentPane().add(ICEbtn);
		
		JButton HotBtn = new JButton("HOT");
		HotBtn.setFont(new Font("굴림", Font.BOLD, 12));
		HotBtn.setForeground(Color.RED);
		HotBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(hotPan);
			}
		});
		HotBtn.setBounds(135, 10, 97, 23);
		CafeLS2.getContentPane().add(HotBtn);
		
		JPanel orderPan = new JPanel();
		orderPan.setBounds(12, 398, 332, 110);
		CafeLS2.getContentPane().add(orderPan);
		orderPan.setLayout(null);
		
		JLabel HistoryLb = new JLabel("주문내역");
		HistoryLb.setFont(new Font("굴림", Font.BOLD, 15));
		HistoryLb.setBounds(0, 10, 80, 15);
		orderPan.add(HistoryLb);
		
		orderTa = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		orderTa.setFont(new Font("굴림", Font.PLAIN, 12));
		orderTa.setForeground(Color.BLACK);
		orderTa.setEditable(false);
		orderTa.setBounds(0, 45, 319, 63);
		orderPan.add(orderTa);
		
		JLabel lblNewLabel = new JLabel("상품명");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel.setBounds(0, 30, 57, 15);
		orderPan.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("가격");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(150, 30, 57, 15);
		orderPan.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("수량");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(210, 30, 57, 15);
		orderPan.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("합계");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(263, 30, 57, 15);
		orderPan.add(lblNewLabel_3);
		
		JButton finishOrder = new JButton("주문완료");
		finishOrder.setFont(new Font("굴림", Font.PLAIN, 12));
		finishOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CafeLS3 = new JFrame();
				CafeLS3.setTitle("CafeJS");
				CafeLS3.setBounds(100, 100, 367, 284);
				CafeLS3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				CafeLS3.getContentPane().setLayout(null);
				
				CafeLS3.setResizable(false);
				CafeLS3.setLocationRelativeTo(null);
				
				JScrollPane sPane = new JScrollPane();
				sPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				sPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				sPane.setBounds(25, 43, 299, 145);
				CafeLS3.getContentPane().add(sPane);
				
				table = new JTable(dtm);
				table.setRowSelectionAllowed(false);
				table.setEnabled(false);
				sPane.setViewportView(table);
				
				//테이블 간격 조절
				DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
				celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
				
				table.getColumn("메뉴").setPreferredWidth(100);
				table.getColumn("메뉴").setCellRenderer(celAlignCenter);
				table.getColumn("수량").setPreferredWidth(3);
				table.getColumn("수량").setCellRenderer(celAlignCenter);
				table.getColumn("총 가격").setPreferredWidth(20);
				table.getColumn("총 가격").setCellRenderer(celAlignCenter);
				table.getTableHeader().setReorderingAllowed(false);
				
				orderHisLab = new JLabel("< 주문내역 >");
				orderHisLab.setFont(new Font("돋움", Font.BOLD, 15));
				orderHisLab.setBounds(121, 10, 104, 23);
				CafeLS3.getContentPane().add(orderHisLab);
				
				totalPrice = ameTp + latteTp + dolceTp + uniTp + hameTp + hlatteTp + hmochaTp + hpeppTp;
				totalPlab = new JLabel("총 가격 : " + totalPrice +"원");
				totalPlab.setBounds(25, 208, 130, 15);
				CafeLS3.getContentPane().add(totalPlab);
				
				CafeLS3.setVisible(true);
				
				JButton reOrderBtn2 = new JButton("재 주문");
				reOrderBtn2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//테이블 초기화
						CafeLS3.dispose();
					}
				});
				reOrderBtn2.setFont(new Font("굴림", Font.PLAIN, 12));
				reOrderBtn2.setBounds(151, 204, 83, 23);
				CafeLS3.getContentPane().add(reOrderBtn2);
				
				JButton payMentBtn = new JButton("결제");
				payMentBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "결제가 완료되었습니다. 감사합니다.");
						CafeLS3.dispose();
						CafeLS2.dispose();
															
						
						int count[] = {ameCount,latteCount,dolceCount,uniCount,hameCount,hlatteCount,hmochaCount,hpeppCount};
						
						for(int i=0; i<itemList.size(); i++) {//1.코드 2.구매수량 3.남은수량
							if(itemList.get(i).orderFlag) {
								int a = itemList.get(i).getQuantity() - count[i];
								pw.println("orderFinish:"+itemList.get(i).items_code+":"+count[i]+":"+a);
							}
							pw.println("end");
						}
						
					}
				});
				payMentBtn.setFont(new Font("굴림", Font.PLAIN, 12));
				payMentBtn.setBounds(242, 204, 83, 23);
				CafeLS3.getContentPane().add(payMentBtn);
			}
		});
		finishOrder.setBounds(234, 510, 97, 23);
		CafeLS2.getContentPane().add(finishOrder);
		
		reOrder = new JButton("재주문");
		reOrder.setFont(new Font("굴림", Font.PLAIN, 12));
		reOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(int i=0; i < itemList.size() ; i++) {
					  itemList.get(i).setOrderFlag(false);
					}
				
				orderTa.setText(""); //각 메뉴 기본 수량 및 총 가격 초기화
				ameTF.setText("0");
				ameCount = 0;
				ameAddBtn.setEnabled(true);
				ameMinusBtn.setEnabled(true);
				
				latteTF.setText("0");
				latteCount = 0;
				latteAddBtn.setEnabled(true);
				latteMinusBtn.setEnabled(true);
				
				dolceTF.setText("0");
				dolceCount = 0;
				dolceAddBtn.setEnabled(true);
				dolceMinusBtn.setEnabled(true);
				
				uniTF.setText("0");
				uniCount = 0;
				uniAddBtn.setEnabled(true);
				uniMinusBtn.setEnabled(true);
				
				hameTF.setText("0");
				hameCount = 0;
				hameAddBtn.setEnabled(true);
				hameMinusBtn.setEnabled(true);
				
				hlatteTF.setText("0");
				hlatteCount = 0;
				hlatteAddBtn.setEnabled(true);
				hlatteMinusBtn.setEnabled(true);
				
				hmochaTF.setText("0");
				hmochaCount = 0;
				hmochaAddBtn.setEnabled(true);
				hmochaMinusBtn.setEnabled(true);
				
				hpeppTF.setText("0");
				hpeppCount = 0;
				hpeppAddBtn.setEnabled(true);
				hpeppMinusBtn.setEnabled(true);
				
				ameTp=0; latteTp=0; dolceTp=0; uniTp=0; hameTp=0; hlatteTp=0; hmochaTp=0; hpeppTp=0;
				//테이블 초기화
				dtm.setNumRows(0);
			}
		});
		reOrder.setBounds(125, 510, 97, 23);
		CafeLS2.getContentPane().add(reOrder);
		

	}
	public void switchPanels (JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
}
