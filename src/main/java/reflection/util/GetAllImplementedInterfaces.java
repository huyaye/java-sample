package reflection.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class GetAllImplementedInterfaces {
    public static void main(String[] args) {
        Set<Class<?>> allImplementedInterfaces = findAllImplementedInterfaces(ArrayList.class);
        for (Class<?> clazz : allImplementedInterfaces) {
            System.out.println(String.format("class name : %s, class package name : %s",
                    clazz.getSimpleName(),
                    clazz.getPackageName()));
        }
    }

    /**
     * Returns all the interfaces that the current input class implements.
     * Note: If the input is an interface itself, the method returns all the interfaces the
     * input interface extends.
     */
    public static Set<Class<?>> findAllImplementedInterfaces(Class<?> input) {
        Set<Class<?>> allImplementedInterfaces = new HashSet<>();

        Class<?>[] inputInterfaces = input.getInterfaces();
        for (Class<?> currentInterface : inputInterfaces) {
            allImplementedInterfaces.add(currentInterface);
            allImplementedInterfaces.addAll(findAllImplementedInterfaces(currentInterface));
        }

        return allImplementedInterfaces;
    }

}
