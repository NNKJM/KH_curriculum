package meal_kit;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends JFrame {

   //private JPanel contentPane;
   private JTextField addrText;
   int count;
   int sum = 0;
   int sumPrice;
   int totalPrice;
      
   Connection con = null;            // DB와 연결하는 객체
   PreparedStatement pstmt = null;   // SQL문을 DB에 전송하는 객체
   ResultSet rs = null;              // SQL문 실행 결과를 가지고 있는 객체
   String sql = null;                // SQL문을 저장하는 문자열 변수
   
   JTextField[] menu;
   JLabel[] price;
   JLabel[] total;
   JTextField[] amount; 
   String addr;
   String opt;
   int optPrice;
   String result;
   
   List<String> mealKit = new ArrayList<String>();
   List<Integer> mealKit_price = new ArrayList<Integer>();
   List<Integer> mealKit_total = new ArrayList<Integer>();
   List<String> mealKit_amount = new ArrayList<String>();
   List<String> mealKit_option = new ArrayList<String>();
   List<String> mealKit_optCheck = new ArrayList<String>();
   
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
   
    public ShoppingCart() {
       
       connect();
      
       JFrame frame = new JFrame("장바구니 페이지");
                   
       JPanel contentPane = new JPanel();
       contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       setContentPane(contentPane);
       contentPane.setLayout(null);
            
        JLabel cart = new JLabel("장바구니");
        cart.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        cart.setForeground(new Color(0, 0, 51));
        cart.setBounds(330, 50, 400, 40);
        contentPane.add(cart);
        
        JLabel address = new JLabel("주소");
        address.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        address.setBounds(50, 125, 60, 20);
        contentPane.add(address);
              
        getAddr(Login.id);
        addrText = new JTextField(addr);
        addrText.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        addrText.setBounds(120, 120, 500, 35);
        contentPane.add(addrText);
        addrText.setColumns(50);
        
        JButton addrButton = new JButton("변경");
        addrButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        addrButton.setBounds(640, 120, 100, 35);
        addrButton.addMouseListener(new MouseListener() {
         
         @Override
         public void mouseReleased(MouseEvent e) {
         }
         
         @Override
         public void mousePressed(MouseEvent e) {
         }
         
         @Override
         public void mouseExited(MouseEvent e) {
         }
         
         @Override
         public void mouseEntered(MouseEvent e) {
         }
         
         @Override
         public void mouseClicked(MouseEvent e) {
            
             if (!(addrText.getText()).isEmpty()) {     
            
               int result = JOptionPane.showConfirmDialog(null, "주소를 변경 하시겠습니까?",
                        "확인", JOptionPane.YES_NO_OPTION);
                  
               if(result == JOptionPane.NO_OPTION) {
                  JOptionPane.showMessageDialog(null, "취소를 클릭하셨습니다.");
               }else if(result == JOptionPane.YES_OPTION) {
                  setAddr(Login.id);
               }            
             }else {
                JOptionPane.showMessageDialog(null, "변경하실 주소를 입력해 주세요.");
             }
             
         }
      });;
      
         getMenu(Login.id);
         
        contentPane.add(addrButton);
      
        JPanel panel = new JPanel();
        //panel.setPreferredSize(new Dimension(950, 300));
        //panel.setPreferredSize(new Dimension(width, 50 + (mealKit.size()*50)));
        
        panel.setLayout(null);
      
        JScrollPane scrollPane = new JScrollPane(panel,
          ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
          ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      
        //JScrollPane scrollPane = new JScrollPane();
        //scrollPane.setBounds(30, 170, 750, 250);
        scrollPane.setBounds(30, 170, 750, 250);
   
        //scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null);
        scrollPane.setViewportView(panel);
        
        // String mealKit[] = {"오뚜기 김치찌개", "눈꽃 함박 스테이크", "된장찌개", "양념치킨", "찌개용 돼지고기 추가(100g)"};
        //int mealKit_price[] = {12300, 16900, 2300, 2300, 16900};
        menu = new JTextField[mealKit.size()];
        price = new JLabel[mealKit.size()];
        total = new JLabel[mealKit.size()];
        amount = new JTextField[mealKit.size()];
        JButton minus[] = new JButton[mealKit.size()];
        JButton plus[] = new JButton[mealKit.size()];
        JLabel option[] = new JLabel[mealKit.size()];
        
        // getMenu(Login.id);
      int width=600;
      if (mealKit.size() > 0) {
         
          JLabel menuLabel = new JLabel("메뉴");
          menuLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
          menuLabel.setBounds(0, 30, 65, 20);
          panel.add(menuLabel);
        
          JLabel priceLabel = new JLabel("단가");
          priceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
          priceLabel.setBounds(260, 30, 65, 20);
          panel.add(priceLabel);
              
          JLabel amoutLabel = new JLabel("수량");
          amoutLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
          amoutLabel.setBounds(430, 30, 65, 20);
          panel.add(amoutLabel);
        
          JLabel totalLabel = new JLabel("총 금액");
          totalLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
          totalLabel.setBounds(560, 30, 65, 20);
          panel.add(totalLabel);  
          
          String checkOp;
      for (int i = 0; i < mealKit.size(); i++) {
         menu[i] = new JTextField(mealKit.get(i));
         menu[i].setFont(new Font("맑은 고딕", Font.BOLD, 18));
         menu[i].setBounds(0, 70 + i*40, 230, 30);
         panel.add(menu[i]);
         menu[i].setColumns(10);
         
         //price[i] = new JLabel(mealKit_price[i] + "원");

         checkOp = mealKit_optCheck.get(i);
         
         if(checkOp.equalsIgnoreCase("Y")) {
           //price[i] = new JLabel(mealKit_price.get(i) + "원");
//            getOption_price(mealKit.get(i), mealKit_price.get(i));
//            price[i] = new JLabel(optPrice + "원");
            width = 950;
         }else {
//            price[i] = new JLabel(mealKit_price.get(i) + "원");
         }
         price[i] = new JLabel(mealKit_price.get(i) + "원");
            
         price[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
         price[i].setBounds(250, 70 + i*40, 100, 20);
         panel.add(price[i]);
         
         minus[i] = new JButton("-");
         minus[i].setBounds(380, 70 + i*40, 45, 30);
         panel.add(minus[i]);
         
         amount[i] = new JTextField(mealKit_amount.get(i));
         amount[i].setBounds(425, 70 + i*40, 66, 30);
         amount[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
         amount[i].setHorizontalAlignment(SwingConstants.CENTER);
         amount[i].setEditable(false);
         amount[i].setColumns(2);
         panel.add(amount[i]);
                  
         plus[i] = new JButton("+");
         plus[i].setBounds(490, 70 + i*40, 45, 30);
         panel.add(plus[i]);   
         
         total[i] = new JLabel(mealKit_total.get(i) + "원");
         total[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
         total[i].setBounds(570, 70 + i*40, 100, 20);
         panel.add(total[i]);
         
         /*         
         if(checkOp.equalsIgnoreCase("Y")) {
           
            getOption(mealKit.get(i));
            option[i] = new JLabel("옵션 : " + opt);
            option[i].setFont(new Font("맑은 고딕", Font.BOLD, 17));
            option[i].setBounds(700, 70 + i*40, 400, 30);
            option[i].setEnabled(true);
            panel.add(option[i]);
         }
         */
         sum += mealKit_total.get(i);
      }
         
      panel.setPreferredSize(new Dimension(width, 50 + (mealKit.size()*50)));
      contentPane.add(scrollPane);
      
      JLabel sumLabel = new JLabel("금액 합계 : " + sum + "원");
      sumLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      sumLabel.setBounds(330, 430, 200, 50);
      contentPane.add(sumLabel);

      JButton orderButton = new JButton("주문하기");
      orderButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));

      orderButton.setBounds(260, 480, 150, 35);
      //orderButton.setBounds(340, 480, 150, 35);
      //orderButton.setBounds(220, 340, 150, 35);
      contentPane.add(orderButton);
      JButton cancelButton = new JButton("주문취소");
      cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
      cancelButton.setBounds(430, 480, 150, 35);
      //orderButton.setBounds(220, 340, 150, 35);
      contentPane.add(orderButton);
      contentPane.add(cancelButton);


          for (int i = 0; i < menu.length; i++) {
              int j=i;
               count = 0;
               sumPrice = 0;
               totalPrice = 0;
                              
               plus[i].addActionListener(new ActionListener() {
                  
                   @Override
                   public void actionPerformed(ActionEvent e) {
                      count = Integer.parseInt(amount[j].getText()) + 1;
                      amount[j].setText(count + "");
                                            
                      String price_val = price[j].getText();
                      String[] split = price_val.split("원");
                       
                       sumPrice = Integer.parseInt(split[0])*count;
                       
                       total[j].setText(sumPrice + "원"); 
                                                
                        totalPrice = gettotal();    
                        //totalPrice = sum + sumPrice - mealKit_price.get(j);
                        sumLabel.setText("금액 합계 : " + totalPrice + "원");
                                              
                       if (count > 0) {
                          minus[j].setEnabled(true);
                       }
                   }
                   
                   int gettotal() {
   
                      totalPrice = 0;
                      
                       for (int k = 0; k < menu.length; k++) { 
                         String price_val;
                          String[] split; 
                          price_val = total[k].getText();
                          split = price_val.split("원");
                          totalPrice = totalPrice + Integer.parseInt(split[0]);
                       }
                       return totalPrice;
                    }

               });
               
               minus[i].addActionListener(new ActionListener() {

                   @Override
                   public void actionPerformed(ActionEvent e) {
                      
                      count = Integer.parseInt(amount[j].getText());
                            
                       if (count > 0) {
                           count = Integer.parseInt(amount[j].getText()) - 1;
                           amount[j].setText(count + "");
                           
                           //sumPrice = mealKit_price.get(j)*count;
                           //sumPrice = Integer.parseInt(price[j].getText())*count;
                           
                           String price_val = price[j].getText();
                           String[] split = price_val.split("원");
                            
                           sumPrice = Integer.parseInt(split[0])*count;
                            
                           total[j].setText(sumPrice + "원"); 

                           totalPrice = gettotal();                      
                           //totalPrice = sum - sumPrice + mealKit_price.get(j);
                           sumLabel.setText("금액 합계 : " + totalPrice + "원");
                                                                    
                           minus[j].setEnabled(true);

                       } else {
                          minus[j].setEnabled(false);
                       }
                                          
                   }
                   int gettotal() {
                     String price_val;
                     String[] split;    
                     totalPrice = 0;
                     
                      for (int k = 0; k < menu.length; k++) {  
                         price_val = total[k].getText();
                         split = price_val.split("원");
                         totalPrice = totalPrice + Integer.parseInt(split[0]);
                      }
                      return totalPrice;
                   }
                   
               });

              
         }   // for문 end
          
          orderButton.addMouseListener(new MouseListener() {

             
              @Override
              public void mouseReleased(MouseEvent e) {
                 // TODO Auto-generated method stub
                 
              }
              
              @Override
              public void mousePressed(MouseEvent e) {
                 // TODO Auto-generated method stub
                 
              }
              
              @Override
              public void mouseExited(MouseEvent e) {
                 // TODO Auto-generated method stub
                 
              }
              
              @Override
              public void mouseEntered(MouseEvent e) {
                 // TODO Auto-generated method stub
                 
              }
              
              @Override
              public void mouseClicked(MouseEvent e) {
                  int result = JOptionPane.showConfirmDialog(null, "주문 하시겠습니까?",
                               "확인", JOptionPane.YES_NO_OPTION);
                         
                      if(result == JOptionPane.NO_OPTION) {
                         JOptionPane.showMessageDialog(null, "취소를 클릭하셨습니다.");
                      }else if(result == JOptionPane.YES_OPTION) {
                         connect();
                         saveOrder(Login.id);
                         
             
                         frame.dispose();
                      }
              }
           }); 
          
          cancelButton.addMouseListener(new MouseListener() {

              
              @Override
              public void mouseReleased(MouseEvent e) {
                 // TODO Auto-generated method stub
                 
              }
              
              @Override
              public void mousePressed(MouseEvent e) {
                 // TODO Auto-generated method stub
                 
              }
              
              @Override
              public void mouseExited(MouseEvent e) {
                 // TODO Auto-generated method stub
                 
              }
              
              @Override
              public void mouseEntered(MouseEvent e) {
                 // TODO Auto-generated method stub
                 
              }
              
              @Override
              public void mouseClicked(MouseEvent e) {
                  int result = JOptionPane.showConfirmDialog(null, "주문을 취소하시겠습니까?",
                               "확인", JOptionPane.YES_NO_OPTION);
                         
                      if(result == JOptionPane.NO_OPTION) {
                         JOptionPane.showMessageDialog(null, "취소를 클릭하셨습니다.");
                      }else if(result == JOptionPane.YES_OPTION) {
                         connect();
                         deleteOrder(Login.id);
                         
             
                         frame.dispose();
                      }
              }
           }); 
      
      } else {
          JLabel cartLabel = new JLabel("장바구니에 담긴 상품이 없습니다.");
          cartLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
          cartLabel.setBounds(250, 200, 500, 20);
          contentPane.add(cartLabel);
      }     // if문 end
      
      
         
      
      frame.add(contentPane);
      frame.setBounds(100, 100, 850, 600);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      //frame.setResizable(false);
      
 
   } // 생성자 end
   
   
   void getMenu(String id) {

      try {
         // 1. 오라클 데이터베이스에 전송할 SQL문 작성
         sql = "select menu, price, amount, total, optCheck "
               + "from shoppingCart where orderStatus = 'N' and id = ?";
         
         
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         
         // 2. 오라클 데이터베이스에 SQL문 전송 및 SQL문 실행
         rs = pstmt.executeQuery();
         //   JOptionPane.showMessageDialog(null, rs);
         

        while(rs.next()) {
           String menu = rs.getString("menu");
           int price = rs.getInt("price");
           String amount = rs.getString("amount");
           int total = rs.getInt("total");
           String optCheck = rs.getString("optCheck");
           
           //int price = price * Integer.parseInt(amount);
           
           mealKit.add(menu);
           mealKit_price.add(price);
           mealKit_total.add(total);
           mealKit_amount.add(amount);
           mealKit_optCheck.add(optCheck);
           
        }
    
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
   } // getMenu() 메서드 end
   
   void saveOrder(String id) {

     int res=0;
     int cnt=0;
     
      try {
         
         for (int i = 0; i < mealKit.size(); i++) {
            
            String checkOp;
            checkOp = checkOption(Login.id, mealKit.get(i));
            
            sql = "update ShoppingCart"
                 + " set price = ?, total = ?, orderStatus = ?, amount = ?, regdate = sysdate"
                  + " where menu = ? and id = ? and orderStatus = 'N' and optcheck = ?";
            
            
            
            pstmt = con.prepareStatement(sql);

            String price_val = price[i].getText();
            String[] split = price_val.split("원");
            String price = split[0];
                        
            pstmt.setInt(1, Integer.parseInt(price));
            
            // System.out.println("price >>> " +price[i].getText());
            String total_val = total[i].getText();
            String[] split2 = total_val.split("원");
            String total = split2[0];
                        
            pstmt.setInt(2, Integer.parseInt(total));
            
            if(Integer.parseInt(amount[i].getText()) > 0) {
               cnt = cnt + 1;
            }
            
            pstmt.setString(3, "Y");
            pstmt.setInt(4, Integer.parseInt(amount[i].getText()));
            pstmt.setString(5, menu[i].getText());
            pstmt.setString(6, id);
            pstmt.setString(7, checkOp);
            
            res = pstmt.executeUpdate();
                    
         }
         
         if (res > 0) {
            if (cnt > 0) {
               JOptionPane.showMessageDialog(null, "주문이 완료되었습니다.");    
            }else {
               JOptionPane.showMessageDialog(null, "모든 상품의 수량이 0이므로 주문이 불가합니다.");
            }
            
          }else {
             JOptionPane.showMessageDialog(null, "주문에 실패하였습니다.");
          }         
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }


   } // saveOrder() 메서드 end
   
   void deleteOrder(String id) {

	     int res=0;
	     int cnt=0;
	     
	      try {
	         
	         for (int i = 0; i < mealKit.size(); i++) {
	            
	            String checkOp;
	            checkOp = checkOption(Login.id, mealKit.get(i));
	            
	            sql = "delete ShoppingCart"
	                  + " where menu = ? and id = ? and orderStatus = 'N' and optcheck = ?";
	            
	            
	            
	            pstmt = con.prepareStatement(sql);
	            
	            if(Integer.parseInt(amount[i].getText()) > 0) {
	               cnt = cnt + 1;
	            }
	            
	            pstmt.setString(1, menu[i].getText());
	            pstmt.setString(2, id);
	            pstmt.setString(3, checkOp);
	            
	            res = pstmt.executeUpdate();               
	                    
	         }
	         
	         if (res > 0) {
	            if (cnt > 0) {
	               JOptionPane.showMessageDialog(null, "주문을 취소하였습니다.");    
	            }else {
	               JOptionPane.showMessageDialog(null, "모든 상품의 수량이 0이므로 주문 취소가 불가합니다.");
	            }
	            
	          }else {
	             JOptionPane.showMessageDialog(null, "주문 취소에 실패하였습니다.");
	          }         
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }


	   } // deleteOrder() 메서드 end
    
    void getAddr(String id) {
      try {
         sql = "select addr from member where id = ?";
         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         
         if (rs.next()) {
            addr = rs.getString("addr");
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
    }
   
    void setAddr(String id) {
       
      try {
            sql = "update member set addr = ? where id = ?";
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, addrText.getText());
            pstmt.setString(2, id);
            rs = pstmt.executeQuery();
            
            int res = pstmt.executeUpdate();
            
            if (res > 0) {
               JOptionPane.showMessageDialog(null, "주소가 변경되었습니다.");
            }else {
               JOptionPane.showMessageDialog(null, "주소 변경이 실패되었습니다.");
            }
            
      } catch (Exception e) {
         e.printStackTrace();
      }
    }
    
    String checkOption(String id, String menu) {
       result = null;
       
        try {
          sql = "select * from shoppingcart "
                + "where orderStatus = 'N' and optCheck = 'Y' and menu = ? and id = ?";
          
          pstmt = con.prepareStatement(sql);
          pstmt.setString(1, menu);
          pstmt.setString(2, id);
          rs = pstmt.executeQuery();
                   
          if (rs.next()) {
             result = "Y";
           }else {
              result = "N";
           }
          
       } catch (Exception e) {
          e.printStackTrace();
       }
        
      return result;
     }
    
    void getOption(String menu) {
        try {
           sql = "select OPT from menu where menu_name = ?";
           pstmt = con.prepareStatement(sql);

           pstmt.setString(1, menu);
           rs = pstmt.executeQuery();
           
           if (rs.next()) {
              opt = rs.getString("OPT");
           }
           
        } catch (Exception e) {
           e.printStackTrace();
        }
      }
     
    void getOption_price(String menu, int price) {
        try {
           sql = "select OptPrice from menu where menu_name = ?";
           pstmt = con.prepareStatement(sql);

           pstmt.setString(1, menu);
           rs = pstmt.executeQuery();
           
           if (rs.next()) {
              optPrice = (rs.getInt("OptPrice") + price);
           }
           
        } catch (Exception e) {
           e.printStackTrace();
        }
      }
}