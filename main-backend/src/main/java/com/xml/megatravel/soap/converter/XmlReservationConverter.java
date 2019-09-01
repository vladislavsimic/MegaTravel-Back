package com.xml.megatravel.soap.converter;

import com.xml.megatravel.dto.request.CreateReservationRequest;
import com.xml.megatravel.model.Reservation;
import com.xml.megatravel.model.User;
import com.xml.megatravel.soap.model.reservation.CreateReservationResponse;
import com.xml.megatravel.soap.model.reservation.GetReservationsResponse;
import com.xml.megatravel.soap.model.reservation.XmlReservation;
import com.xml.megatravel.soap.model.user.XmlUser;
import org.springframework.http.HttpStatus;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.xml.megatravel.soap.converter.XmlPropertyConverter.toXmlPropertyFromProperty;

public class XmlReservationConverter {

    public static CreateReservationRequest toCreateReservationRequestFromXmlCreateReservationRequest(com.xml.megatravel.soap.model.reservation.CreateReservationRequest request, User user) {
        return CreateReservationRequest.builder()
                .propertyId(UUID.fromString(request.getPropertyId()))
                .startDate(request.getStartDate().toGregorianCalendar().toZonedDateTime().toLocalDateTime())
                .endDate(request.getEndDate().toGregorianCalendar().toZonedDateTime().toLocalDateTime())
                .numberOfPeople(request.getNumberOfPeople())
                .price(request.getPrice())
                .user(user)
                .build();
    }

    public static GetReservationsResponse toGetReservationsResponseFromReservationsList(List<Reservation> reservations) {
        return GetReservationsResponse.builder()
                .reservations(reservations.stream().map(XmlReservationConverter::toXmlReservationFromReservation).collect(Collectors.toList()))
                .build();
    }

    public static XmlReservation toXmlReservationFromReservation(Reservation reservation) {
        try {
            final XMLGregorianCalendar startDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(reservation.getStartDate().atZone(ZoneId.systemDefault())));
            final XMLGregorianCalendar endDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(reservation.getEndDate().atZone(ZoneId.systemDefault())));

            final User user = reservation.getUser();

            final XmlUser xmlUser = XmlUser.builder()
                    .email(user.getEmail())
                    .firstNme(user.getFirstName())
                    .lastName(user.getLastName())
                    .build();

            xmlUser.setId(user.getId().toString());

            final XmlReservation xmlReservation =  XmlReservation.builder()
                    .numberOfPeople(reservation.getNumberOfPeople())
                    .endDate(endDate)
                    .startDate(startDate)
                    .price(reservation.getPrice())
                    .property(toXmlPropertyFromProperty(reservation.getProperty()))
                    .reservationStatus(reservation.getReservationStatus().toString())
                    .user(xmlUser)
                    .build();

            xmlReservation.setId(reservation.getId().toString());

            return xmlReservation;
        } catch (DatatypeConfigurationException e) {
            return null;
        }
    }

    public static CreateReservationResponse toCreateReservationResponseFromReservation(Reservation reservation, HttpStatus status) {
        return CreateReservationResponse.builder()
                .reservation(toXmlReservationFromReservation(reservation))
                .status(status.value())
                .build();
    }
}
