package interview_test_2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import com.amazonaws.services.secretsmanager.*;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;

import org.json.JSONObject;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String secretName = "hostedftp";
	    System.out.println("Requesting secret...");
	    AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard().withRegion("us-east-1").build();
	    GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
	    GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);
	    System.out.println("secret retrieved ");
	    String secretString = getSecretValueResult.getSecretString();
		JSONObject jsonObject = new JSONObject(secretString);
		String rds_username = jsonObject.getString("username");
		String rds_password = jsonObject.getString("password");
		// putting password in text file that isn't stored in repository

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", rds_username);
        connectionProps.put("password", rds_password);
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
        try {
			conn = DriverManager.getConnection(
				"jdbc:mysql://database-1.ci5u66lyoqgj.us-east-1.rds.amazonaws.com:3306/mydatabase?characterEncoding=utf8",
				connectionProps);
				
				// 			for local database
				//			conn = DriverManager.getConnection(
				//					"jdbc:mysql://localhost:3306/mydatabase?characterEncoding=utf8",
				//			        connectionProps);
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			response.setContentType("text/html;charset=UTF-8");
			
			String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);


			// Using prepared statements to defend against sql injections.
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
		        response.sendRedirect("index.jsp?error=1");
		    }
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
