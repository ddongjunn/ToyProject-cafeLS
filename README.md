# cafeLS
자바 Swing, Socket, jdbc를 사용하여 만든 셀프 주문 시스템 키오스크입니다.

## 개발 기능
Server
  - 관리자 GUI(관리자 로그인)
  - 재고 현황 조회
  - 판매 현황 조회
  - 상품 발주

Client
  - 로그인 및 회원가입 (중복ID 체크)
  - 주문 내역 확인 (메뉴, 수량, 날짜 시간 확인)
  - TAKE OUT/EAT IN 선택가능
  - 상품 주문 (재고 부족시 주문 불가능)
  - 메뉴 종류별 선택 주문 가능 (ICE, HOT)
  - 상품 추가후 별도의 주문내역 확인 (메뉴, 수량, 총가격)

## Clent Class
Class Client
  - main 메소드가 있는 클래스이며, Login클래스 객체를 생성하여 프로그램이 시작되는 클래스입니다.<br> 별다른 멤버변수와 멤버메소드 없이 Login객체 생성으로 프로그램을 시작합니다.

Class Login
  - 사용자가 로그인 할 수 있도록, 로그인 GUI를 구성, SingUp객체를 생성해서 회원가입<br>
    Socket, BufferedReader, PrintWriter클래스를 사용하여 서버와 6077포트를 사용하여 소켓통신 시작

Class SignUp
  - Login클래스에서 singupBtn을 클릭하면 ActionListener()을 통해 SignUp클래스로 접근 (회원가입 기능)<br>
    BufferedReader br, PrintWriter pw를 받아와서 서버와 데이터를 주고 받는다
  - 회원가입 완료후 사용자가 입력한 데이터를 (String birth, tel, signUp)를 서버로 전송하여 서버에서는 users 테이블에 회원의 정보를 저장

Class CafeLS1
  - 로그인시 메뉴 선택 전 eatInBtn 또는 takeOutBtn 버튼을 이용하여 자유로운 선택
  - eatInBtn 또는 takeOutBtn 버튼을 클릭하여 Order 객체를 생성(br, pw, id)<br>
    chatBtn을 이용하여 사용자 별 주문내역 확인이 가능(메뉴, 수량, 주무날짜 순서대로 표시하여 사용자별 주문 내역 확인이 가능)
  - DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer()객체를 생성하여 setPreFerredWidth 메소드와 setCellRenderer(celAlignCenter) 메소드를 사용하여 테이블을 정렬하고 위치를 조정
  - JFrame, JTable, JLabel, ImageIcon, JPanel, JButton, JScrollPane를 이용하여 화면을 구성

Class Order
 - 클라이언트의 핵심적인 기능이 있는 클래스로, JLayeredPane을 이용하여 ICE, HOT 2가지 버튼을 이용하여 메뉴를 나눴고, 사용자가 원하는 메뉴를 선택후 +, - 버튼을 통해서 메뉴의 수량을 조절할 수 있고, addBtn을 통해서 장바구니 형식처럼 일차적으로 주문할 상품을 추가 할 수 있다. 이때 추가된 상품은 화면 하단에 메뉴명, 수량, 총가격이 표시되며, reOrderBtn을 통해서 선택한 메뉴의 수량을 초기화할 수 있다.
 - Order객체 생성시 서버로부터 전송되는 각 메뉴의 메뉴 코드번호, 메뉴이름, 현재 재고 데이터를 전달받아 Items객체를 이용한 itemList백터를 생성하여 itemList에 저장후 Order클래스에서 상품을 관리
 - payMenBtn을 통해 최종적으로 주문 진행이 되면서 서버로 itemList의 내용을 전달하여 실제 재고를 서버에 저장하게 됩니다.
 - JFrame, JTable, JLabel, ImageIcon, JPanel, JButton, JScrollPane, JLayeredPane, TextArea를 이용하여 화면을 구성 
 
 Class Item
  - 서버에서 메뉴의 데이터들의 정보를 받아와서 Order클래스에서 메뉴데이터들을 자유자재로 사용하기 위해서 items클래스를 설계하여 Order클래스에서 Items객체를 기반으로 itemList백터를 생성하기 위한 클래스
  
## Server Class
Class Server
 - DB에 접근하기 위하여 Connection, Statement객체를 생성하였고, 서버의 시작 클래스<br>
 - Server클래스에서 Thread를 사용하여 2개의 객체 ServerThread, AdminLogin 동시에 실행
 
Class ServerThread
 - Server클래스에서 Statement객체를 받아와서 실질적으로 클라이언트 응답에 의한 데이터 처리가 이루어지는 클래스<br>
 - 클라이언트에서 구분자를 통하여 데이터를 서버로 전달하면 ServerThread클래스에서 데이터의 내용을 구분자로 파싱하여 배열로 저장하여, 배열의 0번째 값에 따라 클라이언트에서 요청한 기능이 실행됩니다.
 - Thread 클래스를 상속받아 run()메소드를 사용하여 실행되는 클래스이며, Socket, BufferedReader, PrintWriter클래스를 사용하여 클라리언트와 6077포트를 사용하여 소켓통신을 합니다.
 - login, idCheck, signUp, orderFinish, orderHistory
    * login : 회원가입이 완료되면 ID 와 PW등 각각 회원정보를 userDB에 저장
    * idCheck : 회원가입시 users table에서 중복ID 유무 체크
    * orderStart : 클라이언트에서 Order객체 생성시 itemstable에 있는 정보 item_code, name, quantitiy 전달
    * orderFinish : 클라이언트에서 결제가 이루어지면 반영된 items들에 대한 정보를 받아서 db에 업데이트
    * orderHistory : 각 회원별 로그인 ID별로 사용자 주문내역 확인으로 users table과 orders table을 join해서 상품이름, 수량, 시간 및 날짜 전송

Class AdminLogin
 - 관리자 로그인 GUI 이며, idTF는 "admin"으로 고정된 값으로 설정하였고, 단순하게 비밀번호만 users table의 admin 정보와 일치하면 AdminWindow객체 생성으로 관리자 화면으로 접속이 됩니다.

Class AdminWindow
 - 관리자 로그인 성공시 나오는 관리자 GUI이며, 재고 현황, 판매 현황, 상품 주문을 할 수 있습니다.
 - 관리자 입장에서 편하게 알아볼 수 있도록 재고 현황, 판매 현황 별로 JTable(dtm)을 이용하여 구성
 - 상품 주문시에는 JComboBox를 활용하여 메뉴를 선택 후 상품 주문 버튼을 클릭하면 자동으로 선택한 상품이 10개가 추가가 되며, JTable이 바로 갱신될 수 있도록 구현하였습니다.
 - JFrame, JTextField, JPasswordField, JLabel, JButton, JTable, JScrollPane을 사용하여 화면을 구성
 
## 데이터베이스 (mysql)
1. USERS TABLE<br>
![image](https://user-images.githubusercontent.com/82015609/133383522-fae9fbef-bf8e-4685-95ad-7ee7632e34c7.png)
  - id : 아이디, primary key
  - pwd : 비밀번호
  - name : 이름   
  - gender : 성별
  - birth : 생년월일
  - tel : 전화번호
  - addr : 주소 

2. ITEMS TABLE<br>
![image](https://user-images.githubusercontent.com/82015609/133383530-92edfeda-cb56-4e77-96f1-36a7db6a7121.png)
  - item_code : 상품코드, auto_increment
  - name : 상품이름
  - quantity : 상품수량
  - price : 상품가격

3. ORDERS TABLE<br>
![image](https://user-images.githubusercontent.com/82015609/133383547-2b28558b-45b2-4365-92c6-6f783f2485a1.png)
  - order_number : 주문번호, primary key
  - item_code : 상품코드, items table의 item_code (foreign key) 
  - quantity : 구매한 상품 수량 
  - id : 구매한 사용자 id, users table의 id (foreign key) 
  - time : timestamp now()함수를 이용해서 주문시 주문테이블이 생성되는 시점의 날짜와 시간이 저장

<b>(1)login</b><br>
`"SELECT * FROM USERS WHERE ID ='"+p[1]+"' AND PWD='"+p[2]+"'“`<br>
사용자가 입력한 p[1] ID의 값과 p[2] PW의 값이 users 테이블에 데이터를 확인하여 로그인 판별

<b>(2)idCheck</b><br>
`"SELECT * FROM USERS WHERE ID ='"+p[1]+"'"`<br>
회원가입시 확인 버튼을 이용해 사용자가 입력한 p[1]의 값이 현재 users 테이블에 이미 사용중인 ID인지 확인 

<b>(3)signUp</b><br>
`"INSERT INTO USERS VALUES('"+p[1]+"','"+p[2]+"','"+p[3]+"','"+p[4]+"','"+p[5]+"','"+p[6]+"','"+p[7]+"')"`<br>
회원가입을 완료하게 되면 순서대로 ID, PW, 이름, 성별, 생년월일, 전화번호, 주소의 데이터를 users table에 추가 

<b>(4)orderStart</b><br>
`"SELECT * FROM ITEMS"`<br>
사용자가 주문 화면에 접근하면 현재 items 테이블에 있는 데이터들을 클라이언트로 전송하여 items을 연동

<b>(5)orderFinish</b><br>
`"INSERT INTO ORDERS (ITEM_CODE, QUANTITY, ID, TIME) VALUE    ("+p[1]+","+p[2]+",'"+id+"',DEFAULT)"`<br>
사용자 주문을 완료하면 상품코드, 주문한 수량, 사용자ID, 현재시간을 orders 테이블에 추가

<b>(6)orderHistroy</b><br>
`"SELECT NAME, ORDERS.QUANTITY, TIME FROM ORDERS LEFT JOIN ITEMS ON ORDERS.ITEM_CODE = ITEMS.ITEM_CODE WHERE ORDERS.ID = '"+id+"' order by time desc"`<br>
사용자가 주문한 내역을 확인하기 위한 쿼리로 orders table과 items table을 left join 하여 상품명, orders table의 수량, orders table의 시간을 결과로 나타낸다. order by time desc는 orders table의 time값으로 내림차순 정렬을 한 것입니다.

## 프로그램 실행
 ### Client
 
 - 초기화면<br>
 ![image](https://user-images.githubusercontent.com/82015609/133381082-59e9e0a5-70c5-4dca-9859-d0a0e563a8d0.png)
 
 - 회원가입<br>
 ![image](https://user-images.githubusercontent.com/82015609/133381404-d37796cb-bee6-4537-9af5-349a91420dca.png)
 ![image](https://user-images.githubusercontent.com/82015609/133381417-e6293be2-5dd0-42fa-ab04-551e7dbfebd4.png)
 ![image](https://user-images.githubusercontent.com/82015609/133381423-eb2d46de-2df9-4dc9-8329-03b550705e41.png)
 
 - 로그인<br>
![image](https://user-images.githubusercontent.com/82015609/133381518-8c1ca4b5-6bd2-4451-9e52-2f35201f46cc.png)
![image](https://user-images.githubusercontent.com/82015609/133381530-7adab656-33ab-4838-892e-c17cc994fff9.png)
![image](https://user-images.githubusercontent.com/82015609/133381535-bde6b2bc-d063-4b99-8c7b-83f6410e69ae.png)

 - 메인화면<br>
 ![image](https://user-images.githubusercontent.com/82015609/133381656-7ca10e00-ba72-4491-a206-eed792f6dabf.png)
 <br>매장에서 식사 및 포장을 선택할 수 있고, 지금까지의 주문내역을 확인할 수 있습니다.<br>
 
 - 상품주문<br>
![image](https://user-images.githubusercontent.com/82015609/133381773-4724cb0a-a9d4-4749-924f-c7580438b3f7.png)
![image](https://user-images.githubusercontent.com/82015609/133381778-f11135af-e8a6-41a3-9459-90d2c9433e8e.png)
![image](https://user-images.githubusercontent.com/82015609/133382338-7c1ad19f-5096-41d1-a493-801a5ec2e700.png)
![image](https://user-images.githubusercontent.com/82015609/133382349-da79f10b-3a2b-4341-9bb2-f10f8c593076.png)
<br>원하는 메뉴의 수량 선택후 추가 버튼을 누르면 화면 하단 주문내역에 메뉴명, 가격, 수량이 추가가 됩니다.<br>
현재 재고보다 많은 수량을 주문하게 될 경우 알림창이 나오며 주문가능한 수량으로 자동으로 셋팅이 됩니다.

- 결제<br>
![image](https://user-images.githubusercontent.com/82015609/133382526-65285ced-8408-4ebe-90e8-2ab25e287655.png)
![image](https://user-images.githubusercontent.com/82015609/133382550-00cde009-79a5-4066-aa04-cc582c5f1354.png)
<br>원하는 메뉴와 수량을 선택 후 주문완료버튼을 누르면 결제창이 나오며, 사용자가 선택한 메뉴가 보기 쉽도록 테이블 형식으로 화면에 표시가 됩니다.<br>
결제 버튼을 누르면 주문이 정상적으로 접수되며, 로그인 직후의 초기 화면으로 돌아가게 됩니다.

### Server
- 관리자 로그인<br>
 ![image](https://user-images.githubusercontent.com/82015609/133382785-fece3b1a-5d4d-481f-9e87-5dd1990693c8.png)

- 관리자 메인<br>
![image](https://user-images.githubusercontent.com/82015609/133382845-9b11f39f-773b-4337-ba47-61599cf9c3c6.png)
![image](https://user-images.githubusercontent.com/82015609/133382849-092f8898-b41e-46ea-87a9-0c07a4ccfef4.png)
<br>재고 현황 및 판매 현황을 파악할 수 있고, 재고가 부족한 상품의 경우 하단의 상품 발주에서 주문할 메뉴 선택 후 발주하기 버튼을 눌러 수량을 추가할 수 있습니다.


### Trouble Shooting
 - 클라이언트 GUI 구현 시 이미지를 넣는 게 잘 안돼서, 검색을 통해서 찾아낸 방법이 Label을 추가하여 Label에 ImageIcon을 넣는 방법이었습니다.
 - 클라이언트 최종 결제 단계에서 처음에 설계했던 대로 진행하다보니, 사용자 입장에서 주문을 했을 때 주문을 하지 않은 메뉴까지 전부 다 서버로 전송이 되어 사용자 입장에서 1가지 메뉴를 주문하더라도 orders table에 8개의 메뉴 전체가 추가되는 문제가 생겨서 이 부분을 해결하기 위해 items class에서 boolean orderFlag를 통하여 각 메뉴에서 주문내역에 추가하게 되면 true, 추가하지 않으면 false로 설정하여 실질적으로 주문한 메뉴만 서버에 넘어오도록 구현하였습니다



 
