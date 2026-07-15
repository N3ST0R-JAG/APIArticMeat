const API_URL = "http://localhost:8080/api";

// =============================================
// AUTENTICACIÓN
// =============================================

/**
 * LOGIN: consulta al backend si el usuario existe y si la contraseña es correcta.
 * El backend busca por CI en la BD y compara la contraseña.
 * Devuelve los datos del usuario si todo está bien, o lanza un error.
 */
async function loginUsuario(ci, contrasena) {
    const response = await fetch(`${API_URL}/usuario/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ ci: parseInt(ci), contrasena: contrasena })
    });

    if (response.status === 404) {
        throw new Error("NO_EXISTE"); // El CI no está registrado
    }
    if (response.status === 401) {
        throw new Error("CONTRASENA_INCORRECTA"); // CI existe pero contraseña mal
    }
    if (!response.ok) {
        throw new Error("ERROR_SERVIDOR");
    }

    return await response.json(); // Devuelve { ID, nombre, ci, email, direccion, ... }
}

/**
 * REGISTRO: envía los datos del nuevo usuario al backend para guardarlo en la BD.
 */
async function registrarUsuario(usuario) {
    const response = await fetch(`${API_URL}/usuario/registrar`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(usuario)
    });

    if (response.status === 409) {
        throw new Error("YA_EXISTE"); // El CI o email ya está registrado
    }
    if (!response.ok) {
        throw new Error("ERROR_SERVIDOR");
    }

    return await response.json();
}

// =============================================
// PRODUCTOS
// =============================================

async function obtenerProductos() {
    try {
        const response = await fetch(`${API_URL}/productos`);
        return await response.json();
    } catch (error) {
        console.error("Error al obtener productos:", error);
    }
}

async function registrarProducto(producto) {
    try {
        const response = await fetch(`${API_URL}/productos/registrar`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(producto)
        });
        return await response.json();
    } catch (error) {
        console.error("Error al registrar producto:", error);
    }
}

// =============================================
// COMPRAS
// =============================================

async function registrarCompra(compra) {
    const response = await fetch(`${API_URL}/compras/registrar`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(compra)
    });

    if (!response.ok) {
        throw new Error("ERROR_COMPRA");
    }

    return await response.json();
}
async function registrarDetalleCompra(detalle) {
    const response = await fetch(`${API_URL}/detalle/registrar`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(detalle)
    });

    if (!response.ok) {
        throw new Error("ERROR_DETALLE");
    }

    return await response.json();
}