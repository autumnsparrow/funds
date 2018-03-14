package com.palmcommerce.funds.roo.service;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import com.palmcommerce.funds.roo.model.TransactionMeta;

public class TransactionMetaServiceImpl implements TransactionMetaService {

	public List<TransactionMeta> findTransactionMetaEntriesByBankDateAndBankCode(
			Date bankDatatime, String bankCode, int first, int max) {
		TypedQuery<TransactionMeta> q = TransactionMeta
				.entityManager()
				.createQuery(
						"SELECT o FROM TransactionMeta AS o WHERE (o.transcode='240003' or o.transcode='250004') and (o.transactionRecord.bankDatetime = :bankDate) and (o.fromCode=:bankCode or o.toCode=:bankCode)",
						TransactionMeta.class);
		q.setParameter("bankDate", bankDatatime);
		q.setParameter("bankCode", bankCode);

		return q.setFirstResult(first).setMaxResults(max).getResultList();
		// return q.getResultList();
		// return TransactionMeta.findTransactionMetaEntries(first, max);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.palmcommerce.funds.roo.service.TransactionMetaService#
	 * findTransactionMetasByTradeDateTimeBetweenAndTransCode(java.util.Date,
	 * java.util.Date, boolean)
	 */
	@Override
	public List<TransactionMeta> findTransactionMetasByTradeDateTimeBetweenAndTransCode(
			Date minTradeDateTime, Date maxTradeDateTime, boolean isCharge) {
		// TODO Auto-generated method stub
		return TransactionMeta
				.findTransactionMetasByTradeDateTimeBetweenAndTransCode(
						minTradeDateTime, maxTradeDateTime, isCharge);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.palmcommerce.funds.roo.service.TransactionMetaService#
	 * countTransactionMetaEntriesByBankDateAndBankCode(java.util.Date,
	 * java.lang.String)
	 */
	@Override
	public int countTransactionMetaEntriesByBankDateAndBankCode(
			Date bankDatatime, String bankCode) {
		// TODO Auto-generated method stub
		TypedQuery<Long> q = TransactionMeta
				.entityManager()
				.createQuery(
						"SELECT count(o)  FROM TransactionMeta  o  WHERE (o.transcode='240003' or o.transcode='250004' or o.transcode='240004' or o.transcode='250005') and (o.transactionRecord.bankDatetime = :bankDate) and (o.fromCode=:bankCode or o.toCode=:bankCode)",
						Long.class);
		q.setParameter("bankDate", bankDatatime);
		q.setParameter("bankCode", bankCode);
		return q.getSingleResult().intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.palmcommerce.funds.roo.service.TransactionMetaService#
	 * findTransactionMetaEntriesByBankDateAndBankCode(java.util.Date,
	 * java.lang.String)
	 */
	@Override
	public List<TransactionMeta> findChargedTransactionMetaEntriesByBankDateAndBankCode(
			Date bankDatatime, String bankCode) {
		// TODO Auto-generated method stub
		TypedQuery<TransactionMeta> q = TransactionMeta
				.entityManager()
				.createQuery(
						"SELECT o FROM TransactionMeta AS o WHERE (o.transcode='240003' or o.transcode='250004') and (o.transactionRecord.bankDatetime = :bankDate) and (o.fromCode=:bankCode or o.toCode=:bankCode) and (o.transactionState='0000')",
						TransactionMeta.class);
		q.setParameter("bankDate", bankDatatime);
		q.setParameter("bankCode", bankCode);
		return q.getResultList();
		// return TransactionMeta.findAllTransactionMetas();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.palmcommerce.funds.roo.service.TransactionMetaService#
	 * findTransactionMetaEntriesByBankDateAndBankCode(java.util.Date,
	 * java.lang.String)
	 */
	@Override
	public List<TransactionMeta> findCanceledTransactionMetaEntriesByBankDateAndBankCode(
			Date bankDatatime, String bankCode) {
		// TODO Auto-generated method stub
		TypedQuery<TransactionMeta> q = TransactionMeta
				.entityManager()
				.createQuery(
						"SELECT o FROM TransactionMeta AS o WHERE (o.transcode='240004' or o.transcode='250005') and (o.transactionRecord.bankDatetime = :bankDate) and (o.fromCode=:bankCode or o.toCode=:bankCode) and (o.transactionState='0000')",
						TransactionMeta.class);
		q.setParameter("bankDate", bankDatatime);
		q.setParameter("bankCode", bankCode);
		return q.getResultList();
		// return TransactionMeta.findAllTransactionMetas();
	}

	public String findBankNumberByCardNumber(String cardNumber) {
		TypedQuery<TransactionMeta> q = TransactionMeta
				.entityManager()
				.createQuery(
						"SELECT o FROM TransactionMeta AS o WHERE (o.transcode='250004' ) and (o.transactionRecord.bankAccount = :cardNumber) and  (o.transactionRecord.bindingtype=0) and (o.transactionState='0000')",
						TransactionMeta.class);
		q.setParameter("cardNumber", cardNumber);
		String bankCode = null;
		List<TransactionMeta> result = q.getResultList();
		if (!(result == null || result.size() == 0)) {

			TransactionMeta meta = result.get(0);
			bankCode = meta.getFromCode();
		}
		return bankCode;
	}

}
