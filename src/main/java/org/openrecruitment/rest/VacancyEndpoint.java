package org.openrecruitment.rest;

import org.openrecruiting.rest.VacancyResource;
import org.openrecruiting.rest.VacancyService;
import org.openrecruitment.persistence.entities.Vacancy;
import org.openrecruitment.persistence.repositories.VacancyRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class VacancyEndpoint implements VacancyService {

  @Inject
  private VacancyRepository repository;

  @Inject
  private VacancyMapper mapper;

  @Override
  public VacancyResource get(String id) {

    Vacancy vacancy = repository.findBy(id);

    VacancyResource result = mapper.entityToResource(vacancy);

    return result;
  }

  @Override
  public List<VacancyResource> getAll() {

    List<VacancyResource> result = new ArrayList<>();

    List<Vacancy> vacancies = repository.findAll();

    for(Vacancy vacancy : vacancies) {
      VacancyResource resource = mapper.entityToResource(vacancy);
      result.add(resource);
    }
    return result;
  }
}
