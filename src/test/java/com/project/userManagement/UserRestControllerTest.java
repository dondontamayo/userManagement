package com.project.userManagement;

import com.project.userManagement.data.entity.User;
import com.project.userManagement.data.repository.UserPagingRepository;
import com.project.userManagement.rest.UserRestController;
import com.project.userManagement.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRestControllerTest {

    @Autowired
	private TestRestTemplate testRestTemplate;

    @Autowired
	private UserRestController userRestController;

    @Autowired
    private UserService userService;

    @Autowired
    private UserPagingRepository userPagingRepository;

    private User sampleUser1;
    private User sampleUser2;
    private User sampleUser3;
    private String responseString;

	@Before
	public void setup() {

	    // user for new user creation
		sampleUser1 = new User();
        sampleUser1.setName("John");
        sampleUser1.setPassword("John1");
        sampleUser1.setUserName("userJohn");
        sampleUser1.setEmail("john@yahoo.com");

        // user for updated user
        sampleUser2 = new User();
        sampleUser2.setName("Johny");
        sampleUser2.setPassword("Johny1");
        sampleUser2.setUserName("userJohny");
        sampleUser2.setEmail("johny@yahoo.com");

        // additional user
        sampleUser3 = new User();
        sampleUser3.setName("Jake");
        sampleUser3.setPassword("Jake");
        sampleUser3.setUserName("userJake");
        sampleUser3.setEmail("jake@yahoo.com");

        responseString = "{\"content\":[{\"id\":2,\"userName\":\"userJohn\",\"password\":\"John1\",\"email\":\"john@yahoo.com\",\"name\":\"John\"}";

	}

	@Test
    public void whenCreateUser_thenCheckForUserAdded() {

        testRestTemplate.put("/users", sampleUser1);
        ResponseEntity<User> response = testRestTemplate.getForEntity("/users/1", User.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertEquals(sampleUser1.getName(), response.getBody().getName());
        assertEquals(sampleUser1.getEmail(), response.getBody().getEmail());
        assertEquals(sampleUser1.getPassword(), response.getBody().getPassword());
        assertEquals(sampleUser1.getUserName(), response.getBody().getUserName());

    }

    @Test
    public void whenUpdateandDeleteUser_thenCheckForUpdatedandDeletedUser() {

        testRestTemplate.postForEntity("/users/1", sampleUser2, User.class);
        ResponseEntity<User> response = testRestTemplate.getForEntity("/users/1", User.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertEquals(sampleUser2.getName(), response.getBody().getName());
        assertEquals(sampleUser2.getEmail(), response.getBody().getEmail());
        assertEquals(sampleUser2.getPassword(), response.getBody().getPassword());
        assertEquals(sampleUser2.getUserName(), response.getBody().getUserName());

        testRestTemplate.delete("/users/1");
        ResponseEntity<User> response1 = testRestTemplate.getForEntity("/users/1", User.class);

        assertThat(response1.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    public void whenUsersAdded_thenCheckForAllUsers() {
        testRestTemplate.put("/users", sampleUser1);
        testRestTemplate.put("/users", sampleUser2);
        testRestTemplate.put("/users", sampleUser3);

        ResponseEntity<String> response = testRestTemplate.getForEntity("/users?&page=0&size=2", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertEquals(responseString, response.getBody().toString().substring(0,100));
    }
}
