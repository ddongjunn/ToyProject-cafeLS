import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class SignUp {
	private JFrame Frame;
	private JTextField idTF;
	private JTextField nameTF;
	private JTextField yearTF;
	private JTextField addrTF;
	private JTextField dateTF;
	private JTextField telTF1;
	private JTextField telTF2;
	private JTextField telTF3;
	private JTextField monthTF;
	private JPasswordField pwPF;

	public SignUp(BufferedReader br, PrintWriter pw) {
		Frame = new JFrame();
		Frame.setTitle("회원가입");
		Frame.setBounds(100, 100, 337, 361);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 321, 328);
		Frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Frame.setResizable(false);
		Frame.setLocationRelativeTo(null);
		
		JLabel idLabel = new JLabel("아이디:");
		idLabel.setBounds(23, 22, 57, 15);
		panel.add(idLabel);
		
		JLabel pwLabel = new JLabel("비밀번호:");
		pwLabel.setBounds(23, 57, 57, 15);
		panel.add(pwLabel);
		
		JLabel nameLabel = new JLabel("이름:");
		nameLabel.setBounds(23, 92, 57, 15);
		panel.add(nameLabel);
		
		JLabel genderLabel = new JLabel("성별:");
		genderLabel.setBounds(23, 125, 57, 15);
		panel.add(genderLabel);
		
		JLabel birthLabel = new JLabel("생년월일:");
		birthLabel.setBounds(23, 162, 57, 15);
		panel.add(birthLabel);
		
		JLabel phoneLabel = new JLabel("전화번호:");
		phoneLabel.setBounds(23, 202, 57, 15);
		panel.add(phoneLabel);
		
		JLabel addrLabel = new JLabel("주소:");
		addrLabel.setBounds(23, 238, 57, 15);
		panel.add(addrLabel);
		
		idTF = new JTextField();
		idTF.setBounds(92, 19, 140, 21);
		panel.add(idTF);
		idTF.setColumns(10);
		
		JButton idCheckBtn = new JButton("확인");
		idCheckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(idTF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
				}else {
					String idCheck = "idCheck:"+idTF.getText();
					pw.println(idCheck);
				
					try {
						String result = br.readLine();
						if(result.equals("false")) {
							JOptionPane.showMessageDialog(null,"사용가능한 아이디입니다." );
						}else {
							JOptionPane.showMessageDialog(null,"중복된 아이디입니다.");
							idTF.setText("");
						}
					} catch (IOException e) {e.printStackTrace();}
				}
			}
		});
		idCheckBtn.setBounds(239, 18, 67, 23);
		panel.add(idCheckBtn);
		
		nameTF = new JTextField();
		nameTF.setColumns(10);
		nameTF.setBounds(92, 89, 140, 21);
		panel.add(nameTF);
		
		yearTF = new JTextField();
		yearTF.setColumns(10);
		yearTF.setBounds(92, 159, 55, 21);
		panel.add(yearTF);
		
		addrTF = new JTextField();
		addrTF.setColumns(10);
		addrTF.setBounds(92, 235, 203, 21);
		panel.add(addrTF);
		
		dateTF = new JTextField();
		dateTF.setColumns(10);
		dateTF.setBounds(194, 159, 27, 21);
		panel.add(dateTF);
		
		telTF1 = new JTextField();
		telTF1.setColumns(10);
		telTF1.setBounds(92, 199, 38, 21);
		panel.add(telTF1);
		
		telTF2 = new JTextField();
		telTF2.setColumns(10);
		telTF2.setBounds(142, 199, 38, 21);
		panel.add(telTF2);
		
		telTF3 = new JTextField();
		telTF3.setColumns(10);
		telTF3.setBounds(194, 199, 38, 21);
		panel.add(telTF3);
		
		JLabel birthL1 = new JLabel("/");
		birthL1.setBounds(151, 162, 15, 15);
		panel.add(birthL1);
		
		JLabel birthL2 = new JLabel("/");
		birthL2.setBounds(189, 162, 15, 15);
		panel.add(birthL2);
		
		JLabel telL1 = new JLabel("-");
		telL1.setBounds(132, 202, 15, 15);
		panel.add(telL1);
		
		JLabel telL2 = new JLabel("-");
		telL2.setBounds(183, 202, 15, 15);
		panel.add(telL2);
		
		JButton signUpBtn = new JButton("가입");
		
		monthTF = new JTextField();
		monthTF.setColumns(10);
		monthTF.setBounds(159, 159, 27, 21);
		panel.add(monthTF);
		
		pwPF = new JPasswordField();
		pwPF.setBounds(92, 54, 140, 21);
		panel.add(pwPF);
		
		JRadioButton manRB = new JRadioButton("남",true);
		manRB.setBounds(88, 121, 38, 23);
		panel.add(manRB);
		
		JRadioButton womanRB = new JRadioButton("여",true);
		womanRB.setBounds(132, 121, 43, 23);
		panel.add(womanRB);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(manRB); bg.add(womanRB);
		signUpBtn.setBounds(50, 278, 97, 23);
		panel.add(signUpBtn);
		
		Frame.setVisible(true);
		
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gender = "";
				
				if(manRB.isSelected()) {
					gender = "남";
				}else if(womanRB.isSelected()) {
					gender = "여";
				}
				
				if(idTF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
				}else if(pwPF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
				}else if(nameTF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력하세요.");
				}else if(yearTF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "출생년도를 입력하세요.");
				}else if(monthTF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "출생월을 입력하세요.");
				}else if(dateTF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "출생일을 입력하세요.");
				}else if(telTF1.getText().equals("")||telTF2.getText().equals("")||telTF3.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "전화번호를 입력하세요.");
				}else if(addrTF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "주소를 입력하세요.");
				}else {
					String birth = yearTF.getText()+"/"+monthTF.getText()+"/"+dateTF.getText();
					String tel = telTF1.getText()+"-"+telTF2.getText()+"-"+telTF3.getText();
					
					String signUp = "signUp:"+idTF.getText()+":"+pwPF.getText()+":"+nameTF.getText()+":"+gender+":"
							+birth+":"+tel+":"+addrTF.getText();
					
					pw.println(signUp);
					
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
					Frame.dispose();
				}
				

				
			}
		});
		
		JButton cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.dispose();
			}
		});
		cancelBtn.setBounds(170, 278, 97, 23);
		panel.add(cancelBtn);

		
	}
}