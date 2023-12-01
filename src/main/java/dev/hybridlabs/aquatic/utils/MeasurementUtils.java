package dev.hybridlabs.aquatic.utils;

public class MeasurementUtils {

    /**
     * Gets blocks in render-distance scale of measurement
     * @param distance the distance you want.
     * @return distances * 4 which turns into real-world block measurements
     */
    public static float Block(float distance) {
        return distance * 4;
    }
}
