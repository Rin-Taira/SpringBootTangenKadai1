// deleteのエラー処理を少しする必要がある。

package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.compare.ProductCompareByCategory;
import com.example.demo.compare.ProductCompareByDate1;
import com.example.demo.compare.ProductCompareByDate2;
import com.example.demo.compare.ProductCompareById;
import com.example.demo.compare.ProductCompareByPrice1;
import com.example.demo.compare.ProductCompareByPrice2;
import com.example.demo.controller.form.ProductForm;
import com.example.demo.controller.form.UserForm;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import com.example.demo.util.ParamUtil;

@Controller
public class SystemController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    HttpSession session;
    
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
    	
    	List<Product> productList = productService.find();
    	
    	List<Category> categoryList = categoryService.find();

    	session.setAttribute("user", user);
    	session.setAttribute("productList", productList);
    	session.setAttribute("categoryList", categoryList);
    	
    	return "menu";
    }
    
    @RequestMapping(value="/sort", method=RequestMethod.GET)
    public String sort(@RequestParam(name = "sort", defaultValue = "") String sortNum, Model model) {
		
		for (int i = 1; i < 7; i++) {
			session.removeAttribute("s" + i);
		}
		
		List<Product> productList = (List<Product>) session.getAttribute("productList");
		

		switch (sortNum) {
		case "1":
			Collections.sort(productList, new ProductCompareById());
			break;

		case "2":
			Collections.sort(productList, new ProductCompareByCategory());
			break;

		case "3":
			Collections.sort(productList, new ProductCompareByPrice1());
			break;

		case "4":
			Collections.sort(productList, new ProductCompareByPrice2());
			break;

		case "5":
			Collections.sort(productList, new ProductCompareByDate1());
			break;

		case "6":
			Collections.sort(productList, new ProductCompareByDate2());
			break;
		}
		
		session.setAttribute("s" + sortNum, "selected");

		session.setAttribute("productList", productList);
    	
    	return "menu";
    }
    
    
    @RequestMapping(value="/search", method=RequestMethod.GET)
    public String search(@RequestParam(name = "keyword", defaultValue = "") String keyword, Model model) {
    	
    	for (int i = 1; i < 7; i++) {
			session.removeAttribute("s" + i);
		}
    	
    	List<Product> productList = Collections.emptyList();
        
        if (ParamUtil.isNullOrEmpty(keyword)) {
        	productList = productService.find();
        } else {
        	productList = productService.findByKeyword(keyword);
        	if (productList.size() == 0) {
        		productList = productService.find();
        		model.addAttribute("msg", "検索結果がありません");
        	}
        }
        
        session.setAttribute("productList", productList);
    	
    	return "menu";
    }
    
    
    @RequestMapping(value="/detail", method=RequestMethod.GET)
    public String detail(@ModelAttribute("detail") ProductForm form, @RequestParam(name = "id", defaultValue = "") String id, Model model) {
    	
    	Product product = productService.findById(id);
    	
    	session.setAttribute("currentId", product.getProductId());
    	
    	model.addAttribute("product", product);
    	
    	return "detail";
    	
    }
    
    @RequestMapping("/return")
    public String top(@ModelAttribute("login") UserForm form, Model model) {
    	return "menu";
    }
    
    @RequestMapping("/updateInput")
    public String updateInput(@ModelAttribute("update") ProductForm form, Model model) {
    	List<Category> categoryList = categoryService.find();
    	model.addAttribute("categoryList", categoryList);
    	return "updateInput";
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public String delete(@RequestParam(name = "id", defaultValue = "") String id, Model model) {
    	
    	int result = productService.deleteById(id);
    	
    	if (result == -1) {
			model.addAttribute("msg", "削除に失敗しました。");
			return "detail";
		}

		model.addAttribute("msg", "削除に成功しました。");
		
		List<Product> productList = productService.find();

		session.setAttribute("productList", productList);
		
		return "menu";
	
    }
    
    @RequestMapping(value="/update", method=RequestMethod.GET)
    public String update(@RequestParam(name = "category", defaultValue = "") Integer id, @Validated @ModelAttribute("product") ProductForm productForm, BindingResult bindingResult, Model model) {
    	
    	if (bindingResult.hasErrors()) {
    		System.out.println("バリデーションエラーがあるねぇ");
    		return "updateInput";
    	}
    	
    	int result = productService.updateById(productForm.getProductId(), productForm.getName(), productForm.getPrice(), id, productForm.getDescription(), (Integer) session.getAttribute("currentId"));
    	
    	if (result == -1) {
    		model.addAttribute("msg", "更新時にエラーが発生しました");
    		return "updateInput";
    	}
    	
    	model.addAttribute("msg", "更新処理が完了しました。");
    	
    	List<Product> productList = productService.find();

    	session.setAttribute("productList", productList);
    	
    	return "menu";
	
    }
    
    
}

