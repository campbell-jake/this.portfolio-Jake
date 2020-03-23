package com.techelevator.model.login;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.register.Register;

@Component
public class JdbcLoginDao implements LoginDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcLoginDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void recordLoginDate(Register theUser) {
		
		String sqlRecordLogin = "insert into logins(userid) " +
											"values((select userid from users where username = ?))";
            
			jdbcTemplate.update(sqlRecordLogin, theUser.getUsername());
		
	}

	@Override
	public Login retrieveLastLoginDate(Register theUser) {
		
		String sqlRetrieveLastLoginDate = "select * " +
										  "from logins " +
										  "where userid = ? " +
										  "order by login_date desc " +
										  "limit 1 offset 1";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlRetrieveLastLoginDate, theUser.getUserId());
		
		Login lastLogin = new Login();
		if(results.next()) {
			lastLogin.setLoginDate(results.getTimestamp("login_date").toLocalDateTime());
		}
		
		return lastLogin;
		
	}

}
