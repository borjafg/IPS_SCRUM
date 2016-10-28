package model.types;

/**
 * COMPLETAMENTE_ASOCIADO_OT --> todos sus productos est�n asociados a ordenes de trabajo</br>
 * POSIBLE_ASOCIAR_OT --> Queda alg�n producto que se puede asociar a una orden de trabajo
 * 
 */
public enum EstadoPedido {
	POSIBLE_ASOCIAR_OT,
	COMPLETAMENTE_ASOCIADO_OT
}