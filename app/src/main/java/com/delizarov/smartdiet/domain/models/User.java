package com.delizarov.smartdiet.domain.models;

public class User {

    public static final User UNATHORIZED_USER = new User("", "");

    private String mId;
    private String mName;

    public User(String id, String name) {
        mId = id;
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (mId != null ? !mId.equals(user.mId) : user.mId != null) return false;
        return mName != null ? mName.equals(user.mName) : user.mName == null;
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        return result;
    }
}
