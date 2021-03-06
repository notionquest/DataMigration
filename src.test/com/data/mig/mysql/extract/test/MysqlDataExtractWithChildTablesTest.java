package com.data.mig.mysql.extract.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.data.mig.constants.IApplicationConstants;
import com.data.mig.mysql.db.MysqlDatabaseConnect;
import com.data.mig.mysql.extract.MysqlDataExtractWithChildTables;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class MysqlDataExtractWithChildTablesTest {
		
	@Test
	public void getMysqlDataExtractWithGivenChildTableTest() throws SQLException {
		MysqlDatabaseConnect mysqlDatabaseConnect = new MysqlDatabaseConnect();

		Connection conn = mysqlDatabaseConnect.getMySqlDBConnection(
				IApplicationConstants.defaultMySqlSchemaName,
				IApplicationConstants.defaultMySqlUserId,
				IApplicationConstants.defaultMySqlPassword);

		Assert.assertNotNull(conn);

		MysqlDataExtractWithChildTables mysqlDataExtractWithChildTables = new MysqlDataExtractWithChildTables();

		/*boolean extractResult = mysqlDataExtractWithChildTables.extractMysqlDataIntoJsonFile(IApplicationConstants.defaultMySqlSchemaName, "customers","orders", 
				5L, "D:\\Habi\\MS\\Git\\MySQL\\orders_customers.json");
		
		Assert.assertTrue(extractResult);
		
		extractResult = mysqlDataExtractWithChildTables.extractMysqlDataIntoJsonFile(IApplicationConstants.defaultMySqlSchemaName, "customers","payments", 
				5L, "D:\\Habi\\MS\\Git\\MySQL\\payments_customers.json");
		
		Assert.assertTrue(extractResult);*/
		
		boolean extractResult = mysqlDataExtractWithChildTables.extractMysqlDataIntoJsonFileForCassandra(IApplicationConstants.defaultMySqlSchemaName, "productlines","products", 
		5L, "D:\\Sampath\\MS\\Dissertation\\MySQL\\products_productlines.json");

		Assert.assertTrue(extractResult);
		
		/*boolean extractResult = mysqlDataExtractWithChildTables.extractMysqlDataIntoJsonFile(IApplicationConstants.defaultMySqlSchemaName, "customers","orders", 
				5L, "D:\\Sampath\\MS\\Dissertation\\MySQL\\orders_customers.json");
		
		Assert.assertTrue(extractResult);
		
		extractResult = mysqlDataExtractWithChildTables.extractMysqlDataIntoJsonFile(IApplicationConstants.defaultMySqlSchemaName, "customers","payments", 
				5L, "D:\\Sampath\\MS\\Dissertation\\MySQL\\payments_customers.json");
		
		Assert.assertTrue(extractResult);*/

		conn.close();

	}
	
	@Test
	public void getMysqlDataExtractWithGivenChildTableTest1() throws SQLException {
		MysqlDatabaseConnect mysqlDatabaseConnect = new MysqlDatabaseConnect();

		Connection conn = mysqlDatabaseConnect.getMySqlDBConnection(
				IApplicationConstants.defaultMySqlSchemaName,
				IApplicationConstants.defaultMySqlUserId,
				IApplicationConstants.defaultMySqlPassword);

		Assert.assertNotNull(conn);

		MysqlDataExtractWithChildTables mysqlDataExtractWithChildTables = new MysqlDataExtractWithChildTables();

		boolean extractResult = mysqlDataExtractWithChildTables.extractMysqlDataIntoJsonFile(IApplicationConstants.defaultMySqlSchemaName, "productlines", 
				5L, "D:\\Sampath\\MS\\Dissertation\\MySQL\\productlines.json");
		
		Assert.assertTrue(extractResult);
		
		

		conn.close();

	}

}
