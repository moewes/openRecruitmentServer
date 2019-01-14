package org.openrecruitment.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Application {

  @Id
  @Column(length=40)
  private String id;

  @Column
  private LocalDate applicationDate;

  @ManyToOne
  private JobOffer jobOffer;
}
