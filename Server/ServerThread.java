import java.sql.*;
import java.io.*;
import java.net.*;

class ServerThread extends Thread {
	Statement stmt;
	ServerSocket sSocket;
	String id = null;
	public ServerThread(Statement stmt)throws Exception {
		this.stmt = stmt;
	}
	public void run() {
		try {
			sSocket = new ServerSocket(6077);
			Socket socket = sSocket.accept();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			
			String line = null;
				while(true) {
					line = br.readLine();
					String[] p = line.split(":");
					
					if(p[0].equals("login")&&p.length > 1) { //로그인
						String sql = "SELECT * FROM USERS WHERE ID ='"+p[1]+"' AND PWD='"+p[2]+"'";
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("id:"+p[1]);
						System.out.println("pw:"+p[2]);
						if(rs.next()) {
							pw.println("true");
							System.out.println(p[1]+"님 로그인 성공");
							id = p[1]; //로그인한 id값 저장
						}else{
							pw.println("false");
							System.out.println(p[1]+"님 로그인 시도");
						}
						
					}else if(p[0].equals("idCheck")) { //중복아이디 체크
						String sql = "SELECT * FROM USERS where id ='"+p[1]+"'";
						ResultSet rs = stmt.executeQuery(sql);
						
						boolean idCheck = rs.next();		
						pw.println(idCheck);
						
					}else if(p[0].equals("signUp")) { //회원가입
						String sql = "INSERT INTO USERS VALUES('"+p[1]+"','"+p[2]+"','"+p[3]+"','"+p[4]+"','"+p[5]+"','"+p[6]+"','"+p[7]+"')";
						int i = stmt.executeUpdate(sql);
						
					}else if(p[0].equals("orderStart")) { //주문시작 (아이템 리스트 전달)
						String sql = "SELECT * FROM ITEMS";
						ResultSet rs = stmt.executeQuery(sql);
							while(rs.next()) {
								pw.println(rs.getString("item_code")+":"+rs.getString("name")+":"+rs.getString("quantity")+":"+rs.getString("price"));

							}
							pw.println("end");
							
					}else if(p[0].equals("orderFinish")) { //주문종료 (아이템 리스트 다시 저장)
						String sql ="INSERT INTO ORDERS (ITEM_CODE, QUANTITY, ID, TIME) VALUE ("+p[1]+","+p[2]+",'"+id+"',DEFAULT)";
						int i = stmt.executeUpdate(sql);
						
						sql = "UPDATE ITEMS SET QUANTITY ="+p[3]+" WHERE ITEM_CODE="+p[1];
						i = stmt.executeUpdate(sql);

						}
					else if(p[0].equals("orderHistory")) { //사용자 주문내역 확인
						System.out.println("test");
						String sql = "SELECT NAME, ORDERS.QUANTITY, TIME FROM ORDERS LEFT JOIN ITEMS ON ORDERS.ITEM_CODE = ITEMS.ITEM_CODE WHERE ORDERS.ID = '"+id+"' order by time desc";
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("11");
						while(rs.next()) {
							System.out.println("test");
							pw.println(rs.getString("name")+";"+rs.getString("quantity")+";"+rs.getString("time"));
						}
						pw.println("end");
					}
				}//while
			}catch (Exception e) { e.printStackTrace(); }
	
		} 
		
	}
	
