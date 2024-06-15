package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//written by Shelly - allows CRUD operations on Employer entities
@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
}