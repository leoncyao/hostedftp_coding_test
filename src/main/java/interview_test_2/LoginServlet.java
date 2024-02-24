package interview_test_2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.File;  // Import the File class

import java.util.Properties;
import java.util.Scanner;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		System.out.println( System.getProperty("user.dir"));
		File myObj = new File("password.txt");
        Scanner myReader = new Scanner(myObj);
        
        String root_password = myReader.nextLine();
        
        myReader.close();
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "admin");
        
        
        connectionProps.put("password", root_password);
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
        try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://database-1.ci5u66lyoqgj.us-east-1.rds.amazonaws.com:3306/mydatabase?characterEncoding=utf8",
			        connectionProps);
			System.out.println("Connected to database");
			
//			conn = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/mydatabase?characterEncoding=utf8",
//			        connectionProps);
//			System.out.println("Connected to database");
			
			String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute query
            ResultSet rs = stmt.executeQuery();
             
    	    if (rs.next()) {
    	    	
    	    	String company_name = rs.getString("company");
    	    	String role_name = rs.getString("role");
    	    	
		        // If login successful, redirect to a welcome page
    	    	String url = String.format("welcome.jsp?company=%s&role=%s", company_name, role_name);
		        response.sendRedirect(String.format(url));
		    } else {
		        // If login fails, redirect back to the login page with an error message
		        response.sendRedirect("login.jsp?error=1");
		    }
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
