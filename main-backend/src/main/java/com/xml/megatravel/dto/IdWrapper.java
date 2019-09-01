package com.xml.megatravel.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class IdWrapper {

    private UUID id;

    private IdWrapper(UUID id) {
        this.id = id;
    }

    public static IdWrapper of(UUID uuid) {
        return new IdWrapper(uuid);
    }
}
