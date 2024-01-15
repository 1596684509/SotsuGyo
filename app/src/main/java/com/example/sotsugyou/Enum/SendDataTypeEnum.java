package com.example.sotsugyou.Enum;

public enum SendDataTypeEnum {

    DOLLUP(SendDataTypeEnum.DATATYPE_DOLLUP_CODE),
    USERLOGIN(SendDataTypeEnum.DATATYPE_USERLOGIN_CODE),
    USERREGISTER(SendDataTypeEnum.DATATYPE_USERREGISTER_CODE),
    USERICONUPDATA(SendDataTypeEnum.DATATYPE_USERUPDATAICON_CODE),
    USERNAMEUPDATA(SendDataTypeEnum.DATATYPE_USERUPDATANAME_CODE),
    USERPASSWORDUPDATA(SendDataTypeEnum.DATATYPE_USERPASSWORDUPDATA_CODE)

    ;
    public static final int DATATYPE_DOLLDOWN_CODE = 40040;
    public static final int DATATYPE_DOLLUP_CODE = 40041;
    public static final int DATATYPE_USERLOGIN_CODE = 40042;
    public static final int DATATYPE_USERREGISTER_CODE = 40043;
    public static final int DATATYPE_USERUPDATAICON_CODE = 40044;
    public static final int DATATYPE_USERUPDATANAME_CODE = 40045;
    public static final int DATATYPE_USERPASSWORDUPDATA_CODE = 40046;

    private int typeCode;

    SendDataTypeEnum(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getTypeCode() {
        return typeCode;
    }
}
