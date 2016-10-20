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

public class VentanaPrincipalUsuario extends JFrame {
	
	private static final long serialVersionUID = -457381L;
	
	//Logica
	private LogicaVentanaPrincipalUsuario logVOUser; //clase para realizar todas las acciones de logica
	
	////
	private DefaultListModel<Producto> modeloListaProductos;
	private DefaultListModel<ModeloProductosPedidos> modeloListaCesta;//Hay que pensar un tipo para poder meter en el modelo de la cesta
	
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
	private JPanel panelAceparPedido;
	private JPanel panelBotonesAceptarPedido;
	private JButton btnCancelarPedido;
	private JButton btnFinalizarPedido;
	private JList listCesta;
	private JList listProductos;
	private JPanel panelBotonesUnidades;
	private JPanel panelAuxiliarBotonesUnidades1;
	private JPanel panelBotonesUnidades_1;
	private JPanel panelAuxiliarBotonesUnidades3;
	private JPanel panelUnidadesDeproducto;
	private JPanel panelBotonesUnidades_2;
	private JButton btnEliminarUnidades;
	private JButton btnSumarUnidades;
	private JTextField textFieldUnidadesProducto;
	private JLabel lblUnidadesProducto;
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
	private JComboBox comboBox;
	
	
	
	//modelo de las listas

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
										
					VentanaPrincipalUsuario frame = new VentanaPrincipalUsuario();
					
					
					frame.setVisible(true);//lo ultimo
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipalUsuario() {
		logVOUser = new LogicaVentanaPrincipalUsuario();//clase que se llama para realizar todas las acciones que no tengan que ver con la propia interfaz
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
			panelPrincipal.add(getPanel_3(), BorderLayout.SOUTH);
			panelPrincipal.add(getPanel_4(), BorderLayout.NORTH);
			panelPrincipal.add(getPanel_1_1(), BorderLayout.CENTER);
		}
		return panelPrincipal;
	}
	private JPanel getPanelCesta() {
		if (panelCesta == null) {
			panelCesta = new JPanel();
			panelCesta.setLayout(new BorderLayout(0, 0));
			panelCesta.add(getLblCesta(), BorderLayout.NORTH);
			panelCesta.add(getScrollPaneCesta(), BorderLayout.CENTER);

			panelCesta.add(getPanelGastosTotales(), BorderLayout.SOUTH);
			
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
			scrollPaneCesta = new JScrollPane();
			scrollPaneCesta.setViewportView(getListCesta());
		}
		return scrollPaneCesta;
	}
	/**
	 * El action Performed, nos permite pasar a la pestaña para aceptar el pedido
	 * El boton aceptar, se pondra enbled cuando el carrito tenga al menos un elemento
	 * 
	 * @return
	 */
	private JButton getBtnAceptarPedido() {
		if (btnAceptarPedido == null) {
			btnAceptarPedido = new JButton("Aceptar");
			
			btnAceptarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//mientras tanto, para probar todo
					//poner un if paraver si la cesta esta vacia
					if(modeloListaCesta.isEmpty()){
						JOptionPane.showMessageDialog(getBtnAceptarPedido(), "Para confirmar pedido, la cesta tiene que tener por lo menos un artículo", "Error", JOptionPane.ERROR_MESSAGE);
					}else{
					((CardLayout)panelBase.getLayout()).show(panelBase, "panelAceptarPedido");
					}}
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
			panelCentro.add(getPanelBotonesUnidades(), BorderLayout.CENTER);
		}
		return panelCentro;
	}
	private JScrollPane getScrollPaneProductos() {
		if (scrollPaneProductos == null) {
			scrollPaneProductos = new JScrollPane();
			scrollPaneProductos.setViewportView(getListProductos());
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
	 * Este es el panel sobre el que se colocan los paneles de la tienda y el de aceptar pedido
	 * 
	 * @return
	 */
	private JPanel getPanelBase() {
		if (panelBase == null) {
			panelBase = new JPanel();
			panelBase.setLayout(new CardLayout(0, 0));
			panelBase.add(getPanelPrincipal(), "panelPrincipal");//se añade el panel de la tienda al card layout
			panelBase.add(getPanelAceparPedido(), "panelAceptarPedido");//se añade el panel de aceptar pedido al card layout
		}
		return panelBase;
	}
	private JPanel getPanelAceparPedido() {
		if (panelAceparPedido == null) {
			panelAceparPedido = new JPanel();
			panelAceparPedido.setLayout(new BorderLayout(0, 0));
			panelAceparPedido.add(getPanelBotonesAceptarPedido(), BorderLayout.SOUTH);
			panelAceparPedido.add(getPanelAceptarPedidoBase(), BorderLayout.CENTER);
		}
		return panelAceparPedido;
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
	 */
	private JButton getBtnCancelarPedido() {
		if (btnCancelarPedido == null) {
			btnCancelarPedido = new JButton("Cancelar");
			btnCancelarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout)panelBase.getLayout()).show(panelBase, "panelPrincipal");
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
					//validar que los campos sean correctos
					String textoDireccion =getTextFieldDireccionCliente().getText();
					MetodosPago combometodo = (MetodosPago) getComboBox().getSelectedItem();
					String textoNombre = getTextFieldNombreCliente().getText();
					if(textoDireccion.equals("")||textoNombre.equals("")){
						JOptionPane.showMessageDialog(getBtnFinalizarPedido(), "Rellene los campos indicados", "Error", JOptionPane.ERROR_MESSAGE);
					}else{
						//subir a la base de datos y reiniciar la aplicacion;
						try {
							logVOUser.generarTodo(textoDireccion,combometodo,textoNombre);
						} catch (BusinessException e1) {
							System.err.println(e1.getMessage());
						}
						modeloListaCesta =logVOUser.resetear();
						getListCesta().setModel(modeloListaCesta);
						((CardLayout)panelBase.getLayout()).show(panelBase, "panelPrincipal");
						
					}
					
					
					
				}
			});
		}
		return btnFinalizarPedido;
	}
	private JList getListCesta() {
		if (listCesta == null) {
			listCesta = new JList();
			listCesta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			modeloListaCesta = new DefaultListModel<ModeloProductosPedidos>();
			listCesta.setModel(modeloListaCesta);
		}
		return listCesta;
	}
	private JList getListProductos() {
		if (listProductos == null) {
			listProductos = new JList();
			listProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			modeloListaProductos = logVOUser.getModeloListaProductos();
			listProductos.setModel(modeloListaProductos);
			
			
			
		}
		return listProductos;
	}
	private JPanel getPanelBotonesUnidades() {
		if (panelBotonesUnidades == null) {
			panelBotonesUnidades = new JPanel();
			panelBotonesUnidades.setLayout(new GridLayout(3, 0, 0, 0));
			panelBotonesUnidades.add(getPanelAuxiliarBotonesUnidades1());
			panelBotonesUnidades.add(getPanelBotonesUnidades_1());
		}
		return panelBotonesUnidades;
	}
	private JPanel getPanelAuxiliarBotonesUnidades1() {
		if (panelAuxiliarBotonesUnidades1 == null) {
			panelAuxiliarBotonesUnidades1 = new JPanel();
		}
		return panelAuxiliarBotonesUnidades1;
	}
	private JPanel getPanelBotonesUnidades_1() {
		if (panelBotonesUnidades_1 == null) {
			panelBotonesUnidades_1 = new JPanel();
			panelBotonesUnidades_1.setLayout(new GridLayout(3, 0, 0, 0));
			panelBotonesUnidades_1.add(getPanelAuxiliarBotonesUnidades3());
			panelBotonesUnidades_1.add(getPanelUnidadesDeproducto());
			panelBotonesUnidades_1.add(getPanelBotonesUnidades_2());
		}
		return panelBotonesUnidades_1;
	}
	private JPanel getPanelAuxiliarBotonesUnidades3() {
		if (panelAuxiliarBotonesUnidades3 == null) {
			panelAuxiliarBotonesUnidades3 = new JPanel();
		}
		return panelAuxiliarBotonesUnidades3;
	}
	private JPanel getPanelUnidadesDeproducto() {
		if (panelUnidadesDeproducto == null) {
			panelUnidadesDeproducto = new JPanel();
			panelUnidadesDeproducto.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelUnidadesDeproducto.add(getLblUnidadesProducto());
			panelUnidadesDeproducto.add(getTextFieldUnidadesProducto());
		}
		return panelUnidadesDeproducto;
	}
	private JPanel getPanelBotonesUnidades_2() {
		if (panelBotonesUnidades_2 == null) {
			panelBotonesUnidades_2 = new JPanel();
			panelBotonesUnidades_2.add(getBtnSumarUnidades());
			panelBotonesUnidades_2.add(getBtnEliminarUnidades());
		}
		return panelBotonesUnidades_2;
	}
	private JButton getBtnEliminarUnidades() {
		if (btnEliminarUnidades == null) {
			btnEliminarUnidades = new JButton("Quitar");
			btnEliminarUnidades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getListProductos().setSelectedIndex(-1);
					ModeloProductosPedidos cestaSeleccionado = (ModeloProductosPedidos) getListCesta().getSelectedValue();
					getListProductos().setSelectedValue(null, false);
					if(cestaSeleccionado == null){
						JOptionPane.showMessageDialog(getBtnEliminarUnidades(), "Se tiene que seleccionar un producto de la cesta", "Error", JOptionPane.ERROR_MESSAGE);
					}else{
					 if(!logVOUser.isnumber(getTextFieldUnidadesProducto().getText())){
						JOptionPane.showMessageDialog(getBtnEliminarUnidades(), "Se tiene que escribir un número entero positivo", "Error", JOptionPane.ERROR_MESSAGE);
					}
					if(!logVOUser.verificarResta(Integer.parseInt(getTextFieldUnidadesProducto().getText()), cestaSeleccionado)){
						JOptionPane.showMessageDialog(getBtnEliminarUnidades(), "No se pueden eliminar tantos productos de la cesta", "Error", JOptionPane.ERROR_MESSAGE);
					}else{
					modeloListaCesta = logVOUser.restarProductoCesta(Integer.parseInt(getTextFieldUnidadesProducto().getText()), cestaSeleccionado);
					getListCesta().setModel(modeloListaCesta);
					getTextGastoTotal().setText(String.valueOf(logVOUser.calcularPrecioTotal()));
					getListCesta().setSelectedIndex(-1);
					}
				}}
			});
		}
		return btnEliminarUnidades;
	
	
	}
		

	
	
	private JButton getBtnSumarUnidades() {
		if (btnSumarUnidades == null) {
			btnSumarUnidades = new JButton("A\u00F1adir");
			btnSumarUnidades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getListCesta().setSelectedIndex(-1);
					Producto productoSeleccionado = (Producto) getListProductos().getSelectedValue();//el producto seleccionado, porque solo permito seleccionar uno
					
					if(productoSeleccionado == null){
						JOptionPane.showMessageDialog(getBtnSumarUnidades(), "Se tiene que seleccionar un producto de la lista de productos", "Error", JOptionPane.ERROR_MESSAGE);
					}
					if(!logVOUser.isnumber(getTextFieldUnidadesProducto().getText())){
						JOptionPane.showMessageDialog(getBtnSumarUnidades(), "Se tiene que escribir un número entero positivo", "Error", JOptionPane.ERROR_MESSAGE);
					}
					//validaciones hasta ahora
					ModeloProductosPedidos productopedido = new ModeloProductosPedidos((Producto) getListProductos().getSelectedValue(), Integer.parseInt(getTextFieldUnidadesProducto().getText()));
					modeloListaCesta = logVOUser.sumarProductoACesta(productopedido,modeloListaCesta);
					getListCesta().setModel(modeloListaCesta);
					getTextGastoTotal().setText(String.valueOf(logVOUser.calcularPrecioTotal()));
					getListProductos().setSelectedIndex(-1);
					getListCesta().setSelectedIndex(-1);
				}
			});
		}
		return btnSumarUnidades;
	}
	private JTextField getTextFieldUnidadesProducto() {
		if (textFieldUnidadesProducto == null) {
			textFieldUnidadesProducto = new JTextField();
			textFieldUnidadesProducto.setHorizontalAlignment(SwingConstants.RIGHT);
			textFieldUnidadesProducto.setText("1");
			textFieldUnidadesProducto.setColumns(10);
		}
		return textFieldUnidadesProducto;
	}
	private JLabel getLblUnidadesProducto() {
		if (lblUnidadesProducto == null) {
			lblUnidadesProducto = new JLabel("Unidades: ");
			lblUnidadesProducto.setLabelFor(getTextFieldUnidadesProducto());
		}
		return lblUnidadesProducto;
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
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox(MetodosPago.values());
		}
		return comboBox;
	}
}
