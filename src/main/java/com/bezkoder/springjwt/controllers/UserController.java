package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.controllers.dto.UserDTO;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/list")
	private List<UserDTO> getAllUser() {
		return userRepository.findAll()
				.stream()
				.filter(u -> u.getRoles().stream().noneMatch(role -> role.getName() != Role.ERole.ROLE_DEVELOPPEUR))
				.map(UserDTO::map) // Map User to UserDTO
				.collect(Collectors.toList());
	}


	@PostMapping("/changeState/{userId}")
	private List<UserDTO> ChangeState(@PathVariable Integer userId ) {

		if (userId == null){
			return Collections.emptyList()	;
		}

		User user = userRepository.findById(Long.parseLong(String.valueOf(userId))).get();
		user.setActive(!user.getActive());
		userRepository.save(user);

		return getAllUser() ;
	}



}
