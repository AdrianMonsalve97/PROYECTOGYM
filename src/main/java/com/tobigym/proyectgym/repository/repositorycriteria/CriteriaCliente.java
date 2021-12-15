package com.tobigym.proyectgym.repository.repositorycriteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.tobigym.proyectgym.models.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CriteriaCliente implements CLCriteriaRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<Cliente> findClientesByNombreAndEdad(String nombres, String edad) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = cq.from(Cliente.class);
        // cq.select(cliente);

        Predicate predicadoNombres = cb.equal(cliente.get("nombres"), nombres);
        Predicate predicadoEdad = cb.like(cliente.get("edad"), "%" + edad + "%");
        cq.where(predicadoNombres, predicadoEdad);
        return em.createQuery(cq).getResultList();

    }

}
