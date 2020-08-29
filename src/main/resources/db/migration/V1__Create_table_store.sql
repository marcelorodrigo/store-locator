CREATE TABLE store
(
    uuid                 varchar(255) not null,
    address_name         varchar(255),
    city                 varchar(255),
    collection_point     boolean      not null,
    complex_number       varchar(255),
    latitude             double       not null,
    location_type        varchar(255),
    longitude            double       not null,
    postal_code          varchar(255),
    sap_storeid          integer      not null,
    show_warning_message boolean      not null,
    street               varchar(255),
    street2              varchar(255),
    street3              varchar(255),
    today_close          varchar(255),
    today_open           varchar(255),
    primary key (uuid)
)