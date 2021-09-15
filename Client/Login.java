import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

class Login {
	BufferedReader br;
	PrintWriter pw;
	String id;
	public Login(){
		try {
			Socket socket = new Socket("localhost",6077);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(),true);
		} catch (Exception e) { e.printStackTrace();}
	
		JFrame Frame;
		JTextField idTF;
		JPasswordField pwTF;
		
		Frame = new JFrame();
		Frame.setTitle("cafeJS");
		Frame.setBounds(100, 100, 299, 178);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 283, 139);
		Frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Frame.setResizable(false);
		Frame.setLocationRelativeTo(null);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(66, 25, 57, 15);
		panel.add(idLabel);
		
		JLabel pwLabel = new JLabel("PW");
		pwLabel.setBounds(66, 53, 31, 15);
		panel.add(pwLabel);
		
		idTF = new JTextField();
		idTF.setBounds(115, 22, 118, 21);
		panel.add(idTF);
		idTF.setColumns(10);
		
		pwTF = new JPasswordField();
		pwTF.setFont(new Font("Gulim", Font.PLAIN, 12));
		pwTF.setBounds(115, 50, 118, 21);
		panel.add(pwTF);
		
		Frame.setVisible(true);
		
		JButton loginBtn = new JButton("로그인");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(idTF.getText().equals("") || pwTF.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"아이디와 비밀번호를 입력해주세요.");			
					}else {
				String login = "login:"+idTF.getText()+":"+pwTF.getText();
				pw.println(login);
				
				try {
					String result = br.readLine();
					if(result.equals("true")) {
						JOptionPane.showMessageDialog(null,"환영합니다. CafeLS입니다.");
						id = idTF.getText();
						new CafeLS1(br, pw, id);
						Frame.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null,"아이디와 비밀번호를 확인해주세요.");
					}
				} catch (IOException e1) {e1.printStackTrace(); }
					}
				
			}
		});
		
		loginBtn.setBounds(28, 95, 97, 23);
		panel.add(loginBtn);
		
		JButton singupBtn = new JButton("회원가입");
		singupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						new SignUp(br,pw);
			}
		});
		singupBtn.setBounds(156, 95, 97, 23);
		panel.add(singupBtn);
		

	}

}
