package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.capabilities.HasId;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clients")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity implements HasId {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private String lastname;
    @Embedded
    private AddressEntity address;
    private String phone;
    private String instagram;
    private Integer score;
}
