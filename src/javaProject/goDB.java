package javaProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class goDB {
	private Connection con;
	private String sql;
	private PreparedStatement pstmt;
	private static ResultSet rs;
	private static int n; // 디비 테이블의 레코드의 크기

	public goDB(String sql) {
		con=null; // db 연결 설정하는 인터페이스
		String name="org.gjt.mm.mysql.Driver";
		String url="jdbc:mysql://localhost:3306/javaproject?useSSL=false&useUnicode=true&characterEncoding=euckr";
		String user="root";
		String password="bomi1122";

		try {
			Class.forName(name); // jdbc 드라이버 적재(초석)
			con=DriverManager.getConnection(url, user, password); // 데이터베이스를 jdbc와 연결
			System.out.println("연결 성공!"); 
			this.sql=sql;
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			n=0; 
			while(rs.next()) { // 직원의 id,이름,이메일 출력
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" ");
				n++;
			}
		} catch(Exception e) {
			System.out.println("연결 안됨");
			e.printStackTrace();
		} /*finally {
			try {
				if(con!=null && !con.isClosed()) { 
					con.close();
				}
			} catch(Exception e ) {
				e.printStackTrace();
			}
		}*/
	}
	public int question() throws SQLException {
		// TODO Auto-generated method stub

		outterWhile:while(true) { // 이중 while문에서 가장 바깥쪽 while문 탈출 위해 라벨을 붙임 
			int kk=0;
			int random=(int)(Math.random()*n);
			System.out.println("random:"+random);
			rs.beforeFirst(); // 커서를 맨 처음 전으로 표시
			while(rs.next()) { // 직원의 id,이름,이메일 출력
				if(kk==random) {
					System.out.println(rs.getString(2)+" ");
					int pp=JOptionPane.showConfirmDialog(null, rs.getString(2),"GoGoGame문제",JOptionPane.YES_NO_OPTION); // 확인창 뜨게함
					System.out.println(pp); // 예를 누르면 무조건 0, 아니요를 누르면 1이 반환됨, 끄기를 누르면 -1이 반환됨
					if(pp==Integer.parseInt(rs.getString(3))) {
						System.out.println("맞았습니다.");
						return 1; // 외부 while문 탈출
					}
					else if(pp==-1)
						return 0; // 외부 while문 다시 반복
					else {
						System.out.println("틀렸습니다");
						return 0;
					}
				}
				kk++;
			}
			
		}
		
	}

}
