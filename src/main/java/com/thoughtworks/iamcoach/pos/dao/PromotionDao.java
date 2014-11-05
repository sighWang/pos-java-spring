package com.thoughtworks.iamcoach.pos.dao;

import com.thoughtworks.iamcoach.pos.PromotionFactory;
import com.thoughtworks.iamcoach.pos.util.DatabaseUtil;
import com.thoughtworks.iamcoach.pos.domain.Promotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionDao {

    Connection connection = null;

    public List<Promotion> getItemPromotionList(int id) {
        List<Promotion> promotionList = new ArrayList<Promotion>();

        String sql = "SELECT promotion.*,promotion_item.discount FROM promotion,promotion_item " +
                "WHERE promotion_item.item_id=? AND promotion.id=promotion_item.promotion_id";

        try {
            connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Promotion promotion = PromotionFactory.createPromotion(resultSet.getInt("id"),
                        resultSet.getString("type"), resultSet.getInt("discount"), resultSet.getInt("level"));
                promotionList.add(promotion);
            }

            resultSet.close();
            preparedStatement.close();
            DatabaseUtil.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotionList;
    }
}
