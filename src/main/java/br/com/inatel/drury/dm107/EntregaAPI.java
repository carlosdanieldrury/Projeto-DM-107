package br.com.inatel.drury.dm107;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("/api")
public class EntregaAPI {

	@Context
	private UriInfo uriInfo;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getEntregas() {
		return Response.status(Status.OK).build();
//		try {
//			EntregaDAO entregaDAO = new EntregaDAO();
//			List<EntregaEntity> entregas = entregaDAO.list();
//			GenericEntity<List<EntregaEntity>> entities = new GenericEntity<List<EntregaEntity>>(entregas) {};
//			return Response.status(Status.OK).entity(entities).build();
//		} catch (Exception e) {
//			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
//		}
	}
	
	@GET
	@Path("/{numeroPedido}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getEntrega(@PathParam("numeroPedido") int numeroPedido) {
		return Response.ok().build();
	}
	
}
