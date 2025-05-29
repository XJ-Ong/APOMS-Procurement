package com.group.apomsproject;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class FileOperations
{

    public void WriteFile(Object obj)
    {
        Class<?> Object = obj.getClass();
        String fileName = Object.getSimpleName() + ".csv";
        List<String> headers = HeaderRegistry.getHeaders(Object);
        String pKeyField = HeaderRegistry.getPKeyField(Object);

        if(headers.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "no defined headers for this class");
            return;
        }
        
        // Primary Key validation
        String pKeyValue = null;
        try
        {
            String getterName = "get" + pKeyField.substring(0, 1).toUpperCase() + pKeyField.substring(1);
            Method getter = Object.getMethod(getterName);
            pKeyValue = getter.invoke(obj).toString();
            List<Map<String, String>> existingData = ReadFile(fileName);
            for(Map<String, String> row : existingData)
            {
                // compare existing rows' ID to the passed object's ID
                if(row.get(pKeyField).equals(pKeyValue))
                {
                    JOptionPane.showMessageDialog(null, "Error: Primary Key " + pKeyValue + " already exists in " + fileName);
                    return;
                }
            }
        }
        catch(Exception e)
        {
            JOptionPane.showConfirmDialog(null, "Error accessing primary key: " + e.getMessage());
        }
        
        Map<String, Method> getterMap = new HashMap<>();
        for(String header : headers)
        {
            String getterName = "get" + header.substring(0, 1).toUpperCase() + header.substring(1);
            try
            {
                Method getter = Object.getMethod(getterName);
                getterMap.put(header, getter);
            }
            catch(NoSuchMethodException e)
            {
                JOptionPane.showMessageDialog(null, "Getter not found for header: " + header);
                return;
            }
        }
        
        try
        {
            File file = new File(fileName);
            boolean isNewFile = !file.exists() || file.length() == 0;

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)))
            {
                // Write headers if the file is new
                if (isNewFile)
                {
                    bw.write(String.join(",", headers));
                    bw.newLine();
                }

                List<String> rowData = new ArrayList<>();
                for(String header : headers)
                {
                    Method getter = getterMap.get(header);
                    Object value = getter.invoke(obj);
                    String strValue = (value != null) ? value.toString() : "";
                    
                    if (strValue.contains(",") || strValue.contains("\"") || strValue.contains("\n"))
                    {
                        strValue = "\"" + strValue.replace("\"", "\"\"") + "\"";
                    }
                    rowData.add(strValue);
                }
                
                // Write the row
                bw.write(String.join(",", rowData));
                bw.newLine();
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public List<Map<String, String>> ReadFile(String filename)
    {
        List<Map<String, String>> data = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            String headerLine = br.readLine();
            
            if(headerLine == null)
            {
                return data;
            }
            
            String[] headers = parseCSVLine(headerLine);
            String line;
            while((line = br.readLine()) != null)
            {
                String[] values = parseCSVLine(line); // returns a list of String from a csv line
                Map<String, String> row = new HashMap<>(); 
                
                // using Map<> to create key-value pairs for easier data operations
                for(int i = 0; i < headers.length; i++)
                {
                    if(i < values.length)
                    {
                        row.put(headers[i], values[i]);
                    }
                    else
                    {
                        row.put(headers[i], "");
                    }
                }
                data.add(row);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return data;
    }
    
    private String[] parseCSVLine(String line)
    {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
                continue;
            }
            if (c == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField.setLength(0);
            } else {
                currentField.append(c);
            }
        }
        fields.add(currentField.toString());
        return fields.toArray(new String[0]);
    }
    
    private Object StringToType(String value, Class<?> targetType)
    {
        if(value.isEmpty())
        {
            if(targetType == int.class){return 0;}
            if(targetType == double.class){return 0.0;}
            if(targetType == boolean.class){return false;}
            if(targetType == long.class){return 0L;}
            if(targetType == float.class){return 0.0f;}
            if(targetType == short.class){return (short) 0;}
            if(targetType == byte.class){return (byte) 0;}
            if(targetType == char.class){return '\0';}
            return null;
        }
        
        try
        {
            if(targetType == String.class){return value;}
            else if(targetType == int.class){return Integer.parseInt(value);}
            else if(targetType == double.class){return Double.parseDouble(value);}
            else if(targetType == boolean.class){return Boolean.parseBoolean(value);}
            else if(targetType == long.class){return Long.parseLong(value);}
            else if(targetType == float.class){return Float.parseFloat(value);}
            else if(targetType == short.class){return Short.parseShort(value);}
            else if(targetType == byte.class){return Byte.parseByte(value);}
            else if(targetType == char.class){return value.charAt(0);}
            else
            {
                Constructor<?> constructor = targetType.getConstructor(String.class);
                return constructor.newInstance(value);
            } 
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Failed to convert '" + value + "' to" + targetType + "." + e.getMessage());
            return StringToType("", targetType);
        }
    }
    
    public <T> List<T> recreateObj(String className)
    {
        List<T> objects = new ArrayList<>();
        String filename = className + ".csv";
        List<Map<String, String>> data = ReadFile(filename);
        
        try
        {
            Class<T> Object = (Class<T>) Class.forName("com.group.apomsproject." + className);
            List<String> headers  = HeaderRegistry.getHeaders(Object);
            if(headers.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "No headers defined for class: " + className);
                return objects;
            }
            
            Map<String, Method> getterMap = new HashMap<>();
            Map<String, Class<?>> typeMap = new HashMap<>();
            for(String header : headers)
            {
                String getterName = "get" + header.substring(0, 1).toUpperCase() + header.substring(1);
                try
                {
                    Method getter = Object.getMethod(getterName);
                    getterMap.put(header, getter);
                    typeMap.put(header, getter.getReturnType());
                }
                catch(NoSuchMethodException e)
                {
                    JOptionPane.showMessageDialog(null, "Getter not found: " + getterName + " for header: " + header);
                }
            }
            
            for(Map<String, String> row : data)
            {
                try
                {
                    Constructor<?>[] constructors = Object.getConstructors();
                    Constructor<?> bestConstructor = null;
                    Object[] args = null;
                    
                    for(Constructor<?> constructor : constructors)
                    {
                        Parameter[] params = constructor.getParameters();
                        if(params.length == headers.size())
                        {
                            boolean allMatched = true;
                            args = new Object[params.length];
                            for(int i = 0; i < params.length; i++)
                            {
                                String header = headers.get(i);
                                if (getterMap.containsKey(header))
                                {
                                    String value = row.getOrDefault(header, "");
                                    args[i] = StringToType(value, params[i].getType());
                                }
                                else
                                {
                                    allMatched = false;
                                    break;
                                }
                            }
                            if(allMatched)
                            {
                                bestConstructor = constructor;
                                break;
                            }
                        }
                    }
                    
                    T instance;
                    if(bestConstructor != null)
                    {
                        instance = (T) bestConstructor.newInstance(args);
                    }
                    else
                    {
                        instance = Object.getDeclaredConstructor().newInstance();
                        for (Map.Entry<String, String> entry : row.entrySet())
                        {
                            String header = entry.getKey();
                            String value = entry.getValue();
                            String setterName = "set" + header.substring(0, 1).toUpperCase() + header.substring(1);
                            try
                            {
                                Class<?> paramType = typeMap.getOrDefault(header, String.class);
                                Method setter = Object.getMethod(setterName, paramType);
                                Object convertedValue = StringToType(value, paramType);
                                setter.invoke(instance, convertedValue);
                            }
                            catch (NoSuchMethodException e){}
                        }
                    }
                    objects.add(instance);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Error creating object for row: " + e.getMessage());
                }
            }
        }
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "Class not found: " + className);
        }
        
        return objects;
    }
    
    public DefaultTableModel getTable(String className)
    {
        String filename = className + ".csv";
        DefaultTableModel model = new DefaultTableModel();
        
        try
        {
            Class<?> Object = Class.forName("com.group.apomsproject." + className);
            List<String> headerList = HeaderRegistry.getHeaders(Object);
            
            if(headerList.isEmpty())
            {
                throw new Exception("No headers defined for this class: " + className);
            }
            
            String[] headers = headerList.toArray(new String[0]);
            
            for(String header : headers)
            {
                model.addColumn(header);
            }
                    
            List<Map<String, String>> data = ReadFile(filename);
            
            if(data.isEmpty())
            {
                throw new Exception("Empty CSV file or no data read");
            }
            
            for(Map<String, String> row : data)
            {
                String[] rowData = new String[headers.length];
                for(int i = 0; i < headers.length; i++)
                {
                    rowData[i] = row.getOrDefault(headers[i], "");
                }
                model.addRow(rowData);
            }
        }   
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "Class not found: " + className);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
        return model;
    }
    
    public void UpdateFile(Object obj, String pKeyValue)
    {
        Class<?> Object = obj.getClass();
        String fileName = Object.getSimpleName() + ".csv";
        List<String> headers = HeaderRegistry.getHeaders(Object);
        String pKeyField = HeaderRegistry.getPKeyField(Object);
        
        if(headers.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "no defined headers for this class");
            return;
        }
        
        List<Map<String, String>> existingData = ReadFile(fileName);
        boolean found = false;
        
        List<Map<String, String>> updatedData = new ArrayList<>();
        for(Map<String, String> row : existingData)
        {
            if(row.get(pKeyField).equals(pKeyValue))
            {
                found = true;
                Map<String, String> updatedRow = new HashMap<>();
                for(String header : headers)
                {
                    try
                    {
                        String getterName = "get" + header.substring(0, 1).toUpperCase() + header.substring(1);
                        Method getter = Object.getMethod(getterName);
                        Object value = getter.invoke(obj);
                        String strValue = (value != null) ? value.toString() : "";
                        
                        if(strValue.contains(",") || strValue.contains("\"") || strValue.contains("\n"))
                        {
                            strValue = "\"" + strValue.replace("\"", "\"\"") + "\"";
                        }
                        updatedRow.put(header, strValue);
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "Error accessing getter for header: " + header);
                        return;
                    }
                }
                updatedData.add(updatedRow);
            }
            else
            {
                updatedData.add(row);
            }
        }
        
        if(!found)
        {
            JOptionPane.showMessageDialog(null, "Error: " + pKeyValue + " not foudn in " + fileName);
            return;
        }
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            bw.write(String.join(",", headers));
            bw.newLine();
            
            for(Map<String, String> row : updatedData)
            {
                List<String> rowData = new ArrayList<>();
                
                for(String header : headers)
                {
                    rowData.add(row.getOrDefault(header, ""));
                }
                bw.write(String.join(",", rowData));
                bw.newLine();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error updating file: " + e.getMessage());
        }
    }
}