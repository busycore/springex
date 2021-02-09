package com.challenge.simpleApi.domains.users.services.getAllUsersService;

import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.domains.users.repositories.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
public class GetAllUsersServiceTest {

  //Here we define UsersRepository as a mockable class
  @Mock
  private UsersRepository usersRepository;

  //Here we instantiate the getAllUsersService and inject the mock in it, in this case we are injecting the usersRepository
  //We mocked earlies
  @InjectMocks
  private GetAllUsersService getAllUsersService;

  private List<Users> fakeRepositoryReturn = new ArrayList<Users>();
  final Users user1 = new Users(1L, "Jonas", 28,null);
  final Users user2 = new Users(2L, "Pedro", 20,null);
  final Users user3 = new Users(3L, "Patricia", 21,null);

  @BeforeEach
  void setUp() {
    fakeRepositoryReturn.add(user1);
    fakeRepositoryReturn.add(user2);
    fakeRepositoryReturn.add(user3);
  }

  @Test
  @DisplayName("Should be able to return all Users")
  void ShouldReturnAllUsers() {
    Mockito.when(usersRepository.findAll()).thenReturn(fakeRepositoryReturn);
    List<Users> actual = getAllUsersService.execute();
    Assertions.assertEquals(fakeRepositoryReturn, actual);
  }
}
