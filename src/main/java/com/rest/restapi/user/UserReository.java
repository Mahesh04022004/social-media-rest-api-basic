package com.rest.restapi.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserReository {
	
	private static List<User> user = new ArrayList<>();
	private static int idCount = 0;
	
	static {
		user.add(new User(++idCount,"Mahesh",LocalDate.now().minusYears(20)));
		user.add(new User(++idCount,"Gungun",LocalDate.now().minusYears(19)));
		user.add(new User(++idCount,"Harsh",LocalDate.now().minusYears(23)));
	}
	
	public List<User> findAll() {
		return user;
	}

	public User findUser(int id) {
		
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return user.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public User save(User newUser) {
		newUser.setId(++idCount);
		user.add(newUser);
		return newUser;
	}
}
