/**
 * 
 */
package com.cuas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.cuas.model.Dealer;

/**
 * @author Cuneyt AŞKIN
 * Data Access Layer: 
 * Connect to DB directly
 * 1-void createDealerTableAndDatabase()	: Create new Database and Table
 * 2-void saveDealer()						: save data to db
 * 3-Dealer fingDealerById(Dealer dealer)	: get data by id form db
 */

@Repository
public class DealerDaoImp implements DealerDao {
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParametreJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParametreJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
	}

	// Create Table in DB directly
	public void createDealerTableAndDatabase() {	
		
		jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS testdb CHARACTER SET utf8 COLLATE utf8_turkish_ci");	
		jdbcTemplate.execute("SET NAMES 'latin5'");
		System.out.println("Sout>>>Successfully Create testdb");
		String sqlCreateDealerSchema = "CREATE TABLE IF NOT EXISTS `testdb`.`dealer` ("
				+ "`identity` VARCHAR(11) NOT NULL , "
				+ "`first_name` VARCHAR(45) NOT NULL , "
				+ "`last_name` VARCHAR(45) NOT NULL , "
				+ "`phone_number` VARCHAR(11) NOT NULL , "
				+ "`email` TEXT NOT NULL , "
				+ "`birth_of_date` TEXT NOT NULL , "
				+ "`adress` VARCHAR(150) NOT NULL , "
				+ "`is_retail_sale` BOOLEAN NOT NULL , "
				+ "`why_choose` TEXT NOT NULL , "
				+ "`which_region` VARCHAR(100) NOT NULL , "
				+ "`investment_amount` DOUBLE NOT NULL , "
				+ "`what_else_want` TEXT NOT NULL,"
				+ " PRIMARY KEY (`identity`) ) ENGINE = InnoDB";
		
		jdbcTemplate.execute(sqlCreateDealerSchema);		
	}

	// Save data DB directly
	public void saveDealerDB(Dealer dealer) {
		String sql = "INSERT INTO `testdb`.`dealer` ("
				+ "identity,"
				+ "first_name,"
				+ "last_name,"
				+ "phone_number,"
				+ "email,"
				+ "birth_of_date,adress,"
				+ "is_retail_sale,"
				+ "why_choose,"
				+ "which_region,"
				+ "investment_amount,"
				+ "what_else_want)"
				+ "VALUES("
				+ ":identity,"
				+ ":first_name,"
				+ ":last_name,"
				+ ":phone_number,"
				+ ":email,"
				+ ":birth_of_date,"
				+ ":adress,"
				+ ":is_retail_sale,"
				+ ":why_choose,"
				+ ":which_region,"
				+ ":investment_amount,"
				+ ":what_else_want)";
		namedParametreJdbcTemplate.update(sql, getSqlParameterByModel(dealer));			
	}

	// Read data DB directly
	public Dealer findDealerById(String identity) {		
		String sql = "SELECT * FROM `testdb`.`dealer` WHERE identity=" + identity;
		// Notice the use of ResultSetExtractor to extract a single row as a POJO.
		return jdbcTemplate.query(sql, new ResultSetExtractor<Dealer>() {
			// ResultSetExtractor to extract a single row as a POJO.
			public Dealer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Dealer dealer = new Dealer();
					dealer.setIdentity(rs.getString("identity"));
					dealer.setFirstName(rs.getString("first_name"));
					dealer.setLastName(rs.getString("last_name"));
					dealer.setPhoneNumber(rs.getString("phone_number"));
					dealer.setEmail(rs.getString("email"));
					dealer.setBirthOfDate(rs.getString("birth_of_date"));
					dealer.setAdress(rs.getString("adress"));
					dealer.setIsRetailSale(rs.getBoolean("is_retail_sale"));
					dealer.setWhyChoose(rs.getString("why_choose"));
					dealer.setInvestmentAmount(rs.getDouble("investment_amount"));
					dealer.setWhatElseWant(rs.getString("what_else_want"));
					return dealer;
				}
				return null;
			}
		});
	}

	//When new add Dealer, Bind Dealer Model for save to db 
	private SqlParameterSource getSqlParameterByModel(Dealer dealer) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		if (dealer != null) {
			parameterSource.addValue("identity", dealer.getIdentity().trim());
			parameterSource.addValue("first_name", dealer.getFirstName().trim());
			parameterSource.addValue("last_name", dealer.getLastName().trim().toUpperCase());
			parameterSource.addValue("phone_number", dealer.getPhoneNumber().trim());
			parameterSource.addValue("email", dealer.getEmail().trim());			
			parameterSource.addValue("birth_of_date", dealer.getBirthOfDate().trim());
			parameterSource.addValue("adress", dealer.getAdress().trim());
			parameterSource.addValue("is_retail_sale", dealer.getIsRetailSale());
			parameterSource.addValue("why_choose", dealer.getWhyChoose().trim());
			parameterSource.addValue("which_region", dealer.getWhichRegion().trim());
			parameterSource.addValue("investment_amount", dealer.getInvestmentAmount());
			parameterSource.addValue("what_else_want", dealer.getWhatElseWant().trim());
		}
		return parameterSource;
	}
}
