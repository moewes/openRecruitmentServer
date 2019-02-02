package org.openrecruitment.rest;

import org.mapstruct.Mapper;
import org.openrecruiting.rest.VacancyResource;
import org.openrecruitment.persistence.entities.Vacancy;

@Mapper( componentModel = "cdi")
public interface VacancyMapper {
  VacancyResource entityToResource(Vacancy vacancy);
  Vacancy resourceToEntity(VacancyResource resource);
}
