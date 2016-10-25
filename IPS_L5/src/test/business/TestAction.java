package test.business;

import javax.persistence.EntityManager;

public interface TestAction {

	public String doTest(EntityManager ent);
}