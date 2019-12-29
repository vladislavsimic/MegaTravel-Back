package com.xml.megatravel.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xml.megatravel.dto.AddressDto;
import com.xml.megatravel.model.enumeration.Category;
import com.xml.megatravel.util.DateTimeUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
@Getter
@Setter
public class CheckReservationDateResponse {

    private boolean reservationFree;

}
