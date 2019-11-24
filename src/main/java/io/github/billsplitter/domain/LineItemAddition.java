package io.github.billsplitter.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = LineItemAddition.LineItemAdditionBuilder.class)
public class LineItemAddition {

    private final String billUuid;
    private final String userUuid;
    private final String description;
    private final String amount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class LineItemAdditionBuilder {
    }
}
