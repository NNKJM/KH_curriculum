package meal_kit;

import javax.swing.*;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.EmptyBorder;

public class Ordering extends JFrame {

   private JPanel contentPane;
   // db와 연결하는 객체
   Connection con = null;

   // db에 sql문을 전송하는 객체
   PreparedStatement pstmt = null;

   // sql문의 실행 결과를 저장할 객체
   ResultSet rs = null;

   // sql문 저장할 변수
   String sql = null;

   String opt, sort, photo, addr, ingredient, his;
   String checked = "N";
   int amount, price, total, optPrice;

   void connect() {

      String driver = "oracle.jdbc.driver.OracleDriver";
      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      String user = "web";
      String password = "1234";

      try {
         // 1. 접속할 오라클 데베 드라이버를 메모리에 올리기 - 동적 작업
         Class.forName(driver);

         // 2. 오라클 데베와 연결 시도
         con = DriverManager.getConnection(url, user, password);
      } catch (Exception e) {
         e.printStackTrace();
      }

   }// connect()메소드 end

   public Ordering(String menu_name) {

      setTitle("메뉴 상세 페이지");

      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      connect();

      JLabel menuName = new JLabel(menu_name);
      menuName.setBounds(5, 5, 1000, 150);
      menuName.setHorizontalAlignment(SwingConstants.CENTER);
      menuName.setFont(new Font("맑은 고딕", Font.BOLD, 40));
      contentPane.add(menuName);

      JPanel left = new JPanel();
      left.setBounds(5, 150, 493, 452);
      contentPane.add(left);
      left.setLayout(null);

      getOpt(menu_name);

      getHis(menu_name);

//      JLabel menuInfo = new JLabel(his);
//      menuInfo.setBounds(100, 0, 476, 163);
//      left.add(menuInfo);

      JTextArea meArea = new JTextArea(his);
      meArea.setBounds(100, 50, 350, 200);
      meArea.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
      meArea.setBackground(new Color(235, 235, 235));
      meArea.setLineWrap(true);
      left.add(meArea);

      getImage(menu_name);
      JLabel image = new JLabel();
      ImageIcon kimchi = new ImageIcon(photo);
      image.setIcon(kimchi);
      image.setBounds(100, 250, 500, 200);
      left.add(image);

      JPanel right = new JPanel();
      right.setBounds(500, 150, 499, 458);
      contentPane.add(right);
      right.setLayout(null);

      JLabel content = new JLabel("기본구성");
      content.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      content.setBounds(15, 0, 100, 45);
      right.add(content);

      getIngredient(menu_name);
      JLabel sortLabel = new JLabel(ingredient);
      sortLabel.setBounds(17, 50, 465, 116);
      sortLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      right.add(sortLabel);

      JTextField cnt = new JTextField("1");
      cnt.setBounds(180, 194, 40, 27);
      right.add(cnt);
      cnt.setColumns(10);
      amount = Integer.parseInt(cnt.getText());

      JLabel option = new JLabel("옵션");
      option.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      option.setBounds(15, 250, 100, 45);
      right.add(option);
      JCheckBox optionCheck = new JCheckBox(opt);
      if (opt != null) {
         optionCheck.setBounds(15, 325, 161, 29);
         optionCheck.setFont(new Font("맑은 고딕", Font.BOLD, 15));

         right.add(optionCheck);
      }

      JButton cntM = new JButton("-");
      cntM.setBounds(100, 193, 45, 29);
      cntM.setFocusPainted(false);
      right.add(cntM);

      cntM.addMouseListener(new MouseListener() {

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
            int num = Integer.parseInt(cnt.getText());
            if (num == 1) {
               JOptionPane.showMessageDialog(null, "주문 최소수량입니다.");
            } else {
               num -= 1;
            }
            cnt.setText(Integer.toString(num));

         }
      });

      JButton cntP = new JButton("+");
      cntP.setBounds(250, 193, 45, 29);
//      cntP.setBorderPainted(false);
      cntP.setFocusPainted(false);
      right.add(cntP);

      cntP.addMouseListener(new MouseListener() {

         @Override
         public void mouseReleased(MouseEvent arg0) {
         }

         @Override
         public void mousePressed(MouseEvent arg0) {
         }

         @Override
         public void mouseExited(MouseEvent arg0) {
         }

         @Override
         public void mouseEntered(MouseEvent arg0) {
         }

         @Override
         public void mouseClicked(MouseEvent arg0) {
            amount = Integer.parseInt(cnt.getText());

            amount += 1;

            cnt.setText(Integer.toString(amount));
         }
      });

      getPrice(menu_name);
      getOptPrice(menu_name);
      getAddr(menu_name);

      JButton addCart = new JButton("장바구니 담기");
      addCart.setBounds(140, 425, 125, 30);
      addCart.setFocusPainted(false);
      right.add(addCart);
      addCart.addMouseListener(new MouseListener() {

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

            if (optionCheck.isSelected()) {
               checked = "Y";
               total = (price + optPrice) * amount;
            } else {
               checked = "N";
               total = amount * price;
            }
            addCart(menu_name);

         }
      });

      setBounds(100, 100, 1000, 700);
      setVisible(true);
   }

   void getOpt(String menu_name) {

      try {
         sql = "select opt from menu where menu_name = ?";
         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, menu_name);
         rs = pstmt.executeQuery();

         if (rs.next()) {
            opt = rs.getString("opt");
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   void getOptPrice(String menu_name) {
      try {
         sql = "select optprice from menu where menu_name = ?";
         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, menu_name);
         rs = pstmt.executeQuery();

         if (rs.next()) {
            optPrice = rs.getInt("optprice");
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   void getIngredient(String menu_name) {

      try {
         sql = "select ingredient from menu where menu_name = ?";
         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, menu_name);
         rs = pstmt.executeQuery();

         if (rs.next()) {
            ingredient = rs.getString("ingredient");
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   void getHis(String menu_name) {

      try {
         sql = "select his from menu where menu_name = ?";
         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, menu_name);
         rs = pstmt.executeQuery();

         if (rs.next()) {
            his = rs.getString("his");
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   void getImage(String menu_name) {
      try {
         sql = "select dir from menu where menu_name = ?";
         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, menu_name);
         rs = pstmt.executeQuery();

         if (rs.next()) {
            photo = rs.getString("dir");
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   void getPrice(String menu_name) {

      try {
         sql = "select price from menu where menu_name = ?";
         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, menu_name);
         rs = pstmt.executeQuery();

         if (rs.next()) {
            price = rs.getInt("price");

         }

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   void getAddr(String menu_name) {

      try {
         sql = "select addr from member where id = ?";
         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, Login.id);
         rs = pstmt.executeQuery();

         if (rs.next()) {
            addr = rs.getString("addr");

         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   void addCart(String menu_name) {
      try {
         String checkDup = checkDup(menu_name);
         if (checkDup.equals("Y")) {
            sql = "update shoppingcart set amount = amount + ?, total = total + ? where id = ? and menu = ? and optcheck = ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, amount);
            pstmt.setInt(2, total);
            pstmt.setString(3, Login.id);
            // pstmt.setString(3, menu_name);
            if (checked == "N") {
               pstmt.setString(4, menu_name);
            } else {

               pstmt.setString(4, menu_name + "(" + opt + ")");
            }

            pstmt.setString(5, checked);

            int res = pstmt.executeUpdate();

            if (res > 0) {
               JOptionPane.showMessageDialog(null, "장바구니에 추가했습니다.");
            }

         } else {
            sql = "insert into shoppingcart(id, address, menu, total,"
                  + " amount, OrderStatus, regdate, optCheck, price)"
                  + " values(?, ?, ?, ?, ?, 'N', sysdate, ?, ?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, Login.id);
            pstmt.setString(2, addr);
            // pstmt.setString(3, menu_name);
            pstmt.setInt(4, total);
            pstmt.setInt(5, amount);
            pstmt.setString(6, checked);
            if (checked == "N") {
               pstmt.setInt(7, price);
               pstmt.setString(3, menu_name);
            } else {
               pstmt.setInt(7, price + optPrice);
               pstmt.setString(3, menu_name + "(" + opt + ")");
            }

            int res = pstmt.executeUpdate();
            if (res > 0) {
               JOptionPane.showMessageDialog(null, "장바구니에 담았습니다.");
               dispose();
            }

         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   String checkDup(String menu_name) {
      int count = 0;
      String judge = null;
      try {
         sql = "select count(*) from shoppingcart where id = ? and "
               + "nvl(substr(menu, 1, instr(menu, '(',1, 1) -1), menu) = ? and "
               + "optCheck = ? and Orderstatus = 'N'";

         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, Login.id);
         pstmt.setString(2, menu_name);
         pstmt.setString(3, checked);

         rs = pstmt.executeQuery();
         if (rs.next()) {
            count = rs.getInt("count(*)");
         }

         if (count != 0) {
            judge = "Y";
         } else {
            judge = "N";
         }

      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return judge;
   }
}