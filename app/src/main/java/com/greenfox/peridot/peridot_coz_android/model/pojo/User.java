package com.greenfox.peridot.peridot_coz_android.model.pojo;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @SerializedName("UserName")
    String username;
    @SerializedName("Password")
    String password;

}
