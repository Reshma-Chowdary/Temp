package com.insurance.Payments.Repositories;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.insurance.Payments.Contractors.PaymentDao;
import com.insurance.Payments.Models.Payments;
import com.insurance.Payments.RowMappers.PaymentMapper;

@Repository
public class PaymentDaoImpl implements PaymentDao {
	
	String Get_Payment = "select * from Payments";
	String Get_PaymentById ="select * from Payments where paymentid=?";
	String Get_PaymentByCustId ="select * from Payments where customerid=?";
	String Get_PaymentByDate ="select * from Payments where paymentdate=?";
	JdbcTemplate jdbc;

	@Autowired
    public PaymentDaoImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
    public List<Payments> getPayments() {
        return jdbc.query(Get_Payment, new PaymentMapper());
    }

	@Override
    public Payments getPaymentById(String paymentId) {
        return jdbc.queryForObject(Get_PaymentById, new Object[] { paymentId }, new PaymentMapper());
    }

	public Payments getPaymentByCustId(String customerId) {
		return jdbc.queryForObject(Get_PaymentByCustId, new Object[] { customerId }, new PaymentMapper());
	}

	public Payments getPaymentByDate(String paymentDate) {
		return jdbc.queryForObject(Get_PaymentByDate, new Object[] { paymentDate }, new PaymentMapper());
	}

	
	
}
