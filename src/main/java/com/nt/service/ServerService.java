package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nt.model.ServerModel;

import com.nt.repository.ServerRepository;
@Service
public class ServerService {

	 @Autowired
	 ServerRepository serverrepo;
	 
	 public List<ServerModel> getServers() {
			List<ServerModel> findall=serverrepo.findAll();
			return findall;
		}
	 public ServerModel saveServers(ServerModel servermodel) {
			ServerModel savedServerDetails =serverrepo.save(servermodel);

			return savedServerDetails;
		}
	 
	public ServerModel deleteServerById(String id) {
		
		return serverrepo.deleteById(id) ;
	}

public ServerModel getServerbyName(String name) {
		
		return  this.serverrepo.findByName(name);
	}
public ServerModel getServerbyId(String id) {
	return  this.serverrepo.findById(id);
}



}
