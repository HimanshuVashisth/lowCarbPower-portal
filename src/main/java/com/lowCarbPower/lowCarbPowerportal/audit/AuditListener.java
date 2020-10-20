package com.lowCarbPower.lowCarbPowerportal.audit;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

public class AuditListener {

    private static String getCurrentLoggedInUser() {

        String username = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {

            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }

        }

        if (username == null) {
            username = DomainConstants.SYSTEM_USER_ID_PREFIX;
        }

        return username;

    }

    @PreUpdate
    @PrePersist
    public void auditEntity(AbstractAuditedEntity entity) {

        ZonedDateTime now = ZonedDateTime.now();

        if (entity.getCreatedDate() == null) {
            entity.setCreatedDate(now);
        }

        entity.setLastModifiedDate(now);

        String username = getCurrentLoggedInUser();

        if (entity.getCreatedBy() == null) {
            entity.setCreatedBy(username);
        }

        entity.setLastModifiedBy(username);

    }


}
