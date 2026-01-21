package uce.edu.web.api.materia.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.materia.domain.Materia;
import uce.edu.web.api.materia.infraestructure.MateriaRepository;

@ApplicationScoped
public class MateriaService {

    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarMateria() {
        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id) {
        return this.materiaRepository.findById(id.longValue());
    }

    @Transactional
    public void crearMateria(Materia mat) {
        this.materiaRepository.persist(mat);

    }

    @Transactional
    public void actualizarMateria(Integer id, Materia mat) {
        Materia mate = this.consultarPorId(id);
        mate.setNombre(mat.getNombre());
        mate.setCodigo(mat.getCodigo());
        mate.setCreditos(mat.getCreditos());
        mate.setHorasSemanales(mat.getHorasSemanales());

    }

    @Transactional
    public void actualizarParcial(Integer id, Materia mat) {
        Materia mate = this.consultarPorId(id);
        if (mat.getNombre() != null) {
            mate.setNombre(mat.getNombre());
        }
        if (mat.getCodigo() != null) {
            mate.setCodigo(mat.getCodigo());
        }
        if (mat.getCreditos() != null) {
            mate.setCreditos(mat.getCreditos());
        }
        if (mat.getHorasSemanales() != null) {
            mate.setHorasSemanales(mat.getHorasSemanales());
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.materiaRepository.deleteById(id.longValue());
    }

    public List<Materia> buscarPorCreditos(Integer creditos) {
        return this.materiaRepository.buscarPorCreditos(creditos);
    }

    public List<Materia> buscarPorHorasSemanales(Integer horasSemanales) {
        return this.materiaRepository.buscarPorHorasSemanales(horasSemanales);
    }

}
