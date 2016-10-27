package ui.almacen;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import model.OrdenTrabajo;
import ui.almacen.empaquetado.PanelEmpaquetadoProductos;

public class GestionAlmacen extends JFrame {

	private static final long serialVersionUID = -45321105L;

	private JPanel contentPane;

	// Paneles que est�n en el panel principal de la aplicaci�n
	
	private PanelEmpaquetadoProductos panelEmpaquetadoProductos;
	//private PanelListaOrdenesAlmacenero panelOrdenesTrabajo;

	/**
	 * Lanza la aplicaci�n
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 330); // El almacenero usa una tablet => Tama�o limitado
		setTitle("Aplicaci�n almacenero");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		a�adirPaneles();
	}

	/**
	 * A�adir pantallas la aplicaci�n del almacenero
	 * 
	 * @throws BusinessException
	 * 
	 */
	private void a�adirPaneles() throws BusinessException {
		panelEmpaquetadoProductos = new PanelEmpaquetadoProductos(this);
		
	//	panelOrdenesTrabajo = new PanelListaOrdenesAlmacenero();
		
		//panelOrdenesTrabajo.setMainFrame(this);
	//	panelOrdenesTrabajo.inicializarDatos();
		

	//	contentPane.add(panelOrdenesTrabajo, "panelOrdenesTrabajo");
		contentPane.add(panelEmpaquetadoProductos, "panelAsignacionProductosPaquete");
	}

	/**
	 * Cuando se quiera pasar a la parte de empaquetado hay que ejecutar este m�todo
	 * que inicializa las variables necesarias y se encarga de realizar el cambio.
	 * 
	 * @throws BusinessException 
	 * 
	 */
	public void pasarEmpaquetar(OrdenTrabajo ordenTrabajo) throws BusinessException {
		// (1) Inicializar
		panelEmpaquetadoProductos.inicializarDatos(ordenTrabajo);
		
		// (2) Mostrar
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelAsignacionProductosPaquete");
	}
}