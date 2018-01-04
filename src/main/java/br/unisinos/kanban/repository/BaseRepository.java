package br.unisinos.kanban.repository;

import br.unisinos.kanban.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alex Carvalho
 */
@SuppressWarnings("unchecked")
class BaseRepository<T extends BaseEntity> {

    @PersistenceContext(unitName = "kanban")
    private EntityManager entityManager;

    private Class<T> entityClass;

    BaseRepository() {
        this.entityClass = getParameterizedClass();
    }

    EntityManager getEntityManager() {
        return entityManager;
    }

    public void remove(T entity) {
        entityManager.remove(entityManager.getReference(entity.getClass(), entity.getId()));
    }

    public void persist(T entity) {
        entityManager.persist(entity);
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public List<T> findAll() {
        return entityManager.createQuery("select e from " + entityClass.getSimpleName() + " e").getResultList();
    }

    private <TYPE> TYPE getParameterizedClass() {
        Type type = getClass().getGenericSuperclass();

        if (type instanceof Class) {
            type = ((Class) type).getGenericSuperclass();
        }

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;

            for (Type t : parameterizedType.getActualTypeArguments()) {
                if (t instanceof Class && BaseEntity.class.isAssignableFrom((Class) t)) {
                    return (TYPE) t;
                }
            }
        }

        throw new RuntimeException("Parameter class not found.");
    }

    private Map<String, Field> loadAllFieldsFromClass(Class clazz) {
        Map<String, Field> fields = new HashMap<>();

        for (Class<?> cl = clazz; cl != null; cl = cl.getSuperclass()) {
            for (Field f : cl.getDeclaredFields()) {
                fields.put(f.getName(), f);
            }
        }

        return fields;
    }
}
