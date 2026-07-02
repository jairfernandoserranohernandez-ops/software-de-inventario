/**
 * Lógica de conexión Frontend-Backend
 * Aprendiz: Jair Fernando Serrano Hernández
 */
document.getElementById("formLogin").addEventListener("submit", function(e) {
    e.preventDefault(); // Evita que la página se recargue y rompa la sesión
    
    const formData = new URLSearchParams(new FormData(this));

    // Enviamos los datos al Servlet de autenticación
    fetch("/Modulo_Web_Servlets/AuthServlet", {
        method: "POST",
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if(data.status === "success") {
            // Redirección al inventario tras autenticación exitosa
            window.location.href = "/Modulo_Web_Servlets/inventario.html"; 
        } else {
            alert("Error: Usuario o contraseña incorrectos.");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Error de conexión con el servidor. Verifica el estado de Apache Tomcat.");
    });
});