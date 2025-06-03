package com.group.apomsproject;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderRegistry
{
    private static final Map<Class<?>, List<String>> headerMap = new HashMap<>();
    private static final Map<Class<?>, String> pKeyFieldMap = new HashMap<>();
    private static final Map<Class<?>, String> pKeyPrefixMap = new HashMap<>();
    private static final Map<Class<?>, Map<String, List<String>>> dependencyMap = new HashMap<>();
    
    public static void registerHeaders(Class<?> Object, List<String> headers, String pKeyField, String pKeyPrefix, Map<String, List<String>> dependencies)
    {
        headerMap.put(Object, headers);
        pKeyFieldMap.put(Object, pKeyField);
        pKeyPrefixMap.put(Object, pKeyPrefix);
        dependencyMap.put(Object, dependencies);
    }
    
    public static List<String> getHeaders(Class<?> Object)
    {
        return headerMap.getOrDefault(Object, Collections.emptyList());
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
        // Admin
        List<String> AMHeaders = Arrays.asList
        (   // Define headers' order
            "AMID", "userName", "userPassword", "userAddress", "userContact"
        );
        registerHeaders(Admin.class, AMHeaders, "AMID", "A", Collections.emptyMap());
        
        // SalesManager
        List<String> SMHeaders = Arrays.asList
        (
            "SMID", "userName", "userPassword", "userAddress", "userContact"
        );
        // Foreign Key Mapping (used for record deletion)
        Map<String, List<String>> SMDpd = new HashMap<>();
        SMDpd.put("Sales.csv", Arrays.asList("SMID"));
        SMDpd.put("PurchaseRequisition.csv", Arrays.asList("SMID"));
        registerHeaders(SalesManager.class, SMHeaders, "SMID", "S", SMDpd);
        
        // PurchaseManager
        List<String> PMHeaders = Arrays.asList
        (
            "PMID", "userName", "userPassword", "userAddress", "userContact"
        );
        Map<String, List<String>> PMDpd = new HashMap<>();
        PMDpd.put("PurchaseOrder.csv", Arrays.asList("PMID"));
        registerHeaders(PurchaseManager.class, PMHeaders, "PMID", "P", PMDpd);
        
        
        // FinanceManager
        List<String> FMHeaders = Arrays.asList
        (
            "FMID", "userName", "userPassword", "userAddress", "userContact"
        );
        Map<String, List<String>> FMDpd = new HashMap<>();
        FMDpd.put("ImportList.csv", Arrays.asList("FMID"));
        registerHeaders(FinanceManager.class, FMHeaders, "FMID", "F", FMDpd);
        
        // InventoryManager
        List<String> IMHeaders = Arrays.asList
        (
            "IMID", "userName", "userPassword", "userAddress", "userContact"
        );
        registerHeaders(InventoryManager.class, IMHeaders, "IMID", "I", Collections.emptyMap());
        
        // Supplier
        List<String> SupplierHeaders = Arrays.asList
        (
            "supplierID", "supplierName", "supplierAddress", "supplierContact"
        );
        Map<String, List<String>> SPDpd = new HashMap<>();
        SPDpd.put("ItemSupplierMap.csv", Arrays.asList("supplierID"));
        SPDpd.put("PurchaseOrder.csv", Arrays.asList("supplierID"));
        SPDpd.put("ImportList.csv", Arrays.asList("supplierID"));
        registerHeaders(Supplier.class, SupplierHeaders, "supplierID", "SP", SPDpd);
        
        // Item
        List<String> ItemHeaders = Arrays.asList
        (
            "itemID", "itemName", "price", "quantity", "ROL"
        );
        Map<String, List<String>> ITMDpd = new HashMap<>();
        ITMDpd.put("ItemSupplierMap.csv", Arrays.asList("itemID"));
        ITMDpd.put("Sales.csv", Arrays.asList("itemID"));
        ITMDpd.put("PurchaseRequisition.csv", Arrays.asList("itemID"));
        ITMDpd.put("PurchaseOrder.csv", Arrays.asList("itemID"));
        ITMDpd.put("ImportList.csv", Arrays.asList("itemID"));
        registerHeaders(Item.class, ItemHeaders, "itemID", "ITM", ITMDpd);
        
        // ItemSupplierMap
        List<String> ItemSupplierMapHeaders = Arrays.asList
        (
            "ISMID", "itemID", "supplierID", "importPrice"
        );
        registerHeaders(ItemSupplierMap.class, ItemSupplierMapHeaders, "ISMID", "ISM", Collections.emptyMap());
        
        // Sales
        List<String> SalesHeaders = Arrays.asList
        (
            "salesID", "itemID", "quantitySold", "dateCreated", "SMID"
        );
        registerHeaders(Sales.class, SalesHeaders, "salesID", "SL", Collections.emptyMap());
        
        // PurchaseRequisition
        List<String> PRHeaders = Arrays.asList
        (
            "PRID", "itemID", "requiredQuantity", "status", "dateCreated", "SMID"
        );
        Map<String, List<String>> PRDpd = new HashMap<>();
        PRDpd.put("PurchaseOrder.csv", Arrays.asList("PRID"));
        registerHeaders(PurchaseRequisition.class, PRHeaders, "PRID", "PR", PRDpd);
        
        // PurchaseOrder
        List<String> POHeaders = Arrays.asList
        (
            "POID", "PRID", "itemID", "orderQuantity", "supplierID", "status", "dateCreated", "PMID"
        );
        Map<String, List<String>> PODpd = new HashMap<>();
        PODpd.put("ImportList.csv", Arrays.asList("POID"));
        registerHeaders(PurchaseOrder.class, POHeaders, "POID", "PO", PODpd);
        
        // ImportList
        List<String> ILHeaders = Arrays.asList
        (
            "ILID", "POID", "itemID", "supplierID", "receivedQuantity", "status", "dateCreated", "FMID"
        );
        registerHeaders(ImportList.class, ILHeaders, "ILID", "IL", Collections.emptyMap());
    }
}
