
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClazzInfo {

    @Test
    public void testCustomer() {
        System.out.println("-----");
        getInfo(Math.class);
    }


    public void getInfo(Class<?> clazz) {

        if (clazz.isInterface()) {
            System.out.println("<< interface >>");
        }

        // Check if the class is an enum
        if (clazz.isEnum()) {
            System.out.println("<< enum >>");
        }

        // Check if the class is abstract
        if (Modifier.isAbstract(clazz.getModifiers())) {
            System.out.println("<< abstract >>");
        }

        System.out.println("Class: " + clazz.getName() + "\n");

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            String superclassName = superclass.getName();
            System.out.println("Superclass Name: " + superclassName);
        } else {
            System.out.println("No Superclass");
        }

        Field[] fields = clazz.getDeclaredFields();
        System.out.println("\nFields:");
        for (Field field : fields) {
            String modifier = Modifier.isPrivate(field.getModifiers()) ? "-" :
                    Modifier.isPublic(field.getModifiers()) ? "+" :
                            Modifier.isProtected(field.getModifiers()) ? "#" : "";
            String fieldTypeName = field.getType().getSimpleName();
            System.out.println(modifier + " " + field.getName() + ": " + fieldTypeName);
//            System.out.println(modifier + " " + field.getName() + ": " + field.getType().getName());
        }


        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("\nMethods:");
        for (Method method : methods) {
            if (method.getName().contains("$")) {
                continue;
            }
            String modifier = Modifier.isPrivate(method.getModifiers()) ? "-" :
                    Modifier.isPublic(method.getModifiers()) ? "+" :
                            Modifier.isProtected(method.getModifiers()) ? "#" : "";
            Class<?> returnType = method.getReturnType();
            String returnTypeName = returnType.getSimpleName();
            if (returnType.isArray()) {
                returnTypeName = returnType.getComponentType().getSimpleName() + "[]";
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            String parameterString = "";
            for (Class<?> parameterType : parameterTypes) {
                parameterString += parameterType.getSimpleName() + ", ";
            }
            if (parameterString.length() > 0) {
                parameterString = parameterString.substring(0, parameterString.length() - 2);
            }
            System.out.println(modifier + " " + method.getName() + "(" + parameterString + ")" + ": " + returnTypeName);
        }
        System.out.println("-----");
    }

}
