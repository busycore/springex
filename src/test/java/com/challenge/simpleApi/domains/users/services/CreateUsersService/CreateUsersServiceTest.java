package com.challenge.simpleApi.domains.users.services.CreateUsersService;

import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.domains.users.repositories.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.test.annotation.DirtiesContext;

//@SpringBootTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
public class CreateUsersServiceTest {

  @Mock
  private UsersRepository usersRepository;

  @InjectMocks
  private CreateUsersService createUsersService;

  //Validator Instances - Used to validate the annotations inside the entities
  private static ValidatorFactory validatorFactory;
  private static Validator validator;

  @BeforeEach
  void setUp() {}

  @BeforeAll
  static void beforeAll() {
    //Instantiate the validators
    validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  @AfterAll
  static void afterAll() {
    validatorFactory.close();
  }

  @Test
  @DisplayName("Should be able to create a new User")
  void createUserTest() {
    Users userInput = new Users(null, "Jonas", 28,null);
    Users expected = new Users(1L, "Jonas", 28,null);
    Mockito.when(usersRepository.save(userInput)).thenReturn(expected);
    Users actual = createUsersService.execute(userInput);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Should not be able to create a new User without a name")
  void CreateUserWithoutNameTet() {
    Users userInput = new Users(null, null, 18,null);
    Set<ConstraintViolation<Users>> violations = validator.validate(userInput);
    List<String> errors = new ArrayList<String>();

    errors =
      violations.stream().map(x -> x.getMessage()).collect(Collectors.toList());

    Assertions.assertEquals("Name should not be empty", errors.get(0));
    //Assertions.assertThrows(MethodArgumentNotValidException.class,()->createUsersService.execute(userInput));
  }

  @Test
  @DisplayName("Should not be able to create a new under 18yo User")
  void CreateUnder18User() {
    Users userInput = new Users(null, "Paull", 17,null);
    var errors = validator
      .validate(userInput)
      .stream()
      .map(x -> x.getMessage())
      .collect(Collectors.toList());

    Assertions.assertEquals("Should not be less than 18", errors.get(0));
  }
}
