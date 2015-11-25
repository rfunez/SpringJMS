package spring.jdbc.tx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class OperationsImp implements OperationsDAO {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedJdbcTemp;
	
	public OperationsImp(JdbcTemplate temp,NamedParameterJdbcTemplate namedTemp){
		this.jdbcTemplate=temp;
		this.namedJdbcTemp=namedTemp;
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int insertTextMessage(String text) {
		// TODO Auto-generated method stub
			System.out.println("Body " +text);
			String insMessage="insert into truck_call values(seq_call.nextVal,?)";
			String bodyMessage="Mierdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
			final String[] chainMessage=new String[2];
			chainMessage[0]=text;
			chainMessage[1]=bodyMessage;
			jdbcTemplate.batchUpdate(insMessage, new BatchPreparedStatementSetter() {
				
				public void setValues(PreparedStatement ps, int idx) throws SQLException {
					// TODO Auto-generated method stub
					ps.setString(1, chainMessage[idx]);
					
					
				}
				
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return chainMessage.length;
				}
			});
			return 0;
	}
	
	//@Transactional(propagation=Propagation.REQUIRED)
	public void otherInsert(final String bodyText){
		String insMsg="insert into truck_call values(seq_call.nextVal,?)";
		this.jdbcTemplate.execute(insMsg, new PreparedStatementCallback<Integer>() {
			public Integer doInPreparedStatement(PreparedStatement ps)throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setString(1, bodyText);
				System.out.println("Inserting");
				return 1;
			}
		});		
	}
}
