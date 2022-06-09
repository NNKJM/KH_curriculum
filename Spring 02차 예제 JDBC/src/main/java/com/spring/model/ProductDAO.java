package com.spring.model;

import java.util.List;

public interface ProductDAO {
	// 추상 메서드
	List<ProductDTO> getProductList();
	int insertProduct(ProductDTO dto);
	ProductDTO getProduct(int num);
	int updateProduct(ProductDTO dto);
	int deleteProduct(int num);
	List<CategoryDTO> categoryList();
	List<ProductDTO> searchProductList(String field, String keyword);
}
