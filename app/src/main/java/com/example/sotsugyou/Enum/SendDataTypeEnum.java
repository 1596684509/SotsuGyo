package com.example.sotsugyou.Enum;

public enum SendDataTypeEnum {

    DOLL(SendDataTypeEnum.DATATYPE_DOLL_CODE),
    USERLOGIN(SendDataTypeEnum.DATATYPE_USERLOGIN_CODE),
    USERREGISTER(SendDataTypeEnum.DATATYPE_USERREGISTER_CODE)

    ;
    public static final int DATATYPE_DOLL_CODE = 40041;
    public static final int DATATYPE_USERLOGIN_CODE = 40042;
    public static final int DATATYPE_USERREGISTER_CODE = 40043;

    private int typeCode;

    SendDataTypeEnum(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getTypeCode() {
        return typeCode;
    }
}
