package com.umg.proyecto.services;

import com.umg.proyecto.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeador de filas para convertir las filas de la base de datos en objetos Customer
    private final RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("ID"));
            customer.setNames(rs.getString("NAMES"));
            customer.setLastNames(rs.getString("LAST_NAMES"));
            customer.setPhoneNumber(rs.getString("PHONE_NUMBER"));
            customer.setAddress(rs.getString("ADDRESS"));
            customer.setStatus(rs.getString("STATUS").charAt(0));
            customer.setEmail(rs.getString("EMAIL"));
            customer.setPassword(rs.getString("PASSWORD"));
            return customer;
        }
    };

    // Método para obtener todos los clientes
    public List<Customer> findAll() {
        String sql = "SELECT * FROM CUSTOMER";
        return jdbcTemplate.query(sql, customerRowMapper);
    }

    // Método para obtener un cliente por ID
    public Customer findById(Integer id) {
        String sql = "SELECT * FROM CUSTOMER WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, customerRowMapper);
    }

    // Método para guardar un nuevo cliente
    public void save(Customer customer) {
        String sql = "INSERT INTO CUSTOMER (ID, NAMES, LAST_NAMES, PHONE_NUMBER, ADDRESS, STATUS, EMAIL, PASSWORD) " +
                "VALUES (CUSTOMER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getNames(), customer.getLastNames(), customer.getPhoneNumber(),
                customer.getAddress(), String.valueOf(customer.getStatus()), customer.getEmail(),
                customer.getPassword());
    }

    // Método para actualizar un cliente existente
    public void update(Customer customer) {
        String sql = "UPDATE CUSTOMER SET NAMES = ?, LAST_NAMES = ?, PHONE_NUMBER = ?, ADDRESS = ?, STATUS = ?, EMAIL = ?, PASSWORD = ? WHERE ID = ?";
        jdbcTemplate.update(sql, customer.getNames(), customer.getLastNames(), customer.getPhoneNumber(),
                customer.getAddress(), String.valueOf(customer.getStatus()), customer.getEmail(),
                customer.getPassword(), customer.getId());
    }

    // Método para eliminar un cliente por ID
    public void delete(Integer id) {
        String sql = "DELETE FROM CUSTOMER WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }
}

