package com.epicodus.signin2.utiil;

import com.epicodus.signin2.models.BikeCollective;

public class ActiveBikeCollective {
    public static BikeCollective activeBikeCollective = null;

    public static BikeCollective getActiveBikeCollective() {
        return activeBikeCollective;
    }

    public static void setActiveBikeCollective(BikeCollective activeBikeCollective) {
        ActiveBikeCollective.activeBikeCollective = activeBikeCollective;
    }

    public static boolean isLoggedIn() {
        return activeBikeCollective != null;
    }

    public static void logout() {
        activeBikeCollective = null;
    }
}
