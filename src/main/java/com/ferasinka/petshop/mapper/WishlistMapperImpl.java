package com.ferasinka.petshop.mapper;

import com.ferasinka.petshop.model.Wishlist;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("wishlistMapper")
public class WishlistMapperImpl implements WishlistMapper {
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public List<Wishlist> findAll() {
		SqlSession session = sqlSessionFactory.openSession();
		
		List<Wishlist> result = session.selectList("com.ferasinka.petshop.model.Wishlist.findAllWishlist");
		session.commit();
		session.close();
		
		return result;
	}
	
	@Override
	public int insert(Wishlist wishlist) {
		SqlSession session = sqlSessionFactory.openSession();
		
		session.insert("com.ferasinka.petshop.model.Wishlist.insertWishlist", wishlist);
		session.commit();
		session.close();
		
		return wishlist.getId();
	}
	
	@Override
	public void update(Wishlist wishlist) {
		SqlSession session = sqlSessionFactory.openSession();
		
		int id = session.update("com.ferasinka.petshop.model.Wishlist.updateWishlist", wishlist);
		session.commit();
		session.close();
	}
	
	@Override
	public void delete(Integer id) {
		SqlSession session = sqlSessionFactory.openSession();
		
		session.delete("com.ferasinka.petshop.model.Wishlist.deleteWishlist", id);
		session.commit();
		session.close();
	}
}
