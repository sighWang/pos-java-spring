package com.thoughtworks.iamcoach.pos.dao;

import com.thoughtworks.iamcoach.pos.domain.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.*;

public class ItemDao {

    private JdbcTemplate jdbcTemplate;
    public ItemDao() {}
    public ItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Item getItemById(final int id) {

        String sql = "SELECT * FROM item WHERE id=?";
        final Item item = new Item();

        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                item.setId(id);
                item.setBarcode(resultSet.getString("barcode"));
                item.setName(resultSet.getString("name"));
                item.setUnit(resultSet.getString("unit"));
                item.setPrice(resultSet.getDouble("price"));
                item.setPrice(resultSet.getDouble("price"));
                item.setCategory(resultSet.getString("category"));
            }
        });

        return item;
    }

    public Item getItemByBarcode(final String barcode) {

        String sql = "SELECT * FROM item WHERE barcode=?";
        final Item item = new Item();

        jdbcTemplate.query(sql, new Object[]{barcode}, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                item.setId(resultSet.getInt("id"));
                item.setBarcode(barcode);
                item.setName(resultSet.getString("name"));
                item.setUnit(resultSet.getString("unit"));
                item.setPrice(resultSet.getDouble("price"));
                item.setPrice(resultSet.getDouble("price"));
                item.setCategory(resultSet.getString("category"));
            }
        });

        return item;
    }
}
