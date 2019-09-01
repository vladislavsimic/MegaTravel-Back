package com.xml.megatravel.model;

import com.xml.megatravel.model.enumeration.MessageType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.xml.megatravel.util.ValidationConstants.MESSAGE_SIZE;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message")
@Where(clause = "is_deleted='false'")
public class Message extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sent_to_entity")
    private MessageType sentToEntity;

    @NotNull
    @Size(max = MESSAGE_SIZE)
    @Column(name = "content")
    private String content;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
