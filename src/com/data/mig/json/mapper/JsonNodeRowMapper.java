// convenient Spring JDBC RowMapper for when you want the flexibility of Jackson's TreeModel API
// Note: Jackson can also serialize standard Java Collections (Maps and Lists) to JSON: if you don't need JsonNode,
//   it's simpler and more portable to have Spring JDBC simply return a Map or List<Map>.

package com.data.mig.json.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

public class JsonNodeRowMapper implements RowMapper<JsonNode> {

    private final ObjectMapper mapper;
    
    public JsonNodeRowMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public JsonNode mapRow(ResultSet rs, int rowNum) throws SQLException {
        ObjectNode objectNode = mapper.createObjectNode();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for (int index = 1; index <= columnCount; index++) {
            String column = JdbcUtils.lookupColumnName(rsmd, index);
            Object value = rs.getObject(column);
            if (value == null) {
                objectNode.putNull(column);
            } else if (value instanceof Integer) {
                objectNode.put(column, (Integer) value);
            } else if (value instanceof String) {
                objectNode.put(column, (String) value);                
            } else if (value instanceof Boolean) {
                objectNode.put(column, (Boolean) value);           
            } else if (value instanceof Date) {
                //objectNode.put(column, ((Date) value).getTime());    
            	SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
            	objectNode.put(column, dt.format((Date) value));
            	//objectNode.put(column, (Date) value);
            } else if (value instanceof Long) {
                objectNode.put(column, (Long) value);                
            } else if (value instanceof Double) {
                objectNode.put(column, (Double) value);                
            } else if (value instanceof Float) {
                objectNode.put(column, (Float) value);                
            } else if (value instanceof BigDecimal) {
                objectNode.put(column, (BigDecimal) value);
            } else if (value instanceof Byte) {
                objectNode.put(column, (Byte) value);
            } else if (value instanceof byte[]) {
                objectNode.put(column, (byte[]) value);                
            } else {
                throw new IllegalArgumentException("Unmappable object type: " + value.getClass());
            }
        }
        return objectNode;
    }

}
