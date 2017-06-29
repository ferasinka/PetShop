package com.ferasinka.petshop.service;

import com.ferasinka.petshop.mapper.WishlistMapper;
import com.ferasinka.petshop.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("wishlistService")
public class WishlistServiceImpl implements WishlistService {
	private WishlistMapper wishlistMapper;
	
	@Autowired
	public void setWishlistMapper(WishlistMapper wishlistMapper) {
		this.wishlistMapper = wishlistMapper;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Wishlist> findAll() {
		return wishlistMapper.findAll();
	}
	
	@Override
	public int insert(Wishlist wishlist) {
		return wishlistMapper.insert(wishlist);
	}
	
	@Override
	public void update(Wishlist wishlist) {
		wishlistMapper.update(wishlist);
	}
	
	@Override
	public void delete(Integer id) {
		wishlistMapper.delete(id);
	}
}
