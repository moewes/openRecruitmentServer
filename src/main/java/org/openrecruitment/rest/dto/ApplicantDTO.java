package org.openrecruitment.rest.dto;

import java.io.Serializable;
import org.openrecruitment.persistence.entities.Applicant;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ApplicantDTO implements Serializable {

	private Long id;
	private int version;
	private String name;

	public ApplicantDTO() {
	}

	public ApplicantDTO(final Applicant entity) {
		if (entity != null) {
			//this.id = entity.getId();
			this.version = entity.getVersion();
			this.name = entity.getName();
		}
	}

	public Applicant fromDTO(Applicant entity, EntityManager em) {
		if (entity == null) {
			entity = new Applicant();
		}
		entity.setVersion(this.version);
		entity.setName(this.name);
		entity = em.merge(entity);
		return entity;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}