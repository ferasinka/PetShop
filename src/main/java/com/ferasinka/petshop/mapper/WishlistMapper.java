package com.ferasinka.petshop.mapper;

import com.ferasinka.petshop.model.Wishlist;

import java.util.List;

public interface WishlistMapper {
	List<Wishlist> findAll();
	
	int insert(Wishlist wishlist);
	
	void update(Wishlist wishlist);
	
	void delete(Integer id);
}
