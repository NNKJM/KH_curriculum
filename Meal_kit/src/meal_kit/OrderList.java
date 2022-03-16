package meal_kit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class OrderList extends JFrame{
   
   Connection con = null;            // DB와 연결하는 객체
   PreparedStatement pstmt = null;   // SQL문을 DB에 전송하는 객체
   ResultSet rs = null;              // SQL문 실행 결과를 가지고 있는 객체
   String sql = null;                // SQL문을 저장하는 문자열 변수
   Statement st = null;
   
   List<String> orderDate = new ArrayList<String>();   
   List<String> mealKit_menu = new ArrayList<String>();

   JLabel DateLabel;
   JTextField[][] menu;
   JComboBox<String>[][] Numcb;
   JLabel[][] ratinglb;
   JButton[][] ratingbtn;
   int menuCount;
   String rating_val;
   
   // DB를 연동하는 메서드
   void connect() {
      String driver = "oracle.jdbc.driver.OracleDriver";
      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      String user = "web";
      String password = "1234";
      
      try {
         // 1. 접속할 오라클 데이터베이스 드라이버를 메모리자 올리자.
         // -동적작업 (프로그램 실행시 메모리에 업로드 됨.)
         Class.forName(driver);
         
         // 2. 오라클 데이터베이스와 연결을 시도.
         con = DriverManager.getConnection(url, user, password);
         
      } catch (Exception e) {

         e.printStackTrace();
      }
            
   }   // connect() 메서드 end  

      
   public OrderList() {
      
      setTitle("주문내역 페이지");
      
      JPanel contentPane = new JPanel();
      // contentPane.setBackground(new Color(210, 229, 255));
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      //contentPane.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      
      JLabel cart = new JLabel("주문내역");
      cart.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      cart.setForeground(new Color(0, 0, 51));
      cart.setBounds(320, 30, 100, 40);
      contentPane.add(cart);

      
        JPanel panel = new JPanel();
       // panel.setPreferredSize(new Dimension(650, 550));;
        panel.setLayout(null);
      
        JScrollPane scrollPane = new JScrollPane(panel,
          ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
          ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                
        connect();
      getDate(Login.id);

       menu = new JTextField[orderDate.size()][];
       Numcb = new JComboBox[orderDate.size()][];
       ratinglb = new JLabel[orderDate.size()][];
       ratingbtn = new JButton[orderDate.size()][];

    int height2 = 0;
    
    if (orderDate.size() > 0) {   
      int height=0;
      for (int i = 0; i < orderDate.size(); i++) {
         
          DateLabel = new JLabel(orderDate.get(i));
          DateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
          DateLabel.setBounds(30, 10 + i*40 + height, 150, 20);
          panel.add(DateLabel);
                    
          getMenu(Login.id, orderDate.get(i));

          menu[i] = new JTextField[mealKit_menu.size()];
          Numcb[i] = new JComboBox[mealKit_menu.size()];
          ratinglb[i] = new JLabel[mealKit_menu.size()];
          ratingbtn[i] = new JButton[mealKit_menu.size()];
          
          height = height + 40;
          //System.out.println(mealKit_menu.size());
          
          for (int j = 0; j < mealKit_menu.size(); j++) {
             menu[i][j] = new JTextField(mealKit_menu.get(j));
             menu[i][j].setFont(new Font("맑은 고딕", Font.BOLD, 18));
             menu[i][j].setBounds(30, 10 + i*40 + height, 230, 30);
             panel.add(menu[i][j]);
             menu[i][j].setColumns(10);
             
             
             String check = checkRating(Login.id, mealKit_menu.get(j), orderDate.get(i));
             
             if (check == "Y") {
                Numcb[i][j] = new JComboBox<String>();
                Numcb[i][j].addItem(rating_val);
                Numcb[i][j].setSelectedItem(rating_val);
                //Numcb[i][j].setEnabled(false);
             }else {
                String[] Num = {"선택", "1", "2", "3", "4", "5"};
                Numcb[i][j] = new JComboBox<String>(Num);
                //Numcb[i].addItem("선택");
                Numcb[i][j].setSelectedItem("선택");
             }
             
             Numcb[i][j].setFont(new Font("맑은 고딕", Font.BOLD, 18));
             Numcb[i][j].setBounds(280, 10 + i*40 + height, 100, 30);
             panel.add(Numcb[i][j]);

               ratinglb[i][j] = new JLabel("/ 5점");
               ratinglb[i][j].setFont(new Font("맑은 고딕", Font.BOLD, 18));
               ratinglb[i][j].setBounds(400, 10 + i*40 + height, 50, 30);
               panel.add(ratinglb[i][j]);
               
               
                 ratingbtn[i][j] = new JButton("평점 남기기");
                 ratingbtn[i][j].setFont(new Font("맑은 고딕", Font.BOLD, 18));
                 ratingbtn[i][j].setBounds(470, 10 + i*40 + height, 150, 30);
                 panel.add(ratingbtn[i][j]);
                 
                 if (check == "Y") {
                    ratingbtn[i][j].setVisible(false);
               }
                        
               int k=i;
               int l=j;
               ratingbtn[i][j].addActionListener(new ActionListener() {
                        
            @Override
               public void actionPerformed(ActionEvent e) {

                  //System.out.println(menu[k][l].getText());
                  //System.out.println(Numcb[k][l].getSelectedItem().toString());
                  if (Numcb[k][l].getSelectedItem().toString() == "선택") {
                     JOptionPane.showMessageDialog(null, "평점을 선택해 주세요.");
                     
                  }else {
                        int result = JOptionPane.showConfirmDialog(null,
                              "평점을 저장하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
                        
                        if(result == JOptionPane.NO_OPTION) {
                           JOptionPane.showMessageDialog(null, "취소를 클릭하셨습니다.");
                        } else if(result == JOptionPane.YES_OPTION){
                           
                           int rating = Integer.parseInt(Numcb[k][l].getSelectedItem().toString());
                           int no = getMenuNo(menu[k][l].getText());
                           
                           
                           String resultKey = saveRating(no, rating);
                           String resultKey2 = updateRating(Login.id, rating,
                                     orderDate.get(k), menu[k][l].getText());
                           
                           if (resultKey.equals("Y") && resultKey2.equals("Y")) {
                              JOptionPane.showMessageDialog(null, "평점이 저장되었습니다.");
                              //ratingbtn[k][l].setEnabled(false);
                              ratingbtn[k][l].setVisible(false);
                           }else {
                              JOptionPane.showMessageDialog(null, "평점 저장에 실패하였습니다.");
                           }
                              
                           
                        }
                     }
                  }
            });
               
               height = height + 40;
               
          } // for문 end
          
          height2 = height2 + (mealKit_menu.size() * 40) ;
            //System.out.println("mealKit_menu.size() >>> " + mealKit_menu.size());
          mealKit_menu.clear();
          
       } // for문 end
   
      //System.out.println(height2);
      //System.out.println("orderDate.size() >>>" + orderDate.size());
      if(orderDate.size() == 1) {
         height2 = height2 + (orderDate.size() * 50);   
      }else {
         height2 = height2 + (orderDate.size() * 100);
      }
      
      //System.out.println(height2);
      panel.setPreferredSize(new Dimension(650, height2));;
      //panel.setPreferredSize(new Dimension(650, 550));;
        scrollPane.setBounds(30, 100, 700, 340);
        scrollPane.setBorder(null);
        scrollPane.setViewportView(panel);
        
      contentPane.add(scrollPane);

    } else {
        JLabel cartLabel = new JLabel("상품을 주문하신 주문내역이 없습니다.");
        cartLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        cartLabel.setBounds(200, 150, 500, 20);
        contentPane.add(cartLabel);
    } // if문 end
       
      //setFont(new Font("맑은 고딕", Font.BOLD, 20));
      //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 800, 600);
      setVisible(true);
   
   }   // 생성자 end
   
      void getDate(String id) {

            try {
               // 1. 오라클 데이터베이스에 전송할 SQL문 작성
               sql = "select to_char(regdate, 'YYYY/MM/DD') as rdate" 
                     + " from ShoppingCart" 
                     + " where orderstatus = 'Y' and id = ?" 
                     + " group by to_char(regdate, 'YYYY/MM/DD')" 
                     + " order by to_char(regdate, 'YYYY/MM/DD') desc";
               
               pstmt = con.prepareStatement(sql);
               pstmt.setString(1, id);
               
               // 2. 오라클 데이터베이스에 SQL문 전송 및 SQL문 실행
               rs = pstmt.executeQuery();

              while(rs.next()) {
                 
                 //String Date = rs.getString("regdate").substring(0, 10);
                 String Date = rs.getString("rdate");
                 
                 //System.out.println(Date);
                 
                 orderDate.add(Date);

              }
          
            } catch (SQLException e) {
               e.printStackTrace();
            }
            
   } // getDate() 메서드 end
   
   
   void getMenu(String id, String regDate) {

         try {
            // 1. 오라클 데이터베이스에 전송할 SQL문 작성
           
           sql = "select distinct nvl(substr(menu, 1, instr(menu, '(', 1, 1)-1 ), menu) as menu from ShoppingCart "
               + "where id = '" + id + "' and orderStatus = 'Y' and "
               + "regdate like '" + regDate.substring(2) + "%'";
           
           st = con.createStatement();
           //pstmt.setString(1, id);
           //pstmt.setString(2, regDate);        
       
           
           // 2. 오라클 데이터베이스에 SQL문 전송 및 SQL문 실행
            //rs = pstmt.executeQuery();
           rs = st.executeQuery(sql);

           while(rs.next()) {
              
              String menu = rs.getString("menu");
              
              mealKit_menu.add(menu);

           }
       
         } catch (SQLException e) {
            e.printStackTrace();
         }
         
   } // getMenu() 메서드 end      

   int getMenuNo(String menuName) {
      int no = 0;
      
         try {
            // 1. 오라클 데이터베이스에 전송할 SQL문 작성
            
           sql = "select no from menu where menu_name= ?";
           
            String Name_val = menuName;
            String[] split = Name_val.split("\\(");
            menuName = split[0];
            
            // System.out.println(sql);
               
           pstmt = con.prepareStatement(sql);
           pstmt.setString(1, menuName);
                     
           // 2. 오라클 데이터베이스에 SQL문 전송 및 SQL문 실행
            rs = pstmt.executeQuery();

           while(rs.next()) {
              
              no = rs.getInt("no");

           }           
           
         } catch (SQLException e) {
            e.printStackTrace();
         }
         
       return no;
         
   } // getMenuNo() 메서드 end
   
   String saveRating(int no, int rating) {
      String resultKey = null;
      try {
         
         // 1. 오라클 데이터베이스에 전송할 SQL문 작성
         sql = "insert into rating values(?, ?)";
         
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, no);
         pstmt.setInt(2, rating);
         
         // 2. 오라클 데이터베이스에 SQL문 전송 및 SQL문 실행.      
         int res = pstmt.executeUpdate();
         
         if (res > 0) {
            //JOptionPane.showMessageDialog(null, "평점이 저장되었습니다.");
            resultKey = "Y";
         }else {
            //JOptionPane.showMessageDialog(null, "평점 저장이 실패되었습니다.");
            resultKey = "N";
         }
         
         // 3. 오라클 데이터베이스에 연결되어 있던 자원 종료.
         pstmt.close();  // con.close(); 
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return resultKey;
      
   } // saveRating() 메서드 end
   
   String updateRating(String id, int rating, String regDate, String menu) {
      String resultKey2 = null;
      
         try {
            // 1. 오라클 데이터베이스에 전송할 SQL문 작성
           
           sql = "update ShoppingCart "
               + "set rating = " + rating     
               + " where id = '" + id + "' and orderStatus = 'Y' and nvl(substr(menu, 1, instr(menu, '(', 1, 1)-1 ), menu) = '" + menu  
               + "' and regdate like '" + regDate.substring(2) + "%'";
           
           st = con.createStatement();
           int res = st.executeUpdate(sql);
           
          if (res > 0) {
            //JOptionPane.showMessageDialog(null, "평점이 저장되었습니다.");
             resultKey2 = "Y";
          }else {
            //JOptionPane.showMessageDialog(null, "평점 저장이 실패되었습니다.");
             resultKey2 = "N";
            }
         
          // 3. 오라클 데이터베이스에 연결되어 있던 자원 종료.
         st.close();  // con.close(); 
       
         } catch (SQLException e) {
            e.printStackTrace();
         }
         return resultKey2;
   } // updateRating() 메서드 end      
   
   
   String checkRating(String id, String menu, String regDate) {
       String result = null;
       
       try {
         sql = "select rating from shoppingcart"
               + " where  nvl(substr(menu, 1, instr(menu, '(', 1, 1)-1 ), menu) = '" + menu + "' and id = '" + id + "' and"
               + " regdate like '" + regDate.substring(2) + "%'";
                  
         
         st = con.createStatement();
         rs = pstmt.executeQuery(sql);
                  
         if (rs.next()) {
            
            String rating = Integer.toString(rs.getInt("rating"));
            
            if(rating.equals("0") || rating == null) {
                result = "N";
            }else {
               result = "Y";
               rating_val = Integer.toString(rs.getInt("rating"));
            }
           
          }else {
             result = "N";
          }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
       
     return result;
    }

}