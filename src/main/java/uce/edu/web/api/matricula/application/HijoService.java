package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;

@ApplicationScoped
public class HijoService {

    @Inject
    private HijoRepository hijoRepository;

    public List<HijoRepresentation> buscarPorEstudianteId(Integer estudianteId) {
        List<HijoRepresentation> lista = new ArrayList<>();
        for (Hijo hijo : this.hijoRepository.buscarPorIdEstudiante(estudianteId)) {
            lista.add(this.mapperToHijoR(hijo));
        }
        return lista;
    }

    private HijoRepresentation mapperToHijoR(Hijo hijo) {
        HijoRepresentation rep = new HijoRepresentation();
        rep.setId(hijo.getId());
        rep.setNombre(hijo.getNombre());
        rep.setApellido(hijo.getApellido());
        return rep;
    }
}
