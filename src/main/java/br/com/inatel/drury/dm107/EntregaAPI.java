package br.com.inatel.drury.dm107;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("/entrega")
public class EntregaAPI {

	@Context
	private UriInfo uriInfo;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getEntregas() {
		try {
			EntregaDAO entregaDAO = new EntregaDAO();
			List<EntregaEntity> entregas = entregaDAO.list();
			GenericEntity<List<EntregaEntity>> entities = new GenericEntity<List<EntregaEntity>>(entregas) {};
			return Response.status(Status.OK).entity(entities).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/{numeroPedido}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getEntrega(@PathParam("numeroPedido") int numeroPedido) {
		try {
			EntregaDAO entregaDAO = new EntregaDAO();
			EntregaEntity entrega = entregaDAO.getEntrega(numeroPedido);
			if (entrega == null) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			GenericEntity<EntregaEntity> entities = new GenericEntity<EntregaEntity>(entrega) {};
			return Response.status(Status.OK).entity(entities).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	//@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createEntrega(@FormParam("numeroPedido") int numeroPedido, @FormParam("clienteId") int clienteId) {
		
		EntregaDAO entregaDAO = new EntregaDAO();
		System.out.println("NumeroPedido " + String.valueOf(numeroPedido));
		System.out.println("Cliente Id " + String.valueOf(clienteId));
		try {
			EntregaEntity entrega = new EntregaEntity();
			entrega.setIdCliente(clienteId);
			entrega.setNumeroPedido(numeroPedido);
			entregaDAO.insertEntrega(entrega);
			GenericEntity entity = new GenericEntity<EntregaEntity>(entrega){};
			return Response.status(Status.CREATED).entity(entity).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT
	@Path("/{numeroPedido}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateEntrega(EntregaEntity entrega) {
		
		EntregaDAO entregaDAO = new EntregaDAO();
		try {
			entregaDAO.updateItem(entrega);
			GenericEntity entity = new GenericEntity<EntregaEntity>(entrega){};
			return Response.status(Status.CREATED).entity(entity).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("/{numeroPedido}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response deleteEntrega(@PathParam("numeroPedido") int numeroPedido) {
		
		EntregaDAO entregaDAO = new EntregaDAO();
		try {
			boolean result = entregaDAO.delete(numeroPedido);
			System.out.println("");
			if (result)
				return Response.status(Status.OK).build();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
