package ro.ubb.samples.behavioral.command.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ReflectCommand {
    private Object obj;
    private Method action;
    private Object[] args;

    public ReflectCommand(Object obj, String methodName, Object[] arguments) {
        this.obj = obj;
        this.args = arguments;
        Class cls = obj.getClass();
        Class[] argTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        try {
            action = cls.getMethod(methodName, argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public Object execute() {
        try {
            action.invoke(obj, args);
            return obj.getClass().getMethod("getState").invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}