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

public class GestionAlmacen extends JFrame {

	private static final long serialVersionUID = -45321105L;

	private JPanel contentPane;

	private Almacenero almacenero; // Almacenero que est� usando la aplicaci�n
	private OrdenTrabajo ordenTrabajo; // Orden de Trabajo que se procesa

	// ===========================================================
	// Paneles que est�n en el panel principal de la aplicaci�n
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
	 * Ejecuta la aplicaci�n
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAlmacen frame = new GestionAlmacen();
					frame.setVisible(true);
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
	public GestionAlmacen() throws BusinessException {
		setTitle("Gesti�n del almac�n");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 581);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		a�adirPaneles();
	}

	/**
	 * A�adir pantallas de la aplicaci�n del almacenero
	 * 
	 * @throws BusinessException
	 * 
	 */
	private void a�adirPaneles() throws BusinessException {
		// ===================================
		// ===== Inicio de la aplicaci�n =====
		// ===================================

		panelLoginAlmacenero = new PanelLoginAlmacenero(this);
		panelOpcionesAlmacenero = new PanelOpcionesAlmacenero(this);

		contentPane.add(panelLoginAlmacenero, "panelLoginAlmacenero");
		contentPane.add(panelOpcionesAlmacenero, "panelOpcionesAlmacenero");

		// =========================================================
		// ===== Recogida de productos en una Orden de Trabajo =====
		// =========================================================

		panelSelecionPedido = new PanelSeleccionPedido(this);
		panelOrdenesTrabajoRetomar = new PanelRetomarOrdenTrabajo(this);
		panelRecogidaProductos = new PanelRecogidaProductos(this);

		contentPane.add(panelSelecionPedido, "panelSelecionPedido");
		contentPane.add(panelOrdenesTrabajoRetomar, "panelOrdenesTrabajoRetomar");
		contentPane.add(panelRecogidaProductos, "panelRecogidaProductos");

		// ============================================================
		// ===== Empaquetado de productos en una Orden de Trabajo =====
		// ============================================================

		panelOrdenesTrabajoEmpaquetar = new PanelOrdenesTrabajoEmpaquetar(this);
		panelEmpaquetadoProductos = new PanelEmpaquetadoProductos(this);

		contentPane.add(panelEmpaquetadoProductos, "panelOrdenesTrabajoEmpaquetar");
		contentPane.add(panelEmpaquetadoProductos, "panelAsignacionProductosPaquete");
	}

	// ===================================
	// ===== Inicio de la aplicaci�n =====
	// ===================================

	/**
	 * Despu�s de que el almacenero hace login en el sistema se muestra las
	 * opciones de las que dispone.
	 * 
	 */
	public void login(Almacenero almacenero) {
		this.almacenero = almacenero;

		((CardLayout) contentPane.getLayout()).show(contentPane, "panelOpcionesAlmacenero");
	}

	/**
	 * Si el almacenero ya inici� sesi�n, entonces tendr� la opci�n de cerrarla
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
	public void mostrarPanelSeleccionPedidos() throws BusinessException {
		// (1) Hay que cargar la lista de posibles pedidos
		panelSelecionPedido.inicializarDatos();

		// (2) Se muestra el panel de selecci�n de pedidos
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelSelecionPedido");
	}

	/**
	 * Cuando el almacenero se loguea tiene la posibilidad de retomar una orden
	 * de trabajo que no empez� a recoger, pero que no complet�
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void mostrarPanelOrdenesTrabajoRetomar() throws BusinessException {
		// (1) Hay que cargar la lista de posibles pedidos
		panelOrdenesTrabajoRetomar.inicializarDatos();

		// (2) Se muestra el panel de selecci�n de pedidos
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelOrdenesTrabajoRetomar");
	}

	/**
	 * Despu�s de decidir sobre qu� Orden de Trabajo trabajar, el almacenero
	 * pasa a recoger los productos de esa Orden de Trabajo
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void mostrarPanelRecogidaProductos() throws BusinessException {
		// (1) Hay que cargar la lista de posibles pedidos
		panelRecogidaProductos.inicializarDatos();

		// (2) Se muestra el panel de selecci�n de pedidos
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelRecogidaProductos");
	}

	// ============================================================
	// ===== Empaquetado de productos en una Orden de Trabajo =====
	// ============================================================

	/**
	 * Cuando el almacenero se loguea tiene la posibilidad de retomar una orden
	 * de trabajo que no empez� a recoger, pero que no complet�.
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void mostrarOrdenesTrabajoEmpaquetar() throws BusinessException {
		// (1) Inicializar
		panelOrdenesTrabajoEmpaquetar.inicializarDatos();

		// (2) Mostrar
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelOrdenesTrabajoEmpaquetar");
	}

	/**
	 * Cuando se quiera pasar a la parte de empaquetado hay que ejecutar este
	 * m�todo, que inicializa las variables necesarias y se encarga de realizar
	 * el cambio.
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void pasarEmpaquetar() throws BusinessException {
		// (1) Inicializar
		panelEmpaquetadoProductos.inicializarDatos();

		// (2) Mostrar
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelAsignacionProductosPaquete");
	}

	// ================================
	// ==== Datos de la aplicaci�n ====
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