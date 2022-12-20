package com.smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.Dao.UserRepository;
import com.smart.Entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	
@Autowired
private UserRepository userRepository;

@RequestMapping("/home")
 public String home(Model model) {
	model.addAttribute("title","Home - Smart Contact Manager");
	 return "home";
 }

@RequestMapping("/about")
public String about(Model model) {
	model.addAttribute("title","About - Smart Contact Manager");
	 return "about";
}


@RequestMapping("/signup")
public String signup(Model model) {
	model.addAttribute("title","Register - Smart Contact Manager");
	model.addAttribute("user", new User());
	 return "signup";
}

@RequestMapping(value ="/do_register", method=RequestMethod.POST)
public String registerUser(@ModelAttribute("user") User user,@RequestParam(value = "agreement",defaultValue = "false")boolean agreement,Model model,HttpSession session) {
	
	try {
		if(!agreement) {
			 throw new Exception("You have not agred to terms and condition"); 
		}
		user.setRole("ROLE_USER");
		user.setEnable(true);
		user.setImageUrl("default.png");
		
		System.out.println("Agreement"+ agreement);
		System.out.println("USER"+user);
		
		User result = this.userRepository.save(user);
		model.addAttribute("user",new User());
		
		session.setAttribute("message", new Message(" User Successfully Registered" ,"alert-success"));
		return "signup";
		
	}
	catch(Exception e) {
		e.printStackTrace();
		model.addAttribute("user",user);
		session.setAttribute("message", new Message("Something went wrong!!" + e.getMessage(),"alert-danger"));
		return "signup";
	}
}

}
