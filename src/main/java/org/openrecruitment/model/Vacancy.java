package org.openrecruitment.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Vacancy {

  @Id
  @Column(length=40)
  private String id;

  @Column(length = 80)
  private String jobtilte;

  @ManyToMany
  private List<JobOffer> jobOffers;

  public Vacancy() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vacancy vacancy = (Vacancy) o;
    return Objects.equals(id, vacancy.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Vacancy{" +
            "id='" + id + '\'' +
            ", jobtilte='" + jobtilte + '\'' +
            '}';
  }
}
