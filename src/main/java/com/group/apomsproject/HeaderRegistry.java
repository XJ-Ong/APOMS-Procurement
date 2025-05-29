package com.group.apomsproject;

import java.util.*;

public class HeaderRegistry
{
    private static final Map<Class<?>, List<String>> headermap = new HashMap<>();
    private static final Map<Class<?>, String> pKeyFieldMap = new HashMap<>();
    private static final Map<Class<?>, String> pKeyPrefixMap = new HashMap<>();
    private static final Map<Class<?>, Map<String, List<String>>> dependencyMap = new HashMap<>();
    
    public static void registerHeaders(Class<?> Object, List<String> headers, String pKeyField, String pKeyPrefix, Map<String, List<String>> dependencies)
    {
        headermap.put(Object, headers);
        pKeyFieldMap.put(Object, pKeyField);
        pKeyPrefixMap.put(Object, pKeyPrefix);
        dependencyMap.put(Object, dependencies);
    }
    
    public static List<String> getHeaders(Class<?> Object)
    {
        return headermap.getOrDefault(Object, Collections.emptyList());
    }
    
    public static String getPKeyField(Class<?> Object)
    {
        return pKeyFieldMap.getOrDefault(Object, "");
    }
    
    public static String getPKeyPrefix(Class<?> Object)
    {
        return pKeyPrefixMap.getOrDefault(Object, "");
    }
    
    public static Map<String, List<String>> getDependencies(Class<?> Object)
    {
        return dependencyMap.getOrDefault(Object, Collections.emptyMap());
    }
    
    public static void init()
    {
        List<String> AMHeaders = Arrays.asList
        (
            "AMID", "userName", "userPassword", "userAddress", "userContact"
        );
        registerHeaders(Admin.class, AMHeaders, "AMID", "AM", Collections.emptyMap());
        
        List<String> SMHeaders = Arrays.asList
        (
            "SMID", "userName", "userPassword", "userAddress", "userContact"
        );
        Map<String, List<String>> SMDpd = new HashMap<>();
        SMDpd.put("Sales.csv", Arrays.asList("SMID"));
        SMDpd.put("PurchaseRequisition.csv", Arrays.asList("SMID"));
        registerHeaders(SalesManager.class, SMHeaders, "SMID", "SM", Collections.emptyMap());
        
        List<String> IMHeaders = Arrays.asList
        (
            "IMID", "userName", "userPassword", "userAddress", "userContact"
        );
        registerHeaders(InventoryManager.class, IMHeaders, "IMID", "IM", Collections.emptyMap());
        
        List<String> ItemHeaders = Arrays.asList
        (
            "itemID", "itemName", "supplierID", "stockLevel", "unitPrice", "reorderLevel"
        );
        Map<String, List<String>> itemDpd = new HashMap<>();
        itemDpd.put("ItemSupplierMap.csv", Arrays.asList("itemID"));
        itemDpd.put("PurchaseRequisition.csv", Arrays.asList("itemID"));
        itemDpd.put("POrder.csv", Arrays.asList("itemID"));
        itemDpd.put("ImportList.csv", Arrays.asList("itemID"));
        registerHeaders(Item.class, ItemHeaders, "itemID", "ITM", itemDpd);
        
        List<String> ItemSupplierMapHeaders = Arrays.asList
        (
            "itemID", "supplierID"
        );
        registerHeaders(ItemSupplierMap.class, ItemSupplierMapHeaders, "itemCode", "", Collections.emptyMap());
    }
}
