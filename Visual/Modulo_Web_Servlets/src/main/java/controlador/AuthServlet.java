package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class AuthServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recibo los datos del formulario (deben coincidir con el name en tu HTML)
        String user = request.getParameter("usuario");
        String pass = request.getParameter("password"); 

        try {
            // Cargo el driver para conectar con MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Conecto a la base de datos 'mydb'
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC", "root", "Luzemi07");
            
            // Consulta basada en tu tabla 'usuarios'
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios WHERE username = ? AND password = ?");
            ps.setString(1, user);
            ps.setString(2, pass);
            
            ResultSet rs = ps.executeQuery();
            
            // Si el usuario existe, la consulta devuelve una fila
            if (rs.next()) {
                // Login correcto: redirige a la página principal
                response.sendRedirect("inventario.html");
            } else {
                // Login incorrecto: regresa al login con un aviso de error
                response.sendRedirect("login.html?error=1");
            }
            
            con.close(); 
        } catch (Exception e) {
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print("Error interno: " + e.getMessage());
        }
    }
}