package com.te.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.te.springboot.bean.EmployeeBean;
import com.te.springboot.customexception.EmployeeExp;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public EmployeeBean getEmployee(int id) {
		EntityManager entityManager = factory.createEntityManager();

		EmployeeBean employeeBean = entityManager.find(EmployeeBean.class, id);
		if(employeeBean!=null) {
		return employeeBean;
		}else {
			throw new EmployeeExp("Something went wrong....Invalid Search Id");
		}
	}

	@Override
	public boolean deleteEmpData(int id) {
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			EmployeeBean bean = entityManager.find(EmployeeBean.class, id);
			if (bean != null) {
				entityManager.remove(bean);
				entityTransaction.commit();
				return true;
			} else {
				throw new EmployeeExp("Something went wrong....ID not present to DELETE");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EmployeeExp("Something went wrong....ID not present to DELETE");
		}

	}

	@Override
	public List<EmployeeBean> getAllEmp() {
		EntityManager entityManager = factory.createEntityManager();

		String query = " from EmployeeBean ";
		Query query2 = entityManager.createQuery(query);

		List<EmployeeBean> allEmpData = query2.getResultList();

		return allEmpData;
	}

	@Override
	public boolean addEmployee(EmployeeBean bean) {
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean isAdded = false;
		try {
			entityTransaction.begin();
			entityManager.persist(bean);
			entityTransaction.commit();
			isAdded = true;
		} catch (Exception e) {
			entityTransaction.rollback();
			isAdded = false;
			e.printStackTrace();
		}
		return isAdded;
	}

	@Override
	public boolean updateEmployee(EmployeeBean bean) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		boolean isUpdated = false;

		try {
			transaction.begin();
			EmployeeBean info = manager.find(EmployeeBean.class, bean.getId());
			if (bean.getName() != null && bean.getName() != "") {
				info.setName(bean.getName());
			}
			if (bean.getDob() != null) {
				info.setDob(bean.getDob());
			}

			if (bean.getPassword() != null && bean.getPassword() != "") {
				info.setPassword(bean.getPassword());
			}

			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return isUpdated;
	}

}
