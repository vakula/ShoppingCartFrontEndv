package com.niit.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.model.Product;
//import com.niit.shoppingcart.model.UserDetails;


@Controller
public class HomeController
{
@Autowired
ProductDAO productDAO;
@Autowired
UserDAO us;
@RequestMapping("/")
public ModelAndView display(){
	ModelAndView m1=new ModelAndView("Home");
	return m1;
}
@RequestMapping("/login")
public ModelAndView display2(){
	ModelAndView m2=new ModelAndView("login");
	return m2;
}
@RequestMapping("/register")
public ModelAndView display3(){
	ModelAndView m3=new ModelAndView("register");
	return m3;
}
public ModelAndView display4()
{
	ModelAndView m4=new ModelAndView("addwatch");
	return m4;
}


@ModelAttribute("Product")
public  Product addwatch()
{
	return new Product();
}
@RequestMapping("/hai")
public String display5()
{
	return "Home";
}
@RequestMapping("/storewatch")
public String addChairs(HttpServletRequest request,@Valid @ModelAttribute("Product")Product product,BindingResult result)
       {
 System.out.println("hello niit...........................");
    	if(result.hasErrors())
    	{
    		return "addwatch";
    	}
    	String filename=product.getImage();
    	product.setImage(filename);
    	
    	try{
    		byte[] bytes=new byte[product.getImg().getInputStream().available()];
    		product.getImg().getInputStream().read(bytes);
    		BufferedInputStream buffer=new BufferedInputStream(product.getImg().getInputStream());
    		MultipartFile file=product.getImg();
    		String path=request.getServletContext().getRealPath("/")+"resources/images";
    		File rootPath=new File(path);
    		if(!rootPath.exists())
    			rootPath.mkdirs();
    		File store=new File(rootPath.getAbsolutePath()+"/"+filename);
    		System.out.println("Image path :"+path);
    		OutputStream os=new FileOutputStream(store);
    		os.write(bytes);;
    	}
    	catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	//productDAO.saveOrUpdate(product);
    	//return "ViewAll";
    	return "retrive";
    }
/*@ModelAttribute("UserDetails")
public UserDetails createuser(){
	return new UserDetails();
}
@RequestMapping(value = "/storeUser", method = RequestMethod.POST)
public String addUser(@Valid @ModelAttribute("userDetails")UserDetails userDetails,BindingResult result, Model model){
   	
	if(result.hasErrors()) {
		
		return "register";
	}
	
	System.out.println("hello storeUser");
	System.out.println(userDetails.getName()+ "hello @@@@@@");
	//us.saveOrUpdate(userDetails);
	return "Home";
   }
*/
}


