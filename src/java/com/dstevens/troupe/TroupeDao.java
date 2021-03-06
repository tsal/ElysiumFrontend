package com.dstevens.troupe;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TroupeDao extends CrudRepository<Troupe, Integer> {

    @Query("SELECT a FROM Troupe a WHERE a.name = ?1 AND a.deleteTimestamp is null")
    Troupe findUndeletedNamed(String name);
    
    @Query("SELECT COUNT(a) FROM Troupe a WHERE a.name = ?1 AND a.deleteTimestamp is null")
    int countOfUndeletedNamed(String name);
    
    @Query("SELECT a FROM Troupe a WHERE a.name = ?1")
    Iterable<Troupe> findNamed(String name);
    
    @Query("SELECT COUNT(a) FROM Troupe a WHERE a.name = ?1")
    int countOfNamed(String name);

    @Query("SELECT a FROM Troupe a WHERE a.deleteTimestamp is null")
	Iterable<Troupe> findAllUndeleted();
}
