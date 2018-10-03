/**
 * 
 */
package com.cuas.service;

import com.cuas.model.Dealer;

/**
 * @author Cuneyt AŞKIN
 * 
 * Service İnterface:
 */
public interface DealerService {
			
	//Create table and database
	public void createDealerTableAndDatabase();
	
	//Save data to DB
	public void saveDealer(Dealer dealer);
	
	//Read data from DB
	public Dealer findDealerById(String identity);
	
}
