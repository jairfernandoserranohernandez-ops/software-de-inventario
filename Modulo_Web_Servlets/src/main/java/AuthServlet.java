/**
 * Módulo de Control de Acceso
 * Evidencia: GA7-220501096-AA5-EV01
 * Aprendiz: Jair Fernando Serrano Hernández
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configuramos la respuesta como JSON para que el frontend la pueda leer
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        String user = request.getParameter("usuario");
        String pass = request.getParameter("contrasena");

        try {
            // Conexión a la base de datos definida en la guía de aprendizaje
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Luzemi07");
            
            // Consulta segura para verificar credenciales
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios WHERE usuario=? AND contrasena=?");
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Autenticación correcta: respondemos éxito
                out.print("{\"status\":\"success\"}");
            } else {
                // Autenticación fallida
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.print("{\"status\":\"error\"}");
            }
            con.close();
        } catch (Exception e) {
            // Manejo de errores para cumplir con la robustez exigida por el SENA
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }
}