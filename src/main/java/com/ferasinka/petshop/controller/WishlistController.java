package com.ferasinka.petshop.controller;

import com.ferasinka.petshop.model.Wishlist;
import com.ferasinka.petshop.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/wishlist")
public class WishlistController {
	private WishlistService wishlistService;
	
	@Autowired
	public void setWishlistService(WishlistService wishlistService) {
		this.wishlistService = wishlistService;
	}
	
	@ResponseBody
	@RequestMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Wishlist> listAll() {
		return wishlistService.findAll();
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int add(@RequestBody Wishlist wishlist) {
		return wishlistService.insert(wishlist);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Wishlist wishlist) {
		wishlistService.update(wishlist);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		wishlistService.delete(id);
	}
}
