package org.openrecruitment.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class JobOffer {

  @Id
  @Column(length = 40)
  private String id;

  @Column(length = 80)
  private String jobtitle;

  @ManyToMany
  private List<Vacancy> vacancies;

  @OneToMany
  private List<Application> applications;

}
