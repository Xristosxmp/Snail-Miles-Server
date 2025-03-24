package com.snailmiles.app.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocketObject {
    @JsonProperty("user_id")
    String user_id;
}
