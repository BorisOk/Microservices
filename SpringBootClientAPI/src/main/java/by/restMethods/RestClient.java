package by.restMethods;

import by.entity.User;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestClient {
    private static final String GET_API_USERS_API = "http://localhost:8080/api/users";
    private static final String GET_USER_BY_ID_API = "http://localhost:8080/api/users/{id}";
    private static final String CREATE_USER_API = "http://localhost:8080/api/users";
    private static final String UPDATE_USER_BY_ID_API = "http://localhost:8080/api/users/{id}";
    private static final String DELETE_USER_BY_ID_API = "http://localhost:8080/api/users/{id}";
    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RestClient().callGetAllUsersAPI()));
        System.out.println(new RestClient().callGetUserByIdAPI(1));
        new RestClient().callCreateUserAPI(new User("FirstName3","LastName3","Email3"));
        new RestClient().callUpdateUserByIdAPI(2,"FirstName2","LastName2","Email2");
        new RestClient().callDeleteUserByIdAPI(7);
    }

    public User[] callGetAllUsersAPI() {
        return restTemplate.getForObject(GET_API_USERS_API, User[].class);
    }

    public User callGetUserByIdAPI(int id) {
        Map<String,Integer> param = new HashMap<>();
        param.put("id",id);
        return restTemplate.getForObject(GET_USER_BY_ID_API, User.class, param);
    }

    public void callCreateUserAPI(User user) {
        restTemplate.postForEntity(CREATE_USER_API, user, User.class); //  ResponseEntity<User> userCreate =
    }

    public void callUpdateUserByIdAPI(int id, String firstName, String lastName, String email) {
        Map<String,Integer> param =  new HashMap<>();
        param.put("id",id);
        User user = new User(firstName, lastName, email);
        restTemplate.put(UPDATE_USER_BY_ID_API, user, param);
    }

    public void callDeleteUserByIdAPI(int id) {
        Map<String,Integer> param =  new HashMap<>();
        param.put("id",id);
        restTemplate.delete(DELETE_USER_BY_ID_API, param);
    }
}