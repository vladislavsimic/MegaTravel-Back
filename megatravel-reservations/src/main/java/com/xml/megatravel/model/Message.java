package com.xml.megatravel.model;

import com.xml.megatravel.model.enumeration.MessageType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

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
    @OneToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @NotNull
    @Type(type = "uuid-char")
    @Enumerated(EnumType.STRING)
    @Column(name = "sent_to_entity")
    private MessageType sentToEntity;

    @NotNull
    @Size(max = MESSAGE_SIZE)
    @Column(name = "content")
    private String content;

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "receiver_id")
    private UUID receiverId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
