package EntityManagerF;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SingletonEntityManagerFactory {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
	
	static public EntityManager getEntityManager (){
		return emf.createEntityManager();
	}

}
