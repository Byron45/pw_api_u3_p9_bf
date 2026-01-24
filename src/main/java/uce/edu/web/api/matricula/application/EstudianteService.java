package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for (Estudiante estu : this.estudianteRepository.listAll()) {
            list.add(this.mapperTOER(estu));
        }
        return list;
    }

    public EstudianteRepresentation consultarPorId(Integer id) {
        return this.mapperTOER(this.estudianteRepository.findById(id).orElse(null));
    }

    @Transactional
    public void crear(Estudiante estu) {
        this.estudianteRepository.persist(estu);
    }

    @Transactional //cuando genera cambios en la base de datos
    public void actualizar(Integer id, EstudianteRepresentation est) {
        EstudianteRepresentation estuRep = this.consultarPorId(id);
        if (estuRep == null) {
            return;
        }
        Estudiante estu = this.mapperToEstudiante(estuRep);
        estu.setApellido(est.getApellido());
        estu.setNombre(est.getNombre());
        estu.setFechaNacimiento(est.getFechaNacimiento());
        estu.setGenero(est.getGenero());
        estu.setProvincia(est.getProvincia());

        //se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarParcial(Integer id, Estudiante est) {
        EstudianteRepresentation estuRep = this.consultarPorId(id);
        if (estuRep == null) {
            return;
        }
        Estudiante estu = this.mapperToEstudiante(estuRep);
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
        this.estudianteRepository.deleteById(id);
    }

    public List<Estudiante> buscarPorProvincia(String provincia, String genero) {
        //return this.estudianteRepository.find("provincia", provincia).list();
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list();

    }

    private EstudianteRepresentation mapperTOER(Estudiante estu) {
        EstudianteRepresentation estuRep = new EstudianteRepresentation();
        estuRep.setId(estu.getId());
        estuRep.setApellido(estu.getApellido());
        estuRep.setNombre(estu.getNombre());
        estuRep.setFechaNacimiento(estu.getFechaNacimiento());
        estuRep.setGenero(estu.getGenero());
        estuRep.setProvincia(estu.getProvincia());
        return estuRep;
    }

    private Estudiante mapperToEstudiante(EstudianteRepresentation estuRep) {
        Estudiante estu = new Estudiante();
        estu.setId(estuRep.getId());
        estu.setApellido(estuRep.getApellido());
        estu.setNombre(estuRep.getNombre());
        estu.setFechaNacimiento(estuRep.getFechaNacimiento());
        estu.setGenero(estuRep.getGenero());
        estu.setProvincia(estuRep.getProvincia());
        return estu;
    }

}
