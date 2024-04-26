package com.erc.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table()
public class NewsFed {
    @Id
    @SequenceGenerator(
            name = "news_fed_sequence",
            sequenceName = "news_fed_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "news_fed_sequence")
    private long id;
    private String message;
    private String notification;
    @JoinColumn(
            name = "id",
            referencedColumnName = "id"
    )
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false
    )
    private Company company;
}
