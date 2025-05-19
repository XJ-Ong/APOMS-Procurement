package com.group.apomsproject;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;

public class FileOperations
{

    public void WriteFile(Object obj)
    {
        Class<?> object = obj.getClass();
        String fileName = object.getSimpleName() + ".csv";
        List<String> headers = new ArrayList<>();
        List<Method> getters = new ArrayList<>();

        // Collect getters and headers
        for (Method method : object.getDeclaredMethods())
        {
            String methodName = method.getName();
            if (methodName.startsWith("get") && !methodName.equals("getClass"))
            {
                getters.add(method);
                String header = methodName.replace("get", "");
                header = header.substring(0, 1).toLowerCase() + header.substring(1);
                headers.add(header);
            }
        }

        try
        {
            File file = new File(fileName);
            boolean isNewFile = !file.exists() || file.length() == 0;

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)))
            {
                // Write headers if the file is new
                if (isNewFile)
                {
                    bw.write(String.join(",", headers));
                    bw.newLine();
                }

                // Collect data values
                List<String> rowData = new ArrayList<>();
                for (Method getter : getters)
                {
                    Object value = getter.invoke(obj);
                    String strValue = (value != null) ? value.toString() : "";

                    // Escape CSV special characters
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
}