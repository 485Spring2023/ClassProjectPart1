import java.util.HashMap;

/**
 * TableManager defines the interfaces that need to be implemented to support the table
 * management features.
 */
public interface TableManager {

  /**
   * Create a table with given name and attributes
   *
   * ERROR Checking and the StatusCode that should return:
   * - tableName already exists --> TABLE_ALREADY_EXISTS
   * - no attribute is provided --> TABLE_CREATION_ATTRIBUTE_INVALID
   * - attributeNames/attributeTypes is null or does not have equal length --> TABLE_CREATION_ATTRIBUTE_INVALID
   * - no primary key attribute is specified --> TABLE_CREATION_NO_PRIMARY_KEY
   * - primary key attributes contains attributes that are not in the attribute definitions --> TABLE_CREATION_PRIMARY_KEY_NOT_FOUND
   * - the attribute type is not supported --> ATTRIBUTE_TYPE_NOT_SUPPORTED
   *
   * @param tableName the table's name
   * @param attributeNames the list of name of attributes
   * @param attributeTypes the list of type of attributes
   * @param primaryKeyAttributeNames the list of name of the primary-key attributes
   * @return status code
   */
  public StatusCode createTable(String tableName, String[] attributeNames, AttributeType[] attributeTypes, String[] primaryKeyAttributeNames);


  /**
   * Delete a table by its name
   *
   * ERROR Checking and the StatusCode that should return:
   * - tableName does not exist --> TABLE_NOT_FOUND
   *
   * @param tableName the table's name
   * @return status code
   */
  public StatusCode deleteTable(String tableName);


  /**
   * List all existing tables
   * @return the map from tableName to table instance
   */
  public HashMap<String, TableMetadata> listTables();

  /**
   * Add a new attribute to a table
   *
   * ERROR Checking:
   * - tableName does not exist --> TABLE_NOT_FOUND
   * - attributeName already exists in the table --> ATTRIBUTE_ALREADY_EXISTS
   * - attributeType is not supported --> ATTRIBUTE_TYPE_NOT_SUPPORTED
   *
   * @param tableName the name of the table to be altered
   * @param attributeName the name of the attribute to be added
   * @param attributeType the type of the attribute to be added
   * @return status code
   */
  public StatusCode addAttribute(String tableName, String attributeName, AttributeType attributeType);

  /**
   * Drop an existing attribute in a table
   *
   * ERROR checking and the StatusCode that should return
   * - tableName does not exist --> TABLE_NOT_FOUND
   * - attributeName does not exist --> ATTRIBUTE_NOT_FOUND
   *
   * @param tableName the name of the table to be altered
   * @param attributeName the name of the attribute to be dropped
   * @return status code
   */
  public StatusCode dropAttribute(String tableName, String attributeName);

  /**
   * Drop all tables in the database
   * @return status code
   */
  public StatusCode dropAllTables();
}
