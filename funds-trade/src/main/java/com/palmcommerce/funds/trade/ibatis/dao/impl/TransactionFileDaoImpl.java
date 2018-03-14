package com.palmcommerce.funds.trade.ibatis.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.palmcommerce.funds.trade.ibatis.bean.TranscationFile;
import com.palmcommerce.funds.trade.ibatis.dao.TransactionFileDao;

public class TransactionFileDaoImpl extends SqlMapClientDaoSupport implements TransactionFileDao{

	public List<TranscationFile> findTransactionFileEntriesByBankDateAndTransCode(
			String bankDatatime, String transCode) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("bankDate", bankDatatime);
		map.put("transCode", transCode);
		List<TranscationFile> list=this.getSqlMapClientTemplate().queryForList("Transaction.findTransactionFileEntriesByBankDateAndTransCode", map);
		return list;
	}
	/* public static List<com.palmcommerce.funds.roo.model.TransactionFile> (Date bankDate, String transCode) {
	        TypedQuery<TransactionFile> q = TransactionFile.entityManager().createQuery("SELECT o FROM TransactionFile AS o WHERE o.bankDatetime = :bankDate and (o.fromCode=:transCode or o.toCode=:transCode)", TransactionFile.class);
	        q.setParameter("bankDate", bankDate);
	        q.setParameter("transCode", transCode);
	        return q.getResultList();
	    }*/

}
