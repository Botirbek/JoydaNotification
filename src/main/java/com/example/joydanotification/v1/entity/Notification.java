package com.example.joydanotification.v1.entity;

import com.example.joydanotification.config.PostgreSQLEnumType;
import com.example.joydanotification.v1.enums.NotificationStatusEnum;
import com.example.joydanotification.v1.enums.NotificationTypeEnum;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Type(type = "jsonb")
    private String title;

    private String image;

    @Type(type = "jsonb")
    private String text;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "notification_type")
    @Type(type = "pgsql_enum")
    private NotificationTypeEnum type;

    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "notification_status")
    @Type(type = "pgsql_enum")
    private NotificationStatusEnum status;

    @Column(updatable = false)
    @CreationTimestamp
    @CreatedDate
    private LocalDateTime created_at;

    @UpdateTimestamp
    @LastModifiedDate
    private LocalDateTime updated_at;

    private Long user_id;

    @Type(type = "jsonb")
    private String data;

    private boolean read_status;

}
