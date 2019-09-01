package com.xml.megatravel.converter;

import com.xml.megatravel.dto.request.TypeRequest;
import com.xml.megatravel.dto.response.TypeResponse;
import com.xml.megatravel.model.Type;

import java.util.List;
import java.util.stream.Collectors;

public class TypeConverter {

    public static Type toTypeFromTypeRequest(TypeRequest request) {
        return Type.builder().name(request.getName()).build();
    }


    public static List<TypeResponse> toListTypeResponseFromListType(List<Type> services) {
        return services.stream().map(TypeConverter::toTypeResponseFromType).collect(Collectors.toList());
    }

    public static TypeResponse toTypeResponseFromType(Type type) {
        return TypeResponse.builder()
                .id(type.getId())
                .name(type.getName())
                .build();
    }
}
