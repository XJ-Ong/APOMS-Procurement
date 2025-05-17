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
}