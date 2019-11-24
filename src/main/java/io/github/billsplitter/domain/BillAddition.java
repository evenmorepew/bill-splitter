package io.github.billsplitter.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = BillAddition.BillAdditionBuilder.class)
public class BillAddition {

    String name;

    @JsonPOJOBuilder(withPrefix = "")
    public static class BillAdditionBuilder {

    }
}