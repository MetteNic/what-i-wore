package org.launchcode.Models.Data;

import org.launchcode.Models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by mettenichols on 5/11/17.
 */
@Repository
@Transactional
public interface TagDao extends CrudRepository<Tag, Integer> {
     Tag findByName(String name);
     Tag findById(int id);





}

