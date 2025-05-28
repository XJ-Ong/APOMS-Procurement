package com.group.apomsproject;
import java.util.*;

public class HeaderRegistry {
    private static final Map<Class<?>, List<String>> headermap = new HashMap<>();
    
    public static void registerHeaders(Class<?> Object, List<String> headers)
    {
        headermap.put(Object, headers);
    }
    
    public static List<String> getHeaders(Class<?> Object)
    {
        return headermap.getOrDefault(Object, Collections.emptyList());
    }
    
    public static void init()
    {
        // CSV Headers for IM
        List<String> IMHeaders = Arrays.asList
        (
                "IMID", "userName", "userPassword", "userAddress", "userContact"
        );
        registerHeaders(InventoryManager.class, IMHeaders);
        
        // CSV Headers for Item
        List<String> ItemHeaders = Arrays.asList
        (
                "itemCode", "itemName", "supplierID", "stockLevel", "unitPrice", "reorderLevel"
        );
    }
}
