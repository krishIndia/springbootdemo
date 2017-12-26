package com.mfg.demo.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mfg.demo.model.User;
import com.mfg.demo.repository.UserRepository;
import com.mfg.demo.service.SecurityService;
import com.mfg.demo.service.UserService;
import com.mfg.demo.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	/**
	 * 
	 * @param model
	 * @return registration page
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}
	/**
	 * Register and save user data
	 * 
	 * @param userForm
	 * @param bindingResult
	 * @param model
	 * @return welcome page
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userService.save(userForm);
		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
		//Redirect to dashboard if welcome page not needed
		return "redirect:/welcome";
	}

	/**
	 * Delete user by id
	 * 
	 * @param model
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteUser(@PathVariable("id") long id, User user, Model model) {
		user = userService.findById(id);
		userRepository.delete(user.getId());
		return new ModelAndView("redirect:/dashboard");
	}


	/**
	 * @param id
	 * @param userForm:@ModelAttribute bind form value
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateUser(@PathVariable("id") long id, @ModelAttribute("userForm") User userForm,
			BindingResult bindingResult, Model model) {
		
	//validator.prop // opening below causes validation fails
		//model.addAttribute("userForm", userService.findById(id));
		User currentUser = userService.findById(id);
		if ((userForm.getUsername()) != null) {
			//Validate details before saving in DB
			userValidator.validate(userForm, bindingResult);
			currentUser.setUsername(userForm.getUsername());
			currentUser.setEmail(userForm.getEmail());
			currentUser.setMobile(userForm.getMobile());
			currentUser.setAddress(userForm.getAddress());
			currentUser.setPassword(userForm.getPassword());
			currentUser.setPasswordConfirm(userForm.getPasswordConfirm());
			
			if(bindingResult.hasErrors()) {
				return ("edit");
			} else {
			//update the user details
			userService.save(currentUser);
			return ("redirect:/dashboard"); 
			}
		}
		return ("edit");
	}

	/**
	 * 
	 * @param model
	 * @param error
	 * @param logout
	 * @return redirect to login page after validation
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		
		if (error != null)
			model.addAttribute("error", " username or password is invalid.");
		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		return "login";
	}

	/**
	 * In case welcome page not needed then need to close this method and use dashboard as root like { "/", "/dashboard" } next method
	 * @param model
	 * @return redirect to welcome page
	 */
	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "welcome";
	}

	/**
	 * Display all the users on dashboard
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView model = new ModelAndView();
		model.addObject("users", userService.getUsers());
		model.setViewName("dashboard");
		return model;
	}

	/**
	 * user page
	 * 
	 * @return user page
	 */
	@RequestMapping(value = { "/user" }, method = RequestMethod.GET)
	public ModelAndView userPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("user");
		return model;
	}

	/**
	 * Admin page
	 * 
	 * @return admin page
	 */
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin");
		return model;
	}

	/**
	 * unauthorization page
	 * 
	 * @return 404 page
	 */
	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public ModelAndView accessDeniedPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("accessdenied");
		return model;
	}
}
