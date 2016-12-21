package com.aniket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.aniket.transferObject.User;

public class HealthCheck {
	@Autowired
	private User user;
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public User getUser() {
		User retrievedUser = jdbcTemplateObject.queryForObject(DaoQueryConstant.GET_ALL_DATA, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            	user.setId(rs.getInt("ID"));
            	user.setName(rs.getString("NAME"));
                return user;
            }
        });
		return retrievedUser;
	}
}
