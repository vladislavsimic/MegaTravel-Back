package com.xml.megatravel.soap.contoller;

import com.xml.megatravel.model.Type;
import com.xml.megatravel.service.TypeService;
import com.xml.megatravel.soap.model.type.GetTypesRequest;
import com.xml.megatravel.soap.model.type.GetTypesResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

import static com.xml.megatravel.soap.converter.XmlTypeConverter.toGetTypesResponseFromTypesList;

@Endpoint
public class TypeWebService {
    private static final String NAMESPACE_URI = "http://www.xml.com/megatravel/soap/model/type";

    private final TypeService typeService;

    public TypeWebService(TypeService typeService) {
        this.typeService = typeService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTypesRequest")
    @ResponsePayload
    public GetTypesResponse getTypes(@RequestPayload GetTypesRequest request) {
        final List<Type> types = typeService.getTypes();
        return toGetTypesResponseFromTypesList(types);
    }


}
