package com.grigoryev.parser.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "database_sequences")
public class DataBaseSequence {
    @Id
    private String id;

    private long seq;
}
