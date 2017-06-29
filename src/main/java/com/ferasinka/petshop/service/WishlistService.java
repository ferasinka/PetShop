package com.ferasinka.petshop.service;

import com.ferasinka.petshop.model.Wishlist;

import java.util.List;

public interface WishlistService {
	List<Wishlist> findAll();
	
	int insert(Wishlist wishlist);
	
	void update(Wishlist wishlist);
	
	void delete(Integer id);
}
