package com.data.mig.constants;

public interface IApplicationConstants {
	
	String defaultMySqlSchemaName = "classicmodels";
	
	String defaultMySqlUserId = "root";
	
	String defaultMySqlPassword = "root";
	
	String defaultOracleSchemaName = "HR";
	String defaultOracleUserId = "HR";
	String defaultOraclePassword = "HR";
	
	String oracleDatabase = "oracle";
	String mysqlDatabase = "mysql";
	String mongoDatabase = "mongo";
	String cassandralDatabase = "cassandra";	
	
	String retriveMySqlRelationshipQuery = "select  " +
				"concat(table_name, '.', column_name) as 'foreign_table_and_column', " +
				"table_name as 'foreign_table', " +
				"column_name as 'foreign_key', " +
				"concat(referenced_table_name, '.', referenced_column_name) as 'references', " +
				"constraint_name as 'constraint name' " +
				"from information_schema.key_column_usage " +
				"where referenced_table_name is not null and table_schema = ? " +
				"and referenced_table_name = ? ";
	
	String retriveOracleRelationshipQuery = "SELECT concat(a.table_name || '.', a.column_name) as foreign_table_and_column, " +
	   " a.table_name, a.column_name foreign_key, a.constraint_name, c.owner, " +
       " c.r_owner, c_pk.table_name foreign_table, c_pk.constraint_name r_pk " +
       " FROM all_cons_columns a " +
       " JOIN all_constraints c ON a.owner = c.owner " +
       " AND a.constraint_name = c.constraint_name " +
       " JOIN all_constraints c_pk ON c.r_owner = c_pk.owner " +
       " AND c.r_constraint_name = c_pk.constraint_name " +
       " WHERE c.constraint_type = 'R' " +
       " AND a.owner = ? " +
       " AND a.table_name = ? ";	
	
	String retriveMySqlRelationshipQuery_2 =  "select " +
			"concat(cu.table_name, '.', cu.column_name) as 'foreign_table_and_column', " +
		    "cu.table_name as 'foreign_table', " +
		    "cu.column_name as 'foreign_key', " +  
		    "concat(cu.referenced_table_name, '.', cu.referenced_column_name) as 'references', " +
		    "cu.constraint_name as 'constraint name', " +
		    "col.data_type as 'data_type', " +
		    "col.column_key as 'column_key' " +
		"from " +
		    "information_schema.key_column_usage cu, information_schema.COLUMNS col " +
		"where " +
		    "referenced_table_name is not null " +
		    "and cu.table_schema = ? " +
		    "and cu.referenced_table_name = ? " +
		    "and cu.table_schema = col.table_schema " +
		    "and cu.table_name = col.table_name " +
		    "and cu.column_name = col.column_name " +
		    "and cu.table_name <> ? ";
	
	String retriveOracleRelationshipQuery_2 =  "SELECT concat(a.table_name || '.', a.column_name) as foreign_table_and_column,a.table_name, " + 
		       " a.column_name foreign_key, a.constraint_name, c.owner, " + 
		       " c.r_owner, c_pk.table_name foreign_table, c_pk.constraint_name r_pk, " +
		       " c_pk.table_name || '.' || a.COLUMN_NAME references, " +
		       " atc.DATA_TYPE data_type " +
		       " FROM all_cons_columns a " +
		       " JOIN all_constraints c ON a.owner = c.owner " +
		                        " AND a.constraint_name = c.constraint_name " +
		       " JOIN all_constraints c_pk ON c.r_owner = c_pk.owner " +
		                           " AND c.r_constraint_name = c_pk.constraint_name " +
		                           " AND c_pk.table_name <> ? " +
		       " JOIN ALL_TAB_COLS atc ON a.owner = atc.owner " + 
		                          " AND a.table_name = atc.table_name " +
		                          " AND atc.COLUMN_NAME = a.COLUMN_NAME " +
		       " WHERE c.constraint_type = 'R' " +
		       " AND a.owner = ? " +
		       " AND a.table_name = ? ";
	
	String retrieveMySQLTableDetails = "Select table_name from information_schema.tables where table_schema = ?";
	
	String retrieveOracleTableDetails = "Select table_name from all_tables where owner = ?";
	
	String retriveMySqlColumnDetails = "select  table_name, column_name, data_type, column_key " +
			"FROM information_schema.COLUMNS " +
			"WHERE table_schema = ? " +
			"and table_name = ? ";	
	
	String retriveOracleColumnDetails = "select  table_name, column_name, data_type " +
			"FROM ALL_TAB_COLS " +
			"WHERE owner = ? " +
			"and table_name = ? ";		

	String retriveMySqlPrimaryDetails = "select  column_name,data_type " +
			"FROM information_schema.COLUMNS " +
			"WHERE table_schema = ? " +
			"and table_name = ? " +
			"and column_key = 'PRI' ";
	
	String retrieveOraclePrimaryDetails = "Select ac.column_name as \"column_name\", ac.DATA_TYPE as \"data_type\" from ALL_TAB_COLS ac where ac.COLUMN_NAME in " +
			" (Select atc.column_name  from ALL_CONS_COLUMNS atc, ALL_CONSTRAINTS ac " + 
					" where atc.OWNER = ac.OWNER " + 
					" and atc.TABLE_NAME = ac.TABLE_NAME " + 
					" and ac.CONSTRAINT_NAME = atc.CONSTRAINT_NAME " +
					" and atc.owner = ? " + 
					" and atc.table_name = ? " + 
					" and ac.CONSTRAINT_TYPE = 'P') " +
					" and ac.owner = ? " +
					" and ac.table_name = ? ";

	String onlineLoadSuccessMessage = "Data has been migrated successfully !!!";
	
	String onlineLoadFailureMessage = "Data migration failed !!!";
	
	String invalidSelectionMessage = "The selection combination is not as expected !!!";
	
}
