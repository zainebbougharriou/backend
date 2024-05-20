package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Niveaux;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.NiveauxRepository;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Repository
public class InitService {

    private static final String SUPPLIER_ACCOUNT_MAIL = "admin@test.com";
    private static final String SUPPLIER_ACCOUNT_NAME = "Dev coder";

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NiveauxRepository niveauxRepository;

    @Transactional
    public void init() {

        addAdmin();
        try {
            addNiveau();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void  addAdmin (){

        if(userRepository.count() > 0){
            return;
        }

        //add role
        Role role = new Role();
        role.setName(Role.ERole.ROLE_ADMINISTRATEUR);
        role.setLabel("ROLE ADMINISTRATEUR");
        Role role1 = roleRepository.save(role);

        //add Admin user
        User user = addUser(SUPPLIER_ACCOUNT_MAIL, SUPPLIER_ACCOUNT_NAME);
        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        user.setRoles(roles);

        userRepository.save(user);
    }

    private User addUser(String username, String name) {


        User user = new User();
        user.setUsername(username);
        user.setEmail(username);

        //Encrypt password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("admin123");
        user.setPassword("{bcrypt}"+hashedPassword);
        return user;
    }


    private void addNiveau() throws IOException {

        if (niveauxRepository.count() > 0){
            return;
        }

        final String folder = "init/images/";

        InputStream in1 = new ClassPathResource(folder + "1st-prize_11173776.png").getInputStream();
        byte[] image1 = IOUtils.toByteArray(in1);
        String base64EncodedString1 = Base64.getEncoder().encodeToString(image1);

        Niveaux niveaux1 = new Niveaux();
        niveaux1.setImageNiveau(base64EncodedString1);
        niveaux1.setNomNiveau("Avancé");
        niveaux1.setPriorityNiveau(3);

        niveauxRepository.save(niveaux1);

        InputStream in2 = new ClassPathResource(folder + "2nd-place_11173785.png").getInputStream();
        byte[] image2 = IOUtils.toByteArray(in2);
        String base64EncodedString2 = Base64.getEncoder().encodeToString(image2);

        Niveaux niveaux2 = new Niveaux();
        niveaux2.setImageNiveau(base64EncodedString2);
        niveaux2.setNomNiveau("Intermédiaire");
        niveaux2.setPriorityNiveau(2);


        niveauxRepository.save(niveaux2);


        InputStream in3 = new ClassPathResource(folder + "3rd-place_11173794.png").getInputStream();
        byte[] image3 = IOUtils.toByteArray(in3);
        String base64EncodedString3 = Base64.getEncoder().encodeToString(image3);

        Niveaux niveaux3 = new Niveaux();
        niveaux3.setImageNiveau(base64EncodedString3);
        niveaux3.setNomNiveau("Debutant");
        niveaux3.setPriorityNiveau(1);

        niveauxRepository.save(niveaux3);


    }
}
