import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class TableManagerTest {
  public static String EmployeeTableName = "Employee";
  public static String[] EmployeeTableAttributeNames = new String[]{"SSN", "Name"};
  public static String[] EmployeeTableAttributeTypes = new String[]{"INT", "VARCHAR"};
  public static String[] EmployeeTablePKAttributes = new String[]{"SSN"};

  public static String DepartmentTableName = "Department";
  public static String[] DepartmentTableAttributeNames = new String[]{"Dno", "DName", "Floor"};
  public static String[] DepartmentTableAttributeTypes = new String[]{"INT", "VARCHAR", "INT"};
  public static String[] DepartmentTablePKAttributes = new String[]{"Dno"};

  private TableManager tableManager;

  @Before
  public void init(){
    tableManager = new TableManagerImpl();
  }

  @Test
  public void unitTest1() {

    TableMetadata EmployeeTable = new TableMetadata(EmployeeTableAttributeNames, EmployeeTableAttributeTypes,
        EmployeeTablePKAttributes);

    assertEquals(StatusCode.SUCCESS, tableManager.createTable(EmployeeTableName,
        EmployeeTableAttributeNames, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));

    HashMap<String, TableMetadata> tables = tableManager.listTables();
    assertEquals(1, tables.size());
    assertEquals(EmployeeTable, tables.get(EmployeeTableName));
  }

  @Test
  public void unitTest2() {
    assertEquals(StatusCode.SUCCESS, tableManager.createTable(EmployeeTableName,
        EmployeeTableAttributeNames, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));
    assertEquals(StatusCode.TABLE_NOT_FOUND, tableManager.deleteTable("department"));
    assertEquals(StatusCode.SUCCESS, tableManager.deleteTable("Employee"));

    HashMap<String, TableMetadata> tables = tableManager.listTables();
    assertTrue(tables.isEmpty());
  }

  @Test
  public void unitTest3() {
    String EmployeeTableName = "Employee";
    String[] EmployeeTableAttributeNames = new String[]{"SSN", "Name"};
    String[] EmployeeTableAttributeTypes = new String[]{"INT", "VARCHAR"};
    String[] EmployeeTablePKAttributes = new String[]{"SSN"};

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

  @Test
  public void unitTest4() {
    TableMetadata EmployeeTable = new TableMetadata(EmployeeTableAttributeNames, EmployeeTableAttributeTypes,
        EmployeeTablePKAttributes);

    assertEquals(StatusCode.SUCCESS, tableManager.createTable(EmployeeTableName,
        EmployeeTableAttributeNames, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));

    String EmployeeBirthdayAttribute = "Birthday";
    String EmployeeBirthdayType = "VARCHAR";
    String EmployeeAddressAttribute = "Address";
    String EmployeeAddressType = "VARCHAR";

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

  @Test
  public void unitTest5() {
    String EmployeeTableName = "Employee";
    String[] EmployeeTableAttributeNames = new String[]{"SSN", "Name", "Address", "Birthday"};
    String[] EmployeeTableAttributeTypes = new String[]{"INT", "VARCHAR", "VARCHAR", "VARCHAR"};
    String[] EmployeeTablePKAttributes = new String[]{"SSN"};

    String DepartmentTableName = "Department";
    String[] DepartmentTableAttributeNames = new String[]{"Dno", "DName", "Floor", "ManagerSSN"};
    String[] DepartmentTableAttributeTypes = new String[]{"INT", "VARCHAR", "INT", "INT"};
    String[] DepartmentTablePKAttributes = new String[]{"Dno"};

    String WorksForTableName = "WorksFor";
    String[] WorksForTableAttributeNames = new String[]{"SSN", "Dno"};
    String[] WorksForTableAttributeTypes = new String[]{"INT", "INT"};
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

  @Test
  public void unitTest6() {
    HashMap<String, TableMetadata> expectTables = new HashMap<>();
    for (int i = 0; i < 1000; i++) {
      String employTableName = EmployeeTableName + i;
      assertEquals(StatusCode.SUCCESS, tableManager.createTable(employTableName,
          EmployeeTableAttributeNames, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));
      TableMetadata tbl = new TableMetadata(EmployeeTableAttributeNames, EmployeeTableAttributeTypes,
          EmployeeTablePKAttributes);
      if (i % 2 == 1) {
        expectTables.put(employTableName, tbl);
      }
    }

    for (int i = 0; i < 1000; i++) {
      if (i % 2 == 0) {
        String tblName = EmployeeTableName + i;
        assertEquals(StatusCode.SUCCESS, tableManager.deleteTable(tblName));
      }
    }
    HashMap<String, TableMetadata> tables = tableManager.listTables();
    assertEquals(expectTables, tables);
  }

  @Test
  public void unitTest7() {
    TableMetadata employeeTable = new TableMetadata(EmployeeTableAttributeNames, EmployeeTableAttributeTypes,
        EmployeeTablePKAttributes);
    assertEquals(StatusCode.SUCCESS, tableManager.createTable(EmployeeTableName,
        EmployeeTableAttributeNames, EmployeeTableAttributeTypes, EmployeeTablePKAttributes));

    for (int i = 0; i < 1000; i++) {
      String attributeName = "attr"+i;
      if (i % 2 == 0) {
        employeeTable.addAttribute(attributeName, "INT");
      }
      assertEquals(StatusCode.SUCCESS, tableManager.addAttribute(EmployeeTableName, attributeName,
          "INT"));
    }

    for (int i = 0; i < 1000; i++) {
      if (i % 2 == 1) {
        String attributeName = "attr"+i;
        assertEquals(StatusCode.SUCCESS, tableManager.addAttribute(EmployeeTableName, attributeName,
            "INT"));
      }
    }
    HashMap<String, TableMetadata> tables = tableManager.listTables();
    assertEquals(1, tables.size());
    assertEquals(employeeTable, tables.get(EmployeeTableName));
  }
}
