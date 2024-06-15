
package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@MappedSuperclass //mapped to super class and defines properties and behaviors inherited by entity classes
public abstract class AbstractEntity {

    //written by Shelly
    @Id //unique identifier
    @GeneratedValue
    private int id; //*

    @NotBlank(message = "Name is required")
    @Size(min=1, max=225, message = "Name must be between 1 and 255 characters")
    private String name; //*

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

} // overide methods to customize how instances of AbstractEntity are represented as strings, compared for equality, and hashed
