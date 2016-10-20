package business.impl.util;

import business.exception.BusinessException;

public interface Command {
	public Object execute() throws BusinessException;
}