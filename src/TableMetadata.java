import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * TableMetadata defines the view of the TableMetadata in system.
 */
public class TableMetadata {

  // Map from AttributeName to AttributeType
  private HashMap<String, String> attributes;

  // A list contains names of the primary key attribute.
  private List<String> primaryKeys;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TableMetadata table = (TableMetadata) o;
    return Objects.equals(attributes, table.attributes) && Objects.equals(primaryKeys, table.primaryKeys);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributes, primaryKeys);
  }

  public TableMetadata() {
    attributes = new HashMap<>();
    primaryKeys = new ArrayList<>();
  }

  public TableMetadata(String[] attributeNames, String[] attributeTypes, String[] primaryKeys) {
    attributes = new HashMap<>();
    for (int i = 0; i < attributeTypes.length; i++) {
      attributes.put(attributeNames[i], attributeTypes[i]);
    }
    this.primaryKeys = Arrays.asList(primaryKeys);
  }

  public boolean doesAttributeExist(String attributeName) {
    return attributes.containsKey(attributeName);
  }

  public void addAttribute(String attributeName, String attributeType) {
    attributes.put(attributeName, attributeType);
  }

  public HashMap<String, String> getAttributes() {
    return attributes;
  }

  public void setAttributes(HashMap<String, String> attributes) {
    this.attributes = attributes;
  }

  public List<String> getPrimaryKeys() {
    return primaryKeys;
  }

  public void setPrimaryKeys(List<String> primaryKeys) {
    this.primaryKeys = primaryKeys;
  }

}
