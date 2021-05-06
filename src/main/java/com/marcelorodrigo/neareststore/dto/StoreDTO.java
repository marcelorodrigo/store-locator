package com.marcelorodrigo.neareststore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreDTO {
    private String uuid;
    private String todayOpen;
    private boolean showWarningMessage;
    private String addressName;
    private String locationType;
    private String street;
    private String street2;
    private String street3;
    private String complexNumber;
    private String postalCode;
    private String city;
    private double latitude;
    private double longitude;
    private boolean collectionPoint;
    private String todayClose;
}
