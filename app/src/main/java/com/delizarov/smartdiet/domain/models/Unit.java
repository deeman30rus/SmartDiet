package com.delizarov.smartdiet.domain.models;


public enum Unit {

    Kilo(1), // кг
    Gram(2), // г
    Liter(3), // л
    MilliLiter(4), // мл
    TeaSpoon(5), // ч. ложка
    TableSpoon(6), // ст. ложка
    Piece(7), // шт.
    Optional(8); // по вкусу

    private long mType;

    Unit(int type) {
        mType = type;
    }

    public long type() {
        return mType;
    }

    public static Unit fromLong(long type) {

        for (Unit unit : Unit.values())
            if (unit.mType == type)
                return unit;

        return null;
    }
}
