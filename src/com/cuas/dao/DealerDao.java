/**
 * 
 */
package com.cuas.dao;

import com.cuas.model.Dealer;

/**
 * @author Cuneyt AŞKIN
 * Data Access Layer : Connect direct to DB
 */

public interface DealerDao {
	
	//Create Table and Database directly
	public void createDealerTableAndDatabase();
	
	//Save data DB directly
	public void saveDealerDB(Dealer dealer);
	
	//Read data DB directly
	public Dealer findDealerById(String identity);
}
