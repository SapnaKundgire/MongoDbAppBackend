package com.nt.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nt.ecxception.ServerNotFoundException;

import com.nt.model.ServerModel;
import com.nt.service.ServerService;
@Controller
public class ServerController {
	@Autowired
	  ServerService serverservice;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		
	    List<ServerModel> listServers = serverservice.getServers();
	    model.addAttribute("listServers", listServers);
	     
	    return "index";
	}
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
	    ServerModel server = new  ServerModel();
	    model.addAttribute("server", server);
	     
	    return "AddServer";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveServerDetails(@ModelAttribute("server") ServerModel  server) {
	    serverservice.saveServers(server);
	     
	    return "redirect:/";
	}
	@RequestMapping("/delete/{id}")
	public String deleteServerDetail(@PathVariable(name = "id") String id) {
	    serverservice.deleteServerById(id);
	    return "redirect:/";       
	}
	
	@RequestMapping(path = {"/search"})
	 public String home(Model model, String id) throws ServerNotFoundException{
		   ServerModel listServers = serverservice.getServerbyId(id);
		  if(listServers==null) {
			             throw new ServerNotFoundException("id not found");  
			  }
			 else
				 model.addAttribute("listServers", listServers);
			  return "SearchbyId";
	 }
	
	@RequestMapping("/searchByname")
	public String  getServerByName(Model model,String name ) throws  ServerNotFoundException {
	
			    ServerModel  server =serverservice.getServerbyName(name);
			    if(server==null)
					throw new ServerNotFoundException("name");
			    else
				model.addAttribute("server", server);
			 return "SearchbyName";
		}
	
    @PutMapping("/updateOrsave")
	public ResponseEntity<ServerModel> updateData(@RequestBody ServerModel servermodel) {
		    	        ServerModel saveserver = serverservice.saveServers(servermodel);
		    	        return ResponseEntity.ok(saveserver);
		}
	
}
