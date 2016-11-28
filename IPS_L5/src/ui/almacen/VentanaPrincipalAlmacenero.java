package ui.almacen;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import model.Almacenero;
import model.OrdenTrabajo;
import model.Transportista;
import ui.almacen.almacenero.PanelOpcionesAlmacenero;
import ui.almacen.empaquetado.PanelEmpaquetadoProductos;
import ui.almacen.envios.PanelEnvioPaquetes;
import ui.almacen.envios.PanelSeleccionTransportista;
import ui.almacen.myTypes.ventanaMensaje.MostrarMensaje;
import ui.almacen.recogida.PanelRecogidaProductos;
import ui.almacen.recogida.PanelRegistroIncidencias;
import ui.almacen.recogida.PanelSeleccionPedido;
import ui.almacen.retomarOT.PanelOrdenesTrabajo;

public class VentanaPrincipalAlmacenero extends JFrame {

	private static final long serialVersionUID = -45321105L;

	private JPanel contentPane;

	private final MostrarMensaje message = new MostrarMensaje(this);

	private Almacenero almacenero; // Almacenero que est� usando la aplicaci�n
	private OrdenTrabajo ordenTrabajo; // Orden de Trabajo que se procesa

	private Transportista transportista; // Transportista que realizar� un env�o

	// ===========================================================
	// Paneles que est�n en el panel principal de la aplicaci�n
	// ===========================================================

	// Paneles iniciales

	private PanelOpcionesAlmacenero panelOpcionesAlmacenero;

	// Paneles Recogida de productos

	private PanelSeleccionPedido panelSelecionPedido;
	private PanelRecogidaProductos panelRecogidaProductos;
	private PanelRegistroIncidencias panelRegistroIncidencias;

	// Paneles Empaquetado de productos

	private PanelOrdenesTrabajo panelOrdenesTrabajoRetomar;
	private PanelEmpaquetadoProductos panelEmpaquetadoProductos;

	// Paneles de envio de paquetes

	private PanelSeleccionTransportista panelSeleccionTransportista;
	private PanelEnvioPaquetes panelEnvioPaquetes;

	/**
	 * Ejecuta la aplicaci�n
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
		setTitle("Gesti�n del almac�n");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		setResizable(false);

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

		// ============================================
		// ===== Panel de opciones del almacenero =====
		// ============================================

		panelOpcionesAlmacenero = new PanelOpcionesAlmacenero();
		panelOpcionesAlmacenero.setVentanaPrincipal(this);

		GridBagLayout gridBagLayout = (GridBagLayout) panelOpcionesAlmacenero.getLayout();

		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gridBagLayout.rowHeights = new int[] { 20, 40, 20, 50, 50, 50, 50, 50, 80 };

		contentPane.add(panelOpcionesAlmacenero, "panelOpcionesAlmacenero");

		// =========================================================
		// ===== Recogida de productos en una Orden de Trabajo =====
		// =========================================================

		panelSelecionPedido = new PanelSeleccionPedido();
		panelSelecionPedido.setVentanaPrincipal(this);

		panelOrdenesTrabajoRetomar = new PanelOrdenesTrabajo();
		panelOrdenesTrabajoRetomar.setVentanaPrincipal(this);

		panelRecogidaProductos = new PanelRecogidaProductos();
		panelRecogidaProductos.setVentanaPrincipal(this);

		panelRegistroIncidencias = new PanelRegistroIncidencias();
		panelRegistroIncidencias.setVentanaPrincipal(this);

		contentPane.add(panelSelecionPedido, "panelSelecionPedido");
		contentPane.add(panelOrdenesTrabajoRetomar, "panelOrdenesTrabajoRetomar");
		contentPane.add(panelRecogidaProductos, "panelRecogidaProductos");
		contentPane.add(panelRegistroIncidencias, "panelRegistroIncidencias");

		// ============================================================
		// ===== Empaquetado de productos en una Orden de Trabajo =====
		// ============================================================

		panelEmpaquetadoProductos = new PanelEmpaquetadoProductos();
		panelEmpaquetadoProductos.setVentanaPrincipal(this);

		contentPane.add(panelEmpaquetadoProductos, "panelEmpaquetadoProductos");

		// ========================================================
		// ==== Seleccion de transportista y envio de paquetes ====
		// ========================================================

		panelSeleccionTransportista = new PanelSeleccionTransportista();
		panelSeleccionTransportista.setVentanaPrincipal(this);

		panelEnvioPaquetes = new PanelEnvioPaquetes();
		panelEnvioPaquetes.setVentanaPrincipal(this);

		contentPane.add(panelSeleccionTransportista, "panelSeleccionTransportista");
		contentPane.add(panelEnvioPaquetes, "panelEnvioPaquetes");
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
		this.almacenero = null;
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

			// (2) Se muestra el panel de selecci�n de pedidos
			((CardLayout) contentPane.getLayout()).show(contentPane, "panelSelecionPedido");

		} catch (BusinessException e) {
			gestionarErrorConexion(e);
		}
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
		boolean hayOT = panelOrdenesTrabajoRetomar.inicializarDatos();

		// -------------------------------------
		// Seg�n haya o no �rdenes de trabajo
		// -------------------------------------

		if (hayOT) {
			// (2) Se muestra el panel de selecci�n de pedidos
			((CardLayout) contentPane.getLayout()).show(contentPane, "panelOrdenesTrabajoRetomar");
		}

		else {
			// (2) Se muestra un mensaje
			getMessage().info("Info", "No hay �rdenes de trabajo disponibles");
		}
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

	/**
	 * Mientras el almacenero est� recogiendo productos de una orden de trabajo
	 * es posible que ocurran incidencias.
	 * 
	 */
	public void mostrarPanelIncidencias() {
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelRegistroIncidencias");
	}

	/**
	 * Despu�s de registrar una incidencia, el almacenero puede volver a
	 * 
	 */
	public void volverPanelRecogidaProductos() {
		try {
			panelRecogidaProductos.comprobarSihuboIncidencias();

			((CardLayout) contentPane.getLayout()).show(contentPane, "panelRecogidaProductos");
		} catch (BusinessException e) {
			gestionarErrorConexion(e);
		}
	}

	// ============================================================
	// ===== Empaquetado de productos en una Orden de Trabajo =====
	// ============================================================

	/**
	 * Cuando se quiera pasar a la parte de empaquetado hay que ejecutar este
	 * m�todo, que inicializa las variables necesarias y se encarga de realizar
	 * el cambio.
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void mostrarPanelEmpaquetadoProductos() throws BusinessException {
		// (1) Inicializar
		panelEmpaquetadoProductos.inicializarDatos();

		// (2) Mostrar
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelEmpaquetadoProductos");
	}

	// ==========================================================
	// ===== Seleccion de transportista y envio de paquetes =====
	// ==========================================================

	public void mostrarPanelSeleccionTransportista() throws BusinessException {
		// (1) Inicializar
		panelSeleccionTransportista.inicializarDatos();

		// (2) Mostrar
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelSeleccionTransportista");
	}

	public void mostrarPanelEnvioPaquetes() throws BusinessException {
		// (1) Inicializar
		panelEnvioPaquetes.inicializarDatos();

		// (2) Mostrar
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelEnvioPaquetes");
	}

	// =======================================================================
	// ===== Ha habido un error mientras el almacenero estaba trabajando =====
	// =======================================================================

	/**
	 * Pasa a la pantalla de opciones del almacenero y le indica que hubo un
	 * error de conexi�n, lo que implica que no podr� continuar con la tarea que
	 * estuvo realizando. </br>
	 * </br>
	 * No tiene sentido que el almacenero trabaje sin estar conectado a la base
	 * de datos, ya que los datos de lo que est� haciendo no podr�n ser
	 * guardados ni validados.
	 * 
	 * @param e
	 *            excepcion que indica la causa del error
	 * 
	 */
	public void gestionarErrorConexion(BusinessException e) {
		message.error("error", e.getMessage());
	}

	// ================================
	// ==== Datos de la aplicaci�n ====
	// ================================

	public Almacenero getAlmacenero() {
		return almacenero;
	}

	public MostrarMensaje getMessage() {
		return message;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public Transportista getTransportista() {
		return transportista;
	}

	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}

}