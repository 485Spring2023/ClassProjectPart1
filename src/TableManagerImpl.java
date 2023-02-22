import java.util.HashMap;
import java.util.List;

// your code
public class TableManagerImpl implements TableManager{

  @Override
  public int createTable(String tableName, String[] attributeNames, String[] attributeType,
                         String[] primaryKeyAttributeNames) {
    return 0;
  }

  @Override
  public int deleteTable(String tableName) {
    return 0;
  }

  @Override
  public HashMap<String, Table> listTables() {
    return null;
  }

  @Override
  public int addAttribute(String tableName, String attributeName, String attributeType) {
    return 0;
  }

  @Override
  public int dropAttribute(String tableName, String attributeName) {
    return 0;
  }
}
