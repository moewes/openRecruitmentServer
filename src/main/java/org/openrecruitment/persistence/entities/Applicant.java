package org.openrecruitment.persistence.entities;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "applicant")
public class Applicant {

	@Id
	@Column(name = "ID",  nullable = false, length = 40)
	private String id;

	@Version
	@Column(name = "version")
	private int version;

	@Column(nullable = false)
	private String name;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Applicant)) {
			return false;
		}
		Applicant other = (Applicant) obj;
		if (id != null) {
			return id.equals(other.id);
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (name != null && !name.trim().isEmpty())
			result += "name: " + name;
		return result;
	}
}