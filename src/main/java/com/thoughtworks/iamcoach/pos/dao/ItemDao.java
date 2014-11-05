package com.thoughtworks.iamcoach.pos.dao;

import com.thoughtworks.iamcoach.pos.util.DatabaseUtil;
import com.thoughtworks.iamcoach.pos.domain.Item;

import java.sql.*;

public class ItemDao {
    private Connection connection = null;

    public Item getItemById(int id) {
        Item item = null;
        String sql = "SELECT * FROM item WHERE id=?";

        try {
            connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            item = new Item(resultSet.getInt("id"), resultSet.getString("barcode"),
                    resultSet.getString("name"), resultSet.getString("unit"),
                    resultSet.getDouble("price"), resultSet.getString("category"));

            preparedStatement.close();
            resultSet.close();
            DatabaseUtil.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public Item getItemByBarcode(String barcode) {
        Item item = null;
        String sql = "SELECT * FROM item WHERE barcode=?";

        try {
            connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, barcode);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            item = new Item(resultSet.getInt("id"), resultSet.getString("barcode"),
                    resultSet.getString("name"), resultSet.getString("unit"),
                    resultSet.getDouble("price"), resultSet.getString("category"));

            resultSet.close();
            preparedStatement.close();
            DatabaseUtil.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

}
