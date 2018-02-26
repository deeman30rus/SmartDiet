package com.delizarov.smartdiet.domain.models;


public class Grocery {

    public static final long UNREGISTERED_GROCERY_ID = -1;

    private long mId;
    private String mName;

    public Grocery(long id, String name) {
        mId = id;
        mName = name;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null || !(obj instanceof Grocery))
            return false;

        Grocery toCompare = (Grocery) obj;

        return mId == toCompare.mId &&
                mName.equals(toCompare.mName);
    }

    @Override
    public int hashCode() {
        return (int) mId;
    }
}
