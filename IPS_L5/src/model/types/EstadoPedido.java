package model.types;

/**
 * COMPLETAMENTE_ASOCIADO_OT --> todos sus productos están asociados a ordenes de trabajo</br>
 * POSIBLE_ASOCIAR_OT --> Queda algún producto que se puede asociar a una orden de trabajo
 * 
 */
public enum EstadoPedido {
	POSIBLE_ASOCIAR_OT,
	COMPLETAMENTE_ASOCIADO_OT
}