package io.github.billsplitter.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Entity
public class LineItem {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String uuid;

    @NotNull
    private String description;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal amount;
}
