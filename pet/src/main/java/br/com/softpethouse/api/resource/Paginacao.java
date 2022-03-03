package br.com.softpethouse.api.resource;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class Paginacao {

    @Min(1L)
    @DefaultValue("10")
    @QueryParam("limit")
    @Parameter(
            description = "A quantidade máxima de registros retornados"
    )
    private int limit;

    @Min(0L)
    @DefaultValue("0")
    @QueryParam("index")
    @Parameter(
            description = "O índice do primeiro resultado"
    )
    private int index;

    public Paginacao() {}

    public Paginacao(int limit, int index) {
        this.limit = limit;
        this.index = index;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
