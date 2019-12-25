package com.tld.takenotes.events;

import com.tld.takenotes.model.entity.Option;

import lombok.Getter;
import lombok.Setter;

public class OptionClicked {
    @Getter
    @Setter
    private Option option;
}
