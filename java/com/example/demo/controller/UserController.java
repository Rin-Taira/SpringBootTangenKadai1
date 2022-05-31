package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.form.UserForm;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;

    @RequestMapping("/index")
    public String index(@ModelAttribute("login") UserForm form, Model model) {

        return "index";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@Validated @ModelAttribute("login") UserForm form, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
    		return "index";
    	}
    	User user = userService.authentication(form.getLoginId(), form.getPassword());
    	if (user == null) {
    		model.addAttribute("msg1", "IDまたはパスワードが不正です");
    		return "index";
    	}
    	model.addAttribute("user", user);
    	
    	List<Product> productList = productService.find();
    	model.addAttribute("productList", productList);
    	
    	return "menu";
    }
    
//    @RequestMapping(value="/login", method=RequestMethod.POST)
//    public String login(@Validated @ModelAttribute("login") UserForm form, Model model) {
//    	
//    	return "menu";
//    }
}

