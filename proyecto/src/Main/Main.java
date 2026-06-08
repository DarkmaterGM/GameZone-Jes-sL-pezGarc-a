package Main;

import DAO.*;
import DTO.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final Scanner sc = new Scanner(System.in);

    // DAOs 
    // Se declaran como atributos estáticos para que todos los métodos del Main
    // puedan acceder a ellos sin necesidad de pasarlos como parámetros.

    private static CategoriaD categoriaDAO;
    private static ClienteD clienteDAO;
    private static EmpleadoD empleadoDAO;
    private static ProductoD productoDAO;
    private static VentaD ventaDAO;
    private static Detalle_ventaD detalleVentaDAO;

    // PUNTO DE ENTRADA
    // Inicializa los DAOs (y con ellos la conexión Singleton a la BD) y lanza
    // el bucle principal del menú. Si la conexión falla, muestra el error y
    // termina la ejecución antes de mostrar nada al usuario.
    
    public static void main(String[] args) {

        try {
            categoriaDAO = new CategoriaD();
            clienteDAO = new ClienteD();
            empleadoDAO = new EmpleadoD();
            productoDAO = new ProductoD();
            ventaDAO = new VentaD();
            detalleVentaDAO = new Detalle_ventaD();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            return;
        }

        boolean salir = false;
        while (!salir) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║        GameZone — Menú       ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1. Gestión de categorías    ║");
            System.out.println("║  2. Gestión de clientes      ║");
            System.out.println("║  3. Gestión de empleados     ║");
            System.out.println("║  4. Gestión de productos     ║");
            System.out.println("║  5. Gestión de ventas        ║");
            System.out.println("║  6. Gestión de detalles      ║");
            System.out.println("║  0. Salir                    ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Opción: ");

            switch (leerInt()) {
                case 1 -> menuCategorias();
                case 2 -> menuClientes();
                case 3 -> menuEmpleados();
                case 4 -> menuProductos();
                case 5 -> menuVentas();
                case 6 -> menuDetalles();
                case 0 -> salir = true;
                default -> System.out.println("Opción no válida.");
            }
        }
        System.out.println("Hasta luego.");
    }

    
    private static void menuCategorias() {
        System.out.println("\n── Categorías ──────────────────");
        System.out.println("  1. Listar todas");
        System.out.println("  2. Buscar por id");
        System.out.println("  3. Insertar");
        System.out.println("  4. Actualizar");
        System.out.println("  5. Eliminar");
        System.out.println("  0. Volver");
        System.out.print("Opción: ");

        try {
            switch (leerInt()) {

                case 1 -> {
                    List<Categoria> lista = categoriaDAO.listarTodos();
                    if (lista.isEmpty()) System.out.println("No hay categorías registradas.");
                    else lista.forEach(System.out::println);
                }

                
                case 2 -> {
                    System.out.print("ID de la categoría: ");
                    String id = sc.nextLine().trim();
                    Categoria c = categoriaDAO.buscarPorId(id);
                    if (c == null) throw new CategoriaNoEncontradoException(id);
                    System.out.println(c);
                }

                
                case 3 -> {
                    System.out.print("ID: ");
                    String id = sc.nextLine().trim();
                    if (categoriaDAO.buscarPorId(id) != null) throw new CategoriaYaRegistradoException(id);
                    System.out.print("Nombre: "); String nom = sc.nextLine().trim();
                    System.out.print("Descripción: "); String des = sc.nextLine().trim();
                    boolean ok = categoriaDAO.insertar(id, new Categoria(nom, des));
                    System.out.println(ok ? "Categoría insertada." : "No se pudo insertar.");
                }

                
                case 4 -> {
                    System.out.print("ID a actualizar: ");
                    String id = sc.nextLine().trim();
                    if (categoriaDAO.buscarPorId(id) == null) throw new CategoriaNoEncontradoException(id);
                    System.out.print("Nuevo nombre: "); String nom = sc.nextLine().trim();
                    System.out.print("Nueva descripción: "); String des = sc.nextLine().trim();
                    boolean ok = categoriaDAO.actualizar(id, new Categoria(nom, des));
                    System.out.println(ok ? "Categoría actualizada." : "No se pudo actualizar.");
                }

              
                case 5 -> {
                    System.out.print("ID a eliminar: ");
                    String id = sc.nextLine().trim();
                    if (categoriaDAO.buscarPorId(id) == null) throw new CategoriaNoEncontradoException(id);
                    boolean ok = categoriaDAO.eliminar(id);
                    System.out.println(ok ? "Categoría eliminada." : "No se pudo eliminar.");
                }
                case 0 -> { }
                default -> System.out.println("Opción no válida.");
            }
        } catch (CategoriaNoEncontradoException | CategoriaYaRegistradoException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }


    
    private static void menuClientes() {
        System.out.println("\n── Clientes ────────────────────");
        System.out.println("  1. Listar todos");
        System.out.println("  2. Buscar por DNI");
        System.out.println("  3. Insertar");
        System.out.println("  4. Actualizar");
        System.out.println("  5. Eliminar");
        System.out.println("  0. Volver");
        System.out.print("Opción: ");

        try {
            switch (leerInt()) {

               
                case 1 -> {
                    List<Cliente> lista = clienteDAO.listarTodos();
                    if (lista.isEmpty()) System.out.println("No hay clientes registrados.");
                    else lista.forEach(System.out::println);
                }

                
                case 2 -> {
                    System.out.print("DNI: ");
                    String dni = sc.nextLine().trim();
                    Cliente c = clienteDAO.buscarPorDni(dni);
                    if (c == null) throw new ClienteNoEncontradoException(dni);
                    System.out.println(c);
                }

                
                case 3 -> {
                    System.out.print("DNI: ");
                    String dni = sc.nextLine().trim();
                    if (clienteDAO.buscarPorDni(dni) != null) throw new ClienteYaRegistradoException(dni);
                    Cliente c = leerClienteConDni(dni);
                    boolean ok = clienteDAO.insertar(c);
                    System.out.println(ok ? "Cliente insertado." : "No se pudo insertar.");
                }

                
                case 4 -> {
                    System.out.print("DNI del cliente a actualizar: ");
                    String dni = sc.nextLine().trim();
                    if (clienteDAO.buscarPorDni(dni) == null) throw new ClienteNoEncontradoException(dni);
                    Cliente c = leerClienteConDni(dni);
                    boolean ok = clienteDAO.actualizar(c);
                    System.out.println(ok ? "Cliente actualizado." : "No se pudo actualizar.");
                }

                
                case 5 -> {
                    System.out.print("DNI a eliminar: ");
                    String dni = sc.nextLine().trim();
                    if (clienteDAO.buscarPorDni(dni) == null) throw new ClienteNoEncontradoException(dni);
                    boolean ok = clienteDAO.eliminar(dni);
                    System.out.println(ok ? "Cliente eliminado." : "No se pudo eliminar.");
                }
                case 0 -> { }
                default -> System.out.println("Opción no válida.");
            }
        } catch (ClienteNoEncontradoException | ClienteYaRegistradoException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

    
    private static void menuEmpleados() {
        System.out.println("\n── Empleados ───────────────────");
        System.out.println("  1. Listar todos");
        System.out.println("  2. Buscar por id");
        System.out.println("  3. Insertar");
        System.out.println("  4. Actualizar");
        System.out.println("  5. Eliminar");
        System.out.println("  6. Listar por cargo");
        System.out.println("  0. Volver");
        System.out.print("Opción: ");

        try {
            switch (leerInt()) {

                
                case 1 -> {
                    List<Empleado> lista = empleadoDAO.listarTodos();
                    if (lista.isEmpty()) System.out.println("No hay empleados registrados.");
                    else lista.forEach(System.out::println);
                }

                
                case 2 -> {
                    System.out.print("ID del empleado: ");
                    int id = leerInt();
                    Empleado e = empleadoDAO.buscarPorId(id);
                    if (e == null) throw new EmpleadoNoEncontradoException(id);
                    System.out.println(e);
                }

                
                case 3 -> {
                    System.out.print("ID: ");
                    int id = leerInt();
                    if (empleadoDAO.buscarPorId(id) != null) throw new EmpleadoYaRegistradoException(id);
                    Empleado e = leerEmpleadoConId(id);
                    boolean ok = empleadoDAO.insertar(e);
                    System.out.println(ok ? "Empleado insertado." : "No se pudo insertar.");
                }

                
                case 4 -> {
                    System.out.print("ID del empleado a actualizar: ");
                    int id = leerInt();
                    if (empleadoDAO.buscarPorId(id) == null) throw new EmpleadoNoEncontradoException(id);
                    Empleado e = leerEmpleadoConId(id);
                    boolean ok = empleadoDAO.actualizar(e);
                    System.out.println(ok ? "Empleado actualizado." : "No se pudo actualizar.");
                }

                
                case 5 -> {
                    System.out.print("ID a eliminar: ");
                    int id = leerInt();
                    if (empleadoDAO.buscarPorId(id) == null) throw new EmpleadoNoEncontradoException(id);
                    boolean ok = empleadoDAO.eliminar(id);
                    System.out.println(ok ? "Empleado eliminado." : "No se pudo eliminar.");
                }

                case 6 -> {
                    System.out.print("Cargo: ");
                    String cargo = sc.nextLine().trim();
                    List<Empleado> lista = empleadoDAO.listarPorCargo(cargo);
                    if (lista.isEmpty()) System.out.println("No hay empleados con ese cargo.");
                    else lista.forEach(System.out::println);
                }
                case 0 -> { }
                default -> System.out.println("Opción no válida.");
            }
        } catch (EmpleadoNoEncontradoException | EmpleadoYaRegistradoException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

   
    private static void menuProductos() {
        System.out.println("\n── Productos ───────────────────");
        System.out.println("  1. Listar todos");
        System.out.println("  2. Buscar por id");
        System.out.println("  3. Insertar");
        System.out.println("  4. Actualizar");
        System.out.println("  5. Eliminar");
        System.out.println("  6. Listar por categoría");
        System.out.println("  7. Actualizar stock");
        System.out.println("  0. Volver");
        System.out.print("Opción: ");

        try {
            switch (leerInt()) {

                case 1 -> {
                    List<Producto> lista = productoDAO.listarTodos();
                    if (lista.isEmpty()) System.out.println("No hay productos registrados.");
                    else lista.forEach(System.out::println);
                }

              
                case 2 -> {
                    System.out.print("ID del producto: ");
                    String id = sc.nextLine().trim();
                    Producto p = productoDAO.buscarPorId(id);
                    if (p == null) throw new ProductoNoEncontradoException(id);
                    System.out.println(p);
                }

                
                case 3 -> {
                    System.out.print("ID del nuevo producto: ");
                    String id = sc.nextLine().trim();
                    if (productoDAO.buscarPorId(id) != null) throw new ProductoYaRegistradoException(id);
                    Producto p = leerProductoConId(id);
                    boolean ok = productoDAO.insertar(p);
                    System.out.println(ok ? "Producto insertado." : "No se pudo insertar.");
                }

                case 4 -> {
                    System.out.print("ID del producto a actualizar: ");
                    String id = sc.nextLine().trim();
                    if (productoDAO.buscarPorId(id) == null) throw new ProductoNoEncontradoException(id);
                    Producto p = leerProductoConId(id);
                    boolean ok = productoDAO.actualizar(p);
                    System.out.println(ok ? "Producto actualizado." : "No se pudo actualizar.");
                }

              
                case 5 -> {
                    System.out.print("ID a eliminar: ");
                    String id = sc.nextLine().trim();
                    if (productoDAO.buscarPorId(id) == null) throw new ProductoNoEncontradoException(id);
                    boolean ok = productoDAO.eliminar(id);
                    System.out.println(ok ? "Producto eliminado." : "No se pudo eliminar.");
                }

                case 6 -> {
                    System.out.print("ID de categoría: ");
                    int idCat = leerInt();
                    List<Producto> lista = productoDAO.listarPorCategoria(idCat);
                    if (lista.isEmpty()) System.out.println("No hay productos en esa categoría.");
                    else lista.forEach(System.out::println);
                }

          
                case 7 -> {
                    System.out.print("ID del producto: ");
                    String id = sc.nextLine().trim();
                    if (productoDAO.buscarPorId(id) == null) throw new ProductoNoEncontradoException(id);
                    System.out.print("Nuevo stock: ");
                    int stock = leerInt();
                    boolean ok = productoDAO.actualizarStock(id, stock);
                    System.out.println(ok ? "Stock actualizado." : "No se pudo actualizar.");
                }
                case 0 -> { }
                default -> System.out.println("Opción no válida.");
            }
        } catch (ProductoNoEncontradoException | ProductoYaRegistradoException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

  
    private static void menuVentas() {
        System.out.println("\n── Ventas ──────────────════════");
        System.out.println("  1. Listar todas");
        System.out.println("  2. Buscar por id");
        System.out.println("  3. Insertar");
        System.out.println("  4. Actualizar");
        System.out.println("  5. Eliminar");
        System.out.println("  6. Listar por cliente");
        System.out.println("  7. Listar por empleado");
        System.out.println("  0. Volver");
        System.out.print("Opción: ");

        try {
            switch (leerInt()) {

                case 1 -> {
                    List<Venta> lista = ventaDAO.listarTodos();
                    if (lista.isEmpty()) System.out.println("No hay ventas registradas.");
                    else lista.forEach(System.out::println);
                }

                case 2 -> {
                    System.out.print("ID de la venta: ");
                    int id = leerInt();
                    Venta v = ventaDAO.buscarPorId(id);
                    if (v == null) throw new VentaNoEncontradoException(id);
                    System.out.println(v);
                }

                
                case 3 -> {
                    System.out.print("ID de la venta: ");
                    int id = leerInt();
                    if (ventaDAO.buscarPorId(id) != null) throw new VentaYaRegistradaException(id);
                    Venta v = leerVentaConId(id);
                    if (clienteDAO.buscarPorDni(v.getId_cliente()) == null)
                        throw new ClienteNoEncontradoException(v.getId_cliente());
                    if (empleadoDAO.buscarPorId(v.getId_empleado()) == null)
                        throw new EmpleadoNoEncontradoException(v.getId_empleado());
                    boolean ok = ventaDAO.insertar(v);
                    System.out.println(ok ? "Venta insertada." : "No se pudo insertar.");
                }

                case 4 -> {
                    System.out.print("ID de la venta a actualizar: ");
                    int id = leerInt();
                    if (ventaDAO.buscarPorId(id) == null) throw new VentaNoEncontradoException(id);
                    Venta v = leerVentaConId(id);
                    if (clienteDAO.buscarPorDni(v.getId_cliente()) == null)
                        throw new ClienteNoEncontradoException(v.getId_cliente());
                    if (empleadoDAO.buscarPorId(v.getId_empleado()) == null)
                        throw new EmpleadoNoEncontradoException(v.getId_empleado());
                    boolean ok = ventaDAO.actualizar(v);
                    System.out.println(ok ? "Venta actualizada." : "No se pudo actualizar.");
                }

                
                case 5 -> {
                    System.out.print("ID a eliminar: ");
                    int id = leerInt();
                    if (ventaDAO.buscarPorId(id) == null) throw new VentaNoEncontradoException(id);
                    boolean ok = ventaDAO.eliminar(id);
                    System.out.println(ok ? "Venta eliminada." : "No se pudo eliminar.");
                }

                
                case 6 -> {
                    System.out.print("DNI del cliente: ");
                    String dni = sc.nextLine().trim();
                    if (clienteDAO.buscarPorDni(dni) == null) throw new ClienteNoEncontradoException(dni);
                    List<Venta> lista = ventaDAO.listarPorCliente(dni);
                    if (lista.isEmpty()) System.out.println("El cliente no tiene ventas.");
                    else lista.forEach(System.out::println);
                }

                
                case 7 -> {
                    System.out.print("ID del empleado: ");
                    int idEmp = leerInt();
                    if (empleadoDAO.buscarPorId(idEmp) == null) throw new EmpleadoNoEncontradoException(idEmp);
                    List<Venta> lista = ventaDAO.listarPorEmpleado(idEmp);
                    if (lista.isEmpty()) System.out.println("El empleado no tiene ventas.");
                    else lista.forEach(System.out::println);
                }
                case 0 -> { }
                default -> System.out.println("Opción no válida.");
            }
        
        } catch (VentaNoEncontradoException | VentaYaRegistradaException e) {
            System.err.println(e.getMessage());
        } catch (ClienteNoEncontradoException e) {
            System.err.println(e.getMessage());
        } catch (EmpleadoNoEncontradoException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

    
    private static void menuDetalles() {
        System.out.println("\n── Detalles de venta ───────────");
        System.out.println("  1. Listar por venta");
        System.out.println("  2. Listar por producto");
        System.out.println("  3. Buscar línea concreta");
        System.out.println("  4. Insertar línea");
        System.out.println("  5. Actualizar línea");
        System.out.println("  6. Eliminar línea");
        System.out.println("  0. Volver");
        System.out.print("Opción: ");

        try {
            switch (leerInt()) {

               
                case 1 -> {
                    System.out.print("ID de la venta: ");
                    int idVenta = leerInt();
                    if (ventaDAO.buscarPorId(idVenta) == null) throw new VentaNoEncontradoException(idVenta);
                    List<Detalle_venta> lista = detalleVentaDAO.listarPorVenta(idVenta);
                    if (lista.isEmpty()) System.out.println("La venta no tiene líneas de detalle.");
                    else lista.forEach(System.out::println);
                }

                
                case 2 -> {
                    System.out.print("ID del producto: ");
                    String idProd = sc.nextLine().trim();
                    if (productoDAO.buscarPorId(idProd) == null) throw new ProductoNoEncontradoException(idProd);
                    List<Detalle_venta> lista = detalleVentaDAO.listarPorProducto(idProd);
                    if (lista.isEmpty()) System.out.println("El producto no aparece en ninguna venta.");
                    else lista.forEach(System.out::println);
                }

                
                case 3 -> {
                    System.out.print("ID de la venta: "); int idVenta = leerInt();
                    System.out.print("ID del producto: "); String idProd = sc.nextLine().trim();
                    Detalle_venta d = detalleVentaDAO.buscarPorPk(idVenta, idProd);
                    System.out.println(d != null ? d : "Línea no encontrada.");
                }

                
                case 4 -> {
                    System.out.print("ID de la venta: ");
                    int idVenta = leerInt();
                    if (ventaDAO.buscarPorId(idVenta) == null) throw new VentaNoEncontradoException(idVenta);
                    System.out.print("ID del producto: "); String idProd = sc.nextLine().trim();
                    System.out.print("Cantidad: "); int cant = leerInt();
                    System.out.print("Precio unitario: "); double precio = leerDouble();
                    boolean ok = detalleVentaDAO.insertar(new Detalle_venta(idVenta, idProd, cant, precio));
                    System.out.println(ok ? "Línea insertada." : "No se pudo insertar.");
                }

             
                case 5 -> {
                    System.out.print("ID de la venta: "); int idVenta = leerInt();
                    System.out.print("ID del producto: "); String idProd  = sc.nextLine().trim();
                    System.out.print("Nueva cantidad: "); int cant = leerInt();
                    System.out.print("Nuevo precio unitario: "); double precio  = leerDouble();
                    boolean ok = detalleVentaDAO.actualizar(new Detalle_venta(idVenta, idProd, cant, precio));
                    System.out.println(ok ? "Línea actualizada." : "Línea no encontrada.");
                }

                case 6 -> {
                    System.out.print("ID de la venta: "); int idVenta = leerInt();
                    System.out.print("ID del producto: "); String idProd  = sc.nextLine().trim();
                    boolean ok = detalleVentaDAO.eliminar(idVenta, idProd);
                    System.out.println(ok ? "Línea eliminada." : "Línea no encontrada.");
                }
                case 0 -> { }
                default -> System.out.println("Opción no válida.");
            }
        } catch (VentaNoEncontradoException | ProductoNoEncontradoException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

    // HELPERS DE LECTURA
    // Métodos auxiliares que solicitan los datos al usuario por consola y
    // construyen el DTO correspondiente. Se separan del flujo principal para
    // mantener los submenús limpios y no repetir código.

    private static Cliente leerCliente() {
        System.out.print("DNI: ");
        String dni = sc.nextLine().trim();
        return leerClienteConDni(dni);
    }

    private static Cliente leerClienteConDni(String dni) {
        System.out.print("Nombre: "); String nom = sc.nextLine().trim();
        System.out.print("Apellido 1: "); String ap1 = sc.nextLine().trim();
        System.out.print("Apellido 2 (Enter para omitir): "); String ap2 = sc.nextLine().trim();
        System.out.print("Teléfono: "); int tel = leerInt();
        System.out.print("Email (Enter para omitir): "); String email = sc.nextLine().trim();
        System.out.print("Dirección: "); String dir = sc.nextLine().trim();
        return new Cliente(dni, nom, ap1, ap2.isEmpty() ? null : ap2, tel, email.isEmpty() ? null : email, dir);
    }

    private static Empleado leerEmpleado() {
        System.out.print("ID: ");
        int id = leerInt();
        return leerEmpleadoConId(id);
    }

    private static Empleado leerEmpleadoConId(int id) {
        System.out.print("Nombre: "); String nom = sc.nextLine().trim();
        System.out.print("Apellido 1: "); String ap1 = sc.nextLine().trim();
        System.out.print("Apellido 2 (Enter para omitir): "); String ap2 = sc.nextLine().trim();
        System.out.print("Fecha contratación (yyyy-MM-dd): "); Date fecha = Date.valueOf(sc.nextLine().trim());
        System.out.print("Cargo: "); String cargo = sc.nextLine().trim();
        System.out.print("Años de experiencia: "); int anios = leerInt();
        return new Empleado(id, nom, ap1, ap2.isEmpty() ? null : ap2, fecha, cargo, anios);
    }

  
    private static Producto leerProductoConId(String id) {
        System.out.print("Nombre: "); String nom = sc.nextLine().trim();
        System.out.print("Plataforma: "); String plat = sc.nextLine().trim();
        System.out.print("ID categoría: "); int idCat = leerInt();
        System.out.print("Precio: "); double precio = leerDouble();
        System.out.print("Stock: "); int stock = leerInt();
        System.out.print("Fecha lanzamiento (yyyy-MM-dd): "); Date fecha = Date.valueOf(sc.nextLine().trim());
        return new Producto(id, nom, plat, idCat, precio, stock, fecha);
    }

    private static Venta leerVenta() {
        System.out.print("ID de la venta: ");
        int id = leerInt();
        return leerVentaConId(id);
    }

    
    private static Venta leerVentaConId(int id) {
        System.out.print("Fecha compra (yyyy-MM-dd): "); Date fecha = Date.valueOf(sc.nextLine().trim());
        System.out.print("DNI del cliente: "); String idCliente = sc.nextLine().trim();
        System.out.print("ID del empleado: "); int idEmpleado = leerInt();
        System.out.print("Importe: "); double importe = leerDouble();
        return new Venta(id, fecha, idCliente, idEmpleado, importe);
    }

    
    private static int leerInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Introduce un número entero: ");
            }
        }
    }

    private static double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Introduce un número decimal: ");
            }
        }
    }
}