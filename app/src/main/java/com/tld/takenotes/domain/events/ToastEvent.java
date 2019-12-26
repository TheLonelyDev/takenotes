package com.tld.takenotes.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ToastEvent {
    @Getter
    @Setter
    private int resourceId;
}
