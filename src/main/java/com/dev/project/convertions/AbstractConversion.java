package com.dev.project.convertions;

import com.dev.project.exception.ResourceConflictException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;
@Component
public class AbstractConversion {
    public Object convert(Object from, Object to) {
        try {
            copyProperties(from, to);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return to;
    }


    public Object convertList(Object fromList, Object toList, String packageName) {
        List<Object> from = (List<Object>) fromList;
        List<Object> to = (List<Object>) toList;
        try {
            Class<?> c = Class.forName(packageName);
            Constructor<?> cons = c.getConstructor();
            for(Object o : from) to.add(convert(o, cons.newInstance()));
            return to;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
            return new ResourceConflictException("Conversion Exception");
        }
    }

    public Object convertPage(Object fromList, Object toList, String packageName) {
        Page<Object> from = (Page<Object>) fromList;
        List<Object> to = (List<Object>) toList;
        try {
            Class<?> c = Class.forName(packageName);
            Constructor<?> cons = c.getConstructor();
            for(Object o : from) to.add(convert(o, cons.newInstance()));
            return to;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return new ResourceConflictException("Conversion Exception");
        }
    }

}
