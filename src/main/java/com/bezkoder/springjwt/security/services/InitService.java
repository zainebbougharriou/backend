package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.*;
import com.bezkoder.springjwt.repository.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Repository
public class InitService {

    private static final String ADMIN_ACCOUNT_MAIL = "admin@test.com";
    private static final String ADMIN_ACCOUNT_PW = "Admin123";
    private static final String ADMIN_ACCOUNT_NAME = "Admin";

    private static final String DEV_ACCOUNT_MAIL = "Dev@test.com";
    private static final String DEV_ACCOUNT_PW = "Devp123";
    private static final String DEV_ACCOUNT_NAME = "Devp";

    private static final String folder = "init/images/";

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NiveauxRepository niveauxRepository;
    @Autowired
    private NiveauxService niveauxService;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private PropositionRepository propositionRepository;
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Transactional
    public void init() {

        addUsers();
        try {
            addNiveau();
            addCategory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void  addUsers (){

        if(userRepository.count() > 0){
            return;
        }

        //add role
        Role role = new Role();
        role.setName(Role.ERole.ROLE_ADMINISTRATEUR);
        role.setLabel("ROLE ADMINISTRATEUR");
        Role role1 = roleRepository.save(role);

        //add Admin user
        User user = addUser(ADMIN_ACCOUNT_MAIL, ADMIN_ACCOUNT_PW , ADMIN_ACCOUNT_NAME);
        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        user.setRoles(roles);

        userRepository.save(user);


        //add role
        Role role2 = new Role();
        role2.setName(Role.ERole.ROLE_DEVELOPPEUR);
        role2.setLabel("ROLE DEVELOPPEUR");
        Role role3 = roleRepository.save(role2);

        //add Admin user
        User user1 = addUser(DEV_ACCOUNT_MAIL, DEV_ACCOUNT_PW , DEV_ACCOUNT_NAME);
        Set<Role> roles1 = new HashSet<>();
        roles1.add(role3);
        user1.setRoles(roles1);

        userRepository.save(user1);
    }
    private User addUser(String username, String pw , String name) {


        User user = new User();
        user.setUsername(username);
        user.setEmail(username);
        user.setName(name);

        //Encrypt password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(pw);
        user.setPassword("{bcrypt}"+hashedPassword);
        return user;
    }


    private void addNiveau() throws IOException {

        if (niveauxRepository.count() > 0){
            return;
        }


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


    private void  addCategory() throws IOException {
        if (categorieRepository.count() > 0){
            return;
        }

        Categorie categoryFlutter = new Categorie();
        categoryFlutter.setNomCategorie("Flutter");
        InputStream flutterImage = new ClassPathResource(folder + "flutter.png").getInputStream();
        byte[] Flutter = IOUtils.toByteArray(flutterImage);
        String flutterImageAsBase64 = Base64.getEncoder().encodeToString(Flutter);
        categoryFlutter.setImageCategorie(flutterImageAsBase64);

        Categorie categoryFlutter1 =  categorieRepository.save(categoryFlutter);


        List<Niveaux> niveaux = niveauxService.getAllNiveaux();

        niveaux.forEach(n -> {
            for (int i = 1; i <= 3; i++) { // Create 3 quizzes for each level
                Quiz quiz = new Quiz();
                quiz.setCategorie(categoryFlutter1);
                quiz.setNomQuiz("Flutter 0" + i);
                quiz.setNiveaux(n);
                Quiz newQuiz =  quizRepository.save(quiz);
                addQuestion(newQuiz);
            }
        });


        Categorie categoryAngular = new Categorie();
        categoryAngular.setNomCategorie("Angular");
        InputStream angularImage = new ClassPathResource(folder + "angular.png").getInputStream();
        byte[] angular = IOUtils.toByteArray(angularImage);
        String angularImageAsBase64 = Base64.getEncoder().encodeToString(angular);
        categoryAngular.setImageCategorie(angularImageAsBase64);

        Categorie categoryAngular1 =  categorieRepository.save(categoryAngular);

        niveaux.forEach(n -> {
            for (int i = 1; i <= 3; i++) { // Create 3 quizzes for each level
                Quiz quiz = new Quiz();
                quiz.setCategorie(categoryAngular1);
                quiz.setNomQuiz("Angular 0" + i);
                quiz.setNiveaux(n);

                Quiz newQuiz =  quizRepository.save(quiz);
                addQuestionForAngularQuiz(newQuiz);

            }
        });




        Categorie categoryDotNet = new Categorie();
        categoryDotNet.setNomCategorie("DotNet");
        InputStream dotNetImage = new ClassPathResource(folder + "dotNet.png").getInputStream();
        byte[] dotNet = IOUtils.toByteArray(dotNetImage);
        String dotNetImageAsBase64 = Base64.getEncoder().encodeToString(dotNet);
        categoryDotNet.setImageCategorie(dotNetImageAsBase64);

        Categorie categoryDotNet1 =  categorieRepository.save(categoryDotNet);

        niveaux.forEach(n -> {
            for (int i = 1; i <= 3; i++) { // Create 3 quizzes for each level
                Quiz quiz = new Quiz();
                quiz.setCategorie(categoryDotNet1);
                quiz.setNomQuiz("DotNet 0" + i);
                quiz.setNiveaux(n);
                Quiz newQuiz =  quizRepository.save(quiz);
                addQuestionForDotNetQuiz(newQuiz);
            }
        });


        Categorie categoryVue = new Categorie();
        categoryVue.setNomCategorie("Vue");
        InputStream vueImage = new ClassPathResource(folder + "Vue.png").getInputStream();
        byte[] vue = IOUtils.toByteArray(vueImage);
        String vueImageAsBase64 = Base64.getEncoder().encodeToString(vue);
        categoryVue.setImageCategorie(vueImageAsBase64);

        Categorie categoryVue1 =  categorieRepository.save(categoryVue);

        niveaux.forEach(n -> {
            for (int i = 1; i <= 3; i++) { // Create 3 quizzes for each level
                Quiz quiz = new Quiz();
                quiz.setCategorie(categoryVue1);
                quiz.setNomQuiz("Vue 0" + i);
                quiz.setNiveaux(n);
                Quiz newQuiz =  quizRepository.save(quiz);
                addQuestionForVueQuiz(newQuiz);
            }
        });

    }

    private void  addQuestion(Quiz quiz){

        List<Question> questionList = new ArrayList<>();

        Question question = new Question();
            question.setTexteQuestion("Qu'est-ce que Flutter ?");
            question.setReponseCorrect("Un framework de développement d'interfaces utilisateur");
            question.setQuiz(quiz);
        Question question1 = questionRepository.save(question);

        Proposition  proposition1 = new Proposition();
        proposition1.setQuestion(question1);
        proposition1.setTexteProp("Un langage de programmation");
        propositionRepository.save(proposition1);

        Proposition  proposition2 = new Proposition();
        proposition2.setQuestion(question1);
        proposition2.setTexteProp("Un framework de développement d'interfaces utilisateur");
        propositionRepository.save(proposition2);

        Proposition  proposition3 = new Proposition();
        proposition3.setQuestion(question1);
        proposition3.setTexteProp(" Un système d'exploitation mobile");
        propositionRepository.save(proposition3);

        questionList.add(question1);


        Question question2 = new Question();
        question2.setTexteQuestion("Quelle plateforme mobile est principalement ciblée par Flutter ?");
        question2.setReponseCorrect("Les deux");
        question2.setQuiz(quiz);

        Question questionSaved2 = questionRepository.save(question2);

        Proposition proposition7 = new Proposition();
        proposition7.setQuestion(questionSaved2);
        proposition7.setTexteProp("iOS");
        propositionRepository.save(proposition7);

        Proposition proposition8 = new Proposition();
        proposition8.setQuestion(questionSaved2);
        proposition8.setTexteProp("Android");
        propositionRepository.save(proposition8);

        Proposition proposition9 = new Proposition();
        proposition9.setQuestion(questionSaved2);
        proposition9.setTexteProp("Les deux");
        propositionRepository.save(proposition9);

        questionList.add(questionSaved2);

        Question question3 = new Question();
        question3.setTexteQuestion("Quelle est la principale caractéristique de Flutter en ce qui concerne le développement d'interfaces utilisateur ?");
        question3.setReponseCorrect("Il propose un système de widgets personnalisés et hautement personnalisables.");
        question3.setQuiz(quiz);

        Question questionSaved3 = questionRepository.save(question3);

        Proposition proposition10 = new Proposition();
        proposition10.setQuestion(questionSaved3);
        proposition10.setTexteProp("Il utilise des composants natifs fournis par les plateformes mobiles.");
        propositionRepository.save(proposition10);

        Proposition proposition11 = new Proposition();
        proposition11.setQuestion(questionSaved3);
        proposition11.setTexteProp("Il utilise des vues web pour l'interface utilisateur.");
        propositionRepository.save(proposition11);

        Proposition proposition12 = new Proposition();
        proposition12.setQuestion(questionSaved3);
        proposition12.setTexteProp("Il propose un système de widgets personnalisés et hautement personnalisables.");
        propositionRepository.save(proposition12);

        questionList.add(questionSaved3);


        Question question4 = new Question();
        question4.setTexteQuestion("Quel est l'outil principal fourni par Flutter pour le développement et la personnalisation des interfaces utilisateur ?");
        question4.setReponseCorrect("Flutter Inspector");
        question4.setQuiz(quiz);

        Question questionSaved4 = questionRepository.save(question4);

        Proposition proposition13 = new Proposition();
        proposition13.setQuestion(questionSaved4);
        proposition13.setTexteProp("Android Studio");
        propositionRepository.save(proposition13);

        Proposition proposition14 = new Proposition();
        proposition14.setQuestion(questionSaved4);
        proposition14.setTexteProp("Xcode");
        propositionRepository.save(proposition14);

        Proposition proposition15 = new Proposition();
        proposition15.setQuestion(questionSaved4);
        proposition15.setTexteProp("Flutter Inspector");
        propositionRepository.save(proposition15);

        questionList.add(questionSaved4); // Ajouter la question à la liste de questions




        // question 5


        Question question5 = new Question();
        question5.setTexteQuestion("Quel langage de programmation est principalement utilisé pour développer des applications avec Flutter ?");
        question5.setReponseCorrect("Dart");
        question5.setQuiz(quiz);

        Question questionSaved = questionRepository.save(question5);

        Proposition proposition4 = new Proposition();
        proposition4.setQuestion(questionSaved);
        proposition4.setTexteProp("Java");
        propositionRepository.save(proposition4);

        Proposition proposition5 = new Proposition();
        proposition5.setQuestion(questionSaved);
        proposition5.setTexteProp("Dart");
        propositionRepository.save(proposition5);

        Proposition proposition6 = new Proposition();
        proposition6.setQuestion(questionSaved);
        proposition6.setTexteProp("Python");
        propositionRepository.save(proposition6);

        questionList.add(questionSaved);
        quiz.setQuestions(questionList);
        quizRepository.save(quiz);


    }


    private void addQuestionForDotNetQuiz(Quiz quiz){

        List<Question> questionList = new ArrayList<>();

        // Question 1
        Question question1 = new Question();
        question1.setTexteQuestion("Qu'est-ce que .NET ?");
        question1.setReponseCorrect("Un framework de développement logiciel");
        question1.setQuiz(quiz);
        Question questionSaved1 = questionRepository.save(question1);

        Proposition proposition1_1 = new Proposition();
        proposition1_1.setQuestion(questionSaved1);
        proposition1_1.setTexteProp("Un langage de programmation");
        propositionRepository.save(proposition1_1);

        Proposition proposition1_2 = new Proposition();
        proposition1_2.setQuestion(questionSaved1);
        proposition1_2.setTexteProp("Un framework de développement logiciel");
        propositionRepository.save(proposition1_2);

        Proposition proposition1_3 = new Proposition();
        proposition1_3.setQuestion(questionSaved1);
        proposition1_3.setTexteProp("Un système d'exploitation");
        propositionRepository.save(proposition1_3);

        questionList.add(questionSaved1);

        // Question 2
        Question question2 = new Question();
        question2.setTexteQuestion("Quel langage de programmation est principalement utilisé avec .NET ?");
        question2.setReponseCorrect("C#");
        question2.setQuiz(quiz);
        Question questionSaved2 = questionRepository.save(question2);

        Proposition proposition2_1 = new Proposition();
        proposition2_1.setQuestion(questionSaved2);
        proposition2_1.setTexteProp("Java");
        propositionRepository.save(proposition2_1);

        Proposition proposition2_2 = new Proposition();
        proposition2_2.setQuestion(questionSaved2);
        proposition2_2.setTexteProp("C#");
        propositionRepository.save(proposition2_2);

        Proposition proposition2_3 = new Proposition();
        proposition2_3.setQuestion(questionSaved2);
        proposition2_3.setTexteProp("Python");
        propositionRepository.save(proposition2_3);

        questionList.add(questionSaved2);

        // Question 3
        Question question3 = new Question();
        question3.setTexteQuestion("Quelle est la plateforme de développement recommandée pour les applications .NET ?");
        question3.setReponseCorrect("Visual Studio");
        question3.setQuiz(quiz);
        Question questionSaved3 = questionRepository.save(question3);

        Proposition proposition3_1 = new Proposition();
        proposition3_1.setQuestion(questionSaved3);
        proposition3_1.setTexteProp("Eclipse");
        propositionRepository.save(proposition3_1);

        Proposition proposition3_2 = new Proposition();
        proposition3_2.setQuestion(questionSaved3);
        proposition3_2.setTexteProp("NetBeans");
        propositionRepository.save(proposition3_2);

        Proposition proposition3_3 = new Proposition();
        proposition3_3.setQuestion(questionSaved3);
        proposition3_3.setTexteProp("Visual Studio");
        propositionRepository.save(proposition3_3);

        questionList.add(questionSaved3);

        // Question 4
        Question question4 = new Question();
        question4.setTexteQuestion("Quelle est la fonction principale de la plateforme .NET Core ?");
        question4.setReponseCorrect("Développement d'applications multiplateformes");
        question4.setQuiz(quiz);
        Question questionSaved4 = questionRepository.save(question4);

        Proposition proposition4_1 = new Proposition();
        proposition4_1.setQuestion(questionSaved4);
        proposition4_1.setTexteProp("Développement de jeux vidéo");
        propositionRepository.save(proposition4_1);

        Proposition proposition4_2 = new Proposition();
        proposition4_2.setQuestion(questionSaved4);
        proposition4_2.setTexteProp("Développement d'applications mobiles");
        propositionRepository.save(proposition4_2);

        Proposition proposition4_3 = new Proposition();
        proposition4_3.setQuestion(questionSaved4);
        proposition4_3.setTexteProp("Développement d'applications multiplateformes");
        propositionRepository.save(proposition4_3);

        questionList.add(questionSaved4);

        // Question 5
        Question question5 = new Question();
        question5.setTexteQuestion("Quel est le modèle de déploiement principal pour les applications .NET ?");
        question5.setReponseCorrect("Déploiement sous forme de packages ou de conteneurs");
        question5.setQuiz(quiz);
        Question questionSaved5 = questionRepository.save(question5);

        Proposition proposition5_1 = new Proposition();
        proposition5_1.setQuestion(questionSaved5);
        proposition5_1.setTexteProp("Déploiement en tant que scripts exécutables");
        propositionRepository.save(proposition5_1);

        Proposition proposition5_2 = new Proposition();
        proposition5_2.setQuestion(questionSaved5);
        proposition5_2.setTexteProp("Déploiement sous forme de packages ou de conteneurs");
        propositionRepository.save(proposition5_2);

        Proposition proposition5_3 = new Proposition();
        proposition5_3.setQuestion(questionSaved5);
        proposition5_3.setTexteProp("Déploiement en tant qu'applications web");
        propositionRepository.save(proposition5_3);

        questionList.add(questionSaved5);

        // Enregistrer la liste de questions dans le quiz
        quiz.setQuestions(questionList);
        quizRepository.save(quiz);
    }


    private void addQuestionForAngularQuiz(Quiz quiz){

        List<Question> questionList = new ArrayList<>();

        // Question 1
        Question question1 = new Question();
        question1.setTexteQuestion("Qu'est-ce qu'Angular ?");
        question1.setReponseCorrect("Un framework JavaScript pour la création d'applications web");
        question1.setQuiz(quiz);
        Question questionSaved1 = questionRepository.save(question1);

        Proposition proposition1_1 = new Proposition();
        proposition1_1.setQuestion(questionSaved1);
        proposition1_1.setTexteProp("Un langage de programmation");
        propositionRepository.save(proposition1_1);

        Proposition proposition1_2 = new Proposition();
        proposition1_2.setQuestion(questionSaved1);
        proposition1_2.setTexteProp("Une bibliothèque JavaScript");
        propositionRepository.save(proposition1_2);

        Proposition proposition1_3 = new Proposition();
        proposition1_3.setQuestion(questionSaved1);
        proposition1_3.setTexteProp("Un framework JavaScript pour la création d'applications web");
        propositionRepository.save(proposition1_3);

        questionList.add(questionSaved1);

        // Question 2
        Question question2 = new Question();
        question2.setTexteQuestion("Quel langage est principalement utilisé pour développer des applications avec Angular ?");
        question2.setReponseCorrect("TypeScript");
        question2.setQuiz(quiz);
        Question questionSaved2 = questionRepository.save(question2);

        Proposition proposition2_1 = new Proposition();
        proposition2_1.setQuestion(questionSaved2);
        proposition2_1.setTexteProp("JavaScript");
        propositionRepository.save(proposition2_1);

        Proposition proposition2_2 = new Proposition();
        proposition2_2.setQuestion(questionSaved2);
        proposition2_2.setTexteProp("TypeScript");
        propositionRepository.save(proposition2_2);

        Proposition proposition2_3 = new Proposition();
        proposition2_3.setQuestion(questionSaved2);
        proposition2_3.setTexteProp("Java");
        propositionRepository.save(proposition2_3);

        questionList.add(questionSaved2);

        // Question 3
        Question question3 = new Question();
        question3.setTexteQuestion("Quelle est la fonction principale du système de modules d'Angular ?");
        question3.setReponseCorrect("Organiser et regrouper le code en fonctionnalités cohérentes");
        question3.setQuiz(quiz);
        Question questionSaved3 = questionRepository.save(question3);

        Proposition proposition3_1 = new Proposition();
        proposition3_1.setQuestion(questionSaved3);
        proposition3_1.setTexteProp("Gérer les requêtes HTTP");
        propositionRepository.save(proposition3_1);

        Proposition proposition3_2 = new Proposition();
        proposition3_2.setQuestion(questionSaved3);
        proposition3_2.setTexteProp("Organiser et regrouper le code en fonctionnalités cohérentes");
        propositionRepository.save(proposition3_2);

        Proposition proposition3_3 = new Proposition();
        proposition3_3.setQuestion(questionSaved3);
        proposition3_3.setTexteProp("Créer des animations");
        propositionRepository.save(proposition3_3);

        questionList.add(questionSaved3);

        // Question 4
        Question question4 = new Question();
        question4.setTexteQuestion("Quel est l'outil principal fourni par Angular pour construire des interfaces utilisateur ?");
        question4.setReponseCorrect("Angular CLI (Command Line Interface)");
        question4.setQuiz(quiz);
        Question questionSaved4 = questionRepository.save(question4);

        Proposition proposition4_1 = new Proposition();
        proposition4_1.setQuestion(questionSaved4);
        proposition4_1.setTexteProp("Angular CLI (Command Line Interface)");
        propositionRepository.save(proposition4_1);

        Proposition proposition4_2 = new Proposition();
        proposition4_2.setQuestion(questionSaved4);
        proposition4_2.setTexteProp("Angular Studio");
        propositionRepository.save(proposition4_2);

        Proposition proposition4_3 = new Proposition();
        proposition4_3.setQuestion(questionSaved4);
        proposition4_3.setTexteProp("Angular Inspector");
        propositionRepository.save(proposition4_3);

        questionList.add(questionSaved4);

        // Question 5
        Question question5 = new Question();
        question5.setTexteQuestion("Quelle est la fonctionnalité principale du système de directives dans Angular ?");
        question5.setReponseCorrect("Modifier le comportement ou l'apparence des éléments DOM");
        question5.setQuiz(quiz);
        Question questionSaved5 = questionRepository.save(question5);

        Proposition proposition5_1 = new Proposition();
        proposition5_1.setQuestion(questionSaved5);
        proposition5_1.setTexteProp("Effectuer des requêtes HTTP");
        propositionRepository.save(proposition5_1);

        Proposition proposition5_2 = new Proposition();
        proposition5_2.setQuestion(questionSaved5);
        proposition5_2.setTexteProp("Modifier le comportement ou l'apparence des éléments DOM");
        propositionRepository.save(proposition5_2);

        Proposition proposition5_3 = new Proposition();
        proposition5_3.setQuestion(questionSaved5);
        proposition5_3.setTexteProp("Gérer les données de l'application");
        propositionRepository.save(proposition5_3);

        questionList.add(questionSaved5);

        // Enregistrer la liste de questions dans le quiz
        quiz.setQuestions(questionList);
        quizRepository.save(quiz);
    }


    private void addQuestionForVueQuiz(Quiz quiz){

        List<Question> questionList = new ArrayList<>();

        // Question 1
        Question question1 = new Question();
        question1.setTexteQuestion("Qu'est-ce que Vue.js ?");
        question1.setReponseCorrect("Un framework JavaScript pour la création d'interfaces utilisateur");
        question1.setQuiz(quiz);
        Question questionSaved1 = questionRepository.save(question1);

        Proposition proposition1_1 = new Proposition();
        proposition1_1.setQuestion(questionSaved1);
        proposition1_1.setTexteProp("Un langage de programmation");
        propositionRepository.save(proposition1_1);

        Proposition proposition1_2 = new Proposition();
        proposition1_2.setQuestion(questionSaved1);
        proposition1_2.setTexteProp("Un framework JavaScript pour la création d'interfaces utilisateur");
        propositionRepository.save(proposition1_2);

        Proposition proposition1_3 = new Proposition();
        proposition1_3.setQuestion(questionSaved1);
        proposition1_3.setTexteProp("Une bibliothèque de styles CSS");
        propositionRepository.save(proposition1_3);

        questionList.add(questionSaved1);

        // Question 2
        Question question2 = new Question();
        question2.setTexteQuestion("Quelle est la principale différence entre Vue.js et d'autres frameworks comme React ou Angular ?");
        question2.setReponseCorrect("Vue.js se concentre sur la simplicité et l'approche progressive");
        question2.setQuiz(quiz);
        Question questionSaved2 = questionRepository.save(question2);

        Proposition proposition2_1 = new Proposition();
        proposition2_1.setQuestion(questionSaved2);
        proposition2_1.setTexteProp("Vue.js est basé sur TypeScript, tandis que React et Angular sont basés sur JavaScript");
        propositionRepository.save(proposition2_1);

        Proposition proposition2_2 = new Proposition();
        proposition2_2.setQuestion(questionSaved2);
        proposition2_2.setTexteProp("Vue.js est uniquement utilisé pour les applications mobiles, tandis que React et Angular sont utilisés pour le développement web");
        propositionRepository.save(proposition2_2);

        Proposition proposition2_3 = new Proposition();
        proposition2_3.setQuestion(questionSaved2);
        proposition2_3.setTexteProp("Vue.js se concentre sur la simplicité et l'approche progressive");
        propositionRepository.save(proposition2_3);

        questionList.add(questionSaved2);

        // Question 3
        Question question3 = new Question();
        question3.setTexteQuestion("Quel langage est principalement utilisé avec Vue.js pour le développement d'applications ?");
        question3.setReponseCorrect("JavaScript");
        question3.setQuiz(quiz);
        Question questionSaved3 = questionRepository.save(question3);

        Proposition proposition3_1 = new Proposition();
        proposition3_1.setQuestion(questionSaved3);
        proposition3_1.setTexteProp("HTML");
        propositionRepository.save(proposition3_1);

        Proposition proposition3_2 = new Proposition();
        proposition3_2.setQuestion(questionSaved3);
        proposition3_2.setTexteProp("CSS");
        propositionRepository.save(proposition3_2);

        Proposition proposition3_3 = new Proposition();
        proposition3_3.setQuestion(questionSaved3);
        proposition3_3.setTexteProp("JavaScript");
        propositionRepository.save(proposition3_3);

        questionList.add(questionSaved3);

        // Question 4
        Question question4 = new Question();
        question4.setTexteQuestion("Quelle est la fonction principale du système de composants dans Vue.js ?");
        question4.setReponseCorrect("Réutiliser du code et structurer l'interface utilisateur en composants modulaires");
        question4.setQuiz(quiz);
        Question questionSaved4 = questionRepository.save(question4);

        Proposition proposition4_1 = new Proposition();
        proposition4_1.setQuestion(questionSaved4);
        proposition4_1.setTexteProp("Gérer les requêtes HTTP");
        propositionRepository.save(proposition4_1);

        Proposition proposition4_2 = new Proposition();
        proposition4_2.setQuestion(questionSaved4);
        proposition4_2.setTexteProp("Styliser l'interface utilisateur");
        propositionRepository.save(proposition4_2);

        Proposition proposition4_3 = new Proposition();
        proposition4_3.setQuestion(questionSaved4);
        proposition4_3.setTexteProp("Réutiliser du code et structurer l'interface utilisateur en composants modulaires");
        propositionRepository.save(proposition4_3);

        questionList.add(questionSaved4);

        // Question 5
        Question question5 = new Question();
        question5.setTexteQuestion("Quel est l'outil principal fourni par Vue.js pour construire des interfaces utilisateur ?");
        question5.setReponseCorrect("Vue CLI (Command Line Interface)");
        question5.setQuiz(quiz);
        Question questionSaved5 = questionRepository.save(question5);

        Proposition proposition5_1 = new Proposition();
        proposition5_1.setQuestion(questionSaved5);
        proposition5_1.setTexteProp("Vue CLI (Command Line Interface)");
        propositionRepository.save(proposition5_1);

        Proposition proposition5_2 = new Proposition();
        proposition5_2.setQuestion(questionSaved5);
        proposition5_2.setTexteProp("Vue Studio");
        propositionRepository.save(proposition5_2);

        Proposition proposition5_3 = new Proposition();
        proposition5_3.setQuestion(questionSaved5);
        proposition5_3.setTexteProp("Vue Inspector");
        propositionRepository.save(proposition5_3);

        questionList.add(questionSaved5);

        // Enregistrer la liste de questions dans le quiz
        quiz.setQuestions(questionList);
        quizRepository.save(quiz);
    }


}
