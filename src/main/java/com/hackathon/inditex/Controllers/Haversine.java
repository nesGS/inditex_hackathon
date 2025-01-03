package com.hackathon.inditex.Controllers;

public class Haversine {
    private static final double EARTH_RADIUS_KM = 6371.0; // Radio de la Tierra en kilómetros

    /**
     * Calcula la distancia entre dos puntos dados por su latitud y longitud
     *
     * @param lat1 Latitud del primer punto en grados
     * @param lon1 Longitud del primer punto en grados
     * @param lat2 Latitud del segundo punto en grados
     * @param lon2 Longitud del segundo punto en grados
     * @return Distancia en kilómetros
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convertir grados a radianes
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Diferencias de latitudes y longitudes
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Aplicar fórmula de Haversine
        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(deltaLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calcular distancia
        return EARTH_RADIUS_KM * c;
    }

//    public static void main(String[] args) {
//        // Ejemplo: Distancia entre Madrid (40.4168, -3.7038) y París (48.8566, 2.3522)
//        double distance = calculateDistance(40.4168, -3.7038, 48.8566, 2.3522);
//        System.out.println("Distancia: " + distance + " km");
//    }
}