package com.marcelorodrigo.neareststore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Store {
    @Id
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
    private int sapStoreID;
    private String todayClose;
}
