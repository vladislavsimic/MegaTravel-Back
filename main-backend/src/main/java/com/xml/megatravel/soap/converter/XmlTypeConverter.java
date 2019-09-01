package com.xml.megatravel.soap.converter;

import com.xml.megatravel.model.Type;
import com.xml.megatravel.soap.model.type.GetTypesResponse;
import com.xml.megatravel.soap.model.type.XmlType;

import java.util.List;
import java.util.stream.Collectors;

public class XmlTypeConverter {

    public static GetTypesResponse toGetTypesResponseFromTypesList(List<Type> types) {
        return GetTypesResponse.builder()
                .types(types.stream().map(XmlTypeConverter::toXmlTypeFromType).collect(Collectors.toList()))
                .build();
    }

    public static XmlType toXmlTypeFromType(Type type) {
        final XmlType xmlType = XmlType.builder()
                .name(type.getName())
                .build();

        xmlType.setId(type.getId().toString());

        return xmlType;
    }
}
