import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * TableMetadata defines the view of the table's metadata in system.
 */
public class TableMetadata {

  // Map from AttributeName to AttributeType
  private HashMap<String, AttributeType> attributes;

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

  public TableMetadata(String[] attributeNames, AttributeType[] attributeTypes, String[] primaryKeys) {
    attributes = new HashMap<>();
    for (int i = 0; i < attributeTypes.length; i++) {
      attributes.put(attributeNames[i], attributeTypes[i]);
    }
    this.primaryKeys = Arrays.asList(primaryKeys);
  }

  public boolean doesAttributeExist(String attributeName) {
    return attributes.containsKey(attributeName);
  }

  public void addAttribute(String attributeName, AttributeType attributeType) {
    attributes.put(attributeName, attributeType);
  }

  public HashMap<String, AttributeType> getAttributes() {
    return attributes;
  }

  public void setAttributes(HashMap<String, AttributeType> attributes) {
    this.attributes = attributes;
  }

  public List<String> getPrimaryKeys() {
    return primaryKeys;
  }

  public StatusCode setPrimaryKeys(List<String> primaryKeys) {
    for (String pk : primaryKeys) {
      if (!attributes.containsKey(pk)) {
        return StatusCode.ATTRIBUTE_NOT_FOUND;
      }
    }

    this.primaryKeys = primaryKeys;
    return StatusCode.SUCCESS;
  }

}
