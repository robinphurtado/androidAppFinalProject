package com.example.myapplication.typeconverter;

import androidx.room.TypeConverter;

public class BooleanTypeConverter {

    @TypeConverter
    public boolean convertIntToBool(int value) {
        boolean flag;
        if (value == 1) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

    @TypeConverter
    public int convertBoolToInt(boolean bool) {
        int value;
        if (bool == true) {
            value = 1;
        } else {
            value = 0;
        }
        return value;
    }

}
