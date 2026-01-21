package uce.edu.web.api.materia.infraestructure;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.materia.domain.Materia;

@ApplicationScoped
public class MateriaRepository implements PanacheRepository<Materia> {

    //Con Panache HQL/JPQL
    /*
    public List<Materia> buscarPorCreditos(Integer creditos) {
        return list("creditos", creditos);
    }

    public List<Materia> buscarPorHorasSemanales(Integer horasSemanales) {
        return list("horasSemanales", horasSemanales);
    }
     */
    // Con Sentencias SQL
    @SuppressWarnings("unchecked")
    public List<Materia> buscarPorCreditos(Integer creditos) {
        return getEntityManager()
                .createNativeQuery("SELECT * FROM Materia WHERE creditos = ?1", Materia.class)
                .setParameter(1, creditos)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Materia> buscarPorHorasSemanales(Integer horasSemanales) {
        return getEntityManager()
                .createNativeQuery("SELECT * FROM Materia WHERE horasSemanales = ?1", Materia.class)
                .setParameter(1, horasSemanales)
                .getResultList();
    }

}
