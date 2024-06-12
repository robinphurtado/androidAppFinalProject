package com.example.myapplication.typeconverter;

import androidx.room.TypeConverter;

public class BooleanTypeConverter {

    @TypeConverter
    public boolean convertIntToBool(int value) {
        boolean bool;
        if (value == 1) {
            bool = true;
        } else {
            bool = false;
        }

        return bool;
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
