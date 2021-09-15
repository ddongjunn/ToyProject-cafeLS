import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class AdminLogin {
	private JFrame frame;
	private JTextField idTF;
	private JTextField pwTF;
	private JPasswordField adminPwTF;
	private Statement stmt;
	
	   public AdminLogin(Statement stmt){
			frame = new JFrame();
			frame.setTitle("cafeLS [admin]");
			frame.setBounds(100, 100, 299, 178);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setVisible(true);
			frame.setLocation(330, 429);
			frame.setResizable(false);
			
			JLabel idLB = new JLabel("ID");
			idLB.setBounds(66, 25, 57, 15);
			frame.getContentPane().add(idLB);
			
			JLabel pwLB = new JLabel("PW");
			pwLB.setBounds(66, 53, 31, 15);
			frame.getContentPane().add(pwLB);
			
			idTF = new JTextField();
			idTF.setText("admin");
			idTF.setEditable(false);
			idTF.setBounds(115, 22, 118, 21);
			frame.getContentPane().add(idTF);
			idTF.setColumns(10);
			
			pwTF = new JPasswordField();
			pwTF.setBounds(115, 50, 118, 21);
			frame.getContentPane().add(pwTF);
			
			JButton loginBtn = new JButton("로그인");
			loginBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String sql = "SELECT * FROM USERS WHERE ID ='ADMIN' AND PWD='"+pwTF.getText()+"'";
					try {
						ResultSet rs = stmt.executeQuery(sql);
							if(rs.next()) {
								try {
									new AdminWindow(stmt);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								frame.setVisible(false);
							}else {
								JOptionPane.showMessageDialog(null, "관리자 비밀번호를 확인해주세요.", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
								pwTF.setText("");
							}
					}catch(SQLException sqle) { sqle.printStackTrace(); }
				}
			});
			loginBtn.setBounds(90, 88, 97, 23);
			frame.getContentPane().add(loginBtn);
		}
}

