package com.umg.proyecto.services;

import com.umg.proyecto.models.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeador de filas para convertir las filas de la base de datos en objetos OrderDetail
    private final RowMapper<OrderDetail> orderDetailRowMapper = new RowMapper<OrderDetail>() {
        @Override
        public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(rs.getInt("ORDER_ID"));
            orderDetail.setProductId(rs.getInt("PRODUCT_ID"));
            orderDetail.setQty(rs.getInt("QTY"));
            return orderDetail;
        }
    };

    // Método para obtener todos los detalles de una orden
    public List<OrderDetail> findByOrderId(Integer orderId) {
        String sql = "SELECT * FROM ORDER_DETAIL WHERE ORDER_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{orderId}, orderDetailRowMapper);
    }

    // Método para agregar un detalle de orden
    public void save(OrderDetail orderDetail) {
        String sql = "INSERT INTO ORDER_DETAIL (ORDER_ID, PRODUCT_ID, QTY) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, orderDetail.getOrderId(), orderDetail.getProductId(), orderDetail.getQty());
    }

    // Método para actualizar un detalle de orden
    public void update(OrderDetail orderDetail) {
        String sql = "UPDATE ORDER_DETAIL SET QTY = ? WHERE ORDER_ID = ? AND PRODUCT_ID = ?";
        jdbcTemplate.update(sql, orderDetail.getQty(), orderDetail.getOrderId(), orderDetail.getProductId());
    }

    // Método para eliminar un detalle de orden
    public void delete(Integer orderId, Integer productId) {
        String sql = "DELETE FROM ORDER_DETAIL WHERE ORDER_ID = ? AND PRODUCT_ID = ?";
        jdbcTemplate.update(sql, orderId, productId);
    }
}
