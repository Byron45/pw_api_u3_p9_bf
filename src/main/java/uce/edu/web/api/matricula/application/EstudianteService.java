package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return this.estudianteRepository.listAll();

    }

    public Estudiante consultarPorId(Integer id) {
        return this.estudianteRepository.findById(id.longValue());
    }

    @Transactional
    public void crear(Estudiante estu) {
        this.estudianteRepository.persist(estu);
    }

    @Transactional //cuando genera cambios en la base de datos
    public void actualizar(Integer id, Estudiante est) {
        Estudiante estu = this.consultarPorId(id);
        estu.setApellido(est.getApellido());
        estu.setNombre(est.getNombre());
        estu.setFechaNacimiento(est.getFechaNacimiento());
        estu.setGenero(est.getGenero());
        estu.setProvincia(est.getProvincia());

        //se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarParcial(Integer id, Estudiante est) {
        Estudiante estu = this.consultarPorId(id);
        if (est.getApellido() != null) {
            estu.setApellido(est.getApellido());
        }
        if (est.getNombre() != null) {
            estu.setNombre(est.getNombre());
        }
        if (est.getFechaNacimiento() != null) {
            estu.setFechaNacimiento(est.getFechaNacimiento());
        }
        if (est.getGenero() != null) {
            estu.setGenero(est.getGenero());
        }
        if (est.getProvincia() != null) {
            estu.setProvincia(est.getProvincia());
        }

        //se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void eliminar(Integer id) {
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<Estudiante> buscarPorProvincia(String provincia, String genero) {
        //return this.estudianteRepository.find("provincia", provincia).list();
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list();

    }

}
