package com.group.apomsproject;
import java.util.*;

public class HeaderRegistry {
    private static final Map<Class<?>, List<String>> headermap = new HashMap<>();
    private static final Map<Class<?>, String> pKeyFieldMap = new HashMap<>();
    private static final Map<Class<?>, String> pKeyPrefixMap = new HashMap<>();
    
    public static void registerHeaders(Class<?> Object, List<String> headers, String pKeyField, String pKeyPrefix)
    {
        headermap.put(Object, headers);
        pKeyFieldMap.put(Object, pKeyField);
        pKeyPrefixMap.put(Object, pKeyPrefix);
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
    
    public static void init()
    {
        // CSV Headers & Primary Key for IM
        List<String> IMHeaders = Arrays.asList
        (
            "IMID", "userName", "userPassword", "userAddress", "userContact"
        );
        registerHeaders(InventoryManager.class, IMHeaders, "IMID", "IM");
        
        // CSV Headers & Primary Key for Item
        List<String> ItemHeaders = Arrays.asList
        (
            "itemID", "itemName", "supplierID", "stockLevel", "unitPrice", "reorderLevel"
        );
        registerHeaders(Item.class, ItemHeaders, "itemID", "ITM");
        
        // CSV Headers for ItemSupplierMap
        List<String> ItemSupplierMapHeaders = Arrays.asList
        (
            "itemID", "supplierID"
        );
        registerHeaders(ItemSupplierMap.class, ItemSupplierMapHeaders, "itemCode", "");
    }
}
