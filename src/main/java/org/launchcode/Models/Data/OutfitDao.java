package org.launchcode.Models.Data;

import org.launchcode.Models.Outfit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by mettenichols on 4/12/17.
 */
@Repository
@Transactional
public interface OutfitDao extends CrudRepository<Outfit, Integer> {
}
