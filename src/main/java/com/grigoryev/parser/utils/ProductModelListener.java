package com.grigoryev.parser.utils;

import com.grigoryev.parser.model.Product;
import com.grigoryev.parser.service.SequenceGeneratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ProductModelListener extends AbstractMongoEventListener<Product> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Product> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Product.SEQUENCE_NAME));
        }
    }

}
