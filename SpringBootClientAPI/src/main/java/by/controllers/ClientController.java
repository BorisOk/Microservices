package by.controllers;

import by.entity.User;
import by.restMethods.RestClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pd")
public class ClientController {

	private User user = new User();
	private RestClient restClient = new RestClient();

	@GetMapping
	public String mainPersonnelDepartment() {
		return "pd";
	}

	@GetMapping("/users")
	public String getUsers(Model model) {
		User[] users = restClient.callGetAllUsersAPI();
		model.addAttribute("users", users);
		return "users";
	}

	@GetMapping("/users/{id}")
	public String getUserById(@PathVariable(value = "id") int userId,
							  Model model) {
		user = restClient.callGetUserByIdAPI(userId);
		model.addAttribute("userById", user);
		return "email";
	}

	@PostMapping("users")
	public String addUser(@RequestParam String firstName,
						  @RequestParam String lastName,
						  @RequestParam String email,
						  Model model) {
		restClient.callCreateUserAPI(new User(firstName,lastName,email));
		model.addAttribute("createGood", "New employee added successfully!");
		User[] users = restClient.callGetAllUsersAPI();
		model.addAttribute("users", users);
		return "users";
	}

	@PostMapping("users/put")
	public String updateUserById(@RequestParam String id,
								 @RequestParam String firstName,
								 @RequestParam String lastName,
								 @RequestParam String email,
								 Model model) {
		restClient.callUpdateUserByIdAPI(Integer.parseInt(id),firstName,lastName,email);
		model.addAttribute("updateGood", "The employee information has been successfully updated!");
		User[] users = restClient.callGetAllUsersAPI();
		model.addAttribute("users", users);
		return "users";
	}

	@PostMapping("users/delete")
	public String deleteUserById(@RequestParam String id,
								 Model model) {
		restClient.callDeleteUserByIdAPI(Integer.parseInt(id));
		model.addAttribute("deleteGood", "The worker was successfully deleted!");
		User[] users = restClient.callGetAllUsersAPI();
		model.addAttribute("users", users);
		return "users";
	}
}
