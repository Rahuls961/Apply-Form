/**
 * 
 */
package com.cuas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cuas.dao.DealerDao;
import com.cuas.model.Dealer;

/**
 * @author Cuneyt AŞKIN
 * 
 * DealerService İmplements for connect DealerDaoImp class 
 * and Controller Class 
 * NOTE: Controller can't access Dao Layer directly.
 * 
 */

@Service
public class DealerServiceImp implements DealerService {
		
	private DealerDao dealerDao;
	
	@Autowired	
	public void setDealerDao(DealerDao dealerDao) {
		this.dealerDao = dealerDao;		
	}
	
	// Access Dao for save data to DB
	public void saveDealer(Dealer dealer) {		
		System.out.println("Sout>>>Save Dealer Service is running...");
		dealerDao.saveDealerDB(dealer);
	}

	// Access Dao for read data from DB by given identity
	public Dealer findDealerById(String identity) {				
		return dealerDao.findDealerById(identity);
	}

	public void createDealerTableAndDatabase() {
		System.out.println("Sout>>>Create Dealer Table and Database Service is running...");
		dealerDao.createDealerTableAndDatabase();
	}
}
