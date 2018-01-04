package br.unisinos.kanban.model;

import java.util.Objects;

/**
 * @author Alex Carvalho
 */
public abstract class BaseEntity<PK> {

    public abstract PK getId();

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
