package com.magonzalezo.xmen.daos;

import org.springframework.data.repository.CrudRepository;

import com.magonzalezo.xmen.entities.StatEntity;

public interface IStatDao extends CrudRepository<StatEntity, Long> {

}
