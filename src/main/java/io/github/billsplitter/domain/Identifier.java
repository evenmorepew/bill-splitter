package io.github.billsplitter.domain;

import lombok.Value;

@Value
class Identifier {

    String id;

    static Identifier fromString(String source) {
        return new Identifier(source);
    }
}
