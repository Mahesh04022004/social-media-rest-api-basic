package com.rest.restapi.user;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserDAO {

	@Autowired
	UserReository userReository;

	@GetMapping("/users")
	public List<User> findAllUser() {
		return userReository.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> findUser(@PathVariable Integer id) {
		User user = userReository.findUser(id);
		
		if(user == null) {
			throw new UserNotFoundException("id"+id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).findAllUser());
		entityModel.add(linkBuilder.withRel("all-users"));
		
		return entityModel;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser( @Valid @RequestBody User user) {
		userReository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
