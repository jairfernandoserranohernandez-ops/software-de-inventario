import React, { useState } from 'react';

// =====================================================================
// Componente Principal: Control de Inventario
// Aprendiz: Jair Fernando Serrano Hernández 
// Evidencia: GA7-220501096-AA4-EV03
// =====================================================================
function App() {
  // Estado para la lista de productos
  const [productos, setProductos] = useState([
    { id: 1, nombre: 'usb', cantidad: 100 }
  ]);

  // Estado para el formulario
  const [nuevoProducto, setNuevoProducto] = useState({ nombre: '', cantidad: '' });

  // Manejador de lo que el usuario escribe
  const manejarCambio = (e) => {
    const { name, value } = e.target;
    setNuevoProducto({ ...nuevoProducto, [name]: value });
  };

  // Guardar el producto en la tabla
  const guardarProducto = (e) => {
    e.preventDefault();
    
    if (!nuevoProducto.nombre || !nuevoProducto.cantidad) {
      alert("Por favor, rellene todos los campos.");
      return;
    }

    const productoAGuardar = {
      id: productos.length + 1,
      nombre: nuevoProducto.nombre,
      cantidad: parseInt(nuevoProducto.cantidad)
    };

    setProductos([...productos, productoAGuardar]);
    setNuevoProducto({ nombre: '', cantidad: '' }); 
  };

  return (
    <div style={{ display: 'flex', fontFamily: 'Arial, sans-serif', minHeight: '100vh', margin: 0, backgroundColor: '#f4f6f9', width: '100vw' }}>
      
      {/* Menú Lateral */}
      <aside style={{ width: '220px', background: '#1e293b', color: 'white', padding: '25px' }}>
        <h2 style={{ fontSize: '18px', margin: '0 0 20px 0', borderBottom: '1px solid #334155', paddingBottom: '10px' }}>SENA ADSO</h2>
        <ul style={{ listStyle: 'none', padding: 0, margin: 0 }}>
          <li style={{ marginBottom: '15px', color: '#38bdf8', fontWeight: 'bold' }}>📦 Inventario</li>
          <li style={{ color: '#94a3b8', cursor: 'pointer' }}>Configuración</li>
        </ul>
      </aside>

      {/* Panel de Contenido */}
      <main style={{ padding: '30px', flex: 1 }}>
        <h1 style={{ color: '#0f172a', margin: '0 0 25px 0' }}>Control de Inventario</h1>

        {/* Tabla */}
        <section style={{ backgroundColor: 'white', padding: '20px', borderRadius: '8px', boxShadow: '0 1px 3px rgba(0,0,0,0.1)', marginBottom: '30px' }}>
          <h3 style={{ marginTop: '0', color: '#334155' }}>Productos en bodega</h3>
          <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '10px' }}>
            <thead>
              <tr style={{ background: '#0284c7', color: 'white', textAlign: 'left' }}>
                <th style={{ padding: '12px' }}>Producto</th>
                <th style={{ padding: '12px' }}>Cantidad</th>
              </tr>
            </thead>
            <tbody>
              {productos.map((prod) => (
                <tr key={prod.id} style={{ borderBottom: '1px solid #e2e8f0' }}>
                  <td style={{ padding: '12px', color: '#334155' }}>{prod.nombre}</td>
                  <td style={{ padding: '12px', color: '#334155', fontWeight: 'bold' }}>{prod.cantidad}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>

        {/* Formulario */}
        <section style={{ backgroundColor: 'white', padding: '20px', borderRadius: '8px', boxShadow: '0 1px 3px rgba(0,0,0,0.1)', width: '320px' }}>
          <h3 style={{ marginTop: '0', color: '#334155' }}>Agregar producto</h3>
          <form onSubmit={guardarProducto} style={{ display: 'flex', flexDirection: 'column', gap: '12px' }}>
            <input
              type="text"
              name="nombre"
              placeholder="Nombre del artículo"
              value={nuevoProducto.nombre}
              onChange={manejarCambio}
              style={{ padding: '10px', borderRadius: '4px', border: '1px solid #cbd5e1' }}
            />
            <input
              type="number"
              name="cantidad"
              placeholder="Cantidad"
              value={nuevoProducto.cantidad}
              onChange={manejarCambio}
              style={{ padding: '10px', borderRadius: '4px', border: '1px solid #cbd5e1' }}
            />
            <button type="submit" style={{ background: '#10b981', color: 'white', padding: '10px', border: 'none', borderRadius: '4px', cursor: 'pointer', fontWeight: 'bold' }}>
              Guardar en Stock
            </button>
          </form>
        </section>
      </main>

    </div>
  );
}

export default App;