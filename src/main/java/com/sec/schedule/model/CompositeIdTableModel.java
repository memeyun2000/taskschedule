package com.sec.schedule.model;

import javax.persistence.Embeddable;

@Embeddable
public class CompositeIdTableModel {
    private String databaseName;
    private String tableSchema;
    private String tableName;


    @Override
    public boolean equals(Object obj) {
        // return obj instanceof CompositeIdTaskFact;
        return obj instanceof CompositeIdTableModel 
                && databaseName.equals(((CompositeIdTableModel)obj).databaseName)
                && tableSchema.equals(((CompositeIdTableModel)obj).tableSchema)
                && tableName.equals(((CompositeIdTableModel)obj).tableName); 
    }

    /**
     * 中给出了一个能最大程度上避免哈希冲突的写法，但我个人认为对于一般的应用来说没有必要搞的这么麻烦．如果你的应用中HashSet中需要存放上万上百万个对象时，那你应该严格遵循书中给定的方法．如果是写一个中小型的应用，那么下面的原则就已经足够使用了：
     * 要保证Coder对象中所有的成员都能在hashCode中得到体现．
     * 对于本例，我们可以这么写：
     * 
     * String 类中的 hashCode()
     * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 17 * 31 + databaseName.hashCode();
        result = 17 * 31^2 + tableSchema.hashCode();
        result = 17 * 31^3 + tableName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{database name:" + databaseName + ",table schema:"+ tableSchema + ",table name:" + tableName + "}";
    }


    //---  getter and setter ---//

    /**
     * @return the databaseName
     */
    public String getDatabaseName() {
        return databaseName;
    }
    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }
    /**
     * @return the tableSchema
     */
    public String getTableSchema() {
        return tableSchema;
    }
    /**
     * @param databaseName the databaseName to set
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    /**
     * @param tableSchema the tableSchema to set
     */
    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

}