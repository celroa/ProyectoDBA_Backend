package com.umg.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonProductService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para vincular un producto con una temporada (INSERT)
    public void addProductToSeason(Integer productId, Integer seasonId) {
        String sql = "INSERT INTO SEASON_PRODUCT (PRODUCT_ID, SEASON_ID) VALUES (?, ?)";
        jdbcTemplate.update(sql, productId, seasonId);
    }

    // Método para eliminar la vinculación de un producto con una temporada (DELETE)
    public void removeProductFromSeason(Integer productId, Integer seasonId) {
        String sql = "DELETE FROM SEASON_PRODUCT WHERE PRODUCT_ID = ? AND SEASON_ID = ?";
        jdbcTemplate.update(sql, productId, seasonId);
    }

    // Método para obtener todos los productos vinculados a una temporada
    public List<Integer> getProductsBySeason(Integer seasonId) {
        String sql = "SELECT PRODUCT_ID FROM SEASON_PRODUCT WHERE SEASON_ID = ?";
        return jdbcTemplate.queryForList(sql, new Object[]{seasonId}, Integer.class);
    }

    // Método para obtener todas las temporadas a las que está vinculado un producto
    public List<Integer> getSeasonsByProduct(Integer productId) {
        String sql = "SELECT SEASON_ID FROM SEASON_PRODUCT WHERE PRODUCT_ID = ?";
        return jdbcTemplate.queryForList(sql, new Object[]{productId}, Integer.class);
    }
}
