package com.liangbx.android.practice.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GithubUser implements Parcelable {
    public long id;
    public String name;
    public String url;
    public String email;
    public String login;
    public String location;
    @SerializedName("avatar_url")
    public String avatarUrl;

    public GithubUser() {
    }

    public boolean hasEmail() {
        return email != null && !email.isEmpty();
    }

    public boolean hasLocation() {
        return location != null && !location.isEmpty();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeString(this.email);
        dest.writeString(this.login);
        dest.writeString(this.location);
        dest.writeString(this.avatarUrl);
    }

    protected GithubUser(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.url = in.readString();
        this.email = in.readString();
        this.login = in.readString();
        this.location = in.readString();
        this.avatarUrl = in.readString();
    }

    public static final Creator<GithubUser> CREATOR = new Creator<GithubUser>() {
        public GithubUser createFromParcel(Parcel source) {
            return new GithubUser(source);
        }

        public GithubUser[] newArray(int size) {
            return new GithubUser[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GithubUser user = (GithubUser) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (url != null ? !url.equals(user.url) : user.url != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (location != null ? !location.equals(user.location) : user.location != null)
            return false;
        return !(avatarUrl != null ? !avatarUrl.equals(user.avatarUrl) : user.avatarUrl != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        return result;
    }
}
