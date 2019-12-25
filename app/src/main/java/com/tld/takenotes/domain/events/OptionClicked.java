package com.tld.takenotes.domain.events;

import com.tld.takenotes.model.Option;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class OptionClicked {
    @Getter
    @Setter
    private Option option;
}
