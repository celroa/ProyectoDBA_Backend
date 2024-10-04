package com.umg.proyecto.services;

import com.umg.proyecto.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productRowMapper = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("ID"));
            product.setName(rs.getString("NAME"));
            product.setPrice(rs.getFloat("PRICE"));
            product.setDescription(rs.getString("DESCRIPTION"));
            product.setStock(rs.getInt("STOCK"));
            product.setImage(rs.getString("IMAGE"));
            product.setBrandId(rs.getInt("BRAND_ID"));
            return product;
        }
    };

    // Método para obtener todos los productos
    public List<Product> findAll() {
        String sql = "SELECT * FROM PRODUCT";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    // Método para obtener un producto por ID
    public Product findById(Integer id) {
        String sql = "SELECT * FROM PRODUCT WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, productRowMapper);
    }

    // Método para guardar un nuevo producto
    public void save(Product product) {
        String sql = "INSERT INTO PRODUCT (ID, NAME, PRICE, DESCRIPTION, STOCK, IMAGE, BRAND_ID) " +
                "VALUES (PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getDescription(), product.getStock(), product.getImage(), product.getBrandId());
    }

    // Método para actualizar un producto existente
    public void update(Product product) {
        String sql = "UPDATE PRODUCT SET NAME = ?, PRICE = ?, DESCRIPTION = ?, STOCK = ?, IMAGE = ?, BRAND_ID = ? WHERE ID = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getDescription(), product.getStock(), product.getImage(), product.getBrandId(), product.getId());
    }

    // Método para eliminar un producto por ID
    public void delete(Integer id) {
        String sql = "DELETE FROM PRODUCT WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }
}
