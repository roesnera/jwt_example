package edu.generation.jfs_jwt_demo.controller;

import edu.generation.jfs_jwt_demo.controller.dto.LoginDto;
import edu.generation.jfs_jwt_demo.controller.dto.Token;
import edu.generation.jfs_jwt_demo.model.Course;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CourseControllerTest {
    private static final String url = "http://localhost:8080";
    private static String accessToken;
    private static final String AUTHENTICATION_URL = url + "/auth";
    private static final String FETCH_COURSE_URL = url + "/api/courses";

    // private final TestRestTemplate restTemplate;
    //
    // @Autowired
    // public CourseControllerTest(TestRestTemplate restTemplate) {
    //     this.restTemplate = restTemplate;
    // }

    @Test
    void testTokenAndCourses() {
        TestRestTemplate restTemplate= new TestRestTemplate();
        ResponseEntity<Token> token = restTemplate.postForEntity(AUTHENTICATION_URL,
                new LoginDto("user@mail.com", "password"), Token.class);
        assertNotNull(token);
        assertEquals(Response.SC_OK, token.getStatusCodeValue());
        accessToken = String.valueOf(token.getBody());
        System.out.printf("Token=%s\n", accessToken);
    // }
    //
    // @Test
    // void nameCourses() {
    //     TestRestTemplate restTemplate = new TestRestTemplate();
    //     authorization.put("Authorization", "Bearer " + accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<Course[]> response =
                restTemplate.exchange(FETCH_COURSE_URL, HttpMethod.GET, jwtEntity, Course[].class);
                // restTemplate.getForEntity(FETCH_COURSE_URL, Course[].class, jwtEntity);
                // restTemplate.getForObject(FETCH_COURSE_URL, Course[].class, jwtEntity);
        assertNotNull(response);
        assertEquals(Response.SC_OK, response.getStatusCodeValue());
        System.out.println(Arrays.toString(response.getBody()));
        // System.out.println(Arrays.toString(response));
    }
}
