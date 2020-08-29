package com.marcelorodrigo.neareststore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreDTO {

    private String uuid;
    private String addressName;
    private String street;
    private String street2;
    private String street3;
    private String city;
    private String postalCode;
    private String complexNumber;
    private boolean showWarningMessage;
    private double latitude;
    private double longitude;
    private String todayOpen;
    private String locationType;
    private boolean collectionPoint;
    private String todayClose;

}
