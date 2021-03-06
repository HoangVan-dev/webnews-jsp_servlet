package com.hvn.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hvn.dao.GenericDAO;
import com.hvn.mapper.RowMapper;

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
				} else if (parameter instanceof Boolean) {
					pre.setBoolean(index, (boolean) parameter);
				} else if (parameter instanceof Integer) {
					pre.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					pre.setTimestamp(index, (Timestamp) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement pre = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			pre = connection.prepareStatement(sql);
			setParameter(pre, parameters);
			pre.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (pre != null) {
					pre.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

	}

	@Override
	public Long insert(String sql, Object... parameters) {
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement pre = null;
		Long id = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			pre = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameter(pre, parameters);
			pre.executeUpdate();
			resultSet = pre.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
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
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public int count(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			int count =0;
			connection = getConnection();
			pre = connection.prepareStatement(sql);
			setParameter(pre, parameters);
			resultSet = pre.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			return 0;
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
				return 0;
			}
		}
	}

}
