package com.usuario.gerenciamentodeusuario.configs;

import javax.persistence.AttributeConverter;
import java.util.UUID;

public class UUIDConverter implements AttributeConverter<UUID, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(UUID attribute) {
        return attribute.toString().getBytes();
    }

    @Override
    public UUID convertToEntityAttribute(byte[] dbData) {
        return UUID.fromString(new String(dbData));
    }

}
