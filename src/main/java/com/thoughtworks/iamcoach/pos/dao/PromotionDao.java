package com.thoughtworks.iamcoach.pos.dao;

import com.thoughtworks.iamcoach.pos.PromotionFactory;
import com.thoughtworks.iamcoach.pos.domain.Promotion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionDao {
    private JdbcTemplate jdbcTemplate;

    public PromotionDao() {}
    public PromotionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Promotion> getItemPromotionList(final int id) {

        String sql = "SELECT promotion.*,promotion_item.discount FROM promotion,promotion_item " +
                "WHERE promotion_item.item_id=? AND promotion.id=promotion_item.promotion_id";
        final List<Promotion> promotionList = new ArrayList<Promotion>();

        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Promotion promotion = PromotionFactory.createPromotion(resultSet.getInt("id"),
                        resultSet.getString("type"), resultSet.getInt("discount"), resultSet.getInt("level"));
                promotionList.add(promotion);
            }
        });

        return promotionList;
    }
}
