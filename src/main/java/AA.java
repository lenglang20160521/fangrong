import component.Station;
import DTO.Stations;

public class AA {
    public static void main(String[] args) {

        String newToken = HttpClientUtil.getNewToken();
        Stations stations = HttpClientUtil.getStations(newToken);
        System.out.println(stations.getCode());
        System.out.println(stations.getMsg());
        System.out.println(stations.getStations());

        for (Station station: stations.getStations()){
            System.out.println(station.toString());
            System.out.println(station.toJson());
        }
    }

}
