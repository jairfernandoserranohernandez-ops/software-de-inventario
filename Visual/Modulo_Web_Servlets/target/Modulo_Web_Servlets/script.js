/**
 * Lógica de conexión Frontend-Backend
 * Sistema de Inventario - Evidencia SENA
 */
document.getElementById("formLogin").addEventListener("submit", function(e) {
    e.preventDefault(); 
    
    const formData = new URLSearchParams(new FormData(this));

    fetch("AuthServlet", {
        method: "POST",
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Error en la respuesta del servidor");
        }
        return response.json();
    })
    .then(data => {
        if (data.status === "success") {
            // Te manda directo a la pantalla de inventario
            window.location.href = "inventario.html"; 
        } else {
            alert("Error: Usuario o contraseña incorrectos.");
        }
    })
    .catch(error => {
        console.error("Error detectado:", error);
        alert("Error de credenciales o de base de datos.");
    });
});