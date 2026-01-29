package uce.edu.web.api.matricula.application.representation;

public class LinkDto {

    public String href;  //referencia
    public String rel;  //nombre del enlace

    public LinkDto() {
    }

    public LinkDto(String href, String rel) {
        this.href = href;
        this.rel = rel;
    }

}
