package org.launchcode.Models.Data;

import org.launchcode.Models.People;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by mettenichols on 4/25/17.
 */
@Repository
@Transactional
public interface PeopleDao extends CrudRepository<People, Integer> {
}
