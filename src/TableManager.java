import java.util.HashMap;
import java.util.List;

public interface TableManager {

  /**
   * Create a table with given name and attributes
   * ERROR Checking:
   * - tableName already exists
   * - no attribute is provided
   * - no primary key attribute is specified
   * - the attribute type is not supported
   * @param tableName the table's name
   * @param attributeNames the list of name of attributes
   * @param attributeType the list of type of attributes
   * @param primaryKeyAttributeNames the list of name of the primary-key attributes
   * @return status code, see {#StatusCode}
   */
  public int createTable(String tableName, String[] attributeNames, String[] attributeType, String[] primaryKeyAttributeNames);


  /**
   * Delete a table by its name
   * ERROR Checking:
   * - tableName does not exist
   * @param tableName the table's name
   * @return status code
   */
  public int deleteTable(String tableName);


  /**
   * List all existing tables
   * @return the map from tableName to table instance
   */
  public HashMap<String, Table> listTables();

  /**
   * Add a new attribute to a table
   * ERROR Checking:
   * - tableName does not exist
   * - attributeName already exists in the table
   * - attributeType is not supported
   * @param tableName the name of the table to be altered
   * @param attributeName the name of the attribute to be added
   * @param attributeType the type of the attribute to be added
   * @return status code
   */
  public int addAttribute(String tableName, String attributeName, String attributeType);

  /**
   * Drop an existing attribute in a table
   * ERROR checking
   * - tableName does not exist
   * - attributeName does not exist
   * @param tableName the name of the table to be altered
   * @param attributeName the name of the attribute to be dropped
   * @return status code
   */
  public int dropAttribute(String tableName, String attributeName);
}
