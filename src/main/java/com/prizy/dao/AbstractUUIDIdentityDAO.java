package com.prizy.dao;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class AbstractUUIDIdentityDAO extends AbstractIdentityDAO {
    @Type(type = "uuid-char")
    @Column(name = "uuid", columnDefinition = "CHAR(36)", length = 36)
    protected UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractUUIDIdentityDAO)) return false;

        AbstractUUIDIdentityDAO that = (AbstractUUIDIdentityDAO) o;

        return getUuid().equals(that.getUuid());

    }

    @Override
    public int hashCode() {
        return getUuid().hashCode();
    }
}
