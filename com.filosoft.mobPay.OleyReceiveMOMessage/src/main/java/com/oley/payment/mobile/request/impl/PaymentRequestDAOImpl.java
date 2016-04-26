package com.oley.payment.mobile.request.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.oley.payment.mobile.request.dao.PaymentRequestDAO;
import com.oley.payment.mobile.request.model.PaymentRequest;

public class PaymentRequestDAOImpl implements PaymentRequestDAO{
	private DataSource dataSource;
	 private JdbcTemplate jdbcTemplateObject;
	public DataSource getDataSource() {
		return dataSource;
		
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void insertPaymentRequest(PaymentRequest paymentRequest) {
		// TODO Auto-generated method stub
		String insertSql="INSERT INTO MOBILE_PAYMENT_T_REQUESTS ( " +
				          " MPR_RECEIVED_SMS_ID, MPR_SENDER_GSM, MPR_RECEIVER_GSM, MPR_ORIGINAL_CONTENT," +
				          "MPR_FIXED_CONTENT, MPR_DATE_RECEIVED,MPR_DATE_REQUEST_RECEIVED,MPR_DATE_UPDATE," +
				          "MPR_OLEY_MEMBER_ID, MPR_AMOUNT, MPR_STATE, MPR_GSM_OPERATOR,MPR_GSM_TYPE ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
//		Object values=new Object[] { paymentRequest.getReceivedSMSId(), paymentRequest.getSenderGSM(), paymentRequest.getReceiverGSM(), paymentRequest.getOriginalContent(),
//				                     paymentRequest.getFixedContent(),paymentRequest.getDateReceived(),paymentRequest.getDateRequestReceived(),paymentRequest.getUpdateTime(),
//				                     paymentRequest.getOleyMemberId(),paymentRequest.getAmount(),paymentRequest.getState(),paymentRequest.getGsmOperator(),paymentRequest.getGsmType() };
//		
//		jdbcTemplateObject.update(insertSql,values);
		
		
		Connection conn=null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        int i=0; 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(insertSql);
			
			
			ps.setString(++i,paymentRequest.getReceivedSMSId());
			ps.setString(++i, paymentRequest.getSenderGSM());
			ps.setString(++i, paymentRequest.getReceiverGSM());
			ps.setString(++i,paymentRequest.getOriginalContent());
			ps.setString(++i,  paymentRequest.getFixedContent());
			ps.setDate(++i, paymentRequest.getDateReceived());
			ps.setDate(++i, paymentRequest.getDateRequestReceived());
			ps.setDate(++i, paymentRequest.getUpdateTime());
			ps.setInt(++i, paymentRequest.getOleyMemberId());
			ps.setBigDecimal(++i, paymentRequest.getAmountOfPayment());
			ps.setInt(++i, paymentRequest.getState());
			ps.setInt(++i, paymentRequest.getGsmOperator());
			ps.setInt(++i, paymentRequest.getGsmType());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}
	
}
