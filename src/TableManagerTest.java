import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Attr;

import java.util.HashMap;

public class TableManagerTest {
  public static String EmployeeTableName = "Employee";
  public static String[] EmployeeTableAttributeNames = new String[]{"SSN", "Name"};
  public static AttributeType[] EmployeeTableAttributeTypes =
      new AttributeType[]{AttributeType.INT, AttributeType.VARCHAR};
  public static String[] EmployeeTablePKAttributes = new String[]{"SSN"};

  public static String DepartmentTableName = "Department";
  public static String[] DepartmentTableAttributeNames = new String[]{"Dno", "DName", "Floor"};
  public static AttributeType[] DepartmentTableAttributeTypes = new AttributeType[]{AttributeType.INT,
      AttributeType.VARCHAR, AttributeType.INT};
  public static String[] DepartmentTablePKAttributes = new String[]{"Dno"};

  private TableManager tableManager;

  @Before
  public void init(){
    tableManager = new TableManagerImpl();
  }

  /**
   * Points: 10
   */
  @Test
  public void unitTest1() {
    tableManager.dropAllTables();

    TableMetadata EmployeeTable = new TableMetadata(EmployeeTableAttributeNames, EmployeeTableAttributeTypes,
        EmployeeTablePKAttributes);

    assertEquals(StatusCode.TABLE_CREATION_ATTRIBUTE_INVALID, tableManager.createTable(EmployeeTableName, null, null, null));
    assertEquals(StatusCode.TABLE_CREATION_ATTRIBUTE_INVALID,
        tableManager.createTable(EmployeeTableName, EmployeeTableAttributeNames, null, EmployeeTablePKAttributes));
    assertEquals(StatusCode.TABLE_CREATION_ATTRIBUTE_INVALID,
        tableManager.createTable(EmployeeTableName, null, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));
    assertEquals(StatusCode.TABLE_CREATION_PRIMARY_KEY_NOT_FOUND,
        tableManager.createTable(EmployeeTableName, EmployeeTableAttributeNames,
            EmployeeTableAttributeTypes, new String[]{"ManagerSSN", "SSN"}));

    assertEquals(StatusCode.SUCCESS, tableManager.createTable(EmployeeTableName,
        EmployeeTableAttributeNames, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));

    HashMap<String, TableMetadata> tables = tableManager.listTables();
    assertEquals(1, tables.size());
    assertEquals(EmployeeTable, tables.get(EmployeeTableName));
  }

  /**
   * Points: 15
   */
  @Test
  public void unitTest2() {
    tableManager.dropAllTables();

    assertEquals(StatusCode.SUCCESS, tableManager.createTable(EmployeeTableName,
        EmployeeTableAttributeNames, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));
    assertEquals(StatusCode.TABLE_NOT_FOUND, tableManager.deleteTable("department"));
    assertEquals(StatusCode.SUCCESS, tableManager.deleteTable("Employee"));

    HashMap<String, TableMetadata> tables = tableManager.listTables();
    assertTrue(tables.isEmpty());
  }

  /**
   * Points: 15
   */
  @Test
  public void unitTest3() {
    tableManager.dropAllTables();

    TableMetadata EmployeeTable = new TableMetadata(EmployeeTableAttributeNames, EmployeeTableAttributeTypes,
        EmployeeTablePKAttributes);
    TableMetadata DepartmentTable = new TableMetadata(DepartmentTableAttributeNames,
        DepartmentTableAttributeTypes, DepartmentTablePKAttributes);

    assertEquals(StatusCode.SUCCESS, tableManager.createTable(EmployeeTableName,
        EmployeeTableAttributeNames, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));
    assertEquals(StatusCode.SUCCESS, tableManager.createTable(DepartmentTableName,
        DepartmentTableAttributeNames, DepartmentTableAttributeTypes, DepartmentTablePKAttributes));
    HashMap<String, TableMetadata> tables = tableManager.listTables();
    assertEquals(2, tables.size());
    assertEquals(EmployeeTable, tables.get(EmployeeTableName));
    assertEquals(DepartmentTable, tables.get(DepartmentTableName));
  }

  /**
   * Points: 15
   */
  @Test
  public void unitTest4() {
    tableManager.dropAllTables();

    TableMetadata EmployeeTable = new TableMetadata(EmployeeTableAttributeNames, EmployeeTableAttributeTypes,
        EmployeeTablePKAttributes);

    assertEquals(StatusCode.SUCCESS, tableManager.createTable(EmployeeTableName,
        EmployeeTableAttributeNames, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));

    String EmployeeBirthdayAttribute = "Birthday";
    AttributeType EmployeeBirthdayType = AttributeType.VARCHAR;
    String EmployeeAddressAttribute = "Address";
    AttributeType EmployeeAddressType = AttributeType.VARCHAR;

    assertEquals(StatusCode.SUCCESS, tableManager.addAttribute(EmployeeTableName,
        EmployeeBirthdayAttribute, EmployeeBirthdayType));
    assertEquals(StatusCode.SUCCESS, tableManager.addAttribute(EmployeeTableName,
        EmployeeAddressAttribute, EmployeeAddressType));

    EmployeeTable.addAttribute(EmployeeBirthdayAttribute, EmployeeBirthdayType);
    EmployeeTable.addAttribute(EmployeeAddressAttribute, EmployeeAddressType);
    HashMap<String, TableMetadata> tables = tableManager.listTables();
    assertEquals(1, tables.size());
    assertEquals(EmployeeTable, tables.get(EmployeeTableName));
  }

  /**
   * Points: 15
   */
  @Test
  public void unitTest5() {
    tableManager.dropAllTables();

    String EmployeeTableName = "Employee";
    String[] EmployeeTableAttributeNames = new String[]{"SSN", "Name", "Address", "Birthday"};
    AttributeType[] EmployeeTableAttributeTypes = new AttributeType[]{AttributeType.INT, AttributeType.VARCHAR,
        AttributeType.VARCHAR, AttributeType.VARCHAR};
    String[] EmployeeTablePKAttributes = new String[]{"SSN"};

    String DepartmentTableName = "Department";
    String[] DepartmentTableAttributeNames = new String[]{"Dno", "DName", "Floor", "ManagerSSN"};
    AttributeType[] DepartmentTableAttributeTypes = new AttributeType[]{AttributeType.INT, AttributeType.VARCHAR,
        AttributeType.INT, AttributeType.INT};
    String[] DepartmentTablePKAttributes = new String[]{"Dno"};

    String WorksForTableName = "WorksFor";
    String[] WorksForTableAttributeNames = new String[]{"SSN", "Dno"};
    AttributeType[] WorksForTableAttributeTypes = new AttributeType[]{AttributeType.INT, AttributeType.INT};
    String[] WorksForTablePKAttributes = new String[]{"SSN", "Dno"};

    assertEquals(StatusCode.SUCCESS, tableManager.createTable(EmployeeTableName,
        EmployeeTableAttributeNames, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));
    assertEquals(StatusCode.SUCCESS, tableManager.createTable(DepartmentTableName,
        DepartmentTableAttributeNames, DepartmentTableAttributeTypes, DepartmentTablePKAttributes));
    assertEquals(StatusCode.SUCCESS, tableManager.createTable(WorksForTableName,
        WorksForTableAttributeNames, WorksForTableAttributeTypes, WorksForTablePKAttributes));

    TableMetadata EmployeeTable = new TableMetadata(EmployeeTableAttributeNames, EmployeeTableAttributeTypes,
        EmployeeTablePKAttributes);
    TableMetadata DepartmentTable = new TableMetadata(DepartmentTableAttributeNames, DepartmentTableAttributeTypes,
        DepartmentTablePKAttributes);
    TableMetadata WorksForTable = new TableMetadata(WorksForTableAttributeNames, WorksForTableAttributeTypes,
        WorksForTablePKAttributes);

    HashMap<String, TableMetadata> tables = tableManager.listTables();
    assertEquals(3, tables.size());
    assertEquals(EmployeeTable, tables.get(EmployeeTableName));
    assertEquals(DepartmentTable, tables.get(DepartmentTableName));
    assertEquals(WorksForTable, tables.get(WorksForTableName));
  }

  /**
   * Points: 15
   */
  @Test
  public void unitTest6() {
    tableManager.dropAllTables();

    int numberOfTables = 1000;

    assertEquals(StatusCode.TABLE_NOT_FOUND, tableManager.deleteTable("Employee"));
    HashMap<String, TableMetadata> expectTables = new HashMap<>();
    for (int i = 0; i < numberOfTables; i++) {
      String employTableName = EmployeeTableName + i;
      assertEquals(StatusCode.SUCCESS, tableManager.createTable(employTableName,
          EmployeeTableAttributeNames, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));
      TableMetadata tbl = new TableMetadata(EmployeeTableAttributeNames, EmployeeTableAttributeTypes,
          EmployeeTablePKAttributes);
      if (i % 2 == 1) {
        expectTables.put(employTableName, tbl);
      }
    }

    for (int i = 0; i < numberOfTables; i++) {
      if (i % 2 == 0) {
        String tblName = EmployeeTableName + i;
        assertEquals(StatusCode.SUCCESS, tableManager.deleteTable(tblName));
      }
    }
    HashMap<String, TableMetadata> tables = tableManager.listTables();
    assertEquals(expectTables, tables);
  }

  /**
   * Points: 15
   */
  @Test
  public void unitTest7() {
    tableManager.dropAllTables();
    int numberOfAttributes = 1000;

    TableMetadata employeeTable = new TableMetadata(EmployeeTableAttributeNames, EmployeeTableAttributeTypes,
        EmployeeTablePKAttributes);
    assertEquals(StatusCode.SUCCESS, tableManager.createTable(EmployeeTableName,
        EmployeeTableAttributeNames, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));
    assertEquals(StatusCode.TABLE_NOT_FOUND, tableManager.addAttribute("Department", "a", AttributeType.INT));
    assertEquals(StatusCode.ATTRIBUTE_ALREADY_EXISTS, tableManager.addAttribute(EmployeeTableName, "SSN", AttributeType.INT));
    assertEquals(StatusCode.ATTRIBUTE_NOT_FOUND, tableManager.dropAttribute(EmployeeTableName, "Salary"));

    for (int i = 0; i < numberOfAttributes; i++) {
      String attributeName = "attr"+i;
      if (i % 2 == 0) {
        employeeTable.addAttribute(attributeName, AttributeType.INT);
      }
      assertEquals(StatusCode.SUCCESS, tableManager.addAttribute(EmployeeTableName, attributeName,
          AttributeType.INT));
    }

    for (int i = 0; i < numberOfAttributes; i++) {
      if (i % 2 == 1) {
        String attributeName = "attr"+i;
        assertEquals(StatusCode.SUCCESS, tableManager.dropAttribute(EmployeeTableName, attributeName));
      }
    }
    HashMap<String, TableMetadata> tables = tableManager.listTables();
    assertEquals(1, tables.size());
    assertEquals(employeeTable, tables.get(EmployeeTableName));
  }
}