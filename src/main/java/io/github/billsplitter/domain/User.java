package io.github.billsplitter.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    Identifier identifier;
}
