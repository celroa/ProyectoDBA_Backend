package com.umg.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryProductService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addProductToCategory(Integer productId, Integer categoryId) {
        String sql = "INSERT INTO CATEGORY_PRODUCT (CATEGORY_ID, PRODUCT_ID) VALUES (?, ?)";
        jdbcTemplate.update(sql, categoryId, productId);
    }

    public void removeProductFromCategory(Integer productId, Integer categoryId) {
        String sql = "DELETE FROM CATEGORY_PRODUCT WHERE CATEGORY_ID = ? AND PRODUCT_ID = ?";
        jdbcTemplate.update(sql, categoryId, productId);
    }

    public List<Integer> getProductsByCategory(Integer categoryId) {
        String sql = "SELECT PRODUCT_ID FROM CATEGORY_PRODUCT WHERE CATEGORY_ID = ?";
        return jdbcTemplate.queryForList(sql, new Object[]{categoryId}, Integer.class);
    }

    public List<Integer> getCategoriesByProduct(Integer productId) {
        String sql = "SELECT CATEGORY_ID FROM CATEGORY_PRODUCT WHERE PRODUCT_ID = ?";
        return jdbcTemplate.queryForList(sql, new Object[]{productId}, Integer.class);
    }
}
