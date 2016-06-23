/**
 * 
 */
package br.com.financeiro.util;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 * @author mendesa
 * 
 */
@ApplicationScoped
public class EntityManagerProducer implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    @PersistenceUnit(unitName = "FinanceiroPU")
    private EntityManagerFactory financeiroPUFactory;
 
    @PersistenceUnit(unitName = "FinanceiroOS")
    private EntityManagerFactory financeiroOSFactory;
 
    @RequestScoped
    @Produces
    public EntityManager createAppEntityManager() {
        return financeiroPUFactory.createEntityManager();
    }
 
    @RequestScoped
    @Produces
    public EntityManager createCorporativoEntityManager() {
        return financeiroOSFactory.createEntityManager();
    }
 
    public void closeEntityManager(@Disposes EntityManager manager) {
        if (manager.isOpen()) {
            manager.close();
        }
    }
 
}