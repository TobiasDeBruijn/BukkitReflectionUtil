package dev.array21.bukkitreflectionlib.exceptions;

public class ReflectException extends Exception {
    private final Exception inner;

    public ReflectException(Exception inner) {
        this.inner = inner;
    }

    @Override
    public void printStackTrace() {
        this.inner.printStackTrace();
    }

    @Override
    public String toString() {
        return this.inner.toString();
    }
}
