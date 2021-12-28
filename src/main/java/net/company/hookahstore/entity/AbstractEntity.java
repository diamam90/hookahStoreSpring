package net.company.hookahstore.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractEntity<T> implements Serializable {


    public abstract T getId() ;

    public String toString(){
        return getClass().getSimpleName()+" id = " + getId();
    }

}
