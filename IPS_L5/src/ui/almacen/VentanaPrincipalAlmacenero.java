package ui.almacen;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import model.Almacenero;
import model.OrdenTrabajo;

import ui.almacen.almacenero.PanelLoginAlmacenero;
import ui.almacen.almacenero.PanelOpcionesAlmacenero;
import ui.almacen.empaquetado.PanelEmpaquetadoProductos;
import ui.almacen.empaquetado.PanelOrdenesTrabajoEmpaquetar;
import ui.almacen.recogida.PanelRecogidaProductos;
import ui.almacen.recogida.PanelRetomarOrdenTrabajo;
import ui.almacen.recogida.PanelSeleccionPedido;

public class VentanaPrincipalAlmacenero extends JFrame {

	private static final long serialVersionUID = -45321105L;

	private JPanel contentPane;

	private Almacenero almacenero; // Almacenero que está usando la aplicación
	private OrdenTrabajo ordenTrabajo; // Orden de Trabajo que se procesa

	// ===========================================================
	// Paneles que están en el panel principal de la aplicación
	// ===========================================================

	// Paneles iniciales

	private PanelLoginAlmacenero panelLoginAlmacenero;
	private PanelOpcionesAlmacenero panelOpcionesAlmacenero;

	// Paneles Recogida de productos

	private PanelRetomarOrdenTrabajo panelOrdenesTrabajoRetomar;
	private PanelSeleccionPedido panelSelecionPedido;
	private PanelRecogidaProductos panelRecogidaProductos;

	// Paneles Empaquetado de productos

	private PanelOrdenesTrabajoEmpaquetar panelOrdenesTrabajoEmpaquetar;
	private PanelEmpaquetadoProductos panelEmpaquetadoProductos;

	/**
	 * Ejecuta la aplicación
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalAlmacenero frame = new VentanaPrincipalAlmacenero();

					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la ventana, y la inicializa
	 * 
	 * @throws BusinessException
	 * 
	 */
	public VentanaPrincipalAlmacenero() throws BusinessException {
		setTitle("Gestión del almacén");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		añadirPaneles();
	}

	/**
	 * Añadir pantallas de la aplicación del almacenero
	 * 
	 * @throws BusinessException
	 * 
	 */
	private void añadirPaneles() throws BusinessException {
		// ===================================
		// ===== Inicio de la aplicación =====
		// ===================================

		panelLoginAlmacenero = new PanelLoginAlmacenero();
		panelLoginAlmacenero.setVentanaPrincipal(this);

		panelOpcionesAlmacenero = new PanelOpcionesAlmacenero();
		panelOpcionesAlmacenero.setVentanaPrincipal(this);

		contentPane.add(panelLoginAlmacenero, "panelLoginAlmacenero");
		contentPane.add(panelOpcionesAlmacenero, "panelOpcionesAlmacenero");

		// =========================================================
		// ===== Recogida de productos en una Orden de Trabajo =====
		// =========================================================

		panelSelecionPedido = new PanelSeleccionPedido();
		panelSelecionPedido.setVentanaPrincipal(this);

		panelOrdenesTrabajoRetomar = new PanelRetomarOrdenTrabajo();
		panelOrdenesTrabajoRetomar.setVentanaPrincipal(this);

		panelRecogidaProductos = new PanelRecogidaProductos();
		panelRecogidaProductos.setVentanaPrincipal(this);

		contentPane.add(panelSelecionPedido, "panelSelecionPedido");
		contentPane.add(panelOrdenesTrabajoRetomar, "panelOrdenesTrabajoRetomar");
		contentPane.add(panelRecogidaProductos, "panelRecogidaProductos");

		// ============================================================
		// ===== Empaquetado de productos en una Orden de Trabajo =====
		// ============================================================

		panelOrdenesTrabajoEmpaquetar = new PanelOrdenesTrabajoEmpaquetar();
		panelOrdenesTrabajoEmpaquetar.setVentanaPrincipal(this);

		panelEmpaquetadoProductos = new PanelEmpaquetadoProductos();
		panelEmpaquetadoProductos.setVentanaPrincipal(this);

		contentPane.add(panelOrdenesTrabajoEmpaquetar, "panelOrdenesTrabajoEmpaquetar");
		contentPane.add(panelEmpaquetadoProductos, "panelEmpaquetadoProductos");
	}

	// ===================================
	// ===== Inicio de la aplicación =====
	// ===================================

	/**
	 * Después de que el almacenero hace login en el sistema se muestra las
	 * opciones de las que dispone.
	 * 
	 */
	public void login(Almacenero almacenero) {
		this.almacenero = almacenero;

		((CardLayout) contentPane.getLayout()).show(contentPane, "panelOpcionesAlmacenero");
	}

	/**
	 * Si el almacenero ya inició sesión, entonces tendrá la opción de cerrarla
	 * 
	 */
	public void logout() {
		// Volver a la pantalla de login
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelLoginAlmacenero");
	}

	/**
	 * Cuando el almacenero se registra tiene la posibilidad de solicitar al
	 * sistema que le muestre una lista de pedidos sobre los que generar una
	 * Orden de Trabajo.
	 * 
	 */
	public void volverPanelOpciones() {
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelOpcionesAlmacenero");
	}

	// =========================================================
	// ===== Recogida de productos en una Orden de Trabajo =====
	// =========================================================

	/**
	 * Cuando el almacenero se loguea tiene la posibilidad de solicitar al
	 * sistema que le muestre una lista de pedidos sobre los que generar una
	 * Orden de Trabajo.
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void mostrarPanelSeleccionPedidos() {
		try {
			// (1) Hay que cargar la lista de posibles pedidos
			panelSelecionPedido.inicializarDatos();

			// (2) Se muestra el panel de selección de pedidos
			((CardLayout) contentPane.getLayout()).show(contentPane, "panelSelecionPedido");

		} catch (BusinessException e) {
			gestionarErrorConexion();
		}
	}

	/**
	 * Cuando el almacenero se loguea tiene la posibilidad de retomar una orden
	 * de trabajo que no empezó a recoger, pero que no completó
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void mostrarPanelOrdenesTrabajoRetomar() throws BusinessException {
		// (1) Hay que cargar la lista de posibles pedidos
		panelOrdenesTrabajoRetomar.inicializarDatos();

		// (2) Se muestra el panel de selección de pedidos
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelOrdenesTrabajoRetomar");
	}

	/**
	 * Después de decidir sobre qué Orden de Trabajo trabajar, el almacenero
	 * pasa a recoger los productos de esa Orden de Trabajo
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void mostrarPanelRecogidaProductos() throws BusinessException {
		// (1) Hay que cargar la lista de posibles pedidos
		panelRecogidaProductos.inicializarDatos();

		// (2) Se muestra el panel de selección de pedidos
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelRecogidaProductos");
	}

	// ============================================================
	// ===== Empaquetado de productos en una Orden de Trabajo =====
	// ============================================================

	/**
	 * Cuando el almacenero se loguea tiene la posibilidad de retomar una orden
	 * de trabajo que no empezó a recoger, pero que no completó.
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void mostrarPanelOrdenesTrabajoEmpaquetar() throws BusinessException {
		// (1) Inicializar
		panelOrdenesTrabajoEmpaquetar.inicializarDatos();

		// (2) Mostrar
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelOrdenesTrabajoEmpaquetar");
	}

	/**
	 * Cuando se quiera pasar a la parte de empaquetado hay que ejecutar este
	 * método, que inicializa las variables necesarias y se encarga de realizar
	 * el cambio.
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void pasarEmpaquetar() throws BusinessException {
		// (1) Inicializar
		panelEmpaquetadoProductos.inicializarDatos();

		// (2) Mostrar
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelEmpaquetadoProductos");
	}

	// =======================================================================
	// ===== Ha habido un error mientras el almacenero estaba trabajando =====
	// =======================================================================

	/**
	 * Pasa a la pantalla de opciones del almacenero y le indica que hubo un
	 * error de conexión, lo que implica que no podrá continuar con la tarea que
	 * estuvo realizando. </br>
	 * </br>
	 * No tiene sentido que el almacenero trabaje sin estar conectado a la base
	 * de datos, ya que los datos de lo que esté haciendo no podrán ser
	 * guardados ni validados.
	 * 
	 */
	public void gestionarErrorConexion() {
		panelOpcionesAlmacenero.mostrarErrorConexion();
		
		volverPanelOpciones();
	}

	// ================================
	// ==== Datos de la aplicación ====
	// ================================

	public Almacenero getAlmacenero() {
		return almacenero;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
}
