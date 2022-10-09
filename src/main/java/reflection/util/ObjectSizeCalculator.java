package reflection.util;

import java.lang.reflect.Field;

public class ObjectSizeCalculator {
    private static final long HEADER_SIZE = 12;
    private static final long REFERENCE_SIZE = 4;

    static class AccountSummary {
        private final String firstName;
        private final String lastName;
        private final short age;
        private final int salary;

        AccountSummary(String firstName, String lastName, short age, int salary) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.salary = salary;
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        AccountSummary accountSummary = new AccountSummary("John", "Smith", (short)20, 100_000);
        System.out.println(sizeOfObject(accountSummary));
    }

    public static long sizeOfObject(Object input) throws IllegalAccessException {
        long size = HEADER_SIZE + REFERENCE_SIZE;
        Class<?> clazz = input.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            if (fieldType.isPrimitive()) {
                size += sizeOfPrimitiveType(fieldType);
            } else if (fieldType.equals(String.class)) {
                size += sizeOfString((String) field.get(input));
            } else {
                size += sizeOfObject(field.get(input));
            }
        }
        return size;
    }

    /*************** Helper methods ********************************/
    private static long sizeOfPrimitiveType(Class<?> primitiveType) {
        if (primitiveType.equals(int.class)) {
            return 4;
        } else if (primitiveType.equals(long.class)) {
            return 8;
        } else if (primitiveType.equals(float.class)) {
            return 4;
        } else if (primitiveType.equals(double.class)) {
            return 8;
        } else if (primitiveType.equals(byte.class)) {
            return 1;
        } else if (primitiveType.equals(short.class)) {
            return 2;
        }
        throw new IllegalArgumentException(String.format("Type: %s is not supported", primitiveType));
    }

    private static long sizeOfString(String inputString) {
        int stringBytesSize = inputString.getBytes().length;
        return HEADER_SIZE + REFERENCE_SIZE + stringBytesSize;
    }
}
