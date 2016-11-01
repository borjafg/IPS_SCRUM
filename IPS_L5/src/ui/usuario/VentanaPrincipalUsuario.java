package ui.usuario;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Producto;
import model.types.MetodosPago;
import ui.usuario.logica.LogicaVentanaPrincipalUsuario;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;
import business.exception.BusinessException;
import javax.swing.ScrollPaneConstants;

public class VentanaPrincipalUsuario extends JFrame {

	private static final long serialVersionUID = -457381L;

	// Hace todas las llamadas a los métodos de la lógica
	private LogicaVentanaPrincipalUsuario logVOUser;

	// Modelos de las listas
	private DefaultListModel<Producto> modeloListaProductos;

	// Hay que pensar un tipo para poder meter en el modelo de la cesta
	private DefaultListModel<ModeloProductosPedidos> modeloListaCesta;

	///
	private JPanel contentPane;
	private JPanel panelPrincipal;
	private JPanel panelCesta;
	private JPanel panelProductos;
	private JLabel lblProductos;
	private JLabel lblCesta;
	private JPanel panelSurBotones;
	private JPanel panelNorteTitulo;
	private JScrollPane scrollPaneCesta;
	private JButton btnAceptarPedido;
	private JPanel panelCentro;
	private JScrollPane scrollPaneProductos;
	private JLabel lblTitulo;
	private JPanel panelGastosTotales;
	private JLabel lblGastoTotal;
	private JTextField textGastoTotal;
	private JPanel panelBase;
	private JPanel panelAceparPedidoNoRegistrados;
	private JPanel panelBotonesAceptarPedido;
	private JButton btnCancelarPedido;
	private JButton btnFinalizarPedido;
	private JList<ModeloProductosPedidos> listCesta;
	private JList<Producto> listProductos;
	private JPanel panelAceptarPedidoBase;
	private JPanel panelAuxAceptarPedido1;
	private JPanel panelDireccionCliente;
	private JPanel panelMetodoPagoCliente;
	private JLabel lblDireccionCliente;
	private JTextField textFieldDireccionCliente;
	private JLabel lblMetodoPagoCliente;

	private JPanel panelProductosContenedor;
	private JLabel lblNombreCliente;
	private JTextField textFieldNombreCliente;
	private JComboBox<MetodosPago> comboBox;
	private JPanel panelCesta2;
	private JPanel panelBotonQuitar;
	private JButton btnQuitar;
	private JPanel panelAñadirProductos;
	private JButton btnAñadir;
	private JTextField textFieldUnidadesProducto;

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalUsuario frame = new VentanaPrincipalUsuario();

					frame.setVisible(true); // lo ultimo
					System.out.println("pase por set visible");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public VentanaPrincipalUsuario() {
		logVOUser = new LogicaVentanaPrincipalUsuario();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 580);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelBase(), BorderLayout.CENTER);

		setTitle("Tienda");
	}

	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();

			panelPrincipal.setLayout(new BorderLayout(0, 0));
			panelPrincipal.add(getPanel_4(), BorderLayout.NORTH);
			panelPrincipal.add(getPanel_1_1(), BorderLayout.CENTER);

			panelPrincipal.add(getPanel_3(), BorderLayout.SOUTH);
		}

		return panelPrincipal;
	}

	private JPanel getPanelCesta() {
		if (panelCesta == null) {
			panelCesta = new JPanel();

			panelCesta.setLayout(new BorderLayout(0, 0));
			panelCesta.add(getLblCesta(), BorderLayout.NORTH);

			panelCesta.add(getPanelGastosTotales(), BorderLayout.SOUTH);
			panelCesta.add(getPanelCesta2(), BorderLayout.WEST);
		}

		return panelCesta;
	}

	private JPanel getPanelProductos() {
		if (panelProductos == null) {
			panelProductos = new JPanel();

			panelProductos.setLayout(new BorderLayout(0, 0));
			panelProductos.add(getLblProductos(), BorderLayout.NORTH);
			panelProductos.add(getPanelProductosContenedor(), BorderLayout.CENTER);
		}

		return panelProductos;
	}

	private JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Productos");
		}

		return lblProductos;
	}

	private JLabel getLblCesta() {
		if (lblCesta == null) {
			lblCesta = new JLabel("Cesta");
		}

		return lblCesta;
	}

	private JPanel getPanel_3() {
		if (panelSurBotones == null) {
			panelSurBotones = new JPanel();

			panelSurBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panelSurBotones.add(getBtnAceptarPedido());
		}

		return panelSurBotones;
	}

	private JPanel getPanel_4() {
		if (panelNorteTitulo == null) {
			panelNorteTitulo = new JPanel();
			panelNorteTitulo.add(getLblTitulo());
		}

		return panelNorteTitulo;
	}

	private JScrollPane getScrollPaneCesta() {
		if (scrollPaneCesta == null) {
			scrollPaneCesta = new JScrollPane(getListCesta());

			scrollPaneCesta.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneCesta.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		}

		return scrollPaneCesta;
	}

	/**
	 * El action Performed, nos permite pasar a la pestaña para aceptar el
	 * pedido El boton aceptar, se pondra enbled cuando el carrito tenga al
	 * menos un elemento
	 * 
	 * @return
	 * 
	 */
	private JButton getBtnAceptarPedido() {
		if (btnAceptarPedido == null) {
			btnAceptarPedido = new JButton("Comprar");

			btnAceptarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Mientras tanto, para probar todo
					// poner un if para ver si la cesta esta vacia
					if (modeloListaCesta.isEmpty()) {
						JOptionPane.showMessageDialog(getBtnAceptarPedido(),
								"Para confirmar pedido, la cesta tiene que tener por lo menos un artículo", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						// Aqui luego ira si se a registrado un usuario
						((CardLayout) panelBase.getLayout()).show(panelBase, "panelAceptarPedido");
					}
				}
			});

			btnAceptarPedido.setHorizontalAlignment(SwingConstants.RIGHT);
		}

		return btnAceptarPedido;
	}

	private JPanel getPanel_1_1() {
		if (panelCentro == null) {
			panelCentro = new JPanel();

			panelCentro.setLayout(new BorderLayout(0, 0));
			panelCentro.add(getPanelCesta(), BorderLayout.EAST);
			panelCentro.add(getPanelProductos(), BorderLayout.WEST);
		}

		return panelCentro;
	}

	private JScrollPane getScrollPaneProductos() {// meter card layout
		if (scrollPaneProductos == null) {
			scrollPaneProductos = new JScrollPane(getListProductos());

			scrollPaneProductos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneProductos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		}

		return scrollPaneProductos;
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Tienda de compras");
		}

		return lblTitulo;
	}

	private JPanel getPanelGastosTotales() {
		if (panelGastosTotales == null) {
			panelGastosTotales = new JPanel();

			panelGastosTotales.add(getLblGastoTotal());
			panelGastosTotales.add(getTextGastoTotal());
		}

		return panelGastosTotales;
	}

	private JLabel getLblGastoTotal() {
		if (lblGastoTotal == null) {
			lblGastoTotal = new JLabel("Gasto :");
		}

		return lblGastoTotal;
	}

	private JTextField getTextGastoTotal() {
		if (textGastoTotal == null) {
			textGastoTotal = new JTextField();

			textGastoTotal.setHorizontalAlignment(SwingConstants.RIGHT);
			textGastoTotal.setText("0.0");
			textGastoTotal.setEditable(false);
			textGastoTotal.setColumns(10);
		}

		return textGastoTotal;
	}

	/**
	 * Este es el panel sobre el que se colocan los paneles de la tienda y el de
	 * aceptar pedido
	 * 
	 * @return
	 */
	private JPanel getPanelBase() {
		if (panelBase == null) {
			panelBase = new JPanel();

			panelBase.setLayout(new CardLayout(0, 0));

			// se añade el panel de la tienda al card layout
			panelBase.add(getPanelPrincipal(), "panelPrincipal");

			// sen añade el panel de aceptar pedido al card layout
			panelBase.add(getPanelAceparPedidoNoRegistrados(), "panelAceptarPedido");
		}

		return panelBase;
	}

	private JPanel getPanelAceparPedidoNoRegistrados() {
		if (panelAceparPedidoNoRegistrados == null) {
			panelAceparPedidoNoRegistrados = new JPanel();

			panelAceparPedidoNoRegistrados.setLayout(new BorderLayout(0, 0));

			panelAceparPedidoNoRegistrados.add(getPanelBotonesAceptarPedido(), BorderLayout.SOUTH);
			panelAceparPedidoNoRegistrados.add(getPanelAceptarPedidoBase(), BorderLayout.CENTER);
		}

		return panelAceparPedidoNoRegistrados;
	}

	private JPanel getPanelBotonesAceptarPedido() {
		if (panelBotonesAceptarPedido == null) {
			panelBotonesAceptarPedido = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelBotonesAceptarPedido.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);

			panelBotonesAceptarPedido.add(getBtnCancelarPedido());
			panelBotonesAceptarPedido.add(getBtnFinalizarPedido());
		}

		return panelBotonesAceptarPedido;
	}

	/**
	 * 
	 * El action performed, nos devuelve a la pestaña de la tienda.
	 * 
	 * @return JButton
	 * 
	 */
	private JButton getBtnCancelarPedido() {
		if (btnCancelarPedido == null) {
			btnCancelarPedido = new JButton("Cancelar");

			btnCancelarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout) panelBase.getLayout()).show(panelBase, "panelPrincipal");
				}
			});
		}

		return btnCancelarPedido;
	}

	private JButton getBtnFinalizarPedido() {
		if (btnFinalizarPedido == null) {
			btnFinalizarPedido = new JButton("Aceptar");

			btnFinalizarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// validar que los campos sean correctos
					String textoDireccion = getTextFieldDireccionCliente().getText();
					// MetodosPago comboMetodo = (MetodosPago)
					// getComboBox().getSelectedItem();
					String textoNombre = getTextFieldNombreCliente().getText();
					if (textoDireccion.equals("") || textoNombre.equals("")) {
						JOptionPane.showMessageDialog(getBtnFinalizarPedido(), "Rellene los campos indicados", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						// subir a la base de datos y reiniciar la aplicacion;
						try {
							logVOUser.generarTodo(textoDireccion, textoNombre);
						} catch (BusinessException e1) {
							System.err.println(e1.getMessage());
						}
						modeloListaCesta = logVOUser.resetear();
						getListCesta().setModel(modeloListaCesta);
						((CardLayout) panelBase.getLayout()).show(panelBase, "panelPrincipal");
					}
				}
			});
		}

		return btnFinalizarPedido;
	}

	private JList<ModeloProductosPedidos> getListCesta() {
		if (listCesta == null) {
			modeloListaCesta = new DefaultListModel<ModeloProductosPedidos>();

			listCesta = new JList<ModeloProductosPedidos>(modeloListaCesta);

			listCesta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listCesta.putClientProperty("List.isFileList", Boolean.TRUE);
		}

		return listCesta;
	}

	private JList<Producto> getListProductos() {
		if (listProductos == null) {
			modeloListaProductos = logVOUser.getModeloListaProductos();

			listProductos = new JList<Producto>(modeloListaProductos);

			listProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listProductos.putClientProperty("List.isFileList", Boolean.TRUE);
		}

		return listProductos;
	}

	private JPanel getPanelAceptarPedidoBase() {
		if (panelAceptarPedidoBase == null) {
			panelAceptarPedidoBase = new JPanel();

			panelAceptarPedidoBase.setLayout(new GridLayout(5, 0, 0, 0));

			panelAceptarPedidoBase.add(getPanelAuxAceptarPedido1());
			panelAceptarPedidoBase.add(getPanelDireccionCliente());
			panelAceptarPedidoBase.add(getPanelMetodoPagoCliente());
		}

		return panelAceptarPedidoBase;
	}

	private JPanel getPanelAuxAceptarPedido1() {
		if (panelAuxAceptarPedido1 == null) {
			panelAuxAceptarPedido1 = new JPanel();

			panelAuxAceptarPedido1.add(getLblNombreCliente());
			panelAuxAceptarPedido1.add(getTextFieldNombreCliente());
		}

		return panelAuxAceptarPedido1;
	}

	private JPanel getPanelDireccionCliente() {
		if (panelDireccionCliente == null) {
			panelDireccionCliente = new JPanel();

			panelDireccionCliente.add(getLblDireccionCliente());
			panelDireccionCliente.add(getTextFieldDireccionCliente());
		}

		return panelDireccionCliente;
	}

	private JPanel getPanelMetodoPagoCliente() {
		if (panelMetodoPagoCliente == null) {
			panelMetodoPagoCliente = new JPanel();
			panelMetodoPagoCliente.add(getLblMetodoPagoCliente());
			panelMetodoPagoCliente.add(getComboBox());
		}

		return panelMetodoPagoCliente;
	}

	private JLabel getLblDireccionCliente() {
		if (lblDireccionCliente == null) {
			lblDireccionCliente = new JLabel("Direccion de envio : ");
		}

		return lblDireccionCliente;
	}

	private JTextField getTextFieldDireccionCliente() {
		if (textFieldDireccionCliente == null) {
			textFieldDireccionCliente = new JTextField();
			textFieldDireccionCliente.setColumns(10);
		}

		return textFieldDireccionCliente;
	}

	private JLabel getLblMetodoPagoCliente() {
		if (lblMetodoPagoCliente == null) {
			lblMetodoPagoCliente = new JLabel("M\u00E9todo de pago : ");
		}

		return lblMetodoPagoCliente;
	}

	private JPanel getPanelProductosContenedor() {
		if (panelProductosContenedor == null) {
			panelProductosContenedor = new JPanel();

			panelProductosContenedor.setLayout(new BorderLayout(0, 0));
			panelProductosContenedor.add(getScrollPaneProductos());
			panelProductosContenedor.add(getPanelAñadirProductos(), BorderLayout.SOUTH);
		}

		return panelProductosContenedor;
	}

	private JLabel getLblNombreCliente() {
		if (lblNombreCliente == null) {
			lblNombreCliente = new JLabel("Nombre :");
		}

		return lblNombreCliente;
	}

	private JTextField getTextFieldNombreCliente() {
		if (textFieldNombreCliente == null) {
			textFieldNombreCliente = new JTextField();
			textFieldNombreCliente.setColumns(10);
		}

		return textFieldNombreCliente;
	}

	private JComboBox<MetodosPago> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<MetodosPago>(MetodosPago.values());
		}

		return comboBox;
	}

	private JPanel getPanelCesta2() {
		if (panelCesta2 == null) {
			panelCesta2 = new JPanel();

			panelCesta2.setLayout(new BorderLayout(0, 0));

			panelCesta2.add(getScrollPaneCesta());
			panelCesta2.add(getPanelBotonQuitar(), BorderLayout.SOUTH);
		}

		return panelCesta2;
	}

	private JPanel getPanelBotonQuitar() {
		if (panelBotonQuitar == null) {
			panelBotonQuitar = new JPanel();
			panelBotonQuitar.add(getBtnQuitar());
		}

		return panelBotonQuitar;
	}

	private JButton getBtnQuitar() {
		if (btnQuitar == null) {
			btnQuitar = new JButton("Quitar");

			btnQuitar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ModeloProductosPedidos cestaSeleccionado = (ModeloProductosPedidos) getListCesta()
							.getSelectedValue();

					// puede que esto sea lo que funciona
					getListProductos().setSelectedValue(null, false);

					if (cestaSeleccionado == null) {
						JOptionPane.showMessageDialog(getBtnQuitar(),
								"Se tiene que seleccionar un producto de la cesta", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						modeloListaCesta = logVOUser.EliminarProducto(getListCesta().getSelectedIndex());

						getListCesta().setModel(modeloListaCesta);
						getTextGastoTotal().setText(String.valueOf(logVOUser.calcularPrecioTotal()));
						getListCesta().setSelectedValue(null, false);
					}

					getListProductos().setSelectedIndex(-1);
					getListCesta().setSelectedIndex(-1);
				}
			});
		}

		return btnQuitar;
	}

	private JPanel getPanelAñadirProductos() {
		if (panelAñadirProductos == null) {
			panelAñadirProductos = new JPanel();
			panelAñadirProductos.add(getTextFieldUnidadesProducto());
			panelAñadirProductos.add(getBtnAñadir());

		}

		return panelAñadirProductos;
	}

	private JButton getBtnAñadir() {
		if (btnAñadir == null) {
			btnAñadir = new JButton("A\u00F1adir");

			btnAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int index = getListProductos().getSelectedIndex();

					if (index == -1) {
						JOptionPane.showMessageDialog(getBtnAceptarPedido(), "Seleccione un producto", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else if (!logVOUser.isnumber(getTextFieldUnidadesProducto().getText())) {
						JOptionPane.showMessageDialog(getBtnAñadir(), "Se tiene que escribir un número entero positivo",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						ModeloProductosPedidos productopedido = new ModeloProductosPedidos(
								(Producto) getListProductos().getSelectedValue(),
								Integer.parseInt(getTextFieldUnidadesProducto().getText()));
						modeloListaCesta = logVOUser.sumarProductoACesta(productopedido, modeloListaCesta);
						getListCesta().setModel(modeloListaCesta);
						getTextGastoTotal().setText(String.valueOf(logVOUser.calcularPrecioTotal()));
						// no muy seguro
						// getListProductos().setSelectedValue(null, false);
					}

					getListProductos().setSelectedIndex(-1);
					getListCesta().setSelectedIndex(-1);
				}
			});
		}

		return btnAñadir;
	}

	private JTextField getTextFieldUnidadesProducto() {
		if (textFieldUnidadesProducto == null) {
			textFieldUnidadesProducto = new JTextField();

			textFieldUnidadesProducto.setText("1");
			textFieldUnidadesProducto.setColumns(10);
		}

		return textFieldUnidadesProducto;
	}
}
