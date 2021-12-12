package net.company.hookahstore.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractEntity<T> implements Serializable {
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" + getClass().getSimpleName() +
                " id = " + id +
                '}';
    }
}
