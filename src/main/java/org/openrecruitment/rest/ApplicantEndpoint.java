package org.openrecruitment.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.openrecruitment.rest.dto.ApplicantDTO;
import org.openrecruitment.model.Applicant;

/**
 * 
 */
@Stateless
@Path("/applicants")
public class ApplicantEndpoint {

	@PersistenceContext(unitName = "openRecruitmentBackend-persistence-unit")
	private EntityManager em;

	@POST
	@Consumes("application/json")
	public Response create(ApplicantDTO dto) {
		Applicant entity = dto.fromDTO(null, em);
		em.persist(entity);
		return Response.created(null).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {

		Applicant entity = em.find(Applicant.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(entity);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		TypedQuery<Applicant> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT a FROM Applicant a WHERE a.id = :entityId ORDER BY a.id",
						Applicant.class);
		findByIdQuery.setParameter("entityId", id);
		Applicant entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		ApplicantDTO dto = new ApplicantDTO(entity);
		return Response.ok(dto).build();
	}

	@GET
	@Produces("application/json")
	public List<ApplicantDTO> listAll(
			@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {

		TypedQuery<Applicant> findAllQuery = em.createQuery(
				"SELECT DISTINCT a FROM Applicant a ORDER BY a.id",
				Applicant.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		final List<Applicant> searchResults = findAllQuery.getResultList();
		final List<ApplicantDTO> results = new ArrayList<ApplicantDTO>();
		for (Applicant searchResult : searchResults) {
			ApplicantDTO dto = new ApplicantDTO(searchResult);
			results.add(dto);
		}
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, ApplicantDTO dto) {
		if (dto == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(dto.getId())) {
			return Response.status(Status.CONFLICT).entity(dto).build();
		}
		Applicant entity = em.find(Applicant.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity = dto.fromDTO(entity, em);
		try {
			entity = em.merge(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Status.CONFLICT).entity(e.getEntity())
					.build();
		}
		return Response.noContent().build();
	}
}