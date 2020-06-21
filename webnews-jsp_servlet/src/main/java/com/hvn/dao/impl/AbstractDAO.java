package com.hvn.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hvn.dao.GenericDAO;
import com.hvn.mapper.RowMapper;
import com.hvn.model.NewModel;

public class AbstractDAO<T> implements GenericDAO<T> {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/news_web";
			String user = "root";
			String password = "230698";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}

	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			pre = connection.prepareStatement(sql);
			setParameter(pre, parameters);
			resultSet = pre.executeQuery();
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (pre != null) {
					pre.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}

	private void setParameter(PreparedStatement pre, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					pre.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					pre.setString(index, (String) parameter);
				}else if (parameter instanceof Boolean) {
					pre.setBoolean(index, (boolean) parameter);
				}else if (parameter instanceof Integer) {
					pre.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					pre.setTimestamp(index, (Timestamp) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
