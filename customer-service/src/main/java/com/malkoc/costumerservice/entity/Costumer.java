package com.malkoc.costumerservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Costumer {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
