package spring.jdbc.tx;

public interface OperationsDAO {
	int insertTextMessage(String text);
	void otherInsert(String txt);
}
