package org.openrecruitment.persistence.repositories;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.openrecruitment.persistence.entities.Vacancy;

@Repository
public interface VacancyRepository extends EntityRepository<Vacancy,String> {
}
