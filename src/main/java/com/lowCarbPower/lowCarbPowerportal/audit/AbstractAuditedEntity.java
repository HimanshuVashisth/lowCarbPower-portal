package com.lowCarbPower.lowCarbPowerportal.audit;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.ZonedDateTime;

@EntityListeners(AuditListener.class)
@MappedSuperclass
@Audited
public abstract class AbstractAuditedEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "created_by", nullable = false)
    private String createdBy = "CRON_API_SCHEDULED_PROCESS";

    @Column(name = "created_date", nullable = false, columnDefinition = "datetime DEFAULT NOW()")
    @Type(type = "com.lowCarbPower.lowCarbPowerportal.config.ZonedDateTimeType")
    @Convert(disableConversion = true)
    private ZonedDateTime createdDate;

    @Column(name = "last_modified_by", nullable = false)
    private String lastModifiedBy = "CRON_API_SCHEDULED_PROCESS";

    @Column(name = "last_modified_date", nullable = false, columnDefinition = "datetime DEFAULT NOW()")
    @Type(type = "com.lowCarbPower.lowCarbPowerportal.config.ZonedDateTimeType")
    @Convert(disableConversion = true)
    private ZonedDateTime lastModifiedDate;

    public AbstractAuditedEntity() {
        ZonedDateTime now = ZonedDateTime.now();
        setCreatedDate(now);
        setLastModifiedDate(now);
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
