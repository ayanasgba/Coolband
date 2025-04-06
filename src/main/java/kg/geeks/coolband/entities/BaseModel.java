package kg.geeks.coolband.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseModel {

    @JoinColumn(name = "add_date")
    LocalDateTime addDate;

    @JoinColumn(name = "update_date")
    LocalDateTime updateDate;

    boolean active;

    @PrePersist
    protected void onCreate() {
        addDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
        active=true;
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDateTime.now();
    }



}
