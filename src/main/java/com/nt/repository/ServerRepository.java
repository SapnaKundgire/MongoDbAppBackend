package com.nt.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nt.model.ServerModel;

public interface ServerRepository extends MongoRepository<ServerModel, Integer> {
	ServerModel deleteById( String  id);
	ServerModel findByName(String name);
	ServerModel findById(String id);
	 

}
