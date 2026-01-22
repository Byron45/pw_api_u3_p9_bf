package uce.edu.web.api.materia.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.materia.application.MateriaService;
import uce.edu.web.api.materia.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    public List<Materia> listarMateria() {
        return this.materiaService.listarMateria();
    }

    @GET
    @Path("/{id}")
    public Materia consultarPorId(@PathParam("id") Integer id) {
        return this.materiaService.consultarPorId(id);
    }

    @POST
    @Path("")
    public void guardar(Materia mat) {
        this.materiaService.crearMateria(mat);
    }

    @PUT
    @Path("/{id}")
    public void actualizarMateria(@PathParam("id") Integer id, Materia mat) {
        this.materiaService.actualizarMateria(id, mat);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@PathParam("id") Integer id, Materia mat) {
        this.materiaService.actualizarParcial(id, mat);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Integer id) {
        this.materiaService.eliminar(id);
    }

    @GET
    @Path("/creditos/{creditos}")
    public List<Materia> buscarPorCredito(@PathParam("creditos") Integer creditos) {
        return this.materiaService.buscarPorCreditos(creditos);

    }

    @GET
    @Path("/horas/{horasSemanales}")
    public List<Materia> buscarPorHorasSemanales(@PathParam("horasSemanales") Integer horasSemanales) {
        return this.materiaService.buscarPorHorasSemanales(horasSemanales);
    }

}
