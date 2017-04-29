package org.launchcode.Models.Data;

import org.launchcode.Models.Occasion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by mettenichols on 4/25/17.
 */
@Repository
@Transactional
public interface OccasionDao extends CrudRepository<Occasion, Integer> {
}
