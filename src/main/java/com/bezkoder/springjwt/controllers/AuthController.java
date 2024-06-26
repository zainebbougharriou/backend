package com.bezkoder.springjwt.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	JwtUtils jwtUtils;
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		try {
			return authenticate(loginRequest.getUsername(), loginRequest.getPassword());
		} catch (AuthenticationException e) {
			logger.error(e.getMessage());
			throw new BadCredentialsException("Invalid username/password supplied");
		}

	}

	private ResponseEntity<?> authenticate(String username, String password) {
		 authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password));
		 User authenticatedUser = userRepository.findByUsername(username).get();

		if (authenticatedUser.getActive() != null && !authenticatedUser.getActive()){
			throw new UsernameNotFoundException("Votre Compte est désactiver");
		}

		if (authenticatedUser == null) {
			throw new UsernameNotFoundException("Username " + username + "not found");
		}


		String jwt = jwtUtils.generateJwtToken(authenticatedUser);

		return ResponseEntity.ok(new JwtResponse(jwt,
				authenticatedUser.getId(),
				authenticatedUser.getUsername(),
				authenticatedUser.getEmail(),
				new ArrayList<>(authenticatedUser.getRoles())	.get(0)			)
		);
	}

		@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if(!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Password or ConfirmPassword in not valid!"));
		}


			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getUsername(),
							"{bcrypt}"+hashedPassword);

	//	Set<String> strRoles = null;
		Set<Role> roles = new HashSet<>();

	//	if (strRoles == null) {
			Role userRole = roleRepository.findByName(Role.ERole.ROLE_DEVELOPPEUR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
	//	}
//		else {
//			strRoles.forEach(role -> {
//				switch (role) {
//				case "administrateur":
//					Role adminRole = roleRepository.findByName(Role.ERole.ROLE_ADMINISTRATEUR)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(adminRole);
//
//					break;
//				default:
//					Role userRole = roleRepository.findByName(Role.ERole.ROLE_DEVELOPPEUR)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(userRole);
//				}
//			});
//		}

		user.setRoles(roles);
		user.setName(signUpRequest.getName());
		user.setActive(false);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
//	@PostMapping("/changerMotDePasse")
//	public ResponseEntity<?> changerMotDePasse(@Valid @RequestBody ChangerMotDePasseDto changerMotDePasseDto) {
//	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    String username = authentication.getName();
//
//	    User user = userRepository.findByUsername(username)
//	            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//
//	    if (!encoder.matches(changerMotDePasseDto.getAncienMotDePasse(), user.getPassword())) {
//	        return ResponseEntity
//	                .badRequest()
//	                .body(new MessageResponse("Error: Ancien mot de passe incorrect!"));
//	    }
//
//	    user.setPassword(encoder.encode(changerMotDePasseDto.getNouveauMotDePasse()));
//	    userRepository.save(user);
//
//	    return ResponseEntity.ok(new MessageResponse("Mot de passe changé avec succès!"));
//	}

}
