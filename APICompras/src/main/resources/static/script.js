let clienteActual = null;
let carrito = [];
let total = 0;

// =============================================
// MODAL LOGIN
// =============================================

/**
 * Se llama al hacer clic en "Ingresar".
 * Consulta a la API si el CI existe y si la contraseña es correcta.
 */
async function iniciarSesion() {
    const ci = document.getElementById("ci").value.trim();
    const contrasena = document.getElementById("contrasena").value.trim();

    if (ci === "" || contrasena === "") {
        mostrarError("loginError", "Debe ingresar su CI y contraseña.");
        return;
    }

    const btnLogin = document.getElementById("btnLogin");
    btnLogin.textContent = "Verificando...";
    btnLogin.disabled = true;

    try {
        // Consulta a la API: ¿existe este usuario y la contraseña es correcta?
        const usuario = await loginUsuario(ci, contrasena);

        // Si llegamos aquí, el login fue exitoso
        clienteActual = {
            id: usuario.id,
            nombre: usuario.nombre,
            ci: usuario.ci || usuario.ci,
            email: usuario.email,
            direccion: usuario.direccion,
            tipoEmpresa: usuario.tipoEmpresa || "Cliente registrado"
        };

        document.getElementById("modalLogin").style.display = "none";
        limpiarErrores("loginError");

    } catch (error) {
        // Manejo de cada caso de error según lo que devuelve la API
        if (error.message === "NO_EXISTE") {
            mostrarError("loginError", "❌ No encontramos ninguna cuenta con ese CI. ¿Desea registrarse?");
        } else if (error.message === "CONTRASENA_INCORRECTA") {
            mostrarError("loginError", "❌ La contraseña es incorrecta. Intente nuevamente.");
        } else {
            mostrarError("loginError", "❌ Error al conectar con el servidor. Intente más tarde.");
        }
    } finally {
        btnLogin.textContent = "Ingresar";
        btnLogin.disabled = false;
    }
}

// =============================================
// MODAL REGISTRO
// =============================================

function mostrarRegistro() {
    document.getElementById("modalLogin").style.display = "none";
    document.getElementById("modalRegistro").style.display = "flex";
}

function mostrarLogin() {
    document.getElementById("modalRegistro").style.display = "none";
    document.getElementById("modalLogin").style.display = "flex";
}

/**
 * Se llama al hacer clic en "Registrarme".
 * Envía los datos al backend para crear la cuenta en la BD.
 */
async function registrarCliente() {
    const nombre = document.getElementById("registroNombre").value.trim();
    const ci = document.getElementById("registroCi").value.trim();
    const email = document.getElementById("registroEmail").value.trim();
    const contrasena = document.getElementById("registroContrasena").value.trim();
    const direccion = document.getElementById("registroDireccion").value.trim();
    const tipoEmpresa = document.getElementById("registroTipoEmpresa").value;

    if (!nombre || !ci || !email || !contrasena || !direccion || !tipoEmpresa) {
        mostrarError("registroError", "Debe completar todos los campos.");
        return;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        mostrarError("registroError", "Por favor ingrese un email válido.");
        return;
    }

    const btnRegistro = document.getElementById("btnRegistro");
    btnRegistro.textContent = "Registrando...";
    btnRegistro.disabled = true;

    try {
        const usuarioData = {
            nombre: nombre,
            ci: parseInt(ci),
            email: email,
            contrasena: contrasena,
            direccion: direccion,
            tipoEmpresa: tipoEmpresa
        };

        // Envía los datos al backend para crear la cuenta
        const usuarioGuardado = await registrarUsuario(usuarioData);

        clienteActual = {
            id: usuarioGuardado.id,
            nombre: usuarioGuardado.nombre,
            ci: usuarioGuardado.ci || usuarioGuardado.ci,
            email: usuarioGuardado.email,
            direccion: usuarioGuardado.direccion,
            tipoEmpresa: tipoEmpresa
        };

        document.getElementById("modalRegistro").style.display = "none";
        limpiarErrores("registroError");
        alert("✅ Registro completado. Ya puede comprar en Arctic Meat.");

    } catch (error) {
        if (error.message === "YA_EXISTE") {
            mostrarError("registroError", "❌ El CI o email ya está registrado. ¿Ya tiene cuenta?");
        } else {
            mostrarError("registroError", "❌ Error al conectar con el servidor. Intente más tarde.");
        }
    } finally {
        btnRegistro.textContent = "Registrarme";
        btnRegistro.disabled = false;
    }
}

// =============================================
// UTILIDADES DE UI
// =============================================

function mostrarError(idElemento, mensaje) {
    const el = document.getElementById(idElemento);
    if (el) {
        el.textContent = mensaje;
        el.style.display = "block";
    }
}

function limpiarErrores(idElemento) {
    const el = document.getElementById(idElemento);
    if (el) {
        el.textContent = "";
        el.style.display = "none";
    }
}

// =============================================
// CARRITO
// =============================================

function mostrarMensaje() {
    alert("Bienvenido a Arctic Meat");
}

function agregarCarrito(nombre, precio) {
    const productoExistente = carrito.find(function (producto) {
        return producto.nombre === nombre;
    });

    if (productoExistente) {
        productoExistente.cantidad += 1;
    } else {
        carrito.push({ nombre: nombre, precio: precio, cantidad: 1 });
    }

    actualizarCarrito();
}

function actualizarCarrito() {
    const lista = document.getElementById("lista-carrito");
    lista.innerHTML = "";
    total = 0;

    carrito.forEach(function (producto, index) {
        const item = document.createElement("li");
        const subtotal = producto.precio * producto.cantidad;
        total += subtotal;

        item.innerHTML = `
            ${producto.nombre} x ${producto.cantidad} — Gs. ${formatearNumero(subtotal)}
            <button class="eliminar" onclick="eliminarProducto(${index})">Eliminar</button>
        `;

        lista.appendChild(item);
    });

    document.getElementById("total").textContent = formatearNumero(total);
}

function eliminarProducto(index) {
    carrito.splice(index, 1);
    actualizarCarrito();
}

function vaciarCarrito() {
    carrito = [];
    total = 0;
    actualizarCarrito();
    document.getElementById("factura").style.display = "none";
}

// =============================================
// COMPRA
// =============================================

async function comprar() {
    if (carrito.length === 0) {
        alert("Debe agregar productos al carrito antes de comprar.");
        return;
    }

    if (!clienteActual) {
        alert("Debe ingresar o registrarse antes de comprar.");
        document.getElementById("modalLogin").style.display = "flex";
        return;
    }

    try {
        // 1. Guardar la compra principal
        const compraData = {
            usuarioID: clienteActual.id,
            fecha: new Date().toISOString().split("T")[0],
            total: total,
            estado: "Pendiente"
        };

        const compraGuardada = await registrarCompra(compraData);

        // 2. Guardar el detalle de cada producto del carrito
        for (const producto of carrito) {
            await registrarDetalleCompra({
                compraID: compraGuardada.id,
                productoNombre: producto.nombre,
                cantidad: producto.cantidad,
                precio: producto.precio
            });
        }

        // 3. Mostrar factura
        generarFactura();
        document.getElementById("factura").style.display = "block";
        document.getElementById("factura").scrollIntoView({ behavior: "smooth" });
        alert("✅ Compra realizada exitosamente.");

    } catch (error) {
        console.error("Error al guardar la compra:", error);
        alert("❌ Error al guardar la compra. Intente nuevamente.");
    }
}
// =============================================
// FACTURA
// =============================================

function generarFactura() {
    const fecha = new Date();
    const detalle = document.getElementById("facturaDetalle");
    detalle.innerHTML = "";

    carrito.forEach(function (producto, index) {
        const fila = document.createElement("tr");
        const subtotal = producto.precio * producto.cantidad;

        fila.innerHTML = `
            <td>${String(index + 1).padStart(3, "0")}</td>
            <td>${producto.nombre}</td>
            <td>${producto.cantidad}</td>
            <td>Gs ${formatearNumero(producto.precio)}</td>
            <td>Gs ${formatearNumero(subtotal)}</td>
        `;

        detalle.appendChild(fila);
    });

    document.getElementById("facturaFecha").textContent = formatearFecha(fecha);
    document.getElementById("facturaNumero").textContent = crearNumeroFactura(fecha);
    document.getElementById("facturaCliente").textContent = clienteActual.nombre;
    document.getElementById("facturaCi").textContent = clienteActual.ci;
    document.getElementById("facturaDireccion").textContent = clienteActual.direccion;
    document.getElementById("facturaTipoEmpresa").textContent = clienteActual.tipoEmpresa;
    document.getElementById("facturaSubtotal").textContent = formatearNumero(total);
    document.getElementById("facturaTotal").textContent = formatearNumero(total);
    document.getElementById("facturaTotalTexto").textContent = convertirTotalATexto(total);
}

function crearNumeroFactura(fecha) {
    const codigo = String(fecha.getTime()).slice(-7);
    return `001-001-${codigo}`;
}

function formatearFecha(fecha) {
    return fecha.toLocaleDateString("es-PY", {
        day: "2-digit",
        month: "2-digit",
        year: "numeric"
    });
}

function formatearNumero(numero) {
    return numero.toLocaleString("es-PY");
}

function convertirTotalATexto(numero) {
    return `${formatearNumero(numero)} GUARANIES`;
}